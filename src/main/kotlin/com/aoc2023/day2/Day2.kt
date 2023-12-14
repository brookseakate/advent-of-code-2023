package main.kotlin.com.aoc2023.day2

import main.kotlin.com.aoc2023.util.Utils.Companion.readFileAsMutableList

class Day2 {
  companion object {
    fun main() {
      println("hello Day 2")

      val bagContents = mapOf(
        "red" to 12,
        "green" to 13,
        "blue" to 14,
      )
      val input = readFileAsMutableList("day2/Input")

      val total = getPossibleGameIdTotal(input, bagContents)
      println(total)
    }

    private fun parseAndCheckGameIsPossible(
      line: String,
      bagContents: Map<String, Int>,
    ): Int {
      val (gameString, allGamesString) = line.split(": ")

      val gameNumber = gameString.substringAfter("Game ").toInt()

      val gameSetsStrings = allGamesString.split("; ")

      var gameIsPossible = true

      for (setString in gameSetsStrings) {
        val cubeQtys = setString.split(", ")

        for (cubeQty in cubeQtys) {
          val (qtyString, color) = cubeQty.split(" ")
          val qty = qtyString.toInt()
          if (bagContents[color]!! < qty) {
            gameIsPossible = false
          }
        }
      }

      return if (gameIsPossible) {
        gameNumber
      } else 0
    }

    private fun getPossibleGameIdTotal(
      input: List<String>,
      bagContents: Map<String, Int>,
    ): Int {
      return input.sumOf { line -> parseAndCheckGameIsPossible(line, bagContents) }
    }
  }
}