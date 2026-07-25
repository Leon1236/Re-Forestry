package com.leon1236.reforestry.modules.features;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.UnaryOperator;

import org.jetbrains.annotations.Nullable;

import net.fabricmc.fabric.api.menu.v1.ExtendedMenuType;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;

import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;

import com.leon1236.reforestry.api.core.IBlockSubtype;
import com.leon1236.reforestry.api.core.IItemSubtype;

public interface IFeatureRegistry {
    Identifier getModuleId();

    default <B extends Block, S extends IBlockSubtype> FeatureBlockGroup.Builder<B, S> blockGroup(
            BiFunction<S, BlockBehaviour.Properties, B> constructor, S[] types) {
        return new FeatureBlockGroup.Builder<B, S>(getModuleId(), constructor).types(types);
    }

    default <I extends Item, S extends IItemSubtype> FeatureItemGroup.Builder<I, S> itemGroup(
            BiFunction<S, Item.Properties, I> constructor, S[] types) {
        return new FeatureItemGroup.Builder<I, S>(getModuleId(), constructor).types(types);
    }

    default <B extends Block> FeatureBlock<B> block(String name, Function<BlockBehaviour.Properties, B> constructor,
                                                      @Nullable BiFunction<B, Item.Properties, BlockItem> itemConstructor) {
        Identifier registryId = Identifier.fromNamespaceAndPath(getModuleId().getNamespace(), name);
        Function<B, BlockItem> boundItemConstructor = itemConstructor == null
                ? null
                : block -> itemConstructor.apply(block, FeatureItem.seededBlockItemProperties(registryId));
        return new FeatureBlock<>(getModuleId(), name, registryId,
                () -> constructor.apply(FeatureBlock.seededProperties(registryId)), boundItemConstructor);
    }

    default <I extends Item> FeatureItem<I> item(String name, Function<Item.Properties, I> constructor) {
        Identifier registryId = Identifier.fromNamespaceAndPath(getModuleId().getNamespace(), name);
        return new FeatureItem<>(getModuleId(), name, registryId,
                () -> constructor.apply(FeatureItem.seededProperties(registryId)));
    }

    default FeatureItem<Item> item(String name) {
        return item(name, Item::new);
    }

    default FeatureCreativeTab creativeTab(String name, Consumer<CreativeModeTab.Builder> builder) {
        Identifier registryId = Identifier.fromNamespaceAndPath(getModuleId().getNamespace(), name);
        return new FeatureCreativeTab(getModuleId(), name, registryId, builder);
    }

    default <T> FeatureDataComponent<T> dataComponent(String name, UnaryOperator<DataComponentType.Builder<T>> builder) {
        Identifier registryId = Identifier.fromNamespaceAndPath(getModuleId().getNamespace(), name);
        return new FeatureDataComponent<>(getModuleId(), name, registryId, builder);
    }

    default <T extends BlockEntity> FeatureBlockEntityType<T> blockEntityType(
            String name, FabricBlockEntityTypeBuilder.Factory<T> factory, Block... blocks) {
        Identifier registryId = Identifier.fromNamespaceAndPath(getModuleId().getNamespace(), name);
        return new FeatureBlockEntityType<>(getModuleId(), name, registryId, factory, blocks);
    }

    default <E extends Entity> FeatureEntityType<E> entity(
            String name, EntityType.EntityFactory<E> factory, MobCategory category,
            UnaryOperator<EntityType.Builder<E>> configurator) {
        Identifier registryId = Identifier.fromNamespaceAndPath(getModuleId().getNamespace(), name);
        return new FeatureEntityType<>(getModuleId(), name, registryId, factory, category, configurator);
    }

    default <T extends AbstractContainerMenu, D> FeatureMenuType<T, D> menuType(
            String name, ExtendedMenuType.ExtendedFactory<T, D> factory,
            StreamCodec<? super RegistryFriendlyByteBuf, D> streamCodec) {
        Identifier registryId = Identifier.fromNamespaceAndPath(getModuleId().getNamespace(), name);
        return new FeatureMenuType<>(getModuleId(), name, registryId, factory, streamCodec);
    }

    default FeatureParticleType<net.minecraft.core.particles.SimpleParticleType> particleType(String name) {
        Identifier registryId = Identifier.fromNamespaceAndPath(getModuleId().getNamespace(), name);
        return new FeatureParticleType<>(getModuleId(), name, registryId, FabricParticleTypes.simple());
    }

    default <T extends ParticleOptions> FeatureParticleType<T> particleType(String name, ParticleType<T> type) {
        Identifier registryId = Identifier.fromNamespaceAndPath(getModuleId().getNamespace(), name);
        return new FeatureParticleType<>(getModuleId(), name, registryId, type);
    }
}
