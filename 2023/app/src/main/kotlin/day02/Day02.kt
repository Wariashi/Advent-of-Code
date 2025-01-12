package day02

import java.net.URL

class Day02 {
    fun getResult1(puzzleInput: URL): Int {
        val lines = puzzleInput.readText().lines()

        var sumOfValidGameIds = 0
        for (line in lines) {
            if (line.isNotEmpty()) {
                val game = getGame(line)
                if (game.isValid(12, 13, 14)) {
                    sumOfValidGameIds += game.id
                }
            }
        }

        return sumOfValidGameIds
    }

    fun getResult2(puzzleInput: URL): Int {
        val lines = puzzleInput.readText().lines()

        var sumOfPowers = 0
        for (line in lines) {
            if (line.isNotEmpty()) {
                val game = getGame(line)
                sumOfPowers += game.getPower()
            }
        }

        return sumOfPowers
    }

    private fun getGame(line: String): Game {
        val parts = line.split(": ")
        val gameId = parts[0].substring(5).toInt()
        val roundDescriptions = parts[1].split("; ")

        val rounds = mutableListOf<Round>()
        for (roundDescription in roundDescriptions) {
            val round = getRound(roundDescription)
            rounds.add(round)
        }

        return Game(gameId, rounds)
    }

    private fun getRound(description: String): Round {
        var red = 0
        var green = 0
        var blue = 0

        val parts = description.split(", ")
        for (part in parts) {
            when {
                part.endsWith(" red") -> {
                    red = part.substringBefore(" ").toInt()
                }

                part.endsWith(" green") -> {
                    green = part.substringBefore(" ").toInt()
                }

                part.endsWith(" blue") -> {
                    blue = part.substringBefore(" ").toInt()
                }
            }
        }

        return Round(red, green, blue)
    }
}
