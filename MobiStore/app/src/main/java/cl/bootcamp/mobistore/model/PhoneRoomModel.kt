package cl.bootcamp.mobistore.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "phones_table")
data class PhonesRoom(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "price")val price: Int,
    @ColumnInfo(name = "image")val image: String
)

@Entity(tableName = "phone_detail")
data class PhoneDetail(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "price")val price: Int,
    @ColumnInfo(name = "image")val image: String,
    @ColumnInfo(name = "description")val description: String,
    @ColumnInfo(name = "lastPrice")val lastPrice: Int,
    @ColumnInfo(name = "credit") val credit: Boolean
)


