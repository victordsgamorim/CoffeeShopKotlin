package com.victor.coffeeshop_kotlin.ui

data class DataState<T>(
    var error: Event<StateError>? = null,
    var load: Loading = Loading(false),
    var data: Data<T>? = null
) {

    companion object {

        fun <T> error(response: Response): DataState<T> {
            return DataState(error = Event(StateError(response)))
        }

        fun <T> loading(isloading: Boolean): DataState<T> {
            return DataState(load = Loading(isLoading = isloading))
        }

        fun <T> data(data: T? = null): DataState<T> {
            return DataState(data = Data(Event.dataEvent(data)))
        }
    }
}