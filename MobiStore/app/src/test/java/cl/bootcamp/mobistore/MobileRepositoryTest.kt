package cl.bootcamp.mobistore

import android.arch.core.executor.testing.InstantTaskExecutorRule
import cl.bootcamp.mobistore.data.PhoneData
import cl.bootcamp.mobistore.model.PhoneDao
import cl.bootcamp.mobistore.model.PhoneDetail
import cl.bootcamp.mobistore.model.PhonesRoom
import cl.bootcamp.mobistore.repository.PhoneRepositoryImp
import cl.bootcamp.mobistore.util.Constants.Companion.ENDPOINT_DETAIL
import cl.bootcamp.mobistore.util.Constants.Companion.ENDPOINT_LIST
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.nio.charset.StandardCharsets


val phone1=PhonesRoom(1,"Samsung Galaxy A21s 64GB",167253,"")
val phone2=PhonesRoom(2,"Samsung Galaxy A2 32GB",99000,"")
class MobileRepositoryTest {


    private val mockWebServer = MockWebServer().apply {
        url("/")
        dispatcher = myDispatcher
    }

    private val restDataSource = Retrofit.Builder()
        .baseUrl(mockWebServer.url("/"))
        .client(OkHttpClient.Builder().build())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(PhoneData::class.java)

    private val newsRepository = PhoneRepositoryImp(restDataSource, MockPhoneDao())

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `Verificar si obtenemos correctamente los usuarios de la DB en la primera tabla`() =
        runBlocking {
            val cells = newsRepository.getPhonesRoom()
            assertEquals(cells.first(), listOf(phone1, phone2))

        }
}

      class MockPhoneDao : PhoneDao {
          private val cells = MutableStateFlow<List<PhonesRoom>>(listOf(phone1, phone2))

          override fun getPhones(): Flow<List<PhonesRoom>> =cells

          override suspend fun insertPhone(phone: PhonesRoom) {
              TODO("Not yet implemented")
          }

          override suspend fun deletePhone(phone: PhonesRoom) {
              TODO("Not yet implemented")
          }

          override fun getAllDetails(): Flow<List<PhoneDetail>> {
              TODO("Not yet implemented")
          }

          override suspend fun insertDetailPhone(phone: PhoneDetail) {
              TODO("Not yet implemented")
          }

          override fun getPhoneById(id: Int): PhoneDetail {
              TODO("Not yet implemented")
          }


      }


    val myDispatcher: Dispatcher = object : Dispatcher() {
        override fun dispatch(request: RecordedRequest): MockResponse {
            return when (request.path) {
                "/$ENDPOINT_LIST" -> MockResponse().apply { addResponse("api_list.json") }
                "$ENDPOINT_DETAIL/1" -> MockResponse().apply { addResponse("api_detail.json") }

                else -> MockResponse().setResponseCode(404)

            }
        }
    }

    fun MockResponse.addResponse(filePath: String): MockResponse {
        val inputStream = javaClass.classLoader?.getResourceAsStream(filePath)
        val source = inputStream?.source()?.buffer()
        source?.let {
            setResponseCode(200)
            setBody(it.readString(StandardCharsets.UTF_8))
        }
        return this
    }

