package anytime.visualizer.repository

import anytime.visualizer.repository.source.storage.StorageDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideRepositoryApi(
        storageDataSource: StorageDataSource
    ) : RepositoryApi {
        return RepositoryService(storageDataSource)
    }
}