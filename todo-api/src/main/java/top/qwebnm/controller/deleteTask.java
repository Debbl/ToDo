package top.qwebnm.controller;

import net.sf.json.JSONObject;
import top.qwebnm.dao.TasksDao;
import top.qwebnm.entity.Tasks;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class deleteTask extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/json; charset=utf-8");
        // 输出
        PrintWriter out = response.getWriter();
        // 返回json数据
        JSONObject jsons = new JSONObject();
        // 任务对象
        Tasks task = new Tasks();
        task.setCount(Integer.parseInt(request.getParameter("count")));
        // 删除用户指定任务
        jsons = TasksDao.delTask(task);
        out.print(jsons);
    }
}
