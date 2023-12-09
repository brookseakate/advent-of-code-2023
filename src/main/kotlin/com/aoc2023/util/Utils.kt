package main.kotlin.com.aoc2023.util

import java.nio.file.Files
import java.nio.file.Paths
import java.util.stream.Stream

class Utils {
  companion object {
    fun readFileAsMutableList(relFilePath: String): MutableList<String> {
      return Files
        .readAllLines(
          Paths.get("./src/main/kotlin/com/aoc2023/$relFilePath")
        )
        .toMutableList()
    }

    fun readFileAsStream(relFilePath: String): Stream<String> {
      return Files
        .lines(
          Paths.get("./src/main/kotlin/com/aoc2023/$relFilePath")
        )
    }
  }
}