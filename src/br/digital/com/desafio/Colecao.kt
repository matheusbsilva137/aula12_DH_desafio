package br.digital.com.desafio

import java.math.BigDecimal

class Colecao (override var titulo: String, override var preco: BigDecimal, vararg livros: Livro) {

    override var codigo: String

    var listaDeLivros = sortedSetOf<Livro>()

    init { codigo = "C${code++}"
    listaDeLivros.addAll(*livros)}
    companion object { var code = 1000 }

}