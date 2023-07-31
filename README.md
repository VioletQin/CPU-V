# CPU-V
## 1、介绍

这个软件是我大三一个课程设计的作品，使用JDK8 和 JavaFX制作,仿照 CPU-Z 和 Windows的任务管理器搞的CPU参数监控程序。
感觉很有趣所以就写了来玩。

![image](https://github.com/VioletQin/OSCourseDesign/blob/main/image-20230224173132178.png)



## 2、IDEA启动

使用maven或手动导入jar依赖

```
oshi-core-6.4.0:
https://mvnrepository.com/artifact/com.github.oshi/oshi-core

jna-5.13.0:
https://mvnrepository.com/artifact/net.java.dev.jna/jna

jna-platform-5.13.0:
https://mvnrepository.com/artifact/net.java.dev.jna/jna-platform

log4j-1.2.17
slf4j-api-1.7.25
slf4j-log4j12-1.7.25
```



过程：

在工程下创建一个lib包，将上述5个jar包导入

然后在idea中点击 File -> Project Structure -> Libraries 中配置jar包依赖



随后点击启动就没问题了



## 3、问题

在这次开发中也算遇到一些问题

比如:

- oshi库的 `CentralProcessor` 中的 `getProcessorCaches()` 方法获取的缓存数据的缓存大小全部都为56

- oshi库的 `CentralProcessor` 中的 `getMaxFreq()` 和 `getCurrentFreq()` 方法获取的数据全部为0
- JavaFx 中 绘制动态AreaChart的时候，显示的面积会出现问题。大概情况是显示的面积总是原点到最远那个点的坐标的线和折线图的面积。具体状况可以将`MainController.java`中的LineChart替换成AreaChart试试







































