package br.com.andreyneto.goomer.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.andreyneto.goomer.R
import br.com.andreyneto.goomer.ui.restaurants.RestaurantListFragment
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().add(R.id.frag_container, RestaurantListFragment()).commit()
    }
}
