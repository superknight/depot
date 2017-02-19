$(function() {
	
	var customerId=getUrlParam("customerId");
	var str = '';
	var index = layer.load(2);
	$.ajax({
		"url":"member/getAddressList.html",
		"type":"POST",
		"data":{"customerId":customerId},
		"success":function(data){
			var list = $.parseJSON(data);
			   console.log(list);
			   for(var i=0;i<list.length;i++){
				   str += '<tr><td>'+list[i].address+'</td><td>'
				   +list[i].phone+'</td><td>'
				   +list[i].receiver+'</td><td>'
				   +list[i].in_time+'</td><td>'
				   +list[i].remark+'</td></tr>';
			   }
			   $("#dataTable tbody").append(str);
			   layer.close(index); 
		},
	    "error":function(data){
	    	layer.msg(list.msg,{icon: 5,time:1000});
	    }
	});
});