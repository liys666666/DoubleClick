# DoubleClick
V2.0.1的使用方法: https://www.jianshu.com/p/df0ef3866cc1

**V2.0.2新增:** ViewDoubleHelper.hookActivity(); 重新hookActivity

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
    implementation 'com.github.liys666666:DoubleClick:V2.0.2'  //添加
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

# 三.历史版本:
**V2.0.2:**	
* 1. 修复：注解android9.0不起作用.
* 2. 手动重新hookActivity

**V2.0.1:**     
* 1. 完全不需要在具体的Activity和Fragment加代码.
* 2. 是否屏蔽hook、单个View处理, 自定义拦截器，全部使用接口注解处理.
* 3. 单个View处理、自定义拦截器，兼容Fragment.

[V1.0版本](https://github.com/liys666666/DoubleClick/blob/master/README1.0.4.md)

