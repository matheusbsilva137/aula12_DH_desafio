package br.digital.com.desafio

import java.math.BigDecimal

abstract class Consultavel {
    var codigo: String? = null
    var preco: BigDecimal? = null
    var titulo: String? = null

    override fun equals(other: Any?): Boolean {
        return (other is Consultavel && other.codigo == this.codigo)
    }
}