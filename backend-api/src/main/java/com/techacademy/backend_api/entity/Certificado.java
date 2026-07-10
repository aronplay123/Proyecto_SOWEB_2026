package com.techacademy.backend_api.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "certificados")
public class Certificado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "alumno_nombre", nullable = false, length = 150)
    private String alumnoNombre;

    @Column(name = "codigo_verificacion", nullable = false, unique = true, length = 50)
    private String codigoVerificacion;

    @Column(name = "nota_final", nullable = false)
    private Double notaFinal;

    @Column(name = "id_empresa", nullable = false)
    private Integer idEmpresa;

    @Column(name = "id_curso", nullable = false)
    private Integer idCurso;

    public Certificado() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getAlumnoNombre() { return alumnoNombre; }
    public void setAlumnoNombre(String alumnoNombre) { this.alumnoNombre = alumnoNombre; }
    public String getCodigoVerificacion() { return codigoVerificacion; }
    public void setCodigoVerificacion(String codigoVerificacion) { this.codigoVerificacion = codigoVerificacion; }
    public Double getNotaFinal() { return notaFinal; }
    public void setNotaFinal(Double notaFinal) { this.notaFinal = notaFinal; }
    public Integer getIdEmpresa() { return idEmpresa; }
    public void setIdEmpresa(Integer idEmpresa) { this.idEmpresa = idEmpresa; }
    public Integer getIdCurso() { return idCurso; }
    public void setIdCurso(Integer idCurso) { this.idCurso = idCurso; }
}