package me.danielvianna.dvadmin.managers

import me.danielvianna.dvadmin.commands.AdminCommand
import me.danielvianna.dvadmin.commands.BuildCommand
import me.danielvianna.dvadmin.commands.SetJailCommand
import me.danielvianna.dvadmin.config
import me.danielvianna.dvadmin.instance
import me.danielvianna.dvadmin.listeners.*
import org.bukkit.Bukkit
import org.bukkit.command.CommandExecutor
import org.bukkit.event.Listener

fun saveConfiguration() {
    config().options().copyDefaults(false)
    instance.saveDefaultConfig()
    instance.reloadConfig()
}

fun command(Comando: String, Classe: CommandExecutor) {
    instance.getCommand(Comando).executor = Classe
}

fun event(Classe: Listener) {
    val pm = Bukkit.getPluginManager()
    pm.registerEvents(Classe, instance)
}

fun registerAll() {
    command("Admin", AdminCommand())
    command("SetJail", SetJailCommand())
    command("Build", BuildCommand())

    event(ChangeFast())
    event(Jail())
    event(PlayerInfo())
    event(TestAutoSoup())
    event(TestNoFall())
    event(BuildEvent())
    event(DropEvent())
}
