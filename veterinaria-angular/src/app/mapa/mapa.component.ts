import { Component, AfterViewInit } from '@angular/core';

declare const google: any;

@Component({
  selector: 'app-mapa',
  templateUrl: './mapa.component.html',
  styleUrls: ['./mapa.component.css']
})
export class MapaComponent implements AfterViewInit {
  map: any;
  directionsService!: google.maps.DirectionsService;
  directionsRenderer!: google.maps.DirectionsRenderer;

  // Locales veterinarios simulados (puedes cambiarlos por datos reales)
  locales = [
    { nombre: 'Veterinaria Centro', lat: 4.60971, lng: -74.08175 },
    { nombre: 'Veterinaria Norte', lat: 4.67615, lng: -74.04979 },
    { nombre: 'Veterinaria Sur', lat: 4.56321, lng: -74.11375 }
  ];

  ngAfterViewInit(): void {
    // Cargar el script si no está cargado
    if (!window['google']) {
      const script = document.createElement('script');
      script.src = 'https://maps.googleapis.com/maps/api/js?key=AIzaSyDhR4iCFnpjMe02_LxqApZAVvVNmhFrBIY&libraries=places';
      script.async = true;
      script.defer = true;
      script.onload = () => this.initMap();
      document.head.appendChild(script);
    } else {
      this.initMap();
    }
  }

  initMap(): void {
    // Bogotá como centro inicial
    const center = new google.maps.LatLng(4.6482837, -74.2478931);
    this.map = new google.maps.Map(document.getElementById('map'), {
      center,
      zoom: 12
    });

    // Inicializar servicios de ruta
    this.directionsService = new google.maps.DirectionsService();
    this.directionsRenderer = new google.maps.DirectionsRenderer();
    this.directionsRenderer.setMap(this.map);

    // Marcar locales
    this.locales.forEach(local => {
      new google.maps.Marker({
        position: { lat: local.lat, lng: local.lng },
        map: this.map,
        title: local.nombre
      });
    });

    // Obtener ubicación del cliente
    if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition(
        position => {
          const ubicacionCliente = {
            lat: position.coords.latitude,
            lng: position.coords.longitude
          };

          new google.maps.Marker({
            position: ubicacionCliente,
            map: this.map,
            icon: 'http://maps.google.com/mapfiles/ms/icons/blue-dot.png',
            title: 'Tú estás aquí'
          });

          // Buscar local más cercano
          const localMasCercano = this.encontrarLocalMasCercano(ubicacionCliente);
          this.trazarRuta(ubicacionCliente, localMasCercano);
        },
        error => {
          console.error('Error al obtener la ubicación:', error);
        }
      );
    } else {
      alert('Tu navegador no soporta geolocalización');
    }
  }

  encontrarLocalMasCercano(ubicacionCliente: { lat: number; lng: number }) {
    let distanciaMin = Infinity;
    let localCercano = this.locales[0];

    this.locales.forEach(local => {
      const distancia = this.calcularDistancia(ubicacionCliente, local);
      if (distancia < distanciaMin) {
        distanciaMin = distancia;
        localCercano = local;
      }
    });

    return localCercano;
  }

  calcularDistancia(a: { lat: number; lng: number }, b: { lat: number; lng: number }): number {
    const rad = (x: number) => (x * Math.PI) / 180;
    const R = 6371; // Radio de la tierra en km
    const dLat = rad(b.lat - a.lat);
    const dLng = rad(b.lng - a.lng);
    const aa =
      Math.sin(dLat / 2) * Math.sin(dLat / 2) +
      Math.cos(rad(a.lat)) * Math.cos(rad(b.lat)) *
      Math.sin(dLng / 2) * Math.sin(dLng / 2);
    const c = 2 * Math.atan2(Math.sqrt(aa), Math.sqrt(1 - aa));
    return R * c;
  }

  trazarRuta(origen: { lat: number; lng: number }, destino: { lat: number; lng: number }): void {
    const request: google.maps.DirectionsRequest = {
      origin: origen,
      destination: destino,
      travelMode: google.maps.TravelMode.DRIVING
    };

    this.directionsService.route(request, (result, status) => {
      if (status === 'OK') {
        this.directionsRenderer.setDirections(result);
      } else {
        console.error('Error al trazar la ruta:', status);
      }
    });
  }
}
