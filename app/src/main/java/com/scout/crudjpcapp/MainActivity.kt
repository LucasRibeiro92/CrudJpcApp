package com.scout.crudjpcapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.scout.crudjpcapp.model.data.Contact
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

    Column(
        modifier = Modifier.padding(16.dp),

        ) {
        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") },
            modifier = Modifier.padding(bottom = 8.dp)
        )
        TextField(
            value = number,
            onValueChange = { number = it },
            label = { Text("Number") },
            modifier = Modifier.padding(bottom = 8.dp)
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
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text("Save Contact")
        }
    }
    Column {
        contacts.forEach { user ->
            Text(text = "${user.name}, ${user.number}")
            // Add buttons for update and delete operations
        }

        // Add TextField and Button for adding new users
    }
}
