import kotlin.math.abs
import kotlin.math.roundToInt

fun main_7_2(args: Array<String>) {
	val lines = readLinesFromFile(args.firstOrNull() ?: "7.txt")
	val positions = lines.first().split(',').map { it.toInt() }.toIntArray()
	val average = positions.average().toInt() // not sure why the fuck this is not roundToInt(). Have to research

	val result = positions.sumOf { abs(it - average).sumOfN() }

	println("Result $result")
}

private fun Int.sumOfN(): Int {
	return (this*(this+1))/2
}
