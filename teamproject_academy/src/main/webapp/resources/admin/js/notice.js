function checkTitle(obj){
    if(obj.value == "" || obj.value === null || obj.value === undefined){
        alert("제목을 입력해주시기 바랍니다.");
        return false;
    }else{
        return true;
    }
}

function checkContent(obj){
    if(obj.value == "" || obj.value === null || obj.value === undefined){
        alert("내용을 입력해주시기 바랍니다.");
        return false;
    }else{
        return true;
    }
}

function register(){
    var titleInput = document.getElementById("title");
    var contentInput = document.getElementById("summernote");

    if(checkTitle(titleInput) && checkContent(contentInput)){
        document.getElementsByName("noticeWritefrm")[0].submit();
    }else{
		return
	}
}
