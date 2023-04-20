package lee.dorian.android.room_ex04

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import lee.dorian.android.room_common.AppDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    @HiltQualifier.AppDatabase
    fun providesAppDatabase(app: Application): AppDatabase {
        return AppDatabase.getInstance(app)
    }

}