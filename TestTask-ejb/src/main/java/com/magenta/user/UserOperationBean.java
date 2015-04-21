package com.magenta.user;

import com.magenta.task.Task;
import java.util.*;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.*;

@Stateless(name = "operation")
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)

public class UserOperationBean implements UserOperation {

    @PersistenceContext(unitName = "COLIBRI", type = PersistenceContextType.EXTENDED)
    private EntityManager em;

    
    private final String SQL_SELECT_COMMON = "select a from " + User.class.getSimpleName() + " a";

    @Override
    public User create(String username) {
      
        User user = new User();
        user.setUsername(username);
        em.joinTransaction();
        em.persist(user);

    
        return user;
    }

    @Override
    public User update(User user) {
        em.joinTransaction();
        return em.merge(user);
    }

    @Override
    public User findByPrimaryKey(Integer id) {
        em.joinTransaction();
        return em.find(User.class, id);
    }

    @Override
    public User findByName(String name) {
        User user;
        em.joinTransaction();
        Query user_query = em.createQuery("SELECT u FROM User u where u.username = :value1")
                .setParameter("value1", name);
        if (user_query.getResultList().isEmpty()) {
            return null;
        } else {
            user = (User) user_query.getResultList().get(0);
        }

        return user;
    }

    @Override
    public User loginOrCreate(String name) {
        User user = findByName(name);
        if (null != user) {
            return user;
        } else {
            return create(name);
        }

    }

    @Override
    public Collection<User> findAll() {
        em.joinTransaction();
        Query sql = em.createQuery(SQL_SELECT_COMMON);
        return (Collection<User>) sql.getResultList();
    }

    @Override
    public void remove(Integer id) {
        em.joinTransaction();
        em.remove(findByPrimaryKey(id));
    }

    @PreDestroy
    public void releaseResources() {
        System.out.println("Close EntityManager for USER");
        if (em.isOpen()) {
            em.close();
        }
    }

    @Override
    public Collection<Task> getTask(User user) {
        em.joinTransaction();

        
        return user.getTasks();
    }
}
