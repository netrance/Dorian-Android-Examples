package lee.dorian.android.room_common

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Member::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun memberDao(): MemberDao

    companion object {
        const val dbName = "db-member"

        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return when {
                (null != instance) -> instance!!
                else -> synchronized(this) {
                    Room.databaseBuilder(
                        context,
                        AppDatabase::class.java,
                        dbName
                    ).build()
                }
            }
        }
    }
}