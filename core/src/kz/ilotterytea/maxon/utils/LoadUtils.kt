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
            m.load("purchasables/tvcat.png", Texture::class.java)

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
                    1,
                    "bror",
                    AnimatedImage(
                        SpriteUtils.splitSpritesheet(m.get("purchasables/bror.png"), 112, 112)
                    ),
                    10,
                    1
                )
            )
            // - - -  SANDWICH CAT - - - :
            i.register(
                PurchasableItem(
                    2,
                    "sandwich_cat",
                    AnimatedImage(
                        SpriteUtils.splitSpritesheet(m.get("purchasables/sandwich_cat.png"), 112, 112)
                    ),
                    80,
                    7
                )
            )
            // - - -  MANLOOSHKA - - - :
            i.register(
                PurchasableItem(
                    3,
                    "manlooshka",
                    AnimatedImage(
                        SpriteUtils.splitSpritesheet(m.get("purchasables/manlooshka.png"), 112, 112)
                    ),
                    640,
                    49
                )
            )
            // - - -  THIRSTY CAT - - - :
            i.register(
                PurchasableItem(
                    4,
                    "thirsty_cat",
                    AnimatedImage(
                        SpriteUtils.splitSpritesheet(m.get("purchasables/thirsty_cat.png"), 112, 112)
                    ),
                    5120,
                    343
                )
            )
            // - - -  FURIOS CAT - - - :
            i.register(
                PurchasableItem(
                    5,
                    "furios_cat",
                    AnimatedImage(
                        SpriteUtils.splitSpritesheet(m.get("purchasables/furios_cat.png"), 112, 112)
                    ),
                    40960,
                    2401
                )
            )
            // - - -  TV CAT - - - :
            i.register(
                PurchasableItem(
                    6,
                    "tv_cat",
                    AnimatedImage(
                        SpriteUtils.splitSpritesheet(m.get("purchasables/tvcat.png"), 112, 112)
                    ),
                    327680,
                    16807
                )
            )
            // - - -  PROGRAMMER CAT - - - :
            i.register(
                PurchasableItem(
                    7,
                    "code_cat",
                    AnimatedImage(
                        SpriteUtils.splitSpritesheet(m.get("purchasables/progcat.png"), 112, 112)
                    ),
                    2621440,
                    117649
                )
            )
            // - - -  SCREAMING CAT - - - :
            i.register(
                PurchasableItem(
                    8,
                    "scream_cat",
                    AnimatedImage(
                        SpriteUtils.splitSpritesheet(m.get("purchasables/screamcat.png"), 112, 112)
                    ),
                    20971520,
                    823543
                )
            )
            // - - -  HELL CAT - - - :
            i.register(
                PurchasableItem(
                    9,
                    "hellcat",
                    AnimatedImage(
                        SpriteUtils.splitSpritesheet(m.get("purchasables/hellcat.png"), 128, 128)
                    ),
                    167772160,
                    5764801
                )
            )
            // - - -  LURKER - - - :
            i.register(
                PurchasableItem(
                    10,
                    "lurker",
                    AnimatedImage(
                        SpriteUtils.splitSpritesheet(m.get("purchasables/lurker.png"), 112, 112)
                    ),
                    1342177280,
                    40353607
                )
            )
            // - - -  PIANO CAT - - - :
            i.register(
                PurchasableItem(
                    11,
                    "piano_cat",
                    AnimatedImage(
                        SpriteUtils.splitSpritesheet(m.get("purchasables/piano_cat.png"), 128, 128)
                    ),
                    10737418240,
                    282475249
                )
            )
            // - - -  BEE CAT - - - :
            i.register(
                PurchasableItem(
                    12,
                    "bee_cat",
                    AnimatedImage(
                        SpriteUtils.splitSpritesheet(m.get("purchasables/bee_cat.png"), 112, 112)
                    ),
                    85899345920,
                    1977326743
                )
            )
            // - - -  BUSINESS CAT - - - :
            i.register(
                PurchasableItem(
                    13,
                    "business_cat",
                    AnimatedImage(
                        SpriteUtils.splitSpritesheet(m.get("purchasables/busy.png"), 112, 112)
                    ),
                    687194767360,
                    13841287201
                )
            )
            // - - -  AEAE - - - :
            i.register(
                PurchasableItem(
                    14,
                    "aeae",
                    AnimatedImage(
                        SpriteUtils.splitSpritesheet(m.get("purchasables/aeae.png"), 112, 112)
                    ),
                    5497558138880,
                    96889010407
                )
            )
            // - - -  SUCKING CAT - - - :
            i.register(
                PurchasableItem(
                    15,
                    "sucking_cat",
                    AnimatedImage(
                        SpriteUtils.splitSpritesheet(m.get("purchasables/succat.png"), 128, 128)
                    ),
                    43980465111040,
                    678223072849
                )
            )
        }
    }
}