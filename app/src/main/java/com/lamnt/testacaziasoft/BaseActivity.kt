package com.lamnt.testacaziasoft

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.lamnt.testacaziasoft.viewmodel.BaseViewModel

abstract class BaseActivity<VM : BaseViewModel> : AppCompatActivity() {

    protected lateinit var viewModel : VM

    abstract fun setLayoutId(): Int

    abstract fun initViewModel()

    abstract fun initView()

    abstract fun initData()

    abstract fun initObserver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(setLayoutId())
        initViewModel()
        initView()
        initData()
        initObserver()
    }

    fun navigateTo(clazz: Class<*>?, bundle: Bundle?) {
        navigateTo(clazz, bundle, true)
    }

    open fun navigateTo(
        clazz: Class<*>?,
        bundle: Bundle?,
        closeCurrent: Boolean) {
        val intent = Intent(this, clazz)
        if (bundle != null) intent.putExtras(bundle)
        startActivity(intent)
        if (closeCurrent) finish()
    }

    @SuppressLint("ShowToast")
    protected fun showToast(content : String) {
        Toast.makeText(this,content,Toast.LENGTH_SHORT).show()
    }

    protected fun showPopup(title : String,
                            message : String,
                            onClickListener: DialogInterface.OnClickListener){
        val builder =  AlertDialog.Builder(this)
        builder.setTitle(title)
            .setMessage(message)
            .setCancelable(false)
            .setNegativeButton("No"
            ) { dialog, _ -> dialog.dismiss()}
            .setPositiveButton("Yes",onClickListener)
        builder.create().show()
    }

}