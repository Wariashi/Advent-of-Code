import day01.Day01
import day02.Day02
import java.net.URL
import kotlin.system.exitProcess

fun main() {
    val day01 = Day01()
    val url01 = getInputUrl("day01")
    println("Day 1:")
    println("Sum of calibration values (part 1): " + day01.getResult1(url01))
    println("Sum of calibration values (part 2): " + day01.getResult2(url01))
    println("")

    val day02 = Day02()
    val url02 = getInputUrl("day02")
    println("Day 2:")
    println("Sum of game IDs: " + day02.getResult1(url02))
    println("")
}

private fun getInputUrl(folder: String): URL {
    val url = object {}.javaClass.getResource("$folder/input")
    if (url == null) {
        println("Input file not found. See Readme for more details.")
        exitProcess(0)
    }
    return url
}
