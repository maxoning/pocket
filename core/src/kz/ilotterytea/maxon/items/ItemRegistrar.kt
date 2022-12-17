package kz.ilotterytea.maxon.items

import com.badlogic.gdx.Gdx

/**
 * Item registrar.
 * @since a_1.0
 */
class ItemRegistrar<T : Registerable> {
    val registeredItems = mutableMapOf<String, T>()

    /** Register an item.
     * @since a_1.0
     */
    fun register(item: T) {
        if (registeredItems.containsKey(item.name_id)) {
            Gdx.app.debug("ItemRegistrar", "Item ID ${item.name_id} already registered! Abort.")
            return
        }
        registeredItems[item.name_id] = item
        Gdx.app.debug("ItemRegistrar", "Item ID ${item.name_id} successfully registered!")
    }
}