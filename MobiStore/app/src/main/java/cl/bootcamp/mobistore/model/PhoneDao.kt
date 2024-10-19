package cl.bootcamp.mobistore.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PhoneDao {
    @Query("SELECT * FROM phones_table")
    fun getPhones(): Flow<List<PhonesRoom>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPhone(phone: PhonesRoom)

    @Delete
    suspend fun deletePhone(phone: PhonesRoom)



    @Query("SELECT * FROM phone_detail ")
     fun getAllDetails(): Flow<List<PhoneDetail>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetailPhone(phone: PhoneDetail)

    @Query("SELECT * FROM phone_detail WHERE id = :id")
    fun getPhoneById(id: Int): PhoneDetail





}