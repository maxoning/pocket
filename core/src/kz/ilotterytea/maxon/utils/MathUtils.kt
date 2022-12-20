package kz.ilotterytea.maxon.utils

/**
 * Math utilities.
 * @since a_1.0
 */
class MathUtils {
    companion object {
        /** Get a random number from minimum value to maximum value.
         * @since a_1.0
         */
        fun random(min: Float, max: Float) = ((Math.random() * (max - min)) + min).toFloat()
    }
}