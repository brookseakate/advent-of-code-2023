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

      val digits = regex.findAll(string).map { it.value }.toList()

      // TODO remove logs
      println(digits)
      val result = Integer.parseInt(digits.first() + digits.last())
      println("Result: $result")

      return result
    }
  }
}