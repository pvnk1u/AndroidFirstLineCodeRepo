package io.github.pvnk1u

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar

class DrawerLayoutMainActivity : AppCompatActivity() {

    /**
     * 定义了一个水果集合，集合里面存放了很多个Fruit的实例，每个实例都代表一种水果
     */
    val fruits = mutableListOf(Fruit("Apple", R.drawable.apple), Fruit("Banana",
        R.drawable.banana), Fruit("Orange", R.drawable.orange), Fruit("Watermelon",
        R.drawable.watermelon), Fruit("Pear", R.drawable.pear), Fruit("Grape",
        R.drawable.grape), Fruit("Pineapple", R.drawable.pineapple), Fruit("Strawberry",
        R.drawable.strawberry), Fruit("Cherry", R.drawable.cherry), Fruit("Mango",
        R.drawable.mango))

    val fruitList = ArrayList<Fruit>()

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
        val navView: NavigationView = findViewById(R.id.navView)
        val drawerLayout :DrawerLayout = findViewById(R.id.drawerLayout)
        /**
         * 首先调用了NavigationView的setCheckedItem()方法将Call菜单项设置为默认选中
         */
        navView.setCheckedItem(R.id.navCall)
        /**
         * 调用了setNavigationItemSelectedListener()方法来设置一个菜单项选中事件的监听器，
         * 当用户点击了任意菜单项时，就会回调到传入的Lambda表达式当中
         */
        navView.setNavigationItemSelectedListener {
            /**
             * 这里调用了DrawerLayout的closeDrawers()方法将滑动菜单关闭，并返回true表示此事件已被处理。
             */
            drawerLayout.closeDrawers()
            true
        }
        /**
         * 设置FloatingActionButton的点击事件，和普通按钮的用法没有太大区别
         */
        val fab : FloatingActionButton = findViewById(R.id.fab)
        /*fab.setOnClickListener{
            Toast.makeText(this,"FAB  clicked",Toast.LENGTH_SHORT).show()
        }*/
        /**
         * 调用了Snackbar的make()方法来创建一个Snackbar对象。make()方法的第一个参数需要传入一个View，
         * 只要是当前界面布局的任意一个View都可以，Snackbar会使用这个View自动查找最外层的布局，用于展示提示信息；
         * 第二个参数就是Snackbar中显示的内容；第三个参数是Snackbar显示的时长，这些和Toast都是类似的。
         *
         * 接着这里又调用了一个setAction()方法来设置一个动作，从而让Snackbar不仅仅是一个提示，
         * 而是可以和用户进行交互的。简单起见，我们在动作按钮的点击事件里面弹出一个Toast提示。最后调用show()方法让Snackbar显示出来。
         */
        fab.setOnClickListener{view->
            Snackbar.make(view, "Data deleted", Snackbar.LENGTH_SHORT)
                .setAction("Undo") {
                    Toast.makeText(this, "Data restored", Toast.LENGTH_SHORT).show()
                }
                .show()
        }


        /**
         * 加载水果数据到列表中
         */
        initFruits()
        val layoutManager = GridLayoutManager(this, 2)

        /**
         * 这里使用了GridLayoutManager这种布局方式。
         * GridLayoutManager的用法也没有什么特别之处，它的构造函数接收两个参数：
         * 第一个是Context，第二个是列数。这里我们希望每一行中会有两列数据。
         */
        val recyclerView : RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = layoutManager
        val adapter = FruitAdapter(this, fruitList)
        recyclerView.adapter = adapter
    }

    /**
     * 初始化水果数据
     */
    private fun initFruits() {
        /**
         * 先是清空了一下fruitList中的数据，接着使用一个随机函数，
         * 从刚才定义的Fruit数组中随机挑选一个水果放入fruitList当中，这样每次打开程序看到的水果数据都会是不同的。
         * 另外，为了让界面上的数据多一些，这里使用了repeat()函数，随机挑选50个水果。
         */
        fruitList.clear()
        repeat(50) {
            val index = (0 until fruits.size).random()
            fruitList.add(fruits[index])
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