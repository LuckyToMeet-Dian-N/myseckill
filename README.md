# 简单抢购的API

## 项目环境搭建：
该项目使用的技术包括如下：
1. **springboot1.5.14**
2. **Mybatis**
3. **RabbitMQ**
4. **redis**
**IDE**: IntelliJ IDEA 2017.2.5 x64 
**操作系统**: win10

##项目介绍博客




##项目的运行：
###**第一步**
　　　下载本项目可以选择Download Zip
###**第二步**
　　　由于使用的的开发环境是IDEA，所以，推荐大家将项目下载后导入IDEA中。**注意：** springboot项目最低支持是JDK1.8,所以下载了的小伙伴请选择JDK1.8以上的环境

###**第三步**
　　　配置好相关环境例如redis服务，RabbitMQ服务。并将项目sql文件夹的seckill.sql文件导入数据库

###**第四步**
　　　修改application.properties文件中的rabbitmq与redis的ip地址，最后修改数据库为您自己的数据库名字。服务的安装可参考博客：
　　　　[随笔（五） rabbitmq的安装与配置](https://blog.csdn.net/weixin_41622183/article/details/82824182)<br/>
　　　　[随笔（三）-- linux下安装redis（详细教程）](https://blog.csdn.net/weixin_41622183/article/details/80030815)
###**第五步**
　　　运行项目。执行接口描述想的几个接口即可。由于个人原因，没有做界面，只做了相关接口。所以登录接口可用忽略掉。aop拦截器默认使用模拟用户了。如果需要，可自行增加页面并揭开aop标识的注释。
###**第六步**
　　　项目部署完成后，需要先调用初始化抢购商品接口，之后再调用抢购商品接口。不适合分布式环境，仅供学习研究。
##项目接口简述：
|接口名字|接口url |接口数据返回
| ------|--- |------|
| 初始化抢购商品|http://localhost:8080/resetProduct| {"msg":"success","code":0,"data":"OK"}|
| 开始抢购接口|http://localhost:8080/seckill?productId=1|{"msg":"success","code":0,"data":"OK"}|
| 用户登录接口|http://localhost:8080/login?password=123456&account=Gentle| {"msg":"success","code":0,"data":"f6efa8e6-3e91-4da4-99b1-d38691e1ef02"}|



##项目中可学习的知识点
- rabbitmq入门
- redis的入门
- 单例模式的好处（需自己百度了解）
- 接口规范
- 项目中遇到的小坑提醒









