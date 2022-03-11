package com.abilashcse.birthdayapp.di

import com.abilashcse.birthdayapp.api.services.RandomUserAPIServices
import com.abilashcse.birthdayapp.api.remotedatasource.RandomUserRemoteDataSource
import com.abilashcse.birthdayapp.data.repo.RandomUsersRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RandomUserModule {
    @Provides
    @Singleton
    fun provideRandomUserDataSource(randomUserAPIService: RandomUserAPIServices) : RandomUserRemoteDataSource {
        return RandomUserRemoteDataSource(randomUserAPIService)
    }
    @Provides
    @Singleton
    fun provideStandingsRepository(randomUserDataSource: RandomUserRemoteDataSource): RandomUsersRepository {
        return RandomUsersRepository(randomUserDataSource)
    }
}
