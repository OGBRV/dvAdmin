package me.danielvianna.dvadmin.commands

import me.danielvianna.dvadmin.instance
import me.danielvianna.dvadmin.managers.Itens
import me.danielvianna.dvadmin.managers.Mensagens
import org.bukkit.Bukkit
import org.bukkit.GameMode
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

class AdminCommand : CommandExecutor {

    override fun onCommand(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>
    ): Boolean {
        val msg = Mensagens()
        val itens = Itens()

        if (sender !is Player) {
            sender.sendMessage(msg.noPermission())
            return false
        }

        val p = sender.player

        if (command.name.equals("admin", ignoreCase = true)) {
            if (p.hasPermission("dvadmin.use")) {
                if (!instance.admins.contains(p)) {

                    instance.admins.add(p)
                    instance.build.add(p)

                    p.gameMode = GameMode.CREATIVE
                    p.addPotionEffect(PotionEffect(PotionEffectType.INVISIBILITY, 1000, 250, false, false), true)

                    instance.itens[p] = p.inventory.contents

                    itens.giveAdminItens(p)

                    Bukkit.getOnlinePlayers().forEach { target ->
                        if(!target.hasPermission("dvadmin.use")) {
                            target.hidePlayer(p)
                        }
                    }

                    p.sendMessage(msg.modoAtivado())
                    p.sendMessage("§cQuantidade de staffs no modo admin: §f${instance.admins.size}")

                    return true
                }

                p.inventory.contents = instance.itens[p]

                instance.itens.remove(p)
                instance.admins.remove(p)
                instance.build.remove(p)

                Bukkit.getOnlinePlayers().forEach { target ->
                    target.showPlayer(p)
                }
                p.removePotionEffect(PotionEffectType.INVISIBILITY)
                p.gameMode = GameMode.SURVIVAL
                p.sendMessage(msg.modoDesativado())
                p.sendMessage("§cQuantidade de staffs no modo admin: §f${instance.admins.size}")

                return true
            }
            p.sendMessage(msg.noPermission())
        }
        return false
    }
}