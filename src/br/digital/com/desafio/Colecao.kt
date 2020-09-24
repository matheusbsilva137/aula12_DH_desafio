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
        val sb = StringBuilder()
        sb.append(" ----> Titulo da Coleção: ${titulo}")
        sb.append("\n ----> Preço: R$${preco}")
        return sb.toString()
    }
}