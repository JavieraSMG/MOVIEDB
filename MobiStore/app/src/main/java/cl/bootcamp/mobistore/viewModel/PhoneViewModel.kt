package cl.bootcamp.mobistore.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.bootcamp.mobistore.model.Phone
import cl.bootcamp.mobistore.model.PhoneDetail
import cl.bootcamp.mobistore.model.PhonesRoom
import cl.bootcamp.mobistore.model.Product
import cl.bootcamp.mobistore.repository.PhoneRepository
import cl.bootcamp.mobistore.state.PhoneState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class PhoneViewModel @Inject constructor(
    private val phoneRepo: PhoneRepository
) : ViewModel() {


    private val _phones = MutableStateFlow<List<Product>>(emptyList())
    val phones = _phones.asStateFlow()

    private val _cells = MutableStateFlow<List<Phone>>(emptyList())
    val cells = _cells.asStateFlow()

    private val _phonesRoom = phoneRepo.getPhonesRoom()
    val phonesRoom = _phonesRoom


    var state by mutableStateOf(PhoneState())
        private set


    init {
        fetchPhones()
        fetchDetails()

    }

    //Obtener todos los datos para la tabla phones_table

    private fun fetchPhones() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val result = phoneRepo.getPhonesApi()
                _phones.value = result ?: emptyList()
                println(result)
            }
        }
    }

    //Obtener todos los detalles para la tabla phone_detail

    private fun fetchDetails() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val result = phoneRepo.getDetailsApi()
                _cells.value = result ?: emptyList()
                println(result)

            }
        }
    }



//limpiar estado
    fun clean() {
        state = PhoneState()
    }

    //Por que corrutinas aca?

    fun getDetailsById(id: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val cell = phoneRepo.getPhoneById(id)
                state = state.copy(
                    id = cell.id,
                    name = cell.name,
                    price = cell.price,
                    image = cell.image,
                    description = cell.description,
                    lastPrice = cell.lastPrice,
                    credit = cell.credit

                )

            }

        }
    }


    //Se uso el de BD local :)
    fun getPhoneById(id: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val cell = phoneRepo.getPhoneById(id)
                state = state.copy(
                    id = cell.id,
                    name = cell.name,
                    price = cell.price,
                    image = cell.image,
                    description = cell.description,
                    lastPrice = cell.lastPrice,
                    credit = cell.credit
                )
            }
        }
    }
}







