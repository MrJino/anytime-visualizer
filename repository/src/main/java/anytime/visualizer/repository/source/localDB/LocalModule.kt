package anytime.visualizer.repository.source.localDB

import android.content.Context
import androidx.room.Room
import anytime.visualizer.repository.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalModule {

    @Singleton
    @Provides
    fun provideLocalAudioDatabase(
        @ApplicationContext context: Context
    ): LocalAudioDatabase {
        return Room.databaseBuilder(
            context,
            LocalAudioDatabase::class.java,
            DATABASE_NAME)
            .build()
    }
}