package kz.ilotterytea.maxon

import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import kz.ilotterytea.maxon.screens.SplashScreen
import kz.ilotterytea.maxon.utils.I18N

/**
 * Main game.
 * @since a_1.0
 */
class MaxonGame : Game() {
    lateinit var batch: SpriteBatch
    lateinit var assets: AssetManager
    lateinit var i18n: I18N

    override fun create() {
        batch = SpriteBatch()
        assets = AssetManager()
        i18n = I18N(Gdx.files.internal("langs"), "en_us")

        this.screen = SplashScreen(this)
    }

    override fun dispose() {
        batch.dispose()
        assets.dispose()
    }
}