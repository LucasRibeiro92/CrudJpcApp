package com.scout.crudjpcapp.model.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.scout.crudjpcapp.model.data.Contact
import com.scout.crudjpcapp.model.data.ContactDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.UUID

class ContactRepository(private val contactDao: ContactDao) {

    private val TAG: String = "Contact_Repo"

    val allContacts: LiveData<List<Contact>> = contactDao.getAllContacts()

    suspend fun insertContact(contact: Contact) {
        withContext(Dispatchers.IO) {
            try {
                contactDao.insert(contact)
            } catch (e: Exception) {
                e.message?.let { Log.d(TAG, it) }
            }
        }
    }
}