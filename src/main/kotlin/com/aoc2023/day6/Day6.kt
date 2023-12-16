package main.kotlin.com.aoc2023.day6

import kotlin.math.pow
import kotlin.math.sqrt

class Day6 {
  companion object {
    fun main() {
      println("hello Day 6")

      val (timesList, distancesList) = getInput()
      println(getProductOfWinningStrategies(timesList, distancesList))
    }

    fun parseInput() {
      TODO("Super boring here, skip for this one")
    }

    private fun getInput(): Pair<List<Long>, List<Long>> /* timeList, distanceList */ {
      val sampleTimes = listOf(71530L)
      val sampleDistances = listOf(940200L)

      val inputTimes = listOf(44826981L)
      val inputDistances = listOf(202107611381458L)

//      return Pair(sampleTimes, sampleDistances)
      return Pair(inputTimes, inputDistances)
    }


    private fun getProductOfWinningStrategies(
      // would do something OO, but not interesting
      raceLengthList: List<Long>,
      recordDistanceList: List<Long>,
    ): Long {
      check(raceLengthList.size == recordDistanceList.size) { "Input is wrong!!" }

      val winningStrategiesPerRaceList = mutableListOf<Long>()

      raceLengthList.forEachIndexed { index, raceLength ->
        val (minWinningButtonPress, maxWinningButtonPress) = getMinAndMaxPresses(
          raceLength = raceLength,
          recordDistance = recordDistanceList[index]
        )

        val winningStrategiesCount = maxWinningButtonPress - minWinningButtonPress + 1 // +1 = inclusive range count

        winningStrategiesPerRaceList.add(winningStrategiesCount)
      }

      return winningStrategiesPerRaceList.reduce { x, y -> x * y }
    }

    private fun getMinAndMaxPresses(
      raceLength: Long,
      recordDistance: Long,
      // feel like we'll want an a (coefficient for our x^2) for part 2, but will resist adding til then...
    ): Pair<Long, Long> /* Min, Max */ {
      // quadratic equation: ax^2 + bx + c

      // our parabola is shaped: y = -x^2 + bx, where:
        // a = -1
        // b = raceLength
        // and we'll solve for y > recordDistance – SO set c = -recordDistance
      val a = -1
      val b = raceLength
      val c = -recordDistance

      // quadratic formula: x = (-b +/- sqrt(b^2 - 4ac)) / 2a
      val recordMinPress = (-b + sqrt(b.toDouble().pow(2) - (4*a*c))) / 2*a
      val recordMaxPress = (-b - sqrt(b.toDouble().pow(2) - (4*a*c))) / 2*a

      val ourMinPress = recordMinPress.toLong() + 1
      val ourMaxPress = recordMaxPress.toLong() // don't add here – we want to hold LESS long
        .let { if (it.compareTo(recordMaxPress) == 0) {
          // ensure we're *under* theirs (handles for case: recordMaxPress == its Int value)
          it - 1
        } else it
      }

      return Pair(ourMinPress, ourMaxPress)
    }
  }
}