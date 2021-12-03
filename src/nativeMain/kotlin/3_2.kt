fun main_3_2(args: Array<String>) {
	val lines = readLinesFromFile(args.firstOrNull() ?: "3.txt")

	val charArrayLines = lines.map { it.toCharArray() }
	val oxygenGeneratorRating = charArrayLines.keepMostCommonBit().joinToString(separator = "").toInt(2)
	val co2ScrubberRating = charArrayLines.keepLeastCommonBit().joinToString(separator = "").toInt(2)

	println("Oxygen Generator Rating $oxygenGeneratorRating")
	println("CO2 Scrubber Rating $co2ScrubberRating")

	val result = oxygenGeneratorRating * co2ScrubberRating

	println("Result $result")
}

fun List<CharArray>.keepMostCommonBit(fromPosition: Int = 0): CharArray {
	if (size == 1 || fromPosition >= get(0).size) return get(0)
	val partitions = partition { chars -> chars[fromPosition] == '1' }
	if (partitions.first.size >= partitions.second.size) {
		return partitions.first.keepMostCommonBit(fromPosition + 1)
	} else {
		return partitions.second.keepMostCommonBit(fromPosition + 1)
	}
}

fun List<CharArray>.keepLeastCommonBit(fromPosition: Int = 0): CharArray {
	if (size == 1 || fromPosition >= get(0).size) return get(0)
	val partitions = partition { chars -> chars[fromPosition] == '1' }
	if (partitions.first.size < partitions.second.size) {
		return partitions.first.keepLeastCommonBit(fromPosition + 1)
	} else {
		return partitions.second.keepLeastCommonBit(fromPosition + 1)
	}
}
