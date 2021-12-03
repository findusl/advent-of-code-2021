fun main_1_1(args: Array<String>) {
	val lines = loadLinesFromUrl(args.first())

	var previous: Int? = null
	var result = 0
	for (line in lines) {
		val number = line.toInt()
		if (previous != null && number > previous) result ++
		previous = number
	}
	println("$result were bigger than the previous")
}
