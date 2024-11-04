<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<title>Chollo Vuelos - Registro de Usuario</title>
<style>
    body {
        background-image: url('image/nuub.jpg');
        background-size: cover;
        background-repeat: no-repeat;
        background-position: center;
        font-family: Arial, sans-serif;
        color: #333;
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
        margin: 0;
    }

    form {
        background-color: #faeded;
        border-radius: 10px;
        box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.2);
        padding: 25px;
        max-width: 400px;
        text-align: center;
        margin: auto;
    }

    h2 {
        font-size: 24px;
        color: #444;
        margin-bottom: 20px;
        text-shadow: 1px 1px 1px rgba(0, 0, 0, 0.1);
    }

    input[type="text"], input[type="password"] {
        width: 90%;
        padding: 10px;
        margin: 10px 0;
        border: 1px solid #ccc;
        border-radius: 5px;
        font-size: 16px;
        box-sizing: border-box;
        transition: border-color 0.3s ease;
    }

    input[type="text"]:focus, input[type="password"]:focus {
        border-color: #1e90ff;
        outline: none;
    }

    input[type="submit"], input[type="reset"] {
        width: 45%;
        padding: 10px;
        margin: 10px 5px;
        border: none;
        border-radius: 5px;
        font-size: 16px;
        cursor: pointer;
        transition: background-color 0.3s ease;
    }

    input[type="submit"] {
        background-color: #1e90ff;
        color: #fff;
    }

    input[type="reset"] {
        background-color: #ff4c4c;
        color: #fff;
    }

    input[type="submit"]:hover, input[type="reset"]:hover {
        background-color: #333;
        color: #fff;
    }
</style>
</head>
<body>
    <form action="RegistroUsuarioServlet" method="POST">
        <h2>Nuevo Usuario</h2>
        <input type="text" name="nombre" placeholder="Nombre">
        <input type="text" name="apellido" placeholder="Apellido">
        <input type="text" name="telf" placeholder="Teléfono">
        <input type="text" name="corr" placeholder="Correo Electrónico">
        <input type="password" name="password" placeholder="Contraseña">
        
        <input type="submit" value="Siguiente">
        <input type="reset" value="Borrar">
    </form>
</body>
</html>