package kz.ilotterytea.maxon.utils

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureRegion

/**
 * Sprite utilities.
 * @since a_1.0
 */
class SpriteUtils {
    companion object {
        /**
         * Split the texture (spritesheet) into a TextureRegion list.
         * @since a_1.0
         * @see TextureRegion
         */
        fun splitSpritesheet(
            texture: Texture,
            tileWidth: Int,
            tileHeight: Int
        ): List<TextureRegion> {
            val tmp = TextureRegion.split(texture, tileWidth, tileHeight)
            val frames = mutableListOf<TextureRegion>()

            for (array in tmp) {
                for (reg in array) {
                    if (reg != null) frames.add(reg)
                }
            }

            return frames
        }
    }
}