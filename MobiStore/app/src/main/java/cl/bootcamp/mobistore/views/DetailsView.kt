package cl.bootcamp.mobistore.views

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import cl.bootcamp.mobistore.R
import cl.bootcamp.mobistore.components.DetailedCard
import cl.bootcamp.mobistore.components.TopBarComponent
import cl.bootcamp.mobistore.viewModel.PhoneViewModel
import android.content.Intent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.ui.res.colorResource


@Composable
fun DetailsView(
    viewModel: PhoneViewModel,
    navController: NavController,
    id: Int

) {
    val context = LocalContext.current
    val email = stringResource(R.string.mail)


    Scaffold(

        topBar = {
            TopBarComponent(
                titulo = stringResource(R.string.Details),
                mostrarBotton = true
            ) {
                viewModel.clean()
                navController.popBackStack()
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                containerColor = colorResource(R.color.c3),
                onClick = {
                    val state = viewModel.state
                    val name = state.name
                    val id = state.id

                    var asunto = "Consulta - $name - Id:$id"
                    var mensaje = """
                                Hola Me gustaría obtener más información del móvil ${name} 
                                de código ${id}. Quedo atento
                                """.trimIndent()


                    val intent = Intent(Intent.ACTION_SEND)
                    intent.putExtra(Intent.EXTRA_EMAIL, email)
                    intent.putExtra(Intent.EXTRA_SUBJECT, asunto)
                    intent.putExtra(Intent.EXTRA_TEXT, mensaje)
                    intent.type = "message/rfc822"
                    context.startActivity(Intent.createChooser(intent, ""))
                }) {


                Icon(
                    Icons.Filled.Add, "Floating action button.",
                    tint = colorResource(R.color.c2)

                )


            }
        }


    ) {
        ContentDetailsView(it, viewModel, id)

    }
}




@Composable
fun ContentDetailsView(
    paddingValues: PaddingValues,
    viewModel: PhoneViewModel,
    id: Int
) {
    LaunchedEffect(Unit) {
        viewModel.getPhoneById(id)
    }

    val state = viewModel.state
    DetailedCard(cell = state, paddingValues = paddingValues)
}




