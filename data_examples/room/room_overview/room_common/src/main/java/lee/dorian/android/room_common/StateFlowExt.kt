package lee.dorian.android.room_common

import androidx.lifecycle.SavedStateHandle
import kotlinx.coroutines.flow.MutableStateFlow

// To save value into SavedStateHandle when emitting it.
suspend fun<T> MutableStateFlow<T>.emitAndSave(
    value: T,
    key: String,
    savedStateHandle: SavedStateHandle
) {
    savedStateHandle[key] = value
    emit(value)
}