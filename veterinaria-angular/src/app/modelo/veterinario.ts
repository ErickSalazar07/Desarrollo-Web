import { Tratamiento } from "./tratamiento";

export interface Veterinario {
  id: number;
  cedula: string;
  nombre: string;
  contrasena: string;
  especialidad: string;
  activo: boolean;
  foto: string;
  tratamientos?: Tratamiento[];
}
