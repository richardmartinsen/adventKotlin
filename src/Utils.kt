import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = File("inputFiles", "$name.txt")
    .readLines()

fun readInt(name: String) = File("inputFiles", "$name.txt")
    .readLines().map { it.toInt() }

fun readFileSplitEmptyline(name: String) = File("inputFiles", "$name.txt")
    .readText().split("\n\n")

/**
 * Converts string to md5 hash.
 */
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')
