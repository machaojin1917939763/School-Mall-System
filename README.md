# School-Mall-System

#### 介绍
School-Mall-System是一款用于学校内一个中小型的商品交易平台，使用SpringCloud框架进行开发，包括前台商城系统及后台管理系统，商家登录后台系统后，可以新建商品分类、品牌、商品、商家属性等基本信息，新建商品后可以选择是否上架，以及设置商品库存等。本商城系统模仿京东商城系统，用户上手即用，没哟学习成本，并且操作流程也和京东商城一样，用户可以在首页搜素、秒杀、添加购物车、支付等功能，因为是校内商城系统，商家需自行配送，若超出配送范围，可配合快递进行使用，目前本商城系统还在开发阶段，预计一个月后开发完成。

#### 软件架构
```
School-Mall-System
├── common -- 工具类及通用代码
├── RuoYIi -- Ruoyi工具自动生产代码
├── coupon -- 优惠券系统
├── gateway -- 后台商城管理路由系统
├── search -- 基于Elasticsearch的商品搜索系统
├── member -- 会员系统
|—— product -- 商城商品管理系统
|—— renren-fast -- 商城后端台管理系统（前后分离）
|—— ware -- 商品库存系统
|—— order -- 订单系统
└── 未完待续
```


#### 技术选型
后端技术
```
技术	说明	
SpringBoot	Web应用开发框架	
SpringCloud 分布式框架
SpringSecurity	认证和授权框架	
MyBatis	ORM框架
MyBatis Plus MyBatis增强
Ruoyi	数据层代码生成器	
Elasticsearch	搜索引擎	
RabbitMQ	消息队列（当前未整合）	
Redis	内存数据存储		
Kibana	日志可视化查看工具	
Nginx	静态资源服务器	
Docker	应用容器引擎	
Jenkins	自动化部署工具	（当前未整合）	
Druid	数据库连接池	
OSS	对象存储	
JWT	JWT登录支持	（当前未整合）
Lombok	Java语言增强库	
Hutool	Java工具类库	
PageHelper	MyBatis物理分页插件	
Swagger-UI	API文档生成工具	
SpringCache 缓存框架
未完待续。。。
```
### 前端技术
```
技术	说明	
Vue	前端框架	
Vue-router	路由框架	
Vuex	全局状态管理框架	
Element	前端UI框架	
Axios	前端HTTP框架	
Thymeleaf 模板引擎
未完待续。。。
```
