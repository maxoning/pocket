package kz.ilotterytea.maxon.items

import com.badlogic.gdx.Gdx

/**
 * Item registrar.
 * @since a_1.0
 */
class ItemRegistrar<T : Registerable> {
    val registeredItems = mutableMapOf<Int, T>()

    /** Register an item.
     * @since a_1.0
     */
    fun register(item: T) {
        if (registeredItems.containsKey(item.num_id)) {
            Gdx.app.debug("ItemRegistrar", "Item ID ${item.name_id} already registered! Abort.")
            return
        }
        registeredItems[item.num_id] = item
        Gdx.app.debug("ItemRegistrar", "Item ID ${item.name_id} successfully registered!")
    }
}