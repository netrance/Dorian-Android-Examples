package lee.dorian.android.sqlite_overview

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SampleSQLiteOpenHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VER) {

    companion object {
        private val DATABASE_NAME = "sample.db"
        private val DATABASE_VER = 1
    }

    override fun onCreate(db: SQLiteDatabase) {
        MemberDAO.createMemberTable(db)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
    }

    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
    }

//    private fun createMemberTable(db: SQLiteDatabase) {
//        db.execSQL(MemberContract.SQL_CREATE_MEMBER_TABLE)
//    }

}