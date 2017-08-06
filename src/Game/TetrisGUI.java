package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TetrisGUI {

    public JFrame frame;
    private Board winboard = new Board();

    public static void main(String[] args) {
        TetrisGUI mainApp = new TetrisGUI();
        mainApp.buildGui();
    }

    public void buildGui() {
        // builds and displays GUI here

        frame = new JFrame("Tetris Clone");

        // File Menu Related
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File"); //Have to name it for it to show
        JMenu editMenu = new JMenu ("Edit");

        JMenuItem newMenuItem = new JMenuItem("New Game");
        newMenuItem.addActionListener(new NewMenuListener());
        JMenuItem quitMenuItem = new JMenuItem("Quit");
        quitMenuItem.addActionListener(new QuitMenuListener());
        JMenuItem editMenuItem = new JMenuItem("Change Background Color");
        editMenuItem.addActionListener(new EditMenuListener());
        fileMenu.add(newMenuItem);
        fileMenu.add(quitMenuItem);
        editMenu.add(editMenuItem);
        menuBar.add(fileMenu);
        menuBar.add(editMenu);

        // The actual frame
        frame.getContentPane().add(winboard);
        winboard.setBackground(Color.darkGray);
        frame.setJMenuBar(menuBar);
        frame.setSize(490,615);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    public void changeBackground() {
        Color currentColor = winboard.getBackground();
        if (currentColor == Color.darkGray) {
            winboard.setBackground(Color.white);
        } else {
            winboard.setBackground(Color.darkGray);
        }
    }

    private class EditMenuListener implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
            changeBackground();
            frame.repaint();
        }
    }

    private class QuitMenuListener implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
            frame.setVisible(false);
            JOptionPane.showMessageDialog(null, "Exited the Game.");
            frame.dispose();
        }
    }

    public class NewMenuListener implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
            winboard.cleanseGameBoard();
        }
    }

}