package day01

import java.net.URL

class Day01 {
    fun getResult1(puzzleInput: URL): Int {
        val lines = getLines(puzzleInput)

        var sumOfCalibrationValues = 0
        for (line in lines) {
            if (line.isNotEmpty()) {
                sumOfCalibrationValues += getCalibrationValue1(line)
            }
        }

        return sumOfCalibrationValues
    }

    fun getResult2(puzzleInput: URL): Int {
        val lines = getLines(puzzleInput)

        var sumOfCalibrationValues = 0
        for (line in lines) {
            if (line.isNotEmpty()) {
                sumOfCalibrationValues += getCalibrationValue2(line)
            }
        }

        return sumOfCalibrationValues
    }

    private fun getCalibrationValue1(line: String): Int {
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

    private fun getCalibrationValue2(line: String): Int {
        var firstDigit = 0
        var lastDigit = 0
        for (index in 0..line.length) {
            val digit = getDigit(line.substring(index))
            if (digit != null) {
                if (firstDigit == 0) {
                    firstDigit = digit
                }
                lastDigit = digit
            }
        }
        return 10 * firstDigit + lastDigit
    }

    private fun getDigit(string: String): Int? {
        return when {
            string.startsWith("1") || string.startsWith("one") -> {
                1
            }

            string.startsWith("2") || string.startsWith("two") -> {
                2
            }

            string.startsWith("3") || string.startsWith("three") -> {
                3
            }

            string.startsWith("4") || string.startsWith("four") -> {
                4
            }

            string.startsWith("5") || string.startsWith("five") -> {
                5
            }

            string.startsWith("6") || string.startsWith("six") -> {
                6
            }

            string.startsWith("7") || string.startsWith("seven") -> {
                7
            }

            string.startsWith("8") || string.startsWith("eight") -> {
                8
            }

            string.startsWith("9") || string.startsWith("nine") -> {
                9
            }

            else -> {
                null
            }
        }
    }

    private fun getLines(url: URL): List<String> {
        val input = url.readText()
        return input.split("\n")
    }
}
