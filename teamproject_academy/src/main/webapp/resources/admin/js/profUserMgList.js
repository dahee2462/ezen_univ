function profUserMgView(pno){
	let f = document.createElement("form");
	
	let obj;
	obj = document.createElement("input");
	obj.setAttribute("type", "hidden");
	obj.setAttribute("name", "pno");
	obj.setAttribute("value", pno);
	
	f.appendChild(obj);
	f.setAttribute("method", "post");
	f.setAttribute("action", "profUserMgView");
	document.body.appendChild(f);
	f.submit();
}