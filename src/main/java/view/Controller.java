package view;

import javax.swing.*;
import java.awt.*;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

public class Controller {

  public void redrawDices() {
    for (int die = 0; die < viewModel.nbDice(); die++) {
      dieCanvas.get(die).repaint();
    }
  }

  public void restart() {
    viewModel.restart();
    for (int row = 0; row < viewModel.nbRows(); row++) {
      this.resetRow(row);
    }
    this.updateRollButton();
  }

  public void update(int row) {
    this.resetRow(row);
    this.resetScore();
  }


  private void resetScore() {
    this.scoreLabel.setText(
      TEXT_TOTAL + viewModel.getTotalScore()
    );
  }

  private void resetRow(int row) {
    chooseButtons.get(row)
      .setEnabled(viewModel.isChoosableRow(row));
    pointLabels.get(row).setText(
      viewModel.getScoreDescription(row)
    );
  }

  private void clickChooseButton(int row) {
    viewModel.choose(row);
    this.updateRollButton();
  }

  private void clickRollButton() {
    if (viewModel.isGameOver()) {
      this.restart();
    } else {
      viewModel.roll();
    }
    this.updateRollButton();
  }

  private void updateRollButton() {
    boolean isActive = 
      viewModel.isGameOver() || viewModel.mayRollDice();
    rollButton.setEnabled(isActive);
    rollButton.setText(viewModel.isGameOver() ?
      TEXT_RESTART:
      TEXT_REROLL
    );
  }

  private void clickDie(int die) {
    viewModel.click(die);
  }


  private final JButton rollButton = new JButton(TEXT_REROLL);
  private final JLabel scoreLabel = new JLabel("Total = 0");
  private final Map<Integer,DieButton> dieCanvas =
    new HashMap<>();
  private final Map<Integer, JButton> chooseButtons = 
    new HashMap<>();
  private final Map<Integer, JLabel> pointLabels = 
    new HashMap<>();
  private final ViewModel viewModel;


  private JPanel makeScoreGrid() {
    JPanel gridPane = new JPanel();
    gridPane.setAlignmentX(Component.CENTER_ALIGNMENT);
    makeGrid(gridPane);
    return gridPane;
  }

  public void makeGrid(Container container) {
    int nbRows = viewModel.nbRows();
    container.setMaximumSize(
      new Dimension(GRID_WIDTH, ROW_HEIGHT * nbRows)
    );
    GridLayout gridLayout = new GridLayout(nbRows,3);
    gridLayout.setHgap(GRID_HGAP);
    gridLayout.setVgap(GRID_VGAP);
    container.setLayout(gridLayout);
    for (int row = 0; row < nbRows; row++) {
      makeRow(container, row);
      this.resetRow(row);   
    }
  }

  public void makeRow(Container container, int row) {
      JButton button = new JButton(TEXT_CHOOSE);
      container.add(button);
      button.addActionListener(event -> this.clickChooseButton(row));
      JLabel description = new JLabel(
        viewModel.getRowDescription(row)
      );
      container.add(description);
      JLabel points = new JLabel("",JLabel.RIGHT); 
      points.setAlignmentX(Component.RIGHT_ALIGNMENT);
      container.add(points);
      chooseButtons.put(row,button);
      pointLabels.put(row,points);
  }

  private Box makeDiceBox() {
    Box diceBox = Box.createHorizontalBox();
    diceBox.setAlignmentX(Component.CENTER_ALIGNMENT);
    makeDice(diceBox);
    return diceBox;
  }


  private void makeDice(Box box) {
    box.add(Box.createRigidArea(HGAP));
    for (int die = 0; die < viewModel.nbDice(); die++) {
      makeDie(box, die);
      box.add(Box.createRigidArea(HGAP));
    }
  }

  private void makeDie(Box box, int die) {
    DieButton dieButton = new DieButton(this.viewModel,die);
    box.add(dieButton);
    dieCanvas.put(die,dieButton);
    dieButton.addActionListener(ev -> this.clickDie(die));
  }

  private void initRollButton() {
    rollButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    rollButton.addActionListener(ev -> this.clickRollButton());
  }

  private Box makeScoreBox() {
    Box scoreBox = Box.createHorizontalBox();
    scoreBox.setPreferredSize(new Dimension(GRID_WIDTH,0));
    scoreBox.add(Box.createHorizontalGlue());
    scoreBox.add(scoreLabel);
    scoreBox.add(Box.createRigidArea(SCORE_RIGHT_MARGIN));
    scoreBox.setAlignmentX(Component.CENTER_ALIGNMENT);
    return scoreBox;
  }

  public Controller() {
    this.viewModel = new ViewModel(this);
    this.initialize();
  }

  private void initialize() {
    JFrame frame = new JFrame(TEXT_FRAME_TITLE);
    frame.setSize(FRAME_WIDTH,FRAME_HEIGHT);
    Container panel = frame.getContentPane();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

    initRollButton();
    List<Component> components = List.of(
      Box.createRigidArea(VGAP),
      makeDiceBox(),
      Box.createRigidArea(VGAP),
      rollButton,
      Box.createRigidArea(VGAP),
      makeScoreGrid(),
      Box.createRigidArea(VGAP),
      makeScoreBox()
      );
    components.forEach(panel::add);  

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }


  private static final Dimension VGAP = new Dimension(0,10);
  private static final Dimension HGAP = new Dimension(10,0);
  private static final Dimension SCORE_RIGHT_MARGIN =
    new Dimension(20,0);
  private static final int FRAME_WIDTH = 500;
  private static final int FRAME_HEIGHT = 700;
  private static final int GRID_WIDTH = 460;  
  private static final int ROW_HEIGHT = 30;
  private static final int GRID_VGAP = 5;
  private static final int GRID_HGAP = 10;

  private static final String TEXT_RESTART = "Recommencer";
  private static final String TEXT_CHOOSE = "Choisir";
  private static final String TEXT_REROLL = "Relancer";
  private static final String TEXT_TOTAL = "Total = ";
  private static final String TEXT_FRAME_TITLE = "Amuhtzee";
}