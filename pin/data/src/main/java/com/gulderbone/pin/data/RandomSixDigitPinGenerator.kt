package com.gulderbone.pin.data

import com.gulderbone.pin.domain.PinGenerator
import javax.inject.Inject
import kotlin.random.Random

internal class RandomSixDigitPinGenerator @Inject constructor(): PinGenerator {

    override fun generate(): Long = Random.nextLong(100000, 1000000)
}