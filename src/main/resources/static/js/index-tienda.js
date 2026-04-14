document.addEventListener('DOMContentLoaded', () => {
    let carrito = JSON.parse(localStorage.getItem('carrito')) || [];
    
    const actualizarContador = () => {
        const btnFinalizar = document.getElementById('btn-finalizar');
        const countFinal = document.getElementById('count-final');
        if (btnFinalizar && countFinal) {
            btnFinalizar.style.display = carrito.length > 0 ? 'block' : 'none';
            countFinal.innerText = carrito.length;
        }
    };

    document.querySelectorAll('.btn-add-cart').forEach((boton) => {
        boton.addEventListener('click', (e) => {
            const cardBody = e.target.closest('.card-body');
            
            const nombreElemento = cardBody.querySelector('h5.card-title');
            const precioElemento = cardBody.querySelector('p.text-primary');

            if (nombreElemento && precioElemento) {
                const nombreZapa = nombreElemento.innerText.trim();
                const precioTexto = precioElemento.innerText;
                const precioNum = parseFloat(precioTexto.replace(/[^\d.,]/g, '').replace(',', '.'));

                // Guardamos el objeto con el nombre real y el precio 
				
                carrito.push({ nombre: nombreZapa, precio: precioNum });
                localStorage.setItem('carrito', JSON.stringify(carrito));

            
                boton.innerText = "¡AÑADIDO!";
                boton.className = "btn btn-success w-100";
                setTimeout(() => {
                    boton.innerText = "AÑADIR A LA CESTA";
                    boton.className = "btn btn-dark w-100";
                }, 700);

                actualizarContador();
            }
        });
    });

    actualizarContador();
});