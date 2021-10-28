package lee.dorian.android.sqlite_overview

import android.provider.BaseColumns

object MemberContract {

    object MemberEntry : BaseColumns {
        const val TABLE_NAME = "Member"
        const val COLUMN_NAME_NAME = "name"
        const val COLUMN_NAME_POINT = "point"
    }

    // ---------------------------------------------------------------------------------------------

    val SQL_CREATE_MEMBER_TABLE = """
        CREATE TABLE IF NOT EXISTS ${MemberEntry.TABLE_NAME} (
            ${BaseColumns._ID} INTEGER PRIMARY KEY,
            ${MemberEntry.COLUMN_NAME_NAME} TEXT,
            ${MemberEntry.COLUMN_NAME_POINT} INTEGER
        )
    """.trimIndent()

    // ---------------------------------------------------------------------------------------------

    val SQL_SELECTION_OF_UPDATE_MEMBER = "(_id = ?)"
    val SQL_SELECTION_OF_DELETE_MEMBER_BY_ID = "_ID = ?"

    // ---------------------------------------------------------------------------------------------

    val SQL_PROJECTION_OF_SELECT = arrayOf(
        BaseColumns._ID,
        MemberEntry.COLUMN_NAME_NAME,
        MemberEntry.COLUMN_NAME_POINT
    )

    val SQL_SELECTION_BY_NAME = "(name LIKE ?)"
    val SQL_SELECTION_BY_POINT = "(point = ?)"
    val SQL_SELECTION_BY_NAME_OR_POINT = "(name LIKE ?) AND (point = ?)"

    val SQL_ORDER_BY_NAME_ASC = "${MemberEntry.COLUMN_NAME_NAME} ASC"

}