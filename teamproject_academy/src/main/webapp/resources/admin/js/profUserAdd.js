
// 유효성 검사
	/* 교수번호 유효성 검사 */
	function checkId(obj){
			let regId = /^\d{7}$/;
			let regRs = regId.test(obj.value); 
			let pidInput = document.getElementById("pid");
			
			if(obj.value == "" || obj.value === null || obj.value === undefined){
				pidInput.setCustomValidity("교수번호를 입력해주세요.");
				pidInput.focus();
				pidInput.maxLength = 7;
				pidInput.style.color = 'red';
				return false;
			}else if(!regRs){
				pidInput.setCustomValidity("숫자 7자리를 입력해주세요.");
				pidInput.focus();
				pidInput.maxLength = 7;
				pidInput.style.color = 'red';
				return false;
			}else{
				pidInput.setCustomValidity("");
				pidInput.maxLength = 7;
				pidInput.style.color = 'green';
				return true;
			}
		}
		
	/* 이름 유효성 검사 */
	function checkName(obj){
			let regId = /^[가-힣a-zA-Z]{2,20}$/;
			let regRs = regId.test(obj.value); 
			let pnameInput = document.getElementById("pname");
			
			if(obj.value == "" || obj.value === null || obj.value === undefined){
				pnameInput.setCustomValidity("성명를 입력해주세요.");
				pnameInput.focus();
				pnameInput.maxLength = 20;
				pnameInput.style.color = 'red';
				return false;
			}else if(!regRs){
				pnameInput.setCustomValidity("한글 또는 영문을 2자리 이상 입력해주세요.");
				pnameInput.focus();
				pnameInput.maxLength = 20;
				pnameInput.style.color = 'red';
				return false;
			}else{
				pnameInput.setCustomValidity("");
				pnameInput.maxLength = 20;
				pnameInput.style.color = 'green';
				return true;
			}
		}
		
	/* 주민번호 유효성 검사 */
	function checkRegNo1(obj){
			let regId = /^(?:[0-9]{2}(?:0[1-9]|1[0-2])(?:0[1-9]|[1,2][0-9]|3[0,1]))$/;
			let regRs = regId.test(obj.value); 
			let pregNo1Input = document.getElementById("pregNo1");
			
			if(obj.value == "" || obj.value === null || obj.value === undefined){
				pregNo1Input.setCustomValidity("주민번호 앞자리를 입력해주세요.");
				pregNo1Input.focus();
				pregNo1Input.maxLength = 6;
				pregNo1Input.style.color = 'red';
				return false;
			}else if(!regRs){
				pregNo1Input.setCustomValidity("주민번호 앞 6자리를 입력해주세요.");
				pregNo1Input.focus();
				pregNo1Input.maxLength = 6;
				pregNo1Input.style.color = 'red';
				return false;
			}else{
				pregNo1Input.setCustomValidity("");
				pregNo1Input.maxLength = 6;
				pregNo1Input.style.color = 'green';
				return true;
			}
		}
		
	function checkRegNo2(obj){
			let regId = /^[1-4]\d{6}$/;
			let regRs = regId.test(obj.value); 
			let pregNo2Input = document.getElementById("pregNo2");
			
			if(obj.value == "" || obj.value === null || obj.value === undefined){
				pregNo2Input.setCustomValidity("주민번호 뒷자리를 입력해주세요.");
				pregNo2Input.focus();
				pregNo2Input.maxLength = 7;
				pregNo2Input.style.color = 'red';
				return false;
			}else if(!regRs){
				pregNo2Input.setCustomValidity("주민번호 뒤 7자리를 입력해주세요.");
				pregNo2Input.focus();
				pregNo2Input.maxLength = 7;
				pregNo2Input.style.color = 'red';
				return false;
			}else{
				pregNo2Input.setCustomValidity("");
				pregNo2Input.maxLength = 7;
				pregNo2Input.style.color = 'green';
				return true;
			}
		}
		
	/* 생년월일 유효성 검사 */
	function checkBirth(obj){
			let regId = /^(19|20)\d\d(0[1-9]|1[0-2])(0[1-9]|[12][0-9]|3[01])$/; 
			let regRs = regId.test(obj.value); 
			let pbirthInput = document.getElementById("pbirth");
			
			if(obj.value == "" || obj.value === null || obj.value === undefined){
				pbirthInput.setCustomValidity("생년월일을 입력해주세요.");
				pbirthInput.focus();
				pbirthInput.maxLength = 8;
				pbirthInput.style.color = 'red';
				return false;
			}else if(!regRs){
				pbirthInput.setCustomValidity("숫자 8자리로 입력해주세요.");
				pbirthInput.focus();
				pbirthInput.maxLength = 8;
				pbirthInput.style.color = 'red';
				return false;
			}else{
				pbirthInput.setCustomValidity("");
				pbirthInput.maxLength = 8;
				pbirthInput.style.color = 'green';
				return true;
			}
		}
		
	/* 성별 유효성 검사 */
	function checkGender(){
		let genderRadios = document.querySelectorAll('input[name="pgender"]');
		
		let isChecked = Array.from(genderRadios).some(function(radio){
			return radio.checked;
		});
		
		if(!isChecked){
			alert("성별을 선택해주세요.");
			return false;
		}
			return true;
		}
		
	/* 직급 유효성 검사 */
	function checkPosition(obj){
			let regId = /^교수$|^[가-힣]+(교수)$/;
			let regRs = regId.test(obj.value); 
			let ppositionInput = document.getElementById("pposition");
			
			if(obj.value == "" || obj.value === null || obj.value === undefined){
				ppositionInput.setCustomValidity("직급을 입력해주세요.");
				ppositionInput.focus();
				ppositionInput.maxLength = 20;
				ppositionInput.style.color = 'red';
				return false;
			}else if(!regRs){
				ppositionInput.setCustomValidity("교수 또는 OO교수로 입력해주세요.");
				ppositionInput.focus();
				ppositionInput.maxLength = 20;
				ppositionInput.style.color = 'red';
				return false;
			}else{
				ppositionInput.setCustomValidity("");
				ppositionInput.maxLength = 20;
				ppositionInput.style.color = 'green';
				return true;
			}
		}
		
	/* 대학 유효성 검사 */
	function checkUniv(obj){
			let regId = /^[가-힣A-Za-z]+(대학교|Univ)$/;
			let regRs = regId.test(obj.value); 
			let punivInput = document.getElementById("puniv");
			
			if(obj.value == "" || obj.value === null || obj.value === undefined){
				punivInput.setCustomValidity("대학교를 입력해주세요.");
				punivInput.focus();
				punivInput.maxLength = 40;
				punivInput.style.color = 'red';
				return false;
			}else if(!regRs){
				punivInput.setCustomValidity("OOO대학교 또는 oooUniv로 입력해주세요.");
				punivInput.focus();
				punivInput.maxLength = 40;
				punivInput.style.color = 'red';
				return false;
			}else{
				punivInput.setCustomValidity("");
				punivInput.maxLength = 40;
				punivInput.style.color = 'green';
				return true;
			}
		}
				
	/* 단과대학 유효성 검사 */
	function checkFaculty(obj){
			let regId = /^[가-힣]+대학$/;
			let regRs = regId.test(obj.value); 
			let pfacultyInput = document.getElementById("pfaculty");
			
			if(obj.value == "" || obj.value === null || obj.value === undefined){
				pfacultyInput.setCustomValidity("단과대학을 입력해주세요.");
				pfacultyInput.focus();
				pfacultyInput.maxLength = 40;
				pfacultyInput.style.color = 'red';
				return false;
			}else if(!regRs){
				pfacultyInput.setCustomValidity("OOO대학으로 입력해주세요.");
				pfacultyInput.focus();
				pfacultyInput.maxLength = 40;
				pfacultyInput.style.color = 'red';
				return false;
			}else{
				pfacultyInput.setCustomValidity("");
				pfacultyInput.maxLength = 40;
				pfacultyInput.style.color = 'green';
				return true;
			}
		}
		
		document.getElementById("pfaculty").addEventListener("input", function() {
    		let resultpfaculty = checkFaculty(this);
    		console.log(resultpfaculty);
		});
		
	/* 전공 유효성 검사 */
	function checkMajor(obj){
			let regId = /^[가-힣]+학$/;
			let regRs = regId.test(obj.value); 
			let pmajorInput = document.getElementById("pmajor");
			
			if(obj.value == "" || obj.value === null || obj.value === undefined){
				pmajorInput.setCustomValidity("전공을 입력해주세요.");
				pmajorInput.focus();
				pmajorInput.maxLength = 40;
				pmajorInput.style.color = 'red';
				return false;
			}else if(!regRs){
				pmajorInput.setCustomValidity("OOO학으로 입력해주세요.");
				pmajorInput.focus();
				pmajorInput.maxLength = 40;
				pmajorInput.style.color = 'red';
				return false;
			}else{
				pmajorInput.setCustomValidity("");
				pmajorInput.maxLength = 40;
				pmajorInput.style.color = 'green';
				return true;
			}
		}
				
	/* 학위 유효성 검사 */
	function checkDegree(obj){
			let regId = /^(석사|박사)$/;
			let regRs = regId.test(obj.value); 
			let pdegreeInput = document.getElementById("pdegree");
			
			if(obj.value == "" || obj.value === null || obj.value === undefined){
				pdegreeInput.setCustomValidity("학위를 입력해주세요.");
				pdegreeInput.focus();
				pdegreeInput.maxLength = 40;
				pdegreeInput.style.color = 'red';
				return false;
			}else if(!regRs){
				pdegreeInput.setCustomValidity("석사 또는 박사를 입력해주세요.");
				pdegreeInput.focus();
				pdegreeInput.maxLength = 40;
				pdegreeInput.style.color = 'red';
				return false;
			}else{
				pdegreeInput.setCustomValidity("");
				pdegreeInput.maxLength = 40;
				pdegreeInput.style.color = 'green';
				return true;
			}
		}
		
	/* 연구실 유효성 검사 */
	function checkLab(obj){
			let regId = /^[0-9a-zA-Z]+호$/;
			let regRs = regId.test(obj.value); 
			let plabInput = document.getElementById("plab");
			
			if(obj.value == "" || obj.value === null || obj.value === undefined){
				plabInput.setCustomValidity("연구실을 입력해주세요.");
				plabInput.focus();
				plabInput.maxLength = 40;
				plabInput.style.color = 'red';
				return false;
			}else if(!regRs){
				plabInput.setCustomValidity("OOO호로 입력해주세요.");
				plabInput.focus();
				plabInput.maxLength = 40;
				plabInput.style.color = 'red';
				return false;
			}else{
				plabInput.setCustomValidity("");
				plabInput.maxLength = 40;
				plabInput.style.color = 'green';
				return true;
			}
		}
		
	/* 임용일자 유효성 검사 */
	function checkAppointDate(obj){
			let regId = /^(19|20)\d\d(0[1-9]|1[0-2])(0[1-9]|[12][0-9]|3[01])$/; 
			let regRs = regId.test(obj.value); 
			let pappointDateInput = document.getElementById("pappointDate");
			
			if(obj.value == "" || obj.value === null || obj.value === undefined){
				pappointDateInput.setCustomValidity("임용일자를 입력해주세요.");
				pappointDateInput.focus();
				pappointDateInput.maxLength = 10;
				pappointDateInput.style.color = 'red';
				return false;
			}else if(!regRs){
				pappointDateInput.setCustomValidity("숫자 8자리로 입력해주세요.");
				pappointDateInput.focus();
				pappointDateInput.maxLength = 10;
				pappointDateInput.style.color = 'red';
				return false;
			}else{
				pappointDateInput.setCustomValidity("");
				pappointDateInput.maxLength = 10;
				pappointDateInput.style.color = 'green';
				return true;
			}
		}
		
	/* 이메일 유효성 검사 */
	function checkEmail(obj){
			let regId = /^[a-zA-Z0-9+-_.]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/; 
			let regRs = regId.test(obj.value); 
			let pemailInput = document.getElementById("pemail");
			
			if(obj.value == "" || obj.value === null || obj.value === undefined){
				pemailInput.setCustomValidity("이메일을 입력해주세요.");
				pemailInput.focus();
				pemailInput.maxLength = 45;
				pemailInput.style.color = 'red';
				return false;
			}else if(!regRs){
				pemailInput.setCustomValidity("이메일 형식에 맞게 입력해주세요.");
				pemailInput.focus();
				pemailInput.maxLength = 45;
				pemailInput.style.color = 'red';
				return false;
			}else{
				pemailInput.setCustomValidity("");
				pemailInput.maxLength = 45;
				pemailInput.style.color = 'green';
				return true;
			}
		}
		
	/* 휴대전화번호 유효성 검사 */
	function checkPhone(obj){
			let regId = /^(010|011)\d{3,4}\d{4}$/; 
			let regRs = regId.test(obj.value); 
			let pphoneInput = document.getElementById("pphone");
			
			if(obj.value == "" || obj.value === null || obj.value === undefined){
				pphoneInput.setCustomValidity("휴대전화번호를 입력해주세요.");
				pphoneInput.focus();
				pphoneInput.maxLength = 11;
				pphoneInput.style.color = 'red';
				return false;
			}else if(!regRs){
				pphoneInput.setCustomValidity("숫자만 입력해주세요.");
				pphoneInput.focus();
				pphoneInput.maxLength = 11;
				pphoneInput.style.color = 'red';
				return false;
			}else{
				pphoneInput.setCustomValidity("");
				pphoneInput.maxLength = 11;
				pphoneInput.style.color = 'green';
				return true;
			}
		}
		
	/* 연구실 전화번호 유효성 검사 */
	function checkCall(obj){
			let regId = /^(0(2|3[1-3]|4[1-4]|5[1-5]|6[1-4]))(\d{3,4})(\d{4})$/;
			let regRs = regId.test(obj.value); 
			let pcallInput = document.getElementById("pcall");
			
			if(obj.value == "" || obj.value === null || obj.value === undefined){
				pcallInput.setCustomValidity("연구실 전화번호를 입력해주세요.");
				pcallInput.focus();
				pcallInput.maxLength = 10;
				pcallInput.style.color = 'red';
				return false;
			}else if(!regRs){
				pcallInput.setCustomValidity("지역번호와 숫자만 입력해주세요.");
				pcallInput.focus();
				pcallInput.maxLength = 10;
				pcallInput.style.color = 'red';
				return false;
			}else{
				pcallInput.setCustomValidity("");
				pcallInput.maxLength = 10;
				pcallInput.style.color = 'green';
				return true;
			}
		}
		
	/* 주소 유효성 검사 */
	function checkAddr(obj){
			let regId = /^[가-힣0-9\s\(\)\-]+$/u;
			let regRs = regId.test(obj.value); 
			let paddrInput = document.getElementById("paddr");
			
			if(obj.value == "" || obj.value === null || obj.value === undefined){
				paddrInput.setCustomValidity("주소를 입력해주세요.");
				paddrInput.focus();
				paddrInput.maxLength = 100;
				paddrInput.style.color = 'red';
				return false;
			}else if(!regRs){
				paddrInput.setCustomValidity("올바르게 입력해주세요.");
				paddrInput.focus();
				paddrInput.maxLength = 100;
				paddrInput.style.color = 'red';
				return false;
			}else{
				paddrInput.setCustomValidity("");
				paddrInput.maxLength = 100;
				paddrInput.style.color = 'green';
				return true;
			}
		}
		
	/* 우편번호 유효성 검사 */
	function checkZipCode(obj){
			let regId = /^\d{5}$/;
			let regRs = regId.test(obj.value); 
			let pzipCodeInput = document.getElementById("pzipCode");
			
			if(obj.value == "" || obj.value === null || obj.value === undefined){
				pzipCodeInput.setCustomValidity("우편번호를 입력해주세요.");
				pzipCodeInput.focus();
				pzipCodeInput.maxLength = 5;
				pzipCodeInput.style.color = 'red';
				return false;
			}else if(!regRs){
				pzipCodeInput.setCustomValidity("올바르게 입력해주세요.");
				pzipCodeInput.focus();
				pzipCodeInput.maxLength = 5;
				pzipCodeInput.style.color = 'red';
				return false;
			}else{
				pzipCodeInput.setCustomValidity("");
				pzipCodeInput.maxLength = 5;
				pzipCodeInput.style.color = 'green';
				return true;
			}
		}

function register() {
    // 모든 유효성 검사를 수행합니다.
    if (
        checkId(pid) && checkName(pname) && checkRegNo1(pregNo1) &&
        checkRegNo2(pregNo2) && checkBirth(pbirth) && checkGender() &&
        checkPosition(pposition) && checkUniv(puniv) && checkFaculty(pfaculty) &&
        checkMajor(pmajor) && checkDegree(pdegree) && checkLab(plab) &&
        checkAppointDate(pappointDate) && checkEmail(pemail) && checkPhone(pphone) &&
        checkCall(pcall) && checkAddr(paddr) && checkZipCode(pzipCode)
    ) {
        // 파일 업로드 유효성 검사 및 FormData에 추가
        let photoInput = document.getElementById("photoInput");
        if (photoInput.files.length > 0) {
            let photoFile = photoInput.files[0];
            if (/\.(jpe?g|png)$/i.test(photoFile.name) && photoFile.size <= 100 * 1024 * 1024) {
                // 파일이 유효하면 FormData에 추가
                let formData = new FormData(document.getElementById("profUserAddForm"));
                formData.append("photo", photoFile);
                formData.append("photoName", photoFile.name);
                document.getElementById("profUserAddForm").submit();
            } else {
                alert("이미지 파일은 jpeg, jpg, png 형식이어야 하며, 크기는 10MB 이하이어야 합니다.");
            }
        } else {
            alert("사진을 선택해주세요.");
        }
    }
}