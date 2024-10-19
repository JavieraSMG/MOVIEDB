package cl.bootcamp.mobistore.di

import android.content.Context
import androidx.room.Room
import cl.bootcamp.mobistore.data.DbData
import cl.bootcamp.mobistore.data.PhoneData
import cl.bootcamp.mobistore.model.PhoneDao
import cl.bootcamp.mobistore.util.Constants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {


    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): PhoneData {
        return retrofit.create(PhoneData::class.java)
    }

    @Provides
    @Singleton
    fun dbDataSource(@ApplicationContext context: Context): DbData {
        return Room.databaseBuilder(context, DbData::class.java, "Phone_database")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun providePhoneDao(db: DbData): PhoneDao = db.phoneDao()



}