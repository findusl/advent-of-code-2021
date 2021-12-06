private lateinit var boards: List<Board>
private lateinit var numbers: IntArray

fun main_4_1(args: Array<String>) {
	val lines = readLinesFromFile(args.firstOrNull() ?: "4.txt")

	numbers = lines.first().split(',').map { it.toInt() }.toIntArray()

	boards = (2 until lines.size step 6)
		.map { boardStart ->
			val boardNumbers = (boardStart until boardStart + 5)
				.asSequence()
				.map { lines[it] }
				.flatMap { it.split(' ') }
				.filter { it.isNotEmpty() }
				.map { it.toInt() }
				.toList().toIntArray()
			Board(boardNumbers)
		}

	var winnerBoard = callNumbersUntilWon()

	val result = winnerBoard.calculateScore()

	println("Result $result")
}

private fun callNumbersUntilWon(): Board {
	for (number in numbers) {
		for (board in boards) {
			if (board.selectNumberAndCheckWin(number)) {
				return board
			}
		}
	}
	error("No board won")
}

private const val firstValueMask = 0b10000_00000_00000_00000_00000
private const val firstRowMask = 0b11111_00000_00000_00000_00000
private const val firstColumnMask = 0b10000_10000_10000_10000_10000

class Board (val boardNumbers: IntArray, var matchedNumbers: Int = 0) {

	private var lastCalledNumber: Int = -1

	fun selectNumberAndCheckWin(numberCalled: Int): Boolean {
		val position = boardNumbers.indexOf(numberCalled)
		if (position >= 0) {
			println("Board contains number $numberCalled in position $position")
			val maskToSet = firstValueMask shr position
			matchedNumbers = matchedNumbers or maskToSet
			val rowMask = firstRowMask shr ((position/5)*5)
			val columnMask = firstColumnMask shr (position%5)
			println("Matched ${matchedNumbers.toString(2, 25)}. Row Mask ${rowMask.toString(2, 25)}, columnMask ${columnMask.toString(2, 25)}")
			lastCalledNumber = numberCalled
			return (rowMask and matchedNumbers == rowMask) || (columnMask and matchedNumbers == columnMask)
		}
		return false
	}

	fun calculateScore(): Int {
		val remainingSum =  IntArray(25) { index ->
			if (matchedNumbers and (firstValueMask shr index) == 0) boardNumbers[index] else 0
		}.sum()
		return remainingSum * lastCalledNumber
	}
}

fun Int.toString(radix: Int, padTo: Int): String {
	return toString(radix).padStart(padTo, '0')
}
