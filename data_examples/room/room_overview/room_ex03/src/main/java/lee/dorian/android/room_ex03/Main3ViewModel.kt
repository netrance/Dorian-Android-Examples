package lee.dorian.android.room_ex03

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import lee.dorian.android.room_common.Member
import lee.dorian.android.room_common.emitAndSave

class Main3ViewModel(
    private val app: Application,
    private val savedStateHandle: SavedStateHandle
) : AndroidViewModel(app) {

    // Keys of saved state
    companion object {
        const val KEY_MEMBER_LIST = "KEY_MEMBER_LIST"
    }

    private val memberRepository: MemberRepository = MemberRepositoryImpl(app)

    private val _flowMemberList= MutableStateFlow(savedStateHandle.get<List<Member>>(KEY_MEMBER_LIST) ?: listOf())
    val flowMemberList = _flowMemberList.asStateFlow()

    private val _flowInsertedRowID = MutableSharedFlow<Long>()
    val flowInsertedRowID = _flowInsertedRowID.asSharedFlow()

    fun insert(member: Member) = viewModelScope.launch {
        val insertedRowID = memberRepository.insert(member)
        _flowInsertedRowID.emit(insertedRowID)
    }

    fun delete(member: Member) = viewModelScope.launch {
        memberRepository.delete(member)
    }

    fun search(name: String, point: String) = viewModelScope.launch {
        val newMemberList: List<Member> = memberRepository.search(name, point)
        _flowMemberList.emitAndSave(newMemberList, KEY_MEMBER_LIST, savedStateHandle)
    }

}