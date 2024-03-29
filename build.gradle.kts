plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.hilt.android) apply false
//    alias(libs.plugins.google.services) apply false //TODO add on release with google-services.json
    alias(libs.plugins.firebase.crashlytics) apply false
    alias(libs.plugins.firebase.appdistribution) apply false
    alias(libs.plugins.version.catalog.versions)
    alias(libs.plugins.version.catalog.update)
}
tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
versionCatalogUpdate {
    sortByKey.set(false)
    keep {
        keepUnusedVersions.set(true)
        keepUnusedLibraries.set(true)
        keepUnusedPlugins.set(true)
    }
}