package lee.dorian.android.room_common

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "Member")
data class Member(
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "point") var point: Int
) : Serializable {

    @PrimaryKey(autoGenerate = true) var id: Long = 0

}