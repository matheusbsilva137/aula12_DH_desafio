package br.digital.com.desafio

import java.math.BigDecimal

fun main(){
    var op: Int = 1
    var cont: Int = 1
    var ent: String? = null
    val livraria: Livraria = Livraria()

    while(op != 0){
        println()
        println(" --- SISTEMA DE LIVRARIA")
        println(" * Opções:")
        println(" [0] - Sair do sistema;")
        println(" [1] - Cadastrar Livro(s);")
        println(" [2] - Cadastrar Coleção;")
        println(" [3] - Consulta por Código;")
        println(" [4] - Efetuar venda.")
        print(" >>> ")
        do ent = readLine() while (ent == null)
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

                    print(" - Preço do livro: R$")
                    do ent = readLine() while (ent == null)
                    preco = ent.toBigDecimal()

                    var livro = Livro(titulo, preco, autor, anoLanc, volume)

                    println(" - Deseja cadastrar a quantidade de livros? (quantidades não informadas serão usadas como ZERO)")
                    println(" [0] - Não; [1] - Sim")
                    print(" >>> ")
                    do ent = readLine() while (ent == null || (ent != "0" && ent != "1"))
                    informQuant = ent.toInt()

                    if (informQuant == 0) livraria.cadastrar(livro)
                    else{
                        print(" - Quantidade de livros: ")
                        do ent = readLine() while (ent == null)
                        quant = ent.toInt()

                        livraria.cadastrar(livro, quant)
                    }

                    println(" **** ${livro.toString()} - cadastrado com sucesso.")
                    println("Deseja cadastrar mais um livro?")
                    println(" [0] - Não; [1] - Sim")
                    print(" >>> ")
                    do ent = readLine() while (ent == null)
                    cont = ent.toInt()
                }
                println(" -> $quantCadastros cadastros de livros realizados!")
            }
            2 -> {
                var quantCadastros : Int = 0
                var titulo: String? = null
                var cod: String? = null
                var preco: BigDecimal? = null
                var quantLivros : Int = 0
                var codigos = mutableSetOf<String>()
                var informQuant: Int? = null
                var quant: Int? = null

                cont = 1
                while (cont == 1){
                    println() // Flush
                    println(" * CADASTRO DE COLEÇÃO")

                    quantCadastros++
                    print(" - Título da coleção: ")
                    do titulo = readLine() while (titulo == null)

                    print(" - Preço da coleção: R$")
                    do ent = readLine() while (ent == null)
                    preco = ent.toBigDecimal()

                    print(" - Quantos livros fazem parte da coleção? ")
                    do ent = readLine() while (ent == null || ent == "0")
                    quantLivros = ent.toInt()

                    for (i in 1..quantLivros){
                        print(" - Código do ${i}º livro: ")
                        do cod = readLine() while (cod == null)

                        codigos.add(cod)
                    }
                    var colecao = Colecao(preco, titulo, *livraria.pesquisarLivros(codigos).toTypedArray())

                    println(" - Deseja cadastrar a quantidade de coleções? (quantidades não informadas serão usadas como ZERO)")
                    println(" [0] - Não; [1] - Sim")
                    print(" >>> ")
                    do ent = readLine() while (ent == null || (ent != "0" && ent != "1"))
                    informQuant = ent.toInt()

                    if (informQuant == 0) livraria.cadastrar(colecao)
                    else {
                        print(" - Quantidade de coleções: ")
                        do ent = readLine() while (ent == null)
                        quant = ent.toInt()

                        livraria.cadastrar(colecao, quant)
                    }

                    println(" **** Coleção cadastrada com o código ${colecao.toString()}.")
                    println("Deseja cadastrar mais uma coleção?")
                    println(" [0] - Não; [1] - Sim")
                    print(" >>> ")
                    do ent = readLine() while (ent == null)
                    cont = ent.toInt()
                }
                println(" -> $quantCadastros cadastros de coleções realizados!")
            }
            3 -> {
                println() // Flush
                println(" * CONSULTA DE CÓDIGO")
                val cod = readLine()
                try {
                    val item = livraria.consultarCodigo(cod ?: "")
                    if (item is Livro) {
                        println(" - Livro '$cod' encontrado!")
                        println(" ----> Titulo: ${item.titulo}")
                        println(" ----> Autor: ${item.autor}")
                        println(" ----> Edição: ${item.edicao}")
                        println(" ----> Ano de Lançamento: ${item.anoDeLancamento}")
                        println(" ----> Preço: R$${item.preco}")
                    } else if (item is Colecao) {
                        println(" - Coleção '$cod' encontrada!")
                        println(" ----> Titulo da Coleção: ${item.titulo}")
                        println(" ----> Preço: R$${item.preco}")
                        println("       Itens nesta coleção:")
                        item.listaDeLivros.forEach {
                            if (it is Livro) {
                                println("     LIVRO:")
                                println("     --> Titulo: ${it.titulo}")
                                println("     --> Autor: ${it.autor}")
                                println("     --> Edição: ${it.edicao}")
                                println("     --> Ano de Lançamento: ${it.anoDeLancamento}")
                                println("     --> Preço: R$${it.preco}")
                            } else if (it is Colecao) {
                                println("     COLEÇÃO:")
                                println("     --> Titulo da Coleção: ${item.titulo}")
                                println("     --> Preço: R$${item.preco}")
                            }
                            println("----------------------------------------------")
                        }
                    }
                } catch (ex: Exception) {
                    println(" -> Código não cadastrado!")
                }
            }
            4 -> {
                var i: Int = 1
                var tot: BigDecimal = BigDecimal.ZERO
                var valItem: BigDecimal = BigDecimal.ZERO
                var cod: String? = null
                do{
                    print(" - Código do ${i}º item da compra: ")
                    do cod = readLine() while (cod == null)

                    valItem = livraria.efetuarVenda(cod)
                    tot.plus(valItem)

                    println(" **** Item de preço R$${valItem} adicionado à compra.")
                    println("Deseja adicionar mais um item à compra?")
                    println(" [0] - Não; [1] - Sim")
                    print(" >>> ")
                    do ent = readLine() while (ent == null)
                    cont = ent.toInt()
                    i++
                }while (cont == 1)
                println()
                println(" **** VALOR TOTAL DA COMPRA: R$$tot")
            }
            else -> {
                println(" - Opção inválida!")
            }
        }
    }
}