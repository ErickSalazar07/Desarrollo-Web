import { Droga } from "./droga";
import { Mascota } from "./mascota";
import { Veterinario } from "./veterinario";

export interface Tratamiento {
  id: number;
  nombreTratamiento: string;
  fecha: Date;
  drogaAsignada: Droga;
  mascota: Mascota;
  veterinario: Veterinario;
}
