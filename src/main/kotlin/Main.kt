import java.nio.file.Files
import java.nio.file.Paths

data class Image(val value: Int, val data: IntArray) {

    companion object {
        fun parse(line: String): Image {
            // 0,2,5...
            val number = line.split(",")
            val value = number[0].toInt()
            val data = number.subList(1, number.size).map { it.toInt() }.toIntArray()
            return Image(value, data)
        }
    }

    override fun toString(): String {
        val stringbuilder = StringBuilder()
        for (i in 0 until 28) {
            for (j in 0 until 28) {
                stringbuilder.append(if (data[i * 28 + j] > 127) "*" else " ")
            }
            stringbuilder.append("\n")
        }
        return stringbuilder.toString()
    }
}

fun main(args: Array<String>) {

    val linesFromFile = Files.readAllLines(Paths.get("archive_mnist\\mnist_train.csv"))
    val images = linesFromFile.subList(1, linesFromFile.size).map { Image.parse(it) }
    images.take(10).forEach { print(it) }
    println(linesFromFile.size)
    println("Hello")
}