package lee.dorian.android.sqlite_overview

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase

class MemberDAO(val dbHelper: SampleSQLiteOpenHelper) {

    fun insert(member: Member): Long {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(MemberContract.MemberEntry.COLUMN_NAME_NAME, member.name)
            put(MemberContract.MemberEntry.COLUMN_NAME_POINT, member.point)
        }

        return db.insert(MemberContract.MemberEntry.TABLE_NAME, null, values)
    }

    fun update(member: Member): Int {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(MemberContract.MemberEntry.COLUMN_NAME_NAME, member.name)
            put(MemberContract.MemberEntry.COLUMN_NAME_POINT, member.point)
        }

        return db.update(
                MemberContract.MemberEntry.TABLE_NAME,
                values,
                MemberContract.SQL_SELECTION_OF_UPDATE_MEMBER,
                arrayOf(member.id.toString())
        )
    }

    fun delete(_id: Long): Int {
        val db = dbHelper.writableDatabase

        return db.delete(
                MemberContract.MemberEntry.TABLE_NAME,
                MemberContract.SQL_SELECTION_OF_DELETE_MEMBER_BY_ID,
                arrayOf(_id.toString())
        )
    }

    fun select(): List<Member> {
        val db = dbHelper.readableDatabase
        val cursor = db.query(
                MemberContract.MemberEntry.TABLE_NAME,
                MemberContract.SQL_PROJECTION_OF_SELECT,
                null,
                null,
                null,
                null,
                MemberContract.SQL_ORDER_BY_NAME_ASC
        )

        return Member.getFrom(cursor)
    }

    fun select(name: String): List<Member> {
        val db = dbHelper.readableDatabase
        val cursor = db.query(
                MemberContract.MemberEntry.TABLE_NAME,
                MemberContract.SQL_PROJECTION_OF_SELECT,
                MemberContract.SQL_SELECTION_BY_NAME,
                arrayOf("%${name}%"),
                null,
                null,
                MemberContract.SQL_ORDER_BY_NAME_ASC
        )

        return Member.getFrom(cursor)
    }

    fun select(point: Int): List<Member> {
        val db = dbHelper.readableDatabase
        val cursor = db.query(
                MemberContract.MemberEntry.TABLE_NAME,
                MemberContract.SQL_PROJECTION_OF_SELECT,
                MemberContract.SQL_SELECTION_BY_POINT,
                arrayOf(point.toString()),
                null,
                null,
                MemberContract.SQL_ORDER_BY_NAME_ASC
        )

        return Member.getFrom(cursor)
    }

    fun select(name: String, point: Int): List<Member> {
        val db = dbHelper.readableDatabase
        val cursor = db.query(
                MemberContract.MemberEntry.TABLE_NAME,
                MemberContract.SQL_PROJECTION_OF_SELECT,
                MemberContract.SQL_SELECTION_BY_NAME_OR_POINT,
                arrayOf("%${name}%", point.toString()),
                null,
                null,
                MemberContract.SQL_ORDER_BY_NAME_ASC
        )

        return Member.getFrom(cursor)
    }

    companion object {
        fun createMemberTable(db: SQLiteDatabase) {
            db.execSQL(MemberContract.SQL_CREATE_MEMBER_TABLE)
        }
    }
}