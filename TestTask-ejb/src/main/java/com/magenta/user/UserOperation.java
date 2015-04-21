package com.magenta.user;

import com.magenta.task.Task;
import java.util.Collection;
import java.util.Date;
import javax.ejb.Remote;

@Remote
public interface UserOperation {

    public User loginOrCreate(String name);

    public User create(String username);

    public User update(User id);

    public User findByPrimaryKey(Integer id);

    public User findByName(String username);

    public Collection<User> findAll();

    public Collection<Task> getTask(User user);

    public void remove(Integer id);
}
