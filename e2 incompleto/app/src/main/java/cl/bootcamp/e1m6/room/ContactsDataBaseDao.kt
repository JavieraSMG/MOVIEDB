package cl.bootcamp.e1m6.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import cl.bootcamp.e1m6.model.Contact
import kotlinx.coroutines.flow.Flow


@Dao
interface ContactsDataBaseDao {

    @Query("SELECT * from Contactos")
    fun getContacts(): Flow<List<Contact>>

    @Query("SELECT * from Contactos where id =:id")
    fun getContactById(id:String): Flow<Contact>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(contact: Contact)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(contact: Contact)

    @Delete
    suspend fun delete(contact: Contact)




}