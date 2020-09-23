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
        adicionarEstoque(item.codigo, quantidade)
    }

    fun adicionarEstoque(codigo: String, quantidade: Int) {
        estoque[codigo] = estoque.getOrDefault(codigo, 0) + quantidade
    }

    fun consultarCodigo(codigo: String): Consultavel =
            if (consultaveis.containsKey(codigo)) consultaveis[codigo]
            else throw IllegalArgumentException("Código não cadastrado")


    fun efetuarVenda(codigo: String) {
        if (!consultaveis.containsKey(codigo)) throw IllegalArgumentException("Código não cadastrado")
        else if (consultaveis[codigo] <= 0) throw IllegalStateException("Item fora de estoque")
        else consultaveis[codigo] = consultaveis[codigo] - 1
    }


}