package com.flaviaprouvot.livrosfirebase


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.flaviaprouvot.livrosfirebase.ui.theme.LivrosFirebaseTheme
import com.flaviaprouvot.livrosfirebase.view.CadastroLivros
import com.flaviaprouvot.livrosfirebase.view.ListaLivros
import com.flaviaprouvot.livrosfirebase.view.LoginScreen



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            LivrosFirebaseTheme {
                val navController = rememberNavController()

                Scaffold(modifier = androidx.compose.ui.Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = "login",
                        modifier = androidx.compose.ui.Modifier.padding(innerPadding)
                    ) {
                        composable("login") {
                            LoginScreen(navController = navController)
                        }
                        composable("CadastroLivros") {
                            CadastroLivros(navController = navController)
                        }
                        composable("ListaLivros") {
                            ListaLivros(navController = navController)
                        }
                    }
                }
            }
        }
    }
}
