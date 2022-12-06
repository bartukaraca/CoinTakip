package com.bartukaraca.cointakip

import com.bartukaraca.cointakip.Quotes
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CryptoModel(

    val id: String,
    @SerialName("last_updated")
    val name: String,
    @SerialName("quotes")
    val quotes: Quotes,
    @SerialName("rank")
    val rank: Int,
    @SerialName("symbol")
    val symbol: String,


    )