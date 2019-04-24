package br.com.andreyneto.goomer.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import br.com.andreyneto.goomer.R
import br.com.andreyneto.goomer.base.BaseFragment
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.cadastro_fragment.*

class CadastroFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.cadastro_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title = "Cadastro"
        btnCadastrar.setOnClickListener {
            val email = edtEmail.text.toString()
            val senha = edtPassword.text.toString()
            FirebaseAuth.getInstance()
                .createUserWithEmailAndPassword(email, senha)
                .addOnSuccessListener { cadastroSuccess() }
                .addOnFailureListener { container.snackbar(it.message!!) }
        }
    }

    private fun cadastroSuccess() {
        (activity as AppCompatActivity).supportFragmentManager.beginTransaction().remove(this).commit()
        activity?.onBackPressed()
    }
}
