import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {
	val timeTaken = measureTimeMillis {
		main_5_2(args)
	}
	println("Computation took $timeTaken ms")
}