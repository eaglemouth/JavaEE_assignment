# AOP

## SoC关注点分离

在多个不同模块的横切关注点，抽象出Aspects，其实现称之为Advices，当需要的时候被weaved进去。

<img src="https://fastly.jsdelivr.net/gh/eaglemouth/PicGo/img/202310251513171.png" style="zoom:67%;" />

## Weaving in AOP

创建proxy对象

## Dynamic Proxy

创建动态代理对象，然后进行拦截。

## Pointcuts

不管拦截表达式是类还是对象或者其他的，拦截的都是方法

## Advices

### annotations

方法执行的位置（时机）

## Global Exception Handling

拦截所有throws的exception

@ExceptionHandler(...)

封装类不加泛型是错的。