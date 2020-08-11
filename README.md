### 项目介绍

- 该项目采用前后端分离技术前端负责数据的展示和与用户的交互，后端用来处理前端发来的请求。前后端通过json进行数据传输

### 实现功能

- 用户登录
- 用户注册
- 任务查询
- 任务添加
- 任务删除
- ~~使用cookie实现浏览器缓存，避免重复登录~~



### 后端使用技术

- Maven-3.3.9
- Tomcat-8.5.56
- servlet-3.1.0
- mysql-5.7.31

### 前端使用技术

- vue-2.6.11
- axios-v0.19.2

###  项目结构

```
ToDO
|--_sql sql脚本
|--todo-api 后端
|--todo-ui  前端
```

### 安装教程

- 使用Maven打包项目
- 生成的war包放到tomcat的webapps目录
- MySQL创建todo数据库，执行_sql文件下的sql脚本
- JDBC配置文件：ToDo/todo-api/src/main/resources/todoJdbc.properties 改成自己的数据库地址，用户名密码
- 前端配置文件：ToDo\todo-ui\js\todo.js 把文件中的apiUrl 改成自己的后端地址
- 演示地址：http://td.qwebnm.top





### API接口文档

**url为后端服务器的地址**

> 用户登录和注册
>
> - url: http://localhost:8080/todo/user
>
> - 请求方式: GET
>
> - 请求参数: 
>
> | 参数名 | 参数说明 |   值   |        备注        |
> | :----: | :------: | :----: | :----------------: |
> | uname  |  用户名  | 用户名 | 没有的用户自动添加 |
>
> - 查询用户响应内容:
>
> ```json
> {
>     "uid": "1",
>     "uname": "用户一",
>     "isAddUser": false,
>     "isRegistered": true
> }
> ```
>
> - 添加用户返回结果:
>
> ```json
> {
>     "isRegistered": false,
>     "isAddUser": true
> }
> ```
>
> 



>用户任务查询
>
>- url: http://localhost:8080/todo/tasks
>
>- 请求方式: GET
>
>- 请求参数:
>
>| 参数名 | 参数说明 |    值     |   备注   |
>| :----: | :------: | :-------: | :------: |
>|  type  | 请求类型 | QueryTask | 查询数据 |
>|  tuid  | 用户编号 | 用户编号  | 用户编号 |
>
>- 响应内容:
>
>```json
>{
>    "tasks": {
>      "1": "任务1",
>      "2": "任务2",
>      "3": "任务3"
>    },
>    "sum": 3
>}
>```



>添加任务
>
>- url: http://localhost:8080/todo/tasks
>
>- 请求方式: GET
>
>- 请求参数:
>
>| 参数名 | 参数说明 |    值    |   备注   |
>| :----: | :------: | :------: | :------: |
>|  type  | 请求类型 | AddTask  | 添加任务 |
>|  task  | 任务内容 | 任务内容 | 任务内容 |
>|  tuid  | 用户编号 | 用户编号 | 用户编号 |
>
>- 响应内容:
>
>```json
>{
>     "addTask": true
>}
>```
>



> 删除任务
>
> - url: http://localhost:8080/todo/delete
>
> - 请求方式:GET
>
> - 请求参数:
>
> | 参数名 | 参数说明 |    值    |   备注   |
> | :----: | :------: | :------: | :------: |
> | count  | 任务编号 | 任务编号 | 任务编号 |
>
> - 响应内容:
>
> ```json
> {
>     "deleteTask": true
> }
> ```
>

