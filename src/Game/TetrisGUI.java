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
        // Highscore, the tetris screen, filemenu, the pieces to display, next piece
        // current score, time
        frame = new JFrame();
        JPanel scorePanel = new JPanel();

        //Score Panel Related
        Font scoreFont = new Font("Serif", Font.BOLD, 20);
        JLabel highLabel = new JLabel("HighScore ", JLabel.CENTER);
        JLabel scoreLabel = new JLabel("Score :");
        scoreLabel.setFont(scoreFont);
        scoreLabel.setForeground(Color.WHITE);
        highLabel.setFont(scoreFont);
        highLabel.setForeground(Color.WHITE);
        scorePanel.setLayout(new BoxLayout(scorePanel, BoxLayout.Y_AXIS));
        scorePanel.add(highLabel);
        scorePanel.add(scoreLabel);

        // File Menu Related
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File"); //Have to name it for it to show
        JMenu editMenu = new JMenu ("Edit");

        JMenuItem newMenuItem = new JMenuItem("New Game");
        // NewMenu actionlistener
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
        frame.getContentPane().add(BorderLayout.EAST, scorePanel);
        frame.getContentPane().add(winboard);
        frame.setJMenuBar(menuBar);
        scorePanel.setBackground(Color.gray);
        frame.setSize(450,650);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    public void changeBackground() {
        Color currentColor = frame.getContentPane().getBackground();
        if (currentColor == Color.BLACK) {
            frame.getContentPane().setBackground(Color.white);
        } else {
            frame.getContentPane().setBackground(Color.BLACK);
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
/*
    public void KeyBoardListener implements KeyListener {
        // this triggers when the user hits a keyboard press
        // directional keys only. It'll call movePiece() to move it
    }

    private void movePiece() {
        // move the piece
        // make sure to move it dependent on the pixel of the screen
    }

    public void NewMenuListener implements ActionListener {
        // triggers when clicking "new" in the file menu
        // creates a new game by erasing the tetris display
    }


}
*/
}