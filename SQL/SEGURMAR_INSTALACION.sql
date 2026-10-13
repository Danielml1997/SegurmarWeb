-- ===========================================================================
-- SEGURMAR - BASE DE DATOS MYSQL 8.0
-- Sistema web de gestión de cotizaciones y servicios tecnológicos
-- Esquema inicial con tablas de clientes, servicios, cotizaciones y usuarios.
--
-- Ejecución: abrir este archivo en MySQL Workbench y ejecutar todo el script.
-- No elimina bases de datos ni tablas existentes. Puede volver a ejecutarse.
-- ===========================================================================

CREATE DATABASE IF NOT EXISTS segurmar_db
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE segurmar_db;

-- ---------------------------------------------------------------------------
-- 1. CLIENTE
-- Campos utilizados por CotizacionServlet:
--     INSERT INTO cliente (nombre_empresa, telefono, correo) VALUES (?, ?, ?)
-- ---------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS cliente (
    id_cliente INT NOT NULL AUTO_INCREMENT,
    nombre_empresa VARCHAR(180) NOT NULL,
    telefono VARCHAR(30) NOT NULL,
    correo VARCHAR(254) NOT NULL DEFAULT '',
    PRIMARY KEY (id_cliente),
    INDEX idx_cliente_correo (correo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ---------------------------------------------------------------------------
-- 2. SERVICIO
-- El Servlet busca el servicio por su NOMBRE usando LOWER(nombre) = LOWER(?).
-- Por ello se precargan los siete textos EXACTOS del <select> de index.jsp.
-- ---------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS servicio (
    id_servicio INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(120) NOT NULL,
    descripcion TEXT NULL,
    PRIMARY KEY (id_servicio),
    UNIQUE KEY uq_servicio_nombre (nombre)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ---------------------------------------------------------------------------
-- 3. COTIZACION
-- Campos utilizados por CotizacionServlet:
--     INSERT INTO cotizacion
--       (id_cliente, id_servicio, descripcion_proyecto, estado)
--     VALUES (?, ?, ?, 'PENDIENTE')
-- La fecha de solicitud es automática porque el Servlet no la proporciona.
-- ---------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS cotizacion (
    id_cotizacion INT NOT NULL AUTO_INCREMENT,
    id_cliente INT NOT NULL,
    id_servicio INT NOT NULL,
    descripcion_proyecto TEXT NOT NULL,
    estado VARCHAR(30) NOT NULL DEFAULT 'PENDIENTE',
    fecha_solicitud DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id_cotizacion),
    INDEX idx_cotizacion_cliente (id_cliente),
    INDEX idx_cotizacion_servicio (id_servicio),
    INDEX idx_cotizacion_estado_fecha (estado, fecha_solicitud),
    CONSTRAINT fk_cotizacion_cliente
        FOREIGN KEY (id_cliente) REFERENCES cliente (id_cliente)
        ON UPDATE CASCADE ON DELETE RESTRICT,
    CONSTRAINT fk_cotizacion_servicio
        FOREIGN KEY (id_servicio) REFERENCES servicio (id_servicio)
        ON UPDATE CASCADE ON DELETE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ---------------------------------------------------------------------------
-- 4. USUARIO (CUENTAS DEL PERSONAL AUTORIZADO)
-- La aplicación consulta esta tabla durante el inicio de sesión.
-- Incluye una cuenta administradora de DEMOSTRACIÓN; no requiere un archivo BAT.
-- ---------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS usuario (
    id_usuario INT NOT NULL AUTO_INCREMENT,
    nombre_usuario VARCHAR(100) NOT NULL,
    correo VARCHAR(254) NOT NULL,
    clave_hash VARCHAR(255) NOT NULL,
    activo TINYINT(1) NOT NULL DEFAULT 1,
    fecha_creacion DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id_usuario),
    UNIQUE KEY uq_usuario_correo (correo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ---------------------------------------------------------------------------
-- 5. CATALOGO INICIAL DE SERVICIOS (LOS SIETE DEL FORMULARIO WEB)
-- INSERT IGNORE evita duplicar servicios si vuelves a ejecutar el script.
-- ---------------------------------------------------------------------------
INSERT IGNORE INTO servicio (nombre, descripcion) VALUES
    ('Videovigilancia CCTV', 'Instalación y soluciones de videovigilancia CCTV.'),
    ('Redes y comunicaciones', 'Implementación de redes y sistemas de comunicaciones.'),
    ('Control de acceso', 'Sistemas y soluciones de control de acceso.'),
    ('Mantenimiento', 'Mantenimiento de equipos e infraestructura tecnológica.'),
    ('Energía solar', 'Soluciones y proyectos de energía solar.'),
    ('Centro de monitoreo', 'Implementación de centros de monitoreo.'),
    ('Proyecto integral', 'Solución tecnológica integral según requerimiento.');

-- ---------------------------------------------------------------------------
-- 6. ADMINISTRADOR INICIAL DE DEMOSTRACION (solo entorno local/académico)
-- Cuenta: admin@segurmar.test
-- La contraseña inicial se comunica en LEEME_PRIMERO.md; nunca se almacena
-- como texto plano en la tabla. El hash utiliza PBKDF2-HMAC-SHA256,
-- sal de 16 bytes y 310000 iteraciones, compatible con Contrasenas.java.
-- INSERT IGNORE no sobrescribe usuarios ni contraseñas existentes.
-- NO usar la cuenta predeterminada en sistemas públicos o de producción.
-- ---------------------------------------------------------------------------
INSERT IGNORE INTO usuario (nombre_usuario, correo, clave_hash, activo) VALUES
    ('Administrador SEGURMAR (DEMO)', 'admin@segurmar.test',
     'pbkdf2_sha256$310000$Ch1V2Sey0axLcDiVw5+0uw==$urbqeXAfDP39HxKoBDyJkRWdIcHn8F6HoiUtsduE9oE=', 1);

-- ---------------------------------------------------------------------------
-- 7. COMPROBACION: 4 tablas, 7 servicios y cuenta de DEMOSTRACION activa
-- Las consultas no muestran contraseñas ni hashes.
-- ---------------------------------------------------------------------------
SHOW TABLES;
SELECT id_servicio, nombre FROM servicio ORDER BY id_servicio;
SELECT COUNT(*) AS total_servicios FROM servicio;
SELECT id_usuario, nombre_usuario, correo, activo FROM usuario
    WHERE correo = 'admin@segurmar.test';
SELECT COUNT(*) AS total_cotizaciones FROM cotizacion;
