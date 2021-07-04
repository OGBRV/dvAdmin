package me.danielvianna.dvadmin.listeners

import me.danielvianna.dvadmin.instance
import me.danielvianna.dvadmin.managers.Itens
import me.danielvianna.dvadmin.managers.Mensagens
import org.bukkit.Material
import org.bukkit.Statistic
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEntityEvent

class PlayerInfo : Listener {

    @EventHandler
    fun onPlayerInteractEntityEvent(e: PlayerInteractEntityEvent) {
        val p = e.player
        val target = e.rightClicked
        val item = p.itemInHand
        val loc = target.location
        val itens = Itens()
        val msg = Mensagens()

        if (item != null) {
            if (item.type == Material.STICK) {
                if (item.equals(itens.newItem("&aPlayer&cInfo", Material.STICK))) {
                    if (instance.admins.contains(p)) {
                        if (target is Player) {

                            val targetIP = target.address.address.hostAddress

                            p.sendMessage("§a[dvAdmin] §7- §cInformações de §f${target.name}")
                            p.sendMessage("§6§lCargo: ${instance.chat?.getPlayerPrefix(target)}")
                            p.sendMessage("§6§lVida: §f${target.health}")
                            p.sendMessage("§6§lFome: §f${target.foodLevel}")
                            p.sendMessage("§6§lKills: §f${target.getStatistic(Statistic.PLAYER_KILLS)}")
                            p.sendMessage("§6§lMortes: §f${target.getStatistic(Statistic.DEATHS)}")
                            p.sendMessage("§6§lEstá voando: §f${target.isFlying}")
                            p.sendMessage("§6§lXP: §f${target.exp}")
                            p.sendMessage("§6§lPing: §fIN-DEV")
                            p.sendMessage("§6§lVelocidade andando: §f${target.walkSpeed}")
                            p.sendMessage("§6§lVelocidade voando: §f${target.flySpeed}")
                            p.sendMessage("§6§lMundo: §f${target.world.name}")
                            p.sendMessage("§6§lCoordenadas: §fX: ${loc.blockX} Y: ${loc.blockY} Z: ${loc.blockZ}")
                            p.sendMessage("§6§lIP: §f$targetIP")

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