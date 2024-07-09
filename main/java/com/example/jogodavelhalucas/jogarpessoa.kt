package com.example.jogodavelhalucas


import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.jogodavelhalucas.databinding.ActivityMainBinding

class jogarpessoa : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding

    //representa o tabuleiro
    val tabuleiro = arrayOf(
        arrayOf("A", "B", "C"),
        arrayOf("D", "E", "F"),
        arrayOf("G", "H", "I")
    )

    //representa que o jogador que está jogando vai ser representado pelo "X"
    var jogadorAtual = "X"

//configura o jogo e restaura no final, se for necessário
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
    }

    //é uma função para manipular eventos quando um botão é clicado
    fun buttonClick(view: View) {
        //quando o botão é clicado, ele altera o valor do botão que foi selecionado
        val buttonSelecionado = view as Button
        buttonSelecionado.text = jogadorAtual

        //A matriz vai receber o valor do botão que for clicado
        when(buttonSelecionado.id){
            binding.buttonUm.id -> tabuleiro[0][0] = jogadorAtual
            binding.buttonDois.id -> tabuleiro[0][1] = jogadorAtual
            binding.buttonTres.id -> tabuleiro[0][2] = jogadorAtual
            binding.buttonQuatro.id -> tabuleiro[1][0] = jogadorAtual
            binding.buttonCinco.id -> tabuleiro[1][1] = jogadorAtual
            binding.buttonSeis.id -> tabuleiro[1][2] = jogadorAtual
            binding.buttonSete.id -> tabuleiro[2][0] = jogadorAtual
            binding.buttonOito.id -> tabuleiro[2][1] = jogadorAtual
            binding.buttonNove.id -> tabuleiro[2][2] = jogadorAtual
        }
        //varivavel criada para receber o vencedor depois de verificar quem é o vencedor no tabuleiro
        var vencedor = verificaVencedor(tabuleiro)

        //procura se o código está vazio. Caso não esteja, ele exibe a mensagem de vencedor e finaliza a atividade
        if(!vencedor.isNullOrBlank()) {
            Toast.makeText(this, "Vencedor: " + vencedor, Toast.LENGTH_LONG).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        /*verifica se o botão selecionado foi selecionado pelo jogador "X". Caso tenha sido, executa o if e altera o fundo
         do botão pra uma imagem da coca cola. Caso não tenha sido, altera o fundo pra uma imagem da pepsi*/
        if(jogadorAtual.equals("X")) {
            buttonSelecionado.setBackgroundResource(R.drawable.cocacola)
            jogadorAtual = "O"
        }else{
            buttonSelecionado.setBackgroundResource(R.drawable.pepsi)
            jogadorAtual = "X"
        }
        buttonSelecionado.isEnabled=false
    }

    //verifica se há um vencedor no tabuleiro do jogo da velha
    fun verificaVencedor(tabuleiro: Array<Array<String>>): String? {

        //verifica se existem 3 jogadas iguais nas linhas e colunas
        for (i in 0 until 3) {
            //verifica se existem 3 jogadas iguais numa linha
            if (tabuleiro[i][0] == tabuleiro[i][1] && tabuleiro[i][1] == tabuleiro[i][2]) {
                return tabuleiro[i][0]
            }
            //verifica se existem 3 jogadas iguais numa coluna
            if (tabuleiro[0][i] == tabuleiro[1][i] && tabuleiro[1][i] == tabuleiro[2][i]) {
                return tabuleiro[0][i]
            }
        }
        //verifica se existem 3 jogadas iguais nas diagonais
        if (tabuleiro[0][0] == tabuleiro[1][1] && tabuleiro[1][1] == tabuleiro[2][2]) {
            return tabuleiro[0][0]
        }
        if (tabuleiro[0][2] == tabuleiro[1][1] && tabuleiro[1][1] == tabuleiro[2][0]) {
            return tabuleiro[0][2]
        }
        //verifica quantos jogadores estão jogando
        var empate = 0
        for (linha in tabuleiro) {
            for (valor in linha) {
                if(valor.equals("X")||valor.equals("O")){
                    empate++
                }
            }
        }
        //verifica se existem nove jogadas iguais nas linhas, colunas e diagonais. Caso não exista, retorna a mensagem "Empate"
        if(empate == 9){
            return "Empate"
        }
        //não há jogada
        return null
    }
}