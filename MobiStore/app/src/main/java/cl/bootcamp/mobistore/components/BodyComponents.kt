package cl.bootcamp.mobistore.components
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cl.bootcamp.mobistore.R
import cl.bootcamp.mobistore.model.PhonesRoom
import cl.bootcamp.mobistore.state.PhoneState
import coil.compose.AsyncImage
import retrofit2.http.Body


@Composable
fun Title(text: String) {
    Text(
        text =text,
        style = MaterialTheme.typography.titleLarge,
        fontFamily = FontFamily.Serif
        )
}

@Composable
fun SubTitle(text: String) {
    Text(
        text =text,
        style = MaterialTheme.typography.titleLarge,
        fontFamily = FontFamily.Serif,
        fontWeight = FontWeight.ExtraBold
    )
}

@Composable
fun Body(text:String){
    Text(
        text = text,
        modifier = Modifier
            .padding(5.dp)

    )
}


@Composable
fun CustomCard(cell: PhonesRoom, onClick: () -> Unit ={}) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .testTag("mainCard"),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(colorResource(R.color.c2)),
        onClick = onClick
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Title(text = cell.name)
            SubTitle(text = "$" + cell.price.toString())
            AsyncImage(
                modifier = Modifier
                    .size(200.dp)
                    .align(Alignment.End)
                    .padding(horizontal = 10.dp),
                model = cell.image,
                contentDescription = null
            )

        }

    }
}

@Composable
fun DetailedCard(cell: PhoneState,paddingValues: PaddingValues ){
    Card(
        modifier= Modifier
            .fillMaxSize()
            .padding(8.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(colorResource(R.color.c1)),
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(10.dp)

        ) {

            AsyncImage(
                modifier = Modifier
                    .fillMaxSize(0.6f)
                    .align(Alignment.CenterHorizontally)
                    .padding(vertical = 10.dp, horizontal = 10.dp),
                model = cell.image,
                contentDescription = null
            )
            Title(text = cell.name)
            SubTitle(text ="$${cell.price}" )
            Text(text = cell.description)
            Text(text = cell.lastPrice.toString())
            if (cell.credit){
                Text(stringResource(R.string.credit))
            }
            else{
                Text(text = stringResource(R.string.no_credit))
            }

        }



    }

}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarComponent(
    titulo: String,
    mostrarBotton: Boolean = false,
    onClick: () -> Unit
) {
    TopAppBar(
        title = { Text(text = titulo, color = Color.White, fontWeight = FontWeight.Bold) },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = colorResource(R.color.c2)
        ),
        navigationIcon = {
            if (mostrarBotton) {
                IconButton(onClick = { onClick() }) {
                    Icon(
                        Icons.AutoMirrored.Default.ArrowBack,
                        "",
                        tint = Color.White
                    )
                }
            }
        }
    )
}









@Composable
fun CardExample(cell: PhonesRoom) {
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column() {
            Text(
                text = cell.name,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = "This is a description inside the card.",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}