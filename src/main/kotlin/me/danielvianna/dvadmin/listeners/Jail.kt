package me.danielvianna.dvadmin.listeners

import me.danielvianna.dvadmin.instance
import me.danielvianna.dvadmin.managers.Itens
import me.danielvianna.dvadmin.managers.Mensagens
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEntityEvent

class Jail : Listener {

    @EventHandler
    fun onPlayerInteractEntityEvent(e: PlayerInteractEntityEvent) {
        val p = e.player
        val target = e.rightClicked
        val item = p.itemInHand
        val msg = Mensagens()
        val itens = Itens()

        if (item != null) {
            if (item.type == Material.IRON_FENCE) {
                if (item.equals(itens.newItem("&4Jail", Material.IRON_FENCE))) {
                    if (instance.admins.contains(p)) {
                        if (target is Player) {

                            val location = Location(
                                Bukkit.getWorld(instance.loc?.config?.get("Jail.World") as String),
                                instance.loc?.config?.getDouble("Jail.X") as Double,
                                instance.loc?.config?.getDouble("Jail.Y") as Double,
                                instance.loc?.config?.getDouble("Jail.Z") as Double,
                                (instance.loc?.config?.getInt("Jail.Yaw") as Int).toFloat(),
                                (instance.loc?.config?.getInt("Jail.Pitch") as Int).toFloat()
                            )

                            target.teleport(location)
                            target.sendMessage(msg.puxadoPrajail())
                            p.sendMessage(msg.playerPuxado())
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