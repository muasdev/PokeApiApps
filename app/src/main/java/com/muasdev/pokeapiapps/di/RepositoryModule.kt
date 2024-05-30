package com.muasdev.pokeapiapps.di

import com.muasdev.pokeapiapps.data.RepoImpl
import com.muasdev.pokeapiapps.domain.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindRepository(repoImpl: RepoImpl): Repository

}