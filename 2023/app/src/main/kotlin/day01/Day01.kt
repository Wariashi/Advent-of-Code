package day01

import java.net.URL

class Day01 {
    fun getResult1(puzzleInput: URL): Int {
        val lines = getLines(puzzleInput)

        var sumOfCalibrationValues = 0
        for (line in lines) {
            if (line.isNotEmpty()) {
                sumOfCalibrationValues += getCalibrationValue(line)
            }
        }

        return sumOfCalibrationValues
    }

    private fun getCalibrationValue(line: String): Int {
        var firstDigit: Char? = null
        var lastDigit: Char? = null
        for (character in line) {
            if (character.isDigit()) {
                if (firstDigit == null) {
                    firstDigit = character
                }
                lastDigit = character
            }
        }
        val calibrationValue = "$firstDigit$lastDigit"

        return calibrationValue.toInt()
    }

    private fun getLines(url: URL): List<String> {
        val input = url.readText()
        return input.split("\n")
    }
}
