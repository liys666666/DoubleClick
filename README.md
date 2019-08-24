# DoubleClick
框架主要有两个功能(基本用法): 

**1. 无入侵解决按钮重复点击.** [使用方法](https://www.jianshu.com/p/7f3e5c8b8643)

**2. onClick重复逻辑统一处理.** [使用方法](https://www.jianshu.com/p/b4038a2d68eb)

**① 导入框架**
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
    implementation 'com.github.liys666666:DoubleClick:V1.0.3'  //添加
}
```
**② 自定义的Application初始化即可**
```
public class AppApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();

        ViewDoubleHelper.init(this); //默认时间：1秒
//        ViewDoubleHelper.init(this, 2000); //自定义点击间隔时间(单位：毫秒)
    }
}
```


**V.1.0.2(包含)以上**

1. onClick重复逻辑统一处理--添加新写法(可选): 
先看基本用法: [onClick重复逻辑统一处理](https://www.jianshu.com/p/b4038a2d68eb)
实现IAddCustomHookClick接口, 其中LoginClickListener可以看上面****

```
public class MainActivity extends AppCompatActivity implements IAddCustomHookClick{
	    @Override
	    public List<CustomHookBean> getCustomHookList() {
		int[] ids = {R.id.btn1, R.id.btn2}; //需要处理的id
		List<CustomHookBean> list = new ArrayList<>();
		for (int i = 0; i < ids.length; i++) { //添加
		    list.add(new CustomHookBean(ids[i], new LoginClickListener()));
		}
		return list;
	    }
}
```

2. 如果有更好的具体实现方法, 也可以自己实现, 具体做法, 其它不变, 在AppApplication是添加:
```
        //连续重复点击多次, 自定义具体实现
//        ViewDoubleHelper.setIViewDoubleClick(new IViewDoubleClick() {
//            @Override
//            public void hookActivityViews(Activity activity, long delayTime) { 
//			//hook  Activity内所有View
//            }
//
//            @Override
//            public void hookResView(Activity activity, int viewResId, long delayTime) {
//			//根据ViewId hook对应的View  
//            }
//
//            @Override
//            public void hookView(Activity activity, View view, long delayTime) {
//			//hook对应的View
//            }
//        });


        //onClick重复逻辑统一处理: 自定义具体实现
//        ViewDoubleHelper.setICustomHookClick(new ICustomHookClick() {
//            @Override
//            public void hookResView(Activity activity, int viewResId, IOnClickListener iClickListener) {
//
//            }
//
//            @Override
//            public void hookView(Activity activity, View view, IOnClickListener iClickListener) {
//
//            }
//        });
```


**历史版本:**

**V1.0.3:**	修复bug: 每次pause返回, 都重复hookView


**V1.0.2:**	
* ①.onClick重复逻辑统一处理使用IAddCustomHookClick处理, 添加自由扩展实现. 
* ②. 新增方法 ViewDoubleHelper.setIViewDoubleClick(IViewDoubleClick), ViewDoubleHelper.setICustomHookClick(ICustomHookClick);



**V1.0.1:** 	新增功能: onClick重复逻辑统一处理



**V1.0.0:** 	新增功能: 无入侵解决按钮重复点击
