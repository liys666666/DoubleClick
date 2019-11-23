# DoubleClick
V3.0 的使用方法: 

**1.导入:**
```
//项目根目录下,build.gradle
buildscript {
    ...
    dependencies {
        classpath 'com.android.tools.build:gradle:3.2.0'
        classpath 'com.hujiang.aspectjx:gradle-android-plugin-aspectjx:2.0.0' //添加
    }
}


allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }  //添加
		}
	}
```
```
//app目录下build.gradle
apply plugin: 'android-aspectjx' //添加
dependencies {
    implementation 'com.github.liys666666:DoubleClick:V3.0.0'  //添加
}
```
如果不需要额外处理的话, 到这里就结束了, 同一个按钮点击, 默认最小间隔时间是500毫秒.

**2.添加自定义属性:**
```
public class AppApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();

        DoubleClickHelper
                .getInstance()
                .delayTime(500)  //间隔时间
                .addAnnotationClass(UnifiedDoubleClick.class) //自定义属性, 可叠加
                .addAnnotationClass(UnifiedDoubleClick2.class);
    }
}
```

```
public interface UnifiedDoubleClick{ //用户自定义接口
//    >>>>>>>>>>>>>>>>>>>屏蔽DoubleClick的Activity>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
//    @ACancelActivity(activitys = {
//            MainActivity.class,
//    })
//    void cancelActivity();


//    >>>>>>>>>>>>>>>>>>>单个view处理>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    @AAddDoubleClick(activity = MainActivity.class,
            addIds = {R.id.btn1, R.id.btn2},
            times = {0, 2000})  //点击的间隔时间
    void mainActivity();

//    >>>>>>>>>>>>>>>>>插入自定义clickListener>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    @AClickListener(activity = MainActivity.class,
            lisenner = LoginClickListener.class, //自定义clickListener
            ids = {R.id.btn2})
    void interceptMain();
}
```


```
public class LoginClickListener implements IOnClickListener {

    public static boolean isLogin = false;

    @Override
    public boolean isNext(View v) {
        //判断登录逻辑
        if(!isLogin){
            LogUtil.d("未登录");
            Toast.makeText(v.getContext(), "未登录", Toast.LENGTH_SHORT).show();
        }
        return isLogin;
    }


    @Override
    public void after(View view) {
        //执行click后
    }

}

```

**扩展---可调用方法**	
```
DoubleClickHelper.openAll(); //开启
DoubleClickHelper.closeAll(); //关闭

//(可选)用hook方式处理单个view, 时间会和上面的时间叠加, 一般使用场景: Activity只有个别按钮需要doubleClick
DoubleClickHelper.hookView(View view, long delayTime); 
```


# 三.历史版本:
**V3.0.0:**	
使用AOP的方式处理, 使用方式和2.0基本一样, 比2.0的方式性能更好.

[V2.0版本](https://github.com/liys666666/DoubleClick/blob/master/README2.0.6.md).

[V1.0版本](https://github.com/liys666666/DoubleClick/blob/master/README1.0.4.md)

* 如果框架中存在不足或者有什么建议, 欢迎指出, 我这边会尽快处理, 让框架更完善, 希望这个框架能帮助更多人, 如果觉得好用, 欢迎star.

