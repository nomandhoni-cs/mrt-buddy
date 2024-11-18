package net.adhikary.mrtbuddy.repository

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.russhwolf.settings.Settings
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import net.adhikary.mrtbuddy.Language

class SettingsRepository(private val settings: Settings) {
    private val _autoSaveEnabled = MutableStateFlow(settings.getBoolean(AUTO_SAVE_KEY, true))
    val autoSaveEnabled: StateFlow<Boolean> = _autoSaveEnabled.asStateFlow()

    private val _currentLanguage = MutableStateFlow(settings.getString(LANGUAGE_KEY, Language.English.isoFormat))
    val currentLanguage: StateFlow<String> = _currentLanguage.asStateFlow()

    private val _isDarkModeEnabled = MutableStateFlow(settings.getBoolean(DARK_MODE_KEY, false))
    val isDarkModeEnabled: StateFlow<Boolean> = _isDarkModeEnabled.asStateFlow()

    fun setAutoSave(enabled: Boolean) {
        settings.putBoolean(AUTO_SAVE_KEY, enabled)
        _autoSaveEnabled.value = enabled
    }

    fun setLanguage(language: String) {
        settings.putString(LANGUAGE_KEY, language)
        _currentLanguage.value = language
    }

    fun setDarkMode(enabled: Boolean) {
        settings.putBoolean(DARK_MODE_KEY, enabled)
        _isDarkModeEnabled.value = enabled
    }

    companion object {
        private const val AUTO_SAVE_KEY = "auto_save_enabled"
        private const val LANGUAGE_KEY = "app_language"
        private const val DARK_MODE_KEY = "dark_mode_enabled"
    }
}
