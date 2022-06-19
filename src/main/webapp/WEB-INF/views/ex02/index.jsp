<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>練習二</title>
<script>
var dataArea = null;
window.onload = function(){
    var btn21 = document.getElementById("btn21");
    var btn22 = document.getElementById("btn22");
    var btn23 = document.getElementById("btn23");
    var btn24 = document.getElementById("btn24");
    dataArea = document.getElementById("dataArea");
    var key = 2;
    btn21.onclick = function(){
        var xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function(){
            if(xhr.readyState == 4 && xhr.status == 200){
                showReq01Result(xhr.responseText);
            }
        }
        xhr.open('GET',"<c:url value='/ex02req01' />",true);
        
        xhr.send();
    }
    btn22.onclick = function(){
        var xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function(){
            if(xhr.readyState == 4 && xhr.status == 200){
                showReq02Result(xhr.responseText);
            }
        }
        xhr.open('GET',"<c:url value='/ex02req02' />" + "/" + key, true);
        xhr.send();
    }
    btn23.onclick = function(){
        var xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function(){
            if(xhr.readyState == 4 && xhr.status == 200){
                showReq03Result(xhr.responseText);
            }
        }
        xhr.open('GET',"<c:url value='/ex02req03' />", true);
        xhr.send();
    }
    btn24.onclick = function(){
        var xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function(){
            if(xhr.readyState == 4 && xhr.status == 200){
                showReq04Result(xhr.responseText);
            }
        }
        xhr.open('GET',"<c:url value='/ex02req04' />", true);
        xhr.send();
    }
    
    
}
function showReq01Result(result){
    dataArea.innerHTML = "<font color ='red' >" + result + "</font>";
}
function showReq02Result(employee){
	var empObj = JSON.parse(employee);
	var response = "<table border='1'>";
	response += "<th colspan='2'>員工基本資料</th>"; 
	response += "<tr><td>員工編號</td><td>" + empObj.id + "</td></tr>";
	response += "<tr><td>員工姓名</td><td>" + empObj.name + "</td></tr>";
	response += "<tr><td>員工生日</td><td>" + empObj.birthday + "</td></tr>";
	response += "<tr><td>員工薪水</td><td>" + empObj.salary + "</td></tr>";
	response += "</table>";
    dataArea.innerHTML = response;
}
function showReq03Result(employees){
//	alert(employees)
	console.log(employees);
	var array = JSON.parse(employees);
	var response = "<table border='1'>"+
	"<tr><td>員編</td><td>姓名</td><td>生日</td><td>薪水</td><td>照片(請求參數)</td><td>照片(路徑變數)</td></tr>";
	for (var n = 0; n < array.length; n++) {
		var emp = array[n];
		var imageURL_reqParam = "<c:url value='/ex02req05' />?no="+ emp.id;
		var imageURL_pathVar = "<c:url value='/ex02req06' />/" + emp.id;
		response += "<tr><td>"+emp.id+"</td>"
				 + "<td>"+emp.name+"</td>"
				 + "<td>"+emp.birthday+"</td>"
				 + "<td>"+emp.salary+"</td>"
				 + "<td><img  width='100' height='100' src='" + imageURL_reqParam + "'</td>"
				 + "<td><img  width='100' height='100' src='" + imageURL_pathVar + "'</td></tr>";
	}
	
	response +="</table>";
    dataArea.innerHTML = response;
}
function showReq04Result(mapData){
	var obj = JSON.parse(mapData);
	var array = obj.lotteryNumber;
	for (var n = 0; n < obj.lotteryNumber.length; n++) {
		console.log("元素"+(n+1)+":"+obj.lotteryNumber[n]);
	}
	dataArea.innerHTML = "<font color ='blue' >" + array + "</font>";
	
}
</script>
</head>
<body>
	<div align='center'>
		<h3>happy練習二</h3>
		<hr>
		<button id='btn21'>傳回單一字串: 現在本地時間</button>
		<button id='btn22'>傳回單一物件 : Employee</button>
		<button id='btn23'>傳回多個型態相同物件 : List<Employee></button>
		<button id='btn24'>傳回多個型態不同物件 : Map<String, Object></button>
		<hr>
		<div id='dataArea'>&nbsp;</div>
		<hr>
		<a href="/">回前頁-A NG</a> <a href="<c:url value='/'  />">回前頁-B OK</a>
	</div>
</body>
</html>