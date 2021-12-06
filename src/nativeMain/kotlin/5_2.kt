private val map = Array(1000) { IntArray(1000)}

fun main_5_2(args: Array<String>) {
	readLinesFromFile(args.firstOrNull() ?: "5.txt")
		.forEach { line ->
			val (start, end) = line.split(" -> ")
			val (startX, startY) = start.split(',').map { it.toInt() }
			val (endX, endY) = end.split(',').map { it.toInt() }
			if (startX == endX) {
				bidirectionalRange(startY, endY).map { y ->
					markPoint(startX, y)
				}
			} else if(startY == endY) {
				bidirectionalRange(startX, endX).map { x ->
					markPoint(x, startY)
				}
			} else {
				// diagonal
				bidirectionalRange(startX, endX)
					.zip(bidirectionalRange(startY, endY), ::Pair)
					.forEach { point ->
						markPoint(point.first, point.second)
					}
			}
		}

	val result = map
		.flatMap { it.asIterable() }
		.count { it > 1 }

	println("Result $result")
}

private fun markPoint(x: Int, y: Int) {
	map[x][y]++
}
