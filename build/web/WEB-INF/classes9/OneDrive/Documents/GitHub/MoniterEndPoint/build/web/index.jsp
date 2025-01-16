<%-- 
    Document   : login
    Created on : Jan 16, 2025, 2:49:56 PM
    Author     : Dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
    <link rel="stylesheet" href="css/login.css">
    <title>Login</title>
</head>
<body>
    <div class="wrapper">
        <form action="" method="post">
            <div class="login_box">
                <div class="login-header">
                    <span>Login</span>
                </div>
                <div class="input_box">
                    <input type="text" class="input-file" name="username" id="username" required>
                    <label for="username" class="label">Username</label>
                    <i class="fa-solid fa-user"></i>
                </div>
                <div class="input_box">
                    <input type="password" class="input-file" id="password" name="password" required>
                    <label for="password" class="label">Password</label>
                    <i class="fa-solid fa-lock"></i>
                </div>
                <div class="remember-forgot">
                    <div class="remember-me">
                        <input type="checkbox" id="remember">
                        <label for="remember"> Remember me</label>
                    </div>
                    <div class="forgot">
                        <a href="#">Forgot password</a>
                    </div>
                </div>
                <div class="input_box">
                    <input type="submit" class="input-submit" value="Login">
                </div>
            </div>
        </form>
    </div>
</body>
</html>
