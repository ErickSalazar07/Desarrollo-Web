import { Tratamiento } from "./tratamiento";

export interface Mascota {
  id: number;
  nombre: string;
  raza: string
  edad: number;
  peso: number;
  enfermedad?: string;
  foto: string;
  estadoActivo: boolean;
  cedulaCliente: string;
  tratamientos?: Tratamiento[];
}
