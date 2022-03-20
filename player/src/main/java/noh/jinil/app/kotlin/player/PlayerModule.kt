package noh.jinil.app.kotlin.player

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PlayerModule {

    @Singleton
    @Provides
    fun providePlayerApi(
        @ApplicationContext context: Context
    ) : PlayerApi {
        return ExoPlayerService(context)
    }
}