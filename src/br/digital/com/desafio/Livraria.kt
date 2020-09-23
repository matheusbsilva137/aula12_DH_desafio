package br.digital.com.desafio

class Livraria {
    val consultaveis = mutableMapOf<String, Consultavel>()
    val estoque = mutableMapOf<Consultavel, Int>()


    fun cadastrar(item: Consultavel) {
        consultaveis += item
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