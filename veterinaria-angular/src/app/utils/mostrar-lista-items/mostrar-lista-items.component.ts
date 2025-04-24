import { Component, Input } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { TratamientoService } from 'src/app/servicio/tratamiento.service';

@Component({
  selector: 'app-mostrar-lista-items',
  templateUrl: './mostrar-lista-items.component.html',
  styleUrls: ['./mostrar-lista-items.component.css']
})
export class MostrarListaItemsComponent {

  lista:any[] = [];
  tipoObjeto:string | null = null;
  mensaje:string|null = null;

  constructor(
    private route:ActivatedRoute,
    private tratamientoServicio:TratamientoService
  ) { }

  ngOnInit() {
    this.tipoObjeto = this.route.snapshot.paramMap.get('tipo');
    this.mensaje = this.route.snapshot.paramMap.get('mensaje');
    this.obtenerLista();
  }

  obtenerLista() {
    switch(this.tipoObjeto) {

      case 'tratamiento': this.tratamientoServicio.obtenerTop3TratamientosMasUnidadVendida()
      .subscribe(l => {this.lista = l;})
      break;

      default:
        this.lista = [];
    }
  }
}
