let indice = 0;
const totalImagenes = 3; // Número de imágenes
const anchoImagen = 500; // Nuevo ancho de imagen

function moverCarrusel() {
    const carrusel = document.querySelector(".carrusel");
    indice = (indice + 1) % totalImagenes; // Cambia entre 0, 1 y 2
    carrusel.style.transform = `translateX(${-indice * anchoImagen}px)`;
}

setInterval(moverCarrusel, 3000); // Cambia cada 3 segundos
