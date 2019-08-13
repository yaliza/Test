package by.itechart.android.data.repository

import android.util.SparseArray
import androidx.core.util.forEach
import androidx.core.util.set
import by.itechart.android.data.api.FacebookApi
import by.itechart.android.data.entity.*
import com.facebook.AccessToken
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInStatusCodes
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.firestore.ktx.toObjects
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.subjects.BehaviorSubject
import retrofit2.Response
import java.util.concurrent.TimeUnit


class Repository(
    private val facebookApi: FacebookApi,
    private val userHelper: UserHelper,
    firestore: FirebaseFirestore
) {

    val user: User
        get() = userHelper.user ?: throw IllegalArgumentException("No user detected")

    private val modalCardsSubj: BehaviorSubject<List<ModalCard>> = BehaviorSubject.create()
    private val topicsSubj: BehaviorSubject<List<Topic>> = BehaviorSubject.create()
    private val certificatesSubj: BehaviorSubject<List<Certificate>> = BehaviorSubject.create()
    private val scoresSubj: BehaviorSubject<List<Score>> = BehaviorSubject.create()
    private val levelsSubj: BehaviorSubject<List<Level>> = BehaviorSubject.create()
    private val followersSubj: BehaviorSubject<List<Sociable>> = BehaviorSubject.create()
    private val followingSubj: BehaviorSubject<List<Sociable>> = BehaviorSubject.create()
    private val invitationsSubj: BehaviorSubject<List<Sociable>> = BehaviorSubject.create()

    init {
        with(firestore){
            collection("followers").orderBy("name").addSnapshotListener { snap, exc ->
                exc?.let { followersSubj.onError(it) }
                    ?: snap?.let { followersSubj.onNext(it.parseSociable(SociableType.FOLLOWER)) }
            }
            collection("following").orderBy("name").addSnapshotListener { snap, exc ->
                exc?.let { followersSubj.onError(it) }
                    ?: snap?.let { followingSubj.onNext(it.parseSociable(SociableType.FOLLOWING)) }
            }
            collection("invites").orderBy("name").addSnapshotListener { snap, exc ->
                exc?.let { followersSubj.onError(it) }
                    ?: snap?.let { invitationsSubj.onNext(it.parseSociable(SociableType.INVITE)) }
            }

            collection("levels").orderBy("level").addSnapshotListener { snap, exc ->
                exc?.let { followersSubj.onError(it) } ?: snap?.let { levelsSubj.onNext(it.parseLevels()) }
            }

            collection("certificates").addSnapshotListener { snap, exc ->
                exc?.let { followersSubj.onError(it) } ?: snap?.let { certificatesSubj.onNext(it.toObjects()) }
            }

            collection("scores").addSnapshotListener { snap, exc ->
                exc?.let { followersSubj.onError(it) } ?: snap?.let { scoresSubj.onNext(it.toObjects()) }
            }

            collection("modal").addSnapshotListener { snap, exc ->
                exc?.let { followersSubj.onError(it) } ?: snap?.let { modalCardsSubj.onNext(it.toObjects()) }
            }

            collection("topics").orderBy("order").addSnapshotListener { snap, exc ->
                exc?.let { followersSubj.onError(it) } ?: snap?.let { topicsSubj.onNext(it.toObjects()) }
            }
        }
    }

    fun getLevels(): Flowable<List<Level>> = levelsSubj.hide()
        .toFlowable(BackpressureStrategy.LATEST)
        .delay(1, TimeUnit.SECONDS)

    fun getModalCards(): Flowable<List<ModalCard>> = modalCardsSubj.hide()
        .toFlowable(BackpressureStrategy.LATEST)
        .delay(1, TimeUnit.SECONDS)

    fun getTopics(): Flowable<List<Topic>> = topicsSubj.hide()
        .toFlowable(BackpressureStrategy.LATEST)
        .delay(1, TimeUnit.SECONDS)

    fun getCertificates(): Flowable<List<Certificate>> = certificatesSubj.hide()
        .toFlowable(BackpressureStrategy.LATEST)

    fun getScores(): Flowable<List<Score>> = scoresSubj.hide()
        .toFlowable(BackpressureStrategy.LATEST)

    fun getFollowers(): Flowable<List<Sociable>> = followersSubj.hide()
        .toFlowable(BackpressureStrategy.LATEST)

    fun getFollowing(): Flowable<List<Sociable>> = followingSubj.hide()
        .toFlowable(BackpressureStrategy.LATEST)

    fun getInvitations(): Flowable<List<Sociable>> = invitationsSubj.hide()
        .toFlowable(BackpressureStrategy.LATEST)

    fun getFacebookUser(accessToken: AccessToken): Single<Response<FacebookResponseUser>> =
        facebookApi.getProfile(accessToken.token)
            .doOnSuccess { response -> response.body()?.let { userHelper.user = User(it) } }
            .delay(1, TimeUnit.SECONDS)

    fun getGoogleUser(task: Task<GoogleSignInAccount>): Single<GoogleSignInAccount> =
        Single.create<GoogleSignInAccount> { emitter ->
            try {
                task.getResult(ApiException::class.java)?.let { emitter.onSuccess(it) }
            } catch (apiException: ApiException) {
                if (apiException.statusCode != GoogleSignInStatusCodes.SIGN_IN_CANCELLED) {
                    emitter.onError(apiException)
                }
            }
        }.doOnSuccess { userHelper.user = User(it) }

    fun getGoogleUser(user: GoogleSignInAccount): Single<GoogleSignInAccount> =
        Single.just(user)
            .doOnSuccess { userHelper.user = User(it) }
            .delay(1, TimeUnit.SECONDS)

    private fun QuerySnapshot.parseSociable(sociableType: SociableType) =
        mutableListOf<Sociable>().apply {
            documents.forEach {
                it?.toObject<Sociable>()?.apply {
                    type = sociableType
                    add(this)
                }
            }
        }

    private fun QuerySnapshot.parseLevels(): List<Level> {
        val levels = SparseArray<Level>()
        documents.forEach { snap: DocumentSnapshot ->
            val level = (snap["level"] as Long).toInt()
            if (levels[level] == null) levels[level] = Level()
            when (snap["type"]) {
                "section" -> snap.toObject<Section>()?.let { levels[level].sections.add(it) }
                "level" ->
                    snap.toObject<Level>()?.apply {
                        sections = levels[level].sections
                        levels[level] = this
                    }
            }
        }
        return mutableListOf<Level>().apply { levels.forEach { key, value -> add(value) } }
    }

}