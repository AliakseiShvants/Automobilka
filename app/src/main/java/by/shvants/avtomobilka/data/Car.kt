package by.shvants.avtomobilka.data

import by.shvants.avtomobilka.network.Image
import com.google.gson.annotations.SerializedName

data class Car(
    val id: Int,
//    val brandName: String,
//    val engine: String,
//    val isForSale: Boolean,
    val image: String,
    val name: String,
)
