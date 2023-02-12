package com.shoppi.app.ui.common

import androidx.lifecycle.Observer

/*
* architecture 참고 사이트
* https://github.com/android/architecture-samples/blob/dev-dagger/app/src/main/java/com/example/android/architecture/blueprints/todoapp/Event.kt
* */

class Event<T>(private val content: T) {

    private var hasBeenHandled = false

    fun getContentIfNotHandled(): T? {
        return if(hasBeenHandled) {
            null
        }else {
            hasBeenHandled = true
            content
        }
    }
}

class EventObserver<T>(private val onEventUnHandledContent: (T) -> Unit) : Observer<Event<T>> {
    override fun onChanged(event: Event<T>?) {
        event?.getContentIfNotHandled()?.let {
            onEventUnHandledContent(it)
        }
    }

}
