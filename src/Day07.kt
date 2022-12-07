fun main() {
    data class File(
        val file: String,
        val size: Int,
    )

    data class Folder(
        val path: String,
        val name: String,
        val folders: MutableList<Folder> = mutableListOf(),
        val files: MutableList<File> = mutableListOf(),
    ) {
        fun addFile(file: File) {
            files.add(file)
        }

        fun addFolder(folder: Folder) {
            folders.add(folder)
        }
    }

    data class Root(
        var files: List<Folder> = listOf(Folder("/", "/")),
        var pwd: String = "/",
        var currentFolder: Folder = files.first()
    ) {
        fun addFolder(folder: Folder) {
            currentFolder.addFolder(folder)
            files = files.map{
                if (currentFolder.path == it.path) {
                    currentFolder
                } else it
            }
        }

        fun addFile(file: File) {
            currentFolder.addFile(file)
            files = files.map{
                if (currentFolder.path == it.path) {
                    currentFolder
                } else it
            }
        }
        fun cd(folderName: String) {
            if (folderName.equals("..")) {
                currentFolder = files.first {
                    it.path == pwd.substringBeforeLast("/")
                }
            } else {
                val desiredFolder = currentFolder.folders.find {
                    it.name == folderName
                }

                if (desiredFolder == null)
                    println("This folder doesn't exist")
                else {
                    currentFolder = desiredFolder
                    pwd += "/$folderName"
                }
            }




//            val folders = pwd.split("/")
//            folders.
        }
    }

    fun part1(input: List<String>): Int {
//        val files: List<Folder> = listOf(Folder("/","/"))
        val root: Root = Root()
        input.forEach {

        }



        root.addFolder(Folder("a", "a"))
        root.addFolder(Folder("b", "b"))
        root.addFile(File("filnavn", 100))
        root.addFile(File("enda en fil", 200))
        root.cd("a")
        root.addFolder(Folder("c", "c"))
        root.addFolder(Folder("d", "d"))
        root.addFile(File("rrrr", 100))
        root.addFile(File("eeee", 200))
        root.cd("..")
        root.cd("b")
        root.addFolder(Folder("e", "e"))
        root.addFolder(Folder("f", "f"))
        root.addFile(File("wwww", 100))
        root.addFile(File("qqqq", 200))


//        files.first { it.name == "/" }.let {
//            it.addFile(File("en fil", 100))
//        }
//        files.first { it.name == "/" }.let {
//            it.addFolder(Folder("a"))
//            it.addFile(File("b.txt", 14848514))
//            it.addFile(File("c.txt", 8504156))
//            it.addFolder(Folder("d"))
//        }
//        files.first { it.name == "/" }.let {
//            it.folders.first {
//                it.name ==
//            }
//        }
//        files
//            .filter { it.name == "/" }
//            .let { println(it)
//                it.filter { it.folders.name == "a" }
//            }
//            .let {
//                it.first().addFolder(Folder("e"))
//                it.first().addFile(File("f", 29116))
//                it.first().addFile(File("g", 2557))
//                it.first().addFile(File("h.lst", 62596))
//            }
        return 0
    }

    fun part2(input: List<String>): Int {
        return 0
    }

    val input = readInput("Day07")
    println(part1(input))
    println(part2(input))
    check(part1(input) == 0)
    check(part2(input) == 0)
}
