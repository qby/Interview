# 问题
## 目标
1. LeakCanary源码
2. OkHTTP源码
3. ANR源码
4. Activity流程
5. Glide 源码
## 面试题
### OKHTTP

1. 你知道 okhttp 是怎么复用连接的吗？

2. Http 断点续传 addHeader("RANGE","bytes=100-500")

### LeakCanary

1. leakcanary 的原理，哪些对象可以用来做 gc-root？
2. 那 Java 层的内存泄漏怎么检测
3. 原理.
4. 是不是所有对象泄漏 leakcanary 都能检测得到。
5. 他的引用链是怎么管理的？
6. 弱引用对象在 gc 的时候会被释放，那什么时候不会被释放？

### Glide
1. Glide 怎样去加载Gif
2. 怎样缓存数据
 Glide 分为四级缓存 前两个为内存缓存，后两个是磁盘缓存（需要异步）  <font color=Yellow>//todo 调查何时存入，怎么存入</font> 
 
    - 正在显示的缓存。当前这个Image是否正在显示到屏幕上？
    
        loadFromActiveResources() -> HashMap 先查看ActiveResources,弱引用。
        
    - 内存缓存。是否在内存里面

        loadFromCache() -> LruResourceCache -> LinkedHashMap
    
    
    - 磁盘缓存。返回图片是否已经decode、转换过。
    - 磁盘缓存。之前是否已将此图像的数据写入磁盘缓存中？
               生成文件的Key后loadFromMemory()
        

3. Glide缓存大小是多少？


    - Glide 一屏幕图片 * 2
        ```
        内存缓存 ：屏幕宽 * 高 * 4Byte（agrb8888）* 2 同时考虑手机内存
        ```
        
    - 默认磁盘缓存250M `DiskLruCacheWrapper -> DiskLruCache`
    
4. 怎么获取控件宽高的

    ```
    ViewTarget.getSize
        1. 获取用户指定的宽高override方法
        2. 先通过view 的 LayoutParam 和view.getHeight() ,view.getWidth() 决定
        3. 如果不合法则通过 ViewTreeObserver observer = view.getViewTreeObserver().addOnPreDrawListener();
        4. 在Size确定后再去取数据
    ```



1. 怎么处理centercorp
2. 怎么加载InputStream
3. 怎么判断Active 内存

    后台线程，弱引用放入队列，清理
    
### LruCache
LruCache 默认返回getItemSize 为 1 ;需要覆盖getByteCount()
```
LruCache<String,Bitmap> cache = new  LruCache<String,Bitmap>(4*1024* 1024) {
    getItemSize(Bitmap item) {
        return item.getByteCount();
    }

}
```


### HashMap
1. 查找时间复杂度由On 变成 O1 通过 `Hash & lenght - 1` 获取 Index
2. 如果Key位null放入0个位置
3. 在需要扩容时，计算了Key的index 所在的数组刚好没有值，则会直接加入table[index]暂时不会扩容

### ConcorrentHashMap
### LinkedHashMap

### 杂
1. dex: 普通dex   classes.dex，  odex： 优化后的dex odex/classes.dex