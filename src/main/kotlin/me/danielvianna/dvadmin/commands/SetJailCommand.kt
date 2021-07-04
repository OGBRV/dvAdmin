package me.danielvianna.dvadmin.commands

import me.danielvianna.dvadmin.instance
import me.danielvianna.dvadmin.managers.Mensagens
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class SetJailCommand : CommandExecutor {

    override fun onCommand(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>
    ): Boolean {
        val msg = Mensagens()
        if (sender !is Player) {
            sender.sendMessage(msg.noPermission())
            return false
        }
        val p = sender.player
        if (command.name.equals("setjail", ignoreCase = true)) {
            if (p.hasPermission("dvadmin.use")) {
                val loc = p.location

                instance.loc?.config?.set("Jail.World", loc.world.name)
                instance.loc?.config?.set("Jail.X", loc.x)
                instance.loc?.config?.set("Jail.Y", loc.y)
                instance.loc?.config?.set("Jail.Z", loc.z)
                instance.loc?.config?.set("Jail.Pitch", loc.pitch as Float)
                instance.loc?.config?.set("Jail.Yaw", loc.yaw as Float)
                instance.loc?.save()

                p.sendMessage(msg.locSetada())
                return true
            }
            p.sendMessage(msg.noPermission())
            return false
        }
        return false
    }

}