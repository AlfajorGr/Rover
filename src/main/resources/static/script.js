createMap();

async function createMap(){
    //Obtener info del rover
    refreshRover();

    //Obtener info de los obstaulo
    let obstacleResponse = await fetch('/api/obstacle/', {
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
    let roverResponse = await fetch('/api/rover/', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    });
    let roverJson = await roverResponse.json();
    moveRover(roverJson.x, roverJson.y);
}

function moveRover(x, y){
    document.getElementById("rover").style.left = (x * 100) + 'px';
    document.getElementById("rover").style.top = (y * 100) + 'px';
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

function clickBtnRotateLeft() {
    sendCommand("L")
}

function clickBtnRotateRight() {
    sendCommand("R");
}

async function moveForward() {
    sendCommand("F");

}

async function sendCommand(command){
    let requestBody = {
        "commands": [command]
    };

    await fetch('/api/rover/command/', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(requestBody)
    });

    await refreshRover();
}

function moveBack() {
    sendCommand("B");
}