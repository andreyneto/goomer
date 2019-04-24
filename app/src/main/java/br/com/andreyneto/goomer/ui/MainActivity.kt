package br.com.andreyneto.goomer.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.andreyneto.goomer.R
import br.com.andreyneto.goomer.ui.login.LoginFragment
import br.com.andreyneto.goomer.ui.restaurants.RestaurantListFragment
import com.google.firebase.FirebaseApp

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        FirebaseApp.initializeApp(applicationContext)
        supportActionBar?.title = "Login"
        supportFragmentManager.beginTransaction().add(R.id.frag_container, LoginFragment(), "LOGIN").commit()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        supportActionBar?.let {
            it.subtitle = ""
            it.title =
                if(supportFragmentManager.findFragmentByTag("LOGIN") != null) "Login"
                else "Goomer"
        }
    }
}
