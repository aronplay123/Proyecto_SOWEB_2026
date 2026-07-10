package com.techacademy.backend_api.repository;

import com.techacademy.backend_api.entity.Certificado;
import com.techacademy.backend_api.dto.CertificadoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CertificadoRepository extends JpaRepository<Certificado, Long> {

    // Consulta SQL Nativa con JOIN para no exponer IDs numéricos en el Frontend
    @Query(value = "SELECT c.id, c.alumno_nombre AS alumnoNombre, c.codigo_verificacion AS codigoVerificacion, " +
                   "c.nota_final AS notaFinal, e.razon_social AS razonSocial, cr.nombre_curso AS nombreCurso " +
                   "FROM certificados c " +
                   "INNER JOIN empresas e ON c.id_empresa = e.id " +
                   "INNER JOIN cursos cr ON c.id_curso = cr.id", 
           nativeQuery = true)
    List<CertificadoDTO> obtenerCertificadosConDetalles();
}