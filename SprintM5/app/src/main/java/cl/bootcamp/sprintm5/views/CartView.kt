package cl.bootcamp.sprintm5.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import cl.bootcamp.sprintm5.components.CartCard
import cl.bootcamp.sprintm5.viewmodel.ProductViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartView(navController: NavController,viewModel:ProductViewModel) {
    val productList = viewModel.products

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("Carrito de Compras")
                }
            )
        },

        ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding),


            ) {
            //Falta actualizacion inmediata
            IconButton(
                onClick = { viewModel.clearProducts() },
                Modifier.align(Alignment.End)
            ) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription ="Delete All" )
                }

                        LazyColumn (
                        modifier = Modifier
                            .weight(1f)
                            .padding(innerPadding),
                verticalArrangement = Arrangement.spacedBy(5.dp),

                ) {
                items(productList) { item ->
                    CartCard(item,
                        onDelete = { viewModel.removeProduct(item) })
                }

            }
            Box(modifier = Modifier.padding(16.dp)){
                Text(
                    text = "Total: ${viewModel.getTotalPrice()}"
                )


            }

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                onClick = { navController.navigate("Home") }) {
                Text(text = "IR A MENU")

            }



            Spacer(modifier =Modifier.padding(20.dp))


            }

        }
    }



