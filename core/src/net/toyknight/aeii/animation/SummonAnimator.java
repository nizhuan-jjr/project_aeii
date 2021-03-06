package net.toyknight.aeii.animation;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import net.toyknight.aeii.entity.Unit;
import net.toyknight.aeii.renderer.CanvasRenderer;
import net.toyknight.aeii.system.AER;
import net.toyknight.aeii.utils.GraphicsUtil;

/**
 * @author toyknight 5/21/2015.
 */
public class SummonAnimator extends UnitAnimator {

    private final int target_x;
    private final int target_y;

    private final Animation<TextureRegion> spark_animation;

    public SummonAnimator(Unit summoner, int target_x, int target_y) {
        this.target_x = target_x;
        this.target_y = target_y;
        this.addLocation(target_x, target_y);
        if (summoner != null) {
            this.addUnit(summoner, "summoner");
        }
        Texture texture_white_spark = AER.resources.getWhiteSparkTexture();
        this.spark_animation = new Animation(1f / 15, GraphicsUtil.createFrames(texture_white_spark, 6, 1));
    }

    @Override
    public void render(Batch batch) {
        int offset = ts() * 2 / 24;
        Unit summoner = getUnit("summoner");
        int target_sx = getCanvas().getXOnScreen(target_x);
        int target_sy = getCanvas().getYOnScreen(target_y);
        CanvasRenderer.drawUnitWithInformation(batch, summoner, summoner.getX(), summoner.getY());
        batch.draw(spark_animation.getKeyFrame(getStateTime(), true), target_sx - offset, target_sy - offset, ts(), ts());
        batch.draw(spark_animation.getKeyFrame(getStateTime(), true), target_sx - offset, target_sy + offset, ts(), ts());
        batch.draw(spark_animation.getKeyFrame(getStateTime(), true), target_sx + offset, target_sy - offset, ts(), ts());
        batch.draw(spark_animation.getKeyFrame(getStateTime(), true), target_sx + offset, target_sy + offset, ts(), ts());
        batch.draw(spark_animation.getKeyFrame(getStateTime(), true), target_sx, target_sy, ts(), ts());
        batch.flush();
    }

    @Override
    public void update(float delta) {
        addStateTime(delta);
    }

    @Override
    public boolean isAnimationFinished() {
        return getStateTime() >= 1f / 15 * 12;
    }

}
