object Config {
    private const val packageName: String = "com.fappslab.tourtip"
    const val namespace: String = packageName
    const val compileSdk: Int = 34
    const val applicationId: String = packageName
    const val minSdk: Int = 21
    const val targetSdk: Int = 34
    const val versionCode: Int = 11
    const val versionName: String = "1.06"

    val artifactId: String
        get() = packageName.substringAfterLast(delimiter = ".")
    const val groupId: String = packageName
    const val version: String = versionName
}
