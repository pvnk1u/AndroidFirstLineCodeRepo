package io.github.pvnk1u

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {


    /**
     *  private val data = listOf("Apple","Banana","Orange","Watermelon",
     *         "Pear","Grape","Pineapple","Strawberry","Cherry","Mango",
     *         "Apple","Banana","Orange","Watermelon","Pear","Grape",
     *         "Pineapple","Strawberry","Cherry","Mango"
     *         )
     *     override fun onCreate(savedInstanceState: Bundle?) {
     *         super.onCreate(savedInstanceState)
     *         setContentView(R.layout.activity_main)
     *         // ListView的适配器
     *         /**
     *          * 在ArrayAdapter的构造函数中依次传入Activity的实例、ListView子项布局的id，以及数据源
     *          * 使用了android.R.layout.simple_list_item_1作为ListView子项布局的id，这是一个
     *          * Android内置的布局文件，里面只有一个TextView，可用于简单地显示一段文本。
     *          */
     *         val adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,data)
     *         val listView: ListView = findViewById(R.id.listView)
     *         /**
     *          * 最后，调用ListView的setAdapter()方法，将构建好的适配器对象传递进去，这样ListView和数据之间的关联就建立完成了。
     *          */
     *         listView.adapter = adapter
     *     }
     */

    private val fruitList = ArrayList<Fruit>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // 初始化水果数据
        initFruits()
        val adapter = FruitAdapter(this,android.R.layout.simple_list_item_1,fruitList)
        val listView: ListView = findViewById(R.id.listView)
        /**
         * 最后，调用ListView的setAdapter()方法，将构建好的适配器对象传递进去，这样ListView和数据之间的关联就建立完成了。
         */
        listView.adapter = adapter
    }

    /**
     * 初始化所有的水果数据
     */
    private fun initFruits(){
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
    }
}