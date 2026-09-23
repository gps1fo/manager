allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

val newBuildDir: Directory = rootProject.layout.buildDirectory.dir("../../build").get()
rootProject.layout.buildDirectory.value(newBuildDir)

subprojects {
    val newSubprojectBuildDir: Directory = newBuildDir.dir(project.name)
    project.layout.buildDirectory.value(newSubprojectBuildDir)
}
// permission_handler_android déclare « compileSdk = 37 », ce qu'AGP traduit par la plateforme
// « android-37 » — que Google n'a JAMAIS publiée : le dépôt ne propose que android-37.0, .1, .2,
// le nouveau nommage par version mineure. Le greffon a bien besoin de l'API 37 (il utilise
// ACCESS_LOCAL_NETWORK et VERSION_CODES.CINNAMON_BUN), donc le rabaisser à 36 ne compile pas :
// essayé, échec à javac. On redirige vers la plateforme réellement installée.
//
// Mesuré le 23/09/2026 : le manager AMONT intact échoue à l'identique. Ce blocage ne vient pas
// de nous, et il touche tout projet tirant ce greffon.
//
// Ce bloc doit rester AVANT « evaluationDependsOn », qui déclenche l'évaluation : enregistré
// après, Gradle refuse avec « project is already evaluated ».
//
// À RETIRER dès qu'AGP sait résoudre « 37 » vers « android-37.0 », ou que Google publie
// « platforms;android-37 » tout court.
subprojects {
    afterEvaluate {
        val ext = extensions.findByName("android")
        if (ext is com.android.build.gradle.BaseExtension) {
            if (ext.compileSdkVersion == "android-37") {
                logger.lifecycle("gps1fo : ${project.name} — android-37 inexistant, redirigé vers android-37.0")
                ext.compileSdkVersion("android-37.0")
            }
        }
    }
}

subprojects {
    project.evaluationDependsOn(":app")
}

tasks.register<Delete>("clean") {
    delete(rootProject.layout.buildDirectory)
}
