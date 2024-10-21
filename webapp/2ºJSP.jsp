<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Ejemplo</title>
</head>
<body>
	<h1>Ejemmplo Declaraciones</h1>

    <p>La suma de 20 + 6 es: <%= Suma() %> </p>
    <p>La resta de 20 - 6 es: <%= Resta() %> </p>
    <p>La multiplicacion de 20 * 6 es: <%= Mult() %> </p>

    <%! 
    public int Suma() {
        return 20 + 6;
    }
   
    public int Resta() {
        return 20 - 6;
    }
    public int Mult() {
        return 20 * 6;
    }
    %>
</body>
</html>	