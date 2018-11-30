package com.github.ajalt.colormath

object Mixer {

    fun mix(a: CMYK, b: CMYK, percent: Double): CMYK {
        requirePercent(percent)

        return CMYK(
                c = (a.c..b.c).mix(percent),
                m = (a.m..b.m).mix(percent),
                y = (a.y..b.y).mix(percent),
                k = (a.k..b.k).mix(percent)
        )
    }

    fun mix(a: HSV, b: HSV, percent: Double): HSV {
        requirePercent(percent)

        return HSV(
                h = (a.h..b.h).mix(percent),
                s = (a.s..b.s).mix(percent),
                v = (a.v..b.v).mix(percent)
        )
    }

    fun mix(a: HSL, b: HSL, percent: Double): HSL {
        requirePercent(percent)

        return HSL(
                h = (a.h..b.h).mix(percent),
                s = (a.s..b.s).mix(percent),
                l = (a.l..b.l).mix(percent)
        )
    }

    fun mix(a: LAB, b: LAB, percent: Double): LAB {
        requirePercent(percent)

        return LAB(
                l = (a.l..b.l).mix(percent),
                a = (a.a..b.a).mix(percent),
                b = (a.b..b.b).mix(percent)
        )
    }

    fun mix(a: RGB, b: RGB, percent: Double): RGB {
        requirePercent(percent)

        return RGB(
                r = (a.r..b.r).mix(percent),
                g = (a.g..b.g).mix(percent),
                b = (a.b..b.b).mix(percent)
        )
    }

    fun mix(a: XYZ, b: XYZ, percent: Double): XYZ {
        requirePercent(percent)

        return XYZ(
                x = (a.x..b.x).mix(percent),
                y = (a.y..b.y).mix(percent),
                z = (a.z..b.z).mix(percent)
        )
    }

}