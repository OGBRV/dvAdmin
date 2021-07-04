 package me.danielvianna.dvadmin.managers

import me.danielvianna.dvadmin.instance
import org.bukkit.Bukkit
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.configuration.file.YamlConfiguration
import java.io.File
import java.io.IOException

class CustomConfiguration(private val name: String) {

    private val file: File
    val config: FileConfiguration

    fun save() {
        try {
            config.save(file)
        } catch (e: IOException) {
            Bukkit.getConsoleSender().sendMessage("§cOcorreu um erro ao salvar o arquivo $name")
        }
    }

    init {
        file = File(instance.getDataFolder(), if (name.endsWith(".yml")) name else "$name.yml")
        config = YamlConfiguration.loadConfiguration(file)
        if (!file.exists()) {
            try {
                instance.saveResource(if (name.endsWith(".yml")) name else "$name.yml", false)
            } catch (e: IllegalArgumentException) {
                try {
                    file.createNewFile()
                } catch (ioException: IOException) {
                    ioException.printStackTrace()
                }
            }
        }
    }
}