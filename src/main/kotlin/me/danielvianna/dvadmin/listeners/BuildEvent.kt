package me.danielvianna.dvadmin.listeners

import me.danielvianna.dvadmin.instance
import me.danielvianna.dvadmin.managers.Mensagens
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.block.BlockPlaceEvent
import org.bukkit.event.player.PlayerQuitEvent

class BuildEvent : Listener {

    @EventHandler
    fun onBlockBreakEvent(e: BlockBreakEvent) {
        val p = e.player
        val msg = Mensagens()
        if (instance.build.contains(p)) {
            e.isCancelled = true
            p.sendMessage(msg.ativarBuild())
        }
    }

    @EventHandler
    fun onBlockPlaceEvent(e: BlockPlaceEvent) {
        val p = e.player
        val msg = Mensagens()
        if (instance.build.contains(p)) {
            e.isCancelled = true
            p.sendMessage(msg.ativarBuild())
            return
        }
        e.isCancelled = false
    }

    @EventHandler
    fun onPlayerQuitEvent(e: PlayerQuitEvent) {
        val p = e.player
        val msg = Mensagens()
        if (instance.admins.contains(p)) {
            instance.admins.remove(p)
            p.inventory.contents = instance.itens[p]
            return
        }
    }

}