package com.gulderbone.core.presentation.ui

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class MutableEventFlow<T> : MutableSharedFlow<T> {
    private val delegate: MutableStateFlow<List<T>> = MutableStateFlow(emptyList())

    override val replayCache: List<T>
        get() = delegate.replayCache.flatten()

    override val subscriptionCount: StateFlow<Int>
        get() = delegate.subscriptionCount

    @Suppress("UNREACHABLE_CODE")
    override suspend fun collect(collector: FlowCollector<T>): Nothing {
        delegate.collect {
            if (it.isNotEmpty()) {
                collector.emit(it.first())
                delegate.update { currentValue ->
                    currentValue.drop(1)
                }
            }
        }
        error("Will not happen")
    }

    @ExperimentalCoroutinesApi
    override fun resetReplayCache() {
        delegate.resetReplayCache()
    }

    override fun tryEmit(value: T): Boolean {
        delegate.update { currentCache ->
            currentCache + value
        }
        return true
    }

    override suspend fun emit(value: T) {
        delegate.update { currentCache ->
            currentCache + value
        }
    }
}