package me.danielvianna.dvadmin

import me.danielvianna.dvadmin.managers.CustomConfiguration
import me.danielvianna.dvadmin.managers.registerAll
import me.danielvianna.dvadmin.managers.saveConfiguration
import net.milkbowl.vault.chat.Chat
import net.milkbowl.vault.economy.Economy
import net.milkbowl.vault.permission.Permission
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.plugin.java.JavaPlugin
import java.util.logging.Logger

internal fun config() = instance.config

lateinit var instance: Main

class Main : JavaPlugin() {

    val log: Logger = Logger.getLogger("Minecraft")
    var chat: Chat? = null
    var econ: Economy? = null
    var perms: Permission? = null

    var loc: CustomConfiguration? = null

    val build = mutableListOf<Player>()
    val admins = mutableListOf<Player>()
    val itens: HashMap<Player, Array<out ItemStack>> = HashMap()

    override fun onLoad() {
        if (!setupEconomy()) {
            log.severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        setupPermissions();
        setupChat();
    }

    override fun onEnable() {
        instance = this
        loc = CustomConfiguration("locations.yml")
        saveConfiguration()
        registerAll()
    }

    private fun setupEconomy(): Boolean {
        if (server.pluginManager.getPlugin("Vault") == null) {
            return false
        }
        val rsp = server.servicesManager.getRegistration(
            Economy::class.java
        ) ?: return false
        econ = rsp.provider
        return econ != null
    }

    private fun setupChat(): Boolean {
        val rsp = server.servicesManager.getRegistration(
            Chat::class.java
        )
        chat = rsp.provider
        return chat != null
    }

    private fun setupPermissions(): Boolean {
        val rsp = server.servicesManager.getRegistration(
            Permission::class.java
        )
        perms = rsp.provider
        return perms != null
    }

}
