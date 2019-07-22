package by.itechart.android.data.entity


class FacebookResponseUser(
    val id: String,
    val name: String,
    val email: String,
    val picture: Picture
) {
    fun toUser() = User(id, name, email, picture.data.url)
}

class Picture(val data: ImageData)

class ImageData(val url: String)


