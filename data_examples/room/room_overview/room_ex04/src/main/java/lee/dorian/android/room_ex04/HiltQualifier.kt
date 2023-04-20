package lee.dorian.android.room_ex04

import javax.inject.Qualifier

interface HiltQualifier {

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class MemberRepositoryImpl

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class AppDatabase

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class CoroutineDispatcherIO

}