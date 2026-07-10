package com.techacademy.backend_api.controller;

import com.techacademy.backend_api.entity.Certificado;
import com.techacademy.backend_api.dto.CertificadoDTO;
import com.techacademy.backend_api.service.CertificadoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/certificados")
@CrossOrigin(origins = "http://localhost:4200") // ¡CRÍTICO! Permite que Angular se conecte
public class CertificadoController {

    private final CertificadoService certificadoService;

    public CertificadoController(CertificadoService certificadoService) {
        this.certificadoService = certificadoService;
    }

    // 1. GET - Acceso Público (Cualquiera puede verificar certificados)
    @GetMapping
    public ResponseEntity<List<CertificadoDTO>> listar() {
        return ResponseEntity.ok(certificadoService.listarDetallado());
    }

    // 2. GET por ID - Acceso Público
    @GetMapping("/{id}")
    public ResponseEntity<Certificado> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(certificadoService.buscarPorId(id));
    }

    // 3. POST - Requiere Auth (Solo ADMIN)
    @PostMapping
    public ResponseEntity<Certificado> crear(@RequestBody Certificado certificado) {
        Certificado nuevo = certificadoService.guardar(certificado);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    // 4. PUT - Requiere Auth (Solo ADMIN)
    @PutMapping("/{id}")
    public ResponseEntity<Certificado> actualizar(@PathVariable Long id, @RequestBody Certificado certificado) {
        return ResponseEntity.ok(certificadoService.actualizar(id, certificado));
    }

    // 5. DELETE - Requiere Auth (Solo ADMIN)
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        certificadoService.eliminar(id);
        return ResponseEntity.ok("Certificado eliminado correctamente de la Base de Datos.");
    }
}