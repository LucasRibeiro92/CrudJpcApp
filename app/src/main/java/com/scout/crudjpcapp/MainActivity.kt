package com.scout.crudjpcapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.scout.crudjpcapp.model.data.Contact
import com.scout.crudjpcapp.ui.ContactItem
import com.scout.crudjpcapp.ui.theme.CrudJpcAppTheme
import com.scout.crudjpcapp.viewmodel.ContactViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val contactViewModel: ContactViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CrudJpcAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HomeScreen(contactViewModel)
                }
            }
        }
    }
}

@Composable
fun HomeScreen(contactViewModel: ContactViewModel) {
    val contacts by contactViewModel.allContacts.observeAsState(listOf())
    var name by remember { mutableStateOf("") }
    var number by remember { mutableStateOf("") }
    val TAG: String = "HomeScreen"

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
        ) {
        Text(
            text = "Add Contact.",
            fontSize = 24.sp
        )
        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") },
            modifier = Modifier
                .padding(bottom = 8.dp)
                .fillMaxWidth()
        )
        TextField(
            value = number,
            onValueChange = { number = it },
            label = { Text("Number") },
            modifier = Modifier
                .padding(bottom = 8.dp)
                .fillMaxWidth()
        )
        Button(
            onClick = {
                if (name.isNotEmpty() && number.isNotEmpty()) {
                    val contact = Contact(name = name, number = number.toInt())
                    contactViewModel.insertContact(contact)
                    name = ""
                    number = ""
                }
            },
            modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxWidth()
        ) {
            Text("Save Contact")
        }
        Text(
            text = "Contact list.",
            fontSize = 24.sp
        )
        Column {
            contacts.forEach { contact ->
                ContactItem(
                    contact,
                    onEdit = { editedContact ->
                        // Handle edit contact
                        // For simplicity, just reusing the same fields for the demo purpose
                        name = editedContact.name
                        number = editedContact.number.toString()
                        contactViewModel.deleteContact(editedContact)
                    },
                    onDelete = { deletedContact ->
                        contactViewModel.
                    }
                )
            }
        }
    }
}
