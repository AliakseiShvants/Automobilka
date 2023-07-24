package by.shvants.avtomobilka.data.model

data class UserResponse(
    val about: String?,
    val auto_count: Int?,
    val avatar: AvatarResponse?,
    val email: String?,
    val id: Int?,
    val main_auto_name: String?,
    val username: String?
)