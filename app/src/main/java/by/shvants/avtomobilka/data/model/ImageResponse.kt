package by.shvants.avtomobilka.data.model

import com.google.gson.annotations.SerializedName

data class ImageResponse(
    val id: Int?,
    val image100: String?,
    val image500: String?,
    val index: Int?,

    @SerializedName("is_primary")
    val isPrimary: Boolean?,
    val size: Int?,

    @SerializedName("thumbnail_url")
    val thumbnailUrl: String?,
    val url: String?
)