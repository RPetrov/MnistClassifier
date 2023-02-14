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
}

fun main(args: Array<String>) {

    val linesFromFile = Files.readAllLines(Paths.get("archive_mnist\\mnist_train.csv"))
    val images = linesFromFile.map { Image.parse(it) }
    println(linesFromFile.size)
    println("Hello")
}