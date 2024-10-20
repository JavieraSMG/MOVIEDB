package cl.bootcamp.e1m6.repository

import cl.bootcamp.e1m6.model.Contact
import cl.bootcamp.e1m6.room.ContactsDataBaseDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ContactRepository @Inject constructor(private val contactDao: ContactsDataBaseDao)  {
suspend fun addContact(contact: Contact) =contactDao.insert(contact)
suspend fun updateContact(contact: Contact) =contactDao.update(contact)
    suspend fun deleteContact(contact: Contact) =contactDao.delete(contact)
    fun getAllContacts(): Flow<List<Contact>> =contactDao.getContacts().flowOn(Dispatchers.IO).conflate()
    fun getContactById(id: String): Flow<Contact> =contactDao.getContactById(id).flowOn(Dispatchers.IO).conflate()

}