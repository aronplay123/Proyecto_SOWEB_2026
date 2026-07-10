export interface Certificado {
    id?: number;
    alumnoNombre: string;
    codigoVerificacion: string;
    notaFinal: number;
    
    // IDs foráneos requeridos para GUARDAR o ACTUALIZAR en la base de datos
    idEmpresa?: number;
    idCurso?: number;
    
    // Nombres claros obtenidos desde el JOIN (Solo lectura)
    razonSocial?: string;
    nombreCurso?: string;
}