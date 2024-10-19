package cl.bootcamp.mobistore.data

import androidx.room.Database
import androidx.room.RoomDatabase
import cl.bootcamp.mobistore.model.PhoneDao
import cl.bootcamp.mobistore.model.PhoneDetail
import cl.bootcamp.mobistore.model.PhonesRoom

@Database(
    entities = [PhonesRoom::class, PhoneDetail::class],
    version = 1, exportSchema = false
)


abstract class DbData: RoomDatabase() {
    abstract fun phoneDao(): PhoneDao

}