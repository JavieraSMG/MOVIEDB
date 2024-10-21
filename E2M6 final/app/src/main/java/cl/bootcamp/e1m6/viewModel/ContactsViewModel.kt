package cl.bootcamp.e1m6.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.bootcamp.e1m6.model.Contact
import cl.bootcamp.e1m6.repository.ContactRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContactsViewModel @Inject constructor(private val repository: ContactRepository) : ViewModel() {

    private val _contactList = MutableStateFlow<List<Contact>>(emptyList())
    val contactList = _contactList.asStateFlow()

    var nameState by mutableStateOf("")
    var phoneState by mutableStateOf("")
    var mailState by mutableStateOf("")
    var imageState by mutableStateOf("")
    var birthState by mutableStateOf("")

    fun onNameChanged(newString: String) {
        nameState = newString
    }
    fun onPhoneChanged(newString: String) {
        phoneState = newString
    }
    fun onMailChanged(newString: String) {
        mailState = newString
    }
    fun onImageChanged(newString: String) {
        imageState = newString
        }
    fun onBirthChanged(newString: String) {
        birthState = newString
    }



    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllContacts().collect{item->
                if (item.isNullOrEmpty()){
                    _contactList.value = emptyList()
                }else{
                    _contactList.value = item
                }
            }
        }
    }

    fun addContact(contact: Contact)=viewModelScope.launch{repository.addContact(contact)}
    fun updateContact(contact: Contact)=viewModelScope.launch(Dispatchers.IO){repository.updateContact(contact)}
    fun deleteContact(contact: Contact)=viewModelScope.launch{repository.deleteContact(contact)}
    fun getContactById(id: Long): Flow<Contact> {return repository.getContactById(id)}




}