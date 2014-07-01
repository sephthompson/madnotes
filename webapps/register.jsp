<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="shortcut icon" href="../../assets/ico/favicon.ico">

<title>welcome to madnotes</title>

<!-- Bootstrap core CSS -->
<link href="static/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="dynamic/css/signin.css" rel="stylesheet">
<link href="dynamic/css/footer.css" rel="stylesheet">

<!-- Just for debugging purposes. Don't actually copy this line! -->
<!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>

	<%@include file="/menubar.jsp"%>

	<div class="container">
		<h1 style="text-align: center;">madnotes</h1>

		<form class="form-signin" role="form" action=register method="post">
			<h2 class="form-signin-heading">register</h2>
			<input name="email" type="email" class="form-control"
				placeholder="email address" required> <input name="password"
				type="password" class="form-control" placeholder="password" required>
			<input name="firstname" type="text" class="form-control"
				placeholder="first name (optional)"> <input name="lastname"
				type="text" class="form-control" placeholder="last name (optional)">
			<button class="btn btn-lg btn-primary btn-block" type="submit" style="margin-top: 20px;">submit</button>
		</form>
		<p style="text-align: center;">
			if you already have an account, please <a href="index.jsp">sign
				in</a>.
		</p>

	</div>
	<!-- /container -->

	<%@include file="/footer.jsp"%>

	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
	<script src="static/js/bootstrap.min.js"></script>
</body>
</html>