package com.bartukaraca.cointakip

import com.bartukaraca.cointakip.USD
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Quotes(

    @SerialName("USD")
    val USD: USD
)




