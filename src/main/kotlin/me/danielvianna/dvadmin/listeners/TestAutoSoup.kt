package me.danielvianna.dvadmin.listeners

import me.danielvianna.dvadmin.instance
import me.danielvianna.dvadmin.managers.Itens
import me.danielvianna.dvadmin.managers.Mensagens
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEntityEvent

class TestAutoSoup : Listener {

    @EventHandler
    fun onPlayerInteractEntityEvent(e: PlayerInteractEntityEvent) {
        val p = e.player
        val target = e.rightClicked
        val item = p.itemInHand
        val msg = Mensagens()
        val itens = Itens()

        if (item != null) {
            if (item.type == Material.BOWL) {
                if (item.equals(itens.newItem("&aTest&cAuto-Soup", Material.BOWL))) {
                    if (instance.admins.contains(p)) {
                        if (target is Player) {
                            if(target.health < 1.0) {
                                target.health = 20.0
                                p.sendMessage(msg.invAberto().replace("{player}", target.name))
                                p.openInventory(target.inventory)
                                target.health = target.health / 2
                                return
                            }
                            p.sendMessage(msg.invAberto().replace("{player}", target.name))
                            p.openInventory(target.inventory)
                            target.health = target.health / 2
                            return
                        }
                        p.sendMessage(msg.notAPlayer())
                        return
                    }
                    return
                }
                return
            }
        }

    }

}