package com.github.andras.palette

import com.github.ajalt.colormath.ConvertibleColor
import com.github.ajalt.colormath.RGB
import kotlinx.html.body
import kotlinx.html.div
import kotlinx.html.html
import kotlinx.html.stream.appendHTML
import kotlinx.html.style
import java.io.File


data class Palette(val colors: List<RGB>)

fun createPalettePreview(palettes: List<Palette>): String {
    val columnCount = palettes.getOrNull(0)?.colors?.size ?: 0

    require(palettes.all { it.colors.size == columnCount })

    val columnNames = buildString { repeat(columnCount) { this.append(" auto") }; this.append(";") }

    return buildString {
        this.appendHTML()
                .html {
                    body {
                        div {
                            style = "display: grid; grid-template-columns: $columnNames"

                            for (palette in palettes) {
                                for (color in palette.colors) {
                                    div {
                                        style = "height: 200px; " +
                                                "background-color: ${color.toHex(withNumberSign = true)}"
                                    }
                                }
                            }
                        }
                    }
                }
    }
}

fun <C : ConvertibleColor> createColorPalette(dark: RGB, middle: RGB, light: RGB) {
    TODO()
}

fun main(args: Array<String>) {

    val columnCount = 3

    val columnNames = buildString { repeat(columnCount) { this.append(" auto") }; this.append(";") }

    val file = File("palette_preview.html")



//    println("Html content is:\n$html\n")
//
//    file.writeText(html)
//    println("File written to ${file.absolutePath}")


}