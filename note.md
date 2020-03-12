## 校园商铺项目
### 0.环境的配置
1. 前端
	* jsp/html与java语言解耦
	* SUI Mobile
2. 后端
	* SSM
	* redis
	* mysql
3. maven工程导入的依赖
	* pom导入相关的依赖jar包
### 1.创建各种实体类以及相关的数据库表

### 2.创建配置文件
1. jdbc.properties
	指定了数据库的连接方式
2. mybatis-config.xml
	对mybatis进行配置
3. spring-service.xml
	将jdbc.properties,mybatis-config.xml进行了加载.
	创建了DataSource连接池.
	配置了mybatis与数据库进行交互的方式.
4. spring-service.xml
	进行事务管理,将datasource注入到事务管理器中.
5. spring-web.xml
	定义dispatcherservlet如何相应url请求,定义controller的一些行为.
6. web.xml:
	将dispatcher作为servlet进行注入,同时整合所有spring相关的配置文件.
	
#### 验证DAO层的正确性
*　AreaDao.xml的编写
	mybatis通过配置文件将创建的Dao接口做一个实现
	
#### logback日志文件

### 3. 店铺注册
#### 1. 连接数据库
#### 2. Mybatis数据库表映射关系的配置
#### 3.　dao->service->controller层代码的编写,Junit的使用
#### 4. Session,图片处理工具Thumbnailator的使用
#### 5. suimobile前端设计与开发 