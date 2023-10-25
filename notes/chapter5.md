"RoleDAO"，"Role"，"RoleController"，和 "IRoleService" 是通常在软件开发中出现的组件，它们之间的关系通常如下所示：

1. RoleDAO（数据访问对象）：
   - RoleDAO 是数据访问层的一部分，用于与数据库或其他数据存储系统进行交互，执行数据的持久化操作。
   - RoleDAO 主要负责存储和检索与角色相关的数据，如角色的信息、权限等。
   - RoleDAO 提供了一种将数据持久化到数据库的方式，以及从数据库中检索数据的方法。
2. Role（角色）：
   - Role 是应用程序的领域模型或实体，代表系统中的角色或权限。
   - Role 包含与角色相关的数据，如名称、描述、权限等。
   - Role 对象通常由业务逻辑层使用，以表示和操作角色的状态和属性。
3. IRoleService（接口）：
   - IRoleService 是应用程序的服务层接口，定义了与角色相关的业务逻辑操作的方法。
   - 这些方法可以包括创建、更新、删除角色，以及其他与角色相关的操作。
   - IRoleService 提供了一个抽象的方法集，以便业务逻辑层可以与角色进行交互，而不必关心具体的数据存储细节。
4. RoleController：
   - RoleController 是应用程序的控制器层，通常是基于某种框架（如Spring MVC或ASP.NET MVC）实现的。
   - 它接收来自客户端的HTTP请求，处理请求参数并调用适当的 IRoleService 方法来执行业务逻辑。
   - RoleController 负责将请求参数转化为业务操作，然后将结果传递给视图或客户端。

一般来说，数据流通常如下：

1. 客户端发起请求到 RoleController。
2. RoleController 接收请求，解析请求参数并调用适当的 IRoleService 方法，将请求转发给服务层。
3. IRoleService 方法根据请求执行业务逻辑，可能需要与 RoleDAO 交互以从数据库中检索或修改角色数据。
4. 数据库操作完成后，IRoleService 返回结果给 RoleController。
5. RoleController 选择适当的视图或格式化数据以响应客户端请求。

这种分层架构有助于实现单一职责原则，提高代码的可维护性和可测试性，并分离了不同层之间的关注点。

---

## Spring Data

采用ORM映射模型，主要利用MyBatis（MyBatis-Plus）

![](https://fastly.jsdelivr.net/gh/eaglemouth/PicGo/img/202310251003994.png)

## MyBatis

将SQL语句映射到Java语句，映射了数据库的行到Java对象的属性。

## MyBatis-Plus

所有MyBatis的功能都保留，相比MyBatis更加方便使用，增强了实用性。

MyBatis Plus要求你创建Mapper接口，它继承了`BaseMapper`接口，并且不需要写SQL语句。

MyBatis Plus支持乐观锁，你可以使用`@Version`注解标识乐观锁字段。

## Code Generation

代码生成器只需要连接好数据库，根据一定的配置信息，即可自动生成与数据库关联的Java程序代码。但是需要**注意在Mapper加上注解@Mapper**。

## Transaction

- **原子性（Atomicity）**：事务是一个不可分割的工作单元，要么全部成功执行，要么全部失败回滚。
- **一致性（Consistency）**：事务执行前后，数据库的完整性约束保持一致。
- **隔离性（Isolation）**：多个事务并发执行时，一个事务的执行不应影响其他事务的执行。
- **持久性（Durability）**：一旦事务成功提交，它对数据库的修改将永久保存。

使用了@Transactional注解，就可以在数据库操作失败时回滚到操作前的状态，并且在每个测试方法执行完后，数据库都要回滚一次，保障，每个测试方法不留下垃圾数据。