private val map = Array(1000) { IntArray(1000)}

fun main_6_1(args: Array<String>) {
	readLinesFromFile(args.firstOrNull() ?: "5.txt")


	println("Result $result")
}

private fun markPoint(x: Int, y: Int) {
	map[x][y]++
}
