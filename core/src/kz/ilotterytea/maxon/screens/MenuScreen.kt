package kz.ilotterytea.maxon.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.math.Interpolation
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.actions.RepeatAction
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.badlogic.gdx.utils.Align
import com.badlogic.gdx.utils.viewport.FillViewport
import kz.ilotterytea.maxon.MaxonConsts
import kz.ilotterytea.maxon.MaxonGame
import kz.ilotterytea.maxon.ui.ChessBackground

/**
 * Menu screen.
 * @since a_1.0
 */
class MenuScreen(val game: MaxonGame) : Screen {
    private val stage   = Stage(FillViewport(Gdx.graphics.width.toFloat() / MaxonConsts.SCALE_FACTOR, Gdx.graphics.height.toFloat() / MaxonConsts.SCALE_FACTOR), game.batch)
    private val skin    = game.assets.get("MainSpritesheet.skin", Skin::class.java)

    private val brand   = game.assets.get("BrandSpritesheet.atlas", TextureAtlas::class.java)

    private val bg = ChessBackground(
        1f, 1f, stage.width, stage.height, skin.getDrawable("tile_01"), skin.getDrawable("tile_02")
    )

    override fun show() {
        val logo = Image(brand.findRegion("brand"))
        logo.scaleX = 100f
        logo.scaleY = 100f
        logo.setPosition(
            (stage.width / 2f) - (logo.width / 2f),
            (stage.height / 2f) - (logo.height / 2f)
        )
        logo.setOrigin(logo.width / 2f, logo.height / 2f)
        logo.addAction(
            Actions.sequence(
                Actions.alpha(0f),
                Actions.parallel(
                    Actions.fadeIn(1f),
                    Actions.scaleTo(1f, 1f, 1f, Interpolation.pow2InInverse)
                ),
                Actions.repeat(
                    RepeatAction.FOREVER,
                    Actions.sequence(
                        Actions.parallel(
                            Actions.rotateTo(-10f, 10f, Interpolation.sine),
                            Actions.scaleTo(0.85f, 0.85f, 10f, Interpolation.sine)
                        ),
                        Actions.parallel(
                            Actions.rotateTo(10f, 10f, Interpolation.sine),
                            Actions.scaleTo(1f, 1f, 10f, Interpolation.sine)
                        )
                    )
                ))
        )
        stage.addActor(logo)

        val table = Table()
        table.width = stage.width
        table.height = stage.height
        table.align(Align.bottom)
        table.pad(16f)
        stage.addActor(table)

        val playBtn = TextButton(game.i18n.text("menu.new_game"), skin)
        if (game.sav.get().keys.size >= 1) {
            playBtn.setText(game.i18n.text("menu.continue"))
        }

        playBtn.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                game.screen = GameScreen(game)
            }
        })

        table.add(playBtn).width(table.width - 8f).pad(8f).row()

        val additionalBtnsTable = Table()

        val langBtn = TextButton(game.i18n.format("\$name"), skin)

        langBtn.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                game.i18n.changeToNextLanguage()

                game.prefs.putString("language", game.i18n.default_lang_id)
                game.prefs.flush()

                game.screen = MenuScreen(game)
            }
        })

        additionalBtnsTable.add(langBtn).width((table.width / 2f) - 8f).pad(8f)

        val resetBtn = TextButton(game.i18n.text("menu.reset"), skin)

        resetBtn.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                game.sav.clear()
                game.sav.flush()

                game.screen = MenuScreen(game)
            }
        })

        additionalBtnsTable.add(resetBtn).width((table.width / 2f) - 8f).pad(8f)

        table.add(additionalBtnsTable).row()

        Gdx.input.inputProcessor = stage
        render(Gdx.graphics.deltaTime)
    }

    override fun render(delta: Float) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        game.batch.begin()

        bg.draw(game.batch)

        game.batch.end()

        stage.draw()
        stage.act(delta)
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
    }
}