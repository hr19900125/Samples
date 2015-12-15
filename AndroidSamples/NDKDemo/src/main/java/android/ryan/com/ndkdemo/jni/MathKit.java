package android.ryan.com.ndkdemo.jni;

/**
 * Created by Ryan on 15/6/9.
 */
public class MathKit {

    public static native int square(int num);

    static {
        System.loadLibrary("JniDemo");
    }

}
