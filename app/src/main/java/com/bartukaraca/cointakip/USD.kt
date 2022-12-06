package com.bartukaraca.cointakip

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class USD(

    @SerialName("price")
    val price: Double,
    @SerialName("volume_24h")
    val volume_24h: Double,


)