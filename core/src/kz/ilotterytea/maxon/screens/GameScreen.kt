package kz.ilotterytea.maxon.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.math.Interpolation
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.badlogic.gdx.utils.Align
import com.badlogic.gdx.utils.Timer
import com.badlogic.gdx.utils.Timer.Task
import com.badlogic.gdx.utils.viewport.FillViewport
import com.rafaskoberg.gdx.typinglabel.TypingLabel
import kz.ilotterytea.maxon.MaxonConsts
import kz.ilotterytea.maxon.MaxonGame
import kz.ilotterytea.maxon.ui.AnimatedImage
import kz.ilotterytea.maxon.ui.ChessBackground
import kz.ilotterytea.maxon.ui.InventoryItemWidget
import kz.ilotterytea.maxon.ui.PurchasableItemWidget
import kz.ilotterytea.maxon.utils.MathUtils
import kz.ilotterytea.maxon.utils.SpriteUtils
import kz.ilotterytea.maxon.utils.StringUtils

/**
 * Game screen.
 * @since a_1.0
 */
class GameScreen(val game: MaxonGame) : Screen {
    private val stage = Stage(FillViewport(Gdx.graphics.width.toFloat() / 2f, Gdx.graphics.height.toFloat() / 2f))
    private val skin = game.assets.get("MainSpritesheet.skin", Skin::class.java)

    private val main = game.assets.get("MainSpritesheet.atlas", TextureAtlas::class.java)

    private val cat = AnimatedImage(SpriteUtils.splitSpritesheet(game.assets.get("playables/Maxon.png", Texture::class.java), 112, 112))

    private val statsBoard = Table(skin)
    private val actionsBoard = Table(skin)

    private var shopTable: Table? = null
    private var inventoryTable: Table? = null

    private val pointsLabel = Label(game.i18n.format("game.board.points", StringUtils.formatValue(game.sav.getLong("points", 0L))), skin, "halffull-default")
    private val multiplierLabel = Label(game.i18n.format("game.board.multiplier", StringUtils.formatValue(game.sav.getLong("multiplier", 1L))), skin, "halffull-default")

    private val bg = ChessBackground(
        1f, 0f, stage.width, stage.height, skin.getDrawable("tile_01"), skin.getDrawable("tile_02")
    )

    private val itemId = mutableListOf<Int>()

    override fun show() {
        // Loading the purchased items:
        for (id in game.sav.getString("purchased", "").split(',')) {
            id.toIntOrNull()?.let { itemId.add(it) }
        }

        // - - - - - -  S T A T S  - - - - - - :
        statsBoard.setBackground("board")
        statsBoard.width = stage.width
        statsBoard.height = 140f
        statsBoard.setPosition(0f, stage.height - statsBoard.height)
        statsBoard.align(Align.left)
        stage.addActor(statsBoard)

        // - - -  P O I N T S  - - - :
        // Icon for points info:
        val pointsI = Image(main.findRegion("points"))
        statsBoard.add(pointsI).size(64f, 64f).pad(6f)

        // Points info:
        pointsLabel.setAlignment(Align.left)
        statsBoard.add(pointsLabel).fillX().pad(6f).row()

        // - - -  M U L T I P L I E R  - - - :
        // Icon for multiplier info:
        val mpI = Image(main.findRegion("multiplier"))
        statsBoard.add(mpI).size(64f, 64f).pad(6f)

        // Multiplier info:
        multiplierLabel.setAlignment(Align.left)
        statsBoard.add(multiplierLabel).fillX().pad(6f).row()

        // - - - - - -  A C T I O N S  - - - - - - :
        actionsBoard.setBackground("board-d")
        actionsBoard.width = stage.width
        actionsBoard.height = 140f
        actionsBoard.setPosition(0f, 0f)
        stage.addActor(actionsBoard)

        // - - - S H O P  - - - :
        // Shop button:
        val shop = ImageButton(skin, "shop")
        shop.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                if (inventoryTable != null) {
                    inventoryTable!!.remove()
                    inventoryTable = null
                }

                shopTable = if (shopTable != null) {
                    shopTable!!.remove()
                    null
                } else {
                    generateShop()
                }
            }
        })
        actionsBoard.add(shop).size(shop.width, shop.height).pad(6f)

        // - - -  M U L T I P L I E R  - - - :
        // Inventory button:
        val inventory = ImageButton(skin, "inventory")
        inventory.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                if (shopTable != null) {
                    shopTable!!.remove()
                    shopTable = null
                }

                inventoryTable = if (inventoryTable != null) {
                    inventoryTable!!.remove()
                    null
                } else {
                    generateInventory()
                }
            }
        })
        actionsBoard.add(inventory).size(inventory.width, inventory.height).pad(6f)

        // - - - - - -  P L A Y A B L E  - - - - - - :
        cat.stopAnimation = true
        cat.setSize(cat.width * 3f, cat.height * 3f)
        cat.setOrigin(cat.width / 3f, cat.height / 3f)
        cat.setPosition(
            (stage.width / 2f) - (cat.width / 2f),
            (stage.height / 2f) - (cat.height / 2f)
        )
        cat.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                increasePoints()
            }
        })
        stage.addActor(cat)

        Gdx.input.inputProcessor = stage

        Timer.schedule(object : Task() {
            override fun run() {
                increasePoints()
            }
        }, 60f, 60f)

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
        bg.update(width.toFloat(), height.toFloat())
        stage.viewport.update(width, height)
    }

    override fun pause() {
        game.sav.flush()
        game.prefs.flush()
    }

    override fun resume() {
        TODO("Not yet implemented")
    }

    override fun hide() { dispose() }

    override fun dispose() {
        stage.dispose()
    }

    private fun increasePoints() {
        game.sav.putLong("points", game.sav.getLong("points", 0L) + game.sav.getLong("multiplier", 1L))
        game.sav.flush()

        val label = TypingLabel(game.i18n.format("game.earned_points", MaxonConsts.DECIMAL_FORMAT.format(game.sav.getLong("multiplier", 1L))), skin)
        val move_up = MathUtils.random(0f, 100f)
        val x = MathUtils.random(
            0f, stage.width - label.width
        )
        val y = MathUtils.random(
            cat.y + cat.height,
            stage.height - (statsBoard.height + label.height + move_up)
        )

        label.setPosition(x, y)

        label.addAction(
            Actions.sequence(
                Actions.delay(2f),
                Actions.parallel(
                    Actions.moveTo(label.x, label.y + move_up, 3f, Interpolation.linear),
                    Actions.fadeOut(3f)
                )
            )
        )

        stage.addActor(label)

        cat.clearActions()
        // Change the cat size:
        cat.addAction(
            Actions.sequence(
                Actions.scaleTo(0.95f, 0.95f, 0f),
                Actions.scaleTo(1f, 1f, 0.5f, Interpolation.smooth)
            )
        )

        cat.next()

        pointsLabel.setText(game.i18n.format("game.board.points", StringUtils.formatValue(game.sav.getLong("points", 0L))))
        // Makes the text rainbow.
        // If you initially make it rainbow, its width will increase because tags count as letters and add to the width, and can cause some errors in text position calculations.
        label.setText(game.i18n.format("game.earned_points.effect", label.text.toString()))

        Timer.schedule(object : Task() {
            override fun run() {
                label.remove()
            }
        }, 5f)
    }

    private fun generateInventory() : Table {
        // - - - - - -  M A I N  T A B L E  - - -  - - -:
        val table = Table(skin)
        table.setBackground("board-bg-2")
        table.setPosition(
            actionsBoard.x,
            actionsBoard.y + actionsBoard.height
        )
        table.setSize(
            stage.width, stage.height - (statsBoard.height + actionsBoard.height)
        )
        stage.addActor(table)

        // - - -  T A B L E  H E A D E R  - - - :
        val header = Table(skin)
        header.setBackground("board-bg")
        header.align(Align.center)
        table.add(header).fillX().padBottom(12f).row()

        // Title:
        val title = Label("Inventory", skin, "halffull-default")
        header.add(title).expandX()

        // - - -  T A B L E  C O N T E N T  - - - :
        val content = Table(skin)
        content.setBackground("board-bg-2")
        val scrollpane = ScrollPane(content)
        scrollpane.setScrollingDisabled(true, false)
        table.add(scrollpane).width(table.width)

        val mapIds = mutableMapOf<Int, Int>()

        for (id in itemId.sorted()) {
            if (game.items.registeredItems.containsKey(id)) {
                var value = mapIds[id]
                if (value == null) value = 0
                mapIds[id] = value + 1
            }
        }

        for (id in mapIds.keys) {
            val item = game.items.registeredItems[id] ?: continue
            val count = mapIds[id] ?: continue

            val widget = InventoryItemWidget(
                item,
                skin,
                game.i18n,
                count
            )
            widget.setBackground("up")

            content.add(widget).padBottom(16f).row()
        }

        println(mapIds)
        println(itemId.sorted())

        return table
    }

    private fun generateShop() : Table {
        // - - - - - -  M A I N  T A B L E  - - -  - - -:
        val table = Table(skin)
        table.setBackground("board-bg-2")
        table.setPosition(
            actionsBoard.x,
            actionsBoard.y + actionsBoard.height
        )
        table.setSize(
            stage.width, stage.height - (statsBoard.height + actionsBoard.height)
        )
        stage.addActor(table)

        // - - -  T A B L E  H E A D E R  - - - :
        val header = Table(skin)
        header.setBackground("board-bg")
        header.align(Align.center)
        table.add(header).fillX().padBottom(12f).row()

        // Title:
        val title = Label("Shop", skin, "halffull-default")
        header.add(title).expandX()

        // - - -  T A B L E  C O N T E N T  - - - :
        val content = Table(skin)
        content.setBackground("board-bg-2")
        val scrollpane = ScrollPane(content)
        scrollpane.setScrollingDisabled(true, false)
        table.add(scrollpane).width(table.width)

        for (item in game.items.registeredItems.values) {
            val widget = PurchasableItemWidget(item, skin, game.i18n)
            if (game.sav.getLong("points", 0L) > item.price) {
                widget.setBackground("up")
            } else {
                widget.background("down")
            }

            widget.addListener(object : ClickListener() {
                override fun clicked(event: InputEvent?, x: Float, y: Float) {
                    multiplierLabel.setText(game.i18n.format("game.board.multiplier", StringUtils.formatValue(game.sav.getLong("multiplier", 1L))))
                    pointsLabel.setText(game.i18n.format("game.board.points", StringUtils.formatValue(game.sav.getLong("points", 0L))))

                    if (game.sav.getLong("points", 0L) < item.price) return

                    game.sav.putLong("points", game.sav.getLong("points", 0L) - item.price)
                    game.sav.putLong("multiplier", game.sav.getLong("multiplier", 1L) + item.multiplier)

                    itemId.add(item.num_id)

                    game.sav.putString("purchased", itemId.joinToString(","))
                    game.sav.flush()
                }
            })

            content.add(widget)
                .fillX()
                .prefHeight(table.width)
                .maxHeight(table.width)
                .width(table.width - 32)
                .pad(16f)
                .row()
        }

        return table
    }
}