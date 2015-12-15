##include <android_ryan_com_ndkdemo_jni_MathKit.h>

JNIEXPORT jint JNICALL Java_android_ryan_com_ndkdemo_jni_MathKit_square(JNIEnv * env, jclass cls, jint num){
     return num * num;
}