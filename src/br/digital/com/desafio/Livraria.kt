package br.digital.com.desafio

class Livraria {
    val consultaveis = mutableMapOf<String, Consultavel>()
    val estoque = mutableMapOf<Consultavel, Int>()


    fun cadastrar(item: Consultavel) {
        cadastrar(item, 0)
    }

    fun cadastrar(item: Consultavel, quantidadeEstoque: Int) {
        if (consultaveis.containsKey(item.codigo)) throw IllegalArgumentException("Produto já cadastrado")
        consultaveis.put(item.codigo, item)
        adicionarEstoque(item, quantidadeEstoque)
    }

    fun adicionarEstoque(item: Consultavel, quantidade: Int) {
        estoque[item] = (estoque.getOrDefault(item, 0) + quantidade)
    }

    fun adicionarEstoque(codigo: String, quantidade: Int) {
        adicionarEstoque(consultarCodigo(codigo), quantidade)
    }

    fun efetuarVenda(codigo: String) {
        if (consultarEstoque(codigo) <= 0) throw IllegalStateException("Item sem estoque")
        else estoque[consultarCodigo(codigo)] = consultarEstoque(codigo) - 1
    }

    fun consultarCodigo(codigo: String): Consultavel {
        return consultaveis[codigo] ?: throw IllegalArgumentException("Código não cadastrado")
    }


    fun consultarEstoque(codigo: String): Int {
        return consultarEstoque(consultarCodigo(codigo))
    }

    fun consultarEstoque(item: Consultavel): Int {
        return estoque[item] ?: 0
    }


}