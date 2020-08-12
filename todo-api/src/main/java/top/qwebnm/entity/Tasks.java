package top.qwebnm.entity;

public class Tasks {
    private Integer count;
    private String task;
    private Integer tuid;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public Integer getTuid() {
        return tuid;
    }

    public void setTuid(Integer tuid) {
        this.tuid = tuid;
    }

    public Tasks() {
    }

    public Tasks(Integer count, String task, Integer tuid) {
        this.count = count;
        this.task = task;
        this.tuid = tuid;
    }
}
