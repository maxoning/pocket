package kz.ilotterytea.maxon.ui

import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.utils.Align
import kz.ilotterytea.maxon.items.PurchasableItem
import kz.ilotterytea.maxon.utils.I18N
import kz.ilotterytea.maxon.utils.StringUtils

/** A widget for the purchasable item. */
class PurchasableItemWidget(
    item: PurchasableItem,
    skin: Skin,
    i18n: I18N
) : Table(skin) {

    init {
        // - - -  H E A D E R  - - - :
        val header = Table(skin)
        super.add(header).fillX().row()

        // Title:
        val title = Label(i18n.text("pet.${item.name_id}.name"), skin, "header")
        header.add(title).fillX()

        // - - -  C O N T E N T  - - - :
        val content = Table(skin)
        super.add(content).expandX().expandY().row()

        // Icon:
        content.add(item.icon).size(384f)

        // - - -  I N F O  - - - :
        val info = Table(skin)
        info.align(Align.right)
        super.add(info).fillX().row()

        // Price:
        val price = Label(i18n.format("shop.price", StringUtils.formatValue(item.price)), skin)
        info.add(price).padRight(12f)

        // Multiplier:
        val mp = Label(i18n.format("shop.multiplier", StringUtils.formatValue(item.multiplier)), skin)
        info.add(mp)
    }
}