import okio.BufferedSource
import okio.FileSystem
import okio.Path.Companion.toPath
import okio.buffer
import okio.use

fun main_3_1_overengineered(args: Array<String>) {
	val filePath = args.firstOrNull() ?: "3.txt"
	FileSystem.SYSTEM.source(filePath.toPath())
		.use { fileSource ->
			fileSource.buffer().use(::execute)
		}
}

fun execute(bufferedSource: BufferedSource) {
	val firstLine = bufferedSource.readUtf8Line()!!
	val lineLength = firstLine.length
	val sums = IntArray(lineLength)
	for (i in 0 until lineLength) {
		sums[i] = firstLine[i].code
	}
	var lineCount = 1

	while (!bufferedSource.exhausted()) {
		for (i in sums.indices) {
			val byte = bufferedSource.readByte()
			sums[i] = sums[i] + byte
		}
		bufferedSource.skip(1) // new line
		lineCount++
	}

	val offset = lineCount * '0'.code + ((lineCount + 1) / 2)
	val gammaRateBinaryChars = CharArray(lineLength)
	for (i in sums.indices) {
		val sumCorrected = sums[i] - offset
		gammaRateBinaryChars[i] = if (sumCorrected >= 0) '1' else '0'
	}

	val gammaRate = gammaRateBinaryChars.concatToString().toUInt(2)

	val epsilonRate = invertOnlyUntilHighestSetBit(gammaRate)

	val result = gammaRate * epsilonRate

	println("Result $result")
}

private fun invertOnlyUntilHighestSetBit(gammaRate: UInt): UInt {
	val epsilonRateBitMask = (gammaRate.takeHighestOneBit()) * 2u - 1u
	return gammaRate.inv().and(epsilonRateBitMask)
}
