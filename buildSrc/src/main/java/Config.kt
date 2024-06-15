object Config {
    private const val packageName: String = "com.fappslab.tourtip"
    const val namespace: String = packageName
    const val compileSdk: Int = 34
    const val applicationId: String = packageName
    const val minSdk: Int = 21
    const val targetSdk: Int = 34
    const val versionCode: Int = 1
    const val versionName: String = "1.06"

    val artifactId: String = packageName.substringAfterLast(delimiter = ".")
    const val version: String = "$versionName.$versionCode"
    const val groupId: String = packageName
}
