import { Tratamiento } from "./tratamiento";
import { UserEntity } from "./UserEntity";

export interface Veterinario {
  id: number;
  cedula: string;
  nombre: string;
  contrasena: string;
  especialidad: string;
  activo: boolean;
  foto: string;
  user: UserEntity;
  tratamientos?: Tratamiento[];
}
