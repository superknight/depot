function jsonDateFormat(jsonDate) {
            try {
                //var date = new Date(parseInt(jsonDate.replace("/Date(", "").replace(")/", ""), 10));
                //alert("jsondate:"+jsonDate);
                if(jsonDate.length==17){
                    return jsonDate ;
                }else{
                
                    var date  = new Date(jsonDate);
                    var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
                    var day = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
                    var hours = date.getHours();
                    var minutes = date.getMinutes();
                    var seconds = date.getSeconds();
                    //var milliseconds = date.getMilliseconds();
                    return date.getFullYear() + "-" + month + "-" + day + " " + hours + ":" + minutes + ":" + seconds ;//+ "." + milliseconds;
                }
            } catch (ex) {
                return "";
            }
        }

/**
 * 设置分页信息
 */
function setPagination(p) {
    $(p).pagination({
        pageSize: 20,//每页显示的记录条数，默认为10
        pageList: [10,20,30,40,50],//可以设置每页记录条数的列表
        beforePageText: '第',//页数文本框前显示的汉字
        afterPageText: '页    共 {pages} 页',
        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
    });
}
//检查手机或电话
var ValidatePhone = {
        isTel: function (value) {
            var patrn = /(\+86)?((\d{3,4})|(\d{3,4})-|)?\d{7,8}/;
            return  patrn.test(value);               
        },
        isMobile: function (value) {
            var validateReg = /(\+86)?1\d{10}/;
            return validateReg.test(value);
        }    
};

// 校验身份证合法性（15或18位）
function isValidCard(data) {
    var rep = /^(^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$)|(^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])((\d{4})|\d{3}[Xx])$)$/;
    return rep.test(data);
}

// 查询结果为空时，中间文字显示“暂无查询结果”
function setNoDataMsg(data) {
    if (data.total == 0) {
        $("#divNoData").remove();
        var div = $(".datagrid-view");
        div.append("<div id='divNoData' style='padding-top:" +(div.height()/2-15)+"px; padding-left:"+(div.width()/2-40)+"px;'>暂无查询结果</div>");
    } else {
        $("#divNoData").remove();
    }
}

// 加载角色
function loadRole(){
    $.ajax({
        url:"getRoleName.html",
        type:"post",
        dateType:"json",
        success:function(data){
            $("#role").find("option").remove(); 
            var list=$.parseJSON(data);
            $("<option selected='selected' value=''>不限</option>").appendTo($("#role"));
            for (var i=0; i<list.length; i++) {
                var str = "<option value='" + list[i].roleId+"'>" + list[i].roleName + "</option>";
                $(str).appendTo($("#role"));
            }
        },
        error:function(data){
            //$.messager.alert('提示',"加载角色失败！",'info');
        }
    });
}

//加载部门
function loadDept(){
    $.ajax({
        url:"getDeptName.html",
        type:"post",
        dateType:"json",
        success:function(data){
            $("#dept").find("option").remove(); 
            var list=$.parseJSON(data);
            $("<option selected='selected' value=''>不限</option>").appendTo($("#dept"));
            
            for (var i=0; i<list.length; i++) {
                var str = "<option value='" + list[i].deptId+"'>" + list[i].deptName + "</option>";
                $(str).appendTo($("#dept"));
            }
        },
        error:function(data){
            //$.messager.alert('提示',"加载角色失败！",'info');
        }
    });
}
// 加载界面时，相关样式绑定事件，添加输入规则
jQuery(function($) {
    // class="carNo" 车牌号仅支持英文/数字输入，默认加载显示"粤”为前缀
    $(".carNo").bind("keyup", function() {
        // 获取当前文本框的值
        var value = $(this).val();
        // 值不为空，替换不是英文或数字的输入为空
        if (value != "") {
            value = "粤" + value.replace(/[^\a-\z\A-\Z0-9]/g, "");
        }
        // 值只等于“粤”，则替换为空
        if (value == "粤") {
            value = "";
        }
        $(this).val(value);
    });
    
    // class="onlyCharNum" 仅支持英文/数字输入
    $(".onlyCharNum").bind("keyup", function() {
        // 获取当前文本框的值
        var value = $(this).val();
        // 替换不是英文或数字的输入为空
        value = value.replace(/[^\a-\z\A-\Z0-9]/g, "");
        $(this).val(value);
    });
    
    // class="onlyCHNChar" 仅支持中文输入
    $(".onlyCHNChar").bind("keyup", function() {
        // 获取当前文本框的值
        var value = $(this).val();
        // 替换不是中文的输入为空
        value = value.replace(/[^\u4E00-\u9FA5]/g, "");
        $(this).val(value);
    });
    /*$(".onlyCHNChar").bind("blur", function() {
        // 获取当前文本框的值
        var value = $(this).val();
        // 替换不是中文的输入为空
        value = value.replace(/[^\u4E00-\u9FA5]/g, "");
        $(this).val(value);
    });*/
    
    // class="onlyNum" 仅支持数字输入
    $(".onlyNum").bind("keyup", function() {
        // 获取当前文本框的值
        var value = $(this).val();
        // 替换不是数字的输入为空
        value = value.replace(/[^0-9]/g, "");
        $(this).val(value);
    });
    /*$(".onlyNum").bind("blur", function() {
        // 获取当前文本框的值
        var value = $(this).val();
        // 替换不是数字的输入为空
        value = value.replace(/[^0-9]/g, "");
        $(this).val(value);
    });*/
});
