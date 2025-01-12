package day02

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class Day02Test {
    @Test
    fun testPuzzle1() {
        val day02 = Day02()
        val url = object {}.javaClass.getResource("example")
        assertNotNull(url, "Example file not found. See Readme for more details.")
        assertEquals(8, day02.getResult1(url))
    }
}
