fun main_6_1(args: Array<String>) {
	val lines = readLinesFromFile(args.firstOrNull() ?: "6.txt")
	val fishPerAge = IntArray(9)
	lines.first().split(',')
		.map { it.toInt() }
		.forEach { fishPerAge[it]++ }

	for (day in 1..80) {
		val breedingFish = fishPerAge[0]
		for (generation in 1..8) {
			fishPerAge[generation - 1] = fishPerAge[generation]
		}
		fishPerAge[6] += breedingFish
		fishPerAge[8] = breedingFish
	}

	val result = fishPerAge.sum()

	println("Result $result")
}
