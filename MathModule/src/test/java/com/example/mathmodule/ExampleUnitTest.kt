package com.example.mathmodule

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun mathHelper_addition_isCorrect() {
        val helper = MathHelper()
        assertEquals(5, helper.add(2, 3))
    }

    @Test
    fun mathHelper_square_isCorrect() {
        val helper = MathHelper()
        assertEquals(9, helper.square(3))
        assertEquals(16, helper.square(4))
    }
}