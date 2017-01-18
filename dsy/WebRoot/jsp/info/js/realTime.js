var param = {};
function reflushTable() {
	table.fnDraw(false);// 刷新表格数据false为当前页数，true为刷新到第一页
}
$(function(){
	$("#search").click(function() {
		var json = $("#paramContainer").toJson();
		$.extend(param, json);
		table.fnDraw(true);
	});

var table= $('.table-sort').dataTable({ 
	       "processing": true,
	       "serverSide": true,
	       "autoWidth":false, //自动获取宽度
	       "searching": false, //检索栏
	       "paging": true,
	       "retrieve": true,
	       "pagingType": "full_numbers", //分页类型
          "language": {
        	"lengthMenu": '<select class="form-control input-xsmall">' +
        	'<option value="1">1</option>' +'<option value="10">10</option>'
        	+ '<option value="20">20</option>' + '<option value="30">30</option>' 
        	+ '<option value="40">40</option>' + '<option value="50">50</option>'
        	+ '</select><span>条记录</span>',//左上角的分页大小显示。
        	
           "sSearch": '<span style="color:#F3745B">从当前数据中检索:</span>',//右上角的搜索文本，可以写html标签
            "oPaginate": {
            "sFirst": "首页",
            "sPrevious": "上页",
            "sNext": "下页",
            "sLast": "末页" 
              },
              "loadingRecords": "正在获取数据，请稍后..",
             "zeroRecords": "没有内容",//table tbody内容为空时，tbody的内容。//下面三者构成了总体的左下角的内容。
              "info": "总共_PAGES_ 页，共 _TOTAL_ 条记录 ",//左下角的信息显示，大写的词为关键字。
               "infoEmpty": "0条记录",//筛选为空时左下角的显示。
              "infoFiltered": ""//筛选之后的左下角筛选提示，
              },
            "ajax":{
             "url":"getRealTimeInfo.html",
             "type":"get",
//             "data":{"data":param},
             "cache":false,//禁用缓存
             "dataType": "json",
             "contentType": 'application/json;charset=UTF-8',
             },
             "initComplete": function( settings, json ) {
            	 $("#total").html("");
            	 $("#total").append(json.recordsTotal);
            	  },
          "aoColumns" : [ //数据列 
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
			{data : 'title'},
			{data : 'remark'},
			{data:null},
			{data : 'deadline',"bSortable": false,}, 
			{data:null},
			{data : 'operator'},
			{data : 'review_time',"bSortable": true,}, 
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
				
			}
         ],
         "columnDefs":[
             { //定制需要操作的列
            "targets":[3], 
            "data":"id",
            "sClass" : "text-c",
			// data:代表当前的值，full:代表当前行的数据
			"mRender" : function(data, type, full) {
				var html = '';
				html += '<a style="text-decoration:none" onClick="freeze(\''
						+data
						+'\')" href="javascript:;" title="冻结">';
					html += '<i class="Hui-iconfont">&#xe665;</i>查看图片</a>';
				return html;
				}
            },
            { //定制需要操作的列
                "targets":[5], 
                "data":"id",
                "sClass" : "text-c",
    			// data:代表当前的值，full:代表当前行的数据
    			"mRender" : function(data, type, full) {
    				var html = '';
    				html += '<a style="text-decoration:none" onClick="freeze(\''
    						+data
    						+'\')" href="javascript:;" title="冻结">';
    					html += '<i class="Hui-iconfont">&#xe665;</i>查看站台</a>';
    				return html;
    				}
                },
             ]
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
