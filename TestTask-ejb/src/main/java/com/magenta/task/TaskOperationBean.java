package com.magenta.task;

import com.magenta.user.User;
import java.util.*;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.*;
import org.hibernate.CacheMode;

@Stateless(name = "TaskOperation")

public class TaskOperationBean implements TaskOperation {

    @PersistenceContext(unitName = "COLIBRI", type = PersistenceContextType.EXTENDED)
    private EntityManager em;

    private final String SQL_SELECT_ALL = "select t from " + Task.class.getSimpleName() + " t";

    @Override
    public Task create(String taskName, Date lastStartTime, User user) {

        Task task = new Task();
        task.setIsActive(1);
        task.setTaskName(taskName);
        task.setLastStartTime(lastStartTime);
        task.setUser(user);

        em.joinTransaction();
        em.persist(task);

//        Collection<Task> tmp=user.getTasks();
//        tmp.add(task);
//        user.setTasks(tmp);
        return task;
    }

    @Override
    public Task update(Task task) {
        em.joinTransaction();

        return em.merge(task);
    }

    @Override
    public Task findByPrimaryKey(Integer id) {
        em.joinTransaction();
        return em.find(Task.class, id);
    }

    @Override
    public Task findByName(String name, User user) {
        Task task;
        em.joinTransaction();
        em.flush();
        Query user_query = em.createQuery("SELECT t FROM Task t where t.taskName = :value1 and t.user = :value2")
                .setParameter("value1", name);
        user_query.setParameter("value2", user);
        if (user_query.getResultList().isEmpty()) {
            return null;
        } else {
            task = (Task) user_query.getResultList().get(0);
        }

        return task;
    }

    @Override
    public Collection<Task> findAll() {
        em.joinTransaction();

        Query sql = em.createQuery(SQL_SELECT_ALL);
        return (Collection<Task>) sql.getResultList();
    }

    @Override
    public Collection<Task> findByUser(User user) {

        em.joinTransaction();

        Query sql = em.createQuery("SELECT t FROM Task t where t.user = :value1").setParameter("value1", user);

        sql.setHint("org.hibernate.cacheMode", CacheMode.REFRESH);
        return (Collection<Task>) sql.getResultList();
    }

    @Override
    public void remove(Integer id) {
        em.joinTransaction();
        em.remove(findByPrimaryKey(id));
    }

    @PreDestroy
    public void releaseResources() {
        System.out.println("Close EntityManager for TASK");
        if (em.isOpen()) {
            em.close();
        }
    }

    @Override
    public Task continueExistingOrCreate(String name, User user) {
        Task task = this.findByName(name, user);
        if (null != task) {
            task = this.startExecution(name, new Date(), user);
        } else {
            task = this.create(name, new Date(), user);
            task.setIsActive(1);
            task = this.update(task);
        }
        return task;
    }

    @Override
    public Task startExecution(String name, Date startDate, User user) {
        Task task = this.findByName(name, user);
        if (null != task) {
            task.setLastStartTime(startDate);
            task.setIsActive(1);
            task = this.update(task);
        }
        return task;
    }

    @Override
    public Task stopExecution(Integer id) {
        Task task = this.findByPrimaryKey(id);
        if (null != task) {
            Date date = new Date();
            int dif = (int) (date.getTime() - task.getLastStartTime().getTime()) / 1000;
            task.setTotalTime(task.getTotalTime() + dif);
            task.setIsActive(0);
            this.update(task);
        }
        return task;
    }
}
