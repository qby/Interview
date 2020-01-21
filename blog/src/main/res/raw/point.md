# 考点整理
## Java 
### 1.基础
1. 浅拷贝和深拷贝
	- 浅拷贝：对基本数据类型进行值传递，对引用数据类型进行引用传递般的拷贝，此为浅拷贝。
	- 深拷贝：对基本数据类型进行值传递，对引用数据类型，创建一个新的对象，并复制其内容，此为深拷贝。

### 2.虚拟机

### 3.HashMap

## Android 
### 1.Handler
1. Handler机制，子线程为什么不能更新UI？

	*Android UI操作并不是线程安全的，并且这些操作必须在UI线程执行。*

2. ThreadLocal

	该类提供线程局部变量。这些变量不同于它们的正常变量，即每一个线程访问自身的局部变量时，都有它自己的，独立初始化的副本。该变量通常是与线程关联的私有静态字段，列如用于ID或事物ID。
    *实现原理ThreadLocalMap*
    
3. Handler和Looper是如何建立关联的
	- Handler构造方法传入Looper
	- 使用Looper.prepare()在创建当前线程的Looper

4. Message在队列中的排序

	`Queue.enqueueMessage()`。
    在将消息加入到消息队列中时，已经将消息按照等待时间进行了排序。排序分为两种情况**（message.when是与当前的系统的时间差）**
    
  - 第一种：如果队列中没有消息，或者当前进入的消息比消息队列中头部的消息等待时间短，那么就放在消息队列的头部
 - 第二种：反之，循环遍历消息队列，把当前进入的消息放入合适的位置（比较等待时间）
 
5. Handler的泄露路径
	- Message被MessageQueue持有
	- Message拥有Handler的引用。是因为Message通过Looper的loop()方法取出后，需要相应的Handler来处理消息（msg.target ==发送消息的Handler）。
	-
   
  6. IdleHandler 闲时任务
  
  ``` 
  Looper.myQueue().addIdleHandler(new MessageQueue.IdleHandler() {
            @Override
            public boolean queueIdle() {
                Log.d(TAG, "queueIdle() called");
                return false;
            }
        });
   ```
 
 
 ### 2.图片
 1. 加载内存大小的计算

 	- **jpg、png 只是图片的容器，图片文件本身的大小与它所占用的内存大小没有什么关系，当然它们的压缩算法并不一样，在解码时所耗的内存与效率此时就会有些区别。**
 	- **控件大小不影响图片加载内存大小**
 	- **一张图片占用的内存大小的计算公式：分辨率 * 像素点大小；但分辨率不一定是原图的分辨率，需要结合一些场景来讨论，像素点大小就几种情况：ARGB_8888(4B)、RGB_565(2B) 等等。**
 	- **使用Bitmap.decodeResource()方法会根据图片存放不同目录会做一次分辨率的转换。_ 规则如下：_ **
	
    ```
   新图高度 = 原图高度 * (设备dpi / 目录对应的dpi)
   新图宽度=  原图宽度 * (设备dpi / 目录对应的dpi)
    ```
- **其他图片的来源，如磁盘，文件，流等，均按照原图的分辨率来进行计算图片的内存大小。**


## OSS
### Glide
1. Glide默认的Bitmap格式是RGB_565
2. 缓存ImageView大小的图片 , 针对同一张图片，两个ImageView会缓存多张
3. 怎样去加载Gif，怎样缓存数据，怎么获取控件宽高的，怎么处理centercorp


## 杂
1. TouchSlop 系统默认值为8dp
2. `post`和`postOnAnimation` 区别
		post 加入队列抛到主线程执行,(View 的队列) 。 `postOnAnimation` 立刻向主线程发送message，执行 。