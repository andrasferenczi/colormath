package com.github.andras.palette

import com.github.ajalt.colormath.ConvertibleColor
import com.github.ajalt.colormath.Mixer
import com.github.ajalt.colormath.RGB
import kotlinx.html.*
import kotlinx.html.stream.appendHTML
import java.io.File


data class Palette(
        val name: String,
        val colors: List<RGB>
)

fun createPalettePreview(palettes: List<Palette>): String {
    val colorCount = palettes.getOrNull(0)?.colors?.size ?: 0
    val columnCount = (colorCount) + 1

    require(palettes.all { it.colors.size == colorCount })

    val columnNames = buildString { repeat(columnCount) { this.append(" auto") }; this.append(";") }

    val elemHeight = 100

    return buildString {
        this.appendHTML()
                .html {
                    body {
                        div {
                            style = "display: grid; grid-template-columns: $columnNames"

                            for (palette in palettes) {

                                div {
                                    style = "height: ${elemHeight}px;}"
                                    +palette.name
                                }

                                for (color in palette.colors) {
                                    div {
                                        style = "height: ${elemHeight}px; " +
                                                "background-color: ${color.toHex(withNumberSign = true)}"
                                    }
                                }
                            }
                        }
                    }
                }
    }
}

fun <C : ConvertibleColor> createColorPalette(
        c100: RGB,
        c900: RGB,
        convert: RGB.() -> C
): Palette {
    return createColorPalette(
            c100,
            Mixer.mix(c100.convert(), c900, 0.5),
            c900,
            convert
    )
}

fun <C : ConvertibleColor> createColorPalette(
        c100: RGB,
        c500: RGB,
        c900: RGB,
        convert: RGB.() -> C
): Palette {

    val half = 0.5

    fun mixHalf(from: RGB, to: RGB) = Mixer.mix(from.convert(), to, half)

    val c300 = mixHalf(c100, c500)

    val c400 = mixHalf(c300, c500)
    val c200 = mixHalf(c100, c300)

    val c700 = mixHalf(c500, c900)

    val c600 = mixHalf(c500, c700)
    val c800 = mixHalf(c700, c900)

    return Palette(
            c100.convert()::class.java.simpleName,
            listOf(
                    c100, c200, c300, c400, c500, c600, c700, c800, c900
            )
    )
}

fun main(args: Array<String>) {

//    val c100 = RGB("#ffcc00")
//    // val c500 = RGB("#00ccff")
//    val c900 = RGB("#004422")


    // Red
    val c100 = RGB(252, 232, 232)
    val c900 = RGB(95, 23, 23)

    // Green
//    val c100 = RGB(231, 255, 254)
//    val c900 = RGB(17, 68, 67)

    val blendings = listOf<(RGB) -> ConvertibleColor>(
            { it.toCMYK() },
            { it.toHSV() },
            { it.toRGB() },
            { it.toLAB() },
            { it.toXYZ() },
            { it.toHSL() }
    )

    val palettes = blendings.map {
        createColorPalette(
                c100,
//                c500,
                c900,
                it
        )
    }

    val html = createPalettePreview(palettes)

    val file = File("palette_preview.html")

    println("Html content is:\n$html\n")

    file.writeText(html)
    println("File written to ${file.absolutePath}")


}