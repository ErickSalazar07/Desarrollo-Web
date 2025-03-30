import { Mascota } from "./mascota";

export interface Tratamiento {
  id: number;
  nombreTratamiento: string;
  fecha: Date;
  mascota: Mascota;
  cedulaVeterinario: string;
}
