package net.blay09.mods.waystones.api;

import com.mojang.datafixers.util.Either;
import net.blay09.mods.waystones.api.cost.*;
import net.blay09.mods.waystones.api.error.WaystoneTeleportError;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;

public interface InternalMethods {

    Either<WaystoneTeleportContext, WaystoneTeleportError> createDefaultTeleportContext(Entity entity, Waystone waystone, Consumer<WaystoneTeleportContext> init);

    Either<WaystoneTeleportContext, WaystoneTeleportError> createCustomTeleportContext(Entity entity, Waystone waystone);

    WaystoneTeleportContext createUnboundTeleportContext(Entity entity);

    Either<List<Entity>, WaystoneTeleportError> tryTeleport(WaystoneTeleportContext context);

    List<Entity> forceTeleport(WaystoneTeleportContext context);

    Optional<Waystone> getWaystoneAt(Level level, BlockPos pos);

    Optional<Waystone> getWaystone(Level level, UUID uuid);

    ItemStack createAttunedShard(Waystone warpPlate);

    ItemStack createBoundScroll(Waystone waystone);

    Optional<Waystone> placeWaystone(Level level, BlockPos pos, WaystoneStyle style);

    Optional<Waystone> placeSharestone(Level level, BlockPos pos, DyeColor color);

    Optional<Waystone> placeWarpPlate(Level level, BlockPos pos);

    Optional<Waystone> getBoundWaystone(Player player, ItemStack itemStack);

    void setBoundWaystone(ItemStack itemStack, @Nullable Waystone waystone);

    Cost calculateCost(WaystoneTeleportContext context);

    void registerCostType(CostType<?> costType);

    void registerCostModifier(CostModifier<?, ?> costModifier);

    void registerCostVariableResolver(CostVariableResolver costVariableResolver);

    void registerCostConditionPredicate(CostConditionResolver costConditionResolver);

    void registerCostParameterSerializer(CostParameterSerializer<?> costParameterSerializer);
}
