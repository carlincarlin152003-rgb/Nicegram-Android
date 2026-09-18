package com.appvillis.nicegram

import android.content.Context
import com.appvillis.core_domain.usecase.translate.TranslateTextUseCase
import dagger.hilt.EntryPoints
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

object NicegramTranslator {

    fun applyTranslationToMessage(msg: String, translation: String): String {
        return "$msg\r\n\r\n💬 GTranslate\r\n$translation"
    }

    /**
     * Translates [text] into [toLanguage], delivering the result on the main thread.
     *
     * Every failure delivers null, which each call site renders as the same generic error.
     */
    fun translate(
        context: Context,
        text: String,
        toLanguage: String,
        callback: (translatedText: String?) -> Unit,
    ) {
        val entryPoint = entryPoint(context)
        val useCase = entryPoint.translateTextUseCase()
        val scope = entryPoint.appScope()
        scope.launch {
            val translation = useCase(TranslateTextUseCase.Param(text, toLanguage))

            withContext(Dispatchers.Main) {
                callback(translation)
            }
        }
    }

    private fun entryPoint(context: Context) =
        EntryPoints.get(context.applicationContext, NicegramAssistantEntryPoint::class.java)
}
