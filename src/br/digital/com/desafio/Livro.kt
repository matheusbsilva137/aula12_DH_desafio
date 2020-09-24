package br.digital.com.desafio

class Livro (override val nome: String,
             override var preco: Double,
             val autor: String,
             val anoDeLancamento: Int,
             val edicao: Int): Comparable<Livro>{
    override lateinit codigo: String

    companion object { var code = 1000 }
    init { codigo = "L${code++}" }

    override fun compareTo(other: Livro): Int { return edicao.compareTo(other.edicao) }
}