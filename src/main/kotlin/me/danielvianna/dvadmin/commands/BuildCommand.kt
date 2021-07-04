package me.danielvianna.dvadmin.commands

import me.danielvianna.dvadmin.instance
import me.danielvianna.dvadmin.managers.Mensagens
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class BuildCommand : CommandExecutor {

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

        if (command.name.equals("build", ignoreCase = true)) {
            if (p.hasPermission("dvadmin.use")) {
                if (!instance.build.contains(p)) {
                    instance.build.add(p)
                    p.sendMessage(msg.buildOff())
                } else {
                    instance.build.remove(p)
                    p.sendMessage(msg.buildOn())
                }
            }
        }
        return false
    }
}