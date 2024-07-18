





# 7. 项目开发

## 7.1 项目初始化

### 7.1.1 数据库初始化

1. **创建数据库**

   在MySQL中创建一个`lease`数据库，建库语句如下

   ```sql
   CREATE DATABASE lease CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
   ```

2. **导入数据库脚本**

   将资料中的`lease.sql`脚本导入`lease`数据库，其中包含了建表语句和少量的原始数据。

### 7.1.2 创建工程

按照如下目录结构创建一个多模块的Maven工程。

```txt
lease
├── common（公共模块——工具类、公用配置等）
│   ├── pom.xml
│   └── src
├── model（数据模型——与数据库相对应地实体类）
│   ├── pom.xml
│   └── src
├── web（Web模块）
│   ├── pom.xml
│   ├── web-admin（后台管理系统Web模块——包含mapper、service、controller）
│   │   ├── pom.xml
│   │   └── src
│   └── web-app（移动端Web模块——包含mapper、service、controller）
│       ├── pom.xml
│       └── src
└── pom.xml
```

各模块的pom.xml文件内容如下：

**1.  根模块**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.atguigu</groupId>
    <artifactId>lease</artifactId>
    <version>1.0-SNAPSHOT</version>
    <packaging>pom</packaging>

    <modules>
        <module>common</module>
        <module>model</module>
        <module>web</module>
    </modules>
  
    <name>lease</name>
</project>
```

**2. common模块**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>com.atguigu</groupId>
        <artifactId>lease</artifactId>
        <version>1.0-SNAPSHOT</version>
    </parent>

    <artifactId>common</artifactId>

    <properties>
        <maven.compiler.source>17</maven.compiler.source>
        <maven.compiler.target>17</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>

</project>
```

**3. model模块**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>com.atguigu</groupId>
        <artifactId>lease</artifactId>
        <version>1.0-SNAPSHOT</version>
    </parent>

    <artifactId>model</artifactId>

    <properties>
        <maven.compiler.source>17</maven.compiler.source>
        <maven.compiler.target>17</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>
  
</project>
```

**4. web模块**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>com.atguigu</groupId>
        <artifactId>lease</artifactId>
        <version>1.0-SNAPSHOT</version>
    </parent>

    <artifactId>web</artifactId>
    <packaging>pom</packaging>
    <modules>
        <module>web-admin</module>
        <module>web-app</module>
    </modules>

    <properties>
        <maven.compiler.source>17</maven.compiler.source>
        <maven.compiler.target>17</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>
  
    <dependencies>
        <dependency>
            <groupId>com.atguigu</groupId>
            <artifactId>common</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>

        <dependency>
            <groupId>com.atguigu</groupId>
            <artifactId>model</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>
    </dependencies>
  
</project>
```

**5. web-admin模块**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>com.atguigu</groupId>
        <artifactId>web</artifactId>
        <version>1.0-SNAPSHOT</version>
    </parent>

    <artifactId>web-admin</artifactId>

    <properties>
        <maven.compiler.source>17</maven.compiler.source>
        <maven.compiler.target>17</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>

</project>
```

**6. web-app模块**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>com.atguigu</groupId>
        <artifactId>web</artifactId>
        <version>1.0-SNAPSHOT</version>
    </parent>

    <artifactId>web-app</artifactId>

    <properties>
        <maven.compiler.source>17</maven.compiler.source>
        <maven.compiler.target>17</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>

</project>
```

## 7.2 后台管理系统后端开发

### 7.2.1 项目初始配置

#### 7.2.1.1 SpringBoot配置

**1. pom文件配置**

在**父工程**的pom.xml文件中增加如下内容

```xml
<!-- 继承Spring Boot父项目 -->
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>3.0.5</version>
</parent>

<!-- 注意：直接替换pom文件中原有的properties -->
<properties>
    <maven.compiler.source>17</maven.compiler.source>
    <maven.compiler.target>17</maven.compiler.target>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <mybatis-plus.version>3.5.3.1</mybatis-plus.version>
    <swagger.version>2.9.2</swagger.version>
    <jwt.version>0.11.2</jwt.version>
    <easycaptcha.version>1.6.2</easycaptcha.version>
    <minio.version>8.2.0</minio.version>
    <knife4j.version>4.1.0</knife4j.version>
    <aliyun.sms.version>2.0.23</aliyun.sms.version>
</properties>

<!--配置dependencyManagement统一管理依赖版本-->
<dependencyManagement>
    <dependencies>
        <!--mybatis-plus-->
        <!--官方文档：https://baomidou.com/pages/bab2db/ -->
        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus-boot-starter</artifactId>
            <version>${mybatis-plus.version}</version>
        </dependency>

        <!--knife4j文档-->
        <!--官方文档：https://doc.xiaominfo.com/docs/quick-start -->
        <dependency>
            <groupId>com.github.xiaoymin</groupId>
            <artifactId>knife4j-openapi3-jakarta-spring-boot-starter</artifactId>
            <version>${knife4j.version}</version>
        </dependency>

        <!--JWT登录认证相关-->
        <!--官方文档：https://github.com/jwtk/jjwt#install-jdk-maven -->
        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt-api</artifactId>
            <version>${jwt.version}</version>
        </dependency>
        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt-impl</artifactId>
            <scope>runtime</scope>
            <version>${jwt.version}</version>
        </dependency>
        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt-jackson</artifactId>
            <scope>runtime</scope>
            <version>${jwt.version}</version>
        </dependency>

        <!--图形验证码-->
        <!--官方文档：https://gitee.com/ele-admin/EasyCaptcha -->
        <dependency>
            <groupId>com.github.whvcse</groupId>
            <artifactId>easy-captcha</artifactId>
            <version>${easycaptcha.version}</version>
        </dependency>

        <!--对象存储，用于存储图像等非结构化数据-->
        <!--官方文档：https://min.io/docs/minio/linux/developers/minio-drivers.html?ref=docs#java-sdk -->
        <dependency>
            <groupId>io.minio</groupId>
            <artifactId>minio</artifactId>
            <version>${minio.version}</version>
        </dependency>

        <!--阿里云短信客户端，用于发送短信验证码-->
        <!--官方文档：https://help.aliyun.com/document_detail/215759.html?spm=a2c4g.215759.0.0.49f32807f4Yc0y -->
        <dependency>
            <groupId>com.aliyun</groupId>
            <artifactId>dysmsapi20170525</artifactId>
            <version>${aliyun.sms.version}</version>
        </dependency>
    </dependencies>
</dependencyManagement>
```

在**web模块**的pom.xml文件中增加如下内容

- 依赖

```xml
<!--包含spring web相关依赖-->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>

<!--包含spring test相关依赖-->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <scope>test</scope>
</dependency>
```

- 插件

```xml
<!-- Spring Boot Maven插件，用于打包可执行的JAR文件 -->
<build>
    <plugins>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
        </plugin>
    </plugins>
</build>
```

**2. 创建application.yml文件**

在**web-admin模块**的`src/main/resources`目录下创建`application.yml`配置文件，内容如下：

```yaml
server:
  port: 8080
```

**3. 创建SpringBoot启动类**

在**web-admin模块**下创建`com.atguigu.lease.AdminWebApplication`类，内容如下：

```java
@SpringBootApplication
public class AdminWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdminWebApplication.class, args);
    }
}
```

#### 7.2.1.2 Mybatis-Plus配置

Mybatis-Plus为公用工具，故将其配置于**common模块**。具体配置可参考其[官方文档](https://baomidou.com/pages/bab2db/#release)。

**1. pom文件配置**

在**common模块**的pom.xml文件中增加如下内容：

```xml
<!--mybatis-plus-->
<dependency>
    <groupId>com.baomidou</groupId>
    <artifactId>mybatis-plus-boot-starter</artifactId>
</dependency>

<!--mysql驱动-->
<dependency>
    <groupId>com.mysql</groupId>
    <artifactId>mysql-connector-j</artifactId>
</dependency>
```

在**model模块**的pom.xml文件中增加如下内容：

> 因为**model模块**下的实体类中需要配置Mybatis-Plus相关注解，故也需引入Mybatis-Plus依赖

```xml
<!--mybatis-plus-->
<dependency>
    <groupId>com.baomidou</groupId>
    <artifactId>mybatis-plus-boot-starter</artifactId>
</dependency>
```

**2. application.yml配置**

在**web-admin模块**的`application.yml`文件增加如下内容：

```yaml
spring:
  datasource:
    type: com.zaxxer.hikari.HikariDataSource
    url: jdbc:mysql://<hostname>:<port>/<database>?useUnicode=true&characterEncoding=utf-8&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=GMT%2b8
    username: <username>
    password: <password>
    hikari:
      connection-test-query: SELECT 1 # 自动检测连接
      connection-timeout: 60000 #数据库连接超时时间,默认30秒
      idle-timeout: 500000 #空闲连接存活最大时间，默认600000（10分钟）
      max-lifetime: 540000 #此属性控制池中连接的最长生命周期，值0表示无限生命周期，默认1800000即30分钟
      maximum-pool-size: 12 #连接池最大连接数，默认是10
      minimum-idle: 10 #最小空闲连接数量
      pool-name: SPHHikariPool # 连接池名称
      
#用于打印框架生成的sql语句，便于调试
mybatis-plus:
  configuration:
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
```

**注意**：需根据实际情况修改`hostname`、`port`、`database`、`username`、`password`。

**3. 配置类**

在**common模块**下创建`com.atguigu.lease.common.mybatisplus.MybatisPlusConfiguration`类，内容如下：

```java
@Configuration
@MapperScan("com.atguigu.lease.web.*.mapper")
public class MybatisPlusConfiguration {
  
}
```

**注意**：`@MapperScan()`的包路径需要根据实际情况进行修改。

#### 7.2.1.3 Knife4j配置

**1. pom文件配置**

在**web模块**的pom.xml文件添加如下内容

> 因为**web-app**模块同样需要Knife4j依赖，故在两个的父工程引入依赖即可

```xml
<dependency>
    <groupId>com.github.xiaoymin</groupId>
    <artifactId>knife4j-openapi3-jakarta-spring-boot-starter</artifactId>
</dependency>
```

在**model模块**的pom.xml文件添加上述内容

> 因为**model模块**下的实体类需要配置Knife4j相关注解，故也需引入Knife4j依赖

```xml
<dependency>
    <groupId>com.github.xiaoymin</groupId>
    <artifactId>knife4j-openapi3-jakarta-spring-boot-starter</artifactId>
</dependency>
```

**2. 配置类**

后台管理系统和移动端的接口配置并不相同，所以需各自编写一个配置类。在**web-admin模块**下创建`config.custom.admin.web.com.atguigu.lease.Knife4jConfiguration`类，内容如下：

```java
@Configuration
public class Knife4jConfiguration {

    @Bean
    public OpenAPI customOpenAPI() {

        return new OpenAPI().info(
                new Info()
                        .title("后台管理系统API")
                        .version("1.0")
                        .description("后台管理系统API"));
    }
    
    @Bean
    public GroupedOpenApi systemAPI() {

        return GroupedOpenApi.builder().group("系统信息管理").
                pathsToMatch(
                        "/admin/system/**"
                ).
                build();
    }

    @Bean
    public GroupedOpenApi loginAPI() {

        return GroupedOpenApi.builder().group("后台登录管理").
                pathsToMatch(
                        "/admin/login/**",
                        "/admin/info"
                ).
                build();
    }
    
    @Bean
    public GroupedOpenApi apartmentAPI() {

        return GroupedOpenApi.builder().group("公寓信息管理").
                pathsToMatch(
                        "/admin/apartment/**",
                        "/admin/room/**",
                        "/admin/label/**",
                        "/admin/facility/**",
                        "/admin/fee/**",
                        "/admin/attr/**",
                        "/admin/payment/**",
                        "/admin/region/**",
                        "/admin/term/**",
                        "/admin/file/**"
                ).build();
    }
    @Bean
    public GroupedOpenApi leaseAPI() {
        return GroupedOpenApi.builder().group("租赁信息管理").
                pathsToMatch(
                        "/admin/appointment/**",
                        "/admin/agreement/**"
                ).build();
    }
    @Bean
    public GroupedOpenApi userAPI() {
        return GroupedOpenApi.builder().group("平台用户管理").
                pathsToMatch(
                        "/admin/user/**"
                ).build();
    }
}
```

**注意**：`pathsToMatch`参数需要根据实际情况进行配置。

#### 7.2.1.3 生成或导入基础代码

在完成上述配置后，便可使用一些逆向工具自动生成基础代码了（例如实体类、mapper、service等），在使用Mybatis-Plus作为存储层框架时，推荐使用IDEA中的[Mybatis X](https://baomidou.com/pages/ba5b24/)插件。除了可自动生成这些代码，也可直接导入资料中提供的代码。推荐大家直接导入。

导入的代码和目标位置如下：

| 导入代码    | 模块      | 包名/路径                                  | 说明                                   |
| ----------- | --------- | ------------------------------------------ | -------------------------------------- |
| 实体类      | model     | `com.atguigu.lease.model.entity`           | 与数据库表一一对应                     |
| 枚举类      | model     | `com.atguigu.lease.model.enums`            | 实体类中的某些状态类字段，使用枚举类型 |
| mapper接口  | web-admin | `com.atguigu.lease.web.admin.mapper`       | 略                                     |
| mapper xml  | web-admin | src/main/resources/mapper                  | 略                                     |
| service     | web-admin | `com.atguigu.lease.web.admin.service`      | 略                                     |
| serviceImpl | web-admin | `com.atguigu.lease.web.admin.service.impl` | 略                                     |

**知识点**：

- 实体类中的公共字段（例如`id`、`create_time`、`update_time`、`is_deleted`）抽取到一个基类，进行统一管理，然后让各实体类继承该基类。
- 实体类中的状态字段（例如`status`）或类型字段（例如`type`），全部使用枚举类型。

  > 状态（类型）字段，在数据库中通常用一个数字表示一个状态（类型）。例如：订单状态（1:待支付，2:待发货，3:待收货，4:已收货，5:已完结）。若实体类中对应的字段也用数字类型，例如`int`，那么程序中就会有大量的如下代码：
  >
  > ```java
  > order.setStatus(1);
  > 
  > if (order.getStatus() == 1) {
  >  order.setStatus(2);
  > }
  > ```
  >
  > 这些代码后期维护起来会十分麻烦，所以本项目中所有的此类字段均使用枚举类型。例如上述订单状态可定义为以下枚举：
  >
  > ```java
  > public enum Status {
  > 
  >  CANCEL(0, "已取消"),
  >  WAIT_PAY(1, "待支付"),
  >  WAIT_TRANSFER(2, "待发货"),
  >  WAIT_RECEIPT(3, "待收货"),
  >  RECEIVE(4, "已收货"),
  >  COMPLETE(5, "已完结");
  > 
  >  private final Integer value;
  >  private final String desc;
  > 
  >  public Integer value() {
  >      return value;
  >  }
  >  public String desc() {
  >      return desc;
  >  }
  > }
  > ```
  >
  > 订单实体类中的状态字段定义为`Status`类型：
  >
  > ```java
  > @Data
  > public class Order{
  >     private Integer id;
  >     private Integer userId;
  >     private Status status;
  >     ...
  > }
  > ```
  >
  > 这样上述代码便可调整为如下效果，后期维护起来会容易许多。
  >
  > ```java
  > order.setStatus(Status.WAIT_PAY);
  > ```
  
- 所有的实体类均实现了`Serializable`接口，方便对实体对象进行缓存。

- 所有的`Mapper`接口均没有使用`@Mapper`注解，而是使用`@MapperScan`注解统一扫描。

#### 7.2.1.4 导入接口定义代码

资料中提供了所有的Controller代码，并且Controller中定义好了每个接口（只有定义，没有实现），大家可直接导入接口定义相关的代码，然后只专注于接口逻辑的实现。

导入的代码和目标位置如下：

| 导入代码   | 模块      | 包名/路径                                | 说明                                                |
| ---------- | --------- | ---------------------------------------- | --------------------------------------------------- |
| controller | web-admin | `com.atguigu.lease.web.admin.controller` | 略                                                  |
| vo         | web-admin | `com.atguigu.lease.web.admin.vo`         | View Object，用于封装或定义接口接受及返回的数据结构 |
| result     | common    | `com.atguigu.lease.common.result`        | 统一定义接口返回的数据结构                          |

导入完成后，便可启动SpringBoot项目，并访问接口文档了，Knife4j文档的url为：http://localhost:8080/doc.html。

**知识点**：

- vo（View Object）：用于封装或定义接口接收及返回的数据的结构。

- 统一接口返回数据结构：为方便前端对接口数据进行处理，统一接口返回数据结构是一个良好的习惯。

  以下是所有接口统一返回的数据结构

  ```json
  {
      "code": 200,
      "message": "正常",
      "data": {
          "id": "1",
          "name": "zhangsan",
          "age": 10
      }
  }
  ```
  
  以下是与上述结构相对应的Java类
  
  - Result
  
    ```java
    @Data
    public class Result<T> {
    
        //返回码
        private Integer code;
    
        //返回消息
        private String message;
    
        //返回数据
        private T data;
    
        public Result() {
        }
    
        private static <T> Result<T> build(T data) {
            Result<T> result = new Result<>();
            if (data != null)
                result.setData(data);
            return result;
        }
    
        public static <T> Result<T> build(T body, ResultCodeEnum resultCodeEnum) {
            Result<T> result = build(body);
            result.setCode(resultCodeEnum.getCode());
            result.setMessage(resultCodeEnum.getMessage());
            return result;
        }
    
        public static <T> Result<T> ok(T data) {
            return build(data, ResultCodeEnum.SUCCESS);
        }
    
        public static <T> Result<T> ok() {
            return Result.ok(null);
        }
    
        public static <T> Result<T> fail() {
            return build(null, ResultCodeEnum.FAIL);
        }
        
        public static <T> Result<T> fail(Integer code, String message) {
            Result<T> result = build(null);
            result.setCode(code);
            result.setMessage(message);
            return result;
        }
    }
    
  - ResultCodeEnum
  
    为方便管理，可将返回码`code`和返回消息`message`封装到枚举类。
  
    ```java
    @Getter
    public enum ResultCodeEnum {
    
        SUCCESS(200, "成功"),
        FAIL(201, "失败"),
        PARAM_ERROR(202, "参数不正确"),
        SERVICE_ERROR(203, "服务异常"),
        DATA_ERROR(204, "数据异常"),
        ILLEGAL_REQUEST(205, "非法请求"),
        REPEAT_SUBMIT(206, "重复提交"),
        DELETE_ERROR(207, "请先删除子集"),
    
        ADMIN_ACCOUNT_EXIST_ERROR(301, "账号已存在"),
        ADMIN_CAPTCHA_CODE_ERROR(302, "验证码错误"),
        ADMIN_CAPTCHA_CODE_EXPIRED(303, "验证码已过期"),
        ADMIN_CAPTCHA_CODE_NOT_FOUND(304, "未输入验证码"),
    
    
        ADMIN_LOGIN_AUTH(305, "未登陆"),
        ADMIN_ACCOUNT_NOT_EXIST_ERROR(306, "账号不存在"),
        ADMIN_ACCOUNT_ERROR(307, "用户名或密码错误"),
        ADMIN_ACCOUNT_DISABLED_ERROR(308, "该用户已被禁用"),
        ADMIN_ACCESS_FORBIDDEN(309, "无访问权限"),
    
        APP_LOGIN_AUTH(501, "未登陆"),
        APP_LOGIN_PHONE_EMPTY(502, "手机号码为空"),
        APP_LOGIN_CODE_EMPTY(503, "验证码为空"),
        APP_SEND_SMS_TOO_OFTEN(504, "验证法发送过于频繁"),
        APP_LOGIN_CODE_EXPIRED(505, "验证码已过期"),
        APP_LOGIN_CODE_ERROR(506, "验证码错误"),
        APP_ACCOUNT_DISABLED_ERROR(507, "该用户已被禁用"),
    
    
        TOKEN_EXPIRED(601, "token过期"),
        TOKEN_INVALID(602, "token非法");
    
    
        private final Integer code;
    
        private final String message;
    
        ResultCodeEnum(Integer code, String message) {
            this.code = code;
            this.message = message;
        }
    }
    ```
  
  **注意：**
  
  由于`Result`和`ResultCodeEnum`中使用`@Data`、`@Getter`注解，因此需要再**common模块**中引入`lombok`依赖。
  
  ```xml
  <dependency>
      <groupId>org.projectlombok</groupId>
      <artifactId>lombok</artifactId>
  </dependency>
  ```

### 7.2.2 公寓信息管理

#### 7.2.2.1 房间支付方式管理

房间支付方式管理共有三个接口，分别是**查询全部支付方式列表**、**保存或更新支付方式**和**根据ID删除支付方式**，下面逐一实现。

首先在`PaymentTypeController`中注入`PaymentTypeService`依赖，如下

```java
@Tag(name = "支付方式管理")
@RequestMapping("/admin/payment")
@RestController
public class PaymentTypeController {

    @Autowired
    private PaymentTypeService service;
}
```

##### 1. 查询全部支付方式列表

在`PaymentTypeController`中增加如下内容

```java
@Operation(summary = "查询全部支付方式列表")
@GetMapping("list")
public Result<List<PaymentType>> listPaymentType() {
    List<PaymentType> list = service.list();
    return Result.ok(list);
}
```

**知识点**：

- **逻辑删除功能**

  由于数据库中所有表均采用逻辑删除策略，所以查询数据时均需要增加过滤条件`is_deleted=0`。

  上述操作虽不难实现，但是每个查询接口都要考虑到，也显得有些繁琐。为简化上述操作，可以使用Mybatis-Plus提供的逻辑删除功能，它可以自动为查询操作增加`is_deleted=0`过滤条件，并将删除操作转为更新语句。具体配置如下，详细信息可参考[官方文档](https://baomidou.com/pages/6b03c5/#%E4%BD%BF%E7%94%A8%E6%96%B9%E6%B3%95)。

  - 步骤一：在`application.yml`中增加如下内容

    ```yml
    mybatis-plus:
      global-config:
        db-config:
          logic-delete-field: flag # 全局逻辑删除的实体字段名(配置后可以忽略不配置步骤二)
          logic-delete-value: 1 # 逻辑已删除值(默认为 1)
          logic-not-delete-value: 0 # 逻辑未删除值(默认为 0)
    ```

  - 步骤二：在实体类中的删除标识字段上增加`@TableLogic`注解

    ```java
    @Data
    public class BaseEntity {
    
        @Schema(description = "主键")
        @TableId(value = "id", type = IdType.AUTO)
        private Long id;
    
        @Schema(description = "创建时间")
        @JsonIgnore
        private Date createTime;
    
        @Schema(description = "更新时间")
        @JsonIgnore
        private Date updateTime;
    
        @Schema(description = "逻辑删除")
        @JsonIgnore
        @TableLogic
        @TableField("is_deleted")
        private Byte isDeleted;
    
    }
    ```

    **注意**：

    逻辑删除功能只对Mybatis-Plus自动注入的sql起效，也就是说，对于手动在`Mapper.xml`文件配置的sql不会生效，需要单独考虑。

- **忽略特定字段**

  通常情况下接口响应的Json对象中并不需要`create_time`、`update_time`、`is_deleted`等字段，这时只需在实体类中的相应字段添加`@JsonIgnore`注解，该字段就会在序列化时被忽略。

  具体配置如下，详细信息可参考Jackson[官方文档](https://github.com/FasterXML/jackson-annotations#annotations-for-ignoring-properties)。

  ```java
  @Data
  public class BaseEntity {
  
      @Schema(description = "主键")
      @TableId(value = "id", type = IdType.AUTO)
      private Long id;
  
      @Schema(description = "创建时间")
      @JsonIgnore
      @TableField(value = "create_time")
      private Date createTime;
  
      @Schema(description = "更新时间")
      @JsonIgnore
      @TableField(value = "update_time")
      private Date updateTime;
  
      @Schema(description = "逻辑删除")
      @JsonIgnore
      @TableField("is_deleted")
      private Byte isDeleted;
  
  }
  ```


##### 2. 保存或更新支付方式

在`PaymentTypeController`中增加如下内容

```java
@Operation(summary = "保存或更新支付方式")
@PostMapping("saveOrUpdate")
public Result saveOrUpdatePaymentType(@RequestBody PaymentType paymentType) {
    service.saveOrUpdate(paymentType);
    return Result.ok();
}
```

**知识点**：

保存或更新数据时，前端通常不会传入`isDeleted`、`createTime`、`updateTime`这三个字段，因此我们需要手动赋值。但是数据库中几乎每张表都有上述字段，所以手动去赋值就显得有些繁琐。为简化上述操作，我们可采取以下措施。

- `is_deleted`字段：可将数据库中该字段的默认值设置为0。

- `create_time`和`update_time`：可使用mybatis-plus的自动填充功能，所谓自动填充，就是通过统一配置，在插入或更新数据时，自动为某些字段赋值，具体配置如下，详细信息可参考[官方文档](https://baomidou.com/pages/4c6bcf/)。

  - 为相关字段配置触发填充的时机，例如`create_time`需要在插入数据时填充，而`update_time`需要在更新数据时填充。具体配置如下，观察`@TableField`注解中的`fill`属性。

    ```java
    @Data
    public class BaseEntity {
    
        @Schema(description = "主键")
        @TableId(value = "id", type = IdType.AUTO)
        private Long id;
    
        @Schema(description = "创建时间")
        @JsonIgnore
        @TableField(value = "create_time", fill = FieldFill.INSERT)
        private Date createTime;
    
        @Schema(description = "更新时间")
        @JsonIgnore
        @TableField(value = "update_time", fill = FieldFill.UPDATE)
        private Date updateTime;
    
        @Schema(description = "逻辑删除")
        @JsonIgnore
        @TableLogic
        @TableField("is_deleted")
        private Byte isDeleted;
    
    }
    ```
  
  - 配置自动填充的内容，具体配置如下
  
    在**common模块**下创建`com.atguigu.lease.common.mybatisplus.MybatisMetaObjectHandler`类，内容如下：
  
    ```java
    @Component
    public class MybatisMetaObjectHandler implements MetaObjectHandler {
        @Override
        public void insertFill(MetaObject metaObject) {
            this.strictInsertFill(metaObject, "createTime", Date.class, new Date());
        }
    
        @Override
        public void updateFill(MetaObject metaObject) {
            this.strictUpdateFill(metaObject, "updateTime", Date.class, new Date());
        }
    }
    ```
  
  在做完上述配置后，当写入数据时，Mybatis-Plus会自动将实体对象的`create_time`字段填充为当前时间，当更新数据时，则会自动将实体对象的`update_time`字段填充为当前时间。

##### 3. 根据ID删除支付方式

在`PaymentTypeController`中增加如下内容

```java
@Operation(summary = "根据ID删除支付方式")
@DeleteMapping("deleteById")
public Result deletePaymentById(@RequestParam Long id) {
    service.removeById(id);
    return Result.ok();
}
```

**知识点**：

MybatisPlus逻辑删除功能的使用。

#### 7.2.2.2 房间租期管理

**房间租期管理**共有三个接口，分别是**查询全部租期列表**、**保存或更新租期信息**和**根据ID删除租期**，具体实现如下。

在`LeaseTermController`中增加如下内容

```java
@Tag(name = "租期管理")
@RequestMapping("/admin/term")
@RestController
public class LeaseTermController {

    @Autowired
    private LeaseTermService service;

    @GetMapping("list")
    @Operation(summary = "查询全部租期列表")
    public Result<List<LeaseTerm>> listLeaseTerm() {
        List<LeaseTerm> list = service.list();
        return Result.ok(list);
    }

    @PostMapping("saveOrUpdate")
    @Operation(summary = "保存或更新租期信息")
    public Result saveOrUpdate(@RequestBody LeaseTerm leaseTerm) {
        service.saveOrUpdate(leaseTerm);
        return Result.ok();
    }

    @DeleteMapping("deleteById")
    @Operation(summary = "根据ID删除租期")
    public Result deleteLeaseTermById(@RequestParam Long id) {
        service.removeById(id);
        return Result.ok();
    }
}
```

#### 7.2.2.3 标签管理

**标签管理**共有三个接口，分别是**[根据类型]查询标签列表**、**保存或更新标签信息**和**根据ID删除标签**，下面逐一实现。

首先在`LabelController`中注入`LabelInfoService`依赖，如下

```java
@Tag(name = "标签管理")
@RestController
@RequestMapping("/admin/label")
public class LabelController {

    @Autowired
    private LabelInfoService service;
}
```

##### 1. [根据类型]查询标签列表

在`LabelController`中增加如下内容

```java
@Operation(summary = "（根据类型）查询标签列表")
@GetMapping("list")
public Result<List<LabelInfo>> labelList(@RequestParam(required = false) ItemType type) {

    LambdaQueryWrapper<LabelInfo> queryWrapper = new LambdaQueryWrapper<>();
    queryWrapper.eq(type != null, LabelInfo::getType, type);
    List<LabelInfo> list = service.list(queryWrapper);
    return Result.ok(list);
}
```

**知识点**：

上述接口的功能是根据**type**（公寓/房间），查询标签列表。由于这个**type**字段在数据库、实体类、前后端交互的过程中有多种不同的形式，因此在请求和响应的过程中，**type**字段会涉及到多次类型转换。

首先明确一下**type**字段的各种形式：

- **数据库中**

  数据库中的**type**字段为`tinyint`类型

  ```
  +-------------+--------------+
  | Field       | Type         |
  +-------------+--------------+
  | id          | bigint       |
  | type        | tinyint      |
  | name        | varchar(255) |
  | create_time | timestamp    |
  | update_time | timestamp    |
  | is_deleted  | tinyint      |
  +-------------+--------------+
  ```

- **实体类**

  实体类中的**type**字段为`ItemType`枚举类型

  `LabelInfo`实体类如下

  ```java
  @Schema(description = "标签信息表")
  @TableName(value = "label_info")
  @Data
  public class LabelInfo extends BaseEntity {
  
      private static final long serialVersionUID = 1L;
  
      @Schema(description = "类型")
      @TableField(value = "type")
      private ItemType type;
  
      @Schema(description = "标签名称")
      @TableField(value = "name")
      private String name;
  }
  ```

  `ItemType`枚举类如下

  ```java
  public enum ItemType {
  
      APARTMENT(1, "公寓"),
      ROOM(2, "房间");
  
      private Integer code;
      private String name;
  
      ItemType(Integer code, String name) {
          this.code = code;
          this.name = name;
      }
  }
  ```

- **前后端交互中**

  前后端交互所传递的数据中**type**字段为**数字**（1/2）。
  
  <img src="images/请求参数和响应结果.png" style="zoom: 70%; border: 1px solid rgb(0, 0, 0);" />

具体转换过程如下图所示：

- **请求流程**

  <img src="images/枚举类型转换过程-请求参数.drawio.png" style="zoom: 200%;" />

  **说明**

  - SpringMVC中的`WebDataBinder`组件负责将HTTP的请求参数绑定到Controller方法的参数，并实现参数类型的转换。
  - Mybatis中的`TypeHandler`用于处理Java中的实体对象与数据库之间的数据类型转换。

- **响应流程**

  <img src="images/枚举类型转换过程-响应体.drawio.png" style="zoom:200%;" />

  **说明**

  - SpringMVC中的`HTTPMessageConverter`组件负责将Controller方法的返回值（Java对象）转换为HTTP响应体中的JSON字符串，或者将请求体中的JSON字符串转换为Controller方法中的参数（Java对象），例如下一个接口**保存或更新标签信息**

  <img src="images/枚举类型转换过程-请求体.drawio.png" style="zoom:200%;" />

下面介绍一下每个环节的类型转换原理

- **WebDataBinder枚举类型转换**

  `WebDataBinder`依赖于[`Converter`](https://docs.spring.io/spring-framework/reference/core/validation/convert.html)实现类型转换，若Controller方法声明的`@RequestParam`参数的类型不是`String`，`WebDataBinder`就会自动进行数据类型转换。SpringMVC提供了常用类型的转换器，例如`String`到`Integer`、`String`到`Date`，`String`到`Boolean`等等，其中也包括`String`到枚举类型，但是`String`到枚举类型的默认转换规则是根据实例名称（"APARTMENT"）转换为枚举对象实例（ItemType.APARTMENT）。若想实现`code`属性到枚举对象实例的转换，需要自定义`Converter`，代码如下，具体内容可参考[官方文档](https://docs.spring.io/spring-framework/reference/core/validation/convert.html#core-convert-Converter-API)。

  - 在**web-admin模块**自定义`com.atguigu.lease.web.admin.custom.converter.StringToItemTypeConverter`

    ```java
    @Component
    public class StringToItemTypeConverter implements Converter<String, ItemType> {
        @Override
        public ItemType convert(String code) {
    
            for (ItemType value : ItemType.values()) {
                if (value.getCode().equals(Integer.valueOf(code))) {
                    return value;
                }
            }
            throw new IllegalArgumentException("code非法");
        }
    }
    ```

  - 注册上述的`StringToItemTypeConverter`，在**web-admin模块**创建`com.atguigu.lease.web.admin.custom.config.WebMvcConfiguration`，内容如下：

    ```java
    @Configuration
    public class WebMvcConfiguration implements WebMvcConfigurer {
    
        @Autowired
        private StringToItemTypeConverter stringToItemTypeConverter;
    
        @Override
        public void addFormatters(FormatterRegistry registry) {
            registry.addConverter(this.stringToItemTypeConverter);
        }
    }
    ```

  但是我们有很多的枚举类型都需要考虑类型转换这个问题，按照上述思路，我们需要为每个枚举类型都定义一个Converter，并且每个Converter的转换逻辑都完全相同，针对这种情况，我们使用[`ConverterFactory`](https://docs.spring.io/spring-framework/reference/core/validation/convert.html#core-convert-ConverterFactory-SPI)接口更为合适，这个接口可以将同一个转换逻辑应用到一个接口的所有实现类，因此我们可以定义一个`BaseEnum`接口，然后另所有的枚举类都实现该接口，然后就可以自定义`ConverterFactory`，集中编写各枚举类的转换逻辑了。具体实现如下：

  - 在**model模块**定义`com.atguigu.lease.model.enums.BaseEnum`接口

    ```java
    public interface BaseEnum {
        Integer getCode();
        String getName();
    }
    ```

  - 令所有`com.atguigu.lease.model.enums`包下的枚举类都实现`BaseEnun`接口

  - 在**web-admin模块**自定义`com.atguigu.lease.web.admin.custom.converter.StringToBaseEnumConverterFactory`

    ```java
    @Component
    public class StringToBaseEnumConverterFactory implements ConverterFactory<String, BaseEnum> {
        @Override
        public <T extends BaseEnum> Converter<String, T> getConverter(Class<T> targetType) {
            return new Converter<String, T>() {
                @Override
                public T convert(String source) {
    
                    for (T enumConstant : targetType.getEnumConstants()) {
                        if (enumConstant.getCode().equals(Integer.valueOf(source))) {
                            return enumConstant;
                        }
                    }
                    throw new IllegalArgumentException("非法的枚举值:" + source);
                }
            };
        }
    }
    ```

  - 注册上述的`ConverterFactory`，在**web-admin模块**创建`com.atguigu.lease.web.admin.custom.config.WebMvcConfiguration`，内容如下：

    ```java
    @Configuration
    public class WebMvcConfiguration implements WebMvcConfigurer {
    
        @Autowired
        private StringToBaseEnumConverterFactory stringToBaseEnumConverterFactory;
    
        @Override
        public void addFormatters(FormatterRegistry registry) {
            registry.addConverterFactory(this.stringToBaseEnumConverterFactory);
        }
    }
    ```
    
    **注意：**
    
    最终采用的是`ConverterFactory`方案，因此`StringToItemTypeConverter`相关代码可以直接删除。

- **TypeHandler枚举类型转换**

  Mybatis预置的`TypeHandler`可以处理常用的数据类型转换，例如`String`、`Integer`、`Date`等等，其中也包含枚举类型，但是枚举类型的默认转换规则是枚举对象实例（ItemType.APARTMENT）和实例名称（"APARTMENT"）相互映射。若想实现`code`属性到枚举对象实例的相互映射，需要自定义`TypeHandler`。

  不过MybatisPlus提供了一个[通用的处理枚举类型的TypeHandler](https://baomidou.com/pages/8390a4/)。其使用十分简单，只需在`ItemType`枚举类的`code`属性上增加一个注解`@EnumValue`，Mybatis-Plus便可完成从`ItemType`对象到`code`属性之间的相互映射，具体配置如下。

  ```java
  public enum ItemType {
  
      APARTMENT(1, "公寓"),
      ROOM(2, "房间");
  
      @EnumValue
      private Integer code;
      private String name;
  
      ItemType(Integer code, String name) {
          this.code = code;
          this.name = name;
      }
  }
  ```

- **HTTPMessageConverter枚举类型转换**

  `HttpMessageConverter`依赖于Json序列化框架（默认使用Jackson）。其对枚举类型的默认处理规则也是枚举对象实例（ItemType.APARTMENT）和实例名称（"APARTMENT"）相互映射。不过其提供了一个注解`@JsonValue`，同样只需在`ItemType`枚举类的`code`属性上增加一个注解`@JsonValue`，Jackson便可完成从`ItemType`对象到`code`属性之间的互相映射。具体配置如下，详细信息可参考Jackson[官方文档](https://fasterxml.github.io/jackson-annotations/javadoc/2.8/com/fasterxml/jackson/annotation/JsonValue.html)。

  ```java
  @Getter
  public enum ItemType {
  
      APARTMENT(1, "公寓"),
      ROOM(2, "房间");
  
      @EnumValue
    	@JsonValue
      private Integer code;
      private String name;
  
      ItemType(Integer code, String name) {
          this.code = code;
          this.name = name;
      }
  }
  ```

##### 2.保存或更新标签信息

在`LabelController`中增加如下内容

```java
@Operation(summary = "保存或更新标签信息")
@PostMapping("saveOrUpdate")
public Result saveOrUpdateFacility(@RequestBody LabelInfo labelInfo) {
    service.saveOrUpdate(labelInfo);
    return Result.ok();
}
```

##### 3. 根据ID删除标签

在`LabelController`中增加如下内容

```java
@Operation(summary = "根据id删除标签信息")
@DeleteMapping("deleteById")
public Result deleteLabelById(@RequestParam Long id) {
    service.removeById(id);
    return Result.ok();
}
```

#### 7.2.2.4 配套管理

**配套管理**共有三个接口，分别是**[根据类型]查询配套列表**、**保存或更新配套信息**和**根据ID删除配套**，具体实现如下。

在`FacilityController`中增加如下内容

```java
@Tag(name = "配套管理")
@RestController
@RequestMapping("/admin/facility")
public class FacilityController {

    @Autowired
    private FacilityInfoService service;

    @Operation(summary = "[根据类型]查询配套信息列表")
    @GetMapping("list")
    public Result<List<FacilityInfo>> listFacility(@RequestParam(required = false) ItemType type) {
        LambdaQueryWrapper<FacilityInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(type != null, FacilityInfo::getType, type);
        List<FacilityInfo> list = service.list(queryWrapper);
        return Result.ok(list);
    }

    @Operation(summary = "新增或修改配套信息")
    @PostMapping("saveOrUpdate")
    public Result saveOrUpdate(@RequestBody FacilityInfo facilityInfo) {
        service.saveOrUpdate(facilityInfo);
        return Result.ok();
    }

    @Operation(summary = "根据id删除配套信息")
    @DeleteMapping("deleteById")
    public Result removeFacilityById(@RequestParam Long id) {
        service.removeById(id);
        return Result.ok();
    }

}
```

#### 7.2.2.5 基本属性管理

房间基本属性管理共有五个接口，分别是**保存或更新属性名称**、**保存或更新属性值**、**查询全部属性名称和属性值列表**、**根据ID删除属性名称**、**根据ID删除属性值**。下面逐一是实现。

首先在`AttrController`中注入`AttrKeyService`和`AttrValueService`，如下：

```java
@Tag(name = "房间属性管理")
@RestController
@RequestMapping("/admin/attr")
public class AttrController {

    @Autowired
    private AttrKeyService attrKeyService;

    @Autowired
    private AttrValueService attrValueService;
}
```

##### 1. 保存或更新属性名称

在`AttrController`增加如下内容

```java
@Operation(summary = "保存或更新属性名称")
@PostMapping("key/saveOrUpdate")
public Result saveOrUpdateAttrKey(@RequestBody AttrKey attrKey) {

    attrKeyService.saveOrUpdate(attrKey);
    return Result.ok();
}
```

##### 2. 保存或更新属性值

在`AttrController`中增加如下内容

```java
@Operation(summary = "保存或更新属性值")
@PostMapping("value/saveOrUpdate")
public Result saveOrUpdateAttrValue(@RequestBody AttrValue attrValue) {

    attrValueService.saveOrUpdate(attrValue);
    return Result.ok();
}
```

##### 3. 查询全部属性名称和属性值列表

- **查看响应的数据结构**

  查看**web-admin模块**下的`com.atguigu.lease.web.admin.vo.attr.AttrKeyVo`，内容如下：

  ```java
  @Data
  public class AttrKeyVo extends AttrKey {
  
      @Schema(description = "属性值列表")
      private List<AttrValue> attrValueList;
  }
  ```

- **编写Controller层逻辑**

  在`AttrController`中添加如下内容

  ```java
  @Operation(summary = "查询全部属性名称和属性值列表")
  @GetMapping("list")
  public Result<List<AttrKeyVo>> listAttrInfo() {
  
      List<AttrKeyVo> list = attrKeyService.listAttrInfo();
      return Result.ok(list);
  }
  ```

- **编写Service层逻辑**

  在`AttrKeyService`中增加如下内容

  ```java
  List<AttrKeyVo> listAttrInfo();
  ```

  在`AttrKeyServiceImpl`中增加如下内容

  ```java
  @Autowired
  private AttrKeyMapper mapper;
  
  @Override
  public List<AttrKeyVo> listAttrInfo() {
      return mapper.listAttrInfo();
  }
  ```

- **编写Mapper层逻辑**

  在`AttrKeyMapper`中增加如下内容

  ```java
  List<AttrKeyVo> listAttrInfo();
  ```

  对应的在`AttrKeyMapper.xml`中增加如下内容

  ```xml
  <resultMap id="BaseResultMap" type="com.atguigu.lease.web.admin.vo.attr.AttrKeyVo">
      <id property="id" column="id"/>
      <result property="name" column="key_name"/>
      <collection property="attrValueList" ofType="com.atguigu.lease.model.entity.AttrValue">
          <id column="value_id" property="id"/>
          <result column="value_name" property="name"/>
          <result column="key_id" property="attrKeyId"/>
      </collection>
  </resultMap>
  <select id="listAttrInfo" resultMap="BaseResultMap">
      select k.id,
             k.name        key_name,
             v.id          value_id,
             v.name        value_name,
             v.attr_key_id key_id
      from attr_key k
               left join attr_value v on k.id = v.attr_key_id and v.is_deleted = 0
      where k.is_deleted = 0
  </select>
  ```

##### 4. 根据ID删除属性名称

在`AttrController`中增加如下内容，注意删除属性名称时，会一并删除其下的所有属性值

```java
@Operation(summary = "根据id删除属性名称")
@DeleteMapping("key/deleteById")
public Result removeAttrKeyById(@RequestParam Long attrKeyId) {

    //删除attrKey
    attrKeyService.removeById(attrKeyId);
    //删除attrValue
    LambdaQueryWrapper<AttrValue> queryWrapper = new LambdaQueryWrapper<>();
    queryWrapper.eq(AttrValue::getAttrKeyId, attrKeyId);
    attrValueService.remove(queryWrapper);
    return Result.ok();
}
```

##### 5. 根据ID删除属性值

在`AttrController`中增加如下内容

```java
@Operation(summary = "根据id删除属性值")
@DeleteMapping("value/deleteById")
public Result removeAttrValueById(@RequestParam Long id) {

    attrValueService.removeById(id);
    return Result.ok();
}
```

#### 7.2.2.6 公寓杂费管理

房间基本属性管理共有五个接口，分别是**保存或更新杂费名称**、**保存或更新杂费值**、**查询全部杂费名称和杂费值列表**、**根据ID删除杂费名称**、**根据ID删除杂费值**。下面逐一实现

首先在`FeeController`中注入`FeeKeyService`和`FeeValueService`，如下

```java
@Tag(name = "房间杂费管理")
@RestController
@RequestMapping("/admin/fee")
public class FeeController {

    @Autowired
    private FeeKeyService feeKeyService;

    @Autowired
    private FeeValueService feeValueService;
}
```

##### 1. 保存或更新杂费名称

在`FeeController`中增加如下内容

```java
@Operation(summary = "保存或更新杂费名称")
@PostMapping("key/saveOrUpdate")
public Result saveOrUpdateFeeKey(@RequestBody FeeKey feeKey) {
    feeKeyService.saveOrUpdate(feeKey);
    return Result.ok();
}
```

##### 2. 保存或更新杂费值

在`FeeController`中增加如下内容

```java
@Operation(summary = "保存或更新杂费值")
@PostMapping("value/saveOrUpdate")
public Result saveOrUpdateFeeValue(@RequestBody FeeValue feeValue) {
    feeValueService.saveOrUpdate(feeValue);
    return Result.ok();
}
```

##### 3. 查询全部杂费名称和杂费值列表

- **查看响应的数据结构**

  查看**web-admin模块**下创的`com.atguigu.lease.web.admin.vo.fee.FeeKeyVo`，内容如下

  ```java
  @Data
  public class FeeKeyVo extends FeeKey {
  
      @Schema(description = "杂费value列表")
      private List<FeeValue> feeValueList;
  }
  ```

- **编写Controller层逻辑**

  在`FeeController`中增加如下内容

  ```java
  @Operation(summary = "查询全部杂费名称和杂费值列表")
  @GetMapping("list")
  public Result<List<FeeKeyVo>> feeInfoList() {
  
      List<FeeKeyVo> list = feeKeyService.listFeeInfo();
      return Result.ok(list);
  }
  ```

- **编写Service层逻辑**

  - 在`FeeKeyService`中增加如下内容

    ```java
    List<FeeKeyVo> listFeeInfo();
    ```

  - 在`FeeKeyServiceImpl`中增加如下内容

    ```java
    @Autowired
    private FeeKeyMapper mapper;
    
    @Override
    public List<FeeKeyVo> listFeeInfo() {
    
        return mapper.listFeeInfo();
    }
    ```

- **编写Mapper层逻辑**

  - 在`FeeKeyMapper`中增加如下内容

    ```java
    List<FeeKeyVo> listFeeInfo();
    ```

  - 在`FeeKeyMapper.xml`中增加如下内容

    ```java
    <resultMap id="FeeInfoList" type="com.atguigu.lease.web.admin.vo.fee.FeeKeyVo">
        <id property="id" column="id"/>
        <result property="name" column="key_name"/>
        <collection property="feeValueList" ofType="com.atguigu.lease.model.entity.FeeValue">
            <id column="value_id" property="id"/>
            <result column="value_name" property="name"/>
            <result column="value_unit" property="unit"/>
            <result column="key_id" property="feeKeyId"/>
        </collection>
    </resultMap>
    
    <select id="listFeeInfo" resultMap="FeeInfoList">
        select k.id,
               k.name       key_name,
               v.id         value_id,
               v.name       value_name,
               v.unit       value_unit,
               v.fee_key_id key_id
        from fee_key k
                 left join fee_value v on k.id = v.fee_key_id and v.is_deleted = 0
        where k.is_deleted = 0
    </select>
    ```

##### 4. 根据ID删除杂费名称

在`FeeController`中增加如下内容

```java
@Operation(summary = "根据id删除杂费名称")
@DeleteMapping("key/deleteById")
public Result deleteFeeKeyById(@RequestParam Long feeKeyId) {

    //删除杂费名称
    feeKeyService.removeById(feeKeyId);
    //删除杂费名称下的杂费值
    LambdaQueryWrapper<FeeValue> queryWrapper = new LambdaQueryWrapper<>();
    queryWrapper.eq(FeeValue::getFeeKeyId, feeKeyId);
    feeValueService.remove(queryWrapper);
    return Result.ok();
}
```

##### 5. 根据ID删除杂费值

在`FeeController`中增加如下内容

```java
@Operation(summary = "根据id删除杂费值")
@DeleteMapping("value/deleteById")
public Result deleteFeeValueById(@RequestParam Long id) {
    feeValueService.removeById(id);
    return Result.ok();
}
```

#### 7.2.2.7 地区信息管理

地区信息管理共有三个接口，分别是**查询省份信息列表**，**根据省份ID查询城市信息列表**和**根据城市ID查询区县信息列表**，具体实现如下

在`RegionInfoController`中增加如下内容

```java
@Tag(name = "地区信息管理")
@RestController
@RequestMapping("/admin/region")
public class RegionInfoController {

    @Autowired
    private ProvinceInfoService provinceInfoService;

    @Autowired
    private CityInfoService cityInfoService;

    @Autowired
    private DistrictInfoService districtInfoService;

    @Operation(summary = "查询省份信息列表")
    @GetMapping("province/list")
    public Result<List<ProvinceInfo>> listProvince() {
        List<ProvinceInfo> list = provinceInfoService.list();
        return Result.ok(list);
    }

    @Operation(summary = "根据省份id查询城市信息列表")
    @GetMapping("city/listByProvinceId")
    public Result<List<CityInfo>> listCityInfoByProvinceId(@RequestParam Long id) {
        LambdaQueryWrapper<CityInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CityInfo::getProvinceId, id);
        List<CityInfo> list = cityInfoService.list(queryWrapper);
        return Result.ok(list);
    }

    @GetMapping("district/listByCityId")
    @Operation(summary = "根据城市id查询区县信息")
    public Result<List<DistrictInfo>> listDistrictInfoByCityId(@RequestParam Long id) {
        LambdaQueryWrapper<DistrictInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(DistrictInfo::getCityId, id);
        List<DistrictInfo> list = districtInfoService.list(queryWrapper);
        return Result.ok(list);
    }
}
```

#### 7.2.2.8 图片上传管理

由于公寓、房间等实体均包含图片信息，所以在新增或修改公寓、房间信息时，需要上传图片，因此我们需要实现一个上传图片的接口。

**1. 图片上传流程**

下图展示了新增房间或公寓时，上传图片的流程。

<img src="images/图片存储方案.drawio.svg" style="zoom: 40%;" />

可以看出图片上传接口接收的是图片文件，返回的Minio对象的URL。

**2. 图片上传接口开发**

下面为该接口的具体实现

- **配置Minio Client**

  - 引入Minio Maven依赖

    在**common模块**的`pom.xml`文件增加如下内容：

    ```xml
    <dependency>
        <groupId>io.minio</groupId>
        <artifactId>minio</artifactId>
    </dependency>
    ```

  - 配置Minio相关参数

    在`application.yml`中配置Minio的`endpoint`、`accessKey`、`secretKey`、`bucketName`等参数

    ```yml
    minio:
      endpoint: http://<hostname>:<port>
      access-key: <access-key>
      secret-key: <secret-key>
      bucket-name: <bucket-name>
    ```

    **注意**：上述`<hostname>`、`<port>`等信息需根据实际情况进行修改。

  - 在**common模块**中创建`com.atguigu.lease.common.minio.MinioProperties`，内容如下

    ```java
    @ConfigurationProperties(prefix = "minio")
    @Data
    public class MinioProperties {
    
        private String endpoint;
    
        private String accessKey;
    
        private String secretKey;
        
        private String bucketName;
    }
    ```

  - 在**common模块**中创建`com.atguigu.lease.common.minio.MinioConfiguration`，内容如下
  
    ```java
    @Configuration
    @EnableConfigurationProperties(MinioProperties.class)
    public class MinioConfiguration {
    
        @Autowired
        private MinioProperties properties;
    
        @Bean
        public MinioClient minioClient() {
            return MinioClient.builder().endpoint(properties.getEndpoint()).credentials(properties.getAccessKey(), properties.getSecretKey()).build();
        }
    }
    ```
  
- **开发图片上传接口**

  - 编写Controller层逻辑

    在`FileUploadController`中增加如下内容

    ```java
    @Tag(name = "文件管理")
    @RequestMapping("/admin/file")
    @RestController
    public class FileUploadController {
    
        @Autowired
        private FileService service;
    
        @Operation(summary = "上传文件")
        @PostMapping("upload")
        public Result<String> upload(@RequestParam MultipartFile file) {
    
            String url = service.upload(file);
            return Result.ok(url);
        }
    }
    ```

    **说明：**`MultipartFile`是Spring框架中用于处理文件上传的类，它包含了上传文件的信息（如文件名、文件内容等）。

  - 编写Service层逻辑

    - 在`FileService`中增加如下内容
  
      ```java
      String upload(MultipartFile file);
      ```

    - 在`FileServiceImpl`中增加如下内容
  
      ```java
      @Autowired
      private MinioProperties properties;
      
      @Autowired
      private MinioClient client;
      
      @Override
      public String upload(MultipartFile file) {
      
          try {
              boolean bucketExists = client.bucketExists(BucketExistsArgs.builder().bucket(properties.getBucketName()).build());
              if (!bucketExists) {
                  client.makeBucket(MakeBucketArgs.builder().bucket(properties.getBucketName()).build());
                  client.setBucketPolicy(SetBucketPolicyArgs.builder().bucket(properties.getBucketName()).config(createBucketPolicyConfig(properties.getBucketName())).build());
              }
      
              String filename = new SimpleDateFormat("yyyyMMdd").format(new Date()) + "/" + UUID.randomUUID() + "-" + file.getOriginalFilename();
              client.putObject(PutObjectArgs.builder().
                      bucket(properties.getBucketName()).
                      object(filename).
                      stream(file.getInputStream(), file.getSize(), -1).
                      contentType(file.getContentType()).build());
      
              return String.join("/", properties.getEndpoint(), properties.getBucketName(), filename);
      
          } catch (Exception e) {
              e.printStackTrace();
          }
          return null;
      }
      
      private String createBucketPolicyConfig(String bucketName) {
      
          return """
                  {
                    "Statement" : [ {
                      "Action" : "s3:GetObject",
                      "Effect" : "Allow",
                      "Principal" : "*",
                      "Resource" : "arn:aws:s3:::%s/*"
                    } ],
                    "Version" : "2012-10-17"
                  }
                  """.formatted(bucketName);
      }
      ```
      
      **注意**：
      
      上述`createBucketPolicyConfig`方法的作用是生成用于描述指定bucket访问权限的JSON字符串。最终生成的字符串格式如下，其表示，允许(`Allow`)所有人(`*`)获取(`s3:GetObject`)指定桶(`<bucket-name>`)的内容。
      
      ```json
      {
        "Statement" : [ {
          "Action" : "s3:GetObject",
          "Effect" : "Allow",
          "Principal" : "*",
          "Resource" : "arn:aws:s3:::<bucket-name>/*"
        } ],
        "Version" : "2012-10-17"
      }
      ```
      
      由于公寓、房间的图片为公开信息，所以将其设置为所有人可访问。
      
    - **异常处理**
    
      - **问题说明**
    
        上述代码只是对`MinioClient`方法抛出的各种异常进行了捕获，然后打印了异常信息，目前这种处理逻辑，无论Minio是否发生异常，前端在上传文件时，总是会受到成功的响应信息。可按照以下步骤进行操作，查看具体现象
    
        关闭虚拟机中的Minio服务
    
        ```bash
        systemctl stop minio
        ```
    
        启动项目，并上传文件，观察接收的响应信息
    
      - **问题解决思路**
    
        为保证前端能够接收到正常的错误提示信息，应该将Service方法的异常抛出到Controller方法中，然后在Controller方法中对异常进行捕获并处理。具体操作如下
    
        **Service层代码**
    
        ```java
        @Override
        public String upload(MultipartFile file) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException{
        
            boolean bucketExists = minioClient.bucketExists(
                    BucketExistsArgs.builder()
                            .bucket(properties.getBucketName())
                            .build());
            if (!bucketExists) {
                minioClient.makeBucket(
                        MakeBucketArgs.builder()
                                .bucket(properties.getBucketName())
                                .build());
                minioClient.setBucketPolicy(
                        SetBucketPolicyArgs.builder()
                                .bucket(properties.getBucketName())
                                .config(createBucketPolicyConfig(properties.getBucketName()))
                                .build());
            }
            String filename = new SimpleDateFormat("yyyyMMdd").format(new Date()) +
                    "/" + UUID.randomUUID() + "-" + file.getOriginalFilename();
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(properties.getBucketName())
                            .stream(file.getInputStream(), file.getSize(), -1)
                            .object(filename)
                            .contentType(file.getContentType())
                            .build());
        
            return String.join("/",properties.getEndpoint(),properties.getBucketName(),filename);
        }
        ```
    
        **Controller层代码**
    
        ```java
        public Result<String> upload(@RequestParam MultipartFile file) {
            try {
                String url = service.upload(file);
                return Result.ok(url);
            } catch (Exception e) {
                e.printStackTrace();
                return Result.fail();
            }
        }
        ```
    
      - **全局异常处理**
    
        按照上述写法，所有的Controller层方法均需要增加`try-catch`逻辑，使用Spring MVC提供的**全局异常处理**功能，可以将所有处理异常的逻辑集中起来，进而统一处理所有异常，使代码更容易维护。
    
        具体用法如下，详细信息可参考[官方文档](https://docs.spring.io/spring-framework/reference/web/webmvc/mvc-controller/ann-exceptionhandler.html)：
    
        在**common模块**中创建`com.atguigu.lease.common.exception.GlobalExceptionHandler`类，内容如下
    
        ```java
        @ControllerAdvice
        public class GlobalExceptionHandler {
        
            @ExceptionHandler(Exception.class)
            @ResponseBody
            public Result error(Exception e){
                e.printStackTrace();
                return Result.fail();
            }
        }
        ```
    
        上述代码中的关键注解的作用如下
    
        `@ControllerAdvice`用于声明处理全局Controller方法异常的类
    
        `@ExceptionHandler`用于声明处理异常的方法，`value`属性用于声明该方法处理的异常类型
    
        `@ResponseBody`表示将方法的返回值作为HTTP的响应体
    
        **注意：**
    
        全局异常处理功能由SpringMVC提供，因此需要在**common模块**的`pom.xml`中引入如下依赖
    
        ```xml
        <!--spring-web-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        ```
    
      - **修改Controller层代码**
    
        由于前文的`GlobalExceptionHandler`会处理所有Controller方法抛出的异常，因此Controller层就无序关注异常的处理逻辑了，因此Controller层代码可做出如下调整。
    
        ```java
        public Result<String> upload(@RequestParam MultipartFile file) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
            String url = service.upload(file);
          return Result.ok(url);
        }
      ```

#### 7.2.2.9 公寓管理

公寓管理共有六个接口，下面逐一实现。

首先在`ApartmentController`中注入`ApartmentInfoService`，如下

```java
@Tag(name = "公寓信息管理")
@RestController
@RequestMapping("/admin/apartment")
public class ApartmentController {

    @Autowired
    private ApartmentInfoService service;
}
```

##### 1. 保存或更新公寓信息

- **查看请求的数据结构**

  查看**web-admin模块**中的`com.atguigu.lease.web.admin.vo.apartment.ApartmentSubmitVo`类，内容如下：

  ```java
  @Schema(description = "公寓信息")
  @Data
  public class ApartmentSubmitVo extends ApartmentInfo {
  
      @Schema(description="公寓配套id")
      private List<Long> facilityInfoIds;
  
      @Schema(description="公寓标签id")
      private List<Long> labelIds;
  
      @Schema(description="公寓杂费值id")
      private List<Long> feeValueIds;
  
      @Schema(description="公寓图片id")
      private List<GraphVo> graphVoList;
  
  }
  ```

- **编写Controller层逻辑**

  在`ApartmentController`中增加如下内容

  ```java
  @Operation(summary = "保存或更新公寓信息")
  @PostMapping("saveOrUpdate")
  public Result saveOrUpdate(@RequestBody ApartmentSubmitVo apartmentSubmitVo) {
      service.saveOrUpdateApartment(apartmentSubmitVo);
      return Result.ok();
  }
  ```

- **编写Service层逻辑**

  - 在`ApartmentInfoService`中增加如下内容

    ```java
    void saveOrUpdateApartment(ApartmentSubmitVo apartmentSubmitVo);
    ```

  - 在`ApartmentInfoServiceImpl`中增加如下内容

    **注意**：所需`Service`和`Mapper`的注入语句省略未写。

    ```java
    @Override
    public void saveOrUpdateApartment(ApartmentSubmitVo apartmentSubmitVo) {
        boolean isUpdate = apartmentSubmitVo.getId()!=null;
        super.saveOrUpdate(apartmentSubmitVo);
    
        if (isUpdate){
            //1.删除图片列表
            LambdaQueryWrapper<GraphInfo> graphQueryWrapper = new LambdaQueryWrapper<>();
            graphQueryWrapper.eq(GraphInfo::getItemType, ItemType.APARTMENT);
            graphQueryWrapper.eq(GraphInfo::getItemId,apartmentSubmitVo.getId());
            graphInfoService.remove(graphQueryWrapper);
    
            //2.删除配套列表
            LambdaQueryWrapper<ApartmentFacility> facilityQueryWrapper = new LambdaQueryWrapper<>();
            facilityQueryWrapper.eq(ApartmentFacility::getApartmentId,apartmentSubmitVo.getId());
            apartmentFacilityService.remove(facilityQueryWrapper);
    
            //3.删除标签列表
            LambdaQueryWrapper<ApartmentLabel> labelQueryWrapper = new LambdaQueryWrapper<>();
            labelQueryWrapper.eq(ApartmentLabel::getApartmentId,apartmentSubmitVo.getId());
            apartmentLabelService.remove(labelQueryWrapper);
    
            //4.删除杂费列表
            LambdaQueryWrapper<ApartmentFeeValue> feeQueryWrapper = new LambdaQueryWrapper<>();
            feeQueryWrapper.eq(ApartmentFeeValue::getApartmentId,apartmentSubmitVo.getId());
            apartmentFeeValueService.remove(feeQueryWrapper);
    
        }
    
        //1.插入图片列表
        List<GraphVo> graphVoList = apartmentSubmitVo.getGraphVoList();
        if (!CollectionUtils.isEmpty(graphVoList)){
            ArrayList<GraphInfo> graphInfoList = new ArrayList<>();
            for (GraphVo graphVo : graphVoList) {
                GraphInfo graphInfo = new GraphInfo();
                graphInfo.setItemType(ItemType.APARTMENT);
                graphInfo.setItemId(apartmentSubmitVo.getId());
                graphInfo.setName(graphVo.getName());
                graphInfo.setUrl(graphVo.getUrl());
                graphInfoList.add(graphInfo);
            }
            graphInfoService.saveBatch(graphInfoList);
        }
    
    
        //2.插入配套列表
        List<Long> facilityInfoIdList = apartmentSubmitVo.getFacilityInfoIds();
        if (!CollectionUtils.isEmpty(facilityInfoIdList)){
            ArrayList<ApartmentFacility> facilityList = new ArrayList<>();
            for (Long facilityId : facilityInfoIdList) {
                ApartmentFacility apartmentFacility = new ApartmentFacility();
                apartmentFacility.setApartmentId(apartmentSubmitVo.getId());
                apartmentFacility.setFacilityId(facilityId);
                facilityList.add(apartmentFacility);
            }
            apartmentFacilityService.saveBatch(facilityList);
        }
    
    
        //3.插入标签列表
        List<Long> labelIds = apartmentSubmitVo.getLabelIds();
        if (!CollectionUtils.isEmpty(labelIds)) {
            List<ApartmentLabel> apartmentLabelList = new ArrayList<>();
            for (Long labelId : labelIds) {
                ApartmentLabel apartmentLabel = new ApartmentLabel();
                apartmentLabel.setApartmentId(apartmentSubmitVo.getId());
                apartmentLabel.setLabelId(labelId);
                apartmentLabelList.add(apartmentLabel);
            }
            apartmentLabelService.saveBatch(apartmentLabelList);
        }
    
    
        //4.插入杂费列表
        List<Long> feeValueIds = apartmentSubmitVo.getFeeValueIds();
        if (!CollectionUtils.isEmpty(feeValueIds)) {
            ArrayList<ApartmentFeeValue> apartmentFeeValueList = new ArrayList<>();
            for (Long feeValueId : feeValueIds) {
                ApartmentFeeValue apartmentFeeValue = new ApartmentFeeValue();
                apartmentFeeValue.setApartmentId(apartmentSubmitVo.getId());
                apartmentFeeValue.setFeeValueId(feeValueId);
                apartmentFeeValueList.add(apartmentFeeValue);
            }
            apartmentFeeValueService.saveBatch(apartmentFeeValueList);
        }
    }
    ```
    

##### 2. 根据条件分页查询公寓列表

- **查看请求和响应的数据结构**

  - **请求数据结构**

    - `current`和`size`为分页相关参数，分别表示**当前所处页面**和**每个页面的记录数**。

    - `ApartmentQueryVo`为公寓的查询条件，详细结构如下：

      ```java
      @Data
      @Schema(description = "公寓查询实体")
      public class ApartmentQueryVo {
      
          @Schema(description = "省份id")
          private Long provinceId;
      
          @Schema(description = "城市id")
          private Long cityId;
      
          @Schema(description = "区域id")
          private Long districtId;
      }
      ```

  - **响应数据结构**

    单个公寓信息记录可查看`com.atguigu.lease.web.admin.vo.apartment.ApartmentItemVo`，内容如下：

    ```java
    @Data
    @Schema(description = "后台管理系统公寓列表实体")
    public class ApartmentItemVo extends ApartmentInfo {
    
        @Schema(description = "房间总数")
        private Long totalRoomCount;
    
        @Schema(description = "空闲房间数")
        private Long freeRoomCount;
    
    }
    ```

- **配置Mybatis-Plus分页插件**

  在**common模块**中的`com.atguigu.lease.common.mybatisplus.MybatisPlusConfiguration`中增加如下内容：

  ```java
  @Bean
  public MybatisPlusInterceptor mybatisPlusInterceptor() {
      MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
      interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
      return interceptor;
  }
  ```
  
- **接口实现**

  - **编写Controller层逻辑**

    在`ApartmentController`中增加如下内容：

    ```java
    @Operation(summary = "根据条件分页查询公寓列表")
    @GetMapping("pageItem")
    public Result<IPage<ApartmentItemVo>> pageItem(@RequestParam long current, @RequestParam long size, ApartmentQueryVo queryVo) {
    
        IPage<ApartmentItemVo> page = new Page<>(current, size);
        IPage<ApartmentItemVo> list = service.pageApartmentItemByQuery(page, queryVo);
        return Result.ok(list);
    }
    ```

  - **编写Service层逻辑**

    - 在`ApartmentInfoService`中增加如下内容

      ```java
      IPage<ApartmentItemVo> pageApartmentItemByQuery(IPage<ApartmentItemVo> page, ApartmentQueryVo queryVo);
      ```

    - 在`ApartmentInfoServiceImpl`中增加如下内容

      ```java
      @Override
      public IPage<ApartmentItemVo> pageApartmentItemByQuery(IPage<ApartmentItemVo> page, ApartmentQueryVo queryVo) {
      
          return apartmentInfoMapper.pageApartmentItemByQuery(page, queryVo);
      }
      ```

  - **编写Mapper层逻辑**

    - 在`ApartmentInfoMapper`中增加如下内容

      ```java
      IPage<ApartmentItemVo> pageApartmentItemByQuery(IPage<ApartmentItemVo> page, ApartmentQueryVo queryVo);
      ```
      
    - 在`ApartmentInfoMapper.xml`中增加如下内容
    
      ```xml
      <select id="pageItem" resultType="com.atguigu.lease.web.admin.vo.apartment.ApartmentItemVo">
          select ai.id,
                 ai.name,
                 ai.introduction,
                 ai.district_id,
                 ai.district_name,
                 ai.city_id,
                 ai.city_name,
                 ai.province_id,
                 ai.province_name,
                 ai.address_detail,
                 ai.latitude,
                 ai.longitude,
                 ai.phone,
                 ai.is_release,
                 ifnull(tc.cnt,0) total_room_count,
                 ifnull(tc.cnt,0) - ifnull(cc.cnt,0) free_room_count
          from (select id,
                       name,
                       introduction,
                       district_id,
                       district_name,
                       city_id,
                       city_name,
                       province_id,
                       province_name,
                       address_detail,
                       latitude,
                       longitude,
                       phone,
                       is_release
                from apartment_info
                  <where>
                      is_deleted=0
                      <if test="queryVo.provinceId != null">
                          and province_id=#{queryVo.provinceId}
                      </if>
                      <if test="queryVo.cityId != null">
                          and city_id=#{queryVo.cityId}
                      </if>
                      <if test="queryVo.districtId != null">
                          and district_id=#{queryVo.districtId}
                      </if>
                  </where>
                ) ai
                   left join
               (select apartment_id,
                       count(*) cnt
                from room_info
                where is_deleted = 0
                  and is_release = 1
                group by apartment_id) tc
               on ai.id = tc.apartment_id
                   left join
               (select apartment_id,
                       count(*) cnt
                from lease_agreement
                where is_deleted = 0
                  and status in (2, 5)
                group by apartment_id) cc
               on ai.id = cc.apartment_id
      
      </select>
      ```
      
  
  **注意：**
  
  默认情况下Knife4j为该接口生成的接口文档如下图所示，其中的queryVo参数不方便调试
  
  <img src="images/flat-param-false.png" style="zoom:50%; zoom:50%;border: 1px solid #000;" />
  
  可在application.yml文件中增加如下配置，将queryVo做打平处理
  
  ```yml
  springdoc:
    default-flat-param-object: true
  ```
  
  将`spring.default-flat-param-object`参数设置为`true`后，效果如下。
  
  <img src="images/flat-param-true.png" style="zoom:50%; zoom:50%;border: 1px solid #000;" />

##### 3. 根据ID获取公寓详细信息

- **查看响应数据结构**

  查看**web-admin**下的`com.atguigu.lease.web.admin.vo.apartment.ApartmentDetailVo`，内容如下

  ```java
  @Schema(description = "公寓信息")
  @Data
  public class ApartmentDetailVo extends ApartmentInfo {
  
      @Schema(description = "图片列表")
      private List<GraphVo> graphVoList;
  
      @Schema(description = "标签列表")
      private List<LabelInfo> labelInfoList;
  
      @Schema(description = "配套列表")
      private List<FacilityInfo> facilityInfoList;
  
      @Schema(description = "杂费列表")
      private List<FeeValueVo> feeValueVoList;
  }

- **编写Controller层逻辑**

  在`ApartmentController`中增加如下内容

  ```java
  @Operation(summary = "根据ID获取公寓详细信息")
  @GetMapping("getDetailById")
  public Result<ApartmentDetailVo> getDetailById(@RequestParam Long id) {
  
      ApartmentDetailVo apartmentInfo = service.getApartmentDetailById(id);
      return Result.ok(apartmentInfo);
  }
  ```

- **编写Service层逻辑**

  - 在`ApartmentInfoService`中增加如下内容

    ```java
    ApartmentDetailVo getApartmentDetailById(Long id);
    ```

  - 在`ApartmentInfoServiceImpl`中增加如下内容

    ```java
    @Override
    public ApartmentDetailVo getApartmentDetailById(Long id) {
        //1.查询ApartmentInfo
        ApartmentInfo apartmentInfo = this.getById(id);
        if (apartmentInfo == null) {
            return null;
        }
    
        //2.查询GraphInfo
        List<GraphVo> graphVoList = graphInfoMapper.selectListByItemTypeAndId(ItemType.APARTMENT, id);
    
        //3.查询LabelInfo
        List<LabelInfo> labelInfoList = labelInfoMapper.selectListByApartmentId(id);
    
        //4.查询FacilityInfo
        List<FacilityInfo> facilityInfoList = facilityInfoMapper.selectListByApartmentId(id);
    
        //5.查询FeeValue
        List<FeeValueVo> feeValueVoList = feeValueMapper.selectListByApartmentId(id);
    
        ApartmentDetailVo adminApartmentDetailVo = new ApartmentDetailVo();
    
        BeanUtils.copyProperties(apartmentInfo, adminApartmentDetailVo);
        adminApartmentDetailVo.setGraphVoList(graphVoList);
        adminApartmentDetailVo.setLabelInfoList(labelInfoList);
        adminApartmentDetailVo.setFacilityInfoList(facilityInfoList);
        adminApartmentDetailVo.setFeeValueVoList(feeValueVoList);
    
        return adminApartmentDetailVo;
    }

- **编写Mapper层逻辑**

  - 编写公寓图片查询逻辑

    - 在`GraphInfoMapper`中增加如下内容

      ```java
      List<GraphVo> selectListByItemTypeAndId(ItemType itemType, Long itemId);
      ```

    - 在`GraphInfoMapper.xml`中增加如下内容

      ```java
      <select id="selectListByItemTypeAndId" resultType="com.atguigu.lease.web.admin.vo.graph.GraphVo">
          select
              name,
              url
          from graph_info
          where is_deleted=0
          and item_type=#{itemType}
          and item_id=#{itemId}
      </select>
      ```

  - 编写公寓标签查询逻辑

    - 在`LabelInfoMapper`中增加如下内容

      ```java
      List<LabelInfo> selectListByApartmentId(Long id);
      ```

    - 在`LabelInfoMapper.xml`中增加如下内容

      ```java
      <select id="selectListByApartmentId" resultType="com.atguigu.lease.model.entity.LabelInfo">
          select id,
                 type,
                 name
          from label_info
          where is_deleted = 0
            and id in
                (select label_id
                 from apartment_label
                 where is_deleted = 0
                   and apartment_id = #{id})
      </select>
      ```

  - 编写公寓配套查询逻辑

    - 在`FacilityInfoMapper`中增加如下内容

      ```java
      List<FacilityInfo> selectListByApartmentId(Long id);
      ```

    - 在`FacilityInfoMapper.xml`中增加如下内容

      ```java
      <select id="selectListByApartmentId" resultType="com.atguigu.lease.model.entity.FacilityInfo">
          select id,
                 type,
                 name,
                 icon
          from facility_info
          where is_deleted = 0
            and id in
                (select facility_id
                 from apartment_facility
                 where is_deleted = 0
                   and apartment_id = #{id})
      </select>
      ```

  - 编写公寓杂费查询逻辑

    - 在`FeeValueMapper`中增加如下内容

      ```java
      List<FeeValueVo> selectListByApartmentId(Long id);
      ```

    - 在`FeeValueMapper.xml`中增加如下内容

      ```java
      <select id="selectListByApartmentId" resultType="com.atguigu.lease.web.admin.vo.fee.FeeValueVo">
          SELECT fv.id,
                 fv.name,
                 fv.unit,
                 fv.fee_key_id,
                 fk.name AS fee_key_name
          FROM fee_value fv
                   JOIN fee_key fk ON fv.fee_key_id = fk.id
          WHERE fv.is_deleted = 0
            AND fk.is_deleted = 0
            and fv.id in (select fee_value_id
                          from apartment_fee_value
                          where is_deleted = 0
                            and apartment_id = #{id})
      </select>
      ```

##### 4. 根据ID删除公寓信息

- **编写Controller层逻辑**

  在`ApartmentController`中增加如下内容

  ```java
  @Operation(summary = "根据id删除公寓信息")
  @DeleteMapping("removeById")
  public Result removeById(@RequestParam Long id) {
  
      service.removeApartmentById(id);
      return Result.ok();
  }
  ```

- **编写Service层逻辑**

  - 在`ApartmentInfoService`中增加如下内容

    ```java
    void removeApartmentById(Long id);
    ```

  - 在`ApartmentInfoServiceImpl`中增加如下内容

    ```java
    @Override
    public void removeApartmentById(Long id) {
        
        super.removeById(id);
        
        //1.删除GraphInfo
        LambdaQueryWrapper<GraphInfo> graphQueryWrapper = new LambdaQueryWrapper<>();
        graphQueryWrapper.eq(GraphInfo::getItemType, ItemType.APARTMENT);
        graphQueryWrapper.eq(GraphInfo::getItemId, id);
        graphInfoService.remove(graphQueryWrapper);
    
        //2.删除ApartmentLabel
        LambdaQueryWrapper<ApartmentLabel> labelQueryWrapper = new LambdaQueryWrapper<>();
        labelQueryWrapper.eq(ApartmentLabel::getApartmentId, id);
        apartmentLabelService.remove(labelQueryWrapper);
    
        //3.删除ApartmentFacility
        LambdaQueryWrapper<ApartmentFacility> facilityQueryWrapper = new LambdaQueryWrapper<>();
        facilityQueryWrapper.eq(ApartmentFacility::getApartmentId, id);
        apartmentFacilityService.remove(facilityQueryWrapper);
    
        //4.删除ApartmentFeeValue
        LambdaQueryWrapper<ApartmentFeeValue> feeQueryWrapper = new LambdaQueryWrapper<>();
        feeQueryWrapper.eq(ApartmentFeeValue::getApartmentId, id);
        apartmentFeeValueService.remove(feeQueryWrapper);
    
    }
    ```
    
    **知识点**：
    
    由于公寓下会包含房间信息，因此在删除公寓时最好先判断一下该公寓下是否存在房间信息，若存在，则提醒用户先删除房间信息后再删除公寓信息，判断逻辑如下
    
    ```java
    LambdaQueryWrapper<RoomInfo> roomQueryWrapper = new LambdaQueryWrapper<>();
    roomQueryWrapper.eq(RoomInfo::getApartmentId, id);
    Long count = roomInfoMapper.selectCount(roomQueryWrapper);
    if (count > 0) {
        //直接为前端返回如下响应：先删除房间信息再删除公寓信息
    }
    ```
    
    想要直接为前端返回响应，可利用前边配置的全局异常处理功能（此处直接抛出异常，全局异常处理器捕获到异常后，便会直接为前端返回响应结果）。
    
    为灵活设置响应信息，可自定义异常类，如下
    
    在**common模块**创建`com.atguigu.lease.common.exception.LeaseException`类，内容如下：
    
    ```java
    @Data
    public class LeaseException extends RuntimeException {
    
        //异常状态码
        private Integer code;
        /**
         * 通过状态码和错误消息创建异常对象
         * @param message
         * @param code
         */
        public LeaseException(String message, Integer code) {
            super(message);
            this.code = code;
        }
    
        /**
         * 根据响应结果枚举对象创建异常对象
         * @param resultCodeEnum
         */
        public LeaseException(ResultCodeEnum resultCodeEnum) {
            super(resultCodeEnum.getMessage());
            this.code = resultCodeEnum.getCode();
        }
    
        @Override
        public String toString() {
            return "LeaseException{" +
                    "code=" + code +
                    ", message=" + this.getMessage() +
                    '}';
        }
    }
    ```
    
    在**common模块**的`com.atguigu.lease.common.exception.GlobalExceptionHandler`类中，增加自定义异常类的处理逻辑
    
    ```java
    @ExceptionHandler(LeaseException.class)
    @ResponseBody
    public Result error(LeaseException e){
        e.printStackTrace();
        return Result.fail(e.getCode(), e.getMessage());
    }
    ```
    
    为Result新增一个构造方法，如下
    
    ```java
    public static <T> Result<T> fail(Integer code, String message) {
        Result<T> result = build(null);
        result.setCode(code);
        result.setMessage(message);
        return result;
    }
    ```
    
    `removeApartmentById`方法的最终实现如下
    
    ```java
    @Override
    public void removeApartmentById(Long id) {
        
        LambdaQueryWrapper<RoomInfo> roomQueryWrapper = new LambdaQueryWrapper<>();
        roomQueryWrapper.eq(RoomInfo::getApartmentId, id);
        Long count = roomInfoMapper.selectCount(roomQueryWrapper);
        if (count > 0) {
            throw new LeaseException(ResultCodeEnum.ADMIN_APARTMENT_DELETE_ERROR);
        }
    
        //1.删除GraphInfo
        LambdaQueryWrapper<GraphInfo> graphQueryWrapper = new LambdaQueryWrapper<>();
        graphQueryWrapper.eq(GraphInfo::getItemType, ItemType.APARTMENT);
        graphQueryWrapper.eq(GraphInfo::getItemId, id);
        graphInfoService.remove(graphQueryWrapper);
    
        //2.删除ApartmentLabel
        LambdaQueryWrapper<ApartmentLabel> labelQueryWrapper = new LambdaQueryWrapper<>();
        labelQueryWrapper.eq(ApartmentLabel::getApartmentId, id);
        apartmentLabelService.remove(labelQueryWrapper);
    
        //3.删除ApartmentFacility
        LambdaQueryWrapper<ApartmentFacility> facilityQueryWrapper = new LambdaQueryWrapper<>();
        facilityQueryWrapper.eq(ApartmentFacility::getApartmentId, id);
        apartmentFacilityService.remove(facilityQueryWrapper);
    
        //4.删除ApartmentFeeValue
        LambdaQueryWrapper<ApartmentFeeValue> feeQueryWrapper = new LambdaQueryWrapper<>();
        feeQueryWrapper.eq(ApartmentFeeValue::getApartmentId, id);
        apartmentFeeValueService.remove(feeQueryWrapper);
    
        //5.删除ApartmentInfo
        super.removeById(id);
    }
    ```

##### 5. 根据ID修改公寓发布状态

在`ApartmentController`中增加如下内容：

```java
@Operation(summary = "根据id修改公寓发布状态")
@PostMapping("updateReleaseStatusById")
public Result updateReleaseStatusById(@RequestParam Long id, @RequestParam ReleaseStatus status) {

    LambdaUpdateWrapper<ApartmentInfo> updateWrapper = new LambdaUpdateWrapper<>();
    updateWrapper.eq(ApartmentInfo::getId, id);
    updateWrapper.set(ApartmentInfo::getIsRelease, status);
    service.update(updateWrapper);
    return Result.ok();
}
```

##### 6. 根据区县ID查询公寓信息列表

在`ApartmentController`中增加如下内容：

```java
@Operation(summary = "根据区县id查询公寓信息列表")
@GetMapping("listInfoByDistrictId")
public Result<List<ApartmentInfo>> listInfoByDistrictId(@RequestParam Long id) {

    LambdaQueryWrapper<ApartmentInfo> queryWrapper = new LambdaQueryWrapper<>();
    queryWrapper.eq(ApartmentInfo::getDistrictId, id);
    List<ApartmentInfo> list = service.list(queryWrapper);
    return Result.ok(list);
}
```

#### 7.2.2.10 房间管理

房间管理共有六个接口，下面逐一实现

首先在`RoomController`中注入`RoomInfoService`，如下

```java
@Tag(name = "房间信息管理")
@RestController
@RequestMapping("/admin/room")
public class RoomController {

    @Autowired
    private RoomInfoService service;
}
```

##### 1. 保存或更新房间信息

- **查看请求的数据结构**

  查看**web-admin模块**中的`com.atguigu.lease.web.admin.vo.room.RoomSubmitVo`，内容如下

  ```java
  @Data
  @Schema(description = "房间信息")
  public class RoomSubmitVo extends RoomInfo {
  
      @Schema(description = "图片列表")
      private List<GraphVo> graphVoList;
  
      @Schema(description = "属性信息列表")
      private List<Long> attrValueIds;
  
      @Schema(description = "配套信息列表")
      private List<Long> facilityInfoIds;
  
      @Schema(description = "标签信息列表")
      private List<Long> labelInfoIds;
  
      @Schema(description = "支付方式列表")
      private List<Long> paymentTypeIds;
  
      @Schema(description = "可选租期列表")
      private List<Long> leaseTermIds;
  }
  ```

- **编写Controller层逻辑**

  在`RoomController`中增加如下内容

  ```java
  @Operation(summary = "保存或更新房间信息")
  @PostMapping("saveOrUpdate")
  public Result saveOrUpdate(@RequestBody RoomSubmitVo roomSubmitVo) {
      service.saveOrUpdateRoom(roomSubmitVo);
      return Result.ok();
  }
  ```
  
- **编写Service 层逻辑**

  在`RoomInfoService`中增加如下内容

  ```java
  void saveOrUpdateRoom(RoomSubmitVo roomSubmitVo);
  ```

  在`RoomInfoServiceImpl`中增加如下内容

  ```java
  @Override
  public void saveOrUpdateRoom(RoomSubmitVo roomSubmitVo) {
      boolean isUpdate = roomSubmitVo.getId() != null;
      super.saveOrUpdate(roomSubmitVo);
  
      //若为更新操作，则先删除与Room相关的各项信息列表
      if (isUpdate) {
          //1.删除原有graphInfoList
          LambdaQueryWrapper<GraphInfo> graphQueryWrapper = new LambdaQueryWrapper<>();
          graphQueryWrapper.eq(GraphInfo::getItemType, ItemType.ROOM);
          graphQueryWrapper.eq(GraphInfo::getItemId, roomSubmitVo.getId());
          graphInfoService.remove(graphQueryWrapper);
  
          //2.删除原有roomAttrValueList
          LambdaQueryWrapper<RoomAttrValue> attrQueryMapper = new LambdaQueryWrapper<>();
          attrQueryMapper.eq(RoomAttrValue::getRoomId, roomSubmitVo.getId());
          roomAttrValueService.remove(attrQueryMapper);
  
          //3.删除原有roomFacilityList
          LambdaQueryWrapper<RoomFacility> facilityQueryWrapper = new LambdaQueryWrapper<>();
          facilityQueryWrapper.eq(RoomFacility::getRoomId, roomSubmitVo.getId());
          roomFacilityService.remove(facilityQueryWrapper);
  
          //4.删除原有roomLabelList
          LambdaQueryWrapper<RoomLabel> labelQueryWrapper = new LambdaQueryWrapper<>();
          labelQueryWrapper.eq(RoomLabel::getRoomId, roomSubmitVo.getId());
          roomLabelService.remove(labelQueryWrapper);
  
          //5.删除原有paymentTypeList
          LambdaQueryWrapper<RoomPaymentType> paymentQueryWrapper = new LambdaQueryWrapper<>();
          paymentQueryWrapper.eq(RoomPaymentType::getRoomId, roomSubmitVo.getId());
          roomPaymentTypeService.remove(paymentQueryWrapper);
  
  
          //6.删除原有leaseTermList
          LambdaQueryWrapper<RoomLeaseTerm> termQueryWrapper = new LambdaQueryWrapper<>();
          termQueryWrapper.eq(RoomLeaseTerm::getRoomId, roomSubmitVo.getId());
          roomLeaseTermService.remove(termQueryWrapper);
      }
  
      //1.保存新的graphInfoList
      List<GraphVo> graphVoList = roomSubmitVo.getGraphVoList();
      if (!CollectionUtils.isEmpty(graphVoList)) {
          ArrayList<GraphInfo> graphInfoList = new ArrayList<>();
          for (GraphVo graphVo : graphVoList) {
              GraphInfo graphInfo = new GraphInfo();
              graphInfo.setItemType(ItemType.ROOM);
              graphInfo.setItemId(roomSubmitVo.getId());
              graphInfo.setName(graphVo.getName());
              graphInfo.setUrl(graphVo.getUrl());
              graphInfoList.add(graphInfo);
          }
          graphInfoService.saveBatch(graphInfoList);
      }
  
      //2.保存新的roomAttrValueList
      List<Long> attrValueIds = roomSubmitVo.getAttrValueIds();
      if (!CollectionUtils.isEmpty(attrValueIds)) {
          List<RoomAttrValue> roomAttrValueList = new ArrayList<>();
          for (Long attrValueId : attrValueIds) {
              RoomAttrValue roomAttrValue = RoomAttrValue.builder().roomId(roomSubmitVo.getId()).attrValueId(attrValueId).build();
              roomAttrValueList.add(roomAttrValue);
          }
          roomAttrValueService.saveBatch(roomAttrValueList);
      }
  
      //3.保存新的facilityInfoList
      List<Long> facilityInfoIds = roomSubmitVo.getFacilityInfoIds();
      if (!CollectionUtils.isEmpty(facilityInfoIds)) {
          List<RoomFacility> roomFacilityList = new ArrayList<>();
          for (Long facilityInfoId : facilityInfoIds) {
              RoomFacility roomFacility = RoomFacility.builder().roomId(roomSubmitVo.getId()).facilityId(facilityInfoId).build();
              roomFacilityList.add(roomFacility);
          }
          roomFacilityService.saveBatch(roomFacilityList);
      }
  
      //4.保存新的labelInfoList
      List<Long> labelInfoIds = roomSubmitVo.getLabelInfoIds();
      if (!CollectionUtils.isEmpty(labelInfoIds)) {
          ArrayList<RoomLabel> roomLabelList = new ArrayList<>();
          for (Long labelInfoId : labelInfoIds) {
              RoomLabel roomLabel = RoomLabel.builder().roomId(roomSubmitVo.getId()).labelId(labelInfoId).build();
              roomLabelList.add(roomLabel);
          }
          roomLabelService.saveBatch(roomLabelList);
      }
  
      //5.保存新的paymentTypeList
      List<Long> paymentTypeIds = roomSubmitVo.getPaymentTypeIds();
      if (!CollectionUtils.isEmpty(paymentTypeIds)) {
          ArrayList<RoomPaymentType> roomPaymentTypeList = new ArrayList<>();
          for (Long paymentTypeId : paymentTypeIds) {
              RoomPaymentType roomPaymentType = RoomPaymentType.builder().roomId(roomSubmitVo.getId()).paymentTypeId(paymentTypeId).build();
              roomPaymentTypeList.add(roomPaymentType);
          }
          roomPaymentTypeService.saveBatch(roomPaymentTypeList);
      }
  
      //6.保存新的leaseTermList
      List<Long> leaseTermIds = roomSubmitVo.getLeaseTermIds();
      if (!CollectionUtils.isEmpty(leaseTermIds)) {
          ArrayList<RoomLeaseTerm> roomLeaseTerms = new ArrayList<>();
          for (Long leaseTermId : leaseTermIds) {
              RoomLeaseTerm roomLeaseTerm = RoomLeaseTerm.builder().roomId(roomSubmitVo.getId()).leaseTermId(leaseTermId).build();
              roomLeaseTerms.add(roomLeaseTerm);
          }
          roomLeaseTermService.saveBatch(roomLeaseTerms);
      }
  }
  ```

##### 2. 根据条件分页查询房间列表

- **查看请求和响应的数据结构**

  - **请求数据结构**

    - `current`和`size`为分页相关参数，分别表示**当前所处页面**和**每个页面的记录数**。

    - `RoomQueryVo`为房间的查询条件，详细结构如下：

      ```java
      @Schema(description = "房间查询实体")
      @Data
      public class RoomQueryVo {
      
          @Schema(description = "省份Id")
          private Long provinceId;
      
          @Schema(description = "城市Id")
          private Long cityId;
      
          @Schema(description = "区域Id")
          private Long districtId;
      
          @Schema(description = "公寓Id")
          private Long apartmentId;
      }
      ```

  - **响应数据结构**

    单个房间信息记录可查看`com.atguigu.lease.web.admin.vo.room.RoomItemVo`，内容如下：

    ```java
    @Data
    @Schema(description = "房间信息")
    public class RoomItemVo extends RoomInfo {
    
        @Schema(description = "租约结束日期")
        private Date leaseEndDate;
    
        @Schema(description = "当前入住状态")
        private Boolean isCheckIn;
    
        @Schema(description = "所属公寓信息")
        private ApartmentInfo apartmentInfo;
    }
    ```

- **编写Controller层逻辑**

  在`RoomController`中增加如下内容

  ```java
  @Operation(summary = "根据条件分页查询房间列表")
  @GetMapping("pageItem")
  public Result<IPage<RoomItemVo>> pageItem(@RequestParam long current, @RequestParam long size, RoomQueryVo queryVo) {
      IPage<RoomItemVo> page = new Page<>(current, size);
      IPage<RoomItemVo> result = service.pageRoomItemByQuery(page, queryVo);
      return Result.ok(result);
  }
  ```

- **编写Service 层逻辑**

  - 在`RoomInfoService`中增加如下内容

    ```java
    IPage<RoomItemVo> pageRoomItemByQuery(IPage<RoomItemVo> page, RoomQueryVo queryVo);
    ```

  - 在`RoomInfoServiceImpl`中增加如下内容

    ```java
    @Override
    public IPage<RoomItemVo> pageRoomItemByQuery(IPage<RoomItemVo> page, RoomQueryVo queryVo) {
        return roomInfoMapper.pageRoomItemByQuery(page, queryVo);
    }
    ```
  
- **编写Mapper层逻辑**

  - 在`RoomInfoMapper`中增加如下内容

    ```java
    IPage<RoomItemVo> pageRoomItemByQuery(IPage<RoomItemVo> page, RoomQueryVo queryVo);
    ```

  - 在`RoomInfoMapper.xml`中增加如下内容

    ```java
    <resultMap id="RoomItemVoMap" type="com.atguigu.lease.web.admin.vo.room.RoomItemVo" autoMapping="true">
        <id property="id" column="id"/>
        <association property="apartmentInfo" javaType="com.atguigu.lease.model.entity.ApartmentInfo" autoMapping="true">
            <id property="id" column="apart_id"/>
            <result property="isRelease" column="apart_is_release"/>
        </association>
    </resultMap>
    
    <select id="pageRoomItemByQuery" resultMap="RoomItemVoMap">
        select ri.id,
               ri.room_number,
               ri.rent,
               ri.apartment_id,
               ri.is_release,
               la.room_id is not null is_check_in,
               la.lease_end_date,
               ai.id                  apart_id,
               ai.name,
               ai.introduction,
               ai.district_id,
               ai.district_name,
               ai.city_id,
               ai.city_name,
               ai.province_id,
               ai.province_name,
               ai.address_detail,
               ai.latitude,
               ai.longitude,
               ai.phone,
               ai.is_release          apart_is_release
        from room_info ri
                 left join lease_agreement la
                           on ri.id = la.room_id
                               and la.is_deleted = 0
                               and la.status in (2,5)
                 left join apartment_info ai
                           on ri.apartment_id = ai.id
                               and ai.is_deleted = 0
        <where>
            ri.is_deleted = 0
            <if test="queryVo.provinceId != null">
                apart.province_id = #{queryVo.provinceId}
            </if>
            <if test="queryVo.cityId != null">
                and apart.city_id = #{queryVo.cityId}
            </if>
            <if test="queryVo.districtId != null">
                and apart.district_id = #{queryVo.districtId}
            </if>
            <if test="queryVo.apartmentId != null">
                and apartment_id = #{queryVo.apartmentId}
            </if>
        </where>
    </select>
    ```

##### 3. 根据ID获取房间详细信息

- **查看响应数据结构**

  查看**web-admin**下的`com.atguigu.lease.web.admin.vo.room.RoomDetailVo`，内容如下

  ```java
  @Schema(description = "房间信息")
  @Data
  public class RoomDetailVo extends RoomInfo {
  
      @Schema(description = "所属公寓信息")
      private ApartmentInfo apartmentInfo;
  
      @Schema(description = "图片列表")
      private List<GraphVo> graphVoList;
  
      @Schema(description = "属性信息列表")
      private List<AttrValueVo> attrValueVoList;
  
      @Schema(description = "配套信息列表")
      private List<FacilityInfo> facilityInfoList;
  
      @Schema(description = "标签信息列表")
      private List<LabelInfo> labelInfoList;
  
      @Schema(description = "支付方式列表")
      private List<PaymentType> paymentTypeList;
  
      @Schema(description = "可选租期列表")
      private List<LeaseTerm> leaseTermList;
  }
  ```

- **编写Controller层逻辑**

  在`RoomController`中增加如下内容

  ```java
  @Operation(summary = "根据id获取房间详细信息")
  @GetMapping("getDetailById")
  public Result<RoomDetailVo> getDetailById(@RequestParam Long id) {
      RoomDetailVo roomInfo = service.getRoomDetailById(id);
      return Result.ok(roomInfo);
  }
  ```

- **编写Service 层逻辑**

  - 在`RoomInfoService`中增加如下内容

    ```java
    RoomDetailVo getRoomDetailById(Long id);
    ```

  - 在`RoomInfoServiceImpl`中增加如下内容

    ```java
    @Override
    public RoomDetailVo getRoomDetailById(Long id) {
    
        //1.查询RoomInfo
        RoomInfo roomInfo = roomInfoMapper.selectById(id);
    
        //2.查询所属公寓信息
        ApartmentInfo apartmentInfo = apartmentInfoMapper.selectById(roomInfo.getApartmentId());
    
        //3.查询graphInfoList
        List<GraphVo> graphVoList = graphInfoMapper.selectListByItemTypeAndId(ItemType.ROOM, id);
    
        //4.查询attrValueList
        List<AttrValueVo> attrvalueVoList = attrValueMapper.selectListByRoomId(id);
    
        //5.查询facilityInfoList
        List<FacilityInfo> facilityInfoList = facilityInfoMapper.selectListByRoomId(id);
    
        //6.查询labelInfoList
        List<LabelInfo> labelInfoList = labelInfoMapper.selectListByRoomId(id);
    
        //7.查询paymentTypeList
        List<PaymentType> paymentTypeList = paymentTypeMapper.selectListByRoomId(id);
    
        //8.查询leaseTermList
        List<LeaseTerm> leaseTermList = leaseTermMapper.selectListByRoomId(id);
    
    
        RoomDetailVo adminRoomDetailVo = new RoomDetailVo();
        BeanUtils.copyProperties(roomInfo, adminRoomDetailVo);
    
        adminRoomDetailVo.setApartmentInfo(apartmentInfo);
        adminRoomDetailVo.setGraphVoList(graphVoList);
        adminRoomDetailVo.setAttrValueVoList(attrvalueVoList);
        adminRoomDetailVo.setFacilityInfoList(facilityInfoList);
        adminRoomDetailVo.setLabelInfoList(labelInfoList);
        adminRoomDetailVo.setPaymentTypeList(paymentTypeList);
        adminRoomDetailVo.setLeaseTermList(leaseTermList);
    
        return adminRoomDetailVo;
    }
    ```
  
- **编写Mapper层逻辑**

  - 编写房间属性查询逻辑

    - 在`AttrValueMapper`中增加如下内容

      ```java
      List<AttrValueVo> selectListByRoomId(Long id);
      ```

    - 在`AttrValueMapper.xml`中增加如下内容

      ```java
      <select id="selectListByRoomId" resultType="com.atguigu.lease.web.admin.vo.attr.AttrValueVo">
          select v.id,
                 v.name,
                 v.attr_key_id,
                 k.name attr_key_name
          from attr_value v
                   join attr_key k on v.attr_key_id = k.id
          where v.is_deleted = 0
            and k.is_deleted = 0
            and v.id in (select attr_value_id
                         from room_attr_value
                         where is_deleted = 0
                           and room_id = #{id})
      </select>
      ```
    
  - 编写房间配套查询逻辑
  
    - 在`FacilityInfoMapper`中增加如下内容
  
      ```java
      List<FacilityInfo> selectListByRoomId(Long id);
      ```

    - 在`FacilityInfoMapper.xml`中增加如下内容

      ```java
      <select id="selectListByRoomId" resultType="com.atguigu.lease.model.entity.FacilityInfo">
          select id,
                 type,
                 name,
                 icon
          from facility_info
          where is_deleted = 0
            and id in
                (select facility_id
                 from room_facility
                 where is_deleted = 0
                   and room_id = #{id})
      </select>
      ```
  
  - 编写房间标签查询逻辑
  
    - 在`LabelInfoMapper`中增加如下内容
  
      ```java
      List<LabelInfo> selectListByRoomId(Long id);
      ```

    - 在`LabelInfoMapper.xml`中增加如下内容

      ```java
      <select id="selectListByRoomId" resultType="com.atguigu.lease.model.entity.LabelInfo">
          select id,
                 type,
                 name
          from label_info
          where is_deleted = 0
            and id in
                (select label_id
                 from room_label
                 where is_deleted = 0
                   and room_id = #{id})
      </select>
      ```
  
  - 编写房间可选支付方式查询逻辑
  
    - 在`PaymentTypeMapper`中增加如下内容
  
      ```java
      List<PaymentType> selectListByRoomId(Long id);
      ```

    - 在`PaymentTypeMapper.xml`中增加如下内容

      ```java
      <select id="selectListByRoomId" resultType="com.atguigu.lease.model.entity.PaymentType">
          select id,
                 name,
                 pay_month_count,
                 additional_info
          from payment_type
          where is_deleted = 0
            and id in
                (select payment_type_id
                 from room_payment_type
                 where is_deleted = 0
                   and room_id = #{id})
      </select> 
      ```
    
  - 编写房间可选租期查询逻辑
  
    - 在`Mapper`中增加如下内容
  
      ```java
      List<LeaseTerm> selectListByRoomId(Long id);
      ```
  
    - 在`Mapper.xml`中增加如下内容
  
      ```java
      <select id="selectListByRoomId" resultType="com.atguigu.lease.model.entity.LeaseTerm">
          select id,
                 month_count,
                 unit
          from lease_term
          where is_deleted = 0
            and id in (select lease_term_id
                       from room_lease_term
                       where is_deleted = 0
                         and room_id = #{id})
      </select>
      ```

##### 4. 根据ID删除房间信息

- **编写Controller层逻辑**

  在`RoomController`中增加如下内容

  ```java
  @Operation(summary = "根据id删除房间信息")
  @DeleteMapping("removeById")
  public Result removeById(@RequestParam Long id) {
      service.removeRoomById(id);
      return Result.ok();
  }
  ```

- **编写Service 层逻辑**

  - 在`RoomInfoService`中增加如下内容

    ```java
    void removeRoomById(Long id);
    ```

  - 在`RoomInfoServiceImpl`中增加如下内容

    ```java
    @Override
    public void removeRoomById(Long id) {
        //1.删除RoomInfo
        super.removeById(id);
    
        //2.删除graphInfoList
        LambdaQueryWrapper<GraphInfo> graphQueryWrapper = new LambdaQueryWrapper<>();
        graphQueryWrapper.eq(GraphInfo::getItemType, ItemType.ROOM);
        graphQueryWrapper.eq(GraphInfo::getItemId, id);
        graphInfoService.remove(graphQueryWrapper);
    
        //3.删除attrValueList
        LambdaQueryWrapper<RoomAttrValue> attrQueryWrapper = new LambdaQueryWrapper<>();
        attrQueryWrapper.eq(RoomAttrValue::getRoomId, id);
        roomAttrValueService.remove(attrQueryWrapper);
    
        //4.删除facilityInfoList
        LambdaQueryWrapper<RoomFacility> facilityQueryWrapper = new LambdaQueryWrapper<>();
        facilityQueryWrapper.eq(RoomFacility::getRoomId, id);
        roomFacilityService.remove(facilityQueryWrapper);
    
        //5.删除labelInfoList
        LambdaQueryWrapper<RoomLabel> labelQueryWrapper = new LambdaQueryWrapper<>();
        labelQueryWrapper.eq(RoomLabel::getRoomId, id);
        roomLabelService.remove(labelQueryWrapper);
    
        //6.删除paymentTypeList
        LambdaQueryWrapper<RoomPaymentType> paymentQueryWrapper = new LambdaQueryWrapper<>();
        paymentQueryWrapper.eq(RoomPaymentType::getRoomId, id);
        roomPaymentTypeService.remove(paymentQueryWrapper);
    
        //7.删除leaseTermList
        LambdaQueryWrapper<RoomLeaseTerm> termQueryWrapper = new LambdaQueryWrapper<>();
        termQueryWrapper.eq(RoomLeaseTerm::getRoomId, id);
        roomLeaseTermService.remove(termQueryWrapper);
    }
    ```

##### 5. 根据id修改房间发布状态

在`RoomController`中增加如下内容

```java
@Operation(summary = "根据id修改房间发布状态")
@PostMapping("updateReleaseStatusById")
public Result updateReleaseStatusById(Long id, ReleaseStatus status) {
    LambdaUpdateWrapper<RoomInfo> updateWrapper = new LambdaUpdateWrapper<>();
    updateWrapper.eq(RoomInfo::getId, id);
    updateWrapper.set(RoomInfo::getIsRelease, status);
    service.update(updateWrapper);
    return Result.ok();
}
```

##### 6. 根据公寓ID查询房间列表

在`RoomController`中增加如下内容

```java
@GetMapping("listBasicByApartmentId")
@Operation(summary = "根据公寓id查询房间列表")
public Result<List<RoomInfo>> listBasicByApartmentId(Long id) {
    LambdaQueryWrapper<RoomInfo> queryWrapper = new LambdaQueryWrapper<>();
    queryWrapper.eq(RoomInfo::getApartmentId, id);
    queryWrapper.eq(RoomInfo::getIsRelease, ReleaseStatus.RELEASED);
    List<RoomInfo> roomInfoList = service.list(queryWrapper);
    return Result.ok(roomInfoList);
}
```

### 7.2.3 租赁管理

#### 7.2.3.1 看房预约管理

看房预约管理共有两个接口，分别是**根据条件分页查询预约信息**、**根据ID更新预约状态**，下面逐一实现

首先在`ViewAppointmentController`中注入`ViewAppointmentService`，如下

```java
@Tag(name = "预约看房管理")
@RequestMapping("/admin/appointment")
@RestController
public class ViewAppointmentController {

    @Autowired
    private ViewAppointmentService service;
}
```

##### 1. 根据条件分页查询预约信息

- **查看请求和响应的数据结构**

  - **请求数据结构**

    - `current`和`size`为分页相关参数，分别表示**当前所处页面**和**每个页面的记录数**。

    - `AppointmentQueryVo`为看房预约的查询条件，详细结构如下：

      ```java
      @Data
      @Schema(description = "预约看房查询实体")
      public class AppointmentQueryVo {
      
          @Schema(description="预约公寓所在省份")
          private Long provinceId;
      
          @Schema(description="预约公寓所在城市")
          private Long cityId;
      
          @Schema(description="预约公寓所在区")
          private Long districtId;
      
          @Schema(description="预约公寓所在公寓")
          private Long apartmentId;
      
          @Schema(description="预约用户姓名")
          private String name;
      
          @Schema(description="预约用户手机号码")
          private String phone;
      }
      ```

  - **响应数据结构**

    单个看房预约信息的结构可查看**web-admin模块**下的`com.atguigu.lease.web.admin.vo.appointment.AppointmentVo`，内容如下：

    ```java
    @Data
    @Schema(description = "预约看房信息")
    public class AppointmentVo extends ViewAppointment {
    
        @Schema(description = "预约公寓信息")
        private ApartmentInfo apartmentInfo;
    }
    ```
    
  
- **编写Controller层逻辑**

  在`ViewAppointmentController`中增加如下内容

  ```java
  @Operation(summary = "分页查询预约信息")
  @GetMapping("page")
  public Result<IPage<AppointmentVo>> page(@RequestParam long current, @RequestParam long size, AppointmentQueryVo queryVo) {
      IPage<AppointmentVo> page = new Page<>(current, size);
      IPage<AppointmentVo> list = service.pageAppointmentByQuery(page, queryVo);
      return Result.ok(list);
  }
  ```
  
- **编写Service层逻辑**

  - 在`ViewAppointmentService`中增加如下内容

    ```java
    IPage<AppointmentVo> pageAppointmentByQuery(IPage<AppointmentVo> page, AppointmentQueryVo queryVo);
    ```

  - 在`ViewAppointmentServiceImpl`中增加如下内容

    ```java
    @Override
    public IPage<AppointmentVo> pageAppointmentByQuery(IPage<AppointmentVo> page, AppointmentQueryVo queryVo) {
        return viewAppointmentMapper.pageAppointmentByQuery(page, queryVo);
    }
    ```

- **编写Mapper层逻辑**

  - 在`ViewAppointmentMapper`中增加如下内容

    ```java
    IPage<AppointmentVo> pageAppointmentByQuery(IPage<AppointmentVo> page, AppointmentQueryVo queryVo);
    ```

  - 在`ViewAppointmentMapper.xml`中增加如下内容

    ```xml
    <resultMap id="AppointmentVoMap" type="com.atguigu.lease.web.admin.vo.appointment.AppointmentVo" autoMapping="true">
        <id property="id" column="id"/>
        <association property="apartmentInfo" javaType="com.atguigu.lease.model.entity.ApartmentInfo" autoMapping="true">
            <id property="id" column="apartment_id"/>
            <result property="name" column="apartment_name"/>
        </association>
    </resultMap>
    
    <select id="pageAppointmentByQuery" resultMap="AppointmentVoMap">
        select va.id,
               va.user_id,
               va.name,
               va.phone,
               va.appointment_time,
               va.additional_info,
               va.appointment_status,
               ai.id   apartment_id,
               ai.name apartment_name,
               ai.district_id,
               ai.district_name,
               ai.city_id,
               ai.city_name,
               ai.province_id,
               ai.province_name
        from view_appointment va
                 left join
             apartment_info ai
             on va.apartment_id = ai.id and ai.is_deleted=0
        <where>
            va.is_deleted = 0
            <if test="queryVo.provinceId != null">
                and ai.province_id = #{queryVo.provinceId}
            </if>
            <if test="queryVo.cityId != null">
                and ai.city_id = #{queryVo.cityId}
            </if>
            <if test="queryVo.districtId != null">
                and ai.district_id = #{queryVo.districtId}
            </if>
            <if test="queryVo.apartmentId != null">
                and va.apartment_id = #{queryVo.apartmentId}
            </if>
            <if test="queryVo.name != null and queryVo.name != ''">
                and va.name like concat('%',#{queryVo.name},'%')
            </if>
            <if test="queryVo.phone != null and queryVo.phone != ''">
                and va.phone like concat('%',#{queryVo.phone},'%')
            </if>
        </where>
    </select>
    ```
    
  
  **知识点**：
  
  `ViewAppointment`实体类中的`appointmentTime`字段为`Date`类型，`Date`类型的字段在序列化成JSON字符串时，需要考虑两个点，分别是**格式**和**时区**。本项目使用JSON序列化框架为Jackson，具体配置如下
  
  - **格式**
  
    格式可按照字段单独配置，也可全局配置，下面分别介绍
  
    - **单独配置**
  
      在指定字段增加`@JsonFormat`注解，如下
  
      ```java
      @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
      private Date appointmentTime;
      ```
  
    - **全局配置**
  
      在`application.yml`中增加如下内容
  
      ```yml
      spring:
        jackson:
          date-format: yyyy-MM-dd HH:mm:ss
      ```
  
  - **时区**
  
    时区同样可按照字段单独配置，也可全局配置，下面分别介绍
  
    - **单独配置**
  
      在指定字段增加`@JsonFormat`注解，如下
  
      ```java
      @JsonFormat(timezone = "GMT+8")
      private Date appointmentTime;
      ```
  
    - **全局配置**
  
      ```yml
      spring:
        jackson:
          time-zone: GMT+8
      ```
  
  推荐格式按照字段单独配置，时区全局配置。

##### 2. 根据ID更新预约状态

在`ViewAppointmentController`中增加如下内容

```java
@Operation(summary = "根据id更新预约状态")
@PostMapping("updateStatusById")
public Result updateStatusById(@RequestParam Long id, @RequestParam AppointmentStatus status) {

    LambdaUpdateWrapper<ViewAppointment> updateWrapper = new LambdaUpdateWrapper<>();
    updateWrapper.eq(ViewAppointment::getId, id);
    updateWrapper.set(ViewAppointment::getAppointmentStatus, status);
    service.update(updateWrapper);
    return Result.ok();
}
```

#### 7.2.3.2 租约管理

租约管理共有五个接口需要实现，除此之外，还需实现一个定时任务，用于检查租约是否到期以及修改到期状态。下面逐一实现

首先在`LeaseAgreementController`中注入`LeaseAgreementService`，如下

```java
@Tag(name = "租约管理")
@RestController
@RequestMapping("/admin/agreement")
public class LeaseAgreementController {

    @Autowired
    private LeaseAgreementService service;
}
```

##### 1. 保存获更新租约信息

在`LeaseAgreementController`中增加如下内容

```java
@Operation(summary = "保存或修改租约信息")
@PostMapping("saveOrUpdate")
public Result saveOrUpdate(@RequestBody LeaseAgreement leaseAgreement) {
    service.saveOrUpdate(leaseAgreement);
    return Result.ok();
}
```

##### 2. 根据条件分页查询租约列表

- **查看请求和响应的数据结构**

  - **请求数据结构**

      - `current`和`size`为分页相关参数，分别表示**当前所处页面**和**每个页面的记录数**。

      - `AgreementQueryVo`为公寓的查询条件，详细结构如下：

        ```java
        @Data
        @Schema(description = "租约查询实体")
        public class AgreementQueryVo {
        
            @Schema(description = "公寓所处省份id")
            private Long provinceId;
        
            @Schema(description = "公寓所处城市id")
            private Long cityId;
        
            @Schema(description = "公寓所处区域id")
            private Long districtId;
        
            @Schema(description = "公寓id")
            private Long apartmentId;
        
            @Schema(description = "房间号")
            private String roomNumber;
        
            @Schema(description = "用户姓名")
            private String name;
        
            @Schema(description = "用户手机号码")
            private String phone;
        }
      
  - **响应数据结构**
  
      单个租约信息的结构可查看`com.atguigu.lease.web.admin.vo.agreement.AgreementVo`，内容如下：
  
      ```java
      @Data
      @Schema(description = "租约信息")
      public class AgreementVo extends LeaseAgreement {
      
          @Schema(description = "签约公寓信息")
          private ApartmentInfo apartmentInfo;
      
          @Schema(description = "签约房间信息")
          private RoomInfo roomInfo;
      
          @Schema(description = "支付方式")
          private PaymentType paymentType;
      
          @Schema(description = "租期")
          private LeaseTerm leaseTerm;
      }
  
- **编写Controller层逻辑**

  在`LeaseAgreementController`中增加如下内容

  ```java
  @Operation(summary = "根据条件分页查询租约列表")
  @GetMapping("page")
  public Result<IPage<AgreementVo>> page(@RequestParam long current, @RequestParam long size, AgreementQueryVo queryVo) {
      IPage<AgreementVo> page = new Page<>(current, size);
      IPage<AgreementVo> list = service.pageAgreementByQuery(page, queryVo);
      return Result.ok(list);
  }
  ```

- **编写Service层逻辑**

  - 在`LeaseAgreementService`中增加如下内容

    ```java
    IPage<AgreementVo> pageAgreementByQuery(IPage<AgreementVo> page, AgreementQueryVo queryVo);
    ```

  - 在`LeaseAgreementServiceImpl`中增加如下内容

    ```java
    @Override
    public IPage<AgreementVo> pageAgreementByQuery(IPage<AgreementVo> page, AgreementQueryVo queryVo) {
    
        return leaseAgreementMapper.pageAgreementByQuery(page, queryVo);
    }
    ```

- **编写Mapper层逻辑**

  - 在`LeaseAgreementMapper`中增加如下内容

    ```java
    IPage<AgreementVo> pageAgreementByQuery(IPage<AgreementVo> page, AgreementQueryVo queryVo);
    ```

  - 在`LeaseAgreementMapper.xml`中增加如下内容

    ```java
    <resultMap id="agreementVoMap" type="com.atguigu.lease.web.admin.vo.agreement.AgreementVo" autoMapping="true">
        <id property="id" column="id"/>
        <association property="apartmentInfo" javaType="com.atguigu.lease.model.entity.ApartmentInfo" autoMapping="true">
            <id property="id" column="apartment_id"/>
            <result property="name" column="apartment_name"/>
        </association>
        <association property="roomInfo" javaType="com.atguigu.lease.model.entity.RoomInfo" autoMapping="true">
            <id property="id" column="room_id"/>
        </association>
        <association property="paymentType" javaType="com.atguigu.lease.model.entity.PaymentType" autoMapping="true">
            <id property="id" column="payment_type_id"/>
            <result property="name" column="payment_type_name"/>
        </association>
        <association property="leaseTerm" javaType="com.atguigu.lease.model.entity.LeaseTerm" autoMapping="true">
            <id property="id" column="lease_term_id"/>
        </association>
    </resultMap>
    
    <select id="pageAgreementByQuery" resultMap="agreementVoMap">
        select la.id,
               la.phone,
               la.name,
               la.identification_number,
               la.lease_start_date,
               la.lease_end_date,
               la.rent,
               la.deposit,
               la.status,
               la.source_type,
               la.additional_info,
               ai.id   apartment_id,
               ai.name apartment_name,
               ai.district_id,
               ai.district_name,
               ai.city_id,
               ai.city_name,
               ai.province_id,
               ai.province_name,
               ri.id   room_id,
               ri.room_number,
               pt.id   payment_type_id,
               pt.name payment_type_name,
               pt.pay_month_count,
               lt.id   lease_term_id,
               lt.month_count,
               lt.unit
        from  lease_agreement la
                 left join
              apartment_info ai
             on la.apartment_id = ai.id and ai.is_deleted=0
                 left join
              room_info ri
             on la.room_id = ri.id and ri.is_deleted=0
                 left join
              payment_type pt
             on la.payment_type_id = pt.id and pt.is_deleted=0
                 left join
              lease_term lt
             on la.lease_term_id = lt.id and lt.is_deleted=0
            <where>
                la.is_deleted = 0
                <if test="queryVo.provinceId != null">
                    and ai.province_id = #{queryVo.provinceId}
                </if>
                <if test="queryVo.cityId != null">
                    and ai.city_id = #{queryVo.cityId}
                </if>
                <if test="queryVo.districtId != null">
                    and ai.district_id = #{queryVo.districtId}
                </if>
                <if test="queryVo.apartmentId != null">
                    and la.apartment_id = #{queryVo.apartmentId}
                </if>
                <if test="queryVo.roomNumber != null and queryVo.roomNumber != ''">
                    and ri.room_number like concat('%',#{queryVo.roomNumber},'%')
                </if>
                <if test="queryVo.name != null and queryVo.name != ''">
                    and la.name like concat('%',#{queryVo.name},'%')
                </if>
                <if test="queryVo.phone != null and queryVo.phone != ''">
                    and la.phone like concat('%',#{queryVo.phone},'%')
                </if>
            </where>
    </select>
    ```

##### 3. 根据ID查询租约信息

- **编写Controller层逻辑**

  在`LeaseAgreementController`中增加如下内容

  ```java
  @Operation(summary = "根据id查询租约信息")
  @GetMapping(name = "getById")
  public Result<AgreementVo> getById(@RequestParam Long id) {
      AgreementVo apartment = service.getAgreementById(id);
      return Result.ok(apartment);
  }
  ```

- **编写Service层逻辑**

  - 在`LeaseAgreementService`中增加如下内容

    ```java
    AgreementVo getAgreementById(Long id);
    ```

  - 在`LeaseAgreementServiceImpl`中增加如下内容

    ```java
    @Override
    public AgreementVo getAgreementById(Long id) {
    
        //1.查询租约信息
        LeaseAgreement leaseAgreement = leaseAgreementMapper.selectById(id);
    
        //2.查询公寓信息
        ApartmentInfo apartmentInfo = apartmentInfoMapper.selectById(leaseAgreement.getApartmentId());
    
        //3.查询房间信息
        RoomInfo roomInfo = roomInfoMapper.selectById(leaseAgreement.getRoomId());
    
        //4.查询支付方式
        PaymentType paymentType = paymentTypeMapper.selectById(leaseAgreement.getPaymentTypeId());
    
        //5.查询租期
        LeaseTerm leaseTerm = leaseTermMapper.selectById(leaseAgreement.getLeaseTermId());
    
        AgreementVo adminAgreementVo = new AgreementVo();
        BeanUtils.copyProperties(leaseAgreement, adminAgreementVo);
        adminAgreementVo.setApartmentInfo(apartmentInfo);
        adminAgreementVo.setRoomInfo(roomInfo);
        adminAgreementVo.setPaymentType(paymentType);
        adminAgreementVo.setLeaseTerm(leaseTerm);
        return adminAgreementVo;
    }
    ```

##### 4. 根据ID删除租约信息

在`LeaseAgreementController`中增加如下内容

```java
@Operation(summary = "根据id删除租约信息")
@DeleteMapping("removeById")
public Result removeById(@RequestParam Long id) {
    service.removeById(id);
    return Result.ok();
}
```

##### 5. 根据ID更新租约状态

后台管理系统需要多个修改租约状态的接口，例如**修改租约状态为已取消**、**修改租约状态为已退租**等等。为省去重复编码，此处将多个接口合并为一个如下，注意，在生产中应避免这样的写法。

在`LeaseAgreementController`中增加如下内容

```java
@Operation(summary = "根据id更新租约状态")
@PostMapping("updateStatusById")
public Result updateStatusById(@RequestParam Long id, @RequestParam LeaseStatus status) {
    LambdaUpdateWrapper<LeaseAgreement> updateWrapper = new LambdaUpdateWrapper<>();
    updateWrapper.eq(LeaseAgreement::getId, id);
    updateWrapper.set(LeaseAgreement::getStatus, status);
    service.update(updateWrapper);
    return Result.ok();
}
```

##### 6. 定时检查租约状态

本节内容是通过定时任务定时检查租约是否到期。SpringBoot内置了定时任务，具体实现如下。

- 启用Spring Boot定时任务

  在SpringBoot启动类上增加`@EnableScheduling`注解，如下

  ```java
  @SpringBootApplication
  @EnableScheduling
  public class AdminWebApplication {
      public static void main(String[] args) {
          SpringApplication.run(AdminWebApplication.class, args);
      }
  }
  ```

- 编写定时逻辑

  在**web-admin模块**下创建`com.atguigu.lease.web.admin.schedule.ScheduledTasks`类，内容如下

  ```java
  @Component
  public class ScheduledTasks {
  
      @Autowired
      private LeaseAgreementService leaseAgreementService;
  
      @Scheduled(cron = "0 0 0 * * *")
      public void checkLeaseStatus() {
  
          LambdaUpdateWrapper<LeaseAgreement> updateWrapper = new LambdaUpdateWrapper<>();
          Date now = new Date();
          updateWrapper.le(LeaseAgreement::getLeaseEndDate, now);
          updateWrapper.eq(LeaseAgreement::getStatus, LeaseStatus.SIGNED);
          updateWrapper.in(LeaseAgreement::getStatus, LeaseStatus.SIGNED, LeaseStatus.WITHDRAWING);
  
          leaseAgreementService.update(updateWrapper);
      }
  }
  ```

  **知识点**:

  SpringBoot中的cron表达式语法如下
  
  ```
    ┌───────────── second (0-59)
    │ ┌───────────── minute (0 - 59)
    │ │ ┌───────────── hour (0 - 23)
    │ │ │ ┌───────────── day of the month (1 - 31)
    │ │ │ │ ┌───────────── month (1 - 12) (or JAN-DEC)
    │ │ │ │ │ ┌───────────── day of the week (0 - 7)
    │ │ │ │ │ │          (0 or 7 is Sunday, or MON-SUN)
    │ │ │ │ │ │
    * * * * * *
  ```

### 7.2.4 用户管理

用户管理共包含两个接口，分别是**根据条件分页查询用户列表**和**根据ID更新用户状态**，下面逐一实现

首先在`UserInfoController`中注入`UserInfoService`，如下

```java
@Tag(name = "用户信息管理")
@RestController
@RequestMapping("/admin/user")
public class UserInfoController {

    @Autowired
    private UserInfoService service;
}
```

##### 1. 根据条件分页查询用户列表

- **查看请求的数据结构**

  - `current`和`size`为分页相关参数，分别表示**当前所处页面**和**每个页面的记录数**。

  - `UserInfoQueryVo`为用户的查询条件，详细结构如下：

    ```java
    @Schema(description = "用户信息查询实体")
    @Data
    public class UserInfoQueryVo {
    
        @Schema(description = "用户手机号码")
        private String phone;
    
        @Schema(description = "用户账号状态")
        private BaseStatus status;
    }
    ```

- **编写Controller层逻辑**

  在`UserInfoController`中增加如下内容

  ```java
  @Operation(summary = "分页查询用户信息")
  @GetMapping("page")
  public Result<IPage<UserInfo>> pageUserInfo(@RequestParam long current, @RequestParam long size, UserInfoQueryVo queryVo) {
  
      IPage<UserInfo> page = new Page<>(current, size);
      LambdaQueryWrapper<UserInfo> queryWrapper = new LambdaQueryWrapper<>();
      queryWrapper.like(queryVo.getPhone() != null, UserInfo::getPhone, queryVo.getPhone());
      queryWrapper.eq(queryVo.getStatus() != null, UserInfo::getStatus, queryVo.getStatus());
      IPage<UserInfo> list = service.page(page, queryWrapper);
      return Result.ok(list);
  }
  ```
  
  **知识点**：
  
  `password`字段属于敏感信息，因此在查询时应过滤掉，可在`UserInfo`实体的`password`字段的`@TableField`注解中增加一个参数`select=false`来实现。

##### 2. 根据ID更新用户状态

在`UserInfoController`中增加如下内容

```java
@Operation(summary = "根据用户id更新账号状态")
@PostMapping("updateStatusById")
public Result updateStatusById(@RequestParam Long id, @RequestParam BaseStatus status) {

    LambdaUpdateWrapper<UserInfo> updateWrapper = new LambdaUpdateWrapper<>();
    updateWrapper.eq(UserInfo::getId, id);
    updateWrapper.set(UserInfo::getStatus, status);
    service.update(updateWrapper);
    return Result.ok();
}
```

### 7.2.5 系统管理

#### 7.2.5.1 后台用户岗位管理

后台用户岗位管理共有六个接口，下面逐一实现

首先在`SystemPostController`中注入`SystemPostService`，如下

```java
@RestController
@Tag(name = "后台用户岗位管理")
@RequestMapping("/admin/system/post")
public class SystemPostController {

    @Autowired
    private SystemPostService service;
}
```

##### 1. 分页查询岗位信息

在`SystemPostController`中增加如下内容

```java
@Operation(summary = "分页获取岗位信息")
@GetMapping("page")
private Result<IPage<SystemPost>> page(@RequestParam long current, @RequestParam long size) {

    IPage<SystemPost> page = new Page<>(current, size);
    IPage<SystemPost> systemPostPage = service.page(page);
    return Result.ok(systemPostPage);
}
```

##### 2. 保存或更新岗位信息

在`SystemPostController`中增加如下内容

```java
@Operation(summary = "保存或更新岗位信息")
@PostMapping("saveOrUpdate")
public Result saveOrUpdate(@RequestBody SystemPost systemPost) {

    service.saveOrUpdate(systemPost);
    return Result.ok();
}
```

##### 3. 根据ID删除岗位信息

在`SystemPostController`中增加如下内容

```java
@DeleteMapping("deleteById")
@Operation(summary = "根据id删除岗位")
public Result removeById(@RequestParam Long id) {

    service.removeById(id);
    return Result.ok();
}
```

##### 4. 获取全部岗位列表

在`SystemPostController`增加入下内容

```java
@Operation(summary = "获取全部岗位列表")
@GetMapping("list")
public Result<List<SystemPost>> list() {
    List<SystemPost> list = service.list();
    return Result.ok(list);
}
```

##### 5. 根据ID获取岗位信息

在`SystemPostController`中增加如下内容

```java
@GetMapping("getById")
@Operation(summary = "根据id获取岗位信息")
public Result<SystemPost> getById(@RequestParam Long id) {
    SystemPost systemPost = service.getById(id);
    return Result.ok(systemPost);
}
```

##### 6. 根据ID修改岗位状态

在`SystemPostController`中增加如下内容

```java
@Operation(summary = "根据岗位id修改状态")
@PostMapping("updateStatusByPostId")
public Result updateStatusByPostId(@RequestParam Long id, @RequestParam BaseStatus status) {
    LambdaUpdateWrapper<SystemPost> updateWrapper = new LambdaUpdateWrapper<>();
    updateWrapper.eq(SystemPost::getId, id);
    updateWrapper.set(SystemPost::getStatus, status);
    service.update(updateWrapper);
    return Result.ok();    
}
```

#### 7.2.5.2 后台用户信息管理

后台用户信息管理共有六个接口，下面逐一实现

首先在`SystemUserController`中注入`SystemUserService`，如下

```java
@Tag(name = "后台用户信息管理")
@RestController
@RequestMapping("/admin/system/user")
public class SystemUserController {

    @Autowired
    SystemUserService service;
}
```

##### 1. 根据条件分页查询后台用户列表

- **查看请求和响应的数据结构**

  - **请求的数据结构**

    - `current`和`size`为分页相关参数，分别表示**当前所处页面**和**每个页面的记录数**。

    - `SystemUserQueryVo`为房间的查询条件，详细结构如下：

      ```java
      @Data
      @Schema(description = "员工查询实体")
      public class SystemUserQueryVo {
      
          @Schema(description= "员工姓名")
          private String name;
      
          @Schema(description= "手机号码")
          private String phone;
      }
      ```

  - **响应的数据结构**

    单个系统用户信息的结构可查看**web-admin**模块下的`com.atguigu.lease.web.admin.vo.system.user.SystemUserItemVo`，具体内容如下：

    ```java
    @Data
    @Schema(description = "后台管理系统用户基本信息实体")
    public class SystemUserItemVo extends SystemUser {
    
        @Schema(description = "岗位名称")
        @TableField(value = "post_name")
        private String postName;
    }
    ```

- **编写Controller层逻辑**

  在`SystemUserController`中增加如下内容

  ```java
  @Operation(summary = "根据条件分页查询后台用户列表")
  @GetMapping("page")
  public Result<IPage<SystemUserItemVo>> page(@RequestParam long current, @RequestParam long size, SystemUserQueryVo queryVo) {
      IPage<SystemUser> page = new Page<>(current, size);
      IPage<SystemUserItemVo> systemUserPage = service.pageSystemUserByQuery(page, queryVo);
      return Result.ok(systemUserPage);
  }
  ```

- **编写Service层逻辑**

  - 在`SystemUserService`中增加如下内容

    ```java
    IPage<SystemUserItemVo> pageSystemUserByQuery(IPage<SystemUser> page, SystemUserQueryVo queryVo);
    ```

  - 在`SystemUserServiceImpl`中增加如下内容

    ```java
    @Override
    public IPage<SystemUserItemVo> pageSystemUserByQuery(IPage<SystemUser> page, SystemUserQueryVo queryVo) {
        return systemUserMapper.pageSystemUserByQuery(page, queryVo);
    }
    ```

- **编写Mapper层逻辑**

  - 在`SystemUserMapper`中增加如下内容

    ```java
    IPage<SystemUserItemVo> pageSystemUserByQuery(IPage<SystemUser> page, SystemUserQueryVo queryVo);
    ```

  - 在`SystemUserMapper.xml`中增加如下内容

    ```java
    <select id="pageSystemUserByQuery"
            resultType="com.atguigu.lease.web.admin.vo.system.user.SystemUserItemVo">
        select su.id,
               username,
               su.name,
               type,
               phone,
               avatar_url,
               additional_info,
               post_id,
    		   su.status,
               sp.name post_name
        from system_user su
                 left join system_post sp on su.post_id = sp.id and sp.is_deleted = 0
        <where>
            su.is_deleted = 0
            <if test="queryVo.name != null and queryVo.name != ''">
                and su.name like concat('%',#{queryVo.name},'%')
            </if>
            <if test="queryVo.phone !=null and queryVo.phone != ''">
                and su.phone like concat('%',#{queryVo.phone},'%')
            </if>
        </where>
    </select>
    ```
    
    **知识点**
    
    `password`字段不要查询。

##### 2. 根据ID查询后台用户信息

- **编写Controller层逻辑**

  在`SystemUserController`中增加如下内容

  ```java
  @Operation(summary = "根据ID查询后台用户信息")
  @GetMapping("getById")
  public Result<SystemUserItemVo> getById(@RequestParam Long id) {
  
      SystemUserItemVo systemUser = service.getSystemUserById(id);
      return Result.ok(systemUser);
  }
  ```

- **编写Service层逻辑**

  - 在`SystemUserServcie`中增加如下内容

    ```java
    SystemUserItemVo getSystemUserById(Long id);
    ```

  - 在`SystemUserServcieImpl`中增加如下内容

    ```java
    @Override
    public SystemUserItemVo getSystemUserById(Long id) {
        SystemUser systemUser = systemUserMapper.selectById(id);
    
        SystemPost systemPost = systemPostMapper.selectById(systemUser.getPostId());
    
        SystemUserItemVo systemUserItemVo = new SystemUserItemVo();
        BeanUtils.copyProperties(systemPost, systemUserItemVo);
        systemUserItemVo.setPostName(systemUserItemVo.getPostName());
    
        return systemUserItemVo;
    }
    ```
    
    **知识点**
    
    `system_user`表中的`password`字段不应查询，需要在`SystemUser`的`password`字段的`@TableField`注解中增加`select=false`参数。

##### 3. 保存或更新后台用户信息

- **编写Controller层逻辑**

  在`SystemUserController`中增加如下内容

  ```java
  @Operation(summary = "保存或更新后台用户信息")
  @PostMapping("saveOrUpdate")
  public Result saveOrUpdate(@RequestBody SystemUser systemUser) {
      if(systemUser.getPassword() != null){
          systemUser.setPassword(DigestUtils.md5Hex(systemUser.getPassword()));
      }
      service.saveOrUpdate(systemUser);
      return Result.ok();
  }
  ```

  **知识点**：

  - **密码处理**

    用户的密码通常不会直接以明文的形式保存到数据库中，而是会先经过处理，然后将处理之后得到的"密文"保存到数据库，这样能够降低数据库泄漏导致的用户账号安全问题。

    密码通常会使用一些单向函数进行处理，如下图所示

    <img src="images/密码处理流程.drawio.png" style="zoom: 50%;" />

    常用于处理密码的单向函数（算法）有MD5、SHA-256等，**Apache Commons**提供了一个工具类`DigestUtils`，其中就包含上述算法的实现。

    > **Apache Commons**是Apache软件基金会下的一个项目，其致力于提供可重用的开源软件，其中包含了很多易于使用的现成工具。

    使用该工具类需引入`commons-codec`依赖，在**common模块**的pom.xml中增加如下内容

    ```xml
    <dependency>
        <groupId>commons-codec</groupId>
        <artifactId>commons-codec</artifactId>
    </dependency>
    ```

  - **Mybatis-Plus update strategy**

    使用Mybatis-Plus提供的更新方法时，若实体中的字段为`null`，默认情况下，最终生成的update语句中，不会包含该字段。若想改变默认行为，可做以下配置。

    - 全局配置

      在`application.yml`中配置如下参数

      ```xml
      mybatis-plus:
        global-config:
          db-config:
            update-strategy: <strategy>
      ```

      **注**：上述`<strategy>`可选值有：`ignore`、`not_null`、`not_empty`、`never`，默认值为`not_null`

      - `ignore`：忽略空值判断，不管字段是否为空，都会进行更新

      - `not_null`：进行非空判断，字段非空才会进行判断

      - `not_empty`：进行非空判断，并进行非空串（""）判断，主要针对字符串类型

      - `never`：从不进行更新，不管该字段为何值，都不更新

    - 局部配置

      在实体类中的具体字段通过`@TableField`注解进行配置，如下：

      ```java
      @Schema(description = "密码")
      @TableField(value = "password", updateStrategy = FieldStrategy.NOT_EMPTY)
      private String password;
      ```

##### 4. 判断后台用户名是否可用

在`SystemUserController`中增加如下内容

```java
@Operation(summary = "判断后台用户名是否可用")
@GetMapping("isUserNameAvailable")
public Result<Boolean> isUsernameExists(@RequestParam String username) {
    LambdaQueryWrapper<SystemUser> queryWrapper = new LambdaQueryWrapper<>();
    queryWrapper.eq(SystemUser::getUsername, username);
    long count = service.count(queryWrapper);
    return Result.ok(count == 0);
}
```
##### 5. 根据ID删除后台用户信息

在`SystemUserController`中增加如下内容

```java
@DeleteMapping("deleteById")
@Operation(summary = "根据ID删除后台用户信息")
public Result removeById(@RequestParam Long id) {
    service.removeById(id);
    return Result.ok();
}
```

##### 6. 根据ID修改后台用户状态

在`SystemUserController`中增加如下内容

```java
@Operation(summary = "根据ID修改后台用户状态")
@PostMapping("updateStatusByUserId")
public Result updateStatusByUserId(@RequestParam Long id, @RequestParam BaseStatus status) {
    LambdaUpdateWrapper<SystemUser> updateWrapper = new LambdaUpdateWrapper<>();
    updateWrapper.eq(SystemUser::getId, id);
    updateWrapper.set(SystemUser::getStatus, status);
    service.update(updateWrapper);
    return Result.ok();
}
```

### 7.2.6 登录管理

#### 7.2.6.1 背景知识

**1. 认证方案概述**

有两种常见的认证方案，分别是基于**Session**的认证和基于**Token**的认证，下面逐一进行介绍

- **基于Session**

  基于Session的认证流程如下图所示

  <img src="images/登录流程-基于Session.drawio.png" style="zoom:50%;" />

  该方案的特点

  - 登录用户信息保存在服务端内存中，若访问量增加，单台节点压力会较大
  - 随用户规模增大，若后台升级为集群，则需要解决集群中各服务器登录状态共享的问题。

- **基于Token**

  基于Token的认证流程如下图所示

  <img src="images/登录流程-基于Token.drawio.png" style="zoom:50%;" />

  该方案的特点

  - 登录状态保存在客户端，服务器没有存储开销
  - 客户端发起的每个请求自身均携带登录状态，所以即使后台为集群，也不会面临登录状态共享的问题。

**2. Token详解**

本项目采用基于Token的登录方案，下面详细介绍Token这一概念。

我们所说的Token，通常指**JWT**（JSON Web TOKEN）。JWT是一种轻量级的安全传输方式，用于在两个实体之间传递信息，通常用于身份验证和信息传递。

JWT是一个字符串，如下图所示，该字符串由三部分组成，三部分由`.`分隔。三个部分分别被称为

- `header`（头部）
- `payload`（负载）
- `signature`（签名）

<img src="images/JWT.png" style="zoom: 80%;" />

各部分的作用如下

- **Header（头部）**

  Header部分是由一个JSON对象经过`base64url`编码得到的，这个JSON对象用于保存JWT 的类型（`typ`）、签名算法（`alg`）等元信息，例如

  ```json
  {
    "alg": "HS256",
    "typ": "JWT"
  }
  ```

- **Payload（负载）**

  也称为 Claims（声明），也是由一个JSON对象经过`base64url`编码得到的，用于保存要传递的具体信息。JWT规范定义了7个官方字段，如下：

  - iss (issuer)：签发人
  - exp (expiration time)：过期时间
  - sub (subject)：主题
  - aud (audience)：受众
  - nbf (Not Before)：生效时间
  - iat (Issued At)：签发时间
  - jti (JWT ID)：编号

  除此之外，我们还可以自定义任何字段，例如

  ```json
  {
    "sub": "1234567890",
    "name": "John Doe",
    "iat": 1516239022
  }
  ```

- **Signature（签名）**

  由头部、负载和秘钥一起经过（header中指定的签名算法）计算得到的一个字符串，用于防止消息被篡改。

#### 7.2.6.2 登录流程

后台管理系统的登录流程如下图所示

<img src="images/后台管理系统-登录流程.drawio.svg" style="zoom:50%;" />

根据上述登录流程，可分析出，登录管理共需三个接口，分别是**获取图形验证码**、**登录**、**获取登录用户个人信息**，除此之外，我们还需为所有受保护的接口增加验证JWT合法性的逻辑，这一功能可通过`HandlerInterceptor`来实现。

#### 7.2.6.3 接口开发

首先在`LoginController`中注入`LoginService`，如下

```java
@Tag(name = "后台管理系统登录管理")
@RestController
@RequestMapping("/admin")
public class LoginController {

    @Autowired
    private LoginService service;
}
```

##### 1. 获取图形验证码

- **查看响应的数据结构**

  查看**web-admin模块**下的`com.atguigu.lease.web.admin.vo.login.CaptchaVo`，内容如下

  ```java
  @Data
  @Schema(description = "图像验证码")
  @AllArgsConstructor
  public class CaptchaVo {
  
      @Schema(description="验证码图片信息")
      private String image;
  
      @Schema(description="验证码key")
      private String key;
  }
  ```

- **配置所需依赖**

  - **验证码生成工具**

    本项目使用开源的验证码生成工具**EasyCaptcha**，其支持多种类型的验证码，例如gif、中文、算术等，并且简单易用，具体内容可参考其[官方文档](https://gitee.com/ele-admin/EasyCaptcha)。

    在**common模块**的pom.xml文件中增加如下内容

    ```xml
    <dependency>
        <groupId>com.github.whvcse</groupId>
        <artifactId>easy-captcha</artifactId>
    </dependency>
    ```

  - **Redis**

    在**common模块**的pom.xml中增加如下内容

    ```xml
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-redis</artifactId>
    </dependency>
    ```

    在`application.yml`中增加如下配置

    ```yml
    spring:
      data:
        redis:
          host: <hostname>
          port: <port>
          database: 0
    ```

    **注意**：上述`hostname`和`port`需根据实际情况进行修改

- **编写Controller层逻辑**

  在`LoginController`中增加如下内容

  ```java
  @Operation(summary = "获取图形验证码")
  @GetMapping("login/captcha")
  public Result<CaptchaVo> getCaptcha() {
      CaptchaVo captcha = service.getCaptcha();
      return Result.ok(captcha);
  }
  ```

- **编写Service层逻辑**

  - 在`LoginService`中增加如下内容

    ```java
    CaptchaVo getCaptcha();
    ```

  - 在`LoginServiceImpl`中增加如下内容

    ```java
    @Autowired
    private StringRedisTemplate redisTemplate;
    
    @Override
    public CaptchaVo getCaptcha() {
        SpecCaptcha specCaptcha = new SpecCaptcha(130, 48, 4);
        specCaptcha.setCharType(Captcha.TYPE_DEFAULT);
    
        String code = specCaptcha.text().toLowerCase();
        String key = RedisConstant.ADMIN_LOGIN_PREFIX + UUID.randomUUID();
        String image = specCaptcha.toBase64();
        redisTemplate.opsForValue().set(key, code, RedisConstant.ADMIN_LOGIN_CAPTCHA_TTL_SEC, TimeUnit.SECONDS);
    
        return new CaptchaVo(image, key);
    }
    ```

     **知识点**：

    - 本项目Reids中的key需遵循以下命名规范：**项目名:功能模块名:其他**，例如`admin:login:123456`

    - `spring-boot-starter-data-redis`已经完成了`StringRedisTemplate`的自动配置，我们直接注入即可。

    - 为方便管理，可以将Reids相关的一些值定义为常量，例如key的前缀、TTL时长，内容如下。大家可将这些常量统一定义在**common模块**下的`com.atguigu.lease.common.constant.RedisConstant`类中
    
        ```java
        public class RedisConstant {
            public static final String ADMIN_LOGIN_PREFIX = "admin:login:";
            public static final Integer ADMIN_LOGIN_CAPTCHA_TTL_SEC = 60;
            public static final String APP_LOGIN_PREFIX = "app:login:";
            public static final Integer APP_LOGIN_CODE_RESEND_TIME_SEC = 60;
            public static final Integer APP_LOGIN_CODE_TTL_SEC = 60 * 10;
            public static final String APP_ROOM_PREFIX = "app:room:";
        }
        ```

##### 2. 登录接口

- **登录校验逻辑**

  用户登录的校验逻辑分为三个主要步骤，分别是**校验验证码**，**校验用户状态**和**校验密码**，具体逻辑如下

  - 前端发送`username`、`password`、`captchaKey`、`captchaCode`请求登录。
  - 判断`captchaCode`是否为空，若为空，则直接响应`验证码为空`；若不为空进行下一步判断。
  - 根据`captchaKey`从Redis中查询之前保存的`code`，若查询出来的`code`为空，则直接响应`验证码已过期`；若不为空进行下一步判断。
  - 比较`captchaCode`和`code`，若不相同，则直接响应`验证码不正确`；若相同则进行下一步判断。
  - 根据`username`查询数据库，若查询结果为空，则直接响应`账号不存在`；若不为空则进行下一步判断。
  - 查看用户状态，判断是否被禁用，若禁用，则直接响应`账号被禁`；若未被禁用，则进行下一步判断。
  - 比对`password`和数据库中查询的密码，若不一致，则直接响应`账号或密码错误`，若一致则进行入最后一步。
  - 创建JWT，并响应给浏览器。
  
- **接口逻辑实现**

  - **查看请求数据结构**

    查看**web-admin**模块下的`com.atguigu.lease.web.admin.vo.login.LoginVo`，具体内容如下

    ```java
    @Data
    @Schema(description = "后台管理系统登录信息")
    public class LoginVo {
    
        @Schema(description="用户名")
        private String username;
    
        @Schema(description="密码")
        private String password;
    
        @Schema(description="验证码key")
        private String captchaKey;
    
        @Schema(description="验证码code")
        private String captchaCode;
    }
    ```
    
  - **配置所需依赖**
  
    登录接口需要为登录成功的用户创建并返回JWT，本项目使用开源的JWT工具**Java-JWT**，配置如下，具体内容可参考[官方文档](https://github.com/jwtk/jjwt/tree/0.11.2)。
  
    - **引入Maven依赖**
    
      在**common模块**的pom.xml文件中增加如下内容
    
      ```xml
      <dependency>
          <groupId>io.jsonwebtoken</groupId>
          <artifactId>jjwt-api</artifactId>
      </dependency>
      
      <dependency>
          <groupId>io.jsonwebtoken</groupId>
          <artifactId>jjwt-impl</artifactId>
          <scope>runtime</scope>
      </dependency>
      
      <dependency>
          <groupId>io.jsonwebtoken</groupId>
          <artifactId>jjwt-jackson</artifactId>
          <scope>runtime</scope>
      </dependency>
      ```
    
    - **创建JWT工具类**
    
      在**common模块**下创建`com.atguigu.lease.common.utils.JwtUtil`工具类，内容如下
    
      ```java
      public class JwtUtil {
      
          private static long tokenExpiration = 60 * 60 * 1000L;
          private static SecretKey tokenSignKey = Keys.hmacShaKeyFor("M0PKKI6pYGVWWfDZw90a0lTpGYX1d4AQ".getBytes());
      
          public static String createToken(Long userId, String username) {
              String token = Jwts.builder().
                      setSubject("USER_INFO").
                      setExpiration(new Date(System.currentTimeMillis() + tokenExpiration)).
                      claim("userId", userId).
                      claim("username", username).
                      signWith(tokenSignKey).
                      compact();
              return token;
          }
      }
      ```
    
  - **编写Controller层逻辑**
  
    在`LoginController`中增加如下内容
  
    ```java
    @Operation(summary = "登录")
    @PostMapping("login")
    public Result<String> login(@RequestBody LoginVo loginVo) {
        String token = service.login(loginVo);
        return Result.ok(token);
    }
    ```
  
  - **编写Service层逻辑**
  
    - 在`LoginService`中增加如下内容
  
      ```java
      String login(LoginVo loginVo);
      ```
  
    - 在`LoginServiceImpl`中增加如下内容
  
      ```java
      @Override
      public String login(LoginVo loginVo) {
          //1.判断是否输入了验证码
          if (!StringUtils.hasText(loginVo.getCaptchaCode())) {
              throw new LeaseException(ResultCodeEnum.ADMIN_CAPTCHA_CODE_NOT_FOUND);
          }
      
          //2.校验验证码
          String code = redisTemplate.opsForValue().get(loginVo.getCaptchaKey());
          if (code == null) {
              throw new LeaseException(ResultCodeEnum.ADMIN_CAPTCHA_CODE_EXPIRED);
          }
      
          if (!code.equals(loginVo.getCaptchaCode().toLowerCase())) {
              throw new LeaseException(ResultCodeEnum.ADMIN_CAPTCHA_CODE_ERROR);
          }
      
          //3.校验用户是否存在
          SystemUser systemUser = systemUserMapper.selectOneByUsername(loginVo.getUsername());
      
          if (systemUser == null) {
              throw new LeaseException(ResultCodeEnum.ADMIN_ACCOUNT_NOT_EXIST_ERROR);
          }
      
          //4.校验用户是否被禁
          if (systemUser.getStatus() == BaseStatus.DISABLE) {
              throw new LeaseException(ResultCodeEnum.ADMIN_ACCOUNT_DISABLED_ERROR);
          }
      
          //5.校验用户密码
          if (!systemUser.getPassword().equals(DigestUtils.md5Hex(loginVo.getPassword()))) {
              throw new LeaseException(ResultCodeEnum.ADMIN_ACCOUNT_ERROR);
          }
      
          //6.创建并返回TOKEN
          return JwtUtil.createToken(systemUser.getId(), systemUser.getUsername());
      }
      ```
    
  - **编写Mapper层逻辑**
  
    - 在`LoginMapper`中增加如下内容
  
      ```java
      SystemUser selectOneByUsername(String username);
      ```
  
    - 在`LoginMapper.xml`中增加如下内容
  
      ```sql
      <select id="selectOneByUsername" resultType="com.atguigu.lease.model.entity.SystemUser">
          select id,
                 username,
                 password,
                 name,
                 type,
                 phone,
                 avatar_url,
                 additional_info,
                 post_id,
                 status
          from system_user
          where is_deleted = 0
            and username = #{username}
      </select>
      ```
    
  - **编写HandlerInterceptor**
  
    我们需要为所有受保护的接口增加校验JWT合法性的逻辑。具体实现如下
  
    - 在`JwtUtil`中增加`parseToken`方法，内容如下
  
      ``` java
      public static Claims parseToken(String token){
      
          if (token==null){
              throw new LeaseException(ResultCodeEnum.ADMIN_LOGIN_AUTH);
          }
      
          try{
              JwtParser jwtParser = Jwts.parserBuilder().setSigningKey(secretKey).build();
              return jwtParser.parseClaimsJws(token).getBody();
          }catch (ExpiredJwtException e){
              throw new LeaseException(ResultCodeEnum.TOKEN_EXPIRED);
          }catch (JwtException e){
              throw new LeaseException(ResultCodeEnum.TOKEN_INVALID);
          }
      }
      ```
      
    - **编写HandlerInterceptor**
    
      在**web-admin模块**中创建`com.atguigu.lease.web.admin.custom.interceptor.AuthenticationInterceptor`类，内容如下，有关`HanderInterceptor`的相关内容，可参考[官方文档](https://docs.spring.io/spring-framework/reference/web/webmvc/mvc-servlet/handlermapping-interceptor.html)。
    
      ```java
      @Component
      public class AuthenticationInterceptor implements HandlerInterceptor {
      
          @Override
          public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
              String token = request.getHeader("access-token");
              JwtUtil.parseToken(token);
              return true;
          }
      }
      ```
      
      **注意**：
      
      我们约定，前端登录后，后续请求都将JWT，放置于HTTP请求的Header中，其Header的key为`access-token`。
      
    - **注册HandlerInterceptor**
    
      在**web-admin模块**的`com.atguigu.lease.web.admin.custom.config.WebMvcConfiguration`中增加如下内容
    
      ```java
      @Autowired
      private AuthenticationInterceptor authenticationInterceptor;
      
      @Override
      public void addInterceptors(InterceptorRegistry registry) {
          registry.addInterceptor(this.authenticationInterceptor).addPathPatterns("/admin/**").excludePathPatterns("/admin/login/**");
      }
      ```
    
  - **Knife4j配置**
  
    在增加上述拦截器后，为方便继续调试其他接口，可以获取一个长期有效的Token，将其配置到Knife4j的全局参数中，如下图所示。
  
    ![](images/全局参数.png)
  
    **注意：**每个接口分组需要单独配置
    
    刷新页面，任选一个接口进行调试，会发现发送请求时会自动携带该header，如下图所示
    
    ![](images/knife4j接口认证.png)

##### 3. 获取登录用户个人信息

- **查看请求和响应的数据结构**

  - **响应的数据结构**

    查看**web-admin模块**下的`com.atguigu.lease.web.admin.vo.system.user.SystemUserInfoVo`，内容如下

    ```java
    @Schema(description = "员工基本信息")
    @Data
    public class SystemUserInfoVo {
    
        @Schema(description = "用户姓名")
        private String name;
    
        @Schema(description = "用户头像")
        private String avatarUrl;
    }
    ```

  - **请求的数据结构**

    按理说，前端若想获取当前登录用户的个人信息，需要传递当前用户的`id`到后端进行查询。但是由于请求中携带的JWT中就包含了当前登录用户的`id`，故请求个人信息时，就无需再传递`id`。

- **修改`JwtUtil`中的`parseToken`方法**

  由于需要从Jwt中获取用户`id`，因此需要为`parseToken` 方法增加返回值，如下

  ```java
  public static Claims parseToken(String token){
  
      if (token==null){
          throw new LeaseException(ResultCodeEnum.ADMIN_LOGIN_AUTH);
      }
  
      try{
          JwtParser jwtParser = Jwts.parserBuilder().setSigningKey(secretKey).build();
          return jwtParser.parseClaimsJws(token).getBody();
      }catch (ExpiredJwtException e){
          throw new LeaseException(ResultCodeEnum.TOKEN_EXPIRED);
      }catch (JwtException e){
          throw new LeaseException(ResultCodeEnum.TOKEN_INVALID);
      }
  }
  ```

- **编写ThreadLocal工具类**

  理论上我们可以在Controller方法中，使用`@RequestHeader`获取JWT，然后在进行解析，如下

  ```java
  @Operation(summary = "获取登陆用户个人信息")
  @GetMapping("info")
  public Result<SystemUserInfoVo> info(@RequestHeader("access-token") String token) {
      Claims claims = JwtUtil.parseToken(token);
      Long userId = claims.get("userId", Long.class);
      SystemUserInfoVo userInfo = service.getLoginUserInfo(userId);
      return Result.ok(userInfo);
  }
  ```

  上述代码的逻辑没有任何问题，但是这样做，JWT会被重复解析两次（一次在拦截器中，一次在该方法中）。为避免重复解析，通常会在拦截器将Token解析完毕后，将结果保存至**ThreadLocal**中，这样一来，我们便可以在整个请求的处理流程中进行访问了。

  >**ThreadLocal概述**
  >
  >ThreadLocal的主要作用是为每个使用它的线程提供一个独立的变量副本，使每个线程都可以操作自己的变量，而不会互相干扰，其用法如下图所示。
  >
  ><img src="images/ThreadLocal.drawio.png" style="zoom: 33%;" />

  在**common模块**中创建`com.atguigu.lease.common.login.LoginUserHolder`工具类

  ```java
  public class LoginUserHolder {
      public static ThreadLocal<LoginUser> threadLocal = new ThreadLocal<>();
  
      public static void setLoginUser(LoginUser loginUser) {
          threadLocal.set(loginUser);
      }
  
      public static LoginUser getLoginUser() {
          return threadLocal.get();
      }
  
      public static void clear() {
          threadLocal.remove();
      }
  }
  ```

  同时在**common模块**中创建`com.atguigu.lease.common.login.LoginUser`类

  ```java
  @Data
  @AllArgsConstructor
  public class LoginUser {
  
      private Long userId;
      private String username;
  }
  ```

- **修改`AuthenticationInterceptor`拦截器**

  ```java
  @Component
  public class AuthenticationInterceptor implements HandlerInterceptor {
  
      @Override
      public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
  
          String token = request.getHeader("access-token");
  
          Claims claims = JwtUtil.parseToken(token);
          Long userId = claims.get("userId", Long.class);
          String username = claims.get("username", String.class);
          LoginUserHolder.setLoginUser(new LoginUser(userId, username));
  
          return true;
  
      }
  
      @Override
      public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
          LoginUserHolder.clear();
      }
  }
  ```

- **编写Controller层逻辑**

  在`LoginController`中增加如下内容

  ```java
  @Operation(summary = "获取登陆用户个人信息")
  @GetMapping("info")
  public Result<SystemUserInfoVo> info() {
      SystemUserInfoVo userInfo = service.getLoginUserInfo(LoginUserHolder.getLoginUser().getUserId());
      return Result.ok(userInfo);
  }
  ```
  
- **编写Service层逻辑**

	在`LoginService`中增加如下内容

	```java
	@Override
	public SystemUserInfoVo getLoginUserInfo(Long userId) {
	    SystemUser systemUser = systemUserMapper.selectById(userId);
	    SystemUserInfoVo systemUserInfoVo = new SystemUserInfoVo();
	    systemUserInfoVo.setName(systemUser.getName());
	    systemUserInfoVo.setAvatarUrl(systemUser.getAvatarUrl());
	    return systemUserInfoVo;
	}

## 7.3 后台管理系统前后端联调

### 7.3.1 启动后端项目

启动后端项目，供前端调用接口。

### 7.3.2 启动前端项目

#### 7.3.2.1 安装Node和npm

1. **部署Node和npm**

   Node和npm的部署比较简单，拿到安装包后按照安装向导操作即可。

2. **配置npm国内镜像**

   为加速npm下载依赖，可以为npm配置国内镜像，在终端执行以下命令为npm配置阿里云镜像。

   ```bash
   npm config set registry https://registry.npmmirror.com
   ```

   若想取消上述配置，可在终端执行以下命令删除镜像，删除后将恢复默认配置。

   ```bash
   npm config delete registry
   ```

#### 7.3.2.2 启动前端项目

1. **导入前端项目**

   将后台管理系统的前端项目（**rentHouseAdmin**）导入`vscode`或者`WebStorm`，打开终端，在项目根目录执行以下命令，安装所需依赖

   ```bash
   npm install
   ```

2. **配置后端接口地址**

   修改项目根目录下的`.env.development`文件中的`VITE_APP_BASE_URL`变量的值为后端接口的地址，此处改为`http://localhost:8080`即可,如下

   ```ini
   VITE_APP_BASE_URL='http://localhost:8080'
   ```

   **注意**：

   上述主机名和端口号需要根据实际情况进行修改。

3. **启动前端项目**

   上述配置完成之后，便可执行以下命令启动前端项目了

   ```bash
   npm run dev
   ```

4. **访问前端项目**

   在浏览器中访问前端项目，并逐个测试每个页面的相关功能。

## 7.4 移动端后端开发

### 7.4.1 项目初始配置

#### 7.4.1.1 SpringBoot配置

**1. 创建application.yml文件**

在**web-app模块**的`src/main/resources`目录下创建`application.yml`配置文件，内容如下：

```yaml
server:
  port: 8081
```

**2. 创建SpringBoot启动类**

在**web-app模块**下创建`com.atguigu.lease.AppWebApplication`类，内容如下：

```java
@SpringBootApplication
public class AppWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(AppWebApplication.class);
    }
}
```

#### 7.4.1.2 Mybatis-Plus配置

在web-admin模块的application.yml文件增加如下内容：

```yaml
spring:
  datasource:
    type: com.zaxxer.hikari.HikariDataSource
    url: jdbc:mysql://<hostname>:<port>/<database>?useUnicode=true&characterEncoding=utf-8&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=GMT%2b8
    username: <username>
    password: <password>
    hikari:
      connection-test-query: SELECT 1 # 自动检测连接
      connection-timeout: 60000 #数据库连接超时时间,默认30秒
      idle-timeout: 500000 #空闲连接存活最大时间，默认600000（10分钟）
      max-lifetime: 540000 #此属性控制池中连接的最长生命周期，值0表示无限生命周期，默认1800000即30分钟
      maximum-pool-size: 12 #连接池最大连接数，默认是10
      minimum-idle: 10 #最小空闲连接数量
      pool-name: SPHHikariPool # 连接池名称
  jackson:
      time-zone: GMT+8

#用于打印框架生成的sql语句，便于调试
mybatis-plus:
  configuration:
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
```

**注意**：需根据实际情况修改`hostname`、`port`、`database`、`username`、`password`。

#### 7.4.1.3 Knife4j配置

**1. 配置类**

在**web-app模块**下创建`com.atguigu.lease.web.app.custom.config.Knife4jConfiguration`类，内容如下：

```java
@Configuration
public class Knife4jConfiguration {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("APP接口")
                        .version("1.0")
                        .description("用户端APP接口")
                        .termsOfService("http://doc.xiaominfo.com")
                        .license(new License().name("Apache 2.0")
                                .url("http://doc.xiaominfo.com")));
    }
    

    @Bean
    public GroupedOpenApi loginAPI() {
        return GroupedOpenApi.builder().group("登录信息").
                pathsToMatch("/app/login/**", "/app/info").
                build();
    }

    @Bean
    public GroupedOpenApi personAPI() {
        return GroupedOpenApi.builder().group("个人信息").
                pathsToMatch(
                        "/app/history/**",
                        "/app/appointment/**",
                        "/app/agreement/**"
                ).
                build();
    }

    @Bean
    public GroupedOpenApi lookForRoomAPI() {
        return GroupedOpenApi.builder().group("找房信息").
                pathsToMatch(
                        "/app/apartment/**",
                        "/app/room/**",
                        "/app/payment/**",
                        "/app/region/**",
                        "/app/term/**"
                ).
                build();
    }
}
```

**2. application.yml配置文件**

在application.yml文件中增加如下配置：

```yml
springdoc:
  default-flat-param-object: true
```

#### 7.2.1.4 导入基础代码

导入的代码和目标位置如下：

| 导入代码    | 模块    | 包名/路径                                | 说明 |
| ----------- | ------- | ---------------------------------------- | ---- |
| mapper接口  | web-app | `com.atguigu.lease.web.app.mapper`       | 略   |
| mapper xml  | web-app | src/main/resources/mapper                | 略   |
| service     | web-app | `com.atguigu.lease.web.app.service`      | 略   |
| serviceImpl | web-app | `com.atguigu.lease.web.app.service.impl` | 略   |

#### 7.2.1.5 导入接口定义代码

需要导入的代码和目标位置如下：

| 导入代码   | 模块    | 包名/路径                              | 说明                                                |
| ---------- | ------- | -------------------------------------- | --------------------------------------------------- |
| controller | web-app | `com.atguigu.lease.web.app.controller` | 略                                                  |
| vo         | web-app | `com.atguigu.lease.web.app.vo`         | View Object，用于封装或定义接口接受及返回的数据结构 |

#### 7.2.1.6 启动项目

由于**common模块**中配置了**MinioClient**这个Bean，并且**web-app模块**依赖于**common模块**，因此在启动**AppWebApplication**时，SpringBoot会创建一个MinioClient实例，但是由于**web-app模块**的application.yml文件中并未提供MinioClient所需的参数（**web-app模块**暂时不需要使用MinioClient），因此MinioClient实例的创建会失败。

为解决该问题，可以为MinioClient的配置类增加一个条件注解`@ConditionalOnProperty`，如下，该注解表达的含义是只有当`minio.endpoint`属性存在时，该配置类才会生效。

```java
@Configuration
@EnableConfigurationProperties(MinioProperties.class)
@ConditionalOnProperty(name = "minio.endpoint")
public class MinioConfiguration {

    @Autowired
    private MinioProperties properties;

    @Bean
    public MinioClient minioClient() {
        return MinioClient.builder().endpoint(properties.getEndpoint()).credentials(properties.getAccessKey(), properties.getSecretKey()).build();
    }
}
```

完成上述配置后，便可启动SpringBoot项目，并访问接口文档了，Knife4j文档的url为：http://localhost:8081/doc.html

### 7.4.2 登录管理

#### 7.4.2.1 登陆流程

移动端的具体登录流程如下图所示

<img src="images/移动端登陆流程.drawio.svg" style="zoom:50%;" />

根据上述登录流程，可分析出，登录管理共需三个接口，分别是**获取短信验证码**、**登录**、**查询登录用户的个人信息**。除此之外，同样需要编写`HandlerInterceptor`来为所有受保护的接口增加验证JWT的逻辑。

#### 7.4.2.2 接口开发

首先在`LoginController`中注入`LoginService`，如下

```java
@RestController
@Tag(name = "登录管理")
@RequestMapping("/app/")
public class LoginController {

    @Autowired
    private LoginService service;
}
```

##### 1. 获取短信验证码

该接口需向登录手机号码发送短信验证码，各大云服务厂商都提供短信服务，本项目使用阿里云完成短信验证码功能，下面介绍具体配置。

- **配置短信服务**

  - **开通短信服务**

    - 在[阿里云官网](https://www.aliyun.com)，注册阿里云账号，并按照指引，完成实名认证（不认证，无法购买服务）

    - 找到[短信服务](https://www.aliyun.com/product/sms)，选择**免费开通**

    - 进入[短信服务控制台](https://dysms.console.aliyun.com/overview)，选择**快速学习和测试**

    - 找到**发送测试**下的**API发送测试**，绑定测试用的手机号（只有绑定的手机号码才能收到测试短信），然后配置短信签名和短信模版，这里选择**[专用]测试签名/模版**。

      ![](images/短信服务测试.png)

  - **创建AccessKey**

    云账号 AccessKey 是访问阿里云 API 的密钥，没有AccessKey无法调用短信服务。点击页面右上角的头像，选择**AccessKey管理**，然后**创建AccessKey**。

    <img src="images/AccessKey管理.png" alt="image-20230808104345383" style="zoom: 45%;" />

- **配置所需依赖**

  如需调用阿里云的短信服务，需使用其提供的SDK，具体可参考[官方文档](https://next.api.aliyun.com/api-tools/sdk/Dysmsapi?spm=a2c4g.215759.0.0.43e6807dDRAZVz&version=2017-05-25&language=java-tea&tab=primer-doc#doc-summary)。

  在**common模块**的pom.xml文件中增加如下内容

  ```xml
  <dependency>
      <groupId>com.aliyun</groupId>
      <artifactId>dysmsapi20170525</artifactId>
  </dependency>
  ```

- **配置发送短信客户端**

  - 在`application.yml`中增加如下内容

    ```yml
    aliyun:
      sms:
        access-key-id: <access-key-id>
        access-key-secret: <access-key-secret>
        endpoint: dysmsapi.aliyuncs.com
    ```

    **注意**：

    上述`access-key-id`、`access-key-secret`需根据实际情况进行修改。

  - 在**common模块**中创建`com.atguigu.lease.common.sms.AliyunSMSProperties`类，内容如下

    ```java
    @Data
    @ConfigurationProperties(prefix = "aliyun.sms")
    public class AliyunSMSProperties {
    
        private String accessKeyId;
    
        private String accessKeySecret;
    
        private String endpoint;
    }
    ```

  - 在**common模块**中创建`com.atguigu.lease.common.sms.AliyunSmsConfiguration`类，内容如下

    ```java
    @Configuration
    @EnableConfigurationProperties(AliyunSMSProperties.class)
    @ConditionalOnProperty(name = "aliyun.sms.endpoint")
    public class AliyunSMSConfiguration {
    
        @Autowired
        private AliyunSMSProperties properties;
    
        @Bean
        public Client smsClient() {
            Config config = new Config();
            config.setAccessKeyId(properties.getAccessKeyId());
            config.setAccessKeySecret(properties.getAccessKeySecret());
            config.setEndpoint(properties.getEndpoint());
            try {
                return new Client(config);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
    
        }
    }
    ```

- **配置Redis连接参数**

  ```yaml
  spring: 
    data:
      redis:
        host: 192.168.10.101
        port: 6379
        database: 0
  ```

- **编写Controller层逻辑**

  在`LoginController`中增加如下内容

  ```java
  @GetMapping("login/getCode")
  @Operation(summary = "获取短信验证码")
  public Result getCode(@RequestParam String phone) {
      service.getSMSCode(phone);
      return Result.ok();
  }
  ```

- **编写Service层逻辑**

  - 编写发送短信逻辑

    - 在`SmsService`中增加如下内容

      ```java
      void sendCode(String phone, String verifyCode);
      ```

    - 在`SmsServiceImpl`中增加如下内容

      ```java
      @Override
      public void sendCode(String phone, String code) {
      
          SendSmsRequest smsRequest = new SendSmsRequest();
          smsRequest.setPhoneNumbers(phone);
          smsRequest.setSignName("阿里云短信测试");
          smsRequest.setTemplateCode("SMS_154950909");
          smsRequest.setTemplateParam("{\"code\":\"" + code + "\"}");
          try {
              client.sendSms(smsRequest);
          } catch (Exception e) {
              throw new RuntimeException(e);
          }
      }
      ```

  - 编写生成随机验证码逻辑

    在**common模块**中创建`com.atguigu.lease.common.utils.VerifyCodeUtil`类，内容如下

    ```java
    public class VerifyCodeUtil {
        public static String getVerifyCode(int length) {
            StringBuilder builder = new StringBuilder();
            Random random = new Random();
            for (int i = 0; i < length; i++) {
                builder.append(random.nextInt(10));
            }
            return builder.toString();
        }
    }
    ```
  
  - 编写获取短信验证码逻辑
  
    - 在`LoginServcie`中增加如下内容
  
      ```java
      void getSMSCode(String phone);
      ```
  
    - 在`LoginServiceImpl`中增加如下内容
  
      ```java
      @Override
      public void getSMSCode(String phone) {
      
          //1. 检查手机号码是否为空
          if (!StringUtils.hasText(phone)) {
              throw new LeaseException(ResultCodeEnum.APP_LOGIN_PHONE_EMPTY);
          }
      
          //2. 检查Redis中是否已经存在该手机号码的key
          String key = RedisConstant.APP_LOGIN_PREFIX + phone;
          boolean hasKey = redisTemplate.hasKey(key);
          if (hasKey) {
              //若存在，则检查其存在的时间
              Long expire = redisTemplate.getExpire(key, TimeUnit.SECONDS);
              if (RedisConstant.APP_LOGIN_CODE_TTL_SEC - expire < RedisConstant.APP_LOGIN_CODE_RESEND_TIME_SEC) {
                  //若存在时间不足一分钟，响应发送过于频繁
                  throw new LeaseException(ResultCodeEnum.APP_SEND_SMS_TOO_OFTEN);
              }
          }
      
          //3.发送短信，并将验证码存入Redis
          String verifyCode = VerifyCodeUtil.getVerifyCode(6);
          smsService.sendCode(phone, verifyCode);
          redisTemplate.opsForValue().set(key, verifyCode, RedisConstant.APP_LOGIN_CODE_TTL_SEC, TimeUnit.SECONDS);
      }
      ```
  
      **注意**：
  
      需要注意防止频繁发送短信。


##### 2. 登录和注册接口

- **登录注册校验逻辑**
  
  - 前端发送手机号码`phone`和接收到的短信验证码`code`到后端。
  - 首先校验`phone`和`code`是否为空，若为空，直接响应`手机号码为空`或者`验证码为空`，若不为空则进入下步判断。
  - 根据`phone`从Redis中查询之前保存的验证码，若查询结果为空，则直接响应`验证码已过期` ，若不为空则进入下一步判断。
  - 比较前端发送的验证码和从Redis中查询出的验证码，若不同，则直接响应`验证码错误`，若相同则进入下一步判断。
  - 使用`phone`从数据库中查询用户信息，若查询结果为空，则创建新用户，并将用户保存至数据库，然后进入下一步判断。
  - 判断用户是否被禁用，若被禁，则直接响应`账号被禁用`，否则进入下一步。
  - 创建JWT并响应给前端。
  
- **接口实现**

  - **编写Controller层逻辑**

    在`LoginController`中增加如下内容

    ```java
    @PostMapping("login")
    @Operation(summary = "登录")
    public Result<String> login(LoginVo loginVo) {
        String token = service.login(loginVo);
        return Result.ok(token);
    }
    ```

  - **编写Service层逻辑**

    - 在`LoginService`中增加如下内容

      ```java
      String login(LoginVo loginVo);
      ```

    - 在`LoginServiceImpl`总增加如下内容

      ```java
      @Override
      public String login(LoginVo loginVo) {
      
          //1.判断手机号码和验证码是否为空
          if (!StringUtils.hasText(loginVo.getPhone())) {
              throw new LeaseException(ResultCodeEnum.APP_LOGIN_PHONE_EMPTY);
          }
      
          if (!StringUtils.hasText(loginVo.getCode())) {
              throw new LeaseException(ResultCodeEnum.APP_LOGIN_CODE_EMPTY);
          }
      
          //2.校验验证码
          String key = RedisConstant.APP_LOGIN_PREFIX + loginVo.getPhone();
          String code = redisTemplate.opsForValue().get(key);
          if (code == null) {
              throw new LeaseException(ResultCodeEnum.APP_LOGIN_CODE_EXPIRED);
          }
      
          if (!code.equals(loginVo.getCode())) {
              throw new LeaseException(ResultCodeEnum.APP_LOGIN_CODE_ERROR);
          }
      
          //3.判断用户是否存在,不存在则注册（创建用户）
          LambdaQueryWrapper<UserInfo> queryWrapper = new LambdaQueryWrapper<>();
          queryWrapper.eq(UserInfo::getPhone, loginVo.getPhone());
          UserInfo userInfo = userInfoService.getOne(queryWrapper);
          if (userInfo == null) {
              userInfo = new UserInfo();
              userInfo.setPhone(loginVo.getPhone());
              userInfo.setStatus(BaseStatus.ENABLE);
              userInfo.setNickname("用户-"+loginVo.getPhone().substring(6));
              userInfoService.save(userInfo);
          }
      
          //4.判断用户是否被禁
          if (userInfo.getStatus().equals(BaseStatus.DISABLE)) {
              throw new LeaseException(ResultCodeEnum.APP_ACCOUNT_DISABLED_ERROR);
          }
      
          //5.创建并返回TOKEN
          return JwtUtil.createToken(userInfo.getId(), loginVo.getPhone());
      }
      ```

  - **编写HandlerInterceptor**

    - **编写AuthenticationInterceptor**

      在**web-app模块**创建`com.atguigu.lease.web.app.custom.interceptor.AuthenticationInterceptor`，内容如下

      ```java
      @Component
      public class AuthenticationInterceptor implements HandlerInterceptor {
      
          @Override
          public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
              String token = request.getHeader("access-token");
      
              Claims claims = JwtUtil.parseToken(token);
              Long userId = claims.get("userId", Long.class);
              String username = claims.get("username", String.class);
              LoginUserHolder.setLoginUser(new LoginUser(userId, username));
      
              return true;
          }
      
          @Override
          public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
              LoginUserHolder.clear();
          }
      }
      ```
      
    - **注册AuthenticationInterceptor**
    
      在**web-app模块**创建`com.atguigu.lease.web.app.custom.config.WebMvcConfiguration`，内容如下
    
      ```java
      @Configuration
      public class WebMvcConfiguration implements WebMvcConfigurer {
      
          @Autowired
          private AuthenticationInterceptor authenticationInterceptor;
      
          @Override
          public void addInterceptors(InterceptorRegistry registry) {
              registry.addInterceptor(this.authenticationInterceptor).addPathPatterns("/app/**").excludePathPatterns("/app/login/**");
          }
      }
      ```
      
  
- **Knife4j增加认证相关配置**

  在增加上述拦截器后，为方便继续调试其他接口，可以获取一个长期有效的Token，将其配置到Knife4j的全局参数中。

##### 3.查询登录用户的个人信息

- **查看响应数据结构**

  查看**web-app模块**下的`com.atguigu.lease.web.app.vo.user.UserInfoVo`，内容如下

  ```java
  @Schema(description = "用户基本信息")
  @Data
  @AllArgsConstructor
  public class UserInfoVo {
  
      @Schema(description = "用户昵称")
      private String nickname;
  
      @Schema(description = "用户头像")
      private String avatarUrl;
  }
  ```

- **编写Controller层逻辑**

  在`LoginController`中增加如下内容

  ```java
  @GetMapping("info")
  @Operation(summary = "获取登录用户信息")
  public Result<UserInfoVo> info() {
      UserInfoVo info = service.getUserInfoById(LoginUserHolder.getLoginUser().getUserId());
      return Result.ok(info);
  }
  ```

- **编写Service层逻辑**

  - 在`LoginService`中增加如下内容

    ```java
    UserInfoVo getUserInfoId(Long id);
    ```

  - 在`LoginServiceImpl`中增加如下内容

    ```java
    @Override
    public UserInfoVo getUserInfoId(Long id) {
        UserInfo userInfo = userInfoService.getById(id);
        return new UserInfoVo(userInfo.getNickname(), userInfo.getAvatarUrl());
    }
    ```

### 7.4.3 找房

#### 7.4.3.1 地区信息

对于找房模块，地区信息共需三个接口，分别是**查询省份列表**、**根据省份ID查询城市列表**、**根据城市ID查询区县列表**，具体实现如下

在`RegionController`中增加如下内容

```java
@Tag(name = "地区信息")
@RestController
@RequestMapping("/app/region")
public class RegionController {

    @Autowired
    private ProvinceInfoService provinceInfoService;

    @Autowired
    private CityInfoService cityInfoService;

    @Autowired
    private DistrictInfoService districtInfoService;

    @Operation(summary="查询省份信息列表")
    @GetMapping("province/list")
    public Result<List<ProvinceInfo>> listProvince(){
        List<ProvinceInfo> list = provinceInfoService.list();
        return Result.ok(list);
    }

    @Operation(summary="根据省份id查询城市信息列表")
    @GetMapping("city/listByProvinceId")
    public Result<List<CityInfo>> listCityInfoByProvinceId(@RequestParam Long id){
        LambdaQueryWrapper<CityInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CityInfo::getProvinceId,id);
        List<CityInfo> list = cityInfoService.list(queryWrapper);
        return Result.ok(list);
    }

    @GetMapping("district/listByCityId")
    @Operation(summary="根据城市id查询区县信息")
    public Result<List<DistrictInfo>> listDistrictInfoByCityId(@RequestParam Long id){
        LambdaQueryWrapper<DistrictInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(DistrictInfo::getCityId,id);
        List<DistrictInfo> list = districtInfoService.list(queryWrapper);
        return Result.ok(list);
    }
}
```

#### 7.4.3.2 支付方式

对于找房模块，支付方式共需一个接口，即**获取全部支付方式列表**，具体实现如下

在`PaymentTypeController`中增加如下内容

```java
@Tag(name = "支付方式接口")
@RestController
@RequestMapping("/app/payment")
public class PaymentTypeController {

    @Autowired
    private PaymentTypeService service;

    @Operation(summary = "获取全部支付方式列表")
    @GetMapping("list")
    public Result<List<PaymentType>> list() {
        List<PaymentType> list = service.list();
        return Result.ok(list);
    }
}
```

#### 7.4.3.4 房间信息

房间信息共需三个接口，分别是**根据条件分页查询房间列表**、**根据ID查询房间详细信息**、**根据公寓ID分页查询房间列表**，下面逐一实现

首先在`RoomController`中注入`RoomInfoService`，如下

```java
@Tag(name = "房间信息")
@RestController
@RequestMapping("/app/room")
public class RoomController {

    @Autowired
    RoomInfoService roomInfoService;
}
```

##### 1. 根据条件分页查询房间列表

- **查看请求和响应的数据结构**

  - **请求数据结构**

    - `current`和`size`为分页相关参数，分别表示**当前所处页面**和**每个页面的记录数**。

    - `RoomQueryVo`为房间的查询条件，详细结构如下：

      ```java
      @Data
      @Schema(description = "房间查询实体")
      public class RoomQueryVo {
      
          @Schema(description = "省份Id")
          private Long provinceId;
      
          @Schema(description = "城市Id")
          private Long cityId;
      
          @Schema(description = "区域Id")
          private Long districtId;
      
          @Schema(description = "最小租金")
          private BigDecimal minRent;
      
          @Schema(description = "最大租金")
          private BigDecimal maxRent;
      
          @Schema(description = "支付方式")
          private Long paymentTypeId;
      
          @Schema(description = "价格排序方式", allowableValues = {"desc", "asc"})
          private String orderType;
      
      }
      ```

  - **响应数据结构**

    单个房间信息记录可查看`com.atguigu.lease.web.app.vo.room.RoomItemVo`，内容如下：

    ```java
    @Schema(description = "APP房间列表实体")
    @Data
    public class RoomItemVo {
    
        @Schema(description = "房间id")
        private Long id;
    
        @Schema(description = "房间号")
        private String roomNumber;
    
        @Schema(description = "租金（元/月）")
        private BigDecimal rent;
    
        @Schema(description = "房间图片列表")
        private List<GraphVo> graphVoList;
    
        @Schema(description = "房间标签列表")
        private List<LabelInfo> labelInfoList;
    
        @Schema(description = "房间所属公寓信息")
        private ApartmentInfo apartmentInfo;
    }
    ```

- **编写Controller层逻辑**

  在`RoomController`中增加如下内容

  ```java
  @Operation(summary = "分页查询房间列表")
  @GetMapping("pageItem")
  public Result<IPage<RoomItemVo>> pageItem(@RequestParam long current, @RequestParam long size, RoomQueryVo queryVo) {
      Page<RoomItemVo> page = new Page<>(current, size);
      IPage<RoomItemVo> list = roomInfoService.pageRoomItemByQuery(page, queryVo);
      return Result.ok(list);
  }
  ```

- **编写Service层逻辑**

  - 在`RoomInfoService`中增加如下内容

    ```java
    IPage<RoomItemVo> pageRoomItemByQuery(Page<RoomItemVo> page, RoomQueryVo queryVo);
    ```

  - 在`RoomInfoServiceImpl`中增加如下内容

    ```java
    @Override
    public IPage<RoomItemVo> pageRoomItemByQuery(Page<RoomItemVo> page, RoomQueryVo queryVo) {
        return roomInfoMapper.pageRoomItemByQuery(page, queryVo);
    }
    ```

- **编写Mapper层逻辑**

  - 在`RoomInfoMapper`中增加如下内容

    ```java
    IPage<RoomItemVo> pageRoomItemByQuery(Page<RoomItemVo> page, RoomQueryVo queryVo);
    ```

  - 在`RoomInfoMapper`中增加如下内容

    ```xml
    <!-- result map -->
    <resultMap id="RoomItemVoMap" type="com.atguigu.lease.web.app.vo.room.RoomItemVo" autoMapping="true">
        <id column="id" property="id"/>
        <!--映射公寓信息-->
        <association property="apartmentInfo" javaType="com.atguigu.lease.model.entity.ApartmentInfo"
                     autoMapping="true">
            <id column="id" property="id"/>
        </association>
        <!--映射图片列表-->
        <collection property="graphVoList" ofType="com.atguigu.lease.web.app.vo.graph.GraphVo"
                    select="selectGraphVoListByRoomId" column="id"/>
        <!--映射标签列表-->
        <collection property="labelInfoList" ofType="com.atguigu.lease.model.entity.LabelInfo"
                    select="selectLabelInfoListByRoomId" column="id"/>
    </resultMap>
    
    <!-- 根据条件查询房间列表 -->
    <select id="pageItem" resultMap="RoomItemVoMap">
        select
            ri.id,
            ri.room_number,
            ri.rent,
            ai.id apartment_id,
            ai.name,
            ai.introduction,
            ai.district_id,
            ai.district_name,
            ai.city_id,
            ai.city_name,
            ai.province_id,
            ai.province_name,
            ai.address_detail,
            ai.latitude,
            ai.longitude,
            ai.phone,
            ai.is_release
        from room_info ri
        left join apartment_info ai on ri.apartment_id = ai.id and ai.is_deleted = 0
        <where>
            ri.is_deleted = 0
            and ri.is_release = 1
            and ri.id not in(
                select room_id
                from lease_agreement
                where is_deleted = 0
                and status in(2,5))
            <if test="queryVo.provinceId != null">
                and ai.province_id = #{queryVo.provinceId}
            </if>
            <if test="queryVo.cityId != null">
                and ai.city_id = #{queryVo.cityId}
            </if>
            <if test="queryVo.districtId != null">
                and ai.district_id = #{queryVo.districtId}
            </if>
            <if test="queryVo.minRent != null and queryVo.maxRent != null">
                and (ri.rent &gt;= #{queryVo.minRent} and ri.rent &lt;= #{queryVo.maxRent})
            </if>
            <if test="queryVo.paymentTypeId != null">
                and ri.id in (
                select
                room_id
                from room_payment_type
                where is_deleted = 0
                and payment_type_id = #{queryVo.paymentTypeId}
                )
            </if>
        </where>
        <if test="queryVo.orderType == 'desc' or queryVo.orderType == 'asc'">
            order by ri.rent ${queryVo.orderType}
        </if>
    </select>
    
    <!-- 根据房间ID查询图片列表 -->
    <select id="selectGraphVoListByRoomId" resultType="com.atguigu.lease.web.app.vo.graph.GraphVo">
        select id,
               name,
               item_type,
               item_id,
               url
        from graph_info
        where is_deleted = 0
          and item_type = 2
          and item_id = #{id}
    </select>
    
    <!-- 根据公寓ID查询标签列表 -->
    <select id="selectLabelInfoListByRoomId" resultType="com.atguigu.lease.model.entity.LabelInfo">
        select id,
               type,
               name
        from label_info
        where is_deleted = 0
          and id in (select label_id
                     from room_label
                     where is_deleted = 0
                       and room_id = #{id})
    </select>
    ```
    
    **知识点**：
    
    - **xml文件`<`和`>`的转义**
    
      由于xml文件中的`<`和`>`是特殊符号，需要转义处理。
    
      | 原符号 | 转义符号 |
      | ------ | -------- |
      | `<`    | `&lt;`   |
      | `>`    | `&gt;`   |
    
    - **Mybatis-Plus分页插件注意事项**
    
      使用Mybatis-Plus的分页插件进行分页查询时，如果结果需要使用`<collection>`进行映射，只能使用**[嵌套查询（Nested Select for Collection）](https://mybatis.org/mybatis-3/sqlmap-xml.html#nested-select-for-collection)**，而不能使用**[嵌套结果映射（Nested Results for Collection）](https://mybatis.org/mybatis-3/sqlmap-xml.html#nested-results-for-collection)**。
    
      **嵌套查询**和**嵌套结果映射**是Collection映射的两种方式，下面通过一个案例进行介绍
    
      例如有`room_info`和`graph_info`两张表，其关系为一对多，如下
    
      <img src="images/mybatis-一对多.drawio.svg" style="zoom:50%;" />
    
      现需要查询房间列表及其图片信息，期望返回的结果如下
    
      ```json
      [
          {
              "id": 1,
              "number": 201,
              "rent": 2000,
              "graphList": [
                  {
                      "id": 1,
                      "url": "http://",
                      "roomId": 1
                  },
                  {
                      "id": 2,
                      "url": "http://",
                      "roomId": 1
                  }
              ]
          },
          {
              "id": 2,
              "number": 202,
              "rent": 3000,
              "graphList": [
                  {
                      "id": 3,
                      "url": "http://",
                      "roomId": 2
                  },
                  {
                      "id": 4,
                      "url": "http://",
                      "roomId": 2
                  }
              ]
          }
      ]
      ```
      
      为得到上述结果，可使用以下两种方式
      
      - **嵌套结果映射**
      
        ```xml
        <select id="selectRoomPage" resultMap="RoomPageMap">
            select ri.id room_id,
                   ri.number,
                   ri.rent,
            	   gi.id graph_id,
                   gi.url,
                   gi.room_id
            from room_info ri
           	left join graph_info gi on ri.id=gi.room_id
        </select>
        
        <resultMap id="RoomPageMap" type="RoomInfoVo" autoMapping="true">
            <id column="room_id" property="id"/>
            <collection property="graphInfoList" ofType="GraphInfo" autoMapping="true">
                <id column="graph_id" property="id"/>
            </collection>
        </resultMap>
        ```
      
        这种方式的执行原理如下图所示
      
        <img src="images/mybatis-嵌套结果映射.drawio.svg" style="zoom:50%;" />
      
      - **嵌套查询**
      
        ```xml
        <select id="selectRoomPage" resultMap="RoomPageMap">
            select id,
                   number,
                   rent
            from room_info
        </select>
        
        <resultMap id="RoomPageMap" type="RoomInfoVo" autoMapping="true">
            <id column="id" property="id"/>
            <collection property="graphInfoList" ofType="GraphInfo" select="selectGraphByRoomId" 				 	column="id"/>
        </resultMap>
        
        <select id="selectGraphByRoomId" resultType="GraphInfo">
            select id,
                   url,
            	   room_id
            from graph_info
            where room_id = #{id}
        </select>
        ```
      
        这种方法使用两个独立的查询语句来获取一对多关系的数据。首先，Mybatis会执行主查询来获取`room_info`列表，然后对于每个`room_info`，Mybatis都会执行一次子查询来获取其对应的`graph_info`。
      
        <img src="images/mybatis-嵌套查询.drawio.svg" style="zoom:50%;" />
      
      若现在使用MybatisPlus的分页插件进行分页查询，假如查询的内容是第**1**页，每页**2**条记录，则上述两种方式的查询结果分别是
      
      - **嵌套结果映射**
      
        <img src="images/mybatis-分页查询-嵌套结果映射.drawio.svg" style="zoom:50%;" />
      
      - **嵌套查询**
      
        <img src="images/mybatis-分页查询-嵌套查询.drawio.svg" style="zoom:50%;" />
      
      显然**嵌套结果映射**的分页逻辑是存在问题的。

##### 2. 根据ID查询房间详细信息

- **查看响应数据结构**

  查看**web-app模块**下的`com.atguigu.lease.web.app.vo.room.RoomDetailVo`，内容如下

  ```java
  @Data
  @Schema(description = "APP房间详情")
  public class RoomDetailVo extends RoomInfo {
  
      @Schema(description = "所属公寓信息")
      private ApartmentItemVo apartmentItemVo;
  
      @Schema(description = "图片列表")
      private List<GraphVo> graphVoList;
  
      @Schema(description = "属性信息列表")
      private List<AttrValueVo> attrValueVoList;
  
      @Schema(description = "配套信息列表")
      private List<FacilityInfo> facilityInfoList;
  
      @Schema(description = "标签信息列表")
      private List<LabelInfo> labelInfoList;
  
      @Schema(description = "支付方式列表")
      private List<PaymentType> paymentTypeList;
  
      @Schema(description = "杂费列表")
      private List<FeeValueVo> feeValueVoList;
  
      @Schema(description = "租期列表")
      private List<LeaseTerm> leaseTermList;
  
  }
  ```
  
- **编写Controller层逻辑**

  在`RoomController`中增加如下内容

  ```java
  @Operation(summary = "根据id获取房间的详细信息")
  @GetMapping("getDetailById")
  public Result<RoomDetailVo> getDetailById(@RequestParam Long id) {
      RoomDetailVo roomInfo = service.getDetailById(id);
      return Result.ok(roomInfo);
  }
  ```

- **编写查询房间信息逻辑**

  - **编写Service层逻辑**

    - 在`RoomInfoService`中增加如下内容

      ```java
      RoomDetailVo getDetailById(Long id);
      ```

    - 在`RoomInfoServiceImpl`中增加如下内容

      ```java
      @Override
      public RoomDetailVo getDetailById(Long id) {
          //1.查询房间信息
          RoomInfo roomInfo = roomInfoMapper.selectById(id);
          if (roomInfo == null) {
              return null;
          }
          //2.查询图片
          List<GraphVo> graphVoList = graphInfoMapper.selectListByItemTypeAndId(ItemType.ROOM, id);
          //3.查询租期
          List<LeaseTerm> leaseTermList = leaseTermMapper.selectListByRoomId(id);
          //4.查询配套
          List<FacilityInfo> facilityInfoList = facilityInfoMapper.selectListByRoomId(id);
          //5.查询标签
          List<LabelInfo> labelInfoList = labelInfoMapper.selectListByRoomId(id);
          //6.查询支付方式
          List<PaymentType> paymentTypeList = paymentTypeMapper.selectListByRoomId(id);
          //7.查询基本属性
          List<AttrValueVo> attrValueVoList = attrValueMapper.selectListByRoomId(id);
          //8.查询杂费信息
          List<FeeValueVo> feeValueVoList = feeValueMapper.selectListByApartmentId(roomInfo.getApartmentId());
          //9.查询公寓信息
          ApartmentItemVo apartmentItemVo = apartmentInfoService.selectApartmentItemVoById(roomInfo.getApartmentId());
      
          RoomDetailVo roomDetailVo = new RoomDetailVo();
          BeanUtils.copyProperties(roomInfo, roomDetailVo);
      
          roomDetailVo.setApartmentItemVo(apartmentItemVo);
          roomDetailVo.setGraphVoList(graphVoList);
          roomDetailVo.setAttrValueVoList(attrValueVoList);
          roomDetailVo.setFacilityInfoList(facilityInfoList);
          roomDetailVo.setLabelInfoList(labelInfoList);
          roomDetailVo.setPaymentTypeList(paymentTypeList);
          roomDetailVo.setFeeValueVoList(feeValueVoList);
          roomDetailVo.setLeaseTermList(leaseTermList);
      
          return roomDetailVo;
      }
      ```

  - **编写Mapper层逻辑**

    - **编写查询房间图片逻辑**

      - 在`GraphInfoMapper`中增加如下内容

        ```java
        List<GraphVo> selectListByItemTypeAndId(ItemType itemType, Long id);
        ```

      - 在`GraphInfoMapper.xml`增加如下内容

        ```xml
        <select id="selectListByItemTypeAndId" resultType="com.atguigu.lease.web.app.vo.graph.GraphVo">
            select name,
                   url
            from graph_info
            where is_deleted = 0
              and item_type = #{itemType}
              and item_id = #{id}
        </select>
        ```
    
    - **编写查询房间可选租期逻辑**

      - 在`LeaseTermMapper`中增加如下内容

        ```java
        List<LeaseTerm> selectListByRoomId(Long id);
        ```
    
      - 在`LeaseTermMapper.xml`中增加如下内容

        ```xml
        <select id="selectListByRoomId" resultType="com.atguigu.lease.model.entity.LeaseTerm">
            select id,
                   month_count,
                   unit
            from lease_term
            where is_deleted = 0
              and id in (select lease_term_id
                         from room_lease_term
                         where is_deleted = 0
                           and room_id = #{id})
        </select>
        ```
    
    - **编写查询房间配套逻辑**
    
      - 在`FacilityInfoMapper`中增加如下内容
    
        ```java
        List<FacilityInfo> selectListByRoomId(Long id);
        ```
    
      - 在`FacilityInfoMapper.xml`中增加如下内容
    
        ```xml
        <select id="selectListByRoomId" resultType="com.atguigu.lease.model.entity.FacilityInfo">
            select id,
                   type,
                   name,
                   icon
            from facility_info
            where is_deleted = 0
              and id in (select facility_id
                         from room_facility
                         where is_deleted = 0
                           and room_id = #{id})
        </select>
        ```
    
    - **编写查询房间标签逻辑**
    
      - 在`LabelInfoMapper`中增加如下内容
    
        ```java
        List<LabelInfo> selectListByRoomId(Long id);
        ```
    
      - 在`LabelInfoMapper.xml`中增加如下内容
    
        ```xml
        <select id="selectListByRoomId" resultType="com.atguigu.lease.model.entity.LabelInfo">
            select id,
                   type,
                   name
            from label_info
            where is_deleted = 0
              and id in (select label_id
                         from room_label
                         where is_deleted = 0
                           and room_id = #{id})
        </select>
        ```
    
    - **编写查询房间可选支付方式逻辑**
    
      - 在`PaymentTypeMapper`中增加如下内容
    
        ```java
        List<PaymentType> selectListByRoomId(Long id);
        ```
    
      - 在`PaymentTypeMapper.xml`中增加如下内容
    
        ```xml
        <select id="selectListByRoomId" resultType="com.atguigu.lease.model.entity.PaymentType">
            select id,
                   name,
                   pay_month_count,
                   additional_info
            from payment_type
            where is_deleted = 0
              and id in (select payment_type_id
                         from room_payment_type
                         where is_deleted = 0
                           and room_id = #{id})
        </select>
        ```
    
    - **编写查询房间属性逻辑**
    
      - 在`AttrValueMapper`中增加如下内容
    
        ```java
        List<AttrValueVo> selectListByRoomId(Long id);
        ```
    
      - 在`AttrValueMapper.xml`中增加如下内容
    
        ```xml
        <select id="selectListByRoomId" resultType="com.atguigu.lease.web.app.vo.attr.AttrValueVo">
            select av.id,
                   av.name,
                   av.attr_key_id,
                   ak.name attr_key_name
            from attr_value av
                     left join attr_key ak on av.attr_key_id = ak.id and ak.is_deleted = 0
            where av.is_deleted = 0
              and av.id in (select attr_value_id
                            from room_attr_value
                            where is_deleted = 0
                              and room_id = #{id})
        </select>
        ```
    
    - **编写查询房间杂费逻辑**
    
      - 在`FeeValueMapper`中增加如下内容
    
        ```java
        List<FeeValueVo> selectListByApartmentId(Long id);
        ```
    
      - 在`FeeValueMapper.xml`中增加如下内容
    
        ```xml
        <select id="selectListByApartmentId" resultType="com.atguigu.lease.web.app.vo.fee.FeeValueVo">
            select fv.id,
                   fv.name,
                   fv.unit,
                   fv.fee_key_id,
                   fk.name fee_key_name
            from fee_value fv
                     left join fee_key fk on fv.fee_key_id = fk.id and fk.is_deleted = 0
            where fv.is_deleted = 0
              and fv.id in (select fee_value_id
                            from apartment_fee_value
                            where is_deleted = 0
                              and apartment_id = #{id})
        </select>
        ```

- **编写查询所属公寓信息逻辑**

  - **编写Service层逻辑**

    在`ApartmentInfoService`中增加如下内容

    ```java
    ApartmentItemVo selectApartmentItemVoById(Long id);
    ```

    在`ApartmentInfoServiceImpl`中增加如下内容

    ```java
    @Override
    public ApartmentItemVo selectApartmentItemVoById(Long id) {
    
        ApartmentInfo apartmentInfo = apartmentInfoMapper.selectById(id);
    
        List<LabelInfo> labelInfoList = labelInfoMapper.selectListByApartmentId(id);
    
        List<GraphVo> graphVoList = graphInfoMapper.selectListByItemTypeAndId(ItemType.APARTMENT, id);
    
        BigDecimal minRent = roomInfoMapper.selectMinRentByApartmentId(id);
    
        ApartmentItemVo apartmentItemVo = new ApartmentItemVo();
        BeanUtils.copyProperties(apartmentInfo, apartmentItemVo);
    
        apartmentItemVo.setGraphVoList(graphVoList);
        apartmentItemVo.setLabelInfoList(labelInfoList);
        apartmentItemVo.setMinRent(minRent);
        return apartmentItemVo;
    }
    ```
  
- **编写Mapper层逻辑**
  
  - **编写查询标签信息逻辑**
  
    - 在`LabelInfoMapper`中增加如下内容
  
      ```java
        List<LabelInfo> selectListByApartmentId(Long id);
      ```
  
    - 在`LabelInfoMapper.xml`中增加如下内容
  
      ```xml
        <select id="selectListByApartmentId" resultType="com.atguigu.lease.model.entity.LabelInfo">
            select id,
                   type,
                   name
            from label_info
            where is_deleted = 0
              and id in (select label_id
                         from apartment_label
                         where is_deleted = 0
                           and apartment_id = #{id})
        </select>
      ```
    
    - **编写查询公寓最小租金逻辑**
    
      - 在`RoomInfoMapper`中增加如下内容
    
        ```java
        BigDecimal selectMinRentByApartmentId(Long id);
        ```
  
      - 在`RoomInfoMapper.xml`中增加如下内容
      
        ```xml
        <select id="selectMinRentByApartmentId" resultType="java.math.BigDecimal">
            select min(rent)
            from room_info
            where is_deleted = 0
            and is_release = 1
            and apartment_id = #{id}
        </select>
        ```
  

##### 3.根据公寓ID分页查询房间列表

- **查看请求和响应的数据结构**

  - **请求的数据结构**

    - `current`和`size`为分页相关参数，分别表示**当前所处页面**和**每个页面的记录数**。
    - `id`为公寓ID。

  - **响应的数据结构**

    - 查看**web-admin模块**下的`com.atguigu.lease.web.app.vo.room.RoomItemVo`，如下

      ```java
      @Schema(description = "APP房间列表实体")
      @Data
      public class RoomItemVo {
      
          @Schema(description = "房间id")
          private Long id;
      
          @Schema(description = "房间号")
          private String roomNumber;
      
          @Schema(description = "租金（元/月）")
          private BigDecimal rent;
      
          @Schema(description = "房间图片列表")
          private List<GraphVo> graphVoList;
      
          @Schema(description = "房间标签列表")
          private List<LabelInfo> labelInfoList;
      
          @Schema(description = "房间所属公寓信息")
          private ApartmentInfo apartmentInfo;
      
      }
      ```
  
- **编写Controller层逻辑**

  在`RoomController`中增加如下内容

  ```java
  @Operation(summary = "根据公寓id分页查询房间列表")
  @GetMapping("pageItemByApartmentId")
  public Result<IPage<RoomItemVo>> pageItemByApartmentId(@RequestParam long current, @RequestParam long size, @RequestParam Long id) {
      IPage<RoomItemVo> page = new Page<>(current, size);
      IPage<RoomItemVo> result = service.pageItemByApartmentId(page, id);
      return Result.ok(result);
  }
  ```

- **编写Service层逻辑**

  在`RoomInfoService`中增加如下内容

  ```java
  IPage<RoomItemVo> pageItemByApartmentId(IPage<RoomItemVo> page, Long id);
  ```

  在`RoomInfoServiceImpl`中增加如下内容

  ```java
  @Override
  public IPage<RoomItemVo> pageItemByApartmentId(IPage<RoomItemVo> page, Long id) {
      return roomInfoMapper.pageItemByApartmentId(page, id);
  }
  ```

- **编写Mapper层逻辑**

  在`RoomInfoMapper`中增加如下内容

  ```java
  IPage<RoomItemVo> pageItemByApartmentId(IPage<RoomItemVo> page, Long id);
  ```

  在`RoomInfoMapper.xml`中增加如下内容

  ```xml
  <select id="pageItemByApartmentId" resultMap="RoomItemVoMap">
      select ri.id,
             ri.room_number,
             ri.rent,
             ai.id apartment_id,
             ai.name,
             ai.introduction,
             ai.district_id,
             ai.district_name,
             ai.city_id,
             ai.city_name,
             ai.province_id,
             ai.province_name,
             ai.address_detail,
             ai.latitude,
             ai.longitude,
             ai.phone,
             ai.is_release
      from room_info ri
               left join apartment_info ai on ri.apartment_id = ai.id and ai.is_deleted = 0
      where ri.is_deleted = 0
        and ri.is_release = 1
        and ai.id = #{id}
        and ri.id not in (select room_id
                          from lease_agreement
                          where is_deleted = 0
                            and status in (2, 5))
  
  </select>
  ```


#### 7.4.3.5 公寓信息

公寓信息只需一个接口，即**根据ID查询公寓详细信息**，具体实现如下

首先在`ApartmentController`中注入`ApartmentInfoService`，如下

```java
@RestController
@Tag(name = "公寓信息")
@RequestMapping("/app/apartment")
public class ApartmentController {
    @Autowired
    private ApartmentInfoService service;
}
```

- **查看响应的数据结构**

  查看**web-app模块**下的`com.atguigu.lease.web.app.vo.apartment.ApartmentDetailVo`，内容如下

  ```java
  @Data
  @Schema(description = "APP端公寓信息详情")
  public class ApartmentDetailVo extends ApartmentInfo {
  
      @Schema(description = "图片列表")
      private List<GraphVo> graphVoList;
  
      @Schema(description = "标签列表")
      private List<LabelInfo> labelInfoList;
  
      @Schema(description = "配套列表")
      private List<FacilityInfo> facilityInfoList;
  
      @Schema(description = "租金最小值")
      private BigDecimal minRent;
  }
  ```
  
- **编写Controller层逻辑**

  在`ApartmentController`中增加如下内容

  ```java
  @Operation(summary = "根据id获取公寓信息")
  @GetMapping("getDetailById")
  public Result<ApartmentDetailVo> getDetailById(@RequestParam Long id) {
      ApartmentDetailVo apartmentDetailVo = service.getApartmentDetailById(id);
      return Result.ok(apartmentDetailVo);
  }
  ```
  
- **编写Service层逻辑**

  - 在`ApartmentInfoService`中增加如下内容

    ```java
    ApartmentDetailVo getDetailById(Long id);
    ```

  - 在`ApartmentInfoServiceImpl`中增加如下内容

    ```java
    @Override
    public ApartmentDetailVo getDetailById(Long id) {
        //1.查询公寓信息
        ApartmentInfo apartmentInfo = apartmentInfoMapper.selectById(id);
        //2.查询图片信息
        List<GraphVo> graphVoList = graphInfoMapper.selectListByItemTypeAndId(ItemType.APARTMENT, id);
        //3.查询标签信息
        List<LabelInfo> labelInfoList = labelInfoMapper.selectListByApartmentId(id);
        //4.查询配套信息
        List<FacilityInfo> facilityInfoList = facilityInfoMapper.selectListByApartmentId(id);
        //5.查询最小租金
        BigDecimal minRent = roomInfoMapper.selectMinRentByApartmentId(id);
    
        ApartmentDetailVo apartmentDetailVo = new ApartmentDetailVo();
    
        BeanUtils.copyProperties(apartmentInfo, apartmentDetailVo);
        apartmentDetailVo.setGraphVoList(graphVoList);
        apartmentDetailVo.setLabelInfoList(labelInfoList);
        apartmentDetailVo.setFacilityInfoList(facilityInfoList);
        apartmentDetailVo.setMinRent(minRent);
        return apartmentDetailVo;
    }
    ```
  
- **编写Mapper层逻辑**

  - **编写查询公寓配套逻辑**

    - 在`FacilityInfoMapper`中增加如下内容

      ```java
      List<FacilityInfo> selectListByApartmentId(Long id);
      ```

    - 在`FacilityInfoMapper.xml`中增加如下内容

      ```xml
      <select id="selectListByApartmentId" resultType="com.atguigu.lease.model.entity.FacilityInfo">
          select id,
                 type,
                 name,
                 icon
          from facility_info
          where is_deleted = 0
            and id in (select facility_id
                       from apartment_facility
                       where is_deleted = 0
                         and apartment_id = #{id})
      </select>

### 7.4.4 个人中心

#### 7.4.4.1 浏览历史

浏览历史指的是浏览房间详情的历史，关于浏览历史，有两项工作需要完成，一是提供一个查询浏览历史列表的接口，二是在浏览完房间详情后，增加保存浏览历史的逻辑，下面分别实现。

##### 1.分页查询浏览历史列表

首先在`BrowsingHistoryController`中注入`BrowsingHistoryService`，如下

```java
@RestController
@Tag(name = "浏览历史管理")
@RequestMapping("/app/history")
public class BrowsingHistoryController {

    @Autowired
    private BrowsingHistoryService service;
}
```

- **查看请求和响应的数据结构**

  - **请求的数据结构**

    `current`和`size`为分页相关参数，分别表示**当前所处页面**和**每个页面的记录数**。

  - **响应的数据结构**

    查看**web-admin模块**下的`com.atguigu.lease.web.app.vo.history.HistoryItemVo`，如下

    ```java
    @Data
    @Schema(description = "浏览历史基本信息")
    public class HistoryItemVo extends BrowsingHistory {
    
        @Schema(description = "房间号")
        private String roomNumber;
    
        @Schema(description = "租金")
        private BigDecimal rent;
    
        @Schema(description = "房间图片列表")
        private List<GraphVo> roomGraphVoList;
    
        @Schema(description = "公寓名称")
        private String apartmentName;
    
        @Schema(description = "省份名称")
        private String provinceName;
    
        @Schema(description = "城市名称")
        private String cityName;
    
        @Schema(description = "区县名称")
        private String districtName;
    }
    ```

- **编写Controller层逻辑**

  在`BrowsingHistoryController`中增加如下内容

  ```java
  @Operation(summary = "获取浏览历史")
  @GetMapping("pageItem")
  private Result<IPage<HistoryItemVo>> page(@RequestParam long current, @RequestParam long size) {
      Page<HistoryItemVo> page = new Page<>(current, size);
      IPage<HistoryItemVo> result = service.pageHistoryItemByUserId(page, LoginUserHolder.getLoginUser().getUserId());
      return Result.ok(result);
  }
  ```
  
- **编写Service层逻辑**

  - 在`BrowsingHistoryService`中增加如下逻辑

    ```java
    IPage<HistoryItemVo> pageHistoryItemByUserId(Page<HistoryItemVo> page, Long userId);
    ```

  - 在`BrowsingHistoryServiceImpl`中增加如下逻辑

    ```java
    @Override
    public IPage<HistoryItemVo> pageHistoryItemByUserId(Page<HistoryItemVo> page, Long userId) {
        return browsingHistoryMapper.pageHistoryItemByUserId(page, userId);
    }
    ```

- **编写Mapper层逻辑**

  - 在`BrowsingHistoryMapper`中增加如下逻辑

    ```java
    IPage<HistoryItemVo> pageHistoryItemByUserId(Page<HistoryItemVo> page, Long userId);
    ```

  - 在`BrowsingHistoryMapper.xml`中增加如下逻辑

    ```xml
    <resultMap id="HistoryItemVoMap" type="com.atguigu.lease.web.app.vo.history.HistoryItemVo" autoMapping="true">
        <id property="id" column="id"/>
        <result property="roomId" column="room_id"/>
        <collection property="roomGraphVoList" ofType="com.atguigu.lease.web.app.vo.graph.GraphVo"
                    select="selectGraphVoByRoomId" column="room_id"/>
    </resultMap>
    
    <select id="pageHistoryItemByUserId" resultMap="HistoryItemVoMap">
        select bh.id,
               bh.user_id,
               bh.room_id,
               bh.browse_time,
               ri.room_number,
               ri.rent,
               ai.name apartment_name,
               ai.district_name,
               ai.city_name,
               ai.province_name
        from browsing_history bh
                 left join room_info ri on bh.room_id = ri.id and ri.is_deleted=0
                 left join apartment_info ai on ri.apartment_id = ai.id and ai.is_deleted=0
        where bh.is_deleted = 0
          and bh.user_id = #{userId}
        order by browse_time desc
    </select>
    
    <select id="selectGraphVoByRoomId" resultType="com.atguigu.lease.web.app.vo.graph.GraphVo">
        select url,
               name
        from graph_info
        where is_deleted = 0
          and item_type = 2
          and item_id = #{room_id}
    </select>
    ```

##### 2.保存浏览历史

- **触发保存浏览历史**

  保存浏览历史的动作应该在浏览房间详情时触发，所以在`RoomInfoServiceImpl`中的`getDetailById`方法的最后增加如下内容

  ```java
  browsingHistoryService.saveHistory(LoginUserContext.getLoginUser().getUserId(), id);
  ```

- **编写Service层逻辑**

  - 在`BrowsingHistoryService`中增加如下内容

    ```java
    void saveHistory(Long userId, Long roomId);
    ```

  - 在`BrowsingHistoryServiceImpl`中增加如下内容

    ```java
    @Override
    public void saveHistory(Long userId, Long roomId) {
    
        LambdaQueryWrapper<BrowsingHistory> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(BrowsingHistory::getUserId, userId);
        queryWrapper.eq(BrowsingHistory::getRoomId, roomId);
        BrowsingHistory browsingHistory = browsingHistoryMapper.selectOne(queryWrapper);
    
        if (browsingHistory != null) {
            browsingHistory.setBrowseTime(new Date());
            browsingHistoryMapper.updateById(browsingHistory);
        } else {
            BrowsingHistory newBrowsingHistory = new BrowsingHistory();
            newBrowsingHistory.setUserId(userId);
            newBrowsingHistory.setRoomId(roomId);
            newBrowsingHistory.setBrowseTime(new Date());
            browsingHistoryMapper.insert(newBrowsingHistory);
        }
    }
    ```
    
    **知识点**：
    
    保存浏览历史的动作不应影响前端获取房间详情信息，故此处采取异步操作。Spring Boot提供了`@Async`注解来完成异步操作，具体使用方式为：
    
    - 启用Spring Boot异步操作支持
    
      在 Spring Boot 主应用程序类上添加 `@EnableAsync` 注解，如下
    
      ```java
      @SpringBootApplication
      @EnableAsync
      public class AppWebApplication {
          public static void main(String[] args) {
              SpringApplication.run(AppWebApplication.class);
          }
      }
      ```
    
    - 在要进行异步处理的方法上添加 `@Async` 注解，如下
    
      ```java
      @Override
      @Async
      public void saveHistory(Long userId, Long roomId) {
      
          LambdaQueryWrapper<BrowsingHistory> queryWrapper = new LambdaQueryWrapper<>();
          queryWrapper.eq(BrowsingHistory::getUserId, userId);
          queryWrapper.eq(BrowsingHistory::getRoomId, roomId);
          BrowsingHistory browsingHistory = browsingHistoryMapper.selectOne(queryWrapper);
      
          if (browsingHistory != null) {
              browsingHistory.setBrowseTime(new Date());
              browsingHistoryMapper.updateById(browsingHistory);
          } else {
              BrowsingHistory newBrowsingHistory = new BrowsingHistory();
              newBrowsingHistory.setUserId(userId);
              newBrowsingHistory.setRoomId(roomId);
              newBrowsingHistory.setBrowseTime(new Date());
              browsingHistoryMapper.insert(newBrowsingHistory);
          }
      }
      ```

#### 7.4.4.2 预约看房

预约看房管理共需三个接口，分别是**保存或更新看房预约**、**查询个人预约列表**和**根据ID查询预约详情信息**，下面逐一实现

首先在`ViewAppointmentController`中注入`ViewAppointmentService`，如下

```java
@Tag(name = "看房预约信息")
@RestController
@RequestMapping("/app/appointment")
public class ViewAppointmentController {

    @Autowired
    private ViewAppointmentService service;
}
```

##### 1. 保存或更新看房预约

在`ViewAppointmentController`中增加如下内容

```java
@Operation(summary = "保存或更新看房预约")
@PostMapping("/saveOrUpdate")
public Result saveOrUpdate(@RequestBody ViewAppointment viewAppointment) {

    viewAppointment.setUserId(LoginUserHolder.getLoginUser().getUserId());
    service.saveOrUpdate(viewAppointment);
    return Result.ok();
}
```

##### 2. 查询个人预约看房列表

- **查看响应的数据结构**

  查看**web-app模块**下的`com.atguigu.lease.web.app.vo.appointment.AppointmentItemVo`，如下

  ```java
  @Data
  @Schema(description = "APP端预约看房基本信息")
  public class AppointmentItemVo {
  
      @Schema(description = "预约Id")
      private Long id;
  
      @Schema(description = "预约公寓名称")
      private String apartmentName;
  
      @Schema(description = "公寓图片列表")
      private List<GraphVo> graphVoList;
  
      @Schema(description = "预约时间")
      @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
      private Date appointmentTime;
  
      @Schema(description = "当前预约状态")
      private AppointmentStatus appointmentStatus;
  }
  ```

- **编写Controller层逻辑**

  在`ViewAppointmentController`中增加如下内容

  ```java
  @Operation(summary = "查询个人预约看房列表")
  @GetMapping("listItem")
  public Result<List<AppointmentItemVo>> listItem() {
      List<AppointmentItemVo> list = service.listItemByUserId(LoginUserHolder.getLoginUser().getUserId());
      return Result.ok(list);
  }
  ```
  
- **编写Service层逻辑**

  - 在`ViewAppointmentService`中增加如下内容

    ```java
    List<AppointmentItemVo> listItemByUserId(Long userId);
    ```

  - 在`ViewAppointmentServiceImpl`中增加如下内容

    ```java
    @Override
    public List<AppointmentItemVo> listItemByUserId(Long userId) {
        return viewAppointmentMapper.listItemByUserId(userId);
    }
    ```

- **编写Mapper层逻辑**

  - 在`ViewAppointmentMapper`中增加如下内容

    ```java
    List<AppointmentItemVo> listItemByUserId(Long userId);
    ```

  - 在`ViewAppointmentMapper.xml`中增加如下内容

    ```xml
    <resultMap id="AppointmentItemVoMap" type="com.atguigu.lease.web.app.vo.appointment.AppointmentItemVo"
               autoMapping="true">
        <id column="id" property="id"/>
        <collection property="graphVoList" ofType="com.atguigu.lease.web.app.vo.graph.GraphVo" autoMapping="true"/>
    </resultMap>
    
    <select id="listItemByUserId" resultMap="AppointmentItemVoMap">
        select va.id,
               va.appointment_time,
               va.appointment_status,
               ai.name apartment_name,
               gi.name,
               gi.url
        from view_appointment va
                 left join apartment_info ai on va.apartment_id = ai.id and ai.is_deleted = 0
                 left join graph_info gi on gi.item_type = 1 and gi.item_id = ai.id and gi.is_deleted = 0
        where va.is_deleted = 0
          and va.user_id = #{userId}
        order by va.create_time desc
    </select>
    ```

##### 3. 根据ID查询预约详情信息

- **查看相应的数据结构**

  查看`web-app模块`下的`com.atguigu.lease.web.app.vo.appointment.AppointmentDetailVo`，内容如下

  ```java
  @Data
  @Schema(description = "APP端预约看房详情")
  public class AppointmentDetailVo extends ViewAppointment {
  
      @Schema(description = "公寓基本信息")
      private ApartmentItemVo apartmentItemVo;
  }
  ```

- **编写Controller层逻辑**

  在`ViewAppointmentController`中增加如下内容

  ```java
  @GetMapping("getDetailById")
  @Operation(summary = "根据ID查询预约详情信息")
  public Result<AppointmentDetailVo> getDetailById(Long id) {
      AppointmentDetailVo appointmentDetailVo = service.getDetailById(id);
      return Result.ok(appointmentDetailVo);
  }
  ```

- **编写Service层逻辑**

  - 在`ViewAppointmentService`中增加如下内容

    ```java
    AppointmentDetailVo getDetailById(Long id);
    ```

  - 在`ViewAppointmentServiceImpl`中增加如下内容

    ```java
    @Override
    public AppointmentDetailVo getDetailById(Long id) {
    
        ViewAppointment viewAppointment = viewAppointmentMapper.selectById(id);
    
        ApartmentItemVo apartmentItemVo = apartmentInfoService.selectApartmentItemVoById(viewAppointment.getApartmentId());
    
        AppointmentDetailVo agreementDetailVo = new AppointmentDetailVo();
        BeanUtils.copyProperties(viewAppointment, agreementDetailVo);
    
        agreementDetailVo.setApartmentItemVo(apartmentItemVo);
    
        return agreementDetailVo;
    }
    ```

#### 7.4.4.3 租约管理

租约管理共有六个接口，分别是**获取个人租约基本信息列表**、**根据ID获取租约详细信息**、**根据ID更新租约状态**、**保存或更新租约**、**根据房间ID获取可选支付方式**和**根据房间ID获取可选租期**，下面逐一实现

首先在`LeaseAgreementController`中注入`LeaseAgreementService`，如下

```java
@RestController
@RequestMapping("/app/agreement")
@Tag(name = "租约信息")
public class LeaseAgreementController {

    @Autowired
    private LeaseAgreementService service;
}
```

##### 1. 获取个人租约基本信息列表

- **查看响应的数据结构**

  查看**web-appp模块**下的`com.atguigu.lease.web.app.vo.agreement.AgreementItemVo`，内容如下

  ```java
  @Data
  @Schema(description = "租约基本信息")
  public class AgreementItemVo {
  
      @Schema(description = "租约id")
      private Long id;
  
      @Schema(description = "房间图片列表")
      private List<GraphVo> roomGraphVoList;
  
      @Schema(description = "公寓名称")
      private String apartmentName;
  
      @Schema(description = "房间号")
      private String roomNumber;
  
      @Schema(description = "租约状态")
      private LeaseStatus leaseStatus;
  
      @Schema(description = "租约开始日期")
      @JsonFormat(pattern = "yyyy-MM-dd")
      private Date leaseStartDate;
  
      @Schema(description = "租约结束日期")
      @JsonFormat(pattern = "yyyy-MM-dd")
      private Date leaseEndDate;
  
      @Schema(description = "租约来源")
      private LeaseSourceType sourceType;
  
      @Schema(description = "租金")
      private BigDecimal rent;
  }
  ```
  
- **编写Controller层逻辑**

  在`LeaseAgreementController`中增加如下内容

  ```java
  @Operation(summary = "获取个人租约基本信息列表")
  @GetMapping("listItem")
  public Result<List<AgreementItemVo>> listItem() {
      List<AgreementItemVo> result = service.listItemByPhone(LoginUserHolder.getLoginUser().getUsername());
      return Result.ok(result);
  }
  ```
  
- **编写Service层逻辑**

  - 在`LeaseAgreementService`中增加如下内容

    ```java
    List<AgreementItemVo> listItemByPhone(String phone);
    ```

  - 在`LeaseAgreementServiceImpl`中增加如下内容

    ```java
    @Override
    public List<AgreementItemVo> listItemByPhone(String phone) {
        return leaseAgreementMapper.listItemByPhone(phone);
    }
    ```
  
- **编写Mapper层逻辑**

  - 在`LeaseAgreementMapper`中增加如下内容

    ```java
    List<AgreementItemVo> listItemByPhone(String phone);
    ```

  - 在`LeaseAgreementMapper.xml`中增加如下内容

    ```xml
    <resultMap id="AgreementItemVoMap" type="com.atguigu.lease.web.app.vo.agreement.AgreementItemVo" autoMapping="true">
        <id property="id" column="id"/>
        <collection property="roomGraphVoList" ofType="com.atguigu.lease.web.app.vo.graph.GraphVo" autoMapping="true"/>
    </resultMap>
    
    <select id="listItemByPhone" resultMap="AgreementItemVoMap">
        select la.id,
               la.lease_start_date,
               la.lease_end_date,
               la.rent,
               la.payment_type_id,
               la.status lease_status,
               la.source_type,
               ai.name apartment_name,
               ri.room_number,
               gi.name,
               gi.url
        from lease_agreement la
                 left join apartment_info ai on la.apartment_id = ai.id and ai.is_deleted = 0
                 left join room_info ri on la.room_id = ri.id and ri.is_deleted = 0
                 left join graph_info gi on gi.item_type = 2 and gi.item_id = ri.id and gi.is_deleted = 0
        where la.is_deleted = 0
          and la.phone = #{phone}
    
    </select>
    ```

##### 2. 根据ID获取租约详细信息

- **查看响应的数据结构**

  查看**web-app模块**下的`com.atguigu.lease.web.app.vo.agreement.AgreementDetailVo`，内容如下

  ```java
  @Data
  @Schema(description = "租约详细信息")
  public class AgreementDetailVo extends LeaseAgreement {
  
      @Schema(description = "租约id")
      private Long id;
  
      @Schema(description = "公寓名称")
      private String apartmentName;
  
      @Schema(description = "公寓图片列表")
      private List<GraphVo> apartmentGraphVoList;
  
      @Schema(description = "房间号")
      private String roomNumber;
  
      @Schema(description = "房间图片列表")
      private List<GraphVo> roomGraphVoList;
  
      @Schema(description = "支付方式")
      private String paymentTypeName;
  
      @Schema(description = "租期月数")
      private Integer leaseTermMonthCount;
  
      @Schema(description = "租期单位")
      private String leaseTermUnit;
  
  }
  ```

- **编写Controller层逻辑**

  在`LeaseAgreementController`中增加如下内容

  ```java
  @Operation(summary = "根据id获取租约详细信息")
  @GetMapping("getDetailById")
  public Result<AgreementDetailVo> getDetailById(@RequestParam Long id) {
      AgreementDetailVo agreementDetailVo = service.getDetailById(id);
      return Result.ok(agreementDetailVo);
  }
  ```
  
- **编写Service层逻辑**

  - 在`LeaseAgreementService`中增加如下内容

    ```java
    AgreementDetailVo getDetailById(Long id);
    ```

  - 在`LeaseAgreementServiceImpl`中增加如下内容

    ```java
    @Override
    public AgreementDetailVo getDetailById(Long id) {
    
        //1.查询租约信息
        LeaseAgreement leaseAgreement = leaseAgreementMapper.selectById(id);
        if (leaseAgreement == null) {
            return null;
        }
        //2.查询公寓信息
        ApartmentInfo apartmentInfo = apartmentInfoMapper.selectById(leaseAgreement.getApartmentId());
    
        //3.查询房间信息
        RoomInfo roomInfo = roomInfoMapper.selectById(leaseAgreement.getRoomId());
    
        //4.查询图片信息
        List<GraphVo> roomGraphVoList = graphInfoMapper.selectListByItemTypeAndId(ItemType.ROOM, leaseAgreement.getRoomId());
        List<GraphVo> apartmentGraphVoList = graphInfoMapper.selectListByItemTypeAndId(ItemType.APARTMENT, leaseAgreement.getApartmentId());
    
        //5.查询支付方式
        PaymentType paymentType = paymentTypeMapper.selectById(leaseAgreement.getPaymentTypeId());
    
        //6.查询租期
        LeaseTerm leaseTerm = leaseTermMapper.selectById(leaseAgreement.getLeaseTermId());
    
        AgreementDetailVo agreementDetailVo = new AgreementDetailVo();
        BeanUtils.copyProperties(leaseAgreement, agreementDetailVo);
        agreementDetailVo.setApartmentName(apartmentInfo.getName());
        agreementDetailVo.setRoomNumber(roomInfo.getRoomNumber());
        agreementDetailVo.setApartmentGraphVoList(apartmentGraphVoList);
        agreementDetailVo.setRoomGraphVoList(roomGraphVoList);
        agreementDetailVo.setPaymentTypeName(paymentType.getName());
        agreementDetailVo.setLeaseTermMonthCount(leaseTerm.getMonthCount());
        agreementDetailVo.setLeaseTermUnit(leaseTerm.getUnit());
    
        return agreementDetailVo;
    }
    ```

##### 3. 根据ID更新租约状态

- **编写Controller层逻辑**

  在`LeaseAgreementController`中增加如下内容

  ```java
  @Operation(summary = "根据id更新租约状态", description = "用于确认租约和提前退租")
  @PostMapping("updateStatusById")
  public Result updateStatusById(@RequestParam Long id, @RequestParam LeaseStatus leaseStatus) {
      LambdaUpdateWrapper<LeaseAgreement> updateWrapper = new LambdaUpdateWrapper<>();
      updateWrapper.eq(LeaseAgreement::getId, id);
      updateWrapper.set(LeaseAgreement::getStatus, leaseStatus);
      service.update(updateWrapper);
      return Result.ok();
  }
  ```

##### 4. 保存或更新租约

- **编写Controller层逻辑**

  在`LeaseAgreementController`中增加如下内容

  ```java
  @Operation(summary = "保存或更新租约", description = "用于续约")
  @PostMapping("saveOrUpdate")
  public Result saveOrUpdate(@RequestBody LeaseAgreement leaseAgreement) {
      service.saveOrUpdate(leaseAgreement);
      return Result.ok();
  }
  ```

##### 5. 根据房间ID获取可选支付方式

- **编写Controller层逻辑**

  在`PaymentTypeController`中增加如下内容

  ```java
  @Operation(summary = "根据房间id获取可选支付方式列表")
  @GetMapping("listByRoomId")
  public Result<List<PaymentType>> list(@RequestParam Long id) {
      List<PaymentType> list = service.listByRoomId(id);
      return Result.ok(list);
  }
  ```

- **编写Service层逻辑**

  在`PaymentTypeService`中增加如下内容

  ```java
  List<PaymentType> listByRoomId(Long id);
  ```

  在`PaymentTypeServiceImpl`中增加如下内容

  ```java
  @Override
  public List<PaymentType> listByRoomId(Long id) {
      return paymentTypeMapper.selectListByRoomId(id);
  }
  ```

##### 6.根据房间ID获取可选租期

- **编写Controller层逻辑**

  在`LeaseTermController`中增加如下内容

  ```java
  @GetMapping("listByRoomId")
  @Operation(summary = "根据房间id获取可选获取租期列表")
  public Result<List<LeaseTerm>> list(@RequestParam Long id) {
      List<LeaseTerm> list = service.listByRoomId(id);
      return Result.ok(list);
  }
  ```

- **编写Service层逻辑**

  在`LeaseTermServcie`中曾加如下内容

  ```java
  List<LeaseTerm> listByRoomId(Long id);
  ```

  在`LeaseTermServiceImpl`中增加如下内容

  ```java
  @Override
  public List<LeaseTerm> listByRoomId(Long id) {
      return leaseTermMapper.selectListByRoomId(id);
  }
  ```

## 7.5 移动端前后端联调

### 7.5.1 启动后端项目

启动后端项目，供前端调用接口。

### 7.5.2 启动前端项目

1. **导入前端项目**

   将移动端的前端项目（**rentHouseH5**）导入`vscode`或者`WebStorm`，打开终端，在项目根目录执行以下命令，安装所需依赖

   ```bash
   npm install
   ```

2. **配置后端接口地址**

   修改项目根目录下的`.env.development`文件中的`VITE_APP_BASE_URL`变量的值为后端接口的地址，此处改为`http://localhost:8081`即可,如下

   ```ini
   VITE_APP_BASE_URL='http://localhost:8081'
   ```

   **注意**：

   上述主机名和端口号需要根据实际情况进行修改。

3. **启动前端项目**

   上述配置完成之后，便可执行以下命令启动前端项目了

   ```bash
   npm run dev
   ```

4. **访问前端项目**

   在浏览器中访问前端项目，并逐个测试每个页面的相关功能。

# 8. 项目优化

## 8.1 缓存优化

### 8.1.1 概述

缓存优化是一个性价比很高的优化手段，多数情况下，缓存优化可以通过一些简单的操作，换来性能的大幅提升。缓存优化的核心思想就是将一些原本保存在磁盘（例如MySQL）中的、经常访问并且查询开销比较大的数据，临时保存到内存（例如Redis）中。后序再访问相同数据时，就可直接从内存中获取结果，而无需再访问磁盘，由于内存的读写速度远高于磁盘，因此就能极大的提高程序的性能。

<img src="images/缓存概述.drawio.svg" style="zoom:50%;" />

在使用缓存优化时，有一个问题不得不提，那就是**数据库和缓存数据的一致性**，当数据库中的数据发生变化时，缓存中的数据也要同步更新，否则就会出现数据不一致的问题，解决该问题的方案有如下几个

- 数据发生变化时，更新数据库的同时也更新缓存
- 数据发生变化时，更新数据库的同时删除缓存

在了解了缓存优化的核心思想后，我们以移动端中的`根据ID获取房间详情`接口为例，进行缓存优化。该接口涉及多表查询，查询时会多次访问数据库，查询代价较高，故可采取缓存优化，加快查询速度。

### 8.1.2 编写缓存逻辑

**1.自定义RedisTemplate**

本项目使用Reids保存缓存数据，因此我们需要使用RedisTemplate进行读写操作。前文提到过，`Spring-data-redis`提供了`StringRedisTemplate`和`RedisTemplate<Object,Object>`两个实例，但是两个实例均不满足我们当前的需求，所以我们需要自定义RedisTemplate。

在**common模块**中创建`com.atguigu.lease.common.redis.RedisConfiguration`类，内容如下

```java
@Configuration
public class RedisConfiguration {

    @Bean
    public RedisTemplate<String, Object> stringObjectRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        template.setKeySerializer(RedisSerializer.string());
        template.setValueSerializer(RedisSerializer.java());
        return template;
    }
}
```

**2.编写缓存逻辑**

修改**web-app模块**中的`com.atguigu.lease.web.app.service.impl.RoomInfoServiceImpl`中的`getDetailById`方法，如下

```java
@Override
public RoomDetailVo getDetailById(Long id) {

    String key = RedisConstant.APP_ROOM_PREFIX + id;
    RoomDetailVo roomDetailVo = (RoomDetailVo) redisTemplate.opsForValue().get(key);
    if (roomDetailVo == null) {
        //1.查询房间信息
        ......

        //2.查询图片
        ......

        //3.查询租期
        ......

        //4.查询配套
        ......

        //5.查询标签
        ......

        //6.查询支付方式
        ......

        //7.查询基本属性
        ......

        //8.查询杂费信息
        ......

        //9.查询公寓信息
        ......

        roomDetailVo = new RoomDetailVo();
        ......

        redisTemplate.opsForValue().set(key, roomDetailVo);
    }

    //10.保存浏览历史
    browsingHistoryService.saveHistory(LoginUserHolder.getLoginUser().getUserId(), id);

    return roomDetailVo;
}
```

**3.编写删除缓存逻辑**

为保证缓存数据的一致性，在房间信息发生变化时，需要删除相关缓存。

修改**web-admin模块**中的`com.atguigu.lease.web.admin.service.impl.RoomInfoServiceImpl`中的`saveOrUpdateRoom`方法，如下

```java
@Override
public void saveOrUpdateRoom(RoomSubmitVo roomSubmitVo) {
    boolean isUpdate = roomSubmitVo.getId() != null;
    super.saveOrUpdate(roomSubmitVo);

    //若为更新操作，则先删除与Room相关的各项信息列表
    if (isUpdate) {
        //1.删除原有graphInfoList
        ......

        //2.删除原有roomAttrValueList
        ......

        //3.删除原有roomFacilityList
        ......

        //4.删除原有roomLabelList
        ......

        //5.删除原有paymentTypeList
        ......

        //6.删除原有leaseTermList
        ......

        //7.删除缓存
        redisTemplate.delete(RedisConstant.APP_LOGIN_PREFIX + roomSubmitVo.getId());
    }

    //1.保存新的graphInfoList
    ......

    //2.保存新的roomAttrValueList
    ......

    //3.保存新的facilityInfoList
    ......

    //4.保存新的labelInfoList
    ......

    //5.保存新的paymentTypeList
    ......

    //6.保存新的leaseTermList
    ......
}
```

修改**web-admin模块**中的`com.atguigu.lease.web.admin.service.impl.RoomInfoServiceImpl`中的`removeRoomById`方法，如下

```java
@Override
public void removeRoomById(Long id) {

    //1.删除RoomInfo
    ......

    //2.删除graphInfoList
    ......

    //3.删除attrValueList
    ......

    //4.删除facilityInfoList
    ......

    //5.删除labelInfoList
    ......

    //6.删除paymentTypeList
    ......

    //7.删除leaseTermList
    ......

    //8.删除缓存
    redisTemplate.delete(RedisConstant.APP_ROOM_PREFIX + id);
}
```

### 8.1.3 压力测试

可使用Postman或者Apifox等工具对`根据ID获取房间详情`这个接口进行压力测试，下图是增加缓存前后的测试报告

<img src="images/缓存压力测试报告.png" style="border:solid 1px" />

# 9. 部署准备

## 9.1 部署方案

本章使用虚拟机模拟服务器，完成前后端项目的部署，我们使用两台虚拟机分别部署前端和后端项目，具体的部署方案如下图所示。

![](images/部署架构图.drawio.svg)

上述方案中，`server-01`用于部署移动端和后台管理系统的两个后端服务。`server02`部署Nginx，作为移动端和后台管理系统两个前端项目的web服务器，同时也作为两个后端服务的反向代理。也就是说Nginx作为所有请求的入口，若请求内容是静态资源，Nginx便直接返回；若请求的内容为动态资源（后端服务接口），Nginx便代理请求后端服务，然后将结果响应给客户端。

**知识点**：

**代理**

上述方案中提到了**反向代理**，下面系统的介绍一下**代理（Proxy）**这一概念。

这里的**代理**，是指在网络通信中，介于客户端和服务端之间的一个服务，客户端将请求发往代理服务，代理服务将请求转发到服务端，得到服务端响应后，再将结果响应为客户端，这一过程如下图所示。

<img src="images/代理概念.drawio.svg" style="zoom: 80%;" />

下面介绍一下**代理**的两种类型——**正向代理**和**反向代理**，首先需要明确的是，两者的本质是相同的，都是位于客户端和服务端之间的一个”中间人“，只是两者代表的对象不同，正向代理是代表客户端向服务端发送请求，而反向代理是代表服务端接收客户端的请求。

下面是正向代理和反向代理的两个案例：

- **正向代理**

  某校园为了阻止学生沉迷于网络游戏，设计了一个防火墙，封锁了所有的游戏网站。聪明的小明同学，通过一个代理服务器，绕过了防火墙的封锁，这个代理服务器就是一个典型的正向代理。

  <img src="images/正向代理概念.drawio.png" style="zoom:50%;" />

- **反向代理**

  某电商平台由于日益增长的访问量，一台单一的后端服务器已经不能满足高并发的要求了，这时需要增设多个后端服务器，来分担压力。但是如何能够将客户端的请求均匀的分发到这多个后端服务器呢？一个最常用的方案便是使用代理服务，具体来讲，就是使用一个代理服务代表后端服务器接收请求，然后由代理服务将请求均匀的转发到多个后端服务器，这个代理服务就是一个典型的反向代理。

  <img src="images/反向代理概念.drawio.png" style="zoom:50%;" />

## 9.2 Nginx快速入门

### 9.2.1 Nginx安装

根据前文的部署方案，需要在`server02`部署Nginx。Nginx官网有详细的安装步骤，具体内容可参考[官方文档](https://nginx.org/en/linux_packages.html)。

1. **配置Nginx yum存储库**

   创建`/etc/yum.repos.d/nginx.repo`文件

   ```bash
   vim /etc/yum.repos.d/nginx.repo
   ```

   增加如下内容

   ```ini
   [nginx-stable]
   name=nginx stable repo
   baseurl=http://nginx.org/packages/centos/$releasever/$basearch/
   gpgcheck=1
   enabled=1
   gpgkey=https://nginx.org/keys/nginx_signing.key
   module_hotfixes=true
   
   [nginx-mainline]
   name=nginx mainline repo
   baseurl=http://nginx.org/packages/mainline/centos/$releasever/$basearch/
   gpgcheck=1
   enabled=0
   gpgkey=https://nginx.org/keys/nginx_signing.key
   module_hotfixes=true
   ```

2. **在线安装Nginx**

   执行以下命令，安装Nginx

   ```bash
   yum -y install nginx
   ```

3. **启动Nginx**

   执行以下命令启动Nginx

   ```bash
   systemctl start nginx
   ```

   执行以下命令查看Nginx运行状态

   ```bash
   systemctl status nginx
   ```

   执行以下命令设置开机自启

   ```bash
   systemctl enable nginx
   ```

4. **访问Nginx服务默认首页**

   访问`http://192.168.10.102`，能访问到如下页面，则证明Nginx运行正常。

   <img src="images/Nginx首页.png" style="zoom:50%;" />

### 9.2.2 重要的目录、文件

Nginx中有很多十分重要的目录或者文件，下面对核心内容进行介绍

1. **配置文件相关**
   - `/etc/nginx/`：主要的Nginx配置文件目录。
   - `/etc/nginx/nginx.conf`：Nginx的主配置文件，包含全局配置信息。
2. **日志相关**
   - `/var/log/nginx/`：Nginx的日志文件目录，包括访问日志和错误日志。
   - `/var/log/nginx/access.log`：访问日志，记录所有进入服务器的请求。
   - `/var/log/nginx/error.log`：错误日志，记录服务器处理过程中的错误信息。

### 9.2.3 配置文件概述

1. **配置文件结构**

   `nginx.conf`文件层次分明，整个文件分为多个区块（block），每个区块下可配置各种参数，也可包含其子级区块，具体结构如下图所示

   <img src="images/nginx配置文件结构.drawio.png" style="zoom:50%;" />

   `nginx.conf`通过` include /etc/nginx/conf.d/*.conf`引入了`/etc/nginx/conf.d`目录下的所有`.conf`文件，该目录下的配置文件结构如下图所示

   <img src="images/nginx配置文件结构-conf.drawio.png" style="zoom: 50%;" />

2. **重要配置说明**

   下面分块介绍重要的配置参数

   - **main block**

     `main block`位于配置文件的最外层，其包含了影响Nginx服务器整体行为的全局参数，例如

     - `user`：定义Nginx工作进程的用户和用户组。
     - `worker_processes`：指定Nginx使用的工作进程数。
     - `error_log`：配置全局错误日志文件路径。

   - **events block**

     `events block`位于`main block`中，用于配置Nginx服务器的事件处理机制，主要配置Nginx如何处理客户端连接。

   - **http block**

     `http block`位于`main block`中，用于配置HTTP服务器相关功能。例如

     - `access_log`：指定访问日志的路径
     - `log_format`：指定访问日志的格式
   
   - **server block**
   
     `server block`位于`http block`，用于配置虚拟主机，一个Nginx服务可包含多个虚拟主机，每个虚拟主机都可以独立的提供服务，因此借助Nginx，我们可以在一台服务器部署多个独立的网站，如下图所示
   
     <img src="images/nginx-虚拟主机.drawio.png" style="zoom:50%;" />
   
     每个虚拟主机使用一个`server block`进行配置，配置的内容包括
   
     - `listen`：虚拟主机监听的端口号。
     - `server_name`：指定虚拟主机的域名或者IP。
   
   - **location block**
   
     `location block`位于`server block`，用于配置请求的处理逻辑，一个`server block`中可以包含多个`location block`，例如
   
     ```nginx
     server {
         listen 80;
         server_name www.atguigu.com;
         location /index {
             root /var/www/html;
         }
     
         location /api {
             proxy_pass http://backend-api;
         }
     }
     ```

### 9.2.4 静态资源服务器案例

下面完成一个简单案例，使用Nginx作为静态资源服务器。

项目资料中有一个简单的前端项目`hello-nginx`，其中只包含html、css等静态资源，现将其部署在`server02`上。

1. **上传静态资源到服务器**

   将`hello-nginx.zip`上传到`server02`服务器任意路径。

2. **解压`hello-nginx.zip`到`/usr/share/nginx/html`中**

   ```bash
   unzip hello-nginx.zip -d /usr/share/nginx/html
   ```

   最终的路径结构如下

   ```tex
   /usr
   └── share
       └── nginx
           └── html
               └── hello-nginx
                   ├── css
                   │   └── style.css
                   ├── images
                   │   └── img.png
                   └── index.html
   ```

3. **配置Nginx虚拟主机**

   虚拟主机的配置应位于`/etc/nginx/nginx.conf`的**server block**中，由于`/etc/nginx/nginx.conf`的**http bolck**中引入了`/etc/nginx/conf.d/*.conf`，所以虚拟主机在`/etc/nginx/conf.d/`目录下的任意`.conf`文件配置即可。

   - 创建`/etc/nginx/conf.d/hello-nginx.conf`文件

     ```bash
     vim /etc/nginx/conf.d/hello-nginx.conf
     ```

   - 添加如下内容

     ```nginx
     server {
         listen       8080;
         server_name  192.168.10.102;
     
         location /hello-nginx {
             root   /usr/share/nginx/html;
             index  index.html;
         }
     }
     ```

   - 重新加载Nginx的配置文件

     ```bash
     systemctl reload nginx
     ```

4. **访问项目**

   访问路径为http://192.168.10.102:8080/hello-nginx，若部署成功，可见到如下页面

   <img src="images/Nginx案例-http服务.png" style="zoom: 67%;" />

5. **案例剖析**

   下面通过上述案例来了解Ngxin处理请求的逻辑。

   - **匹配server**

     由于Nginx中可存在多个虚拟主机的配置，故接收到一个请求后，Nginx首先要确定请求交给哪个虚拟主机进行处理。这很显然是根据`server_name`和`listen`进行判断的。例如上述的请求路径http://192.168.10.102:8080/hello-nginx，就会匹配到以下的虚拟主机

     ```nginx
     server {
         listen       8080;
         server_name  192.168.10.102;
     	......
     }
     ```

   - **匹配location**

     由于一个**server block**中可能包含多个**location block**，故Nginx在完成**server**匹配后，还要匹配**location**，**location**的匹配是根据请求路径进行判断的。例如以下写法`location`关键字后边的`/hello-nginx`就是匹配规则，它表达的含义是匹配以`/hello-nginx`为前缀的请求，例如上述的http://192.168.10.102:8080/hello-nginx请求就会匹配到该**location**，而

     http://192.168.10.102:8080/nginx则不会。

     ```nginx
     location /hello-nginx {
     	......
     }
     ```

   - **定位文件**

     完成**location**的匹配后，Nginx会以**location block**中的`root`作为根目录，然后查找请求路径对应的资源，例如以下配置

     ```nginx
     location /hello-nginx {
         root   /usr/share/nginx/html;
         index  index.html;
     }
     ```

     当请求http://192.168.10.102:8080/hello-nginx 时，Ngxin会在`/usr/share/nginx/html/hello-nginx`路径中查找资源，由于该路径为**目录**（而非文件），故Nginx会在该目录下寻找`index`，也就是上述配置的`index.html`。然后将`index.html`响应给客户端。至此，该请求的处理就结束了。

     **注意**：上述提到的**server_name**和**location**均有多种匹配模式，例如精确匹配、前缀匹配、正则匹配，此处不再展开。

### 9.2.5 反向代理案例

下面完成一个简单案例，使用Nginx作为反向代理。

使用Nginx反向代理其他网站，比如`http://www.atguigu.com`。

1. **配置虚拟主机**

   创建`/etc/nginx/conf.d/hello-proxy.conf`文件

   ```bash
   vim /etc/nginx/conf.d/hello-proxy.conf
   ```

   内容如下

   ``` nginx
   server {
       listen       9090;
       server_name  192.168.10.102;
   
       location / {
           proxy_pass http://www.atguigu.com;
       }
   }
   ```

2. **重新加载Nginx配置文件**

   ```bash
   systemctl reload nginx
   ```

3. **观察代理效果**

   使用浏览器访问http://192.168.10.102:9090，观察响应结果。

**注意**：借助反向代理功能，Nginx可以实现负载均衡等高级功能，此处不再展开。

## 9.3 配置域名映射

现实生活中，几乎所有的网站都是通过域名去访问。真正的域名需要付费购买，此处在宿主机本地配置一下域名映射，模拟一下域名的效果即可。

我们准备两个域名`lease.atguigu.com`和`admin.lease.atguigu.com`，前者用于访问移动端网站，后者用于访问后台管理系统。由于两个前端项目都部署在`server02`上，所以两个域名均指向`server02`的IP。

Windows的域名映射配置文件位于`C:\Windows\System32\drivers\etc\hosts`，需要使用管理员身份修改。使用管理员身份运行任意文本编辑器，然后使用其打开`hosts`文件，并增加如下内容：

```tex
192.168.10.102 lease.atguigu.com admin.lease.atguigu.com
```

修改完毕记得保存。

# 10. 项目部署

## 10.1 部署后端项目

### 10.1.1 打包

使用IDEA的maven插件对项目进行打包，完成后，在**web-admin**和**web-app**模块的`target`目录下找到`web-admin-1.0-SNAPSHOT.jar`和`web-app-1.0-SNAPSHOT.jar`。

### 10.1.2 安装JDK

根据前文的部署方案，需要在`server01`部署后端服务，因此需要在`server01`中安装JDK，本项目采用JDK17。

1. **获取JDK安装包**

将资料中提前下载好的JDK上传到`server01`，也在服务器执行以下命令可直接下载。

```bash
wget https://download.oracle.com/java/17/archive/jdk-17.0.8_linux-x64_bin.tar.gz
```

2. **解压JDK安装包**

执行以下命令将jdk解压到`/opt`目录

```bash
tar -zxvf jdk-17.0.8_linux-x64_bin.tar.gz -C /opt
```

3. **测试JDK安装效果**

执行以下命令，观察输出是否正常

```bash
/opt/jdk-17.0.8/bin/java -version
```

### 10.1.3 部署

1. **上传jar包**

   将后端项目的两个jar包上传到`server01`服务器的`/opt/lease`目录下，若目录不存在，自行创建即可。

2. **集成Systemd**

   为方便项目的启动、停止或者重启，我们同样使用Systemd来管理后端服务的进程。

   - **移动端集成Systemd**

     创建`lease-app.service`文件

     ```bash
     vim /etc/systemd/system/lease-app.service
     ```

     内容如下

     ```bash
     [Unit]
     Description=lease-app
     After=syslog.target
     
     [Service]
     User=root
     ExecStart=/opt/jdk-17.0.8/bin/java -jar /opt/lease/web-app-1.0-SNAPSHOT.jar 1>/opt/lease/app.log 2>&1
     SuccessExitStatus=143
     
     [Install]
     WantedBy=multi-user.target
     ```

   - **后台管理系统集成Systemd**

     创建`lease-admin.service`文件

     ```bash
     vim /etc/systemd/system/lease-admin.service
     ```

     内容如下

     ```bash
     [Unit]
     Description=lease-admin
     After=syslog.target
     
     [Service]
     User=root
     ExecStart=/opt/jdk-17.0.8/bin/java -jar /opt/lease/web-admin-1.0-SNAPSHOT.jar 1>/opt/lease/admin.log 2>&1
     SuccessExitStatus=143
     
     [Install]
     WantedBy=multi-user.target
     ```
     

3. **启动项目**

   执行以下命令启动两个后端项目。

   ```bash
   systemctl start lease-app
   systemctl start lease-admin
   ```

## 10.2 部署前端项目

### 10.2.1 Nginx配置概述

移动端和后台管理系统的前端项目均部署在`server02`的Nginx中，Nginx的配置思路如下图所示

<img src="images/部署架构图-详细.drawio.png" style="zoom:50%;" />

### 10.2.1 移动端

#### 10.2.1.1 打包

1. **明确前端请求的后端接口地址**

   打包之前需要明确前端请求的后台接口地址，根据前文的部署规划，前端请求后台接口时走的是Ngxin反向代理，也就是请求的地址为`http://lease.atguigu.com`。

   所以我们需要修改`.env.production`文件中`VITE_APP_BASE_URL`环境变量的值，修改结果如下

   ```ini
   VITE_APP_BASE_URL='http://lease.atguigu.com'
   ```

2. **构建项目**

   在项目的根目录执行以下命令

   ```bash
   npm run build
   ```

3. **查看打包结果**

   观察项目的根目录是否出现`dist`目录

#### 10.2.1.2 部署

1. **上传dist文件**

   将`rentHouseH5`项目编译得到`dist`文件上传至`server02`服务器的`/usr/share/nginx/html/app`目录下。

   最终的目录结构为

   ```tex
   /usr
   └── share
       └── nginx
           └── html
               └── app
                   ├── static
                   └── index.html
                   └── ...              
   ```

2. **编辑Nginx配置文件**

   创建`/etc/nginx/conf.d/app.conf`文件

   ```bash
   vim /etc/nginx/conf.d/app.conf
   ```

   内容如下

   ```nginx
   server {
       listen       80;
       server_name  lease.atguigu.com;
       
       location / {
           root   /usr/share/nginx/html/app;
           index  index.html;
       }
       location /app {
           proxy_pass http://192.168.10.101:8081;
       }
   }
   ```

3. **重新加载Nginx配置文件**

   执行以下命令重新加载配置文件

   ```bash
   systemctl reload nginx
   ```

4. **访问项目**

   访问http://lease.atguigu.com

### 10.2.2 后台管理系统

#### 10.2.2.1 打包

1. **明确前端请求的后端接口地址**

   后台管理系统的前端请求后端接口时，同样会走Nginx反向代理，故其请求的接口地址为`http://admin.lease.atguigu.com`。

   确保**rentHouseAdmin**项目中的`.env.production`文件中的`VITE_APP_BASE_URL`环境变量配置为如下内容

   ```ini
   VITE_APP_BASE_URL='http://admin.lease.atguigu.com'
   ```

2. **打包**

   在项目根目录执行以下命令

   ```bash
   npm run build
   ```

3. **查看打包结果**

   观察项目的根目录是否出现`dist`目录

#### 10.2.2.2 部署

1. **上传dist文件**

   将`rentHouseAdmin`项目编译得到`dist`文件上传至`server02`服务器的`/usr/share/nginx/html/admin`目录下。

   最终的目录结构为

   ```tex
   /usr
   └── share
       └── nginx
           └── html
               └── admin
                   ├── assets
                   └── index.html
                   └── ...
   ```

2. **编辑Nginx配置文件**

   创建`/etc/nginx/conf.d/admin.conf`文件

   ```bash
   vim /etc/nginx/conf.d/admin.conf
   ```

   内容如下

   ```nginx
   server {
       listen       80;
       server_name  admin.lease.atguigu.com;
       
       location / {
           root   /usr/share/nginx/html/admin;
           index  index.html;
       }
       location /admin {
           proxy_pass http://192.168.10.101:8080;
       }
   }
   ```

3. **重新加载Nginx配置文件**

   执行以下命令重新加载配置文件

   ```bash
   systemctl reload nginx
   ```

4. **访问项目**

   访问http://admin.lease.atguigu.com
