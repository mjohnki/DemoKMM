package de.johnki.kmmtest.android

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import de.johnki.kmmtest.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SharedSingletonModule {

    @Singleton
    @Provides
    fun provideApi(): Api {
        return Api()
    }

    @Provides
    fun provideDriverFactory(@ApplicationContext context: Context): DriverFactory {
        return DriverFactory(context);
    }

    @Singleton
    @Provides
    fun provideIssDatabase(driver: DriverFactory): IssDatabase {
        return IssDatabase(driver)
    }

    @Singleton
    @Provides
    fun provideIssRepository(database: IssDatabase, api: Api): IssRepository {
        return IssRepository(database, api)
    }
}

@Module
@InstallIn(ActivityComponent::class)
object SharedModule {

    @Provides
    fun provideGetIssUseCase(repo: IssRepository): GetIssUseCase {
        return GetIssUseCase(repo)
    }

    @Provides
    fun provideUpdateIssUseCase(repo: IssRepository): UpdateIssUseCase {
        return UpdateIssUseCase(repo)
    }

}