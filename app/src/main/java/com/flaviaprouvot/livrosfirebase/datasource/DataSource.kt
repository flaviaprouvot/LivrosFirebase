package com.flaviaprouvot.livrosfirebase.datasource


import com.google.firebase.firestore.FirebaseFirestore

class DataSource {
    private val db = FirebaseFirestore.getInstance()

    fun salvarLivro(
        titulo: String,
        genero: String,
        autor: String,
        onSucess: () -> Unit,
        onFailure: (Any) -> Unit
    ) {
        val livroMap = hashMapOf(
            "titulo" to titulo,
            "genero" to genero,
            "autor" to autor
        )
        db.collection("Livros")
            .document(titulo)
            .set(livroMap)
            .addOnSuccessListener { onSucess() }
            .addOnFailureListener { erro -> onFailure(erro) }
    }

    fun listarLivros(
        onResult: (List<Map<String, Any>>) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        db.collection("Livros")
            .get()
            .addOnSuccessListener { result ->
                val lista = result.mapNotNull { it.data }
                onResult(lista)
            }
            .addOnFailureListener { e -> onFailure(e) }
    }

    fun deletarLivro(titulo: String) {
        db.collection("Livros")
            .document(titulo)
            .delete()
    }
}


