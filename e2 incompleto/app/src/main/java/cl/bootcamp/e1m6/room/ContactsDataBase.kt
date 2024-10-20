package cl.bootcamp.e1m6.room

import androidx.room.Database
import androidx.room.RoomDatabase
import cl.bootcamp.e1m6.model.Contact

@Database(entities = [Contact::class], version = 1, exportSchema = false)
abstract class ContactsDataBase:RoomDatabase() {
    abstract fun contactsDao(): ContactsDataBaseDao

}