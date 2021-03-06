<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<title>Spring Boot OAuth2 SSO Example</title>
<head>
	<script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
	<script type="text/javascript">
	    $.get("/userdetail", function(data) {
	    	$("#user").html(data.userAuthentication.details.name);
	        $(".unauthenticated").hide()
	        $(".authenticated").show()
	    });
	    
	    function logout() {
	    	alert('2');
	        $.post("/logout", function() {
	        	alert('2.1');
	            $("#user").html('');
	            $(".unauthenticated").show();
	            $(".authenticated").hide();
	        })
	        return true;
	    }
	</script>
</head>
<body>
	<div id="unauthenticated" class="container unauthenticated">
		Github Login <a href="/login">Github Login</a>
	</div>
	<div id="authenticated" class="container authenticated" style="display: none">
		Logged in user: <span id="user"></span>
		<div>
			<button onClick="logout()" class="btn btn-primary">Logout</button>
		</div>
	</div>
	 	<div>
			<button onClick="logout()" class="btn btn-primary">Logout</button>
		</div>
		
		<div>
			<!-- <a onClick="logout()" class="btn btn-primary">Logout</button> -->
			<!-- <a href="/logout">Logout</a>-->
		</div>
</body>
</html>