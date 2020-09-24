package br.digital.com.desafio

import java.lang.IllegalStateException
import java.math.BigDecimal

class Livraria {
    val consultaveis = mutableMapOf<String, Consultavel>()
    val estoque = mutableMapOf<Consultavel, Int>()


    fun cadastrar(item: Consultavel) { cadastrar(item, 0) }

    fun cadastrar(item: Consultavel, quantidadeEstoque: Int) {
        if (consultaveis.containsKey(item.codigo)) throw IllegalArgumentException("Produto já cadastrado")
        consultaveis.put(item.codigo, item)
        adicionarEstoque(item, quantidadeEstoque)
    }

    private fun adicionarEstoque(item: Consultavel, quantidade: Int) { estoque[item] = (estoque.getOrDefault(item, 0) + quantidade) }

    fun efetuarVenda(codigo: String) : BigDecimal {
        if (consultarEstoque(codigo) <= 0) throw IllegalStateException("Item sem estoque")
        else {
            estoque[consultarCodigo(codigo)] = consultarEstoque(codigo) - 1
            return consultaveis[codigo]!!.preco
        }
    }

    fun consultarCodigo(codigo: String): Consultavel { return consultaveis[codigo] ?: throw IllegalArgumentException("Código não cadastrado") }

    private fun consultarEstoque(codigo: String): Int { return consultarEstoque(consultarCodigo(codigo)) }

    private fun consultarEstoque(item: Consultavel): Int { return estoque[item] ?: 0 }

    fun pesquisarLivros(codigos: Collection<String>): List<Consultavel>{
        val lista = mutableListOf<Consultavel>()
        codigos.forEach{ if(consultaveis.containsKey(it)) lista.add(consultarCodigo(it)) }
        return lista
    }
}