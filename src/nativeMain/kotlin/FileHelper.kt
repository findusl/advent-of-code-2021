import okio.*
import okio.Path.Companion.toPath


@Throws(IOException::class)
fun readLinesFromFile(filePath: String): List<String> {
	val path = filePath.toPath()
	FileSystem.SYSTEM.source(path).use { fileSource ->
		fileSource.buffer().use {
			val result = mutableListOf<String>()
			while (!it.exhausted()) {
				result.add(it.readUtf8Line()!!)
			}
			return result
		}
	}
}
