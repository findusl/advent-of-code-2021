import sample.libcurl.CUrl

fun loadLinesFromUrl(url: String): List<String> {
    val curl = CUrl(url)
    val sb = StringBuilder()
    curl.body.subscribe {
        sb.append(it)
    }
    curl.fetch()
    return sb.lines()
}