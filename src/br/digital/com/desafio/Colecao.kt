package br.digital.com.desafio

import java.math.BigDecimal

class Colecao (preco: BigDecimal,
               titulo: String,
               vararg livros: Consultavel): Consultavel(preco, titulo) {
    var listaDeLivros = sortedSetOf<Consultavel>()

    init { codigo = "C${code++}"
    listaDeLivros.addAll(livros)}
    companion object { var code = 1000 }

    override fun toString(): String {
        return super.codigo
    }
}