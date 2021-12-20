import game.Game

fun playTheGame(args: Array<String>){
    val game = Game(args)
    println("Hello!")
    while (game.gameExitFl != 1) {
        game.makeComputerStep()
        game.printHmacKeyWithStep()
        game.makePlayerStepFromInput()
        if(game.gameExitFl == 1) break
        game.showSteps()
        game.showWinner()
        game.printHmacKey()
        println("\nNew game")
    }
    println("Goodbye!")
}

fun main(args: Array<String>) {
    try {
        playTheGame(args)
    }catch(e:Exception){println(e.message)}
}