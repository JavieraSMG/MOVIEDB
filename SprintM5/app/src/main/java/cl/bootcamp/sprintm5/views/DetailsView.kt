package cl.bootcamp.sprintm5.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import cl.bootcamp.sprintm5.components.ButtonText
import cl.bootcamp.sprintm5.components.DetailCard
import cl.bootcamp.sprintm5.components.ProductCard
import cl.bootcamp.sprintm5.components.TitleText
import cl.bootcamp.sprintm5.model.ProductData
import cl.bootcamp.sprintm5.viewmodel.ProductViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsView(navController: NavController,viewModel: ProductViewModel,id:Int) {

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("Detalle del Producto")
                }
            )
        },

        ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(5.dp),
        ) {
            val selectedProduct = viewModel.getProductById(id)
            println(selectedProduct)

            if (selectedProduct != null) {
                DetailCard(
                    selectedProduct.name,
                    selectedProduct.image,
                    selectedProduct.desc,
                    selectedProduct.price
                )
            }

            Row {
                Column(modifier=Modifier.weight(0.5f)) {
                    ButtonText(text = "AGREGAR AL CARRITO") {
                        //Agregar producto seleccionado al carrito

                        if (selectedProduct != null) {
                            viewModel.addProduct(selectedProduct)
                        }


                        //Borrar esto, solo de prueba
                        navController.navigate("Cart/{id}")

                    }

                }
                Column(modifier=Modifier.weight(0.5f)){
                    ButtonText(text = "VOLVER") {
                        navController.navigate("Home")

                    }
                }



            }



        }
    }
}
