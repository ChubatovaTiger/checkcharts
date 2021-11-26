import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.CustomChart
import jetbrains.buildServer.configs.kotlin.v2019_2.CustomChart.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.script
import jetbrains.buildServer.configs.kotlin.v2019_2.buildTypeCustomChart

/*
The settings script is an entry point for defining a TeamCity
project hierarchy. The script should contain a single call to the
project() function with a Project instance or an init function as
an argument.

VcsRoots, BuildTypes, Templates, and subprojects can be
registered inside the project using the vcsRoot(), buildType(),
template(), and subProject() methods respectively.

To debug settings scripts in command-line, run the

    mvnDebug org.jetbrains.teamcity:teamcity-configs-maven-plugin:generate

command and attach your debugger to the port 8000.

To debug in IntelliJ Idea, open the 'Maven Projects' tool window (View
-> Tool Windows -> Maven Projects), find the generate task node
(Plugins -> teamcity-configs -> teamcity-configs:generate), the
'Debug' option is available in the context menu for the task.
*/

version = "2021.2"

project {

    buildType(Conf)

    features {
        buildTypeCustomChart {
            id = "PROJECT_EXT_46"
            title = "New chart title"
            seriesTitle = "Serie"
            format = CustomChart.Format.TEXT
            series = listOf(
                Serie(title = """Build Step #1 - Command Line "command line 2"""", key = SeriesKey.buildStepDuration("RUNNER_1")),
                Serie(title = """Build Step #2 - Command Line "cmd"""", key = SeriesKey.buildStepDuration("RUNNER_2")),
                Serie(title = """Build Step #3 - Command Line "мой шаг шаг"""", key = SeriesKey.buildStepDuration("RUNNER_3"))
            )
        }
    }
}

object Conf : BuildType({
    name = "conf"

    steps {
        script {
            name = """step for "mstest" command"""
            scriptContent = """
                echo a 
                echo b
            """.trimIndent()
        }
        script {
            name = "cmd"
            scriptContent = "echo a"
        }
        script {
            name = "мой шаг шаг"
            scriptContent = """echo "привет""""
        }
    }
})
