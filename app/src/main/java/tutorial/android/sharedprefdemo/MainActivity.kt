package tutorial.android.sharedprefdemo

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var nameText : EditText
    private lateinit var ageText : EditText
    private lateinit var sf : SharedPreferences
    private lateinit var editor : SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        nameText = findViewById(R.id.editTextIdName)
        ageText = findViewById(R.id.editTextIdAge)
        sf = getSharedPreferences("MyPrefs", MODE_PRIVATE)
        editor = sf.edit()
    }

    override fun onPause() {
        super.onPause()
        val name = nameText.text.toString()
        val age = ageText.text.toString().toInt()
        editor.apply(){
            putString("name", name)
            putInt("age", age)
            commit()
        }
    }

    override fun onResume() {
        super.onResume()
        val name = sf.getString("name", null)
        val age = sf.getInt("age", 0)
        nameText.setText(name)
        if(age!=0){
            ageText.setText(age.toString())
        }
    }
}