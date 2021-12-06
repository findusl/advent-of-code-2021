fun main_1_2(args: Array<String>) {
	val lines = loadLinesFromUrl(args.first())

	val numbers = lines
		.map { it.toInt() }
		.toIntArray()

	val window3 = IntArray(numbers.size - 2)
	for (i in window3.indices) {
		window3[i] = numbers[i] + numbers[i + 1] + numbers[i + 2]
	}

	var previous: Int? = null
	var result = 0
	for (window in window3) {
		if (previous != null && window > previous) result ++
		previous = window
	}
	println("$result were bigger than the previous")
}
