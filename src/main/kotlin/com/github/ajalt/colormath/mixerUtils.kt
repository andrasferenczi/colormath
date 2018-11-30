package com.github.ajalt.colormath

internal fun ClosedRange<Int>.mix(percent: Double): Int {
    requirePercent(percent)
    return mix(this.start, this.endInclusive, percent)
}

internal fun ClosedRange<Double>.mix(percent: Double): Double {
    requirePercent(percent)
    return mix(this.start, this.endInclusive, percent)
}

internal fun mix(x: Int, y: Int, percent: Double): Int {
    requirePercent(percent)
    return (x * (1.0 - percent) + y * percent).roundToInt()
}

internal fun mix(x: Double, y: Double, percent: Double): Double {
    requirePercent(percent)
    return x * (1.0 - percent) + y * percent
}

internal fun requirePercent(percent: Double) = require(percent in 0.0..1.0) { "Percent must be in interval [0, 1]." }