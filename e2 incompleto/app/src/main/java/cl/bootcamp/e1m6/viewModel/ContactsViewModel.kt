package cl.bootcamp.e1m6.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.bootcamp.e1m6.model.Contact
import cl.bootcamp.e1m6.repository.ContactRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContactsViewModel @Inject constructor(private val repository: ContactRepository) : ViewModel() {

    private val _contactList = MutableStateFlow<List<Contact>>(emptyList())
    val contactList = _contactList.asStateFlow()


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




}