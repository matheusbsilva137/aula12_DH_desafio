package br.digital.com.desafio

fun main(){
    var op: Int = 1
    var cont: Int = 1
    var ent: String? = null

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
                var titulo: String? = null
                cont = 1
                while (cont == 1){
                    print(" - Título do livro: ")
                    while (titulo == null) titulo = readLine()
                    titulo = readLine()

                    print(" - Preço do livro: ")

                    println("Deseja cadastrar mais um livro?")
                    println(" [0] - Não; [1] - Sim")
                    print(" >>> ")
                    while (ent == null) ent = readLine()
                    cont = ent.toInt()
                }
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