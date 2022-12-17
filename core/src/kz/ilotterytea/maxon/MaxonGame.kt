package kz.ilotterytea.maxon

import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Preferences
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import kz.ilotterytea.maxon.items.ItemRegistrar
import kz.ilotterytea.maxon.items.PurchasableItem
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

    lateinit var prefs: Preferences
    lateinit var sav: Preferences

    lateinit var items: ItemRegistrar<PurchasableItem>

    override fun create() {
        batch = SpriteBatch()
        assets = AssetManager()

        prefs = Gdx.app.getPreferences("MaxoningPrefs")
        sav = Gdx.app.getPreferences("MaxoningLatest")

        i18n = I18N(Gdx.files.internal("langs"), prefs.getString("language", "en_us"))

        items = ItemRegistrar()

        this.screen = SplashScreen(this)
    }

    override fun dispose() {
        batch.dispose()
        assets.dispose()
    }
}