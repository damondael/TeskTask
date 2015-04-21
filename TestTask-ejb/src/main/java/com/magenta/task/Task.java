package com.magenta.task;

import com.google.gson.annotations.Expose;
import com.magenta.user.User;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import static javax.persistence.SharedCacheMode.NONE;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
@Entity
@Table(name="TASKS")
@Cacheable(false)
public class Task implements Serializable {
    
    
    
    
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) @Column(name = "id")
    private Integer id;
     
   
    
    
    @Column(name="name", nullable=false, length=50)
   
    private String taskName;
    
    
    @Column(name="lastStartTime", nullable=false)
   
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date lastStartTime;
    
    
    @Column(name="totalTime", nullable=false)
    
    private int totalTime;
    @ManyToOne

    
    @JoinColumn(name="userId", nullable=false)
 
    private User user;
    
   
   
    @Column(name="isActive", nullable=false, columnDefinition = "int default 0")
    private Integer isActive;
    
    
    
    public Integer getIsActive(){
        return this.isActive;
    }
    
    
    public User getUser(){
        return this.user;
    }
    
    
    
    
    public int getTotalTime(){
        return this.totalTime;
    }
    public void setUser(User user){
        this.user = user;
    }
    
    
    public Date getLastStartTime(){
        return this.lastStartTime;
    }
    
    public void setTaskName(String taskName){
         this.taskName=taskName;
     }
   
    public void setTotalTime(int totalTime){
        this.totalTime = totalTime;
    }
    
     
     public void setLastStartTime(Date lastStartTime){
        this.lastStartTime = lastStartTime;
    }
    public void setIsActive(Integer isActive){
        this.isActive = isActive;
    }
    
    
    public Task() {}
    
    
   
 
  
    
    
 

}
