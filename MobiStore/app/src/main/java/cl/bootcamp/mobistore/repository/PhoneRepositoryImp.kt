package cl.bootcamp.mobistore.repository

import cl.bootcamp.mobistore.data.PhoneData
import cl.bootcamp.mobistore.model.Phone
import cl.bootcamp.mobistore.model.PhoneDao
import cl.bootcamp.mobistore.model.PhoneDetail
import cl.bootcamp.mobistore.model.PhonesRoom
import cl.bootcamp.mobistore.model.Product
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


interface PhoneRepository {
    suspend fun getPhonesApi(): ArrayList<Product>
    suspend fun getDetailsApi(): List<Phone>

    fun getPhonesRoom(): Flow<List<PhonesRoom>>
    fun getDetailsRoom(): Flow<List<PhoneDetail>>
    fun getPhoneById(id: Int): PhoneDetail


}

class PhoneRepositoryImp @Inject constructor(
    private val phoneData: PhoneData,
    private val phoneDao: PhoneDao
) : PhoneRepository {

    //Obtiene lista general de telefonos api

    override suspend fun getPhonesApi(): ArrayList<Product> {
        val data = phoneData.getPhones()
        data.forEach {
            val phoneRoom = PhonesRoom(
                id = it.id,
                name = it.name,
                price = it.price,
                image = it.image
            )
            phoneDao.insertPhone(phoneRoom)
        }
        return ArrayList(data)

    }


    //telefonos la tabla phones_table

    override fun getPhonesRoom(): Flow<List<PhonesRoom>> = phoneDao.getPhones()

    //telefonos la tabla phones_table
    override fun getDetailsRoom(): Flow<List<PhoneDetail>> = phoneDao.getAllDetails()

    //Obtiene detalle de cada telefono y lo inserta en la tabla "phone_detail"
    override suspend fun getDetailsApi(): List<Phone> {
        var list = listOf<Phone>()
        for (index in 1..10) {
            //Segundo endpoint de la api para obtener detalle
            val apiPhone = phoneData.getPhoneById(index).body()!!
            list = list + apiPhone
        }
        list.forEach { apiPhone ->
            val cellDao = PhoneDetail(
                apiPhone.id,
                apiPhone.name,
                apiPhone.price,
                apiPhone.image,
                apiPhone.description,
                apiPhone.lastPrice,
                apiPhone.credit
            )
            phoneDao.insertDetailPhone(cellDao)
        }
        return list
    }


    //Muestra detalle de un telefono dado su id

    override fun getPhoneById(id: Int): PhoneDetail {
        val list = phoneDao.getPhoneById(id)
        val res = PhoneDetail(
            list.id,
            list.name,
            list.price,
            list.image,
            list.description,
            list.lastPrice,
            list.credit
        )
        return res

    }
}








