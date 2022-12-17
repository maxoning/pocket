package kz.ilotterytea.maxon.utils

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.assets.loaders.SkinLoader
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.scenes.scene2d.ui.Skin

/**
 * Utilities that have to load anything.
 * @since a_1.0
 */
class LoadUtils {
    companion object {
        /**
         * Load assets to the asset manager.
         * @since a_1.0
         */
        fun loadAssets(m: AssetManager) {
            // Texture atlases:
            m.load("BrandSpritesheet.atlas", TextureAtlas::class.java)
            m.load("ContribSpritesheet.atlas", TextureAtlas::class.java)
            m.load("MainSpritesheet.atlas", TextureAtlas::class.java)

            // Skins:
            m.load("MainSpritesheet.skin", Skin::class.java, SkinLoader.SkinParameter("MainSpritesheet.atlas"))

            // Playables:
            m.load("playables/Maxon.png", Texture::class.java)

            m.finishLoading()
        }
    }
}