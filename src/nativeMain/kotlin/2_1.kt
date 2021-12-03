fun main_2_1(args: Array<String>) {
	val lines = readLinesFromFile(args.firstOrNull() ?: "2.txt")

	val commands = lines.map { it.toCommand() }

	val verticalDistance = commands.filterIsInstance<Command.FORWARD>().sumOf { it.magnitude }
	val descendDistance = commands.filterIsInstance<Command.DOWN>().sumOf { it.magnitude }
	val ascendDistance = commands.filterIsInstance<Command.UP>().sumOf { it.magnitude }

	val result = (descendDistance - ascendDistance) * verticalDistance
	println("Result is $result")
}

sealed interface Command {
	val magnitude: Int

	class DOWN(override val magnitude: Int) : Command
	class UP(override val magnitude: Int) : Command
	class FORWARD(override val magnitude: Int) : Command
}

fun String.toCommand(): Command {
	val (commandCode, magnitudeString) = this.split(' ')
	val magnitude = magnitudeString.toInt()
	return when(commandCode.lowercase()) {
		"down" -> Command.DOWN(magnitude)
		"up" -> Command.UP(magnitude)
		"forward" -> Command.FORWARD(magnitude)
		else -> error("Unsupported command")
	}
}
