package kz.ilotterytea.maxon.ui

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable

/** Make the image animated! */
class AnimatedImage(val textures: List<TextureRegion>) : Image(textures[0]) {
    private var index = 0
    var stopAnimation = false

    override fun act(delta: Float) {
        super.act(delta)

        if (!stopAnimation) {
            next()
        }
    }

    /** Change image's drawable to the next frame. */
    fun next() {
        index++

        if (index > textures.size - 1) index = 0
        super.setDrawable(TextureRegionDrawable(textures[index]))
    }
}