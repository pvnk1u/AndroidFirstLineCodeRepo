package io.github.pvnk1u

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/**
 * RecyclerView适配器
 */
/**
 * 由于FruitAdapter是继承自RecyclerView.Adapter的，那么就必须重写
 * onCreateViewHolder()、onBindViewHolder()和getItemCount()这3个方法。
 *
 * 1、onCreateViewHolder()方法是用于创建ViewHolder实例的，在这个方法中将
 * fruit_item布局加载进来，然后创建一个ViewHolder实例，并把加载出来的布局传入构造
 * 函数当中，最后将ViewHolder的实例返回。
 * 2、onBindViewHolder()方法用于对RecyclerView子项的数据进行赋值，会在每个子项被滚动到屏幕内的时候执行，
 * 这里通过position参数得到当前项的Fruit实例，然后再将数据设置到ViewHolder的ImageView和
 * TextView当中即可。
 * 3、getItemCount()方法就非常简单了，它用于告诉RecyclerView一共有
 * 多少子项，直接返回数据源的长度就可以了。
 */
class FruitAdapter(val fruitList : List<Fruit>) :
                RecyclerView.Adapter<FruitAdapter.ViewHolder>(){

    /**
     * 定义一个内部类ViewHolder,继承自RecyclerView.ViewHolder。
     * 然后ViewHolder的主构造函数中要传入一个View参数，
     * 这个参数通常就是RecyclerView子项的最外层布局，那么就可以通过findViewById()方
     * 法来获取布局中ImageView和TextView的实例了。
     */
    inner class ViewHolder(view:View) : RecyclerView.ViewHolder(view){
        val fruitImage: ImageView = view.findViewById(R.id.fruitImage)
        val fruitName: TextView = view.findViewById(R.id.fruitName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fruit_item,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount() = fruitList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val fruit = fruitList[position]
        holder.fruitImage.setImageResource(fruit.imageId)
        holder.fruitName.text = fruit.name
    }
}