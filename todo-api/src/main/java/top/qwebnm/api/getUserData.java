package top.qwebnm.api;

import net.sf.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ResourceBundle;
import java.sql.*;

@javax.servlet.annotation.WebServlet(name = "getUserData")
public class getUserData extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/json; charset=utf-8");
//        response.setHeader("Access-Control-Allow-Origin", "*");
//        response.setHeader("Cache-Control","no-cache");
        // 输出
        PrintWriter out = response.getWriter();

        // user任务servlet
        JSONObject jsons = new JSONObject();
        jsons = queryOrAddUsers(request);
        out.print(jsons);
    }

    // 查询或添加用户
    public JSONObject queryOrAddUsers(javax.servlet.http.HttpServletRequest request) {

        // todo_user数据表
        Connection conn = null; // 数据库连接对象
        Statement stmt = null; // 数据库操作对象
        ResultSet rs = null; // 查询结果集
        PreparedStatement ps = null;

        // 获取连接参数
        ResourceBundle bundle = ResourceBundle.getBundle("todoJdbc");
        String url = bundle.getString("jdbc.url");
        String user = bundle.getString("jdbc.username");
        String password = bundle.getString("jdbc.password");

        // 返回json数据
        JSONObject jsons = new JSONObject();

        // 查询或添加用户
        try {
            // 注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            // 获取数据库连接
            conn = DriverManager.getConnection(url,user,password);
            // 获取数据库操作对象
            stmt = conn.createStatement();
            // sql语句
            String sql = "select uid,uname from todo_user where uname = ?";
            // 执行sql语句
            ps = conn.prepareStatement(sql);
            // 用户名参数
            String uname = request.getParameter("uname");
            ps.setString(1,uname);
            rs = ps.executeQuery();
            if (!rs.next()){
               addUser(uname);
               jsons.put("isRegistered",false);
               jsons.put("isAddUser",true);
               return jsons;
            }else{
                jsons.put("uid",rs.getString("uid"));
                jsons.put("uname", rs.getString("uname"));
                jsons.put("isAddUser",false);
                jsons.put("isRegistered",true);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            // 释放资源
            if (stmt != null){
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return jsons;

    }
    public void addUser(String uname){

        // user数据表
        Connection conn = null; // 数据库连接对象
        Statement stmt = null; // 数据库操作对象
        ResultSet rs = null; // 查询结果集
        PreparedStatement ps = null;

        // 获取连接参数
        ResourceBundle bundle = ResourceBundle.getBundle("todoJdbc");
        String url = bundle.getString("jdbc.url");
        String user = bundle.getString("jdbc.username");
        String password = bundle.getString("jdbc.password");

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();
            String sql = "insert into todo_user set uname=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,uname);
            ps.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            // 释放资源
            if (stmt != null){
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
