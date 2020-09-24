package br.digital.com.desafio

import java.math.BigDecimal

abstract class Consultavel(var preco: BigDecimal,
                           var titulo: String) {
    lateinit var codigo: String

    override fun equals(other: Any?): Boolean { return (other is Consultavel && other.codigo == this.codigo) }
}