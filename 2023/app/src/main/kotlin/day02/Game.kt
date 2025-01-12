package day02

class Game(val id: Int, private val rounds: List<Round>) {
    fun getPower(): Int {
        var red = 0
        var green = 0
        var blue = 0

        for (round in rounds) {
            if (red < round.red) {
                red = round.red
            }
            if (green < round.green) {
                green = round.green
            }
            if (blue < round.blue) {
                blue = round.blue
            }
        }

        return red * green * blue
    }

    fun isValid(maxRed: Int, maxGreen: Int, maxBlue: Int): Boolean {
        for (round in rounds) {
            if (maxRed < round.red || maxGreen < round.green || maxBlue < round.blue) {
                return false
            }
        }
        return true
    }
}
