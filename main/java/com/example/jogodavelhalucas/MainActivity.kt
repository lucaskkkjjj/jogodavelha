 package com.example.jogodavelhalucas

 import android.content.Intent
 import android.os.Bundle
 import android.view.View
 import androidx.appcompat.app.AppCompatActivity

 class MainActivity : AppCompatActivity(), View.OnClickListener {

     //configura o inicio do aplicativo e restaura depois, se necessario
     override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)
         setContentView(R.layout.telaprincipal)

         //permite a definição de ações específicas para cada botão
         findViewById<View>(R.id.jogarpessoa).setOnClickListener(this)
         findViewById<View>(R.id.jogarmaquina).setOnClickListener(this)
     }

//permite que os botões apertados levem para as atividades na qual foram designados
     override fun onClick(v: View?) {
         when (v?.id) {
             R.id.jogarpessoa -> {
                 //abre o código de jogar com uma pessoa
                 val intent = Intent(this, jogarpessoa::class.java)
                 intent.putExtra("modoJogo", "pessoa")
                 startActivity(intent)
             }
             R.id.jogarmaquina -> {
                 //abre o codigo de jogar com um computador
                 val intent = Intent(this, telaprincipal::class.java)
                 intent.putExtra("modoJogo", "computador")
                 startActivity(intent)
             }
         }
     }
 }