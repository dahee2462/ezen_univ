//[수강신청 클릭시]
function corRegFn(obj, lno) {
    // 모든 버튼의 lno 값 확인
/*    var buttons = document.querySelectorAll('.btn');
    for (var i = 0; i < buttons.length; i++) {
        var button = buttons[i];
        var buttonLno = button.getAttribute('data-lno');
        if (buttonLno == lno && button.disabled) {
            alert("이미 신청한 강의입니다.");
            return; // 함수종료
        }
    }
*/
    $.ajax({
        url: "cAppCheck",
        type: "post",
        data: "lno=" + lno, //키=값
        success: function (data) {
            if (data.trim() == 'SUCCESS') {
                alert("수강신청이 완료되었습니다.");
                // 중복신청시 배제 = 버튼을 비활성화
                obj.disabled = true;
            } else if (data.trim() == 'FAIL') {
                alert("수강신청이 완료되지 않았습니다.");
            }
        },
        error: function () {
            alert("서버와의 통신에 실패하였습니다.");
        }
    });
}

//[수강신청 취소시]
function canFn(obj,cno){
	$.ajax({
		url: "cAppCheck",
		type: "post",
		data: "cno="+cno,	//키=값
		success:function(data){
			if(data.trim()=='SUCCESS2'){
				alert("삭제가 완료되었습니다.");
				//리로드
				window.location.reload(true);
				//현재 클릭한 버튼의 cno와
				//수강신청페이지의 lecture테이블의 lno 값 확인 -> 같으면 disabled를 false
/*                var buttons = document.querySelectorAll('.btn-apply');
                for (var i = 0; i < buttons.length; i++) {
                    var button = buttons[i];
                    var buttonLno = button.getAttribute('data-lno');
                    if (buttonLno == cno && button.disabled) {
                        button.disabled = false;
                    }
                }
				*/
				//const target = document.getElementById('btn-apply');
 				//target.disabled = false;
			}else if(data.trim()=='FAIL2'){
				alert("삭제가 완료되지 않았습니다.");
			}
		},error:function(){
			alert("서버와의 통신에 실패하였습니다.");
		}		
	});
}