package com.techacademy.backend_api.service;

import com.techacademy.backend_api.entity.Certificado;
import com.techacademy.backend_api.dto.CertificadoDTO;
import com.techacademy.backend_api.repository.CertificadoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CertificadoService {

    private final CertificadoRepository certificadoRepository;

    // Inyección de dependencias por constructor (Buena práctica de arquitectura)
    public CertificadoService(CertificadoRepository certificadoRepository) {
        this.certificadoRepository = certificadoRepository;
    }

    // GET: Listar con JOIN (DTO)
    public List<CertificadoDTO> listarDetallado() {
        return certificadoRepository.obtenerCertificadosConDetalles();
    }

    // GET: Buscar un solo registro para editarlo
    public Certificado buscarPorId(Long id) {
        return certificadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Certificado no encontrado con el ID: " + id));
    }

    // POST: Guardar nuevo certificado
    public Certificado guardar(Certificado certificado) {
        return certificadoRepository.save(certificado);
    }

    // PUT: Actualizar certificado existente
    public Certificado actualizar(Long id, Certificado datosNuevos) {
        return certificadoRepository.findById(id)
                .map(certificadoExistente -> {
                    certificadoExistente.setAlumnoNombre(datosNuevos.getAlumnoNombre());
                    certificadoExistente.setCodigoVerificacion(datosNuevos.getCodigoVerificacion());
                    certificadoExistente.setNotaFinal(datosNuevos.getNotaFinal());
                    certificadoExistente.setIdEmpresa(datosNuevos.getIdEmpresa());
                    certificadoExistente.setIdCurso(datosNuevos.getIdCurso());
                    return certificadoRepository.save(certificadoExistente);
                })
                .orElseThrow(() -> new RuntimeException("Error al actualizar. ID no existe: " + id));
    }

    // DELETE: Eliminar certificado
    public void eliminar(Long id) {
        if (!certificadoRepository.existsById(id)) {
            throw new RuntimeException("Error al eliminar. ID no existe: " + id);
        }
        certificadoRepository.deleteById(id);
    }
}