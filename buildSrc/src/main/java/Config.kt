object Config {
    // App configuration
    private const val PACKAGE_NAME: String = "com.fappslab.tourtip"
    const val NAMESPACE: String = PACKAGE_NAME
    const val COMPILE_SDK: Int = 34
    const val APPLICATION_ID: String = PACKAGE_NAME
    const val MIN_SDK: Int = 21
    const val TARGET_SDK: Int = 34
    // Do not bump manually, it's managed by CI
    const val versionCode: Int = 1
    // Do not bump manually, it's managed by CI
    const val versionName: String = "1.06"

    // Library configuration
    private const val SNAPSHOT: String = "" //"-SNAPSHOT"
    const val VERSION: String = "$versionName.$versionCode$SNAPSHOT"
}
