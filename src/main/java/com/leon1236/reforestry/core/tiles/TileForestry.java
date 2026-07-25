package com.leon1236.reforestry.core.tiles;

import net.fabricmc.fabric.api.menu.v1.ExtendedMenuProvider;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import com.leon1236.reforestry.api.IForestryApi;
import com.leon1236.reforestry.api.core.IErrorLogic;
import com.leon1236.reforestry.api.core.IErrorLogicSource;

public abstract class TileForestry extends BlockEntity implements IErrorLogicSource, ExtendedMenuProvider<BlockPos> {
    private final IErrorLogic errorLogic = IForestryApi.INSTANCE.getErrorManager().createErrorLogic();
    private final TickHelper tickHelper;

    protected TileForestry(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        this.tickHelper = new TickHelper(pos.hashCode());
    }

    protected final boolean updateOnInterval(int tickInterval) {
        tickHelper.onTick();
        return tickHelper.updateOnInterval(tickInterval);
    }

    public boolean isUsableByPlayer(Player player) {
        return TileUtil.isUsableByPlayer(player, this);
    }

    protected boolean isRedstoneActivated() {
        Level level = getLevel();
        return level != null && level.getBestNeighborSignal(getBlockPos()) > 0;
    }

    @Override
    public final IErrorLogic getErrorLogic() {
        return errorLogic;
    }

    public Component getTitle() {
        return Component.translatable(getBlockState().getBlock().getDescriptionId());
    }

    @Override
    public Component getDisplayName() {
        return getTitle();
    }

    @Override
    public BlockPos getScreenOpeningData(ServerPlayer player) {
        return getBlockPos();
    }
}
