package run.aiwan.service;

import run.aiwan.entity.Task;

import java.util.List;

public interface TaskService {
    // 查询用户任务
    List<Task> queryTaskService(Task task);

    // 添加任务
    Integer addTaskService(Task task);

    // 删除任务
    Integer deleteService(Task task);
}
