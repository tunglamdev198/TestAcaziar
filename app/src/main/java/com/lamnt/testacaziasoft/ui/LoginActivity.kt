package com.lamnt.testacaziasoft.ui

import androidx.lifecycle.ViewModelProvider
import com.lamnt.testacaziasoft.BaseActivity
import com.lamnt.testacaziasoft.R
import com.lamnt.testacaziasoft.repository.PrefRepository
import com.lamnt.testacaziasoft.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : BaseActivity<LoginViewModel>() {

    @Inject
    lateinit var preRepository: PrefRepository

    override fun setLayoutId(): Int = R.layout.activity_login

    override fun initViewModel() {
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
    }

    override fun initView() {
        btnLogin.setOnClickListener {
            if (validateNavigate())
                handleLogin()
        }
    }

    override fun initData() {

    }

    override fun initObserver() {

    }

    private fun validateNavigate(): Boolean {
        if (edtPhone.text.toString().length < 8) {
            showToast("Phone must be 8 characters!")
            return false
        }

        if (edtPassword.text.toString().length < 8) {
            showToast("Password must be 8 characters!")
            return false
        }
        return true
    }

    private fun handleLogin(){
        if (cbRememberMe.isChecked){
            preRepository.putBoolean("isLoggedIn",true)
        }
        navigateTo(SwipeActivity::class.java, null, true)
    }

}