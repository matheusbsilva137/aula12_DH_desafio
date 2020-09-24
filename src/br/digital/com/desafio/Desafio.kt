package br.digital.com.desafio

import java.math.BigDecimal

fun main(){
    var op: Int = 1
    var cont: Int = 1
    var ent: String? = null
    val livraria: Livraria = Livraria()

    while(op != 0){
        println(" --- SISTEMA DE LIVRARIA")
        println(" * Opções:")
        println(" [0] - Sair do sistema;")
        println(" [1] - Cadastrar Livro(s);")
        println(" [2] - Cadastrar Coleção;")
        println(" [3] - Consulta por Código;")
        println(" [4] - Efetuar venda.")
        print(" >>> ")
        while (ent == null) ent = readLine()
        op = ent.toInt()

        when(op){
            1 -> {
                var quantCadastros : Int = 0
                var titulo: String? = null
                var preco: BigDecimal? = null
                var autor: String? = null
                var anoLanc: Int? = null
                var volume: Int? = null
                var informQuant: Int? = null
                var quant: Int? = null

                cont = 1
                while (cont == 1){
                    println() // Flush
                    println(" * CADASTRO DE LIVRO")

                    quantCadastros++
                    print(" - Título do livro: ")
                    do titulo = readLine() while (titulo == null)

                    print(" - Ano de lançamento: ")
                    do ent = readLine() while (ent == null)
                    anoLanc = ent.toInt()

                    print(" - Nome do autor: ")
                    do autor = readLine() while (autor == null)

                    print(" - Edição ou Volume do Livro: ")
                    do ent = readLine() while (ent == null)
                    volume = ent.toInt()

                    print(" - Preço do livro: ")
                    do ent = readLine() while (ent == null)
                    preco = ent.toBigDecimal()

                    println(" - Deseja cadastrar a quantidade de livros? (quantidades não informadas serão usadas como ZERO)")
                    println(" [0] - Não; [1] - Sim")
                    print(" >>> ")
                    do ent = readLine() while (ent == null || (ent != "0" && ent != "1"))
                    informQuant = ent.toInt()

                    if (informQuant == 0){
                        var livro = Livro(titulo, preco, autor, anoLanc, volume)
                        livraria.cadastrar(livro)
                    }else{
                        print(" - Quantidade de livros: ")
                        do ent = readLine() while (ent == null)
                        quant = ent.toInt()

                        var livro = Livro(titulo, preco, autor, anoLanc, volume)
                        livraria.cadastrar(livro, quant)
                    }

                    println("Deseja cadastrar mais um livro?")
                    println(" [0] - Não; [1] - Sim")
                    print(" >>> ")
                    do ent = readLine() while (ent == null)
                    cont = ent.toInt()
                }
                println(" -> $quantCadastros cadastros de livros realizados!")
            }
            2 -> {
                println("Escolheu a opção 2")
            }
            3 -> {
                println("Escolheu a opção 3")
            }
            4 -> {
                println("Escolheu a opção 4")
            }
            else -> {
                println("Opção incorreta")
            }
        }
        ent = null
    }
}