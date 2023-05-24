package me.xra1ny.pong;

import me.xra1ny.gameapi.annotations.EntityInfo;
import me.xra1ny.gameapi.models.gameobject.CollisionSide;
import me.xra1ny.gameapi.models.gameobject.Entity;
import me.xra1ny.gameapi.models.screen.GameScreen;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

@EntityInfo(width = 25, height = 25, maxXVelocity = 0.0, maxYVelocity = 0.0)
public class Block extends Entity {
    @Override
    public void onTick(@NotNull GameScreen gameScreen) {
        final PongScreen pongScreen = (PongScreen) gameScreen;
        final Ball ball = pongScreen.getBall();

        if(collidesWith(ball, pongScreen.getGame().getCollisionTolerance())) {
            pongScreen.getGame().getSoundEngine().playSound("block.wav");
            final CollisionSide collisionSide = getCollisionSide(ball, pongScreen.getGame().getCollisionTolerance());

            if(collisionSide == CollisionSide.TOP) {
                ball.setYVelocity(-3);
            }else if(collisionSide == CollisionSide.BOTTOM) {
                ball.setYVelocity(3);
            }else if(collisionSide == CollisionSide.LEFT) {
                ball.setXVelocity(-3);
            }else if(collisionSide == CollisionSide.RIGHT) {
                ball.setXVelocity(3);
            }else {
                ball.setXVelocity(-ball.getXVelocity());
                ball.setYVelocity(-ball.getYVelocity());
            }

            pongScreen.getGameObjects().remove(this);
        }
    }

    @Override
    public void onRender(@NotNull GameScreen gameScreen, @NotNull Graphics2D gtd) {
        gtd.setColor(Color.WHITE);
        gtd.fillRect((int) getX(), (int) getY(), (int) getWidth(), (int) getHeight());
    }
}
