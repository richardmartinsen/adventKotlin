import java.io.File
import kotlin.coroutines.Continuation

fun main() {
    data class File(
        val file: String,
        val size: Int,
    ) {
        fun print() {
            println("$file $size")
        }
    }

    data class Folder(
        var path: String,
        val name: String,
        val folders: MutableList<Folder> = mutableListOf(),
        val files: MutableList<File> = mutableListOf(),
        var parent: Folder? = null,
    ) {
        fun addFile(file: File) {
            files.add(file)
        }

        fun addFolder(folder: Folder, parent: Folder) {
            this.parent = parent
            folders.add(folder)
        }

        fun print() {
            println("$path$name")
            folders.forEach {
                it.print()
            }
            files.forEach {
                it.print()
            }
        }
    }

    data class Root(
        var files: List<Folder> = listOf(Folder("/", "/")),
        var pwd: String = "/",
        var currentFolder: Folder = files.first()
    ) {
        fun addFolder(folder: Folder) {
            println("add folder ${folder.path} in path ${currentFolder.path}")
            folder.path = currentFolder.path
            currentFolder.addFolder(folder, currentFolder)
            files = files.map{
                if (currentFolder.path == it.path) {
                    currentFolder
                } else it
            }
            println("tree after add folder : ${this.print()}")
        }

        fun addFile(file: File) {
            println("add file $file in path ${currentFolder.path}")
            currentFolder.addFile(file)
            files = files.map{
                if (currentFolder.path == it.path) {
                    currentFolder
                } else it
            }
            println("tree after add file : ${this.print()}")
        }

        fun cd(folderName: String) {
            println("${pwd} cd $folderName")
            if (folderName.equals("..")) {
                if (currentFolder.parent == null)
                    print("Already on root")
                else
                    currentFolder = currentFolder.parent!!

//                    files.flatMap { it.folders }.firstOrNull {
//                    it.path == pwd.substringBeforeLast("/").substringBeforeLast("/")
//                } ?: currentFolder
            } else {
                val desiredFolder = currentFolder.folders.find {
                    it.name == folderName
                }

                if (desiredFolder == null)
                    println("Folder $desiredFolder doesn't exist")
                else {
                    currentFolder = desiredFolder
                    pwd += "$folderName/"
                }
            }
            println("$pwd")
        }

        fun print() {
            println("root")
            this.files.forEach {
                it.folders.forEach {
                    it.print()
                }
                it.files.forEach {
                    it.print()
                }
            }
        }
    }

    fun part1(input: List<String>): Int {
        val root = Root()
        input.forEach {
            val line = it.split(" ")
            println(line)
            when (line.first()) {
                "$" -> when(line[1]) {
                    "cd" -> if (!line.last().equals("/")) root.cd(line.last())
                    else -> {}
                }
                "dir" -> root.addFolder(Folder(line.last(), line.last()))
                else -> root.addFile(File(line.last(), line.first().toInt()))
            }
        }
        root.print()
        return 0
    }

//    fun part2(input: List<String>): Int {
//        return 0
//    }

    val input = readInput("Day07")
    part1(input)
//    println(part1(input))
//    println(part2(input))
//    check(part1(input) == 0)
//    check(part2(input) == 0)
}
