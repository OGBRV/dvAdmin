package me.danielvianna.dvadmin.listeners

import me.danielvianna.dvadmin.instance
import me.danielvianna.dvadmin.managers.Itens
import me.danielvianna.dvadmin.managers.Mensagens
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEntityEvent
import org.bukkit.util.Vector

class TestNoFall : Listener {

    @EventHandler
    fun onPlayerInteractEntityEvent(e: PlayerInteractEntityEvent) {
        val p = e.player
        val target = e.rightClicked
        val item = p.itemInHand
        val loc = target.location
        val msg = Mensagens()
        val itens = Itens()

        if (item != null) {
            if (item.type == Material.FEATHER) {
                if (item.equals(itens.newItem("&aTest&cNo-Fall", Material.FEATHER))) {
                    if (instance.admins.contains(p)) {
                        if (target is Player) {
                            target.velocity = Vector(0, 15, 0)
                            p.sendMessage(msg.noFallTestado())
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