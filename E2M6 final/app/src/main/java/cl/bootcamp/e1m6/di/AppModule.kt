package cl.bootcamp.e1m6.di

import android.content.Context
import androidx.room.Room
import cl.bootcamp.e1m6.room.ContactsDataBase
import cl.bootcamp.e1m6.room.ContactsDataBaseDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providesContactsDao(contactsDataBase: ContactsDataBase): ContactsDataBaseDao {
        return contactsDataBase.contactsDao()

    }
    @Singleton
    @Provides
    fun providesContactsDataBase(@ApplicationContext context: Context): ContactsDataBase {
        return Room.databaseBuilder(
            context,
            ContactsDataBase::class.java,
            "contacts_db"
        ).fallbackToDestructiveMigration().build()
    }


}