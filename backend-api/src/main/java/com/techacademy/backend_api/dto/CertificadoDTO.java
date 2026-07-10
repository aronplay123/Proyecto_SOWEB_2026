package com.techacademy.backend_api.dto;

public interface CertificadoDTO {
    Long getId();
    String getAlumnoNombre();
    String getCodigoVerificacion();
    Double getNotaFinal();
    
    // Estos dos campos son la magia: Vienen de las otras tablas
    String getRazonSocial(); 
    String getNombreCurso(); 
}