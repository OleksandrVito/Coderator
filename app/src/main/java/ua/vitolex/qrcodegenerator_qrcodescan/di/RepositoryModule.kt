package ua.vitolex.qrcodegenerator_qrcodescan.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import ua.vitolex.qrcodegenerator_qrcodescan.data.repo.MainRepoImpl
import ua.vitolex.qrcodegenerator_qrcodescan.domain.repo.MainRepo

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {
    @Binds
    @ViewModelScoped
    abstract fun bindMainRepo(
        mainRepoImpl: MainRepoImpl
    ): MainRepo
}