package kz.ilotterytea.maxon.items

import com.badlogic.gdx.scenes.scene2d.ui.Image

/**
 * Make the class registerable for the item registrar.
 * @see ItemRegistrar
 * @since a_1.0
 */
interface Registerable {
    val num_id: Int
    val name_id: String
    val icon: Image
}