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

public class getUserTasks extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {request.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/json; charset=utf-8");
        // 输出
        PrintWriter out = response.getWriter();

        // 返回json数据
        JSONObject jsons = new JSONObject();
        // 任务对象
        Tasks task = new Tasks();

        if ("QueryTask".equals(request.getParameter("type"))){
            task.setTuid(Integer.parseInt(request.getParameter("tuid")));
            jsons = TasksDao.getTasks(task);
        }else if("AddTask".equals(request.getParameter("type"))){
            task.setTask(request.getParameter("task"));
            task.setTuid(Integer.parseInt(request.getParameter("tuid")));
            jsons = TasksDao.addTask(task);
        }else {
            jsons.put("static","Parameter error");
        }
        out.print(jsons);
    }
}
