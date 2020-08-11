package top.qwebnm.api;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.TreeMap;

public class getUserTasks extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {request.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/json; charset=utf-8");
//        response.setHeader("Access-Control-Allow-Origin", "*");
//        response.setHeader("Cache-Control","no-cache");
        // 输出
        PrintWriter out = response.getWriter();

        // user任务servlet
        JSONObject jsons = new JSONObject();
        if ("QueryTask".equals(request.getParameter("type"))){
            jsons = getTasks(request);
        }else if("AddTask".equals(request.getParameter("type"))){
            System.out.println("OK");
            jsons = addTask(request);
        }else {
            jsons.put("static","Parameter error");
        }
        out.print(jsons);
    }

    // 获取指定用户的任务
    public JSONObject getTasks(HttpServletRequest request){
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

        // 返回json数据
        JSONObject jsons = new JSONObject();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();
            String sql = "select count,task from todo_tasks where tuid = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,request.getParameter("tuid"));
            rs = ps.executeQuery();
            // rs指针移动到最后
            rs.last();
            // 任务数组
            int rsList = rs.getRow();
            // 指针移动到最前
            rs.beforeFirst();
            // 任务列表
            TreeMap<String, String> tasks1 = new TreeMap<>();
            String[] tasks = new String[rsList];

            for (int i = 0; i < rsList && rs.next(); i++) {
//                System.out.println(rs.getString("task"));
//                tasks[i] = rs.getString("task");
                tasks1.put(rs.getString("count"),rs.getString("task"));
            }
            jsons.put("tasks",tasks1);
            jsons.put("sum",rsList);
//            System.out.println(jsons);
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
    // 添加任务
    public JSONObject addTask(HttpServletRequest request) {
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

        // 返回json数据
        JSONObject jsons = new JSONObject();

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();
            String sql = "insert into todo_tasks set task = ?,tuid = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,request.getParameter("task"));
            ps.setInt(2,Integer.parseInt(request.getParameter("tuid")));
            System.out.println(sql);
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
        jsons.put("addTask",true);
        return jsons;
    }
}
