<!DOCTYPE html>
<html lang="en">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<style type="text/css">
	html, body {
		margin: 0;
		padding: 0;
		background-color: #F3F4F4;
		text-align: center;
	}
	img {
		margin-top: 30px;
	}
</style>
</head>

<body>

<a href="${request.contextPath}"><img src="${app.publicResPath}/images/404.jpg"></a>

<table style="display: none;">
	<tr><td>status:</td><td>${status }</td></tr>
	<tr><td>error:</td><td>${error }</td></tr>
	<tr><td>exception:</td><td>${exception }</td></tr>
	<tr><td>message:</td><td>${message }</td></tr>
	<tr><td>path:</td><td>${path }</td></tr>
	<tr><td>timestamp:</td><td>${timestamp ?datetime}</td></tr>
</table>

</body>

</html>


