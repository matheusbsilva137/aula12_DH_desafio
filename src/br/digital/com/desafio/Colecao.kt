package br.digital.com.desafio

import java.math.BigDecimal

class Colecao (preco: BigDecimal,
               titulo: String,
               vararg livros: Livro): Consultavel(preco, titulo) {

    var listaDeLivros = sortedSetOf<Livro>()

    init { codigo = "C${code++}"
    listaDeLivros.addAll(livros)}
    companion object { var code = 1000 }

}