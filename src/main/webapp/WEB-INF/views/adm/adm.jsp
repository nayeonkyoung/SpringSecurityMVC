<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.4.1.js"></script>
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-serialize-object/2.5.0/jquery.serialize-object.min.js"></script>
<script>
	var persons = [];
	/*
	function add() {
		var formData = $("#form").serializeArray();
		var struct = {}
		for ( var idx in formData) {
			var elm = formData[idx];
			console.log(elm.name);
			struct[elm.name] = elm.value;
		}
		console.log(JSON.stringify(struct));
		persons.push(struct);
	}
	*/
	function send() {
		//var formData = $("#form").serializeObject();
		//var content = JSON.stringify(persons);
		var content = JSON.stringify($("#form").serializeObject());
		console.log(content);
		$.ajax({
			url : '/handle/test',
			dataType : 'json',
			type : 'POST',
			contentType : "application/json; charset=utf-8",
			data : content,
			traditional : true,
			success : function(data) {
				var result = JSON.stringify(data);
				console.log(result);
				document.getElementById("rtn").innerHTML += result + "<br />";
				document.getElementById("rtn").innerHTML += "성공";
			},
			error : function(xhr, status, error) {
				document.getElementById("rtn").innerHTML = xhr + "["
						+ xhr.status + xhr.statusText + "]" + error;
			}
		});
		return false;
	}
</script>
</head>


<form id="form" method="post">
	이름 : <input type="text" name="name"> <br /> 
	회사 : <input type="text" name="company[name]"> <br /> 
	주소 : <input type="text" name="company[addr]"> <br />
</form>

<input id="send" onClick="add()" type="button" value="더하기" />
<br />
<input id="send" onClick="send()" type="button" value="보내기" />
<br />

<textarea id="rtn" style="width:700px; height:500px;">"정보"</textarea>
<form action="/common/auth/logout" method="post">
	<div>
		<button type="submit">
			<span>로그아웃</span>
		</button>
	</div>
</form>
</body>
</html>