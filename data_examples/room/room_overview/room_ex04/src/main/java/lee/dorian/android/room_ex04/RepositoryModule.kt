package lee.dorian.android.room_ex04

import android.app.Application
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import lee.dorian.android.room_common.AppDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    @HiltQualifier.MemberRepositoryImpl
    abstract fun providesMemberRepository(memberRepositoryImpl: MemberRepositoryImpl): MemberRepository

}