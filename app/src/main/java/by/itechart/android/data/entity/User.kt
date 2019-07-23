package by.itechart.android.data.entity

import com.google.android.gms.auth.api.signin.GoogleSignInAccount

class User(
        val id: String,
        val name: String,
        val email: String,
        val photoUrl: String?
) {

    constructor(gsa: GoogleSignInAccount) : this(
            id = gsa.id ?: "",
            name = gsa.displayName ?: "",
            email = gsa.email ?: "",
            photoUrl = gsa.photoUrl.toString()
    )

    constructor(fru: FacebookResponseUser) : this(
            id = fru.id,
            name = fru.name,
            email = fru.email,
            photoUrl = fru.picture.data.url
    )

}