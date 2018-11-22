package com.example.yuelinghui.rxjavasample.ui

import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.example.yuelinghui.rxjavasample.Injection
import com.example.yuelinghui.rxjavasample.R
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    private lateinit var mNameTxt:TextView

    private lateinit var mNameInput:EditText

    private lateinit var mUpdateBtn:Button

    private lateinit var mViewModelFactory: UserModelFactory

    private lateinit var mViewModel:UserViewModel

    private val mDispatch = CompositeDisposable()

    private val ID = "1"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mNameInput = findViewById(R.id.user_name_input)
        mNameTxt = findViewById(R.id.user_name)
        mUpdateBtn = findViewById(R.id.btn_update)

        mViewModelFactory = Injection.provideUserModelFactory(this)
        mViewModel = ViewModelProviders.of(this,mViewModelFactory).get(UserViewModel::class.java)

        mUpdateBtn.setOnClickListener {

            updateUserName()
        }
    }

    override fun onStart() {
        super.onStart()
        mDispatch.add(mViewModel.getUserName(ID).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe({
            mNameTxt.text = it
        },{

        }))
    }

    override fun onStop() {
        super.onStop()

        mDispatch.clear()
    }

    private fun updateUserName() {
        val userName = mNameInput.text.toString()
        if (TextUtils.isEmpty(userName)) {
            Toast.makeText(this,"名字没有输入",Toast.LENGTH_SHORT).show()
            return
        }

        mUpdateBtn.isEnabled = false

        mDispatch.add(mViewModel.updateUserName(ID,userName).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe({
            mUpdateBtn.isEnabled = true
        },{
            mUpdateBtn.isEnabled = true
            Toast.makeText(this,"更新失败",Toast.LENGTH_SHORT).show()

        }))
    }

}
