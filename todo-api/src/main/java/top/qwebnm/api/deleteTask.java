package top.qwebnm.api;

import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ResourceBundle;

public class deleteTask extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/json; charset=utf-8");
//        response.setHeader("Access-Control-Allow-Origin", "*");
//        response.setHeader("Cache-Control","no-cache");
        // 输出
        PrintWriter out = response.getWriter();
        // 返回json数据
        JSONObject jsons = new JSONObject();
        jsons = delTask(request);
        out.print(jsons);
    }
    public JSONObject delTask(HttpServletRequest request){
        // todo_tasks数据表
        Connection conn = null; // 数据库连接对象
        Statement stmt = null; // 数据库操作对象
        PreparedStatement ps = null;

        // 获取连接参数
        ResourceBundle bundle = ResourceBundle.getBundle("todoJdbc");
        String url = bundle.getString("jdbc.url");
        String user = bundle.getString("jdbc.username");
        String password = bundle.getString("jdbc.password");

        // 返回json数据
        JSONObject jsons = new JSONObject();

        try {
            // 注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            // 获取数据库连接
            conn = DriverManager.getConnection(url,user,password);
            // 获取数据库操作对象
            stmt = conn.createStatement();
            // sql语句
            String sql = "delete from todo_tasks where count = ?";
            // 执行sql语句
            ps = conn.prepareStatement(sql);
            ps.setInt(1,Integer.parseInt(request.getParameter("count")));
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
        jsons.put("deleteTask",true);
        return jsons;
    }
}
