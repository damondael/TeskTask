
<%@page import="java.io.IOException"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.util.Properties"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Collection"%>
<%@page import="javax.naming.NamingException"%>
<%@page import="javax.rmi.PortableRemoteObject"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="java.util.Locale"%>
<%@page import="com.magenta.user.User"%>

<%@page import="com.magenta.user.UserOperation"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="./js"></script>
        <link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css" />

        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.js" type="text/javascript"></script>
        <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js" type="text/javascript"></script>
        <script type="text/javascript" src="app.js"></script>
        <title>JSP Page</title>
    </head>


    <body>
        <% if(session.getAttribute("user")!=null){
        User user= (User)session.getAttribute("user");
              
        %>
        <span id="user_name">Ваше имя:<%=user.getUsername()%></span>
        <input type="hidden" id="user_id" value="<%=user.getId()%>"/>
        <%}
      else{
        %>
        <input type="hidden" id="user_id" value=""/>
        <span id="user_name" style="display: none">Ваше имя:</span>
        <%
                  
      }%>

        <form id="new_task" style="display: none" action="">
            <input type="text" name="task_name" id="task_name"/>
            <input id="start_task_button" type="button" value="Начать задачу" onClick="startTask()"/>
        </form>


        <form id="current_task" style="display: none" action="">
            <span id="current_task_name"></span>
            <input type="hidden" id="current_task_id" value=""/>
            <input id="stop_task_button" type="button" value="Прекратить выполнение задачи" onClick="stopTask()"/>
        </form>
        <%!
            
          
           public void jspInit() {
                
              
                 
                    
                 
                    


              
           }
        
            
           
        %>
        <h1>



        </h1>


        <button id="reload" onClick="reload()" style="display: block">
            Перезагрузить страницу
        </button>
        <% if(session.getAttribute("user")!=null){ %>
        <p>
            <button id="logout" onClick="logout()" style="display: block">
                Выход
            </button>



        </p>

        <form id="login_form" method="POST"  style="display: none">
            <fieldset>
                <legend>Введите свое имя(50 символов макс)</legend>

                <p>
                    <label for="name">Имя:</label>

                    <input id="name" type="text" name="name" />
                </p>
                <p>
                    <input id="login_button" type="button" value="Войти" onClick="checkUser()"/>
                </p>
            </fieldset>
        </form>
        <%  }
          else{
        %><p>
            <button id="logout" onClick="logout()" style="display: none">
                Выход
            </button>

        </p>

        <form id="login_form" method="POST"  style="display: block">
            <fieldset>
                <legend>Введите свое имя(50 символов макс)</legend>

                <p>
                    <label for="name">Имя:</label>

                    <input id="name" type="text" name="name" />
                </p>
                <p>
                    <input id="login_button" type="button" value="Войти" onClick="checkUser()"/>
                </p>
            </fieldset>
        </form>

        <% }%>


        <div id="error"></div>



        <table border="10" style="width: 100%">

            <tr>
                <th>id</th>
                <th>Название</th>
                <th>Общее время</th>
                <th>Последнее время</th>
            </tr>
            <tbody id="tasks_table">

            </tbody>
        </table>

    </body>
</html>
