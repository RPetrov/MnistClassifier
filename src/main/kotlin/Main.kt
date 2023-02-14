import java.nio.file.Files
import java.nio.file.Paths

fun main(args: Array<String>) {

    val linesFromFile = Files.readAllLines(Paths.get("archive_mnist\\mnist_train.csv"))

    println(linesFromFile.size)
}