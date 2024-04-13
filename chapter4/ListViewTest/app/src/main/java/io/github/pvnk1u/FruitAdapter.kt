package io.github.pvnk1u

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

/**
 * 创建一个自定义的适配器，这个适配器继承自ArrayAdapter，并将泛型指定为Fruit类
 */
class FruitAdapter(activity:Activity,val resourceId:Int,data:List<Fruit>):
                ArrayAdapter<Fruit>(activity,resourceId,data){

    /**
     * 重写getView()方法，这个方法在每个子项被滚动到屏幕内的时候会被调用。
      */
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        /**
         * 首先使用LayoutInflater来为这个子项加载传入的布局
         * LayoutInflater的inflate()方法接收3个参数，前两个参数已经知道是什么意思了，
         * 第三个参数指定成false，表示只让我们在父布局中声明的layout属性生效，但不会为这个
         * View添加父布局。因为一旦View有了父布局之后，它就不能再添加到ListView中了。
         */

        val view = LayoutInflater.from(context).inflate(resourceId,parent,false)

        /**
         * 接下来调用View的findViewById()方法分别获取到ImageView和TextView的实例
         */
        val fruitImage: ImageView = view.findViewById(R.id.fruitImage)
        val fruitName: TextView = view.findViewById(R.id.fruitName)

        /**
         * 通过getItem()方法得到当前项的Fruit实例，并分别调用它们的
         * setImageResource()和setText()方法设置显示的图片和文字，最后将布局返回，
         * 这样自定义的适配器就完成了。
         */
        val fruit = getItem(position)
        if (fruit != null){
            fruitImage.setImageResource(fruit.imageId)
            fruitName.text = fruit.name
        }
        return view
    }
}