package main.kotlin.com.aoc2023.day1

import main.kotlin.com.aoc2023.util.Utils.Companion.readFileAsMutableList

class Day1 {
  companion object {
    fun main() {
      println("hello Day 1")

      val input = readFileAsMutableList("day1/Input.txt")
      val total = getCalibrationTotal(input)
      println("Total: $total")
    }

    private fun getCalibrationTotal(lines: List<String>): Int {
      var total = 0
      for (line in lines) {
        val calibrationVal = getCalibrationValue(line)
        total+=calibrationVal

        // TODO remove logs
        println("Running total: $total")
      }

      return total
    }

    private fun getCalibrationValue(string: String): Int {

      val regex = Regex("\\d")

      val digits = regex
        .findAll(
          replaceDigitStringsWithDigits(string)
        )
        .map { it.value }
        .toList()

      // TODO remove logs
      println(digits)
      val result = Integer.parseInt(digits.first() + digits.last())
      println("Result: $result")

      return result
    }

    private fun replaceDigitStringsWithDigits(string: String): String {
      println("Undigitized: $string")

      val regex = Regex(
        Digit.values()
          .joinToString("|") { "(" + it.string + ")" }
      )

      val digitized = string.replace(
          regex = regex,
          transform = { it.value.toDigit().integer.toString() }
        )

      // TODO remove logs
      println("Digitized: $digitized")
      return digitized
    }

    enum class Digit(
      val string: String,
      val integer: Int,
    ) {
      ZERO("zero", 0),
      ONE("one", 1),
      TWO("two", 2),
      THREE("three", 3),
      FOUR("four", 4),
      FIVE("five", 5),
      SIX("six", 6),
      SEVEN("seven", 7),
      EIGHT("eight", 8),
      NINE("nine", 9),
      ;

    }
    private fun String.toDigit(): Digit {
      return Digit.valueOf(this.uppercase())
    }
  }
}