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


**V.1.0.2以上(包含)**

onClick重复逻辑统一处理新写法


**历史版本:**

**V1.0.3:**	修复bug: 每次pause返回, 都重复hookView


**V1.0.2:**	
* ①.onClick重复逻辑统一处理使用IAddCustomHookClick处理, 添加自由扩展实现. 
* ②. 新增方法 ViewDoubleHelper.setIViewDoubleClick(IViewDoubleClick), ViewDoubleHelper.setICustomHookClick(ICustomHookClick);



**V1.0.1:** 	新增功能: onClick重复逻辑统一处理



**V1.0.0:** 	新增功能: 无入侵解决按钮重复点击
