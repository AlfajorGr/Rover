createMap();

async function createMap(){
    //Obtener info del rover
    refreshRover();

    //Obtener info de los obstaulo
    let obstacleResponse = await fetch('http://localhost:8080/api/obstacle', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    });
    let obstacleJson = await obstacleResponse.json();
    obstacleJson.forEach(obstacleJson => {
        createRock(obstacleJson.x, obstacleJson.y)
    });
}

async function refreshRover(){
    let roverResponse = await fetch('http://localhost:8080/api/rover', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    });
    let roverJson = await roverResponse.json();
    console.log("Dirección del rover:", roverJson.direction);  // Verifica la dirección
    moveRover(roverJson.x, roverJson.y, roverJson.direction);  // Actualiza la posición y dirección
}


function moveRover(x, y, direction){
    // Aplica rotación del rover en la interfaz dependiendo de la dirección
    let roverElement = document.getElementById("rover");

    switch(direction) {
        case "NORTH":
            roverElement.style.transform = "rotate(0deg)";  // Norte (sin rotación)
            break;
        case "EAST":
            roverElement.style.transform = "rotate(90deg)";  // Este
            break;
        case "SOUTH":
            roverElement.style.transform = "rotate(180deg)";  // Sur
            break;
        case "WEST":
            roverElement.style.transform = "rotate(270deg)";  // Oeste
            break;
    }

    // Mueve al rover según la nueva posición
    roverElement.style.left = (x * 100) + 'px';
    roverElement.style.top = (y * 100) + 'px';
}




function createRock(x, y){
    var rock = document.createElement("img");
    rock.setAttribute("src", "img/rock.png");
    rock.setAttribute("class", "rock");
    var container = document.getElementById("container");
    container.appendChild(rock);

    rock.style.left = (x * 100) + 'px';
    rock.style.top = (y * 100) + 'px';
}

var posX = 0;
var posY = 0;

function moveUp() {
    sendCommand("F");  // Avanza hacia arriba si está mirando hacia el norte
}

// Función de mover hacia atrás
function moveDown() {
    sendCommand("B");  // Retrocede hacia abajo si está mirando hacia el sur
}

// Función de girar a la izquierda
function moveLeft() {
    sendCommand("L");  // Gira hacia la izquierda
}

// Función de girar a la derecha
function moveRight() {
    sendCommand("R");  // Gira hacia la derecha
}

async function sendCommand(command){
    let requestBody = {
        "commands": [command]
    };

    await fetch('http://localhost:8080/api/rover/command', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(requestBody)
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Error en la respuesta del servidor');
        }
        return response.json();  // Procesamos la respuesta como JSON
    })
    .then(data => {
        console.log("Respuesta del servidor:", data);
        // Actualizamos la interfaz con la nueva posición
        moveRover(data.x, data.y);
    })
    .catch(error => {
        console.error('Error:', error);
    });

    await refreshRover();  // Refresca la posición del rover después de cada comando
}

