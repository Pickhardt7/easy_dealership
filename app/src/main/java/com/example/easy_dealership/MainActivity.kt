package com.example.easy_dealership

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.easy_dealership.navigation.AppNavigation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MeuApp()
        }
    }
}

@Composable
fun MeuApp() {
    MaterialTheme {
        // O padding afasta o conteúdo da barra de status e da barra de navegação do sistema
        Box(modifier = Modifier.fillMaxSize().padding(top = 40.dp, bottom = 24.dp)) {
            AppNavigation()
        }
    }
}
