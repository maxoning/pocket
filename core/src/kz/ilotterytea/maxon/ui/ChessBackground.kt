package kz.ilotterytea.maxon.ui

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.utils.Drawable

/**
 * A background that looks like chess.
 */
class ChessBackground(
    private val velX: Float,
    private val velY: Float,
    private var screenWidth: Float,
    private var screenHeight: Float,
    private vararg val drawables: Drawable
) {
    private val tiles: MutableList<MutableList<Image>> = mutableListOf()

    init { update(screenWidth, screenHeight) }

    /**
     * Create new background tiles. If tiles already exist, they will be cleared first.
     */
    fun update(width: Float, height: Float) {
        screenWidth = width
        screenHeight = height

        Gdx.app.log(ChessBackground::class.simpleName, "Updating the chess background...")
        tiles.clear()
        Gdx.app.debug("${ChessBackground::class.simpleName}::Steps", "List of tiles is cleared!")

        var totalDWidth = 0f
        var totalDHeight = 0f

        for (drawable in drawables) {
            totalDWidth += drawable.minWidth
            totalDHeight += drawable.minHeight
        }

        totalDWidth /= drawables.size
        totalDHeight /= drawables.size

        Gdx.app.debug("${ChessBackground::class.simpleName}::Steps", "Total size of ${drawables.size} drawables: $totalDWidth x $totalDHeight")

        var dIndex = 0

        Gdx.app.debug("${ChessBackground::class.simpleName}::Steps", "Starting to generating the tiles...")

        for (h in 0..(screenHeight / totalDHeight + 3).toInt()) {
            tiles.add(mutableListOf())

            for (w in -1..(screenWidth / totalDWidth).toInt()) {
                if (dIndex + 1 > drawables.size) dIndex = 0

                val tile = Image(drawables[dIndex++])

                tile.setPosition(tile.width * w, tile.height * h)
                tiles[h].add(tile)
            }
        }

        Gdx.app.log("${ChessBackground::class.simpleName}", "Successfully updated the chess background!")
    }

    /**
     * Draw the background tiles.
     */
    fun draw(batch: SpriteBatch) {
        val outYSprites: MutableList<MutableList<Image>> = mutableListOf()

        for (array in tiles) {
            for (tile in array) {
                tile.setPosition(tile.x + velX, tile.y + velY)
                tile.draw(batch, 1f)

                if (tile.x > screenWidth) {
                    val ftile = array[0]

                    tile.setPosition(ftile.x - tile.width, ftile.y)

                    if (tile.drawable == ftile.drawable) {
                        tile.drawable = array[1].drawable
                    }

                    array.remove(tile)
                    array[0] = tile
                }

                if (!outYSprites.contains(array) && tile.y > screenHeight) {
                    outYSprites.add(array)
                }
            }
        }

        for (array in outYSprites) {
            var index = 0

            for (tile in array) {
                if (index + 1 > tiles[0].size) index = 0
                val ftile = tiles[0][index++]
                tile.setPosition(tile.x, ftile.y - tile.height)

                if (ftile.drawable == tile.drawable) {
                    if (index + 1 > tiles[0].size) index = 0
                    tile.drawable = tiles[0][index].drawable
                }
            }
        }
    }
}