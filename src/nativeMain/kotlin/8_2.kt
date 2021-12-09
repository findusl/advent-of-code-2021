fun main_8_2(args: Array<String>) {
	val lines = readLinesFromFile(args.firstOrNull() ?: "8.txt")

	val result = lines
		.sumOf { row ->
			val (allNumbers, outputValues) = row.split('|').map { it.split(' ').filter { digit -> digit.isNotBlank() } }
			val mapper = generateNumberMapper(allNumbers)
			outputValues.map { outputDigit ->
				mapper[outputDigit.sorted()]
			}.joinToString(separator = "").toInt()
		}

	println("Result $result")
}

private fun generateNumberMapper(input: List<String>): Map<String, Int> {
	val numberMapping = arrayOfNulls<String>(10)
	val segmentMapping = mutableMapOf<Char, Char>()

	numberMapping[1] = input.first { it.length == 2 }
	numberMapping[4] = input.first { it.length == 4 }
	numberMapping[7] = input.first { it.length == 3 }
	numberMapping[8] = input.first { it.length == 7 }
	val numbersLength4 = input.filter { it.length == 6 }
	val numbersLength6 = input.filter { it.length == 5 }

	val mapping4Without1 = numberMapping[4]!!.filter { !numberMapping[1]!!.contains(it) }

	segmentMapping['b'] = mapping4Without1.first { c -> numbersLength4.all { it.contains(c) } }
	segmentMapping['d'] = mapping4Without1.first { it != segmentMapping['b'] }

	numberMapping[0] = numbersLength4.first { !it.contains(segmentMapping['d']!!) }
	numberMapping[6] = numbersLength4.first { segment -> !numberMapping[1]!!.all { c -> segment.contains(c) } }
	numberMapping[9] = numbersLength4.minus(setOf(numberMapping[0], numberMapping[6])).first()

	numberMapping[3] = numbersLength6.first { segment -> numberMapping[1]!!.all { c -> segment.contains(c) } }
	numberMapping[5] = numbersLength6.first { it.contains(segmentMapping['b']!!) }
	numberMapping[2] = numbersLength6.minus(setOf(numberMapping[3], numberMapping[5])).first()

	return numberMapping.mapIndexed { number, segments -> Pair(segments!!.sorted(), number) }.toMap()
}

private fun String.sorted(): String {
	return this.toCharArray().apply(CharArray::sort).concatToString()
}
