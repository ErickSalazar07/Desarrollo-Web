import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-mostrar-valor-dinero',
  templateUrl: './mostrar-valor-dinero.component.html',
  styleUrls: ['./mostrar-valor-dinero.component.css']
})
export class MostrarValorDineroComponent {
  txtMostrar:string | null = null;
  dinero:number = 0;

  constructor(
    private route:ActivatedRoute
  ) { }

  ngOnInit() {
    this.txtMostrar = this.route.snapshot.paramMap.get('txt-msg');
    const dineroTxt = this.route.snapshot.paramMap.get('dinero');
    this.dinero = dineroTxt === null ? 0 : Number.parseFloat(dineroTxt);
  }
}
