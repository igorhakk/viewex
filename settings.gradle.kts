rootProject.name = "viewex"

include(":core")

//include(":localization")
//
//include(":security")
//
//include(":domain")
//
include(":app")

include(":ui")

include(":composer")

include(":composer-view")

include(":composer-layout")

//include(":ui")
//
//include(":example")
//
//include(":web-js:vue-quasar")




fun includeSubprojects(vararg path: String) {
    path.forEach {
        val groupDir = composeDir(rootDir, it)

        groupDir.listFiles()?.filter { item -> item.isDirectory }?.forEach { item ->

            val projectPath = if (it.startsWith(":")) { it } else { ":".plus(it) } + ":${item.name}"

            if (isSourceDir(item)) {
                include(projectPath)
                project(projectPath).name = projectPath.replace(":", "-").replace("^-*".toRegex(), "")
            }

            if (item.isDirectory && item?.listFiles()?.any { child -> isSourceDir(child) } == true) {
                this.includeSubprojects(projectPath)
            }
        }
    }
}

fun composeDir(currentDir: File, projectPath: String): File {

    val path = projectPath.replace("^:*".toRegex(), "")


    val dir = File(currentDir.toString() + File.separator + path.replace(":", File.separator))

    if (!dir.exists() || !dir.isDirectory)
        throw java.io.FileNotFoundException("Dir [ $dir ] for the project path [ $projectPath ] not found")

    return dir
}

fun isSourceDir(
    dir: File
): Boolean = dir.listFiles()?.any {
    it.isDirectory && it.name == "src" && it.listFiles().any { child ->
        child.isDirectory && child.name == "main"
    }
} ?: false
