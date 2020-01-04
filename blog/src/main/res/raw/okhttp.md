# OkHttp
> 市面上的网络库Volley、Okhttp、Retrofit、Mars、xUtils、HttpClient、async-http-client、HttpUrlConnection

Okhttp作为一款底层网络访问框架，它和Volley等上层网络框架不一样的地方在于，Okhttp自己实现了与服务端的TCP连接，并在此连接上根据HTTP协议的规范与服务端进行HTTP协议及内容的请求和响应。Okhttp将请求内容通过修正，填充等方式封装成符合HTTP规范的HTTP请求内容，通过TCP连接，将内容以流的方式输出给服务端，并从服务端返回的响应流中读取出响应内容，根据HTTP协议解析并作出相应的响应。

## 1. OkHttp特性
### 1.1 支持协议SPDY和Http/2
#### 1.1.1 SPDY 在OkHttp中deprecated
SPDY（读作“SPeeDY”）是Google开发的基于TCP的传输层协议，用以最小化网络延迟，提升网络速度，优化用户的网络使用体验。
SPDY并不是一种用于替代HTTP的协议，而是对HTTP协议的增强。新协议的功能包括数据流的多路复用、请求优先级以及HTTP报头压缩。
谷歌表示，引入SPDY协议后，在实验室测试中页面加载速度比原先快64%。
#### 1.1.2 Http/2 特性
> Http/1.x的问题：
> 连接无法被复用，每次请求都会进行三次握手基于文本传输

1. 多路复用 :基于TCP链接是全双工的
2. 二进制分帧 : 数据分为多个帧，多个请求同时进行
3. 头部压缩 : HPACK 通过建立首部表，用索引代表首部名，或者键值对，上次发送两端都会记住发送过哪些首部，下一次只需要传输差异数据，相同的数据直接用索引表示即可
> Gzip 只会压缩Body

1. 服务器推送

### 1.2 域名多IP支持，Socket自动选择最好线路
### 1.3 无缝的支持 GZIP 来减少数据流量
### 1.4 高效的IO操作，Okio
Okio它的底层流都是JavaIO定义的基础流
1. 更简洁的API
2. 提高性能
    使用了Segment数据结构（链表），通过对象池复用，较少对象创建销毁的代价，减少了消耗CPU的操作，提高内存使用率。
3. 对于流操作处理有可设置超时器（JavaIO对于数据流处理没有超时概念）

## 2. OkHttp使用
### 2.1 GET 和 POST
#### 2.1.1 GET
```
        OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder()
                .url("http://www.baidu.com")
                .get()
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, okhttp3.Response response) throws IOException {
                
            }
        });
```

#### 2.1.2 POST
```
OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(MediaType.get("application/json; charset=utf-8"), "body");
        Request request = new Request.Builder()
                .url("http://www.roundsapp.com/post")
                .post(body)
                .build();
        client.newCall(request)
                .enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(Call call, okhttp3.Response response) throws IOException {
                    //TODO : Response
                    }
                });
```
## 3. OkHttp的实现

<table>
    <tr>
        <td>客户端</td>
        <td colspan="6" align="center">OkHttpClient</td>
    </tr>
    <tr>
        <td>执行层</td>
        <td>Call</td>
        <td>Dispatcher</td>
        <td>Chain拦截器链</td>
        <td>线程池</td>
        <td>Dns</td>
        <td>Cache</td>
    </tr>
    <tr>
        <td>连接层</td>
        <td colspan="2">StreamAllocation</td>
        <td colspan="2">ConnectionPool</td>
        <td colspan="2">Okio</td>
    </tr>
</table>
### 3.1 Request执行流程
![Request执行流程](https://upload-images.jianshu.io/upload_images/1952476-d805eda2f6bc5dbd.png)
### 3.2 线程池和消息队列
`     executorService = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60, TimeUnit.SECONDS, new SynchronousQueue<Runnable>(), Util.threadFactory("OkHttp Dispatcher", false));
`
1. 0：核心线程数量，保持在线程池中的线程数量(即使已经空闲)，为0代表线程空闲后不会保留，等待一段时间后停止。
2. Integer.MAX_VALUE:表示线程池可以容纳最大线程数量
3. TimeUnit.SECOND:当线程池中的线程数量大于核心线程时，空闲的线程就会等待60s才会被终止，如果小于，则会立刻停止。
4. new SynchronousQueue<Runnable>()：线程等待队列。同步队列，按序排队，先来先服务
5. Util.threadFactory("OkHttp Dispatcher", false):线程工厂，直接创建一个名为OkHttp Dispatcher的非守护线程。

maxRequests = 64 会去指定并发 call 的最大个数。
maxRequestsPerHost = 5 每个主机最大请求数为5 ，也就是最多 5 个call公用一个 host


> [程池和消息队列](https://cloud.tencent.com/developer/article/1199030)

### 3.3 拦截器 (Interceptor)
```
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new LoggerInterceptor())  //应用拦截器
                .connectTimeout(5, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .writeTimeout(5, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build();
```

> Interceptor是拦截者的意思，在OkHttp中把Request请求或者Response回复做一些处理，他是一种面向方面/切面编程（AOP Aspect-Oriented Programming）.
在面向切面编程的就是在你的service或者一个方法，前调用一个方法，或者在方法后调用一个方法比如动态代理就是拦截器的简单实现，在你调用方法前打印出字符串（或者做其它业务逻辑的操作），也可以在你调用方法后打印出字符串，甚至在你抛出异常的时候做业务逻辑的操作。

#### 3.3.1 拦截器的使用
```
class LoggerInterceptor implements Interceptor {
        @Override
        public okhttp3.Response intercept(Chain chain) throws IOException {
            Log.d(TAG, "intercept: call before");

            Response response = chain.proceed(chain.request());

            Log.d(TAG, "intercept: call after");
            return response;
        }
    }
```
#### 3.3.2 应用拦截器的用途
1. 加上一些请求基本参数（如api版本号，客户端版本号等）。
2. Request加密签名：通过请求参数、时间戳等 一系列算法算出签名，在请求Header加上加密签名，服务器对此Header验证，防止攻击。

#### 3.3.3 OkHttp 内部拦截器
1. RetryAndFollowUpInterceptor
    - 负责网络重连的Interceptor
    - FollowUp意思是网络请求已经成功，但是服务器返回状态吗可能不是200，这是还需要对该请求进行检测。
    - 最大重连次数
2. BridgeInterceptor
    - 为Request设置User-Agent、Cookie、Accept-Encoding等相关请求头信息。到此为止一个完整的NetWork Request 就构建完毕，是时候发起真正的网络请求了。
    - 解析Gzip数据等
3. CacheInterceptor
4. ConnectInterceptor
    - 网络连接池的引入和管理机制。内部维护网络连接池，查找当前请求是否有对应可用的连接，避免每次请求重新建立和断开TCP连接。
    - 网络连接智能路由机制。重试查找可用IP，建立连接，同时记录连接失败的IP，避免重复请求无效IP。
    - 支持主流HTTP协议，HTTPS请求和Proxy代理服务。支持HTTP 1.1/2和SPDY协议，支持HTTPS，支持HTTP代理和SOCKS代理。
5. CallServerInterceptor
    - 主要是通过连接好的通道进行数据的交换
    - HttpCodec是一个接口，其实有两个实现类，分别是HttpCodec1、HttpCodec2, 对应着http1 的读写数据和 http2的 读写数据。两者是有差异的；

## 4. Volley更换为OkHttp底层请求
### 4.1 Volley的请求扩展性
### 4.2 OkHttpStack
1. `implementation 'com.squareup.okhttp3:okhttp-urlconnection:3.11.0'`
2. `Volley.newRequestQueue(sContext, new OkHttpStack());`

```
public class OkHttpStack extends HurlStack {

    private final OkUrlFactory okUrlFactory;
    public OkHttpStack() {
        this(new OkUrlFactory(new OkHttpClient()));
    }
    public OkHttpStack(OkUrlFactory okUrlFactory) {
        if (okUrlFactory == null) {
            throw new NullPointerException("Client must not be null.");
        }
        this.okUrlFactory = okUrlFactory;
    }
    @Override
    protected HttpURLConnection createConnection(URL url) throws IOException {
        return okUrlFactory.open(url);
    }

}
```

## 5.总结
### 5.1 What
### 5.2 How
### 5.3 Why
责任链的每一个Interceptor对Request进行处理，客户端只要将Requeset发送到责任链上即可，无需关心请求的处理细节和请求的传递，直到经历了所有Interceptor处理过后，返回结果。所以职责链将请求的发送者和请求的处理者解耦了。
## 6. 问题
1. ThreadPoolExecutor 参数workQueue：一个阻塞队列，用来存储等待执行的任务，如果当前对线程的需求超过了corePoolSize大小，才会放在这里；Okhttp中使用SynchronousQueue顺序加入，顺序执行 。所以请求没有优先级
2. 通过修改源码扩展可加入优先级， 在Request中加入优先级全局变量，修改workQueue为PriorityBlockingQueue

```
new ThreadPoolExecutor(4, Integer.MAX_VALUE, 60, TimeUnit.SECONDS,
                    new PriorityBlockingQueue<Runnable>(60, new AsycCallComparator<Runnable>()), Util.threadFactory("OkHttp Dispatcher", false));
```

1. hostname对应多个ip如何找到的 
2. 
```
  Dns.java
        public List<InetAddress> lookup(String hostname) throws UnknownHostException {
            if (hostname == null) throw new UnknownHostException("hostname == null");
            try {
                return Arrays.asList(InetAddress.getAllByName(hostname));
            } catch (NullPointerException e) {
                UnknownHostException unknownHostException =
                        new UnknownHostException("Broken system behaviour for dns lookup of " + hostname);
                unknownHostException.initCause(e);
                throw unknownHostException;
            }
        }
```
4. RetryAndFollowupInterceptor 加入较早问题我再考虑考虑


![全景](https://upload-images.jianshu.io/upload_images/5713484-a89f541f0f7797a6.png)