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

    <title>error</title>

    <!-- Bootstrap core CSS -->
    <link href="static/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="dynamic/css/signin.css" rel="stylesheet">

  </head>

  <body>

    <div class="container">

        <h2 class="form-signin-heading" style="text-align: center;">welcome to madnotes.</h2>
        
        <br>
		<table border="0" width="50%" style="text-align: center;">
			<tbody>
				<tr>
					<td>DEBUG: Your user ID is:
					<br>(One probably hasn't been passed to the session, yet)</td>
					<td><%=session.getAttribute("user_id")%></td>
				</tr>
				<tr>
					<td>DEBUG: You have registered as:</td>
					<td><%=session.getAttribute("email")%></td>
				</tr>
				<tr>
					<td>DEBUG: You have set your password as:</td>
					<td><%=session.getAttribute("password")%></td>
				</tr>
				<tr>
					<td>DEBUG: You have set your first name as:</td>
					<td><%=session.getAttribute("firstname")%></td>
				</tr>
				<tr>
					<td>DEBUG: You have set your last name as:</td>
					<td><%=session.getAttribute("lastname")%></td>
				</tr>
			</tbody>
		</table>
		<br> <br>
		<h1>BOOYA!</h1>
		<p>
			<a href="logout">Logout</a>
		</p>

    </div>
    
  </body>
</html>