<%-- 
    Document   : Principal
    Created on : 2/09/2025, 15:19:58
    Author     : Usuario
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Juego del Ahorcado</title>
    <!-- Corregimos las rutas absolutas desde la raíz del proyecto -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Styles/css2.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Styles/css.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Styles/login.css">
</head>
<body>
    <!-- Corregimos la ruta del video -->
    <video class="bg-video" autoplay muted loop playsinline>
        <source src="${pageContext.request.contextPath}/Images/fondo.mp4" type="video/mp4">
        Tu navegador no soporta videos HTML5.
    </video>
    <div class="game-container">
        <!-- ventana del ahorcado -->
        <div class="window ahorcado-window">
            <div class="window-header">
                <span class="window-title">AHORCADO</span>
                <div class="window-controls">
                    <span class="control minimize">−</span>
                    <span class="control maximize">□</span>
                    <span class="control close">×</span>
                </div>
            </div>
            <div class="window-content ahorcado-content">
                <div class="hangman-display">
                    <!-- aqui iria la imagen del ahorcado -->
                </div>
                <div class="word-display">
                    <div class="letter-slot">_</div>
                    <div class="letter-slot">_</div>
                    <div class="letter-slot">_</div>
                    <div class="letter-slot">_</div>
                    <div class="letter-slot">_</div>
                </div>
            </div>
            <div class="scrollbar vertical-scrollbar"></div>
        </div>

        <!-- contenedor para cronometro y pista -->
        <div class="timer-hint-container">
            <!-- cronOmetro -->
            <div class="window cronometro-window">
                <div class="window-header">
                    <span class="window-title">CRONÓMETRO</span>
                    <div class="window-controls">
                        <span class="control minimize">−</span>
                        <span class="control maximize">□</span>
                        <span class="control close">×</span>
                    </div>
                </div>
                <div class="window-content cronometro-content">
                    <div class="timer-display">
                        <span class="time">03:50</span>
                    </div>
                </div>
            </div>

            <!-- pista -->
            <div class="window pista-window">
                <div class="window-header">
                    <span class="window-title">PISTA</span>
                    <div class="window-controls">
                        <span class="control minimize">−</span>
                        <span class="control maximize">□</span>
                        <span class="control close">×</span>
                    </div>
                </div>
                <div class="window-content pista-content">
                    <p class="hint-text">ESTAS LISTRO PARA ADIVINAR LA PARABRA?</p>
                </div>
                <div class="scrollbar vertical-scrollbar"></div>
            </div>
        </div>

        <!-- ventana del teclado -->
        <div class="window teclado-window">
            <div class="window-header">
                <span class="window-title">TECLADO</span>
                <div class="window-controls">
                    <span class="control minimize">−</span>
                    <span class="control maximize">□</span>
                    <span class="control close">×</span>
                </div>
            </div>
            <div class="window-content teclado-content">
                <div class="keyboard">
                    <div class="keyboard-row">
                        <button class="key">Q</button>
                        <button class="key">W</button>
                        <button class="key">E</button>
                        <button class="key">R</button>
                        <button class="key">T</button>
                        <button class="key">Y</button>
                        <button class="key">U</button>
                        <button class="key">I</button>
                        <button class="key">O</button>
                        <button class="key">P</button>
                    </div>
                    <div class="keyboard-row">
                        <button class="key">A</button>
                        <button class="key">S</button>
                        <button class="key">D</button>
                        <button class="key">F</button>
                        <button class="key">G</button>
                        <button class="key">H</button>
                        <button class="key">J</button>
                        <button class="key">K</button>
                        <button class="key">L</button>
                    </div>
                    <div class="keyboard-row">
                        <button class="key">Z</button>
                        <button class="key">X</button>
                        <button class="key">C</button>
                        <button class="key">V</button>
                        <button class="key">B</button>
                        <button class="key">N</button>
                        <button class="key">M</button>
                    </div>
                </div>
            </div>
            <div class="scrollbar horizontal-scrollbar"></div>
        </div>
        <div class="control-buttons">
            <button class="game-button">PAUSAR</button>
            <button class="game-button">REINICIAR</button>
            <button class="game-button">SALIR</button>
            <button class="game-button primary">COMENZAR</button>
        </div>
    </div>
    <!-- Corregimos la ruta del script -->
    <script src="${pageContext.request.contextPath}/Scripts/js.js"></script>
</body>
</html>