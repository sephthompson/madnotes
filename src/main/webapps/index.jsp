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

</head>
<body>
	<%@include file="menubar.jsp"%>

	<!-- Note the column within the row within the container.  This must be set for all subsequent pages until Angular can be implemented -->
	<div class="container" style="text-align: center;">
		<div class="row" style="display: inline-block;">
			<div class="col-sm-3 col-lg-12">
				<h1 style="text-align: center;">madnotes</h1>

				<form class="form-signin" role="form" action=signin method="post">
					<h2 class="form-signin-heading" style="text-align: left;">please
						sign in</h2>
					<input name="email" type="email" class="form-control"
						placeholder="email address" required autofocus> <input
						name="password" type="password" class="form-control"
						placeholder="password" required> <label class="checkbox"
						style="width: 45%; display: inline-block; text-align: center">
						<input type="checkbox" value="remember-me"> remember me
					</label> <a href="forgotpass.jsp"
						style="width: 45%; display: inline-block; text-align: center">forgot
						password?</a>
					<button class="btn btn-lg btn-primary btn-block" type="submit">sign
						in</button>
				</form>
				<p style="text-align: center;">
					if you do not have an account, please <a href="register.jsp">register</a>.
				</p>
			</div>
		</div>
	</div>
	<!-- /row -->

	<%@include file="footer.jsp"%>


	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
	<script src="static/js/bootstrap.min.js"></script>
</body>
</html>

<%
	if (session.getAttribute("email") != null) {
		response.sendRedirect("profile.jsp");
	}
%>