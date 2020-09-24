package br.digital.com.desafio

import java.math.BigDecimal

fun main() {
    var op: Int = 1
    var cont: Int = 1
    var ent: String? = null
    val livraria: Livraria = Livraria()

    loop@ while (op != 0) {
        try {
            espaco(3)
            println(" --- SISTEMA DE LIVRARIA")
            println(" * Opções:")
            println(" [0] - Sair do sistema;")
            println(" [1] - Cadastrar Livro(s);")
            println(" [2] - Cadastrar Coleção;")
            println(" [3] - Consulta por Código;")
            println(" [4] - Efetuar venda;")
            println(" [5] - Listar Produtos;")
            println(" [6] - Adicionar ao Estoque.")
            print(" >>> ")
            do ent = readLine() while (ent == null)
            op = if (ent.isNotEmpty()) ent.toInt() else -1


            when (op) {
                0 -> break@loop
                1 -> {
                    var quantCadastros: Int = 0
                    var titulo: String? = null
                    var preco: BigDecimal? = null
                    var autor: String? = null
                    var anoLanc: Int? = null
                    var volume: Int? = null
                    var informQuant: Int? = null
                    var quant: Int? = null

                    cont = 1
                    while (cont == 1) {
                        println() // Flush
                        println(" * CADASTRO DE LIVRO")

                        quantCadastros++
                        print(" - Título do livro: ")
                        do titulo = readLine() while (titulo == null)

                        print(" - Ano de lançamento: ")
                        do ent = readLine() while (ent == null)
                        anoLanc = if (ent.isNotEmpty()) ent.toInt() else 0

                        print(" - Nome do autor: ")
                        do autor = readLine() while (autor == null)

                        print(" - Edição ou Volume do Livro: ")
                        do ent = readLine() while (ent == null)
                        volume = if (ent.isNotEmpty()) ent.toInt() else 0

                        print(" - Preço do livro: R$")
                        do ent = readLine() while (ent == null)
                        preco = if (ent.isNotEmpty()) ent.toBigDecimal() else BigDecimal(0)

                        val livro = Livro(titulo, preco, autor, anoLanc, volume)
                        espaco(2)
                        println(" - Existe estoque disponível para esse livro? (se não informar o estoque será ZERO)")
                        println(" [0] - Não; [1] - Sim")
                        print(" >>> ")
                        do ent = readLine() while (ent == null || (ent != "0" && ent != "1"))
                        informQuant = if (ent.isNotEmpty()) ent.toInt() else 0

                        if (informQuant == 0) livraria.cadastrar(livro)
                        else {
                            espaco(2)
                            print(" - Quantidade de livros: ")
                            do ent = readLine() while (ent == null)
                            quant = ent.toInt()

                            livraria.cadastrar(livro, quant)
                        }
                        espaco(2)
                        println(" **** Livro cadastrado com o código ${livro.codigo}.")
                        espaco(1)
                        println("Deseja cadastrar mais um livro?")
                        println(" [0] - Não; [1] - Sim")
                        print(" >>> ")
                        do ent = readLine() while (ent == null)
                        cont = if (ent.isNotEmpty()) ent.toInt() else 0
                    }
                    espaco(1)
                    println(" -> $quantCadastros cadastros de livros realizados!")
                    pressEnter()
                }
                2 -> {
                    var quantCadastros: Int = 0
                    var titulo: String? = null
                    var cod: String? = null
                    var preco: BigDecimal? = null
                    var quantLivros: Int = 0
                    var codigos = mutableSetOf<String>()
                    var informQuant: Int? = null
                    var quant: Int? = null

                    cont = 1
                    while (cont == 1) {
                        println() // Flush
                        println(" * CADASTRO DE COLEÇÃO")

                        quantCadastros++
                        print(" - Título da coleção: ")
                        do titulo = readLine() while (titulo == null)

                        print(" - Preço da coleção: R$")
                        do ent = readLine() while (ent == null)
                        preco = if (ent.isNotEmpty()) ent.toBigDecimal() else BigDecimal(0)

                        print(" - Quantos livros fazem parte da coleção? ")
                        do ent = readLine() while (ent == null || ent == "0")
                        quantLivros = if (ent.isNotEmpty()) ent.toInt() else 0

                        for (i in 1..quantLivros) {
                            print(" - Código do ${i}º livro: ")
                            do cod = readLine() while (cod == null)

                            codigos.add(cod)
                        }
                        var colecao = Colecao(preco, titulo, *livraria.pesquisarLivros(codigos).toTypedArray())

                        espaco(2)
                        println(" - Existe estoque disponível para essa coleção? (se não informar o estoque será ZERO)")
                        println(" [0] - Não; [1] - Sim")
                        print(" >>> ")
                        do ent = readLine() while (ent == null || (ent != "0" && ent != "1"))
                        informQuant = if (ent.isNotEmpty()) ent.toInt() else 0

                        if (informQuant == 0) livraria.cadastrar(colecao)
                        else {
                            espaco(2)
                            print(" - Quantidade de coleções: ")
                            do ent = readLine() while (ent == null)
                            quant = if (ent.isNotEmpty()) ent.toInt() else 0

                            livraria.cadastrar(colecao, quant)
                        }
                        espaco(1)
                        println(" **** Coleção cadastrada com o código ${colecao.codigo}.")
                        println("Deseja cadastrar mais uma coleção?")
                        println(" [0] - Não; [1] - Sim")
                        print(" >>> ")
                        do ent = readLine() while (ent == null)
                        cont = if (ent.isNotEmpty()) ent.toInt() else 0
                    }
                    println(" -> $quantCadastros cadastros de coleções realizados!")
                    pressEnter()
                }
                3 -> {
                    println() // Flush
                    println(" * CONSULTA DE CÓDIGO")
                    print("Código: ")
                    val cod = readLine()
                    try {
                        val item = livraria.consultarCodigo(cod ?: "")
                        espaco(2)
                        if (item is Livro) {
                            println(" - Livro '$cod' encontrado!")
                            println(item.toString())
                        } else if (item is Colecao) {
                            println(" - Coleção '$cod' encontrada!")
                            println(" ----> Titulo da Coleção: ${item.titulo}")
                            println(" ----> Preço: R$${item.preco}")
                            println("       Itens nesta coleção:")
                            item.listaDeLivros.forEach {
                                if (it is Livro) {
                                    println("     LIVRO:")
                                    println(it.toString())
                                } else if (it is Colecao) {
                                    println("     COLEÇÃO:")
                                    println(it.toString())
                                    println("     --> Preço: R$${item.preco}")
                                }
                                println("----------------------------------------------")
                            }
                        }
                    } catch (ex: Exception) {
                        println(" -> Código não cadastrado!")
                        pressEnter()
                    }
                }
                4 -> {
                    var i: Int = 1
                    var tot: BigDecimal = BigDecimal.ZERO
                    var valItem: BigDecimal = BigDecimal.ZERO
                    var cod: String? = null
                    do {
                        espaco(2)
                        print(" - Código do ${i}º item da compra: ")
                        do cod = readLine() while (cod == null)
                        valItem = livraria.efetuarVenda(cod)
                        tot = tot.plus(valItem)
                        espaco(1)
                        println(" **** Comprado item de preço R$${valItem}.")
                        espaco(1)
                        println("Deseja comprar mais um item?")
                        println(" [0] - Não; [1] - Sim")
                        print(" >>> ")
                        do ent = readLine() while (ent == null)
                        cont = ent.toInt()
                        i++
                    } while (cont == 1)
                    println()
                    println(" **** VALOR TOTAL DA COMPRA: R$$tot")
                    pressEnter()
                }
                5 -> {
                    espaco(2)
                    println(" * CONSULTA DE PRODUTOS")
                    livraria.consultaveis.values.forEach {
                        print("    - [${it.codigo}] '${it.titulo}'")
                        if (it is Livro) {
                            print(" ${it.edicao}º edição, lançado em ${it.anoDeLancamento} por ${it.autor}")
                        } else if (it is Colecao) {
                            print(" Coleção [")
                            it.listaDeLivros.forEachIndexed { i, c ->
                                if (i > 0) print(", ")
                                print(c.codigo)
                            }
                            print("]")
                        }
                        println(" - Preço: R$${it.preco} / ${livraria.estoque[it] ?: 0} em estoque.")
                    }
                    if (livraria.consultaveis.size == 0) println("   *** Nenhum produto cadastrado ***")
                    pressEnter()
                }
                6 -> {
                    println(" * ADICIONAR AO ESTOQUE")
                    var ent: String?

                    var cod: String?
                    var qt: Int

                    do {
                        print("Código: ")
                        cod = readLine()
                        println()
                    } while (cod == null)

                    val item = livraria.consultarCodigo(cod)

                    do {
                        print("Quantidade para adicionar: ")
                        ent = readLine()
                        println()
                    } while (ent == null)
                    qt = if (ent.isNotEmpty()) ent.toInt() else 0
                    livraria.estoque[item] = (livraria.estoque[item] ?: 0) + qt

                    println(" **** ESTOQUE ATUALIZADO! NOVO TOTAL: ${livraria.estoque[item] ?: 0}")
                    pressEnter()
                }
                else -> {
                    println(" - Opção inválida!")
                    pressEnter()
                }
            }
        } catch (e: IllegalStateException) {
            println(e.message)
            pressEnter()
        } catch (e: IllegalArgumentException) {
            println(e.message)
            pressEnter()
        } catch (e: Exception) {
            println("ERRO INESPERADO!")
            pressEnter()
        }
    }
    espaco(5)
    println("VOLTE SEMPRE! :)")
}

fun espaco(qt: Int) {
    for (i in 1..qt) println()
}

fun pressEnter() {
    print("                                       >> [Enter] <<")
    readLine()
}