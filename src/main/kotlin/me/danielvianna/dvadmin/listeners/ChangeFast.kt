package me.danielvianna.dvadmin.listeners

import me.danielvianna.dvadmin.instance
import me.danielvianna.dvadmin.managers.Itens
import me.danielvianna.dvadmin.managers.Mensagens
import org.bukkit.Bukkit
import org.bukkit.GameMode
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent

class ChangeFast : Listener {

    @EventHandler
    fun onPlayerInteractEvent(e: PlayerInteractEvent) {
        val p = e.player as Player
        val item = e.item
        val msg = Mensagens()
        val itens = Itens()

        if (item != null) {
            if (item.equals(itens.newItem("&aChange&cFast", Material.MAGMA_CREAM))) {
                if (instance.admins.contains(p)) {
                    val sh = Bukkit.getScheduler()
                    if (p.gameMode == GameMode.CREATIVE) {
                        p.gameMode = GameMode.SURVIVAL
                        Bukkit.getOnlinePlayers().forEach { target ->
                            target.showPlayer(p)
                        }
                        p.health = 20.0
                        sh.runTaskLater(instance, {
                            p.gameMode = GameMode.CREATIVE
                            Bukkit.getOnlinePlayers().forEach { target ->
                                target.hidePlayer(p)
                            }
                        }, 10)
                        p.sendMessage(msg.trocaRealizada())
                        return
                    }
                    e.isCancelled = true
                }
                return
            }
        }
    }

}