var flag=true;
$(function(){
	var str="";
	$.ajax({
		"url":"menu.html",
		"type":"post",
		"success":function(data){	
			var returnData=$.parseJSON(data);
			var list=returnData.data;
			for(var i=0;i<list.length;i++) {
			    str+='<dl id="menu-article"><dt id="'+list[i].menuId+'" onclick="OpenAndClose('+list[i].menuId+')">'
			    +'<i class="Hui-iconfont" style="font-size:20px;">'+list[i].icon+'  </i>'+list[i].menuName
				+'<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt><dd><ul>';
			    if(list[i].menu==null||list[i].menu=="")
		    		str+='<li style="color:red;padding-left:20px;">亲，没有子菜单</li>'
		    	else{
		    	var array=list[i].menu;
		    	for(var j=0;j<array.length;j++){
			    	str+='<li style="padding-left:10px;"><a id="'+array[j].menuId+'" _href="'+array[j].url+
			    	'" href="javascript:void(0)">'
			    	+array[j].menuName+'</a></li>';
			    }
		    	}
			    str+='</ul></dd></dl>';
			 }
			 $("#nav").html("");
			 $(str).appendTo("#nav");
          }
	});
});
function OpenAndClose(id){
	var str="#"+id;
	if(!flag){
		$(str).next().slideUp(200,function(){
			$(str).find(".menu_dropdown-arrow").html("&#xe6d5;");
		});
		flag=true;
		}
	else{
	$(str).next().slideDown(200,function(){
//		$(str).children().css("transform","rotate(180deg)");
		$(str).find(".menu_dropdown-arrow").html("&#xe6d6;");
	});
	flag=false;
	}
}