package br.digital.com.desafio

import java.math.BigDecimal

class Livro (titulo: String,
             preco: BigDecimal,
             val autor: String,
             val anoDeLancamento: Int,
             val edicao: Int): Consultavel(preco, titulo), Comparable<Livro>{

    companion object { var code = 1000 }

    init { codigo = "L${code++}" }

    override fun toString(): String { return "Livro nº $codigo; $titulo; $autor; $anoDeLancamento; $edicao" }

    override fun compareTo(other: Livro): Int { return edicao.compareTo(other.edicao) }
}