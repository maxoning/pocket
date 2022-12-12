package kz.ilotterytea.maxon

import com.badlogic.gdx.Game
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import kz.ilotterytea.maxon.screens.SplashScreen

/**
 * Main game.
 * @since a_1.0
 */
class MaxonGame : Game() {
    lateinit var batch: SpriteBatch
    lateinit var assets: AssetManager

    override fun create() {
        batch = SpriteBatch()
        assets = AssetManager()

        this.screen = SplashScreen(this)
    }

    override fun dispose() {
        batch.dispose()
        assets.dispose()
    }
}