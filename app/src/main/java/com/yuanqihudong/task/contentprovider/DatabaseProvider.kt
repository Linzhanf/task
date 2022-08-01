package com.yuanqihudong.task.contentprovider

import android.content.*
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.net.Uri
import android.util.Log

class DatabaseProvider : ContentProvider() {

    companion object {
        const val AUTOHORITY = "cn.scu.myprovider"
        const val User_Code = 1
        const val Job_Code = 2
        var matcher: UriMatcher? = null
    }

    init {
        matcher = UriMatcher(UriMatcher.NO_MATCH)
        matcher?.let {
            // 若URI资源路径 = content://cn.scu.myprovider/user ，则返回注册码User_Code
            it.addURI(AUTOHORITY, "user", User_Code)
            it.addURI(AUTOHORITY, "job", Job_Code)
        }
    }

    var mDBHelper: DBHelper? = null
    var db: SQLiteDatabase? = null

    override fun onCreate(): Boolean {
        // 在ContentProvider创建时对数据库进行初始化
        // 运行在主线程，故不能做耗时操作,此处仅作展示
        mDBHelper = DBHelper(context)
        db = mDBHelper?.writableDatabase
        db?.apply {
            execSQL("delete from user")
            execSQL("insert into user values(1, 'linzf');")
            execSQL("insert into user values(2, 'Kobe');")

            execSQL("delete from job")
            execSQL("insert into job values(1, 'Android')")
            execSQL("insert into job values(2, 'iOS')")
        }
        return true
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri {
        val table = getTabName(uri)
        db?.insert(table, null, values)
        context?.contentResolver?.notifyChange(uri, null)
        Log.i(DatabaseProvider::class.simpleName, "insert: $uri")
        return uri
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? =
        db?.query(
            getTabName(uri),
            projection,
            selection,
            selectionArgs,
            null,
            null,
            sortOrder,
            null
        )

    override fun getType(uri: Uri): String? = null

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int = 0

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int = 0

    private fun getTabName(uri: Uri): String? =
        when (matcher?.match(uri)) {
            User_Code -> DBHelper.USER_TABLE_NAME
            Job_Code -> DBHelper.JOB_TABLE_NAME
            else -> null
        }
}

