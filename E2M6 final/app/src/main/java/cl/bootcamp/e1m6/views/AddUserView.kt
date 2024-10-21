package cl.bootcamp.e1m6.views
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.foundation.layout.padding
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import cl.bootcamp.e1m6.components.ContactTextField
import cl.bootcamp.e1m6.components.Space
import cl.bootcamp.e1m6.components.TopBar
import cl.bootcamp.e1m6.model.Contact
import cl.bootcamp.e1m6.viewModel.ContactsViewModel
import kotlinx.coroutines.launch





@Composable
fun AddUserView(id: Long,viewModel: ContactsViewModel, navController: NavController) {

    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    if (id != 0L) {
        val contact = viewModel.getContactById(id).collectAsState(initial = Contact(0L, "", "", "", "", ""))
        viewModel.nameState = contact.value.name
        viewModel.phoneState = contact.value.phone
        viewModel.mailState = contact.value.mail
        viewModel.imageState = contact.value.image
        viewModel.birthState = contact.value.birth

    } else {
        viewModel.nameState = ""
        viewModel.phoneState = ""
        viewModel.mailState = ""
        viewModel.imageState = ""
        viewModel.birthState = ""
    }

    Scaffold(
        topBar = { TopBar(
            if (id != 0L) "Actualizar Contacto"
            else "Agregar Contacto"
        ) {
            navController.navigateUp()
        } },
        scaffoldState = scaffoldState
    ) {
        Column(
           androidx.compose.ui.Modifier.padding(it),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Space()
           ContactTextField(
                label = "Nombre:",
                value = viewModel.nameState,
                onValueChanged = {
                    viewModel.onNameChanged(it)
                }
            )

            Space()
            ContactTextField(
                label = "Fono:",
                value = viewModel.phoneState,
                onValueChanged = {
                    viewModel.onPhoneChanged(it)
                }
            )
            Space()
            ContactTextField(
                label = "Email:",
                value = viewModel.mailState,
                onValueChanged = {
                    viewModel.onMailChanged(it)
                }
            )
            Space()
            ContactTextField(
                label = "Imagen:",
                value = viewModel.imageState,
                onValueChanged = {
                    viewModel.onImageChanged(it)
                }
            )
            Space()
            ContactTextField(
                label = "Cumpleaños:",
                value = viewModel.birthState,
                onValueChanged = {
                    viewModel.onBirthChanged(it)
                }
            )

            Space()
            Button(
                onClick = {
                    if (viewModel.nameState.isNotEmpty() && viewModel.mailState.isNotEmpty()
                        && viewModel.phoneState.isNotEmpty() && viewModel.imageState.isNotEmpty()
                        && viewModel.birthState.isNotEmpty()) {
                        if (id != 0L) {

                            viewModel.updateContact(
                                Contact(
                                    id = id,
                                    name = viewModel.nameState.trim(),
                                    phone = viewModel.phoneState.trim(),
                                    mail = viewModel.mailState.trim(),
                                    image = viewModel.imageState.trim(),
                                    birth = viewModel.birthState.trim()
                                )
                            )
                        } else {

                            viewModel.addContact(
                               Contact(
                                    name = viewModel.nameState.trim(),
                                   phone = viewModel.phoneState.trim(),
                                   mail = viewModel.mailState.trim(),
                                   image = viewModel.imageState.trim(),
                                   birth = viewModel.birthState.trim()

                                )
                            )

                        }
                    }
                    scope.launch {
                        navController.navigateUp()
                    }
                }
            ) {
                Text(
                    text = if (id != 0L) "Actualizar Contacto"
                    else "Agregar Contacto",
                    fontSize = 18.sp
                )
            }
        }
    }



}


