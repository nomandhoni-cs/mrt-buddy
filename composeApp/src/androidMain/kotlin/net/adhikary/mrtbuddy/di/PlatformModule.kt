package net.adhikary.mrtbuddy.di

import net.adhikary.mrtbuddy.database.DatabaseProvider
import net.adhikary.mrtbuddy.utils.AndroidFileSaver
import net.adhikary.mrtbuddy.utils.FileSaver
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

actual val platformModule = module {
    single { DatabaseProvider(androidContext()).getDatabase() }
    single<FileSaver> { AndroidFileSaver(androidContext()) }
}
