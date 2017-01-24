package io.github.cyberpost500.hungryshapes.shapes;

import java.awt.Color;
import java.awt.Graphics;

/**
 * This class repesents a circle that can be drawn as an outline or filled.
 */
public class Circle implements Shape {
    private int x;
    private int y;
    private int size;
    private Color color;
    private boolean solid;

    /**
     * Creates a circle located at the specified location, of the specified
     * size, using the specified color.
     */
    public Circle(int x, int y, int size, Color color, boolean solid) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.color = color;
        this.solid = solid;
    }

    @Override
    public void draw(final Graphics graphics) {
        final int offset = getSize()/2;
        final int realX = getX() - offset;
        final int realY = getY() - offset;
        Color previous = graphics.getColor();
        graphics.setColor(color);
        if (solid) {
            System.out.println(String.format("x: %d y: %d size: %d", realX, realY, size));
            System.out.println(String.format("off x: %d y: %d", getX(), getY(), size));
            graphics.fillArc(realX, realY, size, size, 0, 360);
        } else {
            graphics.drawArc(realX, realY, size, size, 0, 360);
        }
        graphics.setColor(previous);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getSize() {
        return size;
    }
    public void setSize(int size) {
        this.size = size;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean isSolid() {
        return solid;
    }

    public boolean setSolid(boolean value) {
        this.solid = value;
    }
}
