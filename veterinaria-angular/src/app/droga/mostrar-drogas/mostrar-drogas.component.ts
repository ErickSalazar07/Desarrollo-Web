import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Droga } from 'src/app/modelo/droga';
import { DrogaService } from 'src/app/servicio/droga.service';

@Component({
  selector: 'app-mostrar-drogas',
  templateUrl: './mostrar-drogas.component.html',
  styleUrls: ['./mostrar-drogas.component.css']
})
export class MostrarDrogasComponent implements OnInit {
  drogas: Droga[] = [];
  terminoBusqueda: string = '';
  filtroSeleccionado: string = 'nombre';
  
  constructor(private http: HttpClient, private router: Router, private service: DrogaService) {}


  ngOnInit(): void {
    this.service.findAll().subscribe(drogas => {
      this.drogas = drogas;
    });
  }
  
/** 
  mostrarCliente(cliente: Cliente) {
    this.router.navigate(['/cliente/mostrar-cliente', cliente.id]);
  }

  eliminarCliente(cliente: Cliente) {
    console.warn("Entro a eliminar cliente");
    this.service.deleteById(cliente.id).subscribe(() => {
    this.service.findAll().subscribe(clientes => {
        this.clientes = clientes;
      })
    });
  }
  */
  get drogasFiltrados() {
    if (!this.terminoBusqueda) return this.drogas;
  
    return this.drogas.filter(droga => {
      const valor = this.terminoBusqueda.toLowerCase();
  
      switch (this.filtroSeleccionado) {
        case 'nombre':
          return droga.nombre.toLowerCase().includes(valor);
          case 'pVentaMayor':
            return droga.precioVenta > Number(valor);
          case 'pVentaMenor':
            return droga.precioVenta < Number(valor);
        default:
          return true;
      }
    });
  }
  
}  
