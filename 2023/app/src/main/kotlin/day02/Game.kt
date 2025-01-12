package day02

class Game(val id: Int, private val rounds: List<Round>) {
    fun isValid(maxRed: Int, maxGreen: Int, maxBlue: Int): Boolean {
        for (round in rounds) {
            if (maxRed < round.red || maxGreen < round.green || maxBlue < round.blue) {
                return false
            }
        }
        return true
    }
}
