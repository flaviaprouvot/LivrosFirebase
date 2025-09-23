package com.flaviaprouvot.livrosfirebase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.flaviaprouvot.livrosfirebase.view.Cadastro
import com.flaviaprouvot.livrosfirebase.view.CadastroLivros
import com.flaviaprouvot.livrosfirebase.view.ListaLivros
import com.flaviaprouvot.livrosfirebase.view.LoginScreen

import com.flaviaprouvot.livrosfirebase.ui.theme.LivrosFirebaseTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            LivrosFirebaseTheme {
                val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = "login",

                        ) {
                        composable("login") {
                            LoginScreen(navController = navController)
                        }
                        composable("Cadastro") {
                            Cadastro(navController = navController)
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
