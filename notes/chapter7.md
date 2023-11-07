# Spring Security

![](https://fastly.jsdelivr.net/gh/eaglemouth/PicGo/img/202311011419282.png)

## Spring Security

*使用过滤链*

- Spring Security自带一个登录界面，也可以自定义。

- 可以在属性文件中配置用户名密码，作为登录凭据。如果不设置用户名密码，那么在服务启动时，会生成一个字符串，作为登陆密码。

- 认证的一般过程：

  <img src="https://fastly.jsdelivr.net/gh/eaglemouth/PicGo/img/202311071312135.png" style="zoom:67%;" />

  1. **拦截请求**：用户发送一个请求（例如，登录请求），`AuthenticationFilter`拦截这个请求。在大多数情况下，这是通过一个表单提交，但也可以是基于HTTP基本认证或其他认证机制。
  2. **生成Authentication对象**：`AuthenticationFilter`根据用户提交的凭据（如用户名和密码）创建一个`Authentication`对象。在这一步中，该对象通常是未验证的，它仅仅包含了用户提供的凭据。
  3. **请求AuthenticationManager验证**：创建的`Authentication`对象会被传递给`AuthenticationManager`，它负责验证这个对象。
  4. **委托给AuthenticationProvider**：`AuthenticationManager`将验证工作委托给一个或多个`AuthenticationProvider`。每个`AuthenticationProvider`都会尝试验证`Authentication`对象，并返回一个已填充用户权限的`Authentication`对象。
  5. **用户详细信息服务**：在验证过程中，`AuthenticationProvider`通常需要加载用户的详细信息。这是通过调用`UserDetailsService`实现的，它负责从数据库或其他存储系统中检索用户信息。
  6. **密码比对**：用户提交的密码会被`PasswordEncoder`加密后与存储在`UserDetailsService`返回的`UserDetails`对象中的密码进行比对。
  7. **创建已验证的Authentication对象**：如果用户的凭据有效，`AuthenticationProvider`会创建一个新的已验证的`Authentication`对象，其中包含用户的权限（通常称为“授权”）。
  8. **设置安全上下文**：一旦`Authentication`对象被验证，它会被设置到`SecurityContextHolder`中的`SecurityContext`里，为当前的会话提供认证信息。这意味着，一旦身份验证过程完成，应用程序的其他部分可以通过`SecurityContext`获取当前用户的详细信息。
  9. **处理成功或失败**：如果身份验证成功，`AuthenticationFilter`可以重定向到另一个页面或继续处理当前请求。如果认证失败，它可以重定向到错误页面或返回错误信息。

- 我们需要提供的两个Bean：

  <img src="https://fastly.jsdelivr.net/gh/eaglemouth/PicGo/img/202311071332013.png" style="zoom:67%;" />



## JWT Authentication

通过一个Cookie缓存，从JSESSIONID识别用户，使得之后的每次登录都不需要重复输入密码。这是过去的做法，现在使用JWT。

使用Token，每次HTTP请求都会将token加上，这样就能在不同的服务器上都能认证通过。