package io.github.pvnk1u

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity


class FirstActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 打印onCreate日志
        Log.d("FirstActivity",this.toString())
        /**
         * 调用setContentView方法来给当前的Activity加载一个布局
         * 在setContentView()方法中，一般会传入一个布局文件的id。
         * 这里只需要调用R.layout.first_layout就可以得到first_layout.xml布局的id
         */
        setContentView(R.layout.first_layout)

        /**
         * 在Activity中，可以通过findViewById()方法获取在布局文件中定义的元素，这里传入
         * R.id.button1来得到按钮的实例，这个值是在first_layout.xml中通过android:id属
         * 性指定的。findViewById()方法返回的是一个继承自View的泛型对象，因此Kotlin无法自动
         * 推导出它是一个Button还是其他控件，所以需要将button1变量显式地声明成Button类
         * 型。得到按钮的实例之后，通过调用setOnClickListener()方法为按钮注册一个监听
         * 器，点击按钮时就会执行监听器中的onClick()方法。因此，弹出Toast的功能当然是要在
         * onClick()方法中编写了。
         */
        val button1: Button = findViewById(R.id.button1)

        /**
         * 调用startActivityForResult并通过重写onActivityResult方法来实现获取其他Activity返回结果的方式已经不推荐使用
         *
         * 现在推荐使用的是通过registerForActivityResult方法创建执行器，然后通过执行器执行Intent对象的方式来打开新Activity并获取新Activity关闭时返回的结果
         *
         * 需要特别注意的是，通过registerForActivityResult方法创建执行器必须在生命周期STARTED之前调用，所以下面这段代码不能在button1.setOnClickListener()方法中创建
         *
         * 新打开的Activity中的返回数据的方式与旧的startActivityForResult、onActivityResult方法没有区别
         */
        var launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult(),ActivityResultCallback<ActivityResult>(){
            // 新Activity关闭时返回数据将会触发下面的判断逻辑
            if (it.resultCode == RESULT_OK){
                var returnedData = it.data?.getStringExtra("data_return")
                Log.d("FirstActivity","returned data is $returnedData")
            }
        })
        button1.setOnClickListener{
            /**
             * Toast提醒
             *
             * Toast是Android系统提供的一种非常好的提醒方式，在程序中可以使用它将一些短小的信息通
             * 知给用户，这些信息会在一段时间后自动消失，并且不会占用任何屏幕空间，
             *
             * makeText()方法需要传入3个参数。第一个参数是Context，也就是Toast要求的上下文，
             * 由于Activity本身就是一个Context对象，因此这里直接传入this即可。
             * 第二个参数是Toast显示的文本内容。第三个参数是Toast显示的时
             * 长，有两个内置常量可以选择：Toast.LENGTH_SHORT和Toast.LENGTH_LONG。
             */
            /*Toast.makeText(this,"You clicked Button 1",Toast.LENGTH_SHORT).show()*/

            /**
             * 第一种：显式Intent启动Activity，直接通过指定对应Activity的class启动
             * Intent是Android程序中各组件之间进行交互的一种重要方式，它不仅可以指明当前组件想要执
             * 行的动作，还可以在不同组件之间传递数据。Intent一般可用于启动Activity、启动Service以
             * 及发送广播等场景
             */
            // 创建一个Intent对象，第一个参数传入this也就是FirstActivity作为上下文，第二个参数传入SecondActivity::class.java作为目标Activity,
            // 即在FirstActivity的基础上打开SecondActivity,Kotlin中 SecondActivity::class.java的写法就相当于Java中SecondActivity.class的写法
            /*val intent = Intent(this,SecondActivity::class.java)
            startActivity(intent)*/

            /**
             * 第二种：隐式Intent启动Activity
             * 使用Intent的另一个构造函数，直接将action的字符串传了进去，表明想要启动能够响应io.pvnk1u.activitytest.ACTION_START这个action的Activity。
             *
             * android.intent.category.DEFAULT是一种默认的category，在调用startActivity()方法的时候会自动将这个category添加到Intent中。
             */
            /*val intent = Intent("io.pvnk1u.activitytest.ACTION_START")
            // 默认情况下，intent的category是android.intent.category.DEFAULT，不用特殊指定，但是如果在AndroidManifest.xml中手动修改了category，则这里需要使用对应的category名字
            intent.addCategory("io.pvnk1u.activitytest.MY_CATEGORY")
            startActivity(intent)*/
            /**
             * 第三种：不仅可以启动自己程序内的Activity，还可以启动其他程序的Activity，使多个应用程序之间的功能共享成为可能
             */
            // 首先指定了Intent的action是Intent.ACTION_VIEW，这是一个Android系统内置的动作，其常量值为android.intent.action.VIEW。
            /*val intent = Intent(Intent.ACTION_VIEW)
            // 通过Uri.parse()方法将一个 网址字符串解析成一个Uri对象，再调用Intent的setData()方法将这个Uri对象传递进去。
            intent.data = Uri.parse("https://www.baidu.com")
            startActivity(intent)*/
            /**
             * geo表示显示地理位置、tel表示拨打电话
             * 程序中调用系统拨号界面
             */
            /*val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:10086")
            startActivity(intent)*/
            /**
             * 调用其他Activity的时候向其他Activity传递数据
             */
            /*val data = "Hello SecondActivity"
            val intent = Intent(this,SecondActivity::class.java)
            intent.putExtra("extra_data",data)
            startActivity(intent)*/
            /**
             * 除了在调用其他Activity的时候传递数据，也可以获取调用的其他Activity返回的结果
             * startActivityForResult()方法期望在Activity销毁的时候能够返回一个结果给上一个Activity。
             *
             * 获取返回结果需要在当前Activity中重写onActivityResult()方法
             */
            // startActivityForResult()方法接收两个参数：
            // 第一个参数还是Intent；第二个参数是请求码，用于在之后的回调中判断数据的来源。
            /*val intent = Intent(this,SecondActivity::class.java)
            startActivityForResult(intent,1)*/
            /**
             * startActivityForResult已在最新版本中弃用，需要使用registerForActivityResult替代
             */
            /*val intent = Intent(this,SecondActivity::class.java)
            launcher.launch(intent)*/
            /**
             * Activity默认启动模式为standard，在standard模式下，每当启
             * 动一个新的Activity，它就会在返回栈中入栈，并处于栈顶的位置。对于使用standard模式的
             * Activity，系统不会在乎这个Activity是否已经在返回栈中存在，每次启动都会创建一个该
             * Activity的新实例。
             */
            // 再次启动当前Activity,每点击一次按钮，就会创建出一个新的FirstActivity实例。
            val intent = Intent(this,FirstActivity::class.java)
            startActivity(intent)
            // 如果在AndroidManifest.xml中把FirstActivity的launchMode改为singleTop，
            // 在启动Activity时如果发现返回栈的栈顶已经是该Activity，则认为可以直接使用它，不会再创建新的Activity实例。
            // 不过当FirstActivity并未处于栈顶位置时，再启动FirstActivity还是会创建新的实例的。

        }
        /**
         * 销毁Activity的方法：通过finish()方法
         */
       /* button1.setOnClickListener {
            finish()
        }*/
    }

    /**
     * 重写这个方法来得到其他Activity返回的数据
     *
     * onActivityResult()方法带有3个参数：第一个参数requestCode，即我们在启动Activity
     * 时传入的请求码；第二个参数resultCode，即我们在返回数据时传入的处理结果；第三个参
     * 数data，即携带着返回数据的Intent。由于在一个Activity中有可能调用
     * startActivityForResult()方法去启动很多不同的Activity，每一个Activity返回的数据都
     * 会回调到onActivityResult()这个方法中，因此我们首先要做的就是通过检查
     * requestCode的值来判断数据来源。确定数据是从SecondActivity返回的之后，我们再通过
     * resultCode的值来判断处理结果是否成功。最后从data中取值并打印出来，这样就完成了向
     * 上一个Activity返回数据的工作。
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            1 -> if (resultCode  == RESULT_OK){
                val returnedData = data?.getStringExtra("data_return")
                Log.d("FirstActivity","returned data is $returnedData")
            }
        }
    }

    /**
     * 重写创建显示菜单的方法
     */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // 调用父类的getMenuInflater方法得到一个MenuInflater对象，再调用它的inflate方法就可以给当前Activity创建菜单了
        // inflate接收两个参数：第一个参数用于指定通过哪一个资源文件来创建菜单，这里是传入自定义的R.menu.main；第二个参数是指定菜单项将添加到哪一个Menu当中
        menuInflater.inflate(R.menu.main,menu)
        // 最后返回true，表示允许创建的菜单显示出来，如果返回了false，创建的菜单将无法显示
        return true
    }

    /**
     * 定义菜单响应事件
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // 通过调用item.itemId来判断点击的是哪一个菜单项
        when(item.itemId){
            R.id.add_item -> Toast.makeText(this,"You Clicked Add",Toast.LENGTH_SHORT).show()
            R.id.remove_item -> Toast.makeText(this,"You clicked Remove",Toast.LENGTH_SHORT).show()
        }
        return true
    }
}