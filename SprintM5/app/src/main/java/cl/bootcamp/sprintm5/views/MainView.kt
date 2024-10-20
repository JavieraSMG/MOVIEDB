package cl.bootcamp.sprintm5.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
import cl.bootcamp.sprintm5.R
import cl.bootcamp.sprintm5.model.ProductData
import cl.bootcamp.sprintm5.components.Space
import cl.bootcamp.sprintm5.components.TitleText
import cl.bootcamp.sprintm5.components.ProductCard
import cl.bootcamp.sprintm5.viewmodel.ProductViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainView(navController: NavController,viewModel: ProductViewModel) {

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("Shoes Tap")
                }
            )
        },




    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(5.dp),
        ) {
            TitleText(
                text = "Zapatos"
            )

            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {

                items(viewModel.shoesList) { item ->
                    ProductCard(
                        item = item,
                        onClick = {navController.navigate(
                            "Details/${item.id}")
                        }

                    )
                }
            }
           Space()
            TitleText(
                text = "Zapatillas"
            )

            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {

                items(viewModel.sneakersList) { item ->
                    ProductCard(
                        item = item,
                        onClick = {navController.navigate(
                            "Details/${item.id}")
                        }

                        )
                }
            }


        }
    }
}
























