package net.adhikary.mrtbuddy.di

import net.adhikary.mrtbuddy.database.DatabaseProvider
import net.adhikary.mrtbuddy.utils.FileSaver
import net.adhikary.mrtbuddy.utils.IOSFileSaver
import org.koin.dsl.module

actual val platformModule = module {
    single { DatabaseProvider().getDatabase() }
    single<FileSaver> { IOSFileSaver() }
}
