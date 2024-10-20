package cl.bootcamp.sprintm5

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import cl.bootcamp.sprintm5.navigation.NavManager
import cl.bootcamp.sprintm5.ui.theme.SprintM5Theme
import cl.bootcamp.sprintm5.viewmodel.ProductViewModel
import cl.bootcamp.sprintm5.views.MainView


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SprintM5Theme {
                NavManager(viewModel = ProductViewModel())
            }

        }
    }

}
