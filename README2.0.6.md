# DoubleClick
V2.0 的使用方法: 

简书: https://www.jianshu.com/p/df0ef3866cc1
CSDN: https://blog.csdn.net/qq_33199629/article/details/101563232

**简单介绍:**
```
//项目根目录下,build.gradle
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }  //添加
		}
	}
```
```
//app目录下build.gradle
dependencies {
    implementation 'com.github.liys666666:DoubleClick:V2.0.6'  //添加
}
```
```
public class AppApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();

//        ViewDoubleHelper.init(this); //默认时间：1秒
//        ViewDoubleHelper.init(this, 500); //自定义点击间隔时间(单位：毫秒)
          ViewDoubleHelper.init(this, 1000, UnifiedDoubleClick.class); //自定义点击间隔时间(单位：毫秒)
          
          //单独设置, 不同的AnnotationClass可叠加(V2.0.5版本后可用)
          ViewDoubleHelper.addAnnotationClass(UnifiedDoubleClick2.class);
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
public class LoginClickListener extends BaseClickListener{

    public static boolean isLogin = false; //模拟是否登陆

    @Override
    public boolean isNext(View v) {
        //判断登录逻辑 (onClick前执行)

        return isLogin;  //true继续执行onClick,  false不再执行
    }

    @Override
    public void after(View view) {
        //onClick后 执行
    }

}
```

**扩展---可调用方法**	
```
//hook单个view(子View不会跟着变化) 例如: Recyclerview使用的话, itemView是不起作用的
ViewDoubleHelper.hookView(View view, long delayTime); 

hook单个view(所有子View会跟着一起变化) 例如: Recyclerview使用的话, itemView以及子控件全部有用
ViewDoubleHelper.hookChildViews(View view, long delayTime); 

//重新hook当前Activity
ViewDoubleHelper.hookActivity();

//如果你有更好的实现方法, 也可以自己实现
ViewDoubleHelper.addIViewDoubleClick(IViewDoubleClick iHookView);
```


# 三.历史版本:
**V2.0.6:**	
* 修复:
* ViewDoubleHelper.hookChildViews() 不起作用
* ViewDoubleHelper.hookActivity()在onResume()前 调用不起作用.

**V2.0.5:**	
* 优化: 问题描述:原来的注解只能在Application初始化时传一个类进去;
* 问题1: 如果注解使用过多, 所有的注解都写在一个类里面, 不好管理.
* 问题2: 在组件化架构中, 有个id无法在Application中获取.
* 解决办法: 使用新增方法 ViewDoubleHelper.addAnnotationClass(annotationClass); 可设置多个

**V2.0.4:**	
* 修复: 动态加载View不起作用(包括自定义View, Fragment, RecyclerView中不起作用等等)。

**V2.0.2:**	
* 1. 修复兼容问题：注解android9.0不起作用.
* 2. 手动重新hookActivity

**V2.0.1:**     
* 1. 完全不需要在具体的Activity和Fragment加代码.
* 2. 是否屏蔽hook、单个View处理, 自定义拦截器，全部使用接口注解处理.
* 3. 单个View处理、自定义拦截器，兼容Fragment.

[V1.0版本](https://github.com/liys666666/DoubleClick/blob/master/README1.0.4.md)

* 如果框架中存在不足或者有什么建议, 欢迎指出, 我这边会尽快处理, 让框架更完善, 希望这个框架能帮助更多人, 如果觉得好用, 欢迎star.

