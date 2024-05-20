package io.github.pvnk1u

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout

class DrawerLayoutMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.drawer_layout_activity_main)
        val toolbar : Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        /**
         * 调用getSupportActionBar()方法得到了ActionBar的实例，
         * 虽然这个ActionBar的具体实现是由Toolbar来完成的。接着在ActionBar不为空的情况
         * 下调用setDisplayHomeAsUpEnabled()方法让导航按钮显示出来，调用
         * setHomeAsUpIndicator()方法来设置一个导航按钮图标。实际上，Toolbar最左侧的这个按
         * 钮就叫作Home按钮，它默认的图标是一个返回的箭头，含义是返回上一个Activity。很明显，
         * 这里我们将它默认的样式和作用都进行了修改。
         */
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setHomeAsUpIndicator(R.drawable.ic_menu)
        }
    }

    /**
     * 通过重写onCreateOptionsMenu加载菜单文件toolbar.xml到Toolbar上
     */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val drawerLayout : DrawerLayout = findViewById(R.id.drawerLayout)
        when (item.itemId) {
            /**
             * 对Home按钮的点击事件进行处理，Home按钮的id永远都是android.R.id.home。
             * 然后调用DrawerLayout的openDrawer()方法将滑动菜单展示出来，
             * 注意，openDrawer()方法要求传入一个Gravity参数，为了保证这里的行
             * 为和XML中定义的一致，我们传入了GravityCompat.START。
             */
            android.R.id.home -> drawerLayout.openDrawer(GravityCompat.START)
            R.id.backup -> Toast.makeText(this, "You clicked Backup",
                Toast.LENGTH_SHORT).show()
            R.id.delete -> Toast.makeText(this, "You clicked Delete",
                Toast.LENGTH_SHORT).show()
            R.id.settings -> Toast.makeText(this, "You clicked Settings",
                Toast.LENGTH_SHORT).show()
        }
        return true
    }


}