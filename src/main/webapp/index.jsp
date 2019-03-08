<%--
  Created by IntelliJ IDEA.
  User: mask
  Date: 2019/2/16
  Time: 23:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <%
        pageContext.setAttribute("APP_PATH", request.getContextPath());
    %>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>员工列表</title>
    <script type="text/javascript" src="${APP_PATH}/static/js/jquery-3.3.1.js"></script>
    <link rel="stylesheet" href="${APP_PATH}/static/bootstrap-3.3.7-dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="${APP_PATH}/static/bootstrap-3.3.7-dist/css/bootstrap-theme.min.css">
    <script type="text/javascript" src="${APP_PATH}/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
</head>
<body>

<!-- add Modal -->
<div class="modal fade" id="empAddModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">新增用户</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label for="empName_add_input" class="col-sm-2 control-label">empName</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="empName" id="empName_add_input"
                                   placeholder="empName">
                            <span class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="email_add_input" class="col-sm-2 control-label">Email</label>
                        <div class="col-sm-10">
                            <input type="email" class="form-control" name="email" id="email_add_input"
                                   placeholder="email@juchuang.com">
                            <span class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">gender</label>
                        <div class="col-sm-10">
                            <label class="radio-inline">
                                <input type="radio" name="gender" id="gender1_add_input" value="M" checked> 男
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="gender" id="gender2_add_input" value="F"> 女
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="email_add_input" class="col-sm-2 control-label">deptName</label>
                        <div class="col-sm-4">
                            <select class="form-control" name="dId">
                            </select>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" id="emp_add_btn">添加</button>
            </div>
        </div>
    </div>
</div>
<%--end add modal--%>

<%--update modal--%>
<div class="modal fade" id="empUpdateModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">员工修改</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label for="empName_update_static" class="col-sm-2 control-label">empName</label>
                        <div class="col-sm-10">
                            <p class="form-control-static" id="empName_update_static"></p>
                            <span class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="email_update_input" class="col-sm-2 control-label">Email</label>
                        <div class="col-sm-10">
                            <input type="email" class="form-control" name="email" id="email_update_input"
                                   placeholder="email@juchuang.com">
                            <span class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">gender</label>
                        <div class="col-sm-10">
                            <label class="radio-inline">
                                <input type="radio" name="gender" id="gender1_update_input" value="M" checked> 男
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="gender" id="gender2_update_input" value="F"> 女
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="email_update_input" class="col-sm-2 control-label">deptName</label>
                        <div class="col-sm-4">
                            <select class="form-control" name="dId">
                            </select>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" id="emp_update_btn">修改</button>
            </div>
        </div>
    </div>
</div>
<%--end update modal--%>

<div class="container">
    <%--标题--%>
    <div class="row">
        <div class="col-sm-12">
            <h1>SSM-CRUD</h1>
        </div>
    </div>
    <%--按钮--%>
    <div class="row">
        <div class="col-md-4 col-md-offset-8">
            <button class="btn btn-primary" id="emp_add_modal_btn">新增</button>
            <button class="btn btn-danger"  id="emp_delete_modal_btn">删除</button>
        </div>
    </div>
    <%--显示表格数据--%>
    <div class="row">
        <div class="col-sm-12">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th><input type="checkbox" class="checkbox_all"></th>
                    <th>#</th>
                    <th>empName</th>
                    <th>gender</th>
                    <th>email</th>
                    <th>deptName</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>

                </tbody>
            </table>
        </div>
    </div>
    <%--显示分页信息--%>
    <div class="row">
        <%--分页文字信息--%>
        <div class="col-sm-6" id="page_info_area">

        </div>
        <%--分页条信息--%>
        <div class="col-sm-6" id="page_nav_area">

        </div>
    </div>
</div>
<script type="text/javascript">

    var totalRecord,currentNum;

    //1、页面发起请求以后，直接去发送一个ajax请求，要到分页
    $(function () {
        to_Page(1);
    })

    function to_Page(pn) {
        $.ajax({
            url: "${APP_PATH}/empsAjax",
            data: "pn=" + pn,
            type: "get",
            success: function (result) {
                //1、解析并显示员工数据
                build_emps_tabls(result);
                //2、解析并显示分页信息
                build_page_info(result);
                //3、解析并显示分页条信息
                build_page_nav(result);
            }
        })
    }

    function build_emps_tabls(result) {
        //清空
        $("tbody").empty();

        var emps = result.extend.pageInfo.list;
        $.each(emps, function (index, item) {
            var checkBoxTd=$("<td><input type='checkbox' class='checkbox_item'></input></td>");
            var empIdTd = $("<td></td>").append(item.empId);
            var empNameTd = $("<td></td>").append(item.empName);
            var empGenderTd = $("<td></td>").append(item.gender == 'M' ? "男" : "女");
            var empEmailTd = $("<td></td>").append(item.email);
            var empDeptName = $("<td></td>").append(item.department.deptName);


            //编辑按钮
            var editBtn = $("<button></button>").addClass("btn btn-primary btn-sm update_btn")
                .append($("<span></span>")
                    .addClass("glyphicon glyphicon-pencil")).append("编辑");
            //添加修改需要的ID
            editBtn.attr("edit-id",item.empId);

            //删除按钮
            var delBtn = $("<button></button>").addClass("btn btn-danger btn-sm del_btn")
                .append($("<span></span>")
                    .addClass("glyphicon glyphicon-trash")).append("删除");
            //添加删除需要的ID
            delBtn.attr("del-id",item.empId);
            //整合按钮操作列
            var btnTd = $("<td></td>").append(editBtn).append(" ").append(delBtn);
            $("<tr></tr>")
                .append(checkBoxTd)
                .append(empIdTd)
                .append(empNameTd)
                .append(empGenderTd)
                .append(empEmailTd)
                .append(empDeptName)
                .append(btnTd).appendTo("tbody");
        })
    }

    function build_page_info(result) {
        $("#page_info_area").empty();

        $("#page_info_area").append("当前" + result.extend.pageInfo.pageNum + "页,总" + result.extend.pageInfo.pages + "页,总" + result.extend.pageInfo.total + "条记录");
        totalRecord=result.extend.pageInfo.total;
        currentNum=result.extend.pageInfo.pageNum;
    }

    function build_page_nav(result) {
        $("#page_nav_area").empty();

        var ul = $("<ul></ul>").addClass("pagination");
        //首页按钮
        var firstPageLi = $("<li></li>").append($("<a></a>").append("首页").attr("href", "#"));
        //上一页
        var prePageLi = $("<li></li>").append($("<a></a>").append("&laquo;"));

        if (result.extend.pageInfo.hasPreviousPage == false) {
            firstPageLi.addClass("disabled");
            prePageLi.addClass("disabled");
        } else {
            //为元素添加事件
            firstPageLi.click(function () {
                to_Page(1)
            })
            firstPageLi.css("cursor", "hand");

            prePageLi.click(function () {
                to_Page(result.extend.pageInfo.pageNum - 1);
            })
            prePageLi.css("cursor", "hand");
        }


        var nextPageLi = $("<li></li>").append($("<a></a>").append("&raquo;"));

        var lastPageLi = $("<li></li>").append($("<a></a>").append("末页").attr("href", "#"));

        if (result.extend.pageInfo.hasNextPage == false) {
            nextPageLi.addClass("disabled");
            lastPageLi.addClass("disabled");
        } else {
            //为元素添加翻页事件
            nextPageLi.click(function () {
                to_Page(result.extend.pageInfo.pageNum + 1)
            })
            nextPageLi.css("cursor", "hand");

            lastPageLi.click(function () {
                to_Page(result.extend.pageInfo.pages)
            })
            lastPageLi.css("cursor", "hand");
        }


        ul.append(firstPageLi).append(prePageLi);

        $.each(result.extend.pageInfo.navigatepageNums, function (index, item) {
            var numLi = $("<li></li>").append($("<a></a>").append(item));
            if (result.extend.pageInfo.pageNum == item) {
                numLi.addClass("active");
            } else {
                numLi.click(function () {
                    to_Page(item)
                })
                numLi.css("cursor", "hand");
            }

            ul.append(numLi);
        })

        ul.append(nextPageLi).append(lastPageLi);
        var navEle = $("<nav></nav>").append(ul);
        navEle.appendTo("#page_nav_area");
    }
        //获取部门信息
    function get_Depts(ele) {
        $(ele).empty();
        $.ajax({
            type: "GET",
            url: "${APP_PATH}/getDepts",
            dataType:"json",
            success: function (result) {
                    $.each(result.extend.depts,function () {
                      var optionEle = $("<option></option>").append(this.deptName).attr("value",this.deptId)
                        optionEle.appendTo(ele);
                    })
            }

        })
    }
    //显示校验结果的提示信息
    function show_validate_msg(ele,status,msg) {
        //清楚元素之前的状态
        $(ele).parent().removeClass("has-error has-success");
        $(ele).next("span").text("");
        if (status=="success") {
            $(ele).parent().addClass("has-success");
            $(ele).next("span").text(msg);
        }else if(status=="error"){
            $(ele).parent().addClass("has-error");
            $(ele).next("span").text(msg);
        }
    }

    //校验表单数据
    function validate_add_form(){
        //1、拿到校验的数据，使用正则表达式
        var empName=$("#empName_add_input").val();
        var regName=/(^[a-z0-9_-]{6,16}$)|(^[\u2E80-\u9FFF]{2,5})/;
        if(!regName.test(empName)){
            show_validate_msg("#empName_add_input","error","用户名可以是2-5位中文或者6-16位英文和数字组合");
            return false;
        }else {
            show_validate_msg("#empName_add_input","success","");
        }
        //2、校验邮箱
        var email=$("#email_add_input").val();
        var regEmail=/^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/
        if(!regEmail.test(email)){
            show_validate_msg("#email_add_input","error","邮箱格式不正确");
            return false;
        }else{
            show_validate_msg("#email_add_input","success","");
        }
        return true;
    }

    //员工用户名校验
    $("#empName_add_input").change(function () {
        $.ajax({
            url:"${APP_PATH}/checkUser",
            data:"empName="+this.value,
            type:"GET",
            dataType:"json",
            success:function (result) {
                if (result.code==100){
                    show_validate_msg("#empName_add_input","success","用户名可用")
                    $("#emp_add_btn").attr("ajax-va","success");
                }else if(result.code==200){
                    show_validate_msg("#empName_add_input","error",result.extend.va_msg)
                    $("#emp_add_btn").attr("ajax-va","error");
                }
            }
        })
    })

    //点击保存，保存员工
    $("#emp_add_btn").click(function () {
        //1、校验提交数据表单

        //数据提交服务器端前校验
        /*if(!validate_add_form()){
            return false;
        }*/

        if($(this).attr("ajax-va")=="error"){
            return false;
        }

        $.ajax({
            url:"${APP_PATH}/saveEmp",
            data:$("#empAddModal form").serialize(),
            type:"POST",
            dataType:"json",
            success:function (result) {
                if(result.code==100){
                    alert(result.message);
                    //1、关闭模态框
                    $('#empAddModal').modal('hide');
                    //2、到最后一页
                    to_Page(totalRecord);
                }else{
                    //显示失败信息
                    if(undefined!=result.extend.errorFields.email){
                        //邮箱校验
                        show_validate_msg("#email_add_input","error",result.extend.errorFields.email);
                    }

                    if (undefined!=result.extend.errorFields.empName) {
                        //员工名称校验
                        show_validate_msg("#empName_add_input","error",result.extend.errorFields.empName);
                    }
                }
            }
        })
    })
    //绑定修改按钮单机事件
    $("#emp_update_btn").click(function () {
        //1、校验邮箱
        var email=$("#email_update_input").val();
        var regEmail=/^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/
        if(!regEmail.test(email)){
            show_validate_msg("#email_update_input","error","邮箱格式不正确");
            return false;
        }else{
            show_validate_msg("#email_update_input","success","");
        }
        //发送员工更新数据
        $.ajax({
            url:"${APP_PATH}/emp/"+$(this).attr("edit-id"),
            type:"PUT",
            data:$("#empUpdateModal form").serialize(),
            success:function (result) {
                alert(result.message)
                //2、隐藏修改模态框
                $("#empUpdateModal").modal("hide");
                //3、跳转到数据所在页面
                to_Page(currentNum);
            }
        })

    })

    //弹出修改模态框
    $(document).on("click",".update_btn",function () {
        //获取部门列表
        get_Depts("#empUpdateModal select");
        //根据id查询部门信息
        getEmp($(this).attr("edit-id"))
        //把ID传给更新按钮
        $("#emp_update_btn").attr("edit-id",$(this).attr("edit-id"));
        //弹出模态框
        $('#empUpdateModal').modal({
            backdrop: "static"
        })
    })


    //表单重置
    function reset_form(ele){
        $(ele)[0].reset();
        //情况表单样式
        $(ele).find("*").removeClass("has-error has-success");
        $(ele).find(".help-block").text("");
    }

    //弹出模态框
    $("#emp_add_modal_btn").click(function () {
        //清楚模态框表单内容
        reset_form("#empAddModal form");
        //获取部门列表
        get_Depts("#empAddModal select");
        //设置模态框内容
        $("#empAddModal").modal({
            backdrop: "static"
        })
    })
    //获取单个员工信息
    function getEmp(id) {
        $.ajax({
            url:"${APP_PATH}/emp/"+id,
            type:"GET",
            success:function (result) {
                var empData=result.extend.emp;
                $("#empName_update_static").text(empData.empName);
                $("#email_update_input").val(empData.email);
                $("#empUpdateModal input[name=gender]").val([empData.gender])
                $("#empUpdateModal select").val([empData.dId])
            }

        })
    }

    //单个员工删除
    $(document).on("click",".del_btn",function () {
        var empName=$(this).parents("tr").find("td:eq(2)").text();
        if (confirm("确认要删除【"+empName+"】吗？")){
            $.ajax({
                url:"${APP_PATH}/emp/"+$(this).attr("del-id"),
                type:"DELETE",
                success:function (result) {
                    alert(result.message)
                    //回到本页
                    to_Page(currentNum)
                }
            })
        }
    })
    //全选
    $(".checkbox_all").click(function () {
        //使用prop修改添加原生属性
        $(".checkbox_item").prop("checked",$(this).prop("checked"));
    })
    $(document).on("click",".checkbox_item",function () {
        var flag=$(".checkbox_item:checked").length==$(".checkbox_item").length;
        $(".checkbox_all").prop("checked",flag);
    })

    //提交修改数据
    $("#emp_delete_modal_btn").click(function () {
        var empsNames="";
        var del_idsstr="";
        $.each($(".checkbox_item:checked"),function () {
          empsNames+=$(this).parents("tr").find("td:eq(2)").text()+",";
          del_idsstr+=$(this).parents("tr").find("td:eq(1)").text()+"-";
        })
       empsNames= empsNames.substring(0,empsNames.length-1);
        del_idsstr=del_idsstr.substring(0,del_idsstr.length-1);
        if(confirm("确定要删除【"+empsNames+"】吗？")){
            $.ajax({
                url:"${APP_PATH}/emp/"+del_idsstr,
                type:"DELETE",
                success:function (result) {
                    alert(result.message)
                    to_Page(currentNum)
                }
            })
        }
    })
</script>
</body>
</html>
