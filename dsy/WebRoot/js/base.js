/**
 * base on JQuery
 */
var windowPage = {
	login : function() {
		$("#sign").on("click", function() {
			windowPage.doSubmit();
		});
		$("#ok").on("click", function() {
			windowPage.ok();
		});
	},
	doSubmit : function() {
		if ($("#account").val() == '' || $("#password").val() == '') {
			$("#warn").modal("show");
			return false;
		}
		$("form").submit();
	},
	ok : function() {
		$("#warn").modal("hide");
	},
	getCurrentTime : function() { //get current time
		var date = new Date();
		var seperator1 = "-";
		var seperator2 = ":";
		var month = date.getMonth() + 1;
		var day = date.getDate();
		var hour = date.getHours();
		var minute = date.getMinutes();
		var second = date.getSeconds();
		if (month >= 1 && month <= 9) {
			month = "0" + month;
		}
		if (day >= 0 && day <= 9) {
			day = "0" + day;
		}
		if (hour >= 0 && hour <= 9) {
			hour = "0" + hour;
		}
		if (minute >= 0 && minute <= 9) {
			minute = "0" + minute;
		}
		if (second >= 0 && second <= 9) {
			second = "0" + second;
		}
		var currentdate = date.getFullYear() + seperator1 + month + seperator1
				+ day + " " + hour + seperator2 + minute + seperator2 + second;
		return currentdate;
	},
	blockUI:function(){  //wait
		$(document).ajaxStart(function() {
			$.blockUI({
				message : $('#loading'),
				css : {
					top : ($(window).height() - 200) / 2 + 'px',
					left : ($(window).width() - 200) / 2 + 'px',
					width : '200px',
					border : 'none',
					backgroundColor: '#fff',
					opacity: .2
				},
				overlayCSS : {
					backgroundColor : '#fff',
					opacity: .1
				}
			});
			setTimeout($.unblockUI, 10000);//timeout 10s
		}).ajaxStop($.unblockUI);
	},
	terminal:function(){ // terminal station
		$.ajax({
			url : 'terminal/getData.html',
			type : 'post',
			data : '',
			dataType : 'json',
			success : function(data, status) {
				if (data == undefined || data.header != '000') {
					windowPage.showError();
				} else {
					windowPage.hideError();
					showHTML(data.data);
				}
			},
			error : function(data, status, e) {
				windowPage.showError();
			}
		});

		function showHTML(data) {
			var tbBody = "<table class='table table-hover'>";
			for ( var i = 0; i < data.length; i++) {
				tbBody += "<tr>";
				tbBody += "<td><div>"
						+ "<a href='route/routeLine/"
					+ data[i].stacode
					+ "'><div class='base-font ml'><i class='fa fa-circle green-dot' aria-hidden='true'></i>"
						+ data[i].stationcode +" - "+ data[i].staname
						+ "</div></a></div><td>";
				tbBody += "<tr>";
			}
			tbBody += "</table>";
			var divBody = document.getElementById("divBody");
			divBody.innerHTML = tbBody;
		};
	},
	getStaticData:function(baseURL,value,to,time){ // static data
		  $.ajax({
	    		url : baseURL+"/route/getStaticData.html",
	    		type : 'post',
	    		data : "s="+value,
	    		dataType : 'json',
	    		success : function(data, status) {
	    			if (data == undefined || data.header != '000') {
	    				windowPage.showError();
	    			} else {
	    				windowPage.hideError();
	    				showStatic(data.data,to,time);
	    				//console.log(JSON.stringify(data));
	    			}
	    		},
	    		error : function(data, status, e) {
	    			windowPage.showError();
	    		}
	    	});
		  function showStatic(data,to,time){
		    	var len = data.length;
		    	var routeWidth = 320;
		    	var w = routeWidth*len;	 
		    	$(".content").css("width",w+"px"); // modified component width
		    	for(var i = 0; i<len; i++){
		    		var routeInfo = data[i].routeinfo;
		    		var appendDiv = ""; 		
		    		var levelDiv1 = "<div id=route"+i+" class='route radius5'>"; //not close yet
		    		var levelDiv2 = "<div class='"+data[i].busColor+" rname'>"+data[i].routename+"</div>";
		    		var levelDiv3 = "<div class='to radius5'><span>"+to+"：</span><span>"+routeInfo[routeInfo.length-1].staname+"</span></div>";
		    		var levelDiv4 = "<div class='utime radius5'><span>"+time+"：</span><span class='time'>"+windowPage.getCurrentTime()+"</span></div>";
		    		var levelDiv5 = "<div class='station'>"; //not close yet
		    		var ulStart = "<ul>"; // not close yet
		    		appendDiv = levelDiv1 + levelDiv2 + levelDiv3 + levelDiv4  + levelDiv5 + ulStart;
		    		
		    		for(var j=0;j<routeInfo.length;j++){
		    			appendDiv += "<li>"+routeInfo[j].staname+"</li>";
		    		}
		    		var ulEnd = "</ul>";
		    		appendDiv += ulEnd; // ul closed
		    		appendDiv += "</div>"; // levelDiv4 closed
		    		appendDiv += "</div>"; //levelDiv1 closed
		    		$(".content").append(appendDiv);
		    	}
		    	
		    }
	},
	getDynmicData:function(baseURL,value){ // dynamic data
		$.ajax({
			url : baseURL+"/route/getDynamicData.html",
			type : 'post',
			data : "s="+value,
			dataType : 'json',
			success : function(data, status) {
				if (data == undefined || data.header != '000') {
					windowPage.showError();
				} else {
					windowPage.hideError();
					showDynamic(data.data);
					//console.log(JSON.stringify(data));
				}
			},
			error : function(data, status, e) {
				windowPage.showError();
			}
		});
		  function showDynamic(data){
		    	for(var i=0;i<data.length;i++){
		    		$("#route"+i+" .time").empty().append(windowPage.getCurrentTime()); // update time
		    		var color = data[i].busColor;
		    		var routeInfo = data[i].dynamicInfo;
		    		for(var j=0;j<routeInfo.length;j++){
		    			$("#route"+i+" .station ul>li").eq(j).children("span").remove(); // remove last time bus plate
		    			var busplate = routeInfo[j].busplate;
		    			if(busplate != ""){
		    				var plates = busplate.split(",");
		    				var appendPlate = "";
		    				for(var k = 0; k<plates.length; k++){
		    					 appendPlate += "<span><i class='fa fa-bus bus "+color+"' aria-hidden='true'></i><strong>"+plates[k]+"</strong></span>";
		    				}		
		    				$("#route"+i+" .station ul>li").eq(j).append(appendPlate);
		    				
		    			}
		    		}
		    	
		    	}
		    	
		    }
	},
	showError:function(){ 
		$(".content").hide();
		$("body").css("width",$(window).width() + 'px');
		$("body").css("height",$(window).height() + 'px');
		$(".error").css("top",($(window).height() - 150) / 2 + 'px');
		$(".error").css("left",($(window).width() - 150) / 2 + 'px');
		$(".error").show();
	},
	hideError:function(){
		$(".error").hide();
	}	
	
};
$(function() {
	windowPage.login();
});