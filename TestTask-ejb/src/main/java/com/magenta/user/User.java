package com.magenta.user;

import com.magenta.task.Task;
import java.io.Serializable;
import java.util.*;
import javax.persistence.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name="USERS")
@Cacheable(false)
public class User implements Serializable {
    
    
    
    
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) @Column(name = "id")
    private Integer id;
     
   public Integer getId(){
       return this.id;
   }
    
    
    @Column(name="USERNAME", nullable=false, length=50)
   
    
    private String username;
    
    public String getUserName(){
        return this.username;
    }
    
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
     @org.hibernate.annotations.Cascade({ 
        org.hibernate.annotations.CascadeType.PERSIST,
        org.hibernate.annotations.CascadeType.MERGE,
        org.hibernate.annotations.CascadeType.REFRESH,
        org.hibernate.annotations.CascadeType.SAVE_UPDATE,
        org.hibernate.annotations.CascadeType.REPLICATE,
        org.hibernate.annotations.CascadeType.LOCK,
        org.hibernate.annotations.CascadeType.DETACH })
 
    private Collection<Task> tasks;
    
   
    public Collection<Task> getTasks() {
		return this.tasks;
    }
    public void setTasks(Collection<Task> tasks) {
		this.tasks = tasks;
	}
    public void setUsername(String username){
         this.username=username;
     }
    public String getUsername(){
        return this.username;
    }
   
    public User() {this.tasks = null;
}
    
    public User( String username) {
        this.tasks = null;
        
        this.username = username;
       
    }
    public User(Integer id, String username) {
        this.tasks = null;
        this.id = id;
        this.username = username;
     
    }
 
  
    
    
 

}
