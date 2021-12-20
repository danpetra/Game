package game

class Game (private val args:Array<String> ){
    init{
        var rightInput = args.distinct().size == args.size && args.size%2==1 && args.size >= 3
        require(rightInput) {"Wrong input: 3 or more odd unique parameters expected"}
    }
    var computerValue = -1
    var playerValue = -1
    var gameExitFl = 0
    private val table = Table(args)
    private val computerStep = Step(args)
    fun makeComputerStep(){
        computerValue = computerStep.makeStep()
    }
    fun printHmacKeyWithStep(){
        println("Hmac: ${computerStep.getHmac(args[computerValue]+computerStep.getKey())}")
    }
    fun printHmacKey(){
        println("Hmac key: ${computerStep.getHmac(computerStep.getKey())}")
    }
    private fun printLegend(){
        args.forEachIndexed{index, char -> println("${index+1} - $char")}
        println("? - Help\n0 - Exit")
    }
    fun makePlayerStepFromInput(){
        playerValue = -1
        do {
            println("Available moves:")
            printLegend()
            println("Enter the move:")
            val line = readLine()
            if (line?.length==1) {
                when (line.get(0)) {
                    '?' -> table.printWinTable();
                    else -> {
                        try {
                            val lineToInt: Int = line.toInt() ?: -1
                            when (line.toInt()) {
                                0 -> {gameExitFl = 1; break;}
                                in 1..args.size -> playerValue = (lineToInt) - 1
                            }
                        } catch (e: Exception) {}
                    }
                }
            }
        }while(playerValue !in args.indices)
    }
    fun showSteps() {
        println("Player value: ${args[playerValue]}")
        println("Computer value: ${args[computerValue]}")
    }
    fun showWinner() {
        var winValue = table.getWinValue(computerValue, playerValue)
        if (winValue<0) println("Draw.")
        else {
            var loseValue = if (winValue == computerValue) playerValue else computerValue
            var playerWin = winValue == playerValue
            println("${args[winValue]} kills ${args[loseValue]}")
            println(if (playerWin) "You win!" else "You lose.")
        }
    }
}