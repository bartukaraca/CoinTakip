package com.bartukaraca.cointakip

import com.bartukaraca.cointakip.CryptoModel
import retrofit2.Call
import retrofit2.http.GET

interface CryptoAPI {
    //https://pro-api.coinmarketcap.com/v1/cryptocurrency/listings/latest?CMC_PRO_API_KEY=2372342d-2eb3-4242-95cb-ebf8d0415750
//https://api.coinpaprika.com/v1/tickers
    //https://api.coincap.io/v2/assets
    //https://api.coincap.io/v2/markets
    //https://api.coinpaprika.com/v1/tickers


    @GET("v1/tickers")

    fun getData(): Call<List<CryptoModel>>

}