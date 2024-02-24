package ru.redguy.webinfo.sponge.utils;

import net.kyori.adventure.text.Component;
import org.spongepowered.api.Sponge;
import ru.redguy.webinfo.common.controllers.AbstractChatController;

import java.util.UUID;

public class SpongeChatController extends AbstractChatController {
    @Override
    public void sendMessage(String message) {
        Sponge.server().broadcastAudience().sendMessage(Component.text(message));
    }

    @Override
    public void sendMessage(String message, UUID uuid) {
        Sponge.server().player(uuid).ifPresent(p -> p.sendMessage(Component.text(message)));
    }
}
