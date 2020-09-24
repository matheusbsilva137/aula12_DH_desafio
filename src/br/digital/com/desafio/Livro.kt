package br.digital.com.desafio

import java.math.BigDecimal

class Livro(titulo: String,
            preco: BigDecimal,
            val autor: String,
            val anoDeLancamento: Int,
            val edicao: Int) : Consultavel(preco, titulo), Comparable<Livro> {

    companion object { var code = 1000 }

    init { codigo = "L${code++}" }

    override fun toString(): String {
        var sb = StringBuilder()
        sb.append(" ----> Titulo: ${titulo}")
        sb.append("\n ----> Autor: ${autor}")
        sb.append("\n ----> Edição: ${edicao}")
        sb.append("\n ----> Ano de Lançamento: ${anoDeLancamento}")
        sb.append("\n ----> Preço: R$${preco}")
        return sb.toString()
    }

    override fun compareTo(other: Livro): Int {
        return edicao.compareTo(other.edicao)
    }
}