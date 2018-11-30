package com.github.ajalt.colormath

object Mixer {

    /**
     * Mixes two colors by weighting them component-wise. The first color parameter type
     * determines how the colors are going to be mixed.
     *
     * E.g.: if first is RGB, then red, green and blue channels will be calculated separately.
     * E.g.: if first is HSV, then hue, saturation and value are calculate separately and then converted back to RGB.
     *
     * Second color is converted to match the first color's type.
     *
     * If there is no applicable mixing for the first color type, an error is thrown.
     */
    fun mix(a: ConvertibleColor, b: ConvertibleColor, percent: Double): RGB {
        requirePercent(percent)

        return when (a) {
            is CMYK -> mixCMYK(a, b.toCMYK(), percent).toRGB()
            is HSV -> mixHSV(a, b.toHSV(), percent).toRGB()
            is HSL -> mixHSL(a, b.toHSL(), percent).toRGB()
            is LAB -> mixLAB(a, b.toLAB(), percent).toRGB()
            is RGB -> mixRGB(a, b.toRGB(), percent)
            is XYZ -> mixXYZ(a, b.toXYZ(), percent).toRGB()
            else -> throw IllegalArgumentException("Mix calculation for class ${a::class} is not supported.")
        }
    }

    private fun mixCMYK(a: CMYK, b: CMYK, percent: Double): CMYK {
        requirePercent(percent)

        return CMYK(
                c = (a.c..b.c).mix(percent),
                m = (a.m..b.m).mix(percent),
                y = (a.y..b.y).mix(percent),
                k = (a.k..b.k).mix(percent)
        )
    }

    private fun mixHSV(a: HSV, b: HSV, percent: Double): HSV {
        requirePercent(percent)

        return HSV(
                h = (a.h..b.h).mix(percent),
                s = (a.s..b.s).mix(percent),
                v = (a.v..b.v).mix(percent)
        )
    }

    private fun mixHSL(a: HSL, b: HSL, percent: Double): HSL {
        requirePercent(percent)

        return HSL(
                h = (a.h..b.h).mix(percent),
                s = (a.s..b.s).mix(percent),
                l = (a.l..b.l).mix(percent)
        )
    }

    private fun mixLAB(a: LAB, b: LAB, percent: Double): LAB {
        requirePercent(percent)

        return LAB(
                l = (a.l..b.l).mix(percent),
                a = (a.a..b.a).mix(percent),
                b = (a.b..b.b).mix(percent)
        )
    }

    private fun mixRGB(a: RGB, b: RGB, percent: Double): RGB {
        requirePercent(percent)

        return RGB(
                r = (a.r..b.r).mix(percent),
                g = (a.g..b.g).mix(percent),
                b = (a.b..b.b).mix(percent)
        )
    }

    private fun mixXYZ(a: XYZ, b: XYZ, percent: Double): XYZ {
        requirePercent(percent)

        return XYZ(
                x = (a.x..b.x).mix(percent),
                y = (a.y..b.y).mix(percent),
                z = (a.z..b.z).mix(percent)
        )
    }

}