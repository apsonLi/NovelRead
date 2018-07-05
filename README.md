# NovelRead
## 与服务端的通信加密采用了 hmacsha1通用加密算法,只要私密钥匙不泄露,就不会被人通过破解app盗用api。
因为这个app其实是将各种android基础代码实现的练手作品，
例如
* 基础的andorid UI 设计
* 常用的activity跳转操作,数据回传,stratactivityforresult
* listview加载优化
* 底层ndk调用
* broadcastReceiver修改UI界面,
* android消息传递的Handler,Looper
* 主线程不能进行http请求，必须在子线程中开启,
* 子线程不能修改UI必须开启UI线程或者将数据传递到UI线程来渲染出数据
### 诸如此类,基础的android开发遇到的问题和一些开发者必须掌握的知识,都会在此记录下来。
