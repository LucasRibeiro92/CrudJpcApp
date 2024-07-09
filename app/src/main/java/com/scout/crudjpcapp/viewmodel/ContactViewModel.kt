package com.scout.crudjpcapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.scout.crudjpcapp.model.data.Contact
import com.scout.crudjpcapp.model.repository.ContactRepository
import kotlinx.coroutines.launch

class ContactViewModel(
    private val contactRepository: ContactRepository
): ViewModel() {

    val allContacts: LiveData<List<Contact>> = contactRepository.allContacts

    fun insertContact(contact: Contact) {
        viewModelScope.launch {
            contactRepository.insertContact(contact)
        }
    }
}