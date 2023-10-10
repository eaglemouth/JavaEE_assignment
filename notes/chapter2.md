# Java进阶语法

## Stream and IO

### 概念

Stream 是Java 8引入的新特性，它提供了一种更高级别的数据处理方式，用于处理集合数据。它可以用于对集合进行过滤、映射、排序、聚合等操作。而IO用于处理不同类型的数据流，主要功能是进行文件的读取和写入。

### 用法

写入文件和读取文件时，需要经常注意捕获和抛出异常，异常可以向上层抛出，但主函数最好要对捕获的异常进行处理。对于文件的打开和关闭，常常用到`try-finally`语句。这样可以保证资源使用完后被关闭。也可以采用更现代的`try-with-resources`语句。

```java
try(FileReader fr = new FileReader("test.txt")) {
  // 文件读取操作
} catch (IOException e) {
  e.printStackTrace();
} 
```

**异常处理相当重要，异常会经常出现，根据代码清晰的逻辑仔细分析可能出现的异常，对其进行合适的处理。**

## 泛型

### 注意

泛型的类型参数不能是基本数据类型,只能是引用类型。

泛型类型在实例化时要指定类型参数的具体类型。

Java的泛型在编译时具有类型检查，但在运行时会进行类型擦除。这意味着泛型类型信息在运行时不可用，只在编译时有效。例如，`List<String>`和`List<Integer>`在运行时都会变成`List`。

## 反射

### 概念

在Java中,每个类都对应一个Class类的对象,包含了该类的所有完整结构信息。通过这个Class对象就可以访问对应类的构造方法、字段、方法等。JVM加载类信息时会生成对应的Class对象,可以通过Class.forName(),对象实例的getClass()等方式获取到这个对象。

![](https://fastly.jsdelivr.net/gh/eaglemouth/PicGo/img/202309262030246.png)

### 用法

#### 获取类的几种方法

**使用类字面量**：

```java
Class<?> clazz = MyClass.class; // MyClass是你要获取的类名
```

**使用`Class.forName()`方法**：

```java
try {
    Class<?> clazz = Class.forName("com.example.MyClass"); // 根据类的完整路径名获取Class对象
} catch (ClassNotFoundException e) {
    e.printStackTrace();
}
```

**使用对象的`getClass()`方法**：

```java
MyClass myObject = new MyClass();
Class<?> clazz = myObject.getClass();
```

**使用`ClassLoader`**：

```java
ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
Class<?> clazz = classLoader.loadClass("com.example.MyClass");
```

#### 获取字段和方法

获取字段和方法比较类似，都有获取`public`、指定和全部的方法。需要注意的是，修改`private`字段时，需要使用`nameField.setAccessible(true);`语句允许私有属性读写。

## 注解

## Maven

Maven用于自动化构建Java项目。它定义了标准的项目结构和生命周期，使开发者能够轻松执行诸如编译、测试、打包、部署等构建任务。通过简单的命令，开发者可以执行这些任务，而不需要手动编写复杂的构建脚本。

##  单元测试

一般采用JUnit测试框架。

使用断言来验证代码的行为和结果。JUnit等测试框架提供了各种断言方法，例如`assertEquals`、`assertTrue`、`assertNull`等，用于检查预期结果是否与实际结果一致。

永远不要编写依赖于其他测试用例的测试，应该能够在任何时间、任何顺序运行任何测试。

## 补充

扁平化代码是更优秀的，要减少`if`嵌套语句，可以采用先处理非法条件的办法，如下示例。

```java
    private static void invokeInitMethod(Class clazz,Object obj) throws BootstrapException {
        for (Method method : clazz.getDeclaredMethods()) {
            if (!method.isAnnotationPresent(InitMethod.class)) {continue;}
            if (method.getParameterCount() > 0) {
                throw new BootstrapException(BootstrapException.ErrorType.INITMETHOD_ERROR,"带参数的方法不允许标注@InitMethod");
            }
            invokeMethod(Modifier.isStatic(method.getModifiers()) ?clazz:obj, method);
        }
    }
```

