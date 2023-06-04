// Created by android on 2023/3/24.
#include <jni.h>
#include <string>

extern "C"
JNIEXPORT jstring JNICALL
Java_com_yuanqihudong_task_utils_NdkUtils_stringFromJNI(JNIEnv *env, jclass clazz) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}