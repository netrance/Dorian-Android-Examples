package lee.dorian.android.room_ex04

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import lee.dorian.android.room_common.Member
import javax.inject.Inject

@HiltViewModel
class EditMember4ViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    @HiltQualifier.MemberRepositoryImpl
    private val memberRepository: MemberRepository
) : ViewModel() {

    // Keys of saved state
    companion object {
        const val KEY_MEMBER = "KEY_MEMBER"
    }

    var memberToEdit: Member?
        get() = (savedStateHandle.get<Member>(KEY_MEMBER))
        set(value) {
            savedStateHandle[KEY_MEMBER] = value
        }

    fun update(member: Member) = viewModelScope.launch {
        memberRepository.update(member)
    }

}