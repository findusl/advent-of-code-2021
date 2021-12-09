import kotlin.math.abs
import kotlin.math.roundToInt

fun main_8_1(args: Array<String>) {
	val lines = readLinesFromFile(args.firstOrNull() ?: "8.txt")

	val result = lines.asSequence()
		.map { it.split('|')[1] }
		.flatMap { it.split(' ') }
		.count {
			val size = it.length
			size == 2 || size == 3 || size == 4 || size == 7
		}

	println("Result $result")
}
