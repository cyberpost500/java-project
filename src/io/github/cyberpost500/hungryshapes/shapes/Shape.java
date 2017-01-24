package io.github.cyberpost500.hungryshapes.shapes;

import java.awt.Graphics;

/**
 * Any shape can be drawn to a graphics object.  The constructor of the shape
 * should handle initializing all the typical values and tracking elements like
 * the position, color, etc.  These details are specific to each individual
 * Shape, but what they all have in common is the ability to draw themselves to
 * a Graphics object.
 */
public interface Shape {

    /**
     * Draw the shape to the provided Graphics object.
     * @param graphics The graphics contentx in which to draw the shape.
     */
    public void draw(final Graphics graphics);
}
