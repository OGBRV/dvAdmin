package me.danielvianna.dvadmin.managers

import me.danielvianna.dvadmin.config

class Mensagens {

    fun noPermission() = config().getString("Mensagens.Sem-Permissao").replace("&", "§")

    fun modoAtivado() = config().getString("Mensagens.Modo-Ativado").replace("&", "§")

    fun modoDesativado() = config().getString("Mensagens.Modo-Desativado").replace("&", "§")

    fun locSetada() = config().getString("Mensagens.Localizacao-Setada").replace("&", "§")

    fun notAPlayer() = config().getString("Mensagens.Not-A-Player").replace("&", "§")

    fun puxadoPrajail() = config().getString("Mensagens.Puxado-Para-A-Jail").replace("&", "§")

    fun playerPuxado() = config().getString("Mensagens.Player-Puxado").replace("&", "§")

    fun trocaRealizada() = config().getString("Mensagens.Troca-Realizada").replace("&", "§")

    fun invAberto() = config().getString("Mensagens.Inventario-Aberto").replace("&", "§")

    fun noFallTestado() = config().getString("Mensagens.No-Fall-Testado").replace("&", "§")

    fun buildOn() = config().getString("Mensagens.Build-Ativada").replace("&", "§")

    fun buildOff() = config().getString("Mensagens.Build-Desativada").replace("&", "§")

    fun ativarBuild() = config().getString("Mensagens.Ative-A-Build").replace("&", "§")

    fun youCant() = config().getString("Mensagens.Impossivel-Dropar").replace("&", "§")
}