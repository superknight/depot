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