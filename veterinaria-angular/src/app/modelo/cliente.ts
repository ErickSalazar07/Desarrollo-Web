import { Mascota } from "./mascota";

export interface Cliente {
  id: number;
  cedula: string;
  nombre: string;
  correo: string;
  celular: string;
  mascotas?: Mascota[];
}