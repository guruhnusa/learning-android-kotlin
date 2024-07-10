package tutorial.android.studykotlin


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.graphics.Color
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val greetingTextView = findViewById<TextView>(R.id.tvHello)
        val inputField = findViewById<EditText>(R.id.tvName)
        val buttonSubmit = findViewById<Button>(R.id.btnSubmit)
        val buttonOffers = findViewById<Button>(R.id.btnOffers)

        var enteredName = ""
        buttonSubmit.setOnClickListener {
             enteredName = inputField.text.toString()
            if(enteredName.isEmpty()){
                buttonOffers.visibility = INVISIBLE
                greetingTextView.text = ""
                Toast.makeText(
                    this@MainActivity,
                    "Please enter your name",
                    Toast.LENGTH_SHORT
                ).show()
            }else{
                val message = "Hello $enteredName!"
                greetingTextView.text = message
                greetingTextView.setTextColor(Color.GRAY)
                buttonOffers.visibility = VISIBLE
            }
            inputField.text.clear()
        }

        buttonOffers.setOnClickListener {
            val intent = Intent(this,SecondActivity::class.java)
            intent.putExtra("USER", enteredName)
            startActivity(intent)
        }
    }
}