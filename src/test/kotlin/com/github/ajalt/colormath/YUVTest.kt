package com.github.ajalt.colormath

import io.kotlintest.data.forall
import io.kotlintest.matchers.plusOrMinus
import io.kotlintest.properties.forAll
import io.kotlintest.shouldBe
import io.kotlintest.tables.row
import org.junit.Test

class YUVTest {

    @Test
    fun `YUV to RGB`() {
        listOf(
                YUV(0.5, 0.02, -0.6),
                YUV(0.5, 0.42, -0.3),
                YUV(0.15, 0.2, 0.5),
                YUV(0.5, -0.39, -0.3)
        )
                .map { it to it.toRGB() }
                .forEach<Pair<YUV, RGB>> { (yuv, rgb) ->
                    val yuv2 = rgb.toYUV()

                    yuv.y shouldBe (yuv2.y plusOrMinus 0.01)
                    yuv.u shouldBe (yuv2.u plusOrMinus 0.01)
                    yuv.v shouldBe (yuv2.v plusOrMinus 0.01)
                }
    }

}