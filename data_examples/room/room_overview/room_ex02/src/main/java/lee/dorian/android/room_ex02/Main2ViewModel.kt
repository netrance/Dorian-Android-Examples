package lee.dorian.android.room_ex02

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import lee.dorian.android.room_common.AppDatabase
import lee.dorian.android.room_common.Member
import lee.dorian.android.room_common.emitAndSave

class Main2ViewModel(
    app: Application,
    private val savedStateHandle: SavedStateHandle
) : AndroidViewModel(app) {

    // Keys of saved state
    companion object {
        const val KEY_MEMBER_LIST = "KEY_MEMBER_LIST"
    }

    private val _flowMemberList: MutableStateFlow<List<Member>> by lazy {
        MutableStateFlow(savedStateHandle.get<List<Member>>(KEY_MEMBER_LIST) ?: listOf())
    }
    val flowMemberList = _flowMemberList.asStateFlow()

    private val _flowInsertedRowID = MutableSharedFlow<Long>()
    val flowInsertedRowID = _flowInsertedRowID.asSharedFlow()

    private val db = AppDatabase.getInstance(app)
    private val memberDao = db.memberDao()

    suspend fun insert(member: Member) = viewModelScope.launch(Dispatchers.IO) {
        val insertedRowID = memberDao.insertMember(member)
        _flowInsertedRowID.emit(insertedRowID)
    }

    fun delete(member: Member) = viewModelScope.launch(Dispatchers.IO) {
        memberDao.deleteMember(member)
    }

    suspend fun search(name: String, point: String) = viewModelScope.launch(Dispatchers.IO) {
        val newMemberList: List<Member> = when {
            (name.isEmpty() && point.isEmpty()) -> memberDao.selectAll()
            (name.isNotEmpty() && point.isEmpty()) -> memberDao.selectByName(name)
            (name.isEmpty() && point.isNotEmpty()) -> memberDao.selectByPoint(point.toInt())
            else -> memberDao.select(name, point.toInt())
        }

        _flowMemberList.emitAndSave(newMemberList, KEY_MEMBER_LIST, savedStateHandle)
    }

}