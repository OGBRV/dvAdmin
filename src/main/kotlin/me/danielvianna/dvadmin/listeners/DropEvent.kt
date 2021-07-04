package me.danielvianna.dvadmin.listeners

import me.danielvianna.dvadmin.instance
import me.danielvianna.dvadmin.managers.Mensagens
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerDropItemEvent

class DropEvent : Listener {

    @EventHandler
    fun onPlayerDropItemEvent(e: PlayerDropItemEvent) {
        val p = e.player
        val msg = Mensagens()
        if (instance.admins.contains(p)) {
            e.isCancelled = true
            p.sendMessage(msg.youCant())
            return
        }
    }
}