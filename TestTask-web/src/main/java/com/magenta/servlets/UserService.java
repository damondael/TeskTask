/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magenta.servlets;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.graph.GraphAdapterBuilder;
import com.magenta.task.Task;
import com.magenta.task.TaskOperation;
import com.magenta.user.User;
import com.magenta.user.UserOperation;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import static javax.ws.rs.HttpMethod.GET;
import static javax.ws.rs.HttpMethod.PUT;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;



/**
 *
 * @author server
 */
@Stateless
@Path("/rest")
public class UserService
{
    private User cur_user=null;
    
    
    @EJB
    private UserOperation userEJB;
    
    @EJB
    private TaskOperation taskEJB;
    @Path("/check")
    @PUT
    public Response checkUser(@QueryParam("name") String userName,
            @Context HttpServletRequest request
          
           ) 
    {
  
        HttpSession session = request.getSession();
    
        User user =userEJB.findByName(userName);
     
       // Gson gson = new Gson();
        Map<String, String> map = new Hashtable<String, String>();
        map.put("authorized", "true");
        
       String json ="";
          
                  GsonBuilder gsonBuilder = new GsonBuilder();
                new GraphAdapterBuilder()
                    .addType(Task.class)
                    .registerOn(gsonBuilder);
                Gson gson = gsonBuilder.create();
        if(null!=user){
              cur_user=user; 
              
                  session.setAttribute("user", cur_user);
//              map.put("tasks",gson.toJson(cur_user.getTasks()));
               
                json =  gson.toJson(cur_user);
             
              return Response.ok().entity(json).build();
        }
        else{
            
               user = userEJB.create(userName);
                cur_user=user;
                
                     session.setAttribute("user", cur_user);
             
                json =  gson.toJson(cur_user);
              return Response.status(Response.Status.CREATED).entity(json).build();
       }
      
      
    }
    @Path("/logout")
    @GET
     public Response logout(
            @Context HttpServletRequest request
             
           ) {
         
         HttpSession session = request.getSession();
         session.removeAttribute("user");
         cur_user=null;
         return Response.ok().build();
    }
//    private String loadTasks(){
//         Gson gson = new Gson();
//        String tasksString=null;
//
//        if(cur_user!=null){
//            tasksString =gson.toJson(cur_user.getTasks() ) ;
//        }
//        return tasksString;
//    }
    @Path("/taskinfo")
    @GET
     public Response loadTasksInfo(@QueryParam("userId") Integer userId){
         User user = userEJB.findByPrimaryKey(userId);
         Collection<Task> taskList= taskEJB.findByUser(user);
       
         GsonBuilder gsonBuilder = new GsonBuilder();
         new GraphAdapterBuilder()
                 .addType(Task.class)
                 .registerOn(gsonBuilder);
        

          return Response.ok().entity(gsonBuilder.create().toJson(taskList)).build();
     }
    @Path("/starttask")
    @POST
    public Response startExecution(@QueryParam("taskname") String taskName,
        @Context HttpServletRequest request){
       User user = (User)request.getSession().getAttribute("user");
         taskEJB.continueExistingOrCreate(taskName,userEJB.findByPrimaryKey(user.getId()));
         return Response.ok().build();        
     }
    @Path("/stoptask")
    @POST
     public Response stopExecution(@QueryParam("taskId") Integer taskId){
         taskEJB.stopExecution(taskId);
         return Response.ok().build();
     }
    
}
