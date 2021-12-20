package game

class Table(val args:Array<String>) {
    private fun fillString(str:String, len:Int):String{
        val sb = StringBuilder()
        for (i in 0 until len) {
            sb.append(' ')
        }
        return (str + sb.substring(str.length))
    }
    fun getWinValue(playerValue:Int, computerValue:Int):Int{
        val numOfPart = (args.size-1)/2
        val difference = playerValue - computerValue
        if(difference==0) return -1
        val manWin = difference in -numOfPart..  -1 || difference > numOfPart //right win left
        val winValue = if (manWin) playerValue else computerValue
        return winValue
    }
    fun printWinTable(){
        var len=0
        args.forEach {arg -> len=if(arg.length>len) arg.length else len }
        len+=5
        print(fillString(" ", len))
        for (arg in args) print(fillString(arg, len))
        print("\n")
        for (i in args.indices){
            print(fillString("${args[i]}->", len))
            for (j in args.indices){
                when(getWinValue(i, j)){
                    i -> print(fillString("W",len))
                    j -> print(fillString("L",len))
                    -1 -> print(fillString("-",len))
                }
            }
            print("\n")
        }
    }
}