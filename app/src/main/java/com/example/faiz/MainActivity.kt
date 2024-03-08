package com.example.faiz

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.RelativeLayout
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    val firebaseAuth = FirebaseAuth.getInstance()
    lateinit var namaku : TextView
    lateinit var logout : ImageButton
    lateinit var peta : ImageButton
    lateinit var rute1 : RelativeLayout
    lateinit var rute2 : RelativeLayout
    lateinit var rute3 : RelativeLayout
    lateinit var rute4 : RelativeLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        namaku = findViewById(R.id.username)
        logout = findViewById(R.id.keluar)
        peta = findViewById(R.id.peta)
        rute1 = findViewById(R.id.route1)
        rute2 = findViewById(R.id.route2)
        rute3 = findViewById(R.id.route3)
        rute4 = findViewById(R.id.route4)

        setarah()
        val firebaseUser = firebaseAuth.currentUser
        if (firebaseUser != null) {
            namaku.text = firebaseUser.displayName
        } else {
            startActivity(Intent(this, Login::class.java))
            finish()
        }
//        tombol.setOnClickListener {
//            startActivity(Intent(this, MapsActivity::class.java))
//        }

        logout.setOnClickListener {
            firebaseAuth.signOut()
            Intent(this, Login::class.java).also {
                it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(it)
                finish()
            }
        }
        peta.setOnClickListener{
            startActivity(Intent(this,MapsActivity::class.java))
        }
    }
    private fun setarah(){
        rute1.setOnClickListener {
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("google.navigation:q=-7.792390377963261, 110.36591036856137&mode=1")
            )
            intent.setPackage("com.google.android.apps.maps")

            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            }
        }

        rute2.setOnClickListener {
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("google.navigation:q=-7.811664184835047, 110.3631882378717&mode=1")
            )
            intent.setPackage("com.google.android.apps.maps")

            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            }
        }

        rute3.setOnClickListener {
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("google.navigation:q=-7.827473564897634, 110.49503174022487&mode=1")
            )
            intent.setPackage("com.google.android.apps.maps")

            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            }
        }

        rute4.setOnClickListener {
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("google.navigation:q=-7.818887165022111, 110.37055916917122&mode=1")
            )
            intent.setPackage("com.google.android.apps.maps")

            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            }
        }
    }
}