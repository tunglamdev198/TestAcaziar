package com.lamnt.testacaziasoft.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.lamnt.testacaziasoft.R
import com.lamnt.testacaziasoft.repository.PrefRepository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    @Inject
    lateinit var sharedPreferences: PrefRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler().postDelayed(Runnable { handleNavigate() }, 3000)
    }

    private fun handleNavigate() {
        val isLogged = sharedPreferences.getBoolean("isLoggedIn")
        if (isLogged) {
            navigateTo(SwipeActivity::class.java, null, true)
        } else {
            navigateTo(LoginActivity::class.java, null, true)
        }
    }

    private fun navigateTo(
        clazz: Class<*>?,
        bundle: Bundle?,
        closeCurrent: Boolean
    ) {
        val intent = Intent(this, clazz)
        if (bundle != null) intent.putExtras(bundle)
        startActivity(intent)
        if (closeCurrent) finish()
    }
}