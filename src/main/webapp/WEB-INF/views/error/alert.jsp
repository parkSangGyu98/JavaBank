<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Error</title>
</head>
<body>

	<script type="text/javascript">
		var message= "${msg}";
		alert(message);
		history.back();
	</script>

</body>
</html>