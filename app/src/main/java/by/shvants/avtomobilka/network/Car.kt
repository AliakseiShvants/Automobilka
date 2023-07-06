package by.shvants.avtomobilka.network

import com.google.gson.annotations.SerializedName

//data class Car(
//    val id: Int?,
//    val isFoeSale: Boolean?,
//    val brandName: String?,
//    val engineName: String?,
//)
data class CarResponse(
//    @SerializedName("brand_id")
    val brand_id: Int,

//    @SerializedName("brand_id")
    val brand_name: String,

//    @SerializedName("brand_id")
    val city_name: String,

//    @SerializedName("brand_id")
    val country_name: String,

//    @SerializedName("brand_id")
    val engine: String,
    val engine_id: Int,
    val engine_name: String,
    val engine_volume: String,
    val for_sale: Int,
    val id: Int,
    val image: String,
    val images: List<Image>,
    val model_id: Int,
    val model_name: String,
    val name: String,
    val place_id: String,
    val place_name: String,
    val price: Any,
    val thumbnail: String,
    val transmission_id: Int,
    val transmission_name: String,
    val year: Int
)

data class Image(
    val id: Int,
    val image100: String,
    val image500: String,
    val index: Int,
    val is_primary: Boolean,
    val size: Int,
    val thumbnail_url: String,
    val url: String
)