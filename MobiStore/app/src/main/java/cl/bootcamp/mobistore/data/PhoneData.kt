package cl.bootcamp.mobistore.data

import cl.bootcamp.mobistore.model.Phone
import cl.bootcamp.mobistore.model.Product
import cl.bootcamp.mobistore.util.Constants.Companion.ENDPOINT_DETAIL
import cl.bootcamp.mobistore.util.Constants.Companion.ENDPOINT_LIST
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PhoneData {

    @GET(ENDPOINT_LIST)
    suspend fun getPhones(): ArrayList<Product>

    @GET("$ENDPOINT_DETAIL{id}")
    suspend fun getPhoneById(@Path("id") id: Int): Response<Phone>
}