package io.github.pvnk1u

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MsgAdapter(val msgList: List<Msg>):RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class LeftViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val leftMsg: TextView = view.findViewById(R.id.leftMsg)
    }
    inner class RightViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val rightMsg: TextView = view.findViewById(R.id.rightMsg)
    }

    /**
     * onCreateViewHolder()方法是用于创建ViewHolder实例的，在这个方法中将
     * 对应的View布局加载进来，然后创建一个ViewHolder实例，并把加载出来的View布局传入构造
     * 函数当中，最后将ViewHolder的实例返回。
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        /**
         * 根据不同的viewType来加载不同的布局并创建不同的ViewHolder
         */
        if (viewType == Msg.TYPE_RECEIVED){
            val view = LayoutInflater.from(parent.context).inflate(R.layout.msg_left_item,parent,false)
            LeftViewHolder(view)
        }else{
            val view  =LayoutInflater.from(parent.context).inflate(R.layout.msg_right_item,parent,false)
            RightViewHolder(view)
        }

    /**
     * onBindViewHolder()方法用于对RecyclerView子项的数据进行赋值，会在每个子项被滚动到屏幕内的时候执行
     */
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val msg = msgList[position]
        /**
         * 判断ViewHolder的类型：如果是LeftViewHolder，就将内容显示到左边的消息布局；
         * 如果是RightViewHolder，就将内容显示到右边的消息布局
         */
        when (holder) {
            is LeftViewHolder -> holder.leftMsg.text = msg.content
            is RightViewHolder -> holder.rightMsg.text = msg.content
            else -> throw IllegalArgumentException()
        }
    }


    override fun getItemViewType(position: Int): Int {
        val msg = msgList[position]
        return msg.type
    }

    /**
     * getItemCount()用于告诉RecyclerView一共有多少子项，直接返回数据源的长度就可以了。
     */
    override fun getItemCount() = msgList.size
}