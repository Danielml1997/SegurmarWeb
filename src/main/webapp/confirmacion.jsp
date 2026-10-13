<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Solicitud registrada - SEGURMAR</title>

    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f4f6f8;
            margin: 0;
            min-height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
        }

        .contenedor {
            background: white;
            width: 90%;
            max-width: 550px;
            padding: 40px;
            text-align: center;
            border-radius: 12px;
            box-shadow: 0 4px 20px rgba(0,0,0,0.12);
        }

        .check {
            font-size: 65px;
            color: #28a745;
        }

        h1 {
            color: #16324f;
        }

        .servicio {
            font-weight: bold;
            color: #16324f;
            font-size: 18px;
        }

        .boton {
            display: inline-block;
            margin-top: 20px;
            padding: 12px 25px;
            background: #16324f;
            color: white;
            text-decoration: none;
            border-radius: 6px;
        }
    </style>
</head>

<body>

    <div class="contenedor">

        <div class="check">✓</div>

        <h1>Solicitud registrada correctamente</h1>

        <p>
            Gracias,
            <strong>${nombreCliente}</strong>.
        </p>

        <p>
            Hemos recibido su solicitud de cotización para:
        </p>

        <p class="servicio">
            ${servicioSolicitado}
        </p>

        <p>
            Nuestro equipo de SEGURMAR revisará la información proporcionada.
        </p>

        <a class="boton"
           href="${pageContext.request.contextPath}/">
            Volver al inicio
        </a>

    </div>

</body>
</html>
