package net.jolene.jojosquared.stand.api.hitbox;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;

public class StandAbilityHitbox {
    private final Box aabb;
    private final int createdTick;
    private final int life;

    public StandAbilityHitbox(Vec3d pos, Vec3d size, int createdTick, int life)
    {
        this.createdTick = createdTick;
        this.life = life;
        this.aabb = new Box(pos.subtract(size), pos.add(size));
    }

    public int getCreatedTick() {
        return createdTick;
    }

    public int getDestroyTick() {
        return getCreatedTick() + life;
    }

    public List<LivingEntity> getEntitiesInside(World world)
    {
        return world.getEntitiesByClass(LivingEntity.class, this.aabb, EntityPredicates.VALID_LIVING_ENTITY);
    }

    @Environment(EnvType.CLIENT)
    public void visualize(MatrixStack matrices, VertexConsumerProvider vertexConsumers)
    {
        VertexConsumer buf = vertexConsumers.getBuffer(RenderLayer.getDebugQuads());
        MatrixStack.Entry entry = matrices.peek();

        double minX = aabb.minX;
        double minY = aabb.minY;
        double minZ = aabb.minZ;
        double maxX = aabb.maxX;
        double maxY = aabb.maxY;
        double maxZ = aabb.maxZ;

        float r = 1f, g = 0f, b = 0f, a = 0.3f;

        putQuad(buf, entry,
                minX, minY, maxZ,
                maxX, minY, maxZ,
                maxX, maxY, maxZ,
                minX, maxY, maxZ, r, g, b, a);
        putQuad(buf, entry,
                maxX, minY, minZ,
                minX, minY, minZ,
                minX, maxY, minZ,
                maxX, maxY, minZ, r, g, b, a);
        putQuad(buf, entry,
                minX, minY, minZ,
                minX, minY, maxZ,
                minX, maxY, maxZ,
                minX, maxY, minZ, r, g, b, a);
        putQuad(buf, entry,
                maxX, minY, maxZ,
                maxX, minY, minZ,
                maxX, maxY, minZ,
                maxX, maxY, maxZ, r, g, b, a);
        putQuad(buf, entry,
                minX, maxY, maxZ,
                maxX, maxY, maxZ,
                maxX, maxY, minZ,
                minX, maxY, minZ, r, g, b, a);
        putQuad(buf, entry,
                minX, minY, minZ,
                maxX, minY, minZ,
                maxX, minY, maxZ,
                minX, minY, maxZ, r, g, b, a);
    }

    @Environment(EnvType.CLIENT)
    private void putQuad(VertexConsumer buf, MatrixStack.Entry entry,
                         double x1, double y1, double z1,
                         double x2, double y2, double z2,
                         double x3, double y3, double z3,
                         double x4, double y4, double z4,
                         float r, float g, float b, float a) {
        buf.vertex(entry.getPositionMatrix(), (float) x1, (float) y1, (float) z1).color(r, g, b, a);
        buf.vertex(entry.getPositionMatrix(), (float) x2, (float) y2, (float) z2).color(r, g, b, a);
        buf.vertex(entry.getPositionMatrix(), (float) x3, (float) y3, (float) z3).color(r, g, b, a);
        buf.vertex(entry.getPositionMatrix(), (float) x4, (float) y4, (float) z4).color(r, g, b, a);
    }
}