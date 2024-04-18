package io.github.pvnk1u

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(),View.OnClickListener {

    private val msgList = ArrayList<Msg>()


    private var adapter:MsgAdapter ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // 初始化聊天记录
        initMsg()
        val layoutManager = LinearLayoutManager(this)
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        // 为RecyclerView指定LayoutManager
        recyclerView.layoutManager = layoutManager
        // 为RecyclerView指定MsgAdapter
        adapter = MsgAdapter(msgList)
        recyclerView.adapter = adapter
        // 为发送消息按钮指定点击事件
        val send : Button = findViewById(R.id.send)
        send.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val send : Button = findViewById(R.id.send)
        when (v) {
            send -> {
                // 获取EditText的内容
                val inputText: EditText = findViewById(R.id.inputText)
                val content = inputText.text.toString()
                /**
                 * 如果内容不为空字符串，则创建一个新的Msg对象并添加到msgList列表中去
                 */
                if (content.isNotEmpty()) {
                    val msg = Msg(content, Msg.TYPE_SENT)
                    msgList.add(msg)
                    /**
                     * 调用适配器的notifyItemInserted()方法，用于通知列表有新的数据插入，
                     * 这样新增的一条消息才能够在RecyclerView中显示出来。
                     *
                     * 或者你也可以调用适配器的notifyDataSetChanged()方法，它会将RecyclerView中所有可
                     * 见的元素全部刷新，这样不管是新增、删除、还是修改元素，界面上都会显示最新的数据，但
                     * 缺点是效率会相对差一些。
                     */
                    adapter?.notifyItemInserted(msgList.size - 1)
                    // 当有新消息时， 刷新RecyclerView中的显示
                    /**
                     * 接着调用RecyclerView的scrollToPosition()方法将显示的数
                     * 据定位到最后一行，以保证一定可以看得到最后发出的一条消息。
                     */
                    val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
                    recyclerView.scrollToPosition(msgList.size - 1)
                    // 将RecyclerView定位到最后一行
                    inputText.setText("") // 清空输入框中的内容
                }
            }
        }
    }

    /**
     * 初始化聊天记录
     */
    private fun initMsg() {
        val msg1 = Msg("Hello guy.", Msg.TYPE_RECEIVED)
        msgList.add(msg1)
        val msg2 = Msg("Hello. Who is that?", Msg.TYPE_SENT)
        msgList.add(msg2)
        val msg3 = Msg("This is Tom. Nice talking to you. ", Msg.TYPE_RECEIVED)
        msgList.add(msg3)
    }
}