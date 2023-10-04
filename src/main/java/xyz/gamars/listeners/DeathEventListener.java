package xyz.gamars.listeners;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.data.Keys;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Cause;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.cause.entity.damage.source.DamageSource;
import org.spongepowered.api.event.entity.DamageEntityEvent;
import org.spongepowered.api.event.message.MessageEvent;


public class DeathEventListener {

    @Listener
    public void deathCheck(DamageEntityEvent event) {
        if (event.entity() instanceof Player) {
            Player player = (Player) event.entity();
            if (event.willCauseDeath()) {
                for (Player onlinePlayer : Sponge.server().onlinePlayers()) {
                    onlinePlayer.offer(Keys.HEALTH, 0.0);
                }
                Component text = Component.text(String.format("%s died and ruined it for everyone", player.name()), NamedTextColor.AQUA);
                Sponge.server().broadcastAudience().sendMessage(text);
            }
        }
    }

    @Listener
    public void messageCheck(MessageEvent event) {
        if (event.source() instanceof DamageSource) {
            event.setMessage(Component.text(""));
        }

    }


}

