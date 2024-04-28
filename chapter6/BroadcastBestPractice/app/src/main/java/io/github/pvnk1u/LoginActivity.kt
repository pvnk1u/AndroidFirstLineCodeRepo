package io.github.pvnk1u

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class LoginActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        // 设置登录按钮点击事件
        val login: Button = findViewById(R.id.login)
        // 账户输入框
        val accountEdit: EditText = findViewById(R.id.accountEdit)
        // 密码输入框
        val passwordEdit: EditText = findViewById(R.id.passwordEdit)
        login.setOnClickListener{
            val account = accountEdit.text.toString()
            val password = passwordEdit.text.toString()
            // 如果账号是admin且密码是123456，就认为登录成功
            if (account == "admin" && password == "123456") {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "account or password is invalid",
                    Toast.LENGTH_SHORT).show()
            }
        }
    }
}