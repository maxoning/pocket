package kz.ilotterytea.maxon.utils

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.files.FileHandle
import com.badlogic.gdx.utils.JsonReader
import java.lang.StringBuilder
import java.util.Scanner

/** Localization manager. */
class I18N(fh: FileHandle, var default_lang_id: String) {
    private var languages = mutableMapOf<String, MutableMap<String, String>>()

    init {
        Gdx.app.log("I18N", "Initializing...")

        if (fh.isDirectory) {
            Gdx.app.debug("I18N", "The path ${fh.path()} is a directory! Starting to process...")

            val mfh = fh.list()

            Gdx.app.debug("I18N", "The path (${fh.path()}) has ${mfh.size} language files.")

            for (fh2 in mfh) {
                processLocaleFile(fh2)
            }
        } else {
            processLocaleFile(fh)
        }

        Gdx.app.log("I18N", "Finished! Loaded ${languages.size} language(s).")
    }

    /** Get the string from translation file. */
    fun text(id: String): String? {
        return languages[default_lang_id]?.get(id)
    }

    /** Returns a string with replaced placeholders.
     * @see text
     */
    fun format(id: String, vararg params: String): String? {
        val dummy = text(id)
        if (dummy == null) return dummy

        val scan = Scanner(dummy)
        val res = StringBuilder()
        var index = 0

        while (scan.hasNext()) {
            var next = scan.next()

            if (next.contains("%s")) {
                next = next.replace("%s", params[index])
                if (index + 1 < params.size) index++
            }

            res.append(next).append(' ')
        }

        return res.toString()
    }

    /** Process locale file. */
    private fun processLocaleFile(filehandle: FileHandle) {
        Gdx.app.debug("I18N", "Starting to process the ${filehandle.nameWithoutExtension()} file...")
        val json = JsonReader().parse(filehandle)
        // Just for stats :)
        var keys = 0

        languages[filehandle.nameWithoutExtension()] = mutableMapOf()

        for (value in json.iterator()) {
            keys++
            languages[filehandle.nameWithoutExtension()]?.set(value.name, json.getString(value.name))
        }

        Gdx.app.debug("I18N", "Finished with ${filehandle.nameWithoutExtension()} file! It has $keys key(s).")
    }
}