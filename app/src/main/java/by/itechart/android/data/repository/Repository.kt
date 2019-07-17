package by.itechart.android.data.repository

import by.itechart.android.data.mock.ModalCards
import by.itechart.android.ui.entity.ModalCardItem
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.subjects.BehaviorSubject


class Repository {

    private val modalCardsSubj: BehaviorSubject<List<ModalCardItem>> = BehaviorSubject.create()

    init {
        modalCardsSubj.onNext(ModalCards.mock)
    }

    fun getModalCards(): Flowable<List<ModalCardItem>> = modalCardsSubj.hide().toFlowable(BackpressureStrategy.LATEST)

}