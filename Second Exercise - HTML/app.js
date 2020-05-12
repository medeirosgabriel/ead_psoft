var states, indexes = [], 
    answer = document.querySelector("#answer"),
    state = document.querySelector("#state"),
    score = document.querySelector("#score"),
    result = document.querySelector("#result"),
    confirm = document.querySelector("#confirm")

answer.onkeypress = function (e) {
    var key = e.which;
    if (event.keyCode == 13 || event.which == 13) {
        confirm.click();
    }
};

function startGame() {
    fetch("https://raw.githubusercontent.com/EduardoNunes5/PSOFT/master/PSOFT-GuessTheCapital/estados.json")
	.then(resolve => resolve.json())
	.then(statesJson => {
		states = statesJson;
        chooseState()
	})
}

let index;
function chooseState() {
    if (parseInt(score.innerText) == 5) {
        result.innerText = "You win the game! Congratulations! Restarting in 2 seconds..."
        setTimeout(reset_game, 2000)
    }
    index = Math.trunc((Math.random() * states.estados.length));
    state.innerText = states.estados[index]['nome'] + '?'
}

function reset_game() {
    score.innerText = 0
}

function checkAnswer() {
    if (answer.value == states.estados[index]['capital']) {
        score.innerText = parseInt(score.innerText) + 1
        result.innerText = "Correct!"
    }else {
        result.innerText = "Incorrect! Try Again!"
    }
    clear()
    chooseState()
}

function clear_aux() {
    answer.value = ""
    result.innerText = ""
}

function clear() {
    setTimeout(clear_aux, 500)
}

startGame()

