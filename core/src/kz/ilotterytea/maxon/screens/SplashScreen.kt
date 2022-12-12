package kz.ilotterytea.maxon.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.utils.viewport.FillViewport
import kz.ilotterytea.maxon.MaxonGame
import kz.ilotterytea.maxon.utils.LoadUtils

/**
 * Splash screen. This screen is used to load assets while showing the game creators.
 * @since a_1.0
 */
class SplashScreen(val game: MaxonGame) : Screen {
    private val stage: Stage = Stage(FillViewport(Gdx.graphics.width.toFloat() / 2f, Gdx.graphics.height.toFloat() / 2f), game.batch)
    private val skin: Skin = Skin(Gdx.files.internal("MainSpritesheet.skin"))

    private val devs: TextureAtlas = TextureAtlas(Gdx.files.internal("ContribSpritesheet.atlas"))
    private lateinit var bar: ProgressBar

    init { show() }

    override fun show() {
        // Main game developer logo:
        val dev = Image(devs.findRegion("devOld"))
        dev.width = dev.width * 3f
        dev.height = dev.height * 3f
        dev.setPosition(
            (stage.width / 2f) - (dev.width / 2f),
            (stage.height / 2f) - (dev.height / 2f)
        )
        stage.addActor(dev)

        // Loading assets progress bar:
        bar = ProgressBar(0f, 100f, 1f, false, skin)
        bar.setPosition(0f, 0f)
        bar.width = stage.height
        bar.height = 8f
        stage.addActor(bar)

        LoadUtils.loadAssets(game.assets)
        render(Gdx.graphics.deltaTime)
    }

    private fun update() {
        bar.value = bar.maxValue / (game.assets.queuedAssets + 1)

        if (game.assets.update()) {
            println("OK!")
        }
    }

    override fun render(delta: Float) {
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        stage.draw()
        stage.act(delta)

        update()
    }

    override fun resize(width: Int, height: Int) {
        stage.viewport.update(width, height)
    }

    override fun pause() {
        TODO("Not yet implemented")
    }

    override fun resume() {
        TODO("Not yet implemented")
    }

    override fun hide() { dispose() }

    override fun dispose() {
        stage.dispose()
        skin.dispose()
        devs.dispose()
    }
}