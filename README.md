# PermissionX
## 第一行代码实战项目 ***权限请求框架***
初版完成，使用 ***Activity Results API*** 对权限申请操作进行了封装，虽然新API的底层仍然是onActivityResult那一套，不过总觉得使用起来更麻烦了，第一次写框架，尽力了  
  
**library**目录里是框架库，**demo**目录里就是kotlin和java的示例了，封装了单个权限请求以及多个权限请求的操作，demo里有很具体注释也很详细的示例了，library里的框架工作流程的注释和代码也很详细了，一般都能看懂，那么就这样吧，最后一个项目完工  

~~对用户永久拒绝授权的权限没有进行封装操作，[轮子哥的权限框架](https://gitee.com/getActivity/XXPermissions)会跳到app的权限页让用户手动开启，这样也挺好啊。。。~~

**minSdkVersion 23(安卓6)**&nbsp;&nbsp;&nbsp;&nbsp;**compileSdkVersion 31(安卓12)**&nbsp;&nbsp;&nbsp;&nbsp;**targetSdkVersion 31(安卓12)**  

![kotlin使用示例截图](https://user-images.githubusercontent.com/54784104/143451193-c507b932-6781-4e94-a63f-bdfdc92f27ba.png)  

![java使用示例截图](https://user-images.githubusercontent.com/54784104/143451458-bbecc606-d58a-4490-bd88-aad1dd9143e4.png)

对比下来还是感觉kotlin的更舒服啊，两个月没用java已经有些陌生了,java遍历map集合取值还会空指针警告=.=