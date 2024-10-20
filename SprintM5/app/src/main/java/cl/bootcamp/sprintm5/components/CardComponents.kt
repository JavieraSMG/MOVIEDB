package cl.bootcamp.sprintm5.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cl.bootcamp.sprintm5.model.ProductData


//Card Vista Principal

@Composable
fun ProductCard(
    item: ProductData,
    onClick: () -> Unit,
) {
    Card(
        onClick = onClick,
        modifier = Modifier.size(width = 190.dp, height = 270.dp)
    ) {
        Column(Modifier.padding(16.dp)) {
            Image(
                painter = painterResource(id = item.image),
                contentDescription = item.name,
                modifier = Modifier.size(180.dp)
            )
            TextCard(item.name, 16)
            TextCard("$${item.price}", 20)
            IconButton(onClick = {}) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "Eliminar")

            }
        }
    }
}
//Card Vista Detalle

@Composable
fun DetailCard(
    name: String,
    image: Int,
    desc: String,
    price: Int,

    ) {
    Card(
        modifier = Modifier
            .size(width = 420.dp, height = 650.dp)
            .padding(16.dp)
    ) {
        Column(Modifier.padding(16.dp)) {
            Image(
                painter = painterResource(id = image),
                contentDescription = name,
                modifier = Modifier
                    .size(350.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Space()
            TextCard(name, 20)
            DescriptionText(desc, 16)
            Space()
            TextCard("$${price}", 25)
        }
    }
}

//Card Vista Carrito

@Composable
fun CartCard(
    product: ProductData,
    onDelete: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(5.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = product.image),
                    contentDescription = "",
                    modifier = Modifier
                        .size(80.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column{
                    Text(text = product.name, fontSize = 15.sp, fontWeight = FontWeight.Bold)
                    Text(text = "$${product.price}", fontSize = 15.sp, fontWeight = FontWeight.Bold)
                    Text(text = "${product.quantity}", fontSize = 15.sp, fontWeight = FontWeight.Bold)
                    Text(text = "${product.quantity * product.price}", fontSize = 15.sp, fontWeight = FontWeight.Bold)
                }
            }
            IconButton(onClick = onDelete) {
                Icon(Icons.Default.Delete, contentDescription = "Delete")
            }
        }
    }
}





@Composable
fun DescriptionText(name: String, size: Int) {
    Text(
        text = name,
        fontSize = size.sp,
        fontWeight = FontWeight.Light,
        modifier = Modifier.padding(bottom = 8.dp)


    )
}


@Composable
fun TitleText(text: String) {
    Text(
        text = text,
        fontSize = 25.sp,
        fontWeight = FontWeight.SemiBold,
        modifier = Modifier.padding(start = 16.dp, top = 10.dp)


    )
}

@Composable
fun Space() {
    Spacer(modifier = Modifier.height(16.dp))
}

@Composable
fun ButtonText(
    text: String,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick, modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = text,

            )

    }
}



@Composable
fun QuantitySelector(initialQuantity:Int) {
    var quantity by remember { mutableStateOf(initialQuantity) }
    var text by remember { mutableStateOf(quantity.toString()) }

    Row(verticalAlignment = Alignment.CenterVertically) {
        Column {

            IconButton(onClick = {
                quantity++
                text = quantity.toString()
            }) {
                Icon(Icons.Filled.KeyboardArrowUp, contentDescription = "Increase")
            }

            TextField(
                value = text,
                onValueChange = {
                    text = it
                    if (it.isNotEmpty()) {
                        quantity = it.toIntOrNull() ?: quantity
                    }
                },
                modifier = Modifier.width(30.dp)
            )

            IconButton(onClick = {
                if (quantity >= 1) {
                    quantity--
                    text = quantity.toString()
                }
            }) {
                Icon(Icons.Default.ArrowDropDown, contentDescription = "Decrease")
            }

        }




    }
}

@Composable
fun CartController(onClick: () -> Unit) {
    Column(Modifier.padding(5.dp)) {
        Text("+", modifier = Modifier.clickable { onClick() })
        Text("1")
        Text("-", modifier = Modifier.clickable { onClick() })


    }
}


//Text nombre producto

@Composable
fun TextCard(name: String, size: Int) {
    Text(
        text = name,
        fontSize = size.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        modifier = Modifier.padding(bottom = 8.dp)


    )
}

