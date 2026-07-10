import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { CertificadoService } from './services/certificado'; 
import { Certificado } from './models/certificado';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './app.html',  // <-- Ajustado a tu estructura
  styleUrls: ['./app.css']    // <-- Ajustado a tu estructura
})
export class App implements OnInit {

  listaCertificados: Certificado[] = [];

  nuevoCertificado: Certificado = { alumnoNombre: '', codigoVerificacion: '', notaFinal: 0, idEmpresa: 1, idCurso: 1 };
  
  esEdicion: boolean = false;

  constructor(private certificadoService: CertificadoService) {}

  ngOnInit(): void {
    this.cargarCertificados();
  }

  // MÓDULO 1: Leer (Público)
  cargarCertificados(): void {
    this.certificadoService.listar().subscribe({
      next: (data) => {
        this.listaCertificados = data;
        console.log("Certificados cargados:", data);
      },
      error: (err) => console.error('Error cargando certificados', err)
    });
  }

  // MÓDULO 2 & 3: Guardar/Actualizar (Seguro)
  guardar(): void {
    if (!this.nuevoCertificado.alumnoNombre || !this.nuevoCertificado.codigoVerificacion) return;

    if (this.esEdicion && this.nuevoCertificado.id !== undefined) {
      this.certificadoService.actualizar(this.nuevoCertificado.id, this.nuevoCertificado).subscribe({
        next: () => {
          console.log('Certificado actualizado en la BD');
          this.cargarCertificados();
          this.cancelarEdicion();
        },
        error: (err) => alert('Error al actualizar. Verifique consola.')
      });
    } else {
      this.certificadoService.crear(this.nuevoCertificado).subscribe({
        next: () => {
          console.log('Certificado creado en la BD');
          this.cargarCertificados();
          this.cancelarEdicion();
        },
        error: (err) => alert('Error al crear. El código podría estar duplicado.')
      });
    }
  }

  // MÓDULO 4: Preparar Edición
  seleccionarParaEditar(cert: Certificado): void {
    if (cert.id) {
      this.certificadoService.buscarPorId(cert.id).subscribe({
        next: (dataBD) => {
          this.nuevoCertificado = { ...dataBD };
          this.esEdicion = true;
        }
      });
    }
  }

  // MÓDULO 5: Borrar (Seguro)
  borrar(id: number | undefined): void {
    if (id === undefined) return;
    if (confirm('¿Seguro que deseas eliminar este certificado definitivamente de MySQL?')) {
      this.certificadoService.eliminar(id).subscribe({
        next: (msg) => {
          this.listaCertificados = this.listaCertificados.filter(c => c.id !== id);
          console.log(msg);
        },
        error: (err) => alert('Error al eliminar. Verifique consola.')
      });
    }
  }

  cancelarEdicion(): void {
    this.nuevoCertificado = { alumnoNombre: '', codigoVerificacion: '', notaFinal: 0, idEmpresa: 1, idCurso: 1 };
    this.esEdicion = false;
  }
}