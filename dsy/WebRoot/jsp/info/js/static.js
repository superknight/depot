var param = {};
function reflushTable() {
	table.fnDraw(false);// 刷新表格数据false为当前页数，true为刷新到第一页
}
$(function(){
	
	var currentDTOpt = { 
            "ajax":{
             "url":"LcdStaticinfo.html",
             "type":"POST",
//             "data":{"data":param},
             "cache":false,//禁用缓存
             "dataType": "json",
             "contentType": 'application/json;charset=UTF-8',
             },
           "columns" : [ //数据列 
                    {
                        "mDataProp" : "id",
						"sClass" : "text-c",
						"bSortable": false,
						"mRender" : function(data, type, full) {
							var html = '<input type="checkbox" value="'
									+ data + '" title="' + data
									+ '" id="checkbox_' + data
									+ '" name="checkbox_' + data
									+ '" />';
							return html;
						}
                     },          
			{data : 'message',"className":"text-c"},
			{data : 'start_time',"className":"text-c"}, 
			{data : 'expire_time',"className":"text-c"}, 
			{data:null},
			{
				"mDataProp" : "status",
				"sClass" : "text-c",
				"mRender" : function(data, type, full) {
					var status = '未审核';
					var html="";
					if (data == '11') {
						status='已审核';
						html+='<span class="label label-success radius">'
							+status+'</span>';
					} else if (data == '00') {
						status = '未审核';
						html+='<span class="label label-info radius">'
							+status+'</span>';
					} else if (data == '10') {
						status = '不通过';
						html+='<span class="label label-danger radius">'
							+status+'</span>';
					}
					
					
					return html;
				}
				
			},
			{data : 'review_time',"className":"text-c"}, 
         ],
         "columnDefs":[
                       	{ //定制需要操作的列
                       		"targets":[4], 
                       		"data":"id",
                       		"sClass" : "text-c",
                       		// data:代表当前的值，full:代表当前行的数据
                       		"mRender" : function(data, type, full) {
                       			var html = '';
                       			html += '<a style="text-decoration:none" onClick="look_station(\''
                       				+full.related_station
                       				+'\',\'查看站台\',\'info-station.html\')" href="javascript:;">';
                       			html += '<i class="Hui-iconfont">&#xe665;</i>查看站台</a>';
                       			return html;
                       		}
                       	},
             { //定制需要操作的列
            "targets":[7], 
            "data":"id",
            "sClass" : "text-c",
			// data:代表当前的值，full:代表当前行的数据
			"mRender" : function(data, type, full) {
				var html = '';
				if(full.status=='11'){
					html += '';
				}else{
					html += '<a style="text-decoration:none" onClick="freeze(\''
						+data
						+'\')" href="javascript:;" title="冻结">';
					html += '<i class="Hui-iconfont">&#xe631;</i></a>';
				}
				return html;
			}
            }],
            "order":[[6, 'desc']],
          }
	var dtOption = $.extend({},DT_COMMON_CONFIG, currentDTOpt); // JSON 合并
	var table = $('.table-sort').DataTable(dtOption); // 渲染DT
	
    selectCheckAndTable(); //全选与table行选中
    
    $("#search").click(function() {
     		var json = $("#paramContainer").toJson();
     		$.extend(param, json);
     		table.draw(true);
     	});

    });

/*静态消息-审核*/
function article_shenhe(obj,id){
	layer.confirm('审核文章？', {
		btn: ['通过','不通过'], 
		shade: false
	},
	function(){
		$(obj).parents("tr").find(".td-manage").prepend('<a class="c-primary" onClick="article_start(this,id)" href="javascript:;" title="申请上线">申请上线</a>');
		$(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">已发布</span>');
		$(obj).remove();
		layer.msg('已发布', {icon:6,time:1000});
	},
	function(){
		$(obj).parents("tr").find(".td-manage").prepend('<a class="c-primary" onClick="article_shenqing(this,id)" href="javascript:;" title="申请上线">申请上线</a>');
		$(obj).parents("tr").find(".td-status").html('<span class="label label-danger radius">未通过</span>');
		$(obj).remove();
    	layer.msg('未通过', {icon:5,time:1000});
	});	
}

/*静态消息-添加*/
function static_add(title,url,w,h){
	var index = layer.open({
		type: 2,
		title: title,
		content: url,
		area: ['500px', '300px']
	});
}
/*静态消息-修改*/
function article_edit(title,url,w,h){
	var index = layer.open({
		type: 2,
		title: title,
		content: url,
		area: ['500px', '300px']
	});
}

/*查看-站台*/
function look_station(idArray,title,url){
	var index = layer.open({
		type: 2,
		title: title,
		content: url+"?idArray="+idArray,
		area: ['500px', '500px']
	});	
}