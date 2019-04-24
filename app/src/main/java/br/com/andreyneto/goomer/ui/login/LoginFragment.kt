package br.com.andreyneto.goomer.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import br.com.andreyneto.goomer.R
import br.com.andreyneto.goomer.base.BaseFragment
import br.com.andreyneto.goomer.ui.restaurants.RestaurantListFragment
import br.com.andreyneto.goomer.utils.hideKeyboard
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.login_fragment.*

class LoginFragment: BaseFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.login_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        edtEmail.requestFocus()
        btnCadastro.setOnClickListener {
            (activity as AppCompatActivity).supportFragmentManager
                .beginTransaction()
                .add(R.id.frag_container, CadastroFragment())
                .addToBackStack(null)
                .commit()
        }
        btnCadastrar.setOnClickListener {
            val email = edtEmail.text.toString()
            val senha = edtPassword.text.toString()
            FirebaseAuth.getInstance()
                .signInWithEmailAndPassword(email, senha)
                .addOnSuccessListener {
                    loginSuccess()
                }
                .addOnFailureListener {
                    container.snackbar(it.message!!)
                }
            activity?.hideKeyboard()
        }
    }

    private fun loginSuccess() {
        (activity as AppCompatActivity).supportFragmentManager
            .beginTransaction()
            .replace(R.id.frag_container, RestaurantListFragment())
            .commit()
    }
}