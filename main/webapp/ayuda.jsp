<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Ayuda - Vuelos</title>
    <link rel="stylesheet"  href="ayuda.css" />
</head>
<body>
		<div id="cabecera">
            <h1 class="titulo">Chollo Vuelos</h1>
            <div id="menu">
                <a href="viaja.jsp">Viaja</a>
                <a href="iberia1.jsp">Tus Vuelos</a>
                <a href="#">Servicios</a>
            </div>
        </div>
        
        <div class="entr">
        <h1>Centro de Ayuda de Vuelos</h1>
        <p>Encuentra respuestas a preguntas frecuentes y ayuda sobre nuestros servicios de vuelos.</p>
    	</div>

    <section class="faq">
        <h2>Preguntas Frecuentes</h2>
        <div class="question">
            <h3>¿Cómo puedo buscar vuelos?</h3>
            <p>Para buscar vuelos, utiliza nuestro formulario en la página principal, selecciona el origen, destino, fecha de ida y el número de pasajeros, y haz clic en "Buscar".</p>
        </div>
        <div class="question">
            <h3>¿Puedo cambiar mi reserva después de confirmar?</h3>
            <p>Sí, puedes realizar cambios en tu reserva, aunque esto puede conllevar un costo adicional dependiendo de la política de la aerolínea. Ve a la sección de "Mis Reservas" para realizar cambios.</p>
        </div>
        <div class="question">
            <h3>¿Qué métodos de pago aceptan?</h3>
            <p>Aceptamos tarjetas de crédito, débito y pagos a través de plataformas de pago electrónico como PayPal. Todas las transacciones son seguras y encriptadas.</p>
        </div>
    </section>

    <section class="support">
        <h2>Contacto de Soporte</h2>
        <p>Si tienes preguntas adicionales o necesitas ayuda, contacta a nuestro equipo de soporte:</p>
        <ul>
            <li>Email: <a href="mailto:soporte@vuelos.com">soporte@vuelos.com</a></li>
            <li>Teléfono: +34 123 456 789</li>
            <li>Horario de Atención: Lunes a Viernes, 9:00 - 18:00</li>
        </ul>
    </section>

    <section class="tips">
        <h2>Consejos de Viaje</h2>
        <p>Algunos consejos para tener un viaje tranquilo:</p>
        <ul>
            <li>Llega al aeropuerto con al menos 2 horas de antelación para vuelos nacionales y 3 horas para vuelos internacionales.</li>
            <li>Verifica que tienes todos los documentos necesarios, como el pasaporte y las visas.</li>
            <li>Consulta las políticas de equipaje de la aerolínea para evitar cargos adicionales.</li>
        </ul>
    </section>

    <footer>
        <p>&copy; 2023 Vuelos - Todos los derechos reservados.</p>
    </footer>
    
    <script>
    document.addEventListener("DOMContentLoaded", function() {
        const questions = document.querySelectorAll(".question h3");
        
        questions.forEach(question => {
            question.addEventListener("click", () => {
                question.parentElement.classList.toggle("open");
            });
        });
    });
	</script>
    
</body>
</html>
