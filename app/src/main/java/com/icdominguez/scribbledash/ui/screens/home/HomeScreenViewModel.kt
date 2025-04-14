package com.icdominguez.scribbledash.ui.screens.home

import com.icdominguez.scribbledash.core.MviViewModel

class HomeScreenViewModel : MviViewModel<HomeScreenViewModel.State, HomeScreenViewModel.Event>() {

    data class State(
        val isLoading: Boolean = false,
    )

    override var currentState: State = State()

    sealed class Event {
        data object IsLoading: Event()
    }

    override fun uiEvent(event: Event) {
        super.uiEvent(event)

        when(event) {
            Event.IsLoading -> updateState { copy(isLoading = true) }
        }
    }
}