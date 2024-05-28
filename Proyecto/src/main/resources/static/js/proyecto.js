document.addEventListener('DOMContentLoaded', function() {
    // Evento para detectar cambios en el campo de búsqueda
    document.addEventListener('keyup', e => {
        if (e.target.matches('#srch')) {
            const searchValue = e.target.value.toLowerCase();
            const cardsContainer = document.querySelector('.cards');

            document.querySelectorAll('.col-md-4').forEach(planta => {
                const plantaText = planta.textContent.toLowerCase();

                if (plantaText.includes(searchValue)) {
                    planta.classList.remove('filtro');
                    cardsContainer.classList.add('search-mode');
                } else {
                    planta.classList.add('filtro');
                    if (!document.querySelectorAll('.col-md-4:not(.filtro)').length) {
                        cardsContainer.classList.remove('search-mode');
                    }
                }
            });
        }
    });

    // Evento para mostrar u ocultar el menú en dispositivos móviles
    const menuIcon = document.getElementById("fabars");
    const menuList = document.getElementById("menu-list");

    menuIcon.addEventListener("click", function() {
        menuList.classList.toggle("show");
    });

    // Función para gestionar los eventos de los campos de entrada
    document.querySelectorAll('.form input, .form textarea').forEach(function(element) {
        element.addEventListener('keyup', function(e) {
            handleInputEvent(e, this);
        });
        element.addEventListener('blur', function(e) {
            handleInputEvent(e, this);
        });
        element.addEventListener('focus', function(e) {
            handleInputEvent(e, this);
        });
    });

    // Función para gestionar los eventos de los campos de entrada
    function handleInputEvent(e, element) {
        var label = element.previousElementSibling;
        if (e.type === 'keyup') {
            if (element.value === '') {
                label.classList.remove('active', 'highlight');
            } else {
                label.classList.add('active', 'highlight');
            }
        } else if (e.type === 'blur') {
            if (element.value === '') {
                label.classList.remove('active', 'highlight');
            } else {
                label.classList.remove('highlight');
            }
        } else if (e.type === 'focus') {
            if (element.value === '') {
                label.classList.remove('highlight');
            } else {
                label.classList.add('highlight');
            }
        }
    }

    // Función para mostrar los detalles de una fila en un modal
    function detalles(botondetalles) {
        var fila = botondetalles.parentElement.parentElement.children;

        document.getElementById("genero").value = fila[0].textContent;
        document.getElementById("nombre").value = fila[1].textContent;
        document.getElementById("Nombre completo").value = fila[2].textContent;
        document.getElementById("pais").value = fila[3].textContent;
        document.getElementById("email").value = fila[4].textContent;

        $('#dialogoDetalles').modal({
            keyboard: false
        });
        $('#dialogoDetalles').modal('show');
    }

    // Función para eliminar un registro
    function eliminarRegistro(botonEliminar) {
        var fila = botonEliminar.parentElement.parentElement;
        $('#dialogoEliminar').modal('show');

        // Manejar el clic en el botón de confirmación
        $('#btnConfirmarEliminar').on('click', function() {
            fila.remove(); // Eliminar la fila de la tabla
            $('#dialogoEliminar').modal('hide'); // Ocultar el modal
            // Aquí podrías agregar lógica adicional para eliminar los datos de manera permanente
        });
    }
});
