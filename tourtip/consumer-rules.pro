# Keep all public classes within the com.fappslab.tourtip package and its subpackages.
-keep public class com.fappslab.tourtip.** { *; }

# Keep all classes annotated with @androidx.annotation.Keep and their members.
-keep @androidx.annotation.Keep class * { *; }

# Keep all methods within any class that are annotated with @androidx.annotation.Keep.
-keepclassmembers class * {
    @androidx.annotation.Keep <methods>;
}

# Keep all fields and the standard 'values()' and 'valueOf()' methods of all enum classes.
-keepclassmembers enum * {
    <fields>;
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
