package net.jolene.jojosquared.entity.custom;

import net.jolene.jojosquared.entity.ModEntities;
import net.jolene.jojosquared.item.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec2f;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class SteelBallProjectileEntity extends PersistentProjectileEntity {
    private float rotation;
    public Vec2f groundedOffset;
    private boolean returning = false;
    private int blockHitCount = 0;
    private double returnBounceDamping = 0.2;
    private int stuckTickCounter = 0;
    private BlockPos lastBlockPos = null;


    public SteelBallProjectileEntity(EntityType<? extends PersistentProjectileEntity> type, World world) {
        super(type, world);
    }

    public SteelBallProjectileEntity(World world, PlayerEntity owner) {
        super(ModEntities.STEEL_BALL, owner, world, new ItemStack(ModItems.STEEL_BALL), null);
        this.setVelocity(this.getVelocity().multiply(0.5));
    }

    @Override
    protected ItemStack getDefaultItemStack() {
        return new ItemStack(ModItems.STEEL_BALL);
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        Entity entity = entityHitResult.getEntity();
        if (!entity.getPassengerList().isEmpty()) return;
        if (returning && entity == this.getOwner()) return;

        if (!this.getWorld().isClient) {
            entity.damage(((ServerWorld) this.getWorld()),
                    this.getDamageSources().thrown(this, this.getOwner()), 4);

            Vec3d knockbackDir = entity.getPos().subtract(this.getPos()).normalize();
            double knockbackStrength = 0.5;
            entity.addVelocity(knockbackDir.x * knockbackStrength, 0.1, knockbackDir.z * knockbackStrength);
            entity.velocityModified = true;

            ((ServerWorld) this.getWorld()).spawnParticles(
                    ParticleTypes.CRIT, this.getX(), this.getY(), this.getZ(),
                    10, 0.25, 0.25, 0.25, 1.0);
        }
    }

    @Override
    protected void onBlockHit(BlockHitResult result) {
        if (!this.getWorld().isClient) {
            var blockState = this.getWorld().getBlockState(result.getBlockPos());
            ((ServerWorld) this.getWorld()).spawnParticles(
                    new net.minecraft.particle.BlockStateParticleEffect(ParticleTypes.BLOCK, blockState),
                    this.getX(), this.getY(), this.getZ(),
                    20, 0.25, 0.25, 0.25, 1.0);

            this.getWorld().playSound(null, this.getX(), this.getY(), this.getZ(),
                    SoundEvents.BLOCK_NETHERITE_BLOCK_HIT, SoundCategory.PLAYERS, 1.0f, 1.2f);
        }

        blockHitCount++;
        if (blockHitCount >= 3 && !returning) {
            returning = true;
            this.setNoGravity(true);
            this.setVelocity(Vec3d.ZERO);
            return;
        }

        if (this.getVelocity().lengthSquared() < 0.02) {
            returning = true;
            this.setNoGravity(true);
            this.setVelocity(Vec3d.ZERO);
            return;
        }

        Vec3d normal = Vec3d.of(result.getSide().getVector());
        Vec3d currentVel = this.getVelocity();
        double damping = returning ? returnBounceDamping : 0.8;
        Vec3d reflected = currentVel.subtract(normal.multiply(2 * currentVel.dotProduct(normal))).multiply(damping);

        Vec3d offset = normal.multiply(0.1);
        this.setPosition(this.getX() + offset.x, this.getY() + offset.y, this.getZ() + offset.z);
        this.setVelocity(reflected);

        if (returning) {
            returnBounceDamping = Math.max(returnBounceDamping * 0.5, 0.01);
        }

        var side = result.getSide();
        if (side == Direction.SOUTH) groundedOffset = new Vec2f(215f, 180f);
        else if (side == Direction.NORTH) groundedOffset = new Vec2f(215f, 0f);
        else if (side == Direction.EAST) groundedOffset = new Vec2f(215f, -90f);
        else if (side == Direction.WEST) groundedOffset = new Vec2f(215f, 90f);
        else if (side == Direction.DOWN) groundedOffset = new Vec2f(115f, 180f);
        else if (side == Direction.UP) groundedOffset = new Vec2f(285f, 180f);
    }

    @Override
    public void tick() {
        super.tick();

        // rotation while moving
        if (this.getVelocity().lengthSquared() > 1e-4) {
            this.rotation += 10.0f;
            if (this.rotation >= 360f) this.rotation -= 360f;
        }

        if (this.getWorld().isClient) {
            this.getWorld().addParticleClient(
                    ParticleTypes.CRIT, this.getX(), this.getY() + 0.25, this.getZ(),
                    0, 0, 0);
        }

        if (!this.getWorld().isClient && this.getOwner() instanceof PlayerEntity player) {
            double distance = this.getPos().distanceTo(player.getEyePos());
            if (!returning && distance >= 25.0) {
                returning = true;
                this.setNoGravity(true);
            }

            if (returning) {
                Vec3d targetPos = player.getEyePos().subtract(0, 1.75, 0);
                Vec3d dir = targetPos.subtract(this.getPos());
                double homingDistance = dir.length();

                if (homingDistance < 1.0) {
                    // Return to player with preserved damage
                    ItemStack returned = new ItemStack(ModItems.STEEL_BALL);
                    player.getInventory().insertStack(returned);

                    this.getWorld().sendEntityStatus(this, (byte) 3);
                    this.discard();
                    return;
                }

                Vec3d norm = dir.normalize();
                double returnSpeed = 0.6;
                if (homingDistance < 3.0) {
                    returnSpeed = 0.5 + (homingDistance / 3.0) * 0.5;
                }
                Vec3d newVel = this.getVelocity().add(norm.multiply(0.15));
                newVel = newVel.normalize().multiply(returnSpeed);
                newVel = newVel.add(0, 0.03, 0);

                this.setVelocity(newVel);

                BlockPos currentBlockPos = this.getBlockPos();
                if (lastBlockPos != null && lastBlockPos.equals(currentBlockPos)) {
                    stuckTickCounter++;
                    if (stuckTickCounter >= 10) {
                        // drop as item with preserved damage
                        ItemStack dropped = new ItemStack(ModItems.STEEL_BALL);
                        this.getWorld().spawnEntity(
                                new net.minecraft.entity.ItemEntity(
                                        this.getWorld(), this.getX(), this.getY(), this.getZ(), dropped));
                        this.discard();
                    }
                } else {
                    stuckTickCounter = 0;
                    lastBlockPos = currentBlockPos;
                }
            }
        }
    }

    @Override
    protected void age() {
        if (!returning) {
            super.age();
        }
    }

    @Override
    public boolean isAttackable() {
        return false;
    }

    @Override
    public boolean doesRenderOnFire() {
        return false;
    }

    @Override
    public boolean isPushable() {
        return false;
    }
}