// Chinese (China) (zh_CN)
plupload.addI18n({"Stop Upload":"停止上传","Upload URL might be wrong or doesn't exist.":"上传的URL可能是错误的或不存在。","tb":"tb","Size":"大小","Close":"关闭","Init error.":"初始化错误。","Add files to the upload queue and click the start button.":"将文件添加到上传队列，然后点击”开始上传“按钮。","Filename":"文件名","Image format either wrong or not supported.":"图片格式错误或者不支持。","Status":"状态","HTTP Error.":"HTTP 错误。","Start Upload":"开始上传","mb":"mb","kb":"kb","Duplicate file error.":"重复文件错误。","File size error.":"文件大小超出限制：最大为1MB。","N/A":"N/A","gb":"gb","Error: Invalid file extension:":"错误：无效的文件扩展名:","Select files":"选择文件","%s already present in the queue.":"%s 已经在当前队列里。","File: %s":"文件: %s","b":"b","Uploaded %d/%d files":"已上传 %d/%d 个文件","Upload element accepts only %d file(s) at a time. Extra files were stripped.":"每次只接受同时上传 %d 个文件，多余的文件将会被删除。","%d files queued":"%d 个文件加入到队列","File: %s, size: %d, max file size: %d":"文件: %s, 大小: %d, 最大文件大小: %d","Drag files here.":"把文件拖到这里。","Runtime ran out of available memory.":"运行时已消耗所有可用内存。","File count error.":"文件数量错误。","File extension error.":"文件类型错误，仅支持zip文件！","Error: File too large:":"错误: 文件太大:","Add Files":"增加文件"});

function creatPluploader(uploaderName,button,uploadUrl,typeTitle,type_extensions,max_size,showList){
	var uploaderName = new plupload.Uploader({
        browse_button : ''+button+'', 
        url : uploadUrl, 
        flash_swf_url : '<%=basePath%>/plupload/Moxie.swf', 
        silverlight_xap_url : '<%=basePath%>/plupload/Moxie.xap', 
    	filters: {
   		  mime_types : [ 
   		    { title : ""+typeTitle+"", extensions : ""+type_extensions+"" }
   		  ],
   		  max_file_size : max_size
//    		  prevent_duplicates : true //不允许选取重复文件
   		},
//     	chunk_size:'2mb',
    	multi_selection:false
    });    
    uploaderName.init();
    //绑定各种事件，并在事件监听函数中做你想做的事
    uploaderName.bind('FilesAdded',function(uploader1,files){
    	if(plupload.formatSize(files[0].size)=='N/A'){
			alert("上传文件为空文件，请重新操作！");
			uploader1.removeFile(files[0]);
			return;
		}
    	plupload.each(files, function(file) {
			document.getElementById(showList).innerHTML = '<div id="' + file.id + '">' + file.name + ' (' + plupload.formatSize(file.size) + ') <b></b></div>';
		});
    	$("#"+button).attr('disabled',true);
    	uploader1.start();
    });
    uploaderName.bind('UploadProgress',function(uploader1,file){
    	document.getElementById(file.id).getElementsByTagName('b')[0].innerHTML = '<span style="color:red;">' + file.percent + "%</span>";
    });
    uploaderName.bind('Error',function(uploader1,err){
    	
    	if("HTTP 错误。"==err.message){
    		alert(err.file.name+'  :  '+err.response+',请检查文件后重新上传！');
	    	$("#"+button).attr('disabled',false);
	    	document.getElementById(showList).innerHTML = '<a class="erropro" href="javascript:;">* 请上传附件</a>';
// 	    	alert(err.file.name+"上传失败，请检查网络或文件是否已损坏！");
    	}else{
    		alert(err.message);
    	}

    });
    uploaderName.bind('UploadComplete',function(uploader1,files){
//	    	alert('上传完成！！！');
    	plupload.each(files, function(file) {
	    	document.getElementById(showList).innerHTML = '<a class="pt5 tdul" name="flge4check" href="<%=basePath %>/users/file/showPicByIdType?id='+id+'&attType=2 " target="_blank">'+file.name+'</a>';
		});
    	$("#"+button).attr('disabled',false);
    });
}