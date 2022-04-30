package gui;

import static edu.vt.cs5044.DABGuiName.*;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import edu.vt.cs5044.Coordinate;
import edu.vt.cs5044.DABGame;
import edu.vt.cs5044.DABGrid;
import edu.vt.cs5044.Direction;
import edu.vt.cs5044.DotsAndBoxes;
import edu.vt.cs5044.Player;

import java.awt.*;

@SuppressWarnings("serial")
public class DABPanel extends JPanel {

    private final DotsAndBoxes game;

    private final JLabel p1ScoreLabel;
    private final JLabel p2ScoreLabel;
    private final JLabel turnLabel;
    private final JComboBox<Integer> xCombo;
    private final JComboBox<Integer> yCombo;
    private final JComboBox<Direction> dirCombo;
    private final JButton drawButton;
    private final DABGrid dabGrid;

    public DABPanel(JFrame frame) {

        frame.setJMenuBar(setupMenuBar());
        game = new DABGame();

        xCombo = new JComboBox<>();
        xCombo.setName(X_COMBO);

        yCombo = new JComboBox<>();
        yCombo.setName(Y_COMBO);

        dirCombo = new JComboBox<>(Direction.values());
        dirCombo.setName(DIR_COMBO);

        drawButton = new JButton();
        drawButton.setName(DRAW_BUTTON);
        drawButton.setText("DRAW!");
        drawButton.addActionListener(this::handleDrawButton);

        turnLabel = new JLabel();
        turnLabel.setName(TURN_LABEL);

        p1ScoreLabel = new JLabel();
        p1ScoreLabel.setName(P1_SCORE_LABEL);

        p2ScoreLabel = new JLabel();
        p2ScoreLabel.setName(P2_SCORE_LABEL);

        dabGrid = new DABGrid(game);
        dabGrid.setName(DAB_GRID);

        setupLayout();

        startGame(3);
    }

    private void handleDrawButton(ActionEvent ae) {
        int x = xCombo.getItemAt(xCombo.getSelectedIndex());
        int y = yCombo.getItemAt(yCombo.getSelectedIndex());
        Direction dir = dirCombo.getItemAt(dirCombo.getSelectedIndex());
        Coordinate cord = new Coordinate(x, y);

        boolean drawn = game.drawEdge(cord, dir);
        if (drawn) {
            updateStatus(true);
        }
        updateStatus(false);
    }

    private void updateStatus(boolean updateRequired) {
        if (updateRequired) {
            p1ScoreLabel.setText("PlayerONE: " + game.getScores().get(Player.ONE).toString());
            p2ScoreLabel.setText("PlayerTWO: " + game.getScores().get(Player.TWO).toString());
        }
        if (game.getCurrentPlayer() == null) {
            turnLabel.setText("GAME OVER");
            drawButton.setEnabled(false);
        } else {
            turnLabel.setText("Go Player" + game.getCurrentPlayer() + "!");
        }
        repaint();
    }

    private void updateCombos() {
        xCombo.removeAllItems();
        yCombo.removeAllItems();
        for (int i = 0; i < game.getSize(); i++) {
            xCombo.addItem(i);
            yCombo.addItem(i);
        }
    }

    private void startGame(int size) {
        game.init(size);
        updateCombos();
        updateStatus(true);
        drawButton.setEnabled(true);
    }

    private void setupLayout() {
        setLayout(new BorderLayout());

        JPanel comboPanel = new JPanel();
        comboPanel.add(xCombo);
        comboPanel.add(yCombo);
        comboPanel.add(dirCombo);
        comboPanel.add(drawButton);

        JPanel turnPanel = new JPanel();
        turnPanel.setLayout(new BorderLayout());
        turnLabel.setHorizontalAlignment(JLabel.CENTER);
        turnPanel.add(turnLabel, BorderLayout.PAGE_START);
        turnPanel.add(comboPanel, BorderLayout.CENTER);
        add(turnPanel, BorderLayout.PAGE_START);

        add(dabGrid, BorderLayout.CENTER);

        JPanel scorePanel = new JPanel();
        scorePanel.setLayout(new BorderLayout());
        scorePanel.add(p1ScoreLabel, BorderLayout.LINE_START);
        scorePanel.add(p2ScoreLabel, BorderLayout.LINE_END);
        add(scorePanel, BorderLayout.PAGE_END);
    }

    private JMenuBar setupMenuBar() {

        JFrame menuBar = new JFrame();
        JMenuBar mainBar = new JMenuBar();
        JMenu menu = new JMenu("Game");
        JMenu subMenu = new JMenu("New");
        JMenuItem twoGrid = new JMenuItem("Size 2x2");
        JMenuItem threeGrid = new JMenuItem("Size 3x3");
        JMenuItem fourGrid = new JMenuItem("Size 4x4");

        subMenu.add(twoGrid).addActionListener(e -> {
            startGame(2);
        });
        subMenu.add(threeGrid).addActionListener(e -> {
            startGame(3);
        });
        subMenu.add(fourGrid).addActionListener(e -> {
            startGame(4);
        });

        menu.add(subMenu);
        mainBar.add(menu);
        menuBar.setJMenuBar(mainBar);

        JCheckBoxMenuItem interactive = new JCheckBoxMenuItem("Interactive grid");
        menu.add(interactive);

        interactive.addActionListener(e -> {
            if (interactive.isSelected()) {
                System.out.println("Enable Interactive Mode!");
                dabGrid.setListener(newEdgeDrawn -> updateStatus(true));
            } else {
                dabGrid.removeListener();
                System.out.println("Disable Interactive Mode!");
            }
        });

        return mainBar;
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Dots And Boxes");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JComponent newContentPane = new DABPanel(frame);
        newContentPane.setOpaque(true);
        frame.setContentPane(newContentPane);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(DABPanel::createAndShowGUI);

    }
}