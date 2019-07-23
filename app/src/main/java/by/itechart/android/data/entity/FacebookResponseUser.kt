package by.itechart.android.data.entity


class FacebookResponseUser(
    val id: String,
    val name: String,
    val email: String,
    val picture: Picture
)

class Picture(val data: ImageData)

class ImageData(val url: String)