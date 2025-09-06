<%-- 
    Document   : index
    Created on : 2/09/2025, 15:07:36
    Author     : Usuario
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Bienvenido al Juego del Ahorcado</title>
    <link rel="stylesheet" href="Styles/css2.css">
    <link rel="stylesheet" href="Styles/login.css">
</head>
<body>
    <video class="bg-video" autoplay muted loop playsinline>
        <source src="Images/Login.mp4" type="video/mp4">
        Tu navegador no soporta videos HTML5.
    </video>
    
    <button class="start-button" onclick="mostrarLogin()">
        JUGAR AHORA
    </button>

    <form class="login-container floating" id="loginForm" style="display: none;" 
          action="Controlador" method="POST">
        <fieldset>
            <legend>Login</legend>
            <input type="text" name="txtUsuario" placeholder="Username" required />
            <input type="password" name="txtPassword" placeholder="Password" required />
            <input type="hidden" name="menu" value="Usuarios">
            <input type="hidden" name="accion" value="Validar">
            <button type="submit">Entrar</button>
        </fieldset>
    </form>
    <script>
        function mostrarLogin() {
            document.querySelector('.start-button').style.display = 'none';
            document.getElementById('loginForm').style.display = 'block';
        }
    </script>
</body>
</html>