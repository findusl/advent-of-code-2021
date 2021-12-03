fun main_3_1(args: Array<String>) {
	val lines = readLinesFromFile(args.firstOrNull() ?: "3.txt")

	val sums = lines
		.map { binaryNumberString ->
			binaryNumberString.toCharArray()
				.map { c ->
					when(c) {
						// I could just leave 0 and then compare to 0.5, but this seems more intuitive
						'0' -> -1
						'1' -> 1
						else -> error("Invalid binary number $c")
					}
				}
		}.reduce { sum, number ->
			sum.zip(number, Int::plus)
		}

	val gammaRateBinaryString = sums.map { sum ->
		when {
			sum > 0 -> '1'
			else -> '0'
		}
	}.joinToString(separator = "")

	println("Gamma rate bin string $gammaRateBinaryString")

	val gammaRate = gammaRateBinaryString.toUInt(2)

	println("Gamma rate $gammaRate")

	val epsilonRateBitMask = (gammaRate.takeHighestOneBit()) * 2u - 1u
	val epsilonRate = gammaRate.inv().and(epsilonRateBitMask)

	println("epsilon rate $epsilonRate")

	val result = gammaRate * epsilonRate

	println("Result $result")

}
