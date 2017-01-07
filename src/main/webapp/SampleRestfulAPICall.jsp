<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
String webPath = request.getContextPath();
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<!-- JQuery JS -->
<script type="text/javascript" src="<%=webPath %>/js/easyui/jquery-1.7.2.min.js"></script>

<!-- EASYUI JS and CSS -->
<script type="text/javascript" src="<%=webPath %>/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=webPath %>/js/easyui/locale/easyui-lang-en.js"></script>
<link rel="stylesheet" type="text/css" href="<%=webPath %>/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=webPath %>/js/easyui/themes/icon.css">

<script type="text/javascript">
 $(function(){
   callHelloWorldRestfulAPI();
   callConversationRestfulAPI();
 });

 function callHelloWorldRestfulAPI(){
	var url = "${pageContext.request.contextPath}/ws/sample/helloworld";
   	$.ajax({
		url : url,
		type : "GET",
		dataType : 'json',
		cache : false,
		beforeSend : function() {
		},
		success : function(data) {
			var message = data.message;
			$("#msg").html("{"+message+"}");
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
		  console.log("Error with message: "+textStatus);
		},
		complete : function() {
		}
	});
}
 
function callConversationRestfulAPI(){
		var url = "${pageContext.request.contextPath}/ws/sample/conversation";
	   	$.ajax({
			url : url,
			type : "GET",
			dataType : 'json',
			cache : false,
			beforeSend : function() {
			},
			success : function(data) {
				var question = data.inputText;
				var answer = data.output.text[0];
				$("#msg2").html("{Question: \""+question+"\" - Answer: \""+answer+"\"}");
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
			  console.log("Error with message: "+textStatus);
			},
			complete : function() {
			}
		});
	} 
</script>

<title>Sample Restful API Call</title>

</head>
<body>
	<div>The Hello World API Call Return Message: <span id="msg"></span></div>
	<div>The Conversation API Call Return Message: <span id="msg2"></span></div>
</body>
</html>