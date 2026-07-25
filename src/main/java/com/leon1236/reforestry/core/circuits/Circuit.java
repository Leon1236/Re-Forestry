package com.leon1236.reforestry.core.circuits;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;

import com.leon1236.reforestry.api.circuits.ICircuit;

import java.util.List;

public abstract class Circuit implements ICircuit {
    private final String uid;

    protected Circuit(String uid) {
        this.uid = uid;
    }

    @Override
    public String getId() {
        return "reforestry." + this.uid;
    }

    @Override
    public String getTranslationKey() {
        return "for.circuit." + this.uid;
    }

    @Override
    public void addTooltip(List<Component> list) {
        list.add(Component.translatable(getTranslationKey()).withStyle(ChatFormatting.GRAY));
        int i = 1;
        while (true) {
            String descriptionKey = getTranslationKey() + ".description." + i;
            if (!Component.translatable(descriptionKey).getString().equals(descriptionKey)) {
                list.add(Component.literal(" - ").append(Component.translatable(descriptionKey)).withStyle(ChatFormatting.GRAY));
                i++;
            } else {
                break;
            }
        }
    }
}
