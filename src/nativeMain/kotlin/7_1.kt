import kotlin.math.abs

fun main_7_1(args: Array<String>) {
	val lines = readLinesFromFile(args.firstOrNull() ?: "7.txt")
	val positionsSorted = lines.first().split(',').map { it.toInt() }.toIntArray().sortedArray()
	val median = positionsSorted.medianOfSorted()

	val result = positionsSorted.sumOf { abs(it - median) }

	println("Result $result")
}

fun IntArray.medianOfSorted(): Int {
	return if (size % 2 == 0) {
		(get(size / 2) + get(size / 2 - 1)) / 2
	} else {
		get(size / 2)
	}
}
