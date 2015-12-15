package android.ryan.com.ndkdemo.jni;

/**
 * Created by Ryan on 15/6/9.
 */
public class StringKit {

    public static native void setNull(String str);

    static {
        System.loadLibrary("JniDemo");
    }

}
