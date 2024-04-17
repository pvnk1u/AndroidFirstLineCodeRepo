package io.github.pvnk1u

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

class MainActivity : AppCompatActivity() {

    private val fruitList = ArrayList<Fruit>()

    /**override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // 初始化水果数据
        initFruits()
        /**
         * 创建一个LinearLayoutManager对象，并将它设置到RecyclerView当中。
         *
         * LayoutManager用于指定RecyclerView的布局方式，这里使用的
         * LinearLayoutManager是线性布局的意思，可以实现和ListView类似的效果。
         */
        val layoutManager = LinearLayoutManager(this)
        /**
         * 设置布局方向，默认是纵向排列的，LinearLayoutManager.HORIZONTAL表示让布局横向排列
         */
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = layoutManager
        /**
         * 创建FruitAdapter的实例，并将水果数据传入FruitAdapter的构造函数中，最后调用
         * RecyclerView的setAdapter()方法来完成适配器设置，这样RecyclerView和数据之间的关
         * 联就建立完成了。
         */
        val adapter = FruitAdapter(fruitList)
        recyclerView.adapter = adapter
    }*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // 初始化水果数据
        initFruits()
        /**
         * 创建了一个StaggeredGridLayoutManager的实例。
         * StaggeredGridLayoutManager的构造函数接收两个参数：第一个参数用于指定布局的列
         * 数，传入3表示会把布局分为3列；第二个参数用于指定布局的排列方向，传入
         * StaggeredGridLayoutManager.VERTICAL表示会让布局纵向排列。
         */
        val layoutManager = StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL)
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        /**
         * 把创建好的实例设置到RecyclerView当中
         */
        recyclerView.layoutManager = layoutManager
        /**
         * 创建FruitAdapter的实例，并将水果数据传入FruitAdapter的构造函数中，最后调用
         * RecyclerView的setAdapter()方法来完成适配器设置，这样RecyclerView和数据之间的关
         * 联就建立完成了。
         */
        val adapter = FruitAdapter(fruitList)
        recyclerView.adapter = adapter
    }

   /* private fun initFruits(){
        repeat(2){
            fruitList.add(Fruit("Apple", R.drawable.apple_pic))
            fruitList.add(Fruit("Banana", R.drawable.banana_pic))
            fruitList.add(Fruit("Orange", R.drawable.orange_pic))
            fruitList.add(Fruit("Watermelon", R.drawable.watermelon_pic))
            fruitList.add(Fruit("Pear", R.drawable.pear_pic))
            fruitList.add(Fruit("Grape", R.drawable.grape_pic))
            fruitList.add(Fruit("Pineapple", R.drawable.pineapple_pic))
            fruitList.add(Fruit("Strawberry", R.drawable.strawberry_pic))
            fruitList.add(Fruit("Cherry", R.drawable.cherry_pic))
            fruitList.add(Fruit("Mango", R.drawable.mango_pic))
        }
    }*/

    private fun initFruits(){
        repeat(2){
            fruitList.add(Fruit(getRandomLengthString("Apple"),
                R.drawable.apple_pic))
            fruitList.add(Fruit(getRandomLengthString("Banana"),
                R.drawable.banana_pic))
            fruitList.add(Fruit(getRandomLengthString("Orange"),
                R.drawable.orange_pic))
            fruitList.add(Fruit(getRandomLengthString("Watermelon"),
                R.drawable.watermelon_pic))
            fruitList.add(Fruit(getRandomLengthString("Pear"),
                R.drawable.pear_pic))
            fruitList.add(Fruit(getRandomLengthString("Grape"),
                R.drawable.grape_pic))
            fruitList.add(Fruit(getRandomLengthString("Pineapple"),
                R.drawable.pineapple_pic))
            fruitList.add(Fruit(getRandomLengthString("Strawberry"),
                R.drawable.strawberry_pic))
            fruitList.add(Fruit(getRandomLengthString("Cherry"),
                R.drawable.cherry_pic))
            fruitList.add(Fruit(getRandomLengthString("Mango"),
                R.drawable.mango_pic))
        }
    }

    private fun getRandomLengthString(str: String): String {
        val n = (1..20).random()
        val builder = StringBuilder()
        repeat(n) {
            builder.append(str)
        }
        return builder.toString()
    }
}