package day01

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class Day01Test {
    @Test
    fun testPuzzle1() {
        val day01 = Day01()
        val url = object {}.javaClass.getResource("example")
        assertNotNull(url, "Example file not found. See Readme for more details.")
        assertEquals(142, day01.getResult1(url))
    }
}
