# Spring Boot

## Web App基础

通过HTTP协议传输，通过客户端向服务器端请求页面，现在是前后端分离的。

## Spring Boot使用

### Rest API

REST API（Representational State Transfer Application Programming Interface）是一种基于HTTP协议的架构风格，用于创建分布式系统中的Web服务。它强调使用HTTP方法（例如GET、POST、PUT、DELETE）来执行对资源的操作，并使用标准HTTP状态码来表示操作结果。

![](https://fastly.jsdelivr.net/gh/eaglemouth/PicGo/img/202310180947090.png)

<img src="https://fastly.jsdelivr.net/gh/eaglemouth/PicGo/img/202310180948254.png" style="zoom:67%;" />

### 注解

**RestController**：声明一个restful API

**RequestMapping**：将HTTP请求映射到特定的处理方法

**GetMapping**：GET请求的映射

**PostMapping**：POST请求的映射

**PutMapping**：PUT请求的映射

**DeleteMapping**：DELETE请求的映射

GET API:

**Path Variable**：parameter in path

**ResponseEntity**：a wrapper class for Rest response.

•**ok: HTTP state code is 200**

•**noContent: HTTP state code is 204**

**Query Parameter：** **the parameters in the form of “queryName=value”** **No annotaion is required**

POST API:

**RequestBody:** **the parameter is deserialized from the request body**