import java.nio.file.Files
import java.nio.file.Paths
import kotlin.math.sqrt

data class Image(val value: Int?, val data: IntArray) {

    companion object {
        fun parseWithValue(line: String): Image {
            // 0,2,5...
            val number = line.split(",")
            val value = number[0].toInt()
            val data = number.subList(1, number.size).map { it.toInt() }.toIntArray()
            return Image(value, data)
        }

        fun parseWithoutValue(line: String): Image {
            // 0,2,5...
            val number = line.split(",")
            val value = null
            val data = number.map { it.toInt() }.toIntArray()
            return Image(value, data)
        }

    }

    fun distance(anotherImage: Image): Double {
        var summ: Int = 0
        for (i in 0 until anotherImage.data.size)
            summ += data[i] - anotherImage.data[i] * data[i] - anotherImage.data[i]
        return sqrt(summ.toDouble())
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

    val images = linesFromFile.subList(1, linesFromFile.size).map { Image.parseWithValue(it) }
    images.take(1).forEach { print(it) }
    println(linesFromFile.size)

    val linesFromFileTest = Files.readAllLines(Paths.get("archive_mnist\\mnist_test.csv"))
    val imagesTest =
        linesFromFileTest.subList(1, linesFromFileTest.size).map { Image.parseWithoutValue(it) }
    println(imagesTest.first())
}