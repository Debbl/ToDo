package run.aiwan.dao;

import run.aiwan.entity.Task;
import run.aiwan.entity.User;

import java.util.List;

public interface TaskDao {
    // 查询用户任务
    List<Task> queryTask(Task task);

    // 添加任务
    Integer addTask(Task task);

    // 删除任务
    Integer deleteTask(Task task);
}
