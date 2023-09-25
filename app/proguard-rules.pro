# To enable ProGuard in your project, edit project.properties
# to define the proguard.config property as described in that file.
#
# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in ${sdk.dir}/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the ProGuard
# include property in project.properties.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in D:\Android\sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles

#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
# To enable ProGuard in your project, edit project.properties
# to define the proguard.config property as described in that file.
#
# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in ${sdk.dir}/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the ProGuard
# include property in project.properties.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}
#-keep class packagename.** {*;}
-optimizationpasses 5
-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-dontpreverify
-verbose
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*

# 保留行号
-keepattributes SourceFile,LineNumberTable

#-dontwarn  #//dontwarn去掉警告
#-dontskipnonpubliclibraryclassmembers
#-keep public class * extends android.app.Fragment
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
#-keep public class * extends android.preference.Preference
#-keep public class * extends android.support.v4.**
-keep public class com.android.vending.licensing.ILicensingService
-keepclasseswithmembernames class * {
    native <methods>;
}
#-keepclasseswithmembernames class * {
#    public <init>(android.content.Context, android.util.AttributeSet);
#}
#-keepclasseswithmembernames class * {
#    public <init>(android.content.Context, android.util.AttributeSet, int);
#}
#----------------enum-----------------
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
#----------------Parcelable-----------------
-keepclassmembers class * implements android.os.Parcelable {
    static ** CREATOR;
    <fields>;
    <methods>;
}

-keep class * implements java.io.Serializable {
    *;
}
##---------------Begin: proguard configuration for Gson ----------
# Gson specific classes
-dontwarn sun.misc.**
#-keep class com.google.gson.stream.** { *; }

# Application classes that will be serialized/deserialized over Gson
-keep class com.google.gson.examples.android.model.** { *; }

# Prevent proguard from stripping interface information from TypeAdapterFactory,
# JsonSerializer, JsonDeserializer instances (so they can be used in @JsonAdapter)
-keep class * implements com.google.gson.TypeAdapterFactory
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserializer

-keep,allowobfuscation,allowshrinking class com.google.gson.reflect.TypeToken { *; }
-keep,allowobfuscation,allowshrinking class * extends com.google.gson.reflect.TypeToken

# GSON
-keep class com.google.gson.reflect.TypeToken { *; }
-keep class * extends com.google.gson.reflect.TypeToken

# Keep generic signature of RxJava3 (R8 full mode strips signatures from non-kept items).
-keep,allowobfuscation,allowshrinking class io.reactivex.rxjava3.core.Single
-keep,allowobfuscation,allowshrinking class io.reactivex.rxjava3.core.Flowable
-keep,allowobfuscation,allowshrinking class io.reactivex.rxjava3.core.Observable
-keep,allowobfuscation,allowshrinking class io.reactivex.rxjava3.core.Completable


-keep,allowobfuscation,allowshrinking interface retrofit2.Call
-keep,allowobfuscation,allowshrinking class retrofit2.Response
-keep,allowobfuscation,allowshrinking class kotlin.coroutines.Continuation

-dontwarn org.bouncycastle.jsse.BCSSLParameters
-dontwarn org.bouncycastle.jsse.BCSSLSocket
-dontwarn org.bouncycastle.jsse.provider.BouncyCastleJsseProvider
-dontwarn org.conscrypt.Conscrypt$Version
-dontwarn org.conscrypt.Conscrypt
-dontwarn org.conscrypt.ConscryptHostnameVerifier
-dontwarn org.openjsse.javax.net.ssl.SSLParameters
-dontwarn org.openjsse.javax.net.ssl.SSLSocket
-dontwarn org.openjsse.net.ssl.OpenJSSE

-keepattributes Signature
-keepattributes *Annotation*

#----------------android-----------------
-dontwarn android.**
-keep class android.** { *;}
#----------------v4-----------------
-dontwarn android.support.v4.**
-keep class android.support.v4.** { *; }
#----------------v7-----------------
-dontwarn android.support.v7.**
-keep class android.support.v7.** { *;}

#----------------------Android通用-----------------

# 避免混淆Android基本组件，下面是兼容性比较高的规则
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Fragment
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class * extends android.view.View
-keep public class com.android.vending.licensing.ILicensingService

# 保留support下的所有类及其内部类
-keep class android.support.** {*;}
-keep interface android.support.** {*;}
-keep public class * extends android.support.v4.**
-keep public class * extends android.support.v7.**
-keep public class * extends android.support.annotation.**
-dontwarn android.support.**

# 保留androidx下的所有类及其内部类
-keep class androidx.** {*;}
-keep public class * extends androidx.**
-keep interface androidx.** {*;}
-keep class com.google.android.material.** {*;}
-dontwarn androidx.**
-dontwarn com.google.android.material.**
-dontnote com.google.android.material.**

# 保持Activity中与View相关方法不被混淆
-keepclassmembers class * extends android.app.Activity{
        public void *(android.view.View);
}

# 避免混淆所有native的方法,涉及到C、C++
-keepclasseswithmembernames class * {
        native <methods>;
}

# 避免混淆自定义控件类的get/set方法和构造函数
-keep public class * extends android.view.View{
        *** get*();
        void set*(***);
        public <init>(android.content.Context);
        public <init>(android.content.Context,android.util.AttributeSet);
        public <init>(android.content.Context,android.util.AttributeSet,int);
}
-keepclasseswithmembers class * {
        public <init>(android.content.Context, android.util.AttributeSet);
        public <init>(android.content.Context, android.util.AttributeSet, int);
}

# 避免混淆枚举类
-keepclassmembers enum * {
        public static **[] values();
        public static ** valueOf(java.lang.String);
}

# 避免混淆序列化类
# 不混淆Parcelable和它的实现子类，还有Creator成员变量
-keep class * implements android.os.Parcelable {
        public static final android.os.Parcelable$Creator *;
}

# 不混淆Serializable和它的实现子类、其成员变量
-keep public class * implements java.io.Serializable {*;}
-keepclassmembers class * implements java.io.Serializable {
        static final long serialVersionUID;
        private static final java.io.ObjectStreamField[] serialPersistentFields;
        private void writeObject(java.io.ObjectOutputStream);
        private void readObject(java.io.ObjectInputStream);
        java.lang.Object writeReplace();
        java.lang.Object readResolve();
}

# 资源ID不被混淆
-keep class **.R$* {*;}

# 回调函数事件不能混淆
-keepclassmembers class * {
        void *(**On*Event);
        void *(**On*Listener);
}

# Webview 相关不混淆
-keepclassmembers class fqcn.of.javascript.interface.for.webview {
   public *;
}
-keepclassmembers class * extends android.webkit.WebViewClient {
        public void *(android.webkit.WebView, java.lang.String, android.graphics.Bitmap);
        public boolean *(android.webkit.WebView, java.lang.String);
}
-keepclassmembers class * extends android.webkit.WebViewClient {
        public void *(android.webkit.WebView, java.lang.String);
 }

# 使用GSON、fastjson等框架时，所写的JSON对象类不混淆，否则无法将JSON解析成对应的对象
-keepclassmembers class * {
         public <init>(org.json.JSONObject);
}

#不混淆泛型
-keepattributes Signature

#避免混淆注解类
-dontwarn android.annotation
-keepattributes *Annotation*

#避免混淆内部类
-keepattributes InnerClasses

#（可选）避免Log打印输出
-assumenosideeffects class android.util.Log {
        public static *** v(...);
        public static *** d(...);
        public static *** i(...);
        public static *** w(...);
        public static *** e(...);
}

#kotlin 相关
-dontwarn kotlin.**
-keep class kotlin.** { *; }
-keep interface kotlin.** { *; }
-keepclassmembers class kotlin.Metadata {
    public <methods>;
}
-keepclasseswithmembers @kotlin.Metadata class * { *; }
-keepclassmembers class **.WhenMappings {
    <fields>;
}
-assumenosideeffects class kotlin.jvm.internal.Intrinsics {
    static void checkParameterIsNotNull(java.lang.Object, java.lang.String);
}

-keep class kotlinx.** { *; }
-keep interface kotlinx.** { *; }
-dontwarn kotlinx.**
-dontnote kotlinx.serialization.SerializationKt

-keep class org.jetbrains.** { *; }
-keep interface org.jetbrains.** { *; }
-dontwarn org.jetbrains.**



#Eventbus
-keepattributes *Annotation*
-keepclassmembers class ** {
    @org.greenrobot.eventbus.Subscribe <methods>;
}
-keep enum org.greenrobot.eventbus.ThreadMode { *; }

# Only required if you use AsyncExecutor
-keepclassmembers class * extends org.greenrobot.eventbus.util.ThrowableFailureEvent {
    <init>(java.lang.Throwable);
}

# 防止安全联盟SDK混淆
-keep class com.bun.miitmdid.core.** {*;}

# 防止沉浸式状态栏控件混淆
-keep class com.readystatesoftware.systembartint.** {*;}

#-------------云信相关的混淆配置------------
-dontwarn com.netease.**
-keep class com.netease.** {*;}
#如果你使用全文检索插件，需要加入
-dontwarn org.apache.lucene.**
-keep class org.apache.lucene.** {*;}
-keep class com.peach.mobile.im.holder.** {*;}
#-------------云信相关的混淆配置------------


#-------------TakePhoto的混淆配置------------
-keep class com.jph.takephoto.** { *; }
-dontwarn com.jph.takephoto.**

-keep class com.darsh.multipleimageselect.** { *; }
-dontwarn com.darsh.multipleimageselect.**

-keep class soundcloud.android.crop.** { *; }
-dontwarn com.soundcloud.android.crop.**

#-------------TakePhoto的混淆配置------------

#腾讯崩溃收集
-dontwarn com.tencent.bugly.**
-keep public class com.tencent.bugly.**{*;}

# glide4.0
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public class * extends com.bumptech.glide.module.AppGlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}
# for DexGuard only
#-keepresourcexmlelements manifest/application/meta-data@value=GlideModule

# BaseAdapter
-keep class com.chad.library.adapter.** {
*;
}
-keep public class * extends com.chad.library.adapter.base.BaseQuickAdapter
-keep public class * extends com.chad.library.adapter.base.BaseViewHolder
-keepclassmembers  class **$** extends com.chad.library.adapter.base.BaseViewHolder {
     <init>(...);
}

# 内部WebView混淆过滤
-keepclassmembers class * {
    @android.webkit.JavascriptInterface <methods>;
}

-keep class org.json.** {*;}

-dontwarn com.peach.mobile.base.bindadapter.**
-keep class com.peach.mobile.utils.db.** {*;}

-dontwarn com.peach.mobile.presenter.**
-keep class com.peach.mobile.presenter.** {*;}
-dontwarn com.peach.mobile.room.widget.dialog.giftrecorddialog.dialogpresenter.**
-keep class com.peach.mobile.room.dialog.giftrecorddialog.dialogpresenter.** {*;}
#-keep class dtsbile.ui.newfind.** {*;}

# 网络加载
-dontwarn com.peach.core.**
-keep class com.peach.core.** {*;}

-keep class com.baidu.bottom.** { *; }
-keep class com.baidu.kirin.** { *; }
-keep class com.baidu.mobstat.** { *; }

# fastjson
-dontwarn com.alibaba.fastjson.**
-keep class com.alibaba.fastjson.**{*; }

# retrofit2
# Platform calls Class.forName on types which do not exist on Android to determine platform.
-dontnote retrofit2.Platform
# Platform used when running on Java 8 VMs. Will not be used at runtime.
-dontwarn retrofit2.Platform$Java8
# Retain declared checked exceptions for use by a Proxy instance.
-keepattributes Exceptions

# okhttp3
-dontwarn okhttp3.**
#  okio
-dontwarn okio.**
# A resource is loaded with a relative path so the package of this class must be preserved.
-keepnames class okhttp3.internal.publicsuffix.PublicSuffixDatabase

#svga
-keep class io.agora.** { *; }
-keep class com.squareup.wire.** { *; }
-keep class com.opensource.svgaplayer.proto.** { *; }

