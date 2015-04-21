package com.magenta.task;

import com.magenta.user.User;
import java.util.Collection;
import java.util.Date;
import javax.ejb.Remote;

@Remote
public interface TaskOperation {

    public Task continueExistingOrCreate(String name, User user);

    public Task create(String taskName, Date lastStartTime, User user);

    public Task startExecution(String name, Date startDate, User user);

    public Task stopExecution(Integer id);

    public Task update(Task task);

    public Task findByPrimaryKey(Integer id);

    public Task findByName(String name, User user);

    public Collection<Task> findByUser(User user);

    public Collection<Task> findAll();

    public void remove(Integer id);
}
