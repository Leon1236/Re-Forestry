package com.leon1236.reforestry.mixin;

import java.util.List;

import com.mojang.datafixers.util.Pair;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;

@Mixin(StructureTemplatePool.class)
public interface StructureTemplatePoolAccessor {
    @Accessor("templates")
    ObjectArrayList<StructurePoolElement> reforestry$getTemplates();

    @Accessor("rawTemplates")
    List<Pair<StructurePoolElement, Integer>> reforestry$getRawTemplates();

    @Mutable
    @Accessor("rawTemplates")
    void reforestry$setRawTemplates(List<Pair<StructurePoolElement, Integer>> value);
}
