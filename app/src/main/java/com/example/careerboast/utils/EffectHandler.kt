package com.example.careerboast.utils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.receiveAsFlow

interface EffectHandler<T> {

    val effectChannel: Channel<T>

    suspend fun sendEffect(effect : T) {
        effectChannel.send(effect)
    }

    val effectFlow : Flow<T>
        get() = effectChannel.receiveAsFlow().flowOn(Dispatchers.Main.immediate)

}