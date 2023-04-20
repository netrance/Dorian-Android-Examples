package lee.dorian.android.room_ex04

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
object CoroutineDispatcherModule {

    @Provides
    @HiltQualifier.CoroutineDispatcherIO
    fun provideCoroutineDispatherIO(): CoroutineDispatcher = Dispatchers.IO

}