# Spring Core

## 简介

容器是一种为某种特定组件的运行提供必要支持的一个软件环境。例如，Tomcat就是一个Servlet容器，它可以为Servlet的运行提供运行环境。类似Docker这样的软件也是一个容器，它提供了必要的Linux环境以便运行一个特定的Linux进程。

Spring的核心就是提供了一个IoC容器，它可以管理所有轻量级的JavaBean组件，提供的底层服务包括组件的生命周期管理、配置和组装服务、AOP支持，以及建立在AOP基础上的声明式事务服务等。

## IOC

IoC全称Inversion of Control，直译为控制反转。IOC容器主要做了对象创建和实例化，**依赖注入**。简单来讲，IOC就是一个为程序员创建对象的容器，使得程序员不再需要根据实现类创建对象。IOC容器中的Java对象被称为Java Beans。

## Spring Config

Spring的配置文件是一个固定格式的XML文件，Spring通过解析这个XML文件，实现相应的功能。

**注意**：注入属性(property)的时候，与之对应的是set方法名所确定的对象名，而不是形参名。

![](https://fastly.jsdelivr.net/gh/eaglemouth/PicGo/img/202310101614473.png)

## Beans的生命周期

Beans创建默认是单例(singleton)，通过修改scope属性为prototype，变为非单例。

创建顺序：调用构造函数创建对象-->init-->DI-->destroy

## 注解

前面的XML语法在不同的Spring技术中不尽相同，而Annotation却十分相似，需要掌握好它的用法。