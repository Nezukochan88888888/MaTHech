# -------------------------
# Entry points: Activities/Services/Receivers/Providers
# (AGP keeps manifest components automatically, but we keep them explicitly to be safe.)
-keep class com.example.mathech.** extends android.app.Activity { *; }
-keep class com.example.mathech.** extends android.app.Service { *; }
-keep class com.example.mathech.** extends android.content.BroadcastReceiver { *; }
-keep class com.example.mathech.** extends android.content.ContentProvider { *; }

# -------------------------
# Keep classes used by WebView and JS bridge
# -------------------------
-keepclassmembers class * {
    @android.webkit.JavascriptInterface <methods>;
}

# -------------------------
# Keep AESUtils methods used in app
# -------------------------
-keep class com.example.mathech.AESUtils {
    public static java.lang.String decryptFromAssets(android.content.Context, java.lang.String);
    public static byte[] encrypt(byte[]);
}

# -------------------------
# Optional tool class present in sources; do NOT keep so it can be obfuscated/removed if unused
## (no keep rule on EncryptTool)

# -------------------------
# Keep line numbers for crash debugging
# -------------------------
-keepattributes SourceFile,LineNumberTable

# -------------------------
# Keep native method names (important for Android internals)
# -------------------------
-keepclasseswithmembernames class * {
    native <methods>;
}

# -------------------------
# Remove android.util.Log calls in release to reduce size/noise
# -------------------------
-assumenosideeffects class android.util.Log {
    public static boolean isLoggable(java.lang.String, int);
    public static int v(...);
    public static int d(...);
    public static int i(...);
    public static int w(...);
    public static int e(...);
    public static int wtf(...);
}
