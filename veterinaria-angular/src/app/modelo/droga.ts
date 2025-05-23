import { Tratamiento } from "./tratamiento";

export interface Droga {
  id: number;
  nombre: string;
  precioCompra: number;
  precioVenta: number;
  unidadDisponible: number;
  unidadVendida: number;
  tratamientos?: Tratamiento[];
}
