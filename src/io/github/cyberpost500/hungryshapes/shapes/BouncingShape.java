package io.github.cyberpost500.hungryshapes.shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * Similar to the moving shape, this is an animated shape, but instead of
 * getting stuck on the edges, it will bounce.
 */
public class BouncingShape extends MovingShape {

    public BouncingShape(int x, int y, int size, int dX, int dY,
            Color color, boolean solid, Rectangle bounds) {
        super(x, y, size, dX, dY, color, solid, bounds);
    }

    @Override
    public void move() {
        final int oldX = getX();
        final int oldY = getY();
        super.move();
        final int currentX = getX();
        final int currentY = getY();
        final Rectangle bounds = getBounds();
        final int left = (int) bounds.getX();
        final int right = left + (int) bounds.getWidth();
        final int top = (int) bounds.getY();
        final int bottom = top + (int) bounds.getHeight();

        if (oldX == currentX && (currentX == left || currentX == right)) {
            setDx(getDx() * -1);
        }

        if (oldY == currentY && (currentY == top || currentY == bottom)) {
            setDy(getDy() * -1);
        }
    }
}
