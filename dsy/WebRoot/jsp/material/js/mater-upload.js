$(function(){
	
});


function metarial_add(){
var str='<dl class="permission-list" style="margin-bottom: 10px;">'
		+'<dd style="height:120px;">'
	+'<div class="row cl pd-5" >'
		+'<label class="form-label col-2"><span class="c-red">*</span>上传类型：</label>'
		+'<div class="formControls col-9">'
		+'<span class="select-box inline">'
		+'<select id="type" name="type" class="select">'
			  +'<option selected="selected" value="0">请选择</option>'
			  +'<option selected="" value="1">图片</option>'
			  +'<option selected="" value="1">视频</option>'
			+'</select>'
			+'</span>'
		+'</div>'
		+'<div class="formControls col-1"><a onclick="remove(this)" href="javascript:;">'
		+'<span class="label radius" style="color:#222;background-color: #ffffff; border:1px solid #ccc;">关闭'
		+'<i class="Hui-iconfont" style="float:right;top:0px;color:#B7170F;">&#xe6a6;</i>'
		+'</span></a>'
		+'</div>'
	+'</div>'
	+'<div class="row cl pd-5">'
		+'<label class="form-label col-2"><span class="c-red">*</span>简略标题：</label>'
		+'<div class="formControls col-10">'
			+'<input type="text" class="input-text" value="" placeholder="" id="" name="">'
		+'</div>'
	+'</div>'
	+'<div class="row cl pd-5">'
		+'<label class="form-label col-2"><span class="c-red">*</span>上传文件：</label>'
		+'<div class="formControls col-10">'
			+'<input type="file" class="input-text" id="imgFile" name="imgFile">'
		+'</div>'
	+'</div>'
    +'</dd>'
    +'</dl>';
    
	$("#content").append(str);
}

function remove(obj){
	$(obj).parents('.permission-list').remove();
}