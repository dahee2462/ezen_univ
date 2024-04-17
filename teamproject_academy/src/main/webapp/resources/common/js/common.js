function loginButtonClick() {
	let id = document.loginForm.id.value;
	let pw = document.loginForm.pw.value;
	let isValue = (id == "" || id === undefined || id === null || pw == "" || pw === undefined || pw === null);
	if (!isValue) {
		document.loginForm.submit();
	} else {
		alert("아이디나 비밀번호를 입력해 주세요.")
	}
}


function searchId() {
	let type = document.searchIdForm.selectType.value;
	let name = document.searchIdForm.inputName.value;
	let birth = document.searchIdForm.inputBirth.value;
	let phone = document.searchIdForm.inputPhone.value;

	let isSubmit = true;

	if (name == "" || name === undefined || name === null) {
		isSubmit = false;
	} else {
		let regId = /^[가-힣a-zA-Z]$/;
		let regRs = regId.test(name);
		if (!regRs) {
		} else {
			isSubmit = false;
		}
	}

	if (birth == "" || birth === undefined || birth === null) {
		isSubmit = false;
	} else {
		let regId = /^(19|20)\d\d(0[1-9]|1[0-2])(0[1-9]|[12][0-9]|3[01])$/;
		let regRs = regId.test(birth);
		if (regRs) {
		} else {
			isSubmit = false;
		}
	}

	if (phone == "" || phone === undefined || phone === null) {
		isSubmit = false;
	} else {
		let regId = /^01(0|1|[6-9])[0-9]{3,4}[0-9]{4}$/;
		let regRs = regId.test(phone);
		if (regRs) {
		} else {
			isSubmit = false;
		}
	}

	if (isSubmit) {
		let params = $('#searchIdForm').serialize();
		$.ajax({
			url: "findId",
			type: "post",
			data: params,
			success: function(data) {
				if (data.trim() == "null") {
					alert("일치하는 아이디가 없습니다.");
				} else {
					alert("일치하는 아이디는 : '" + data + "' 입니다.");
				}
			}, error: function() {
			}
		});
	} else {
		alert("입력값이 유효하지 않습니다.")
	}


}



let time= 180000;
let min=3;
let sec=60;

function TIMER(){
    PlAYTIME=setInterval(function(){
        time=time-1000; //1초씩 줄어듦
        min=time/(60*1000); //초를 분으로 나눠준다.

       if(sec>0){ //sec=60 에서 1씩 빼서 출력해준다.
            sec=sec-1;
            Timer.value=Math.floor(min)+':'+sec; //실수로 계산되기 때문에 소숫점 아래를 버리고 출력해준다.
           
        }
        if(sec===0){
         	// 0에서 -1을 하면 -59가 출력된다.
            // 그래서 0이 되면 바로 sec을 60으로 돌려주고 value에는 0을 출력하도록 해준다.
            sec=60;
            Timer.value=Math.floor(min)+':'+'00'
        }     
   
    },1000); //1초마다 
}


let istimer = false;

function sendEmail() {
	if(istimer){
		clearInterval(PlAYTIME);
	    time= 180000;
		min=3;
		sec=60;
	}
	let email = document.searchPwForm.email.value;
	alert("이메일이 발송되었습니다.");
	$.ajax({
		url: "sendEmail",
		type: "post",
		data: {email:email},
		success: function(data) {
			if (data.trim() == "ok") {
				document.getElementById('Timer').value=min+":"+'00'; 
				TIMER();
				setTimeout(function(){
				    clearInterval(PlAYTIME);
				    time= 180000;
					min=3;
					sec=60;
					istimer = false;
					document.getElementById('Timer').value="timeout"
				},180000);//3분이 되면 타이머를 삭제한다.
				istimer = true;
			}else{
				alert("오류가 발생했습니다. 다시 시도해 주세요.");
			}
		}, error: function() {
			alert("오류가 발생했습니다. 다시 시도해 주세요.!");
		}
	});

}


let checkOk = false;

function codeCheck(){
	let code = document.searchPwForm.code.value;
	$.ajax({
		url: "sendCode",
		type: "post",
		data: {code:code},
		success: function(data) {
			if (data.trim() == "ok") {
				alert("인증이 완료되었습니다.");
				clearInterval(PlAYTIME);
				istimer = false;
				time= 180000;
				min=3;
				sec=60;
				document.getElementById('Timer').value="";
				checkOk = true;
			}else{
				alert("인증 번호가 다릅니다.");
			}
		}, error: function() {
			alert("오류가 발생했습니다. 다시 시도해 주세요.!");
		}
	});
}



function searchPw() {
	let type = document.searchPwForm.type.value;
	let id = document.searchPwForm.id.value;
	let name = document.searchPwForm.name.value;
	let birth = document.searchPwForm.birth.value;
	let phone = document.searchPwForm.phone.value;
	let email = document.searchPwForm.email.value;
	let code = document.searchPwForm.code.value;

	let isSubmit = true;

	if (id == "" || id === undefined || id === null) {
		isSubmit = false;
	} else {
		let regId = /^[a-z][a-z0-9]{3,20}$/g;
		let regRs = regId.test(id);
		if (regRs) {
		} else {
			isSubmit = false;
		}
	}

	if (name == "" || name === undefined || name === null) {
		isSubmit = false;
	} else {
		let regId = /^[가-힣a-zA-Z]$/;
		let regRs = regId.test(name);
		if (!regRs) {
		} else {
			isSubmit = false;
		}
	}

	if (birth == "" || birth === undefined || birth === null) {
		isSubmit = false;
	} else {
		let regId = /^(19|20)\d\d(0[1-9]|1[0-2])(0[1-9]|[12][0-9]|3[01])$/;
		let regRs = regId.test(birth);
		if (regRs) {
		} else {
			isSubmit = false;
		}
	}

	if (phone == "" || phone === undefined || phone === null) {
		isSubmit = false;
	} else {
		let regId = /^01(0|1|[6-9])[0-9]{3,4}[0-9]{4}$/;
		let regRs = regId.test(phone);
		if (regRs) {
		} else {
			isSubmit = false;
		}
	}

	if (email == "" || email === undefined || email === null) {
		isSubmit = false;
	} else {
		let regId = /^[a-zA-Z0-9+-_.]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/;
		let regRs = regId.test(email);
		if (regRs) {
		} else {
			isSubmit = false;
		}
	}

	if (isSubmit && checkOk) {
		let params = $('#searchPwForm').serialize();
		$.ajax({
			url: "findPw",
			type: "post",
			data: params,
			success: function(data) {
				if (data.trim() == "null") {
					alert("일치하는 계정을 찾을 수 없습니다.");
				} else {
					alert("새 비밀번호는 : '" + data + "' 입니다.");
					location.href="/teamproject_academy/";
				}
			}, error: function() {
			}
		});
	} else {
		alert("입력값이 유효하지 않거나 인증이 되지 않았습니다.")
	}
	

}