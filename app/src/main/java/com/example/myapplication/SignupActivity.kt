package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SignupActivity : AppCompatActivity() {
    //@SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.signup)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnSignUp=findViewById<Button>(R.id.btnSignUp)
        btnSignUp.setOnClickListener {
            val Intent = Intent(this, MainActivity1::class.java)
            startActivity(Intent)
        }

        val back: ImageView = findViewById(R.id.ivBack)
        back.setOnClickListener {
            @Suppress("DEPRECATION")
            onBackPressed()  // Navigate to the previous screen
        }

        val UserName = findViewById<EditText>(R.id.editTextText44)
        val Email=findViewById<EditText>(R.id.editTextTextEmailAddress6)
        val Password=findViewById<EditText>(R.id.editPassword)
        val Phone=findViewById<EditText>(R.id.editTextPhone7)

        //val sendDataBtn = findViewById<Button>(R.id.btnSignUp)
        //sendDataBtn.setOnClickListener {

            //val sendDataIntent = Intent(this@SignupActivity, ProfileActivity::class.java).apply {
                //putExtra("Name", UserName.text.toString())
                //putExtra("Email", Email.text.toString())
                //putExtra("Password", Password.text.toString())
                //putExtra("Phone", Email.text.toString())

            //}
            //startActivity(sendDataIntent)

        //}


    }
}