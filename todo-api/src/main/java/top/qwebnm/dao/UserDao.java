package top.qwebnm.dao;

import net.sf.json.JSONObject;
import top.qwebnm.entity.Users;
import top.qwebnm.utils.DBUtil;
import java.sql.*;

public class UserDao {

    // 查询或添加用户
    public static JSONObject queryOrAddUsers(Users user) {
        // 返回json对象
        JSONObject jsons = new JSONObject();

        // JDBC
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            // 获取连接
            conn = DBUtil.getConnection();
            // sql语句
            String sql = "select uid,uname from todo_user where uname = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,user.getUname());
            rs = ps.executeQuery();
            if (!rs.next()){
                addUser(user.getUname());
                jsons.put("isRegistered",false);
                jsons.put("isAddUser",true);
                return jsons;
            }else{
                jsons.put("uid",rs.getString("uid"));
                jsons.put("uname", rs.getString("uname"));
                jsons.put("isAddUser",false);
                jsons.put("isRegistered",true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn,ps,rs);
        }
        return jsons;
    }

    // 添加用户
    public static void addUser(String uname) {
        // JDBC
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            // 获取连接
            conn = DBUtil.getConnection();
            // sql语句
            String sql = "insert into todo_user set uname=?";

            ps = conn.prepareStatement(sql);
            ps.setString(1,uname);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn,ps,null);
        }
    }
}
