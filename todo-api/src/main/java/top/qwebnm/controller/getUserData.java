package top.qwebnm.controller;

import net.sf.json.JSONObject;
import top.qwebnm.dao.UserDao;
import top.qwebnm.entity.Users;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ResourceBundle;

@javax.servlet.annotation.WebServlet(name = "getUserData")
public class getUserData extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/json; charset=utf-8");

        // 输出对象
        PrintWriter out = response.getWriter();

        // 返回的json结果
        JSONObject jsons = new JSONObject();
        // 用户对象
        Users user = new Users(null,request.getParameter("uname"));
        
        jsons = UserDao.queryOrAddUsers(user);

        out.print(jsons);
    }
}

