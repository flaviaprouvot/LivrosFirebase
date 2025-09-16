package com.flaviaprouvot.livrosfirebase.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.flaviaprouvot.livrosfirebase.datasource.DataSource
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListaLivros(navController: NavController) {

    val dataSource = DataSource()
    var listaLivros by remember { mutableStateOf(listOf<Map<String, Any>>()) }
    var mensagem by remember { mutableStateOf("") }

    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    // Carregar lista ao abrir a tela
    LaunchedEffect(Unit) {
        dataSource.listarLivros(
            onResult = { livros -> listaLivros = livros },
            onFailure = { e -> mensagem = "Erro: ${e.message}" }
        )
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Text(
                    "📚 Menu dos livros",
                    modifier = Modifier.padding(16.dp),
                    fontWeight = FontWeight.Bold
                )
                HorizontalDivider()
                NavigationDrawerItem(
                    label = { Text("Lista de Livros") },
                    selected = false,
                    onClick = { navController.navigate("ListaLivros") }
                )
                NavigationDrawerItem(
                    label = { Text("Cadastro de Livros") },
                    selected = false,
                    onClick = { navController.navigate("CadastroLivros") }
                )
                NavigationDrawerItem(
                    label = { Text("Sair") },
                    selected = false,
                    onClick = { navController.navigate("login") }
                )
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Lista de Livros", fontWeight = FontWeight.Bold) },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color(0xFFFFA726),
                        titleContentColor = Color.White,

                        ),
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                scope.launch {
                                    if (drawerState.isClosed) drawerState.open() else drawerState.close()
                                }
                            }
                        ) {
                            Icon(
                                Icons.Default.Menu,
                                contentDescription = "Menu:",
                                tint = Color.White,
                                modifier = Modifier.size(28.dp)
                            )
                        }
                    }
                )
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = { navController.navigate("CadastroLivros") },
                    containerColor = Color(0xFFFFA726),
                    contentColor = Color.White
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Adicionar livro")
                }
            }
        ) { innerPadding ->

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp)
                    .padding(innerPadding)
            ) {
                if (listaLivros.isEmpty()) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("Nenhum livro cadastrado ainda 📭", color = Color.Gray)
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(12.dp),

                        ) {
                        items(listaLivros) { livro ->
                            val titulo = livro["titulo"] as? String ?: "Sem título"
                            val genero = livro["genero"] as? String ?: "Sem gênero"
                            val autor = livro["autor"] as? String ?: "Sem autor"

                            Card(
                                modifier = Modifier.fillMaxWidth(),
                                colors = CardDefaults.cardColors(
                                    containerColor = Color(0xFFFFE0B2)
                                ),
                                elevation = CardDefaults.cardElevation(6.dp)
                            ) {
                                Column(modifier = Modifier.padding(16.dp)) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        modifier = Modifier.fillMaxWidth()
                                    ) {
                                        Text(
                                            text = titulo,
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 18.sp,
                                            color = Color(0xFFF57C00)
                                        )
                                        Icon(
                                            Icons.Default.Delete,
                                            contentDescription = "Apagar livro",
                                            tint = Color.Red,
                                            modifier = Modifier
                                                .size(24.dp)
                                                .clickable {
                                                    scope.launch {
                                                        dataSource.deletarLivro(titulo)
                                                        dataSource.listarLivros(
                                                            onResult = { livros -> listaLivros = livros },
                                                            onFailure = { e -> mensagem = "Erro: ${e.message}" }
                                                        )
                                                    }
                                                }
                                        )
                                    }
                                    Spacer(modifier = Modifier.height(6.dp))
                                    Text("Gênero: $genero", fontSize = 14.sp)
                                    Text("Autor: $autor", fontSize = 14.sp)
                                }
                            }
                        }
                    }
                }

                if (mensagem.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(mensagem, color = Color.Red)
                }
            }
        }
    }
}


