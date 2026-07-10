import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Certificado } from '../models/certificado';

@Injectable({
  providedIn: 'root'
})
export class CertificadoService {

  private apiUrl = 'http://localhost:8081/api/certificados'; // URL de tu Backend Spring Boot

  // Inyectamos las credenciales HTTP Basic Auth (admin:password123) codificadas en Base64
  private authHeaders = new HttpHeaders({
    'Authorization': 'Basic ' + btoa('admin:password123')
  });

  constructor(private http: HttpClient) { }

  // 1. GET - Público
  listar(): Observable<Certificado[]> {
    return this.http.get<Certificado[]>(this.apiUrl);
  }

  // 2. GET por ID - Público
  buscarPorId(id: number): Observable<Certificado> {
    return this.http.get<Certificado>(`${this.apiUrl}/${id}`);
  }

  // 3. POST - Seguro
  crear(certificado: Certificado): Observable<Certificado> {
    return this.http.post<Certificado>(this.apiUrl, certificado, { headers: this.authHeaders });
  }

  // 4. PUT - Seguro
  actualizar(id: number, certificado: Certificado): Observable<Certificado> {
    return this.http.put<Certificado>(`${this.apiUrl}/${id}`, certificado, { headers: this.authHeaders });
  }

  // 5. DELETE - Seguro
  eliminar(id: number): Observable<string> {
    return this.http.delete(`${this.apiUrl}/${id}`, { 
      headers: this.authHeaders, 
      responseType: 'text' 
    });
  }
}