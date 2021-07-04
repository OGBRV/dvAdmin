package me.danielvianna.dvadmin.managers

import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class Itens {

    fun giveAdminItens(p: Player) {
        val inv = p.inventory
        inv.clear()
        inv.armorContents = null
        val testKB = newItem("&aTest&cKB", Material.BLAZE_ROD)
        val testNoFall = newItem("&aTest&cNo-Fall", Material.FEATHER)
        val testAutoSoup = newItem("&aTest&cAuto-Soup", Material.BOWL)
        val playerInfo = newItem("&aPlayer&cInfo", Material.STICK)
        val changeFast = newItem("&aChange&cFast", Material.MAGMA_CREAM)
        val jail = newItem("&4Jail", Material.IRON_FENCE)

        val testKBMeta = testKB.itemMeta

        testKBMeta.addEnchant(Enchantment.KNOCKBACK, 25, true)
        testKB.itemMeta = testKBMeta

        inv.setItem(0, testKB)
        inv.setItem(1, testNoFall)
        inv.setItem(3, testAutoSoup)
        inv.setItem(5, jail)
        inv.setItem(7, playerInfo)
        inv.setItem(8, changeFast)

    }

    fun newItem(name: String, material: Material, vararg lore: String): ItemStack {
        val item = ItemStack(material)
        val itemMeta = item.itemMeta
        itemMeta.displayName = name.replace("&", "§")
        itemMeta.lore = lore.toMutableList()
        item.itemMeta = itemMeta
        return item
    }

}