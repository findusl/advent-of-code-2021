private lateinit var boards: List<Board>
private lateinit var numbers: IntArray

fun main_4_2(args: Array<String>) {
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

	var loserBoard = callNumbersUntilOneLeft()

	val result = loserBoard.calculateScore()

	println("Result $result")
}

private fun callNumbersUntilOneLeft(): Board {
	var remainingBoards = boards
	lateinit var lastBoard: Board
	for (number in numbers) {
		remainingBoards = remainingBoards.filter { board ->
			!board.selectNumberAndCheckWin(number)
		}
		if (remainingBoards.size == 1) {
			lastBoard = remainingBoards.first()
		} else if (remainingBoards.isEmpty()) {
			return lastBoard
		}
	}
	error("No board won")
}
