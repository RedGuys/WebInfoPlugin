package ru.redguy.webinfo.sponge.utils;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.text.Text;
import ru.redguy.webinfo.common.controllers.AbstractChatController;

import java.util.UUID;

public class SpongeChatController extends AbstractChatController {
    @Override
    public void sendMessage(String message) {
        Sponge.getServer().getBroadcastChannel().send(Text.of(message));
    }

    @Override
    public void sendMessage(String message, UUID uuid) {
        Sponge.getServer().getPlayer(uuid).ifPresent(p -> p.sendMessage(Text.of(message)));
    }
}
