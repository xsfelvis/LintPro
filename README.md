# LintPro

>为什么需要自定义Lint

 - 原生Lint无法满足团队特有的需求，如：编码规范

 - 原生Lint存在一些检测缺陷或者缺少一些我们认为有必要的检测,如new Message等


>自定义Lint,主要完成了以下功能

- Actvity、Fragment布局文件名称前缀为`actvity_`、`fragment_`的检查
- Log、System.out.pritln打印必须为工程中自定义的`AppLog`检查(这个因项目而异)
- 检测new Message,提醒使用`Message.Obtain()`/`handler.obtainMessage`
- xml文件中各种控件命名规范化，如Buttion前缀为btn，适合规范化编程

> 依赖方式

采用LinkedIn提供了另一种思路 : 将jar放到一个aar中。这样我们就可以针对工程进行自定义Lint，lint.jar只对当前工程有效，使用前记得开启AS的支持自定义Lint检查功能，如下图
![](http://ogopjinry.bkt.clouddn.com/as_lint.png)

>部分效果示意图

![](http://ogopjinry.bkt.clouddn.com/Log.png)

![](http://ogopjinry.bkt.clouddn.com/activityprefix.png)

![](http://ogopjinry.bkt.clouddn.com/btn.png)

