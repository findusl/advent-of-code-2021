fun main_2_2(args: Array<String>) {
	val lines = readLinesFromFile(args.firstOrNull() ?: "2.txt")

	val commands = lines.map { it.toCommand() }

	var horizontalPosition = 0
	var aim = 0
	var depth = 0
	for (c in commands) {
		when (c) {
			is Command.DOWN -> aim += c.magnitude
			is Command.UP -> aim -= c.magnitude
			is Command.FORWARD -> {
				horizontalPosition += c.magnitude
				depth += aim * c.magnitude
			}
		}
	}
	val result = horizontalPosition * depth
	println("Result is $result")
}
