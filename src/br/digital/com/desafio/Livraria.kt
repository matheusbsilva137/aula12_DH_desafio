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
        if (consultarEstoque(codigo) <= 0) throw IllegalStateException("Item fora sem estoque")
        else estoque[consultarCodigo(codigo)] = consultarEstoque(codigo) - 1
    }

    fun consultarCodigo(codigo: String): Consultavel {
        val c = consultaveis[codigo]
        if (c == null) throw throw IllegalArgumentException("Código não cadastrado")
        return c
    }


    fun consultarEstoque(codigo: String): Int {
        return consultarEstoque(consultarCodigo(codigo))
    }

    fun consultarEstoque(item: Consultavel): Int {
        val qt = estoque[item]
        if (qt == null) return 0
        return qt
    }


}