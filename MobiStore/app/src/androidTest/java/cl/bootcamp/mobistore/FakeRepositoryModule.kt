package cl.bootcamp.mobistore

import cl.bootcamp.mobistore.di.RepositoryModule
import cl.bootcamp.mobistore.model.Phone
import cl.bootcamp.mobistore.model.PhoneDetail
import cl.bootcamp.mobistore.model.PhonesRoom
import cl.bootcamp.mobistore.model.Product
import cl.bootcamp.mobistore.repository.PhoneRepository
import cl.bootcamp.mobistore.viewModel.PhoneViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [RepositoryModule::class]
)
class FakeRepositoryModule {

    @Provides
    @Singleton
    fun mobileRepository(): PhoneRepository = object: PhoneRepository{
        private val phones = MutableStateFlow<List<PhonesRoom>>(emptyList())
        override suspend fun getPhonesApi(): ArrayList<Product> {
            TODO("Not yet implemented")
        }

        override suspend fun getDetailsApi(): List<Phone> {
            TODO("Not yet implemented")
        }

        override fun getPhonesRoom(): Flow<List<PhonesRoom>> {
            TODO("Not yet implemented")
        }

        override fun getDetailsRoom(): Flow<List<PhoneDetail>> {
            TODO("Not yet implemented")
        }

        override fun getPhoneById(id: Int): PhoneDetail {
            TODO("Not yet implemented")
        }


    }

}



