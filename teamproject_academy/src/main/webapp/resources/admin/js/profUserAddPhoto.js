/* 사진 미리보기 */
function displayPhotoPreview(event) {
        let input = document.getElementById("photoInput");
        let previewElement = document.getElementById('photoPreview');

        if (input.files && input.files[0]) {
            let reader = new FileReader();
            let file = input.files[0];

            if (/\.(jpe?g|png)$/i.test(file.name)) { // 파일 형식 유효성 검사 jpeg, jpg, png
				if(file.size <= 100 * 1024 * 1024){ // 파일 크기 유효성 검사 크기 = 10MB
                	reader.onload = function (e) {
                    previewElement.innerHTML = '<img src="' + e.target.result + '" style="width:100%; height:100%;" />';
					
					console.log("파일이름 : ", file.name);                    
                	}
             	}
                reader.readAsDataURL(file);
            } else {
                alert("이미지 파일만 업로드 가능합니다.");
                input.value = ''; // 파일 선택을 취소하여 미리 선택한 파일을 업로드하지 않도록 합니다.
                previewElement.innerHTML = ''; // 이미지 미리보기를 초기화합니다.
            }
        } else {
            previewElement.innerHTML = ''; // 이미지 미리보기를 초기화합니다.
        }
}

document.getElementById("photoInput").addEventListener("input", function() {
    		let resultPhoto = displayPhotoPreview(this);
    		console.log(resultPhoto);
		});