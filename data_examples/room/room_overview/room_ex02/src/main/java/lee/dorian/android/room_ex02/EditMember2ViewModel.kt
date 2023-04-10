package lee.dorian.android.room_ex02

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import lee.dorian.android.room_common.AppDatabase
import lee.dorian.android.room_common.Member

class EditMember2ViewModel(
    app: Application,
    private val savedStateHandle: SavedStateHandle
) : AndroidViewModel(app) {

    // Keys of saved state
    companion object {
        const val KEY_MEMBER = "KEY_MEMBER"
    }

    private val db = AppDatabase.getInstance(app)
    private val memberDao = db.memberDao()

    var memberToEdit: Member?
        get() = (savedStateHandle.get<Member>(KEY_MEMBER))
        set(value) {
            savedStateHandle[KEY_MEMBER] = value
        }

    fun update(member: Member) = viewModelScope.launch(Dispatchers.IO) {
        memberDao.updateMember(member)
    }

}