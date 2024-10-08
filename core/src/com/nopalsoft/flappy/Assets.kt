package com.nopalsoft.flappy

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.GlyphLayout
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion
import com.badlogic.gdx.graphics.g2d.TextureRegion

object Assets {

    private val glyphLayout = GlyphLayout()

    @JvmField
    val font = BitmapFont()

    @JvmField
    var bird: Animation<AtlasRegion>? = null

    @JvmField
    var background = TextureRegion()

    @JvmField
    var gameOver = TextureRegion()

    @JvmField
    var getReady = TextureRegion()

    @JvmField
    var tap = TextureRegion()

    @JvmField
    var downPipe = TextureRegion()

    @JvmField
    var upPipe = TextureRegion()

    fun load() {
        val atlas = TextureAtlas(Gdx.files.internal("data/atlasMap.txt"))

        background = atlas.findRegion("fondo")
        gameOver = atlas.findRegion("gameOver")
        getReady = atlas.findRegion("getReady")
        tap = atlas.findRegion("tap")
        downPipe = atlas.findRegion("tuberiaDown")
        upPipe = atlas.findRegion("tuberiaUp")

        bird = Animation(
            .3F,
            atlas.findRegion("bird1"),
            atlas.findRegion("bird2"),
            atlas.findRegion("bird3")
        )


        // Use default libGDX font
        font.data.scale(7f)
    }

    // Get the text width in order to center it in the screen
    @JvmStatic
    fun getTextWidth(text: String): Float {
        glyphLayout.setText(font, text)
        return glyphLayout.width
    }
}