/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

// Variables del juego
var palabra = '';
var letras = [];
var vidas = 5;
var tiempoRestante = 230;
var estaJugando = false;
var temporizador;
var estaPausado = false;
var mensaje = "ESTAS LISTRO PARA ADIVINAR LA PARABRA?";
var pistaActual = "";

// Función para obtener palabra de la base de datos
function obtenerPalabra() {
    // Hacer petición al servidor
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "Controlador?menu=Palabras&accion=ObtenerPalabra", true);
    
    xhr.onreadystatechange = function() {
        if(xhr.readyState == 4 && xhr.status == 200) {
            var respuesta = JSON.parse(xhr.responseText);
            palabra = respuesta.palabra;
            pistaActual = respuesta.pista;
            
            // Poner guiones
            letras = [];
            for(var i = 0; i < palabra.length; i++) {
                letras[i] = "_";
            }
            
            ponerPalabra();
            document.querySelector('.hint-text').textContent = pistaActual;
            
            // Iniciar juego
            estaJugando = true;
            estaPausado = false;
            empezarTiempo();
            
            // Activar teclado
            var botonesTeclado = document.querySelectorAll('.key');
            for(var i = 0; i < botonesTeclado.length; i++) {
                botonesTeclado[i].disabled = false;
            }
        }
    };
    
    xhr.send();
}

// Empieza el juego
function empezarJuego() {
    // Reiniciar todo
    vidas = 5;
    tiempoRestante = 230;
    estaJugando = false;
    estaPausado = true;
    clearInterval(temporizador);
    
    // Mostrar mensaje inicial
    document.querySelector('.hint-text').textContent = mensaje;
    document.querySelector('.hangman-display').textContent = "Vidas: 5";
    
    // Limpiar palabra
    palabra = '';
    letras = [];
    ponerPalabra();
    
    // Apagar teclado
    var botonesTeclado = document.querySelectorAll('.key');
    for(var i = 0; i < botonesTeclado.length; i++) {
        botonesTeclado[i].disabled = true;
    }
    
    // Poner reloj a 0
    actualizarTiempo();
}

// Poner el tiempo en pantalla
function actualizarTiempo() {
    var mins = Math.floor(tiempoRestante / 60);
    var segs = tiempoRestante % 60;
    
    if(mins < 10) mins = "0" + mins;
    if(segs < 10) segs = "0" + segs;
    
    document.querySelector('.time').textContent = mins + ":" + segs;
}

// Hacer funcionar el reloj
function empezarTiempo() {
    clearInterval(temporizador);
    temporizador = setInterval(function() {
        if(!estaPausado) {
            tiempoRestante--;
            actualizarTiempo();
            
            if(tiempoRestante <= 0) {
                terminar(false);
            }
        }
    }, 1000);
}

// Revisar si la letra está
function buscarLetra(letra) {
    if(!estaJugando || estaPausado) return;
    
    var encontro = false;
    
    // Buscar la letra en la palabra
    for(var i = 0; i < palabra.length; i++) {
        if(palabra[i] == letra) {
            letras[i] = letra;
            encontro = true;
        }
    }
    
    // Si no encontró, quitar vida
    if(!encontro) {
        vidas = vidas - 1;
        document.querySelector('.hangman-display').textContent = "Te quedan: " + vidas;
    }
    
    // Actualizar palabra en pantalla
    ponerPalabra();
    
    // Ver si ganó o perdió
    revisarSiGano();
}

// Poner la palabra en pantalla
function ponerPalabra() {
    var dondeVaLaPalabra = document.querySelector('.word-display');
    dondeVaLaPalabra.innerHTML = '';
    
    for(var i = 0; i < letras.length; i++) {
        var cajita = document.createElement('div');
        cajita.className = 'letter-slot';
        cajita.textContent = letras[i];
        dondeVaLaPalabra.appendChild(cajita);
    }
}

// Ver si ganó o perdió
function revisarSiGano() {
    var gano = true;
    
    // Ver si completó la palabra
    for(var i = 0; i < palabra.length; i++) {
        if(letras[i] != palabra[i]) {
            gano = false;
            break;
        }
    }
    
    if(gano) {
        terminar(true);
        return;
    }
    
    // Ver si perdió
    if(vidas <= 0) {
        terminar(false);
    }
}

// Terminar el juego
function terminar(gano) {
    estaJugando = false;
    estaPausado = true;
    clearInterval(temporizador);
    
    // Apagar teclado
    var botonesTeclado = document.querySelectorAll('.key');
    for(var i = 0; i < botonesTeclado.length; i++) {
        botonesTeclado[i].disabled = true;
    }
    
    // Decir si ganó o perdió
    if(gano) {
        alert('¡Muy bien! ¡Ganaste!');
    } else {
        alert('¡Oh no! Perdiste... La palabra era: ' + palabra);
    }
    
    // Poner mensaje inicial
    setTimeout(function() {
        document.querySelector('.hint-text').textContent = mensaje;
    }, 1000);
}

// Pausar el juego
function pausar() {
    if(estaJugando) {
        estaPausado = !estaPausado;
        var botonPausar = document.querySelector('.game-button');
        
        if(estaPausado) {
            // Pausar juego
            botonPausar.textContent = "CONTINUAR";
            var botonesTeclado = document.querySelectorAll('.key');
            for(var i = 0; i < botonesTeclado.length; i++) {
                botonesTeclado[i].disabled = true;
            }
        } else {
            // Continuar juego
            botonPausar.textContent = "PAUSAR";
            var botonesTeclado = document.querySelectorAll('.key');
            for(var i = 0; i < botonesTeclado.length; i++) {
                botonesTeclado[i].disabled = false;
            }
        }
    }
}

// Volver a empezar
function reiniciar() {
    empezarJuego();
    document.querySelector('.hint-text').textContent = mensaje;
}

// Salir del juego
function salir() {
    var quiereSalir = confirm("¿Seguro que quieres salir?");
    if(quiereSalir) {
        window.location.href = "Controlador?menu=Index&accion=Salir";
    }
}

// Cuando carga la página
window.onload = function() {
    // Configurar mensaje inicial
    document.querySelector('.hint-text').textContent = mensaje;
    
    // Hacer que funcionen las letras
    var botonesTeclado = document.querySelectorAll('.key');
    for(var i = 0; i < botonesTeclado.length; i++) {
        botonesTeclado[i].onclick = function() {
            if(!estaPausado && estaJugando) {
                buscarLetra(this.textContent);
                this.disabled = true;
            }
        };
    }

    // Hacer que funcionen los botones
    var botonesJuego = document.querySelectorAll('.game-button');
    for(var i = 0; i < botonesJuego.length; i++) {
        botonesJuego[i].onclick = function() {
            if(this.textContent == 'COMENZAR') {
                obtenerPalabra();
            } else if(this.textContent == 'PAUSAR' || this.textContent == 'CONTINUAR') {
                pausar();
            } else if(this.textContent == 'REINICIAR') {
                empezarJuego();
            } else if(this.textContent == 'SALIR') {
                salir();
            }
        };
    }
    
    // Inicializar juego
    empezarJuego();
};

