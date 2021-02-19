package run.aiwan.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import run.aiwan.entity.Task;
import run.aiwan.service.TaskService;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping(value = "/todo")
public class TaskController {

    @Resource
    private TaskService taskService;

    @RequestMapping(value = "tasks")
    // 查询或添加用户任务数据
    public Map<Object, Object> doQueryOrAddTask(String type, Task task) {

        Map<Object, Object> jsons = new HashMap<>();

        if ("QueryTask".equals(type)) {

            List<Task> tasks = this.taskService.queryTaskService(task);
            Map<Object, Object> resultTasks = tasks.stream().collect(Collectors.toMap(e -> e.getCount(), e-> e.getTask()));
            jsons.put("tasks", resultTasks);
            jsons.put("sum", tasks.size());
            return jsons;
        } else if ("AddTask".equals(type)) {

            Integer addResult = this.taskService.addTaskService(task);
            if (addResult == 1) {
                jsons.put("addTask", true);
            } else {
                jsons.put("addTask", false);
            }
            return jsons;
        }

        return null;
    }

    @RequestMapping(value = "/delete")
    // 删除用户任务数据
    public Map<Object, Object> doDeleteTask(Task task) {

        HashMap<Object, Object> jsons = new HashMap<>();

        Integer deleteResult = this.taskService.deleteService(task);
        if (deleteResult == 1) {
            jsons.put("deleteTask", true);
        } else {
            jsons.put("deleteTask", false);
        }
        return jsons;
    }
}
