package com.leon1236.reforestry.api.core;

import net.minecraft.resources.Identifier;

public interface IError {
    Identifier getId();

    String getDescriptionTranslationKey();

    String getHelpTranslationKey();

    Identifier getSprite();
}
