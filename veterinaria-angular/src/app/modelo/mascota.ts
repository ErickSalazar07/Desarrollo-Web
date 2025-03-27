export interface Mascota {
  id: number;
  nombre: string;
  raza: string
  edad: number;
  peso: number;
  cedulaCliente: string;
  enfermedad?: string;
  foto: string;
  estadoActivo: boolean;
}
