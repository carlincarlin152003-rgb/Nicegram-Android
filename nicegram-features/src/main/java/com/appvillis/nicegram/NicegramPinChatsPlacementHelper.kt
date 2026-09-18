package com.appvillis.nicegram

import android.content.Context
import com.appvillis.core_domain.usecase.call
import com.appvillis.core_domain.entry.placement.PinnedChatsPlacementEntry
import com.appvillis.core_domain.usecase.placement.SetPinChatsPlacementHiddenUseCase
import dagger.hilt.EntryPoints

object NicegramPinChatsPlacementHelper {
    private fun entryPoint(context: Context) = EntryPoints
        .get(context.applicationContext, NicegramAssistantEntryPoint::class.java)

    fun getPinChatsPlacements(context: Context) =
        entryPoint(context).getAllPinChatsPlacementsUseCase().call()

    fun isPinnedChatHidden(context: Context, pin: PinnedChatsPlacementEntry) =
        entryPoint(context).isPinChatsPlacementHiddenUseCase().invoke(pin)

    fun setPinnedChatHidden(context: Context, id: String, hidden: Boolean) {
        entryPoint(context).setPinChatsPlacementHiddenUseCase().invoke(
            SetPinChatsPlacementHiddenUseCase.Param(id = id, hidden = hidden)
        )
    }
}
