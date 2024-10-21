package cl.bootcamp.e1m6.views

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import cl.bootcamp.e1m6.components.ContactCard
import cl.bootcamp.e1m6.components.TopBar
import cl.bootcamp.e1m6.model.Contact
import cl.bootcamp.e1m6.viewModel.ContactsViewModel

@Composable
fun MainView(viewModel: ContactsViewModel, navController: NavController){


    Scaffold(
        topBar = { TopBar("Lista de Contactos") },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {navController.navigate("AddUserView" + "/0L") }
            ) {

                Icon(
                    Icons.Filled.Add, "Floating action button.",
                    tint = Color(0xFFFFFFFF)
                )

            }
        }

    ) {
        HomeContent(viewModel = viewModel, navController = navController, paddingValues =it )


    }
}

@Composable
fun HomeContent(viewModel: ContactsViewModel, navController: NavController,paddingValues: PaddingValues) {
    val contact=Contact(0,"Name","Phone","Mail","https://laboratoriosniam.com/wp-content/uploads/2018/07/michael-dam-258165-unsplash_WEB2.jpg","nacimiento")
    ContactCard(contact = contact, onEdit = {}, onDelete = {},paddingValues)

    val userList= viewModel.contactList.collectAsState(initial = listOf())
    LazyColumn(){
        items(userList.value){contact->
            ContactCard(
                contact = contact,
                onEdit = {
                    val id = contact.id
                    navController.navigate("AddUserView/$id")},
                onDelete = {viewModel.deleteContact(contact)},
                paddingValues
            )
        }
    }




}


