package run.aiwan.service.impl;

import org.springframework.stereotype.Service;
import run.aiwan.dao.TaskDao;
import run.aiwan.entity.Task;
import run.aiwan.service.TaskService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Resource
    private TaskDao taskDao;

    @Override
    public List<Task> queryTaskService(Task task) {
        return this.taskDao.queryTask(task);
    }

    @Override
    public Integer addTaskService(Task task) {
        return this.taskDao.addTask(task);
    }

    @Override
    public Integer deleteService(Task task) {
        return this.taskDao.deleteTask(task);
    }
}
