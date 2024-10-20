package cl.bootcamp.e1m6

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cl.bootcamp.e1m6.components.ContactCard
import cl.bootcamp.e1m6.model.Contact
import cl.bootcamp.e1m6.navigation.NavManager
import cl.bootcamp.e1m6.ui.theme.E1M6Theme
import cl.bootcamp.e1m6.viewModel.ContactsViewModel
import coil.compose.AsyncImage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel: ContactsViewModel by viewModels()

        setContent {
            E1M6Theme {

                NavManager(viewModel = viewModel)

                }
            }
        }
    }



