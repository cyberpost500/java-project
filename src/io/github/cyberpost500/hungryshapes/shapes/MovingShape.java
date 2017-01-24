package io.github.cyberpost500.hungryshapes.shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * This class defines a shape that can move around a surface.  In order to get
 * redrawn after it has moved it can notify an observer so that observer knows
 * to call draw.
 */
public class MovingShape extends Circle {

    private int dX;
    private int dY;
    private Rectangle bounds;

    public MovingShape(int x, int y, int size, int dX, int dY,
            Color color, boolean solid, Rectangle bounds) {
        super(x, y, size, color, solid);
        setBounds(bounds);
        setDx(dX);
        setDy(dY);
        setX(x);
        setY(y);
        setSize(size);
    }

    public void enforceBounds() {
        final int currentX = (int) getX();
        final int currentY = (int) getY();
        final Rectangle bounds = getBounds();
        final int left = (int) bounds.getX();
        final int top = (int) bounds.getY();
        final int right = left + (int) bounds.getWidth();
        final int bottom = top + (int) bounds.getHeight();

        if (currentX < left) {
            setX(left);
        }
        if (currentX > right) {
            setX(right);
        }
        if (currentY < top) {
            setY(top);
        }
        if (currentY > bottom) {
            setY(bottom);
        }
    }

    public void move() {
        final int newX = getX()+getDx();
        final int newY = getY()+getDy();
        setX(newX);
        setY(newY);
        enforceBounds();
        System.out.println(this);
    }

    public void setDx(int dX) {
        this.dX = dX;
    }

    public int getDx(){
        return dX;
    }

    public void setDy(int dY) {
        this.dY = dY;
    }

    public int getDy() {
        return dY;
    }

    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    /**
     * Returns a vaguely human readable representation of the object using the following format:
     */
    public String toString(){
        return String.format("[MovingShape x: (%d) y: (%d) size: (%d) dX: (%d) dY: (%d) color: (%s) bounds(%s)",
                getX(), getY(), getSize(), getDx(), getDy(), getColor(), getBounds());
    }

}
