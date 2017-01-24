package io.github.cyberpost500.hungryshapes;

import javax.swing.JFrame;
/**
 * This class defines a very simple JFrame that will later become the game's
 * main window.
 */
@SuppressWarnings("serial")
public class MainWindow extends JFrame {
    private final static String APP_TITLE = "Hungry Shapes";

    public static void main(String[] args) {
        final JFrame frame = new JFrame(APP_TITLE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,600);
        frame.setVisible(true);
    }
}
