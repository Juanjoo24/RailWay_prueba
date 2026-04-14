document.addEventListener('DOMContentLoaded', () => {
    const listaCarrito = document.getElementById('lista-carrito');
    const totalElement = document.getElementById('total');
    const formRegistro = document.getElementById('form-registro');
    
    // Obtenemos los productos guardados
    let carrito = JSON.parse(localStorage.getItem('carrito')) || [];

    const renderCarrito = () => {
        let suma = 0;
        if (!listaCarrito) return;
        listaCarrito.innerHTML = '';

        carrito.forEach((prod, index) => {
            const p = (prod && prod.precio) ? parseFloat(prod.precio) : 0;
            suma += p;
            listaCarrito.innerHTML += `
                <tr>
                    <td class="fw-bold py-3">${prod.nombre || 'Zapatilla'}</td>
                    <td class="text-end fw-bold">${p.toFixed(2)}€</td>
                    <td class="text-end">
                        <button class="btn btn-danger btn-sm" onclick="eliminar(${index})">X</button>
                    </td>
                </tr>`;
        });

        if(totalElement) totalElement.innerText = suma.toFixed(2) + "€";
    };

    window.eliminar = (index) => {
        carrito.splice(index, 1);
        localStorage.setItem('carrito', JSON.stringify(carrito));
        renderCarrito();
    };

    // PROCESAMOSA  LA COMPRA 
	
    formRegistro.onsubmit = async (e) => {
        e.preventDefault();

        if (carrito.length === 0) {
            alert("Tu carrito está vacío.");
            return;
        }

        const datosPedido = {
            direccionEntrega: document.getElementById('direccion-input').value,
            total: parseFloat(totalElement.innerText.replace('€', '')),
            estado: "PAGADO"
        };

        try {
            const response = await fetch('/api/pedidos/guardar', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(datosPedido)
            });

            if (response.ok) {
                alert("¡Pago con tarjeta realizado con éxito!");
                localStorage.removeItem('carrito');
                window.location.href = '/bienvenida'; 
            } else {
                alert("Error al procesar el pago. Revisa tu sesión.");
            }
        } catch (error) {
            console.error("Error en fetch:", error);
            alert("No se pudo conectar con el servidor.");
        }
    };

    renderCarrito();
});