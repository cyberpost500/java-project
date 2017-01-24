package io.github.cyberpost500.hungryshapes;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
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

    public static void main(String[] args) {
        final MainWindow window = new MainWindow();
        window.getContentPane().add(new JPanel(true) {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setFont(new Font("Default", Font.PLAIN, 32));
                g.setColor(Color.BLUE);
                g.drawString("Hello, World!", 10, 40);
            }
        });

        window.setVisible(true);
    }
}
