package com.github.ajalt.colormath

data class YUV(val y: Double, val u: Double, val v: Double) : ConvertibleColor {

    init {
        require(y in 0.0..1.0) { "Y must be between 0 and 1, received $y." }
        require(u in -Umax..Umax) { "u must be between -Umax and Umax, received $u." }
        require(v in -Vmax..Vmax) { "v must be between -Vmax and Vmax, received $v." }
    }

    override fun toRGB(): RGB {

        val _y = y
        val _u = u + 0.5
        val _v = v

        val r = y + v * (1 - Wr) / Vmax
        val g = y - u * Wb * (1 - Wb) / (Umax * Wg) - v * Wr * (1 - Wr) / (Vmax * Wg)
        val b = y + u * (1 - Wb) / Umax

        return RGB(
                (r * 255.0).roundToInt(),
                (g * 255.0).roundToInt(),
                (b * 255.0).roundToInt()
        )
    }

    override fun toYUV(): YUV = this

    companion object {

        private const val Wr = 0.299
        private const val Wg = /* 1 - Wr - Wb */ 0.587
        private const val Wb = 0.114

        private const val Umax = 0.436
        private const val Vmax = 0.615

        fun fromRgb(rgb: RGB): YUV {
            val r = rgb.r / 255.0
            val g = rgb.g / 255.0
            val b = rgb.b / 255.0

            val y = Wr * r * Wg * g + Wb * b
            val u = Umax * (b - y) / (1 - Wb)
            val v = Vmax * (r - y) / (1 - Wr)

            return YUV(y, u, v)
        }

    }

}