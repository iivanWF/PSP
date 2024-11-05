<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<title>Chollo Vuelos</title>
<link rel="stylesheet"  href="cssdeiberia1.css" />
</head>
<body>
    <div id="central">
        <div id="cabecera">
            <h1 class="titulo">Chollo Vuelos</h1>
            <div id="menu">
                <a href="viaja.jsp">Viaja</a>
                <a href="Hoteles.jsp">Hoteles</a>
                <a href="servicios.jsp">Servicios</a>
                <a href="ayuda.jsp">Necesitas Ayuda</a>
            </div>
        </div>
        <div class="formulario-vuelo">
    <h2>Buscar Vuelo</h2>
    <form action="BuscarVuelos" method="POST">
	    <div class="form-group">
	        <input type="text" id="origen" name="origen" placeholder="Ciudad de origen" required>
	        <input type="text" id="destino" name="destino" placeholder="Ciudad de destino" required>
	        <input type="date" id="fecha-ida" name="fecha-ida" required>
	        <input type="date" id="fecha-vuelta" name="fecha-vuelta">
	        <input type="number" id="personas" name="personas" placeholder="Nº Personas" min="1" required>
	        <button type="submit">Buscar</button>
	    </div>
	</form>
	</div>
   <br>
   <div class="container">
        <div class="card">
          <figure>
            <img src="image/vi.webp" width="500" height="320">
          </figure>
          <div class="contenido">
            <h3>Vuelos Baratos</h3>
            <p>Descubre nuestras ofertas de vuelos al mejor precio. ¿Donde te llevamos?</p>
            <a href="servicios.jsp">Leer mas</a>
          </div>
        </div>
        <div class="card">
          <figure>
            <img src="image/ht.webp" width="500" height="320">
          </figure>
          <div class="contenido">
            <h3>Vuelo + Hotel</h3>
            <p>Combina Vuelo + Hotel para obtener el mejor precio.</p>
            <a href="servicios.jsp">Leer mas</a>
          </div>
        </div>
        <div class="card">
          <figure>
            <img src="image/bs.webp" width="500" height="320">
          </figure>
          <div class="contenido">
            <h3>Business Larga distancia</h3>
            <p>Más personalizada, más digital, sostenible y con una nueva propuesta gastronómica</p>
            <a href="servicios.jsp">Leer mas</a>
          </div>
        </div>
      </div>
      
	      <hr size= "4" width="100%">
	      
   </div>
</body>
</html>