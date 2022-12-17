package kz.ilotterytea.maxon.items

import com.badlogic.gdx.scenes.scene2d.ui.Image

/**
 * Purchasable item.
 * @since a_1.0
 */
data class PurchasableItem(
    override val name_id: String,
    override val icon: Image,
    val price: Long,
    val multiplier: Long
) : Registerable
