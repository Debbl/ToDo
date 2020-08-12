package top.qwebnm.dao;

import net.sf.json.JSONObject;
import top.qwebnm.entity.Tasks;
import top.qwebnm.utils.DBUtil;
import java.sql.*;
import java.util.TreeMap;

public class TasksDao {
    // 获得用户任务列表
    public static JSONObject getTasks(Tasks task) {
        // JDBC
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        // 返回json数据
        JSONObject jsons = new JSONObject();

        try {
            // 获取连接
            conn = DBUtil.getConnection();
            // sql
            String sql = "select count,task from todo_tasks where tuid = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,task.getTuid());
            rs = ps.executeQuery();
            // rs指针移动到最后
            rs.last();
            // 任务数组
            int rsList = rs.getRow();
            // 指针移动到最前
            rs.beforeFirst();
            // 任务列表 count、task
            TreeMap<String, String> tasks = new TreeMap<>();

            for (int i = 0; i < rsList && rs.next(); i++) {
                // 添加count、task
                tasks.put(rs.getString("count"),rs.getString("task"));
            }
            // jsons
            jsons.put("tasks",tasks);
            jsons.put("sum",rsList);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn,ps,rs);
        }
        return jsons;
    }
    public static JSONObject addTask(Tasks tasks) {
        // JDBC
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        // 返回json数据
        JSONObject jsons = new JSONObject();

        try {
            // 连接对象
            conn = DBUtil.getConnection();
            // sql
            String sql = "insert into todo_tasks set task = ?,tuid = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,tasks.getTask());
            ps.setInt(2,tasks.getTuid());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn,ps,rs);
        }
        // 返回json数据
        jsons.put("addTask",true);
        return jsons;
    }
    // 删除任务
    public static JSONObject delTask(Tasks tasks) {
        // JDBC
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        // 返回json数据
        JSONObject jsons = new JSONObject();

        try {
            // 连接对象
            conn = DBUtil.getConnection();
            //sql
            String sql = "delete from todo_tasks where count = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,tasks.getCount());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn,ps,rs);
        }
        jsons.put("deleteTask",true);
        return jsons;
    }
}
