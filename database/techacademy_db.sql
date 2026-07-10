-- ====================================================================
-- SCRIPT COMPLETO DE BASE DE DATOS - SEGUNDO AVANCE SOWEB 2026
-- ====================================================================
CREATE DATABASE IF NOT EXISTS techacademy_db;
USE techacademy_db;

-- --------------------------------------------------------------------
-- 1. CREACIÓN DE TABLAS MAESTRAS (NORMALIZACIÓN 3FN)
-- --------------------------------------------------------------------

-- Tabla Maestra: Empresas Clientes Corporativos
CREATE TABLE IF NOT EXISTS empresas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    razon_social VARCHAR(150) NOT NULL,
    ruc VARCHAR(11) NOT NULL UNIQUE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Tabla Maestra: Catálogo Técnico de Cursos
CREATE TABLE IF NOT EXISTS cursos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre_curso VARCHAR(100) NOT NULL,
    horas_academicas INT NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Tabla Maestra de Seguridad Perimetral: Usuarios del Sistema
CREATE TABLE IF NOT EXISTS usuario (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    rol VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------------------
-- 2. CREACIÓN DE TABLA PRINCIPAL DE NEGOCIO (CON LLAVES FORÁNEAS)
-- --------------------------------------------------------------------

-- Tabla Transaccional: Certificados Emitidos
CREATE TABLE IF NOT EXISTS certificados (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    alumno_nombre VARCHAR(150) NOT NULL,
    codigo_verificacion VARCHAR(50) NOT NULL UNIQUE,
    nota_final DOUBLE NOT NULL,
    id_empresa INT NOT NULL,
    id_curso INT NOT NULL,
    CONSTRAINT fk_certificado_empresa FOREIGN KEY (id_empresa) REFERENCES empresas(id),
    CONSTRAINT fk_certificado_curso FOREIGN KEY (id_curso) REFERENCES cursos(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------------------
-- 3. POBLACIÓN DE DATOS SEMILLA (INSERCIONES DE PRUEBA CORRELACIONADAS)
-- --------------------------------------------------------------------

-- Registros semilla para Empresas
INSERT INTO empresas (razon_social, ruc) VALUES 
('Interbank Corporación', '20100053451'),
('Alicorp Soluciones', '20100055231'),
('Saga Falabella Tecnologías', '20100078421');

-- Registros semilla para Cursos
INSERT INTO cursos (nombre_curso, horas_academicas) VALUES 
('Desarrollo Full Stack con Angular y Spring Boot', 80),
('Arquitectura de Microservicios Cloud Native', 60),
('Ingeniería de Datos y Modelamiento en MySQL', 48);

-- Registros semilla para Certificados (Asociados a los IDs creados arriba)
INSERT INTO certificados (alumno_nombre, codigo_verificacion, nota_final, id_empresa, id_curso) VALUES 
('Carlos Mendoza Ortiz', 'CERT-2026-001', 18.5, 1, 1),
('Gabriela Villar Saavedra', 'CERT-2026-002', 19.0, 1, 2),
('Adrian Henry Ramos', 'CERT-2026-003', 16.0, 2, 1),
('Pamela Vásquez Castro', 'CERT-2026-004', 17.5, 3, 3);

-- Registro semilla de Seguridad: Usuario Administrador
-- Credenciales en texto plano: Username = admin / Password = password123
INSERT INTO usuario (username, password, rol) VALUES 
('admin', '$2a$10$TXS.999ntpSlrcgXofuiYebE0RIv1XAW7scjU717U7EB6wIDd6Wre', 'ADMIN');