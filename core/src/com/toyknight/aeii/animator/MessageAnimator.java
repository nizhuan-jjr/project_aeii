package com.toyknight.aeii.animator;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.toyknight.aeii.ResourceManager;
import com.toyknight.aeii.renderer.BorderRenderer;
import com.toyknight.aeii.renderer.FontRenderer;
import com.toyknight.aeii.screen.GameScreen;
import com.toyknight.aeii.screen.MapCanvas;

/**
 * @author toyknight on 5/25/2015.
 */
public class MessageAnimator extends MapAnimator {

    private final float delay;
    private final String message_upper;
    private final String message_lower;

    private float alpha = 1.0f;

    public MessageAnimator(MapCanvas canvas, String message, float delay) {
        this(canvas, message, null, delay);
    }

    public MessageAnimator(MapCanvas canvas, String message_upper, String message_lower, float delay) {
        super(canvas);
        this.delay = delay;
        this.message_upper = message_upper;
        this.message_lower = message_lower;
    }

    @Override
    public void render(Batch batch) {
        int width = getCanvas().getViewportWidth();
        int height = message_lower == null ? ts : 2 * ts;
        int x = 0;
        int y = (getCanvas().getViewportHeight() - height) / 2 + ts;
        FontRenderer.setTextAlpha(alpha);
        ResourceManager.setBatchAlpha(batch, alpha);
        batch.draw(ResourceManager.getPanelBackground(), x, y, width, height);
        BorderRenderer.drawTopBottomBorder(batch, x, y, width, height);
        if (message_lower == null) {
            FontRenderer.drawTextCenter(batch, message_upper, x, y, width, height);
        } else {
            FontRenderer.drawTextCenter(batch, message_upper, x, y + height / 2, width, height / 2);
            FontRenderer.drawTextCenter(batch, message_lower, x, y, width, height / 2);
        }
        //restore alpha
        FontRenderer.setTextAlpha(1.0f);
        ResourceManager.setBatchAlpha(batch, 1.0f);
        batch.flush();
    }

    public void update(float delta_time) {
        addStateTime(delta_time);
        if (getStateTime() > delay) {
            alpha -= delta_time / (1.0f / 30) * 0.2f;
            if (alpha < 0f) {
                alpha = 0f;
            }
        }
    }

    public boolean isAnimationFinished() {
        return alpha <= 0f;
    }

}
