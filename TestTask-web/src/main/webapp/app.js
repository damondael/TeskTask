
/* global UserService */
var timerId;


function reload() {
    location.reload();
}
function stopTask() {
    clearInterval(timerId);
    var task_id = $("#current_task_id").val();
    UserService.stopExecution({taskId: task_id});
    loadTaskData();
}
function startTask() {
    clearInterval(timerId);
    var task_name = $("#task_name").val();
    UserService.startExecution({taskname: task_name});
    loadTaskData();
}

function loadTaskData() {

    var userId = $("#user_id").val();
    if (userId.length > 0) {
        var taskData = JSON.parse(UserService.loadTasksInfo({userId: userId}));
        if (taskData.hasOwnProperty("activeTask")) {
            activeTask = taskData.activeTask;

        }
        else {

        }
        data = taskData;

        var str = "";
        var active = 0;
        var exar = new Array();
        for (var tasklist in data) {


            for (var task in data[tasklist]) {

                if (exar.indexOf(data[tasklist][task].id) !== -1) {
                    continue;

                }
                else {
                    exar.push(data[tasklist][task].id);
                }
                if (data[tasklist][task].isActive === 1) {

                    active = 1;
                    $("#current_task_name").html("Текущая задача: " + data[tasklist][task].taskName);
                    $("#current_task_id").val(data[tasklist][task].id);
                    str += "<tr>";
                    str += "<td>" + data[tasklist][task].id + "</td>";
                    str += "<td>" + data[tasklist][task].taskName + "</td>";
                    str += "<td class=\"timer\">" + data[0][task].totalTime + "</td>";
                    str += "<td>" + data[tasklist][task].lastStartTime + "</td>";
                    str += "</tr>";
                    var time = parseInt(data[tasklist][task].totalTime);
                    //alert(time);
                    var start_time = Date.parse(data[tasklist][task].lastStartTime);
                    //alert(start_time);
                    //   alert(new Date().getTime());
                    timerId = setInterval(function () {

                        dif = (new Date().getTime() - start_time) / 1000 >> 0;
                        //  alert(dif-3600);
                        total = dif + time;

                        datetime = new Date(total * 1000);
                        $(".timer").html(datetime.getMinutes() + ":" + datetime.getSeconds());
                    }, 1000);

                } else {
                    datetime = new Date(data[tasklist][task].totalTime * 1000);
                    str += "<tr>";
                    str += "<td>" + data[tasklist][task].id + "</td>";
                    str += "<td>" + data[tasklist][task].taskName + "</td>";
                    str += "<td>" + datetime.getMinutes() + ":" + datetime.getSeconds() + "</td>";
                    str += "<td>" + data[tasklist][task].lastStartTime + "</td>";
                    str += "</tr>";
                }

                //  alert(data[0][task].id);
            }
        }
        $("#tasks_table").html(str);
        if (active === 1) {
            $("#current_task").show();
            $("#new_task").hide();
        }
        else {
            $("#current_task").hide();
            $("#new_task").show();
        }
    }
    else {
        $("#current_task").hide();
        $("#new_task").hide();

        $("#tasks_table").html("");
    }


}


function checkUser()
{
    //Collect input from html page
    var userName = $("#name").val();

    var message = JSON.parse(UserService.checkUser({name: userName}));
    $("#user_name").show();
    $("#user_name").html("Ваше имя:" + message.username);
    $("#user_id").val(message.id);
    $("#logout").show();
    $("#login_form").hide();
    //Use the REST API response
    loadTaskData();
    //  document.getElementById("error").innerHTML = "<h2><span style='color:red'>" + message + " !!</span></h2>";
}
function logout() {
    UserService.logout();
    $("#logout").hide();
    $("#login_form").show();
    $("#user_id").val("");
    $("#user_name").html();
    $("#user_name").hide();
    $("#tasks_table").html("");

    $("#current_task").hide();
    $("#new_task").hide();


    clearInterval(timerId);
    loadTaskData();
}
$(document).ready(function () {

    //Stops the submit request
    $("#myAjaxRequestForm").submit(function (e) {
        e.preventDefault();
    });
    loadTaskData();
    //$("#login_button").on('click',addUserForm());


});
