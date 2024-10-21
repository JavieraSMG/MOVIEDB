package cl.bootcamp.e1m6.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Contactos")
data class Contact(
    @PrimaryKey(autoGenerate = true)
    val id:Long=0,
    @ColumnInfo(name = "Nombre")
    val name:String="",
    @ColumnInfo(name = "Telefono")
    val phone:String,
    @ColumnInfo(name = "E-mail")
    val mail:String,
    @ColumnInfo(name = "Imagen de Contacto")
    val image:String,
    @ColumnInfo(name = "Fecha de Nacimiento")
    val birth:String
)
