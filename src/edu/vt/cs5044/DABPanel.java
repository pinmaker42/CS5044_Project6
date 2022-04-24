package edu.vt.cs5044;

import static edu.vt.cs5044.DABGuiName.*;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

// 2022.Spring

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

        // Add a menu bar to the frame that will contain this panel
        frame.setJMenuBar(setupMenuBar());

        // Create a new DABGame instance that will act as the game engine
        game = new DABGame();

        // Next we construct the user interface components, and assign a unique name to each
        // The name is required so that the test code can identify and fetch each component

        // This combo box is initially empty; you'll add option values in updateCombos() below
        xCombo = new JComboBox<>();
        xCombo.setName(X_COMBO);

        // This combo box is initially empty; you'll add option values in updateCombos() below
        yCombo = new JComboBox<>();
        yCombo.setName(Y_COMBO);

        // This combo box is pre-populated with all the valid Direction enum values
        dirCombo = new JComboBox<>(Direction.values());
        dirCombo.setName(DIR_COMBO);

        // The draw button will attempt to draw an edge, as specified by the combo box selections
        drawButton = new JButton();
        drawButton.setName(DRAW_BUTTON);
        // TODO: Set the text to be displayed on the draw button
        // TODO: Set the action of the draw button to call handleDrawButton()
        // NOTE: For any credit, an anonymous inner class must be used
        // NOTE: For more credit, a lambda expression must be used instead
        // NOTE: For full credit, a method reference must be used instead

        // The JLabel components begin empty; you'll set the relevant text in updateStatus() below
        turnLabel = new JLabel();
        turnLabel.setName(TURN_LABEL);

        p1ScoreLabel = new JLabel();
        p1ScoreLabel.setName(P1_SCORE_LABEL);

        p2ScoreLabel = new JLabel();
        p2ScoreLabel.setName(P2_SCORE_LABEL);

        // This is the custom DABGrid component provided by the library
        dabGrid = new DABGrid(game);
        dabGrid.setName(DAB_GRID);

        // Perform the layout of all the user interface components
        setupLayout();

        // Begin a new 3x3 game by default
        startGame(3);

    }

    private void handleDrawButton(ActionEvent ae) {
        // TODO: First read the values currently selected by the combo boxes
        // TODO: Then call drawEdge() using the selected values and retain the returned boolean
        // TODO: Finally call updateStatus() using the returned value of drawEdge() as the argument
    }

    private void updateStatus(boolean updateRequired) {
        // TODO: If parameter is false, do nothing and just return; otherwise do the following:
        // TODO: Read the game status via accessors, then set each JLabel text accordingly
        // TODO: Disable the draw button, if the game is over as indicated by getCurrentPlayer()
        // TODO: Call repaint() at the end of this method, in order to actually render any changes
    }

    private void updateCombos() {
        // TODO: Update the coordinate combo box options, based on the current size of the grid
        // NOTE: For any credit, the combo options must handle games up to a size of 4x4
        // NOTE: For more credit, the combo options must exactly match the size of the game
        // NOTE: For full credit, a single loop must be used to eliminate redundancies
    }

    private void startGame(int size) {
        // TODO: Initialize a new game of the specified size
        // TODO: Call updateCombos() and updateStatus(true)
        // TODO: Enable the draw button, which may have been disabled from a previous game
    }

    private void setupLayout() {
        // TODO: Layout this panel and all its sub-panels and components
        // NOTE: For any credit, the layout must include all components
        // NOTE: For more credit, the layout must look reasonably neat
        // NOTE: For full credit, the layout must be reasonably responsive to resizing of the frame
    }

    private JMenuBar setupMenuBar() {
        // TODO: Create a new JMenuBar and populate it with the required items
        // TODO: Call startGame() with the correct size when a "new game" item is selected
        // TODO: Handle enabling/disabling interactive mode via a JCheckBoxMenuItem
        // NOTE: For any credit, anonymous inner classes must be used
        // NOTE: For more credit, lambda expressions must be used instead
        // NOTE: For full credit, a method reference must be used within the interactive mode lambda
        return null; // TODO: replace this placeholder so that it returns the JMenuBar
    }

    private static void createAndShowGUI() {
        // This is boilerplate code; please just use this exactly as it is.
        JFrame frame = new JFrame("Dots And Boxes");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JComponent newContentPane = new DABPanel(frame);
        newContentPane.setOpaque(true);
        frame.setContentPane(newContentPane);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        // This is boilerplate code; please be sure to use this exactly as-is.
        // Note that a method reference is used to simplify the syntax
        SwingUtilities.invokeLater(DABPanel::createAndShowGUI);
    }

}
