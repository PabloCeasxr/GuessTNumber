package flores.pablo.guesstnumber

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.math.max
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    var maxValue = 100
    var num: Int = 0
    var minValue = 0
    var won = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val guessing: TextView = findViewById(R.id.guessing)
        val down: Button = findViewById(R.id.down)
        val up: Button = findViewById(R.id.up)
        val generate: Button = findViewById(R.id.generate)
        val guessed: Button = findViewById(R.id.guessed)

        generate.setOnClickListener {
            num = Random.nextInt(minValue, maxValue)
            guessing.setText(num.toString())
            generate.visibility = View.INVISIBLE
            guessed.visibility = View.VISIBLE
        }
        up.setOnClickListener {
            minValue = num
            if (checkingLimits()) {
                num = Random.nextInt(minValue, maxValue)
                guessing.setText(num.toString())
            } else {
                guessing.setText("No puede ser :(me ganaste")
            }
            down.setOnClickListener {
                maxValue = num
                if (checkingLimits()) {
                    num = Random.nextInt(minValue, maxValue)
                    guessing.setText(num.toString())
                } else {
                    guessing.setText("No puede ser :(me ganaste")
                }
            }
            guessed.setOnClickListener {
                guessing.setText("Adiviné, tu numero es el " + num)
                guessed.setText("Volver a jugar")
                won = true
            }else{
            generate.visibility = View.VISIBLE
            guessing.setText("Tap on generate to start")
            guessed.visibility = View.GONE
            resetValue()
        }
            }

        }
    fun resetValue(){
        minValue=0
        maxValue= 100
        num=0
        won=false

        fun checkingLimits(): Boolean {
            return minValue != maxValue
        }

    }
}



