import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-mostrar-num-items',
  templateUrl: './mostrar-num-items.component.html',
  styleUrls: ['./mostrar-num-items.component.css']
})
export class MostrarNumItemsComponent {

  nombreItem:string | null = null;
  cantidad!:number;

  constructor(
    private route: ActivatedRoute
  ) { }

  ngOnInit() {
    this.nombreItem = this.route.snapshot.paramMap.get('nombre-item');
    let cantidadTxt = this.route.snapshot.paramMap.get('cantidad');
    this.cantidad = cantidadTxt === null ? 0 : Number.parseInt(cantidadTxt);
  }
}
