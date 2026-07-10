package com.techacademy.backend_api.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "cursos")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre_curso", nullable = false, length = 100)
    private String nombreCurso;

    @Column(name = "horas_academicas", nullable = false)
    private Integer horasAcademicas;

    public Curso() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getNombreCurso() { return nombreCurso; }
    public void setNombreCurso(String nombreCurso) { this.nombreCurso = nombreCurso; }
    public Integer getHorasAcademicas() { return horasAcademicas; }
    public void setHorasAcademicas(Integer horasAcademicas) { this.horasAcademicas = horasAcademicas; }
}