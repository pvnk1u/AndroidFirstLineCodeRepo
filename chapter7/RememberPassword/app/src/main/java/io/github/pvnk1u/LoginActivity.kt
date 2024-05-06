package io.github.pvnk1u

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast

class LoginActivity : BaseActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        // 获取了SharedPreferences对象，然后调用它的getBoolean()方法去获取remember_password这个键对应的值。
        val prefs = getPreferences(Context.MODE_PRIVATE)
        val isRemember = prefs.getBoolean("remember_password", false)
        val accountEdit : EditText = findViewById(R.id.accountEdit)
        val passwordEdit : EditText = findViewById(R.id.passwordEdit)
        val rememberPass : CheckBox = findViewById(R.id.rememberPass)
        val login : Button = findViewById(R.id.login)
        // 如果设置了记住密码，将用户名密码设置到对应的文本框中,第一次登陆时肯定是false
        if (isRemember) {
            // 将账号和密码都设置到文本框中
            val account = prefs.getString("account", "")
            val password = prefs.getString("password", "")
            accountEdit.setText(account)
            passwordEdit.setText(password)
            rememberPass.isChecked = true
        }
        login.setOnClickListener {
            val account = accountEdit.text.toString()
            val password = passwordEdit.text.toString()
            // 如果账号是admin且密码是123456，就认为登录成功
            if (account == "admin" && password == "123456") {
                /**
                 * 在登录成功之后，会调用CheckBox的isChecked()方法来检查复选框是否被选中。如果被选中了，则表示用户想要记
                 * 住密码，这时将remember_password设置为true，然后把account和password对应的值都
                 * 存入SharedPreferences文件中并提交；如果没有被选中，就简单地调用一下clear()方法，
                 * 将SharedPreferences文件中的数据全部清除掉。
                 */
                val editor = prefs.edit()
                if (rememberPass.isChecked) { // 检查复选框是否被选中
                    editor.putBoolean("remember_password", true)
                    editor.putString("account", account)
                    editor.putString("password", password)
                } else {
                    editor.clear()
                }
                editor.apply()
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