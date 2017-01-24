package io.github.cyberpost500.hungryshapes;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

import io.github.cyberpost500.hungryshapes.shapes.Circle;
import io.github.cyberpost500.hungryshapes.shapes.MovingShape;
import io.github.cyberpost500.hungryshapes.shapes.Shape;

/**
 * This class defines a very simple JFrame that will later become the game's
 * main window.
 */
@SuppressWarnings("serial")
public class MainWindow extends JFrame {
    private final static String APP_TITLE = "Hungry Shapes";

    /**
     * Creates a MainWindow that's 800x600 and will cause the app to exit if it
     * is closed.
     */
    public MainWindow() {
        super(APP_TITLE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
    }

    /**
     * Creates a rectangle using the provided dimension for the width and
     * height.
     * @param dimension The Dimension to be converted.
     */
    private static Rectangle dimensionToRectangle(final Dimension dimension) {
        return new Rectangle(0, 0, (int)dimension.getWidth(), (int)dimension.getHeight());
    }

    public static void main(String[] args) {
        final Shape circle = new Circle(125, 125, 50, Color.RED, true);
        final MainWindow window = new MainWindow();
        final MovingShape moving = new MovingShape(0, 0, 50, 3, 3, Color.GREEN, true, dimensionToRectangle(window.getSize()));
        final JPanel panel = new JPanel(true) {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);

                g.setFont(new Font("Default", Font.PLAIN, 32));
                g.setColor(Color.BLUE);
                g.drawString("Hello, World!", 10, 40);

                circle.draw(g);
                g.drawArc(100, 100, 50, 50, 0, 360);
            }
        };
        window.getContentPane().add(panel);

        /**
         * The thread responsible for moving the shape.
         */
        UpdateRunner mover = new UpdateRunner(1){
            @Override
            public void update() {
                moving.move();
                //If you want some feedback while debugging modifications, you
                //can uncomment the next line.
                //System.out.println("moved; " + moving);
            }
        };
        final Thread moverThread = new Thread(mover);
        moverThread.start();

        /**
         * The thread responsible for updating the display after the shape has
         * moved.
         */
        UpdateRunner runnable = new UpdateRunner(30) {
            @Override
            public void update(){
                panel.invalidate();
                panel.repaint(dimensionToRectangle(window.getSize()));
            }
        };
        final Thread updaterThread = new Thread(runnable);
        updaterThread.start();

        window.setVisible(true);
    }
}

/**
 * This runnable represents a sort of worker thread.  Any task that needs to be
 * scheduled to run a certain number of times per second can use this class to
 * create a runnable and place it in a thread.  It will stop when it has been
 * interrupted, or the setRunning method can be used to stop it.
 */
abstract class UpdateRunner implements Runnable {
    boolean running;
    private int delay;

    /**
     * Creates an UpdateRunner that will call its update method fps times per
     * second.
     * @param fps The number of updates to run per second.
     */
    public UpdateRunner(int fps) {
        delay = (int) (60.0/(double)fps);
        running = true;
    }

    @Override
    public void run() {
        while (isRunning()) {
            try{
                Thread.sleep(delay);
            }catch(InterruptedException ie) {
                setRunning(false);
                break;
            }
            update();
        }
    }

    /**
     * This method must be overridden to accomplish some task.
     */
    abstract void update();

    /**
     * This check is used by the run method to make sure it still needs to be
     * running.
     */
    public boolean isRunning() {
        boolean running = false;
        synchronized(this) {
            running = this.running;
        }
        return running;
    }

    /**
     * This can be used to stop additional iterations (or potentially to
     * recycle the runnable in a new thread).
     * @param value The new value for the running field.
     */
    public void setRunning(boolean value) {
        synchronized(this) {
            this.running = value;
        }
    }
}
