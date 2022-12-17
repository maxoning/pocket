package kz.ilotterytea.maxon.utils

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.assets.loaders.SkinLoader
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import kz.ilotterytea.maxon.items.ItemRegistrar
import kz.ilotterytea.maxon.items.PurchasableItem
import kz.ilotterytea.maxon.ui.AnimatedImage

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

            // Purchasables:
            m.load("purchasables/aeae.png", Texture::class.java)
            m.load("purchasables/bee_cat.png", Texture::class.java)
            m.load("purchasables/bror.png", Texture::class.java)
            m.load("purchasables/busy.png", Texture::class.java)
            m.load("purchasables/furios_cat.png", Texture::class.java)
            m.load("purchasables/hellcat.png", Texture::class.java)
            m.load("purchasables/lurker.png", Texture::class.java)
            m.load("purchasables/manlooshka.png", Texture::class.java)
            m.load("purchasables/piano_cat.png", Texture::class.java)
            m.load("purchasables/progcat.png", Texture::class.java)
            m.load("purchasables/sandwich_cat.png", Texture::class.java)
            m.load("purchasables/screamcat.png", Texture::class.java)
            m.load("purchasables/succat.png", Texture::class.java)
            m.load("purchasables/thirsty_cat.png", Texture::class.java)
            m.load("purchasables/tv_cat.png", Texture::class.java)

            m.finishLoading()
        }

        /**
         * Load items to the item registrar.
         * @since a_1.0
         */
        fun loadPurchasableItems(m: AssetManager, i: ItemRegistrar<PurchasableItem>) {
            // - - -  BROR - - - :
            i.register(
                PurchasableItem(
                    "bror",
                    AnimatedImage(
                        SpriteUtils.splitSpritesheet(m.get("purchasables/bror.png"), 112, 112)
                    ),
                    150,
                    15
                )
            )
            // - - -  SANDWICH CAT - - - :
            i.register(
                PurchasableItem(
                    "sandwich_cat",
                    AnimatedImage(
                        SpriteUtils.splitSpritesheet(m.get("purchasables/sandwich_cat.png"), 112, 112)
                    ),
                    450,
                    45
                )
            )
            // - - -  MANLOOSHKA - - - :
            i.register(
                PurchasableItem(
                    "manlooshka",
                    AnimatedImage(
                        SpriteUtils.splitSpritesheet(m.get("purchasables/manlooshka.png"), 112, 112)
                    ),
                    1350,
                    135
                )
            )
            // - - -  THIRSTY CAT - - - :
            i.register(
                PurchasableItem(
                    "thirsty_cat",
                    AnimatedImage(
                        SpriteUtils.splitSpritesheet(m.get("purchasables/thirsty_cat.png"), 112, 112)
                    ),
                    4050,
                    405
                )
            )
            // - - -  FURIOS CAT - - - :
            i.register(
                PurchasableItem(
                    "furios_cat",
                    AnimatedImage(
                        SpriteUtils.splitSpritesheet(m.get("purchasables/furios_cat.png"), 112, 112)
                    ),
                    12150,
                    1215
                )
            )
            // - - -  TV CAT - - - :
            i.register(
                PurchasableItem(
                    "tv_cat",
                    AnimatedImage(
                        SpriteUtils.splitSpritesheet(m.get("purchasables/tvcat.png"), 112, 112)
                    ),
                    36450,
                    3645
                )
            )
            // - - -  PROGRAMMER CAT - - - :
            i.register(
                PurchasableItem(
                    "code_cat",
                    AnimatedImage(
                        SpriteUtils.splitSpritesheet(m.get("purchasables/progcat.png"), 112, 112)
                    ),
                    109350,
                    10935
                )
            )
            // - - -  SCREAMING CAT - - - :
            i.register(
                PurchasableItem(
                    "scream_cat",
                    AnimatedImage(
                        SpriteUtils.splitSpritesheet(m.get("purchasables/screamcat.png"), 112, 112)
                    ),
                    328050,
                    32805
                )
            )
            // - - -  HELL CAT - - - :
            i.register(
                PurchasableItem(
                    "hellcat",
                    AnimatedImage(
                        SpriteUtils.splitSpritesheet(m.get("purchasables/hellcat.png"), 112, 112)
                    ),
                    984150,
                    98415
                )
            )
            // - - -  LURKER - - - :
            i.register(
                PurchasableItem(
                    "lurker",
                    AnimatedImage(
                        SpriteUtils.splitSpritesheet(m.get("purchasables/lurker.png"), 112, 112)
                    ),
                    2952450,
                    295245
                )
            )
            // - - -  PIANO CAT - - - :
            i.register(
                PurchasableItem(
                    "piano_cat",
                    AnimatedImage(
                        SpriteUtils.splitSpritesheet(m.get("purchasables/piano_cat.png"), 112, 112)
                    ),
                    8857350,
                    885735
                )
            )
            // - - -  BEE CAT - - - :
            i.register(
                PurchasableItem(
                    "bee_cat",
                    AnimatedImage(
                        SpriteUtils.splitSpritesheet(m.get("purchasables/bee_cat.png"), 112, 112)
                    ),
                    26572050,
                    2657205
                )
            )
            // - - -  BUSINESS CAT - - - :
            i.register(
                PurchasableItem(
                    "business_cat",
                    AnimatedImage(
                        SpriteUtils.splitSpritesheet(m.get("purchasables/busy.png"), 112, 112)
                    ),
                    79716150,
                    7971615
                )
            )
            // - - -  AEAE - - - :
            i.register(
                PurchasableItem(
                    "aeae",
                    AnimatedImage(
                        SpriteUtils.splitSpritesheet(m.get("purchasables/aeae.png"), 112, 112)
                    ),
                    239148450,
                    23914845
                )
            )
            // - - -  SUCKING CAT - - - :
            i.register(
                PurchasableItem(
                    "sucking_cat",
                    AnimatedImage(
                        SpriteUtils.splitSpritesheet(m.get("purchasables/sucking_cat.png"), 112, 112)
                    ),
                    100,
                    1
                )
            )
        }
    }
}