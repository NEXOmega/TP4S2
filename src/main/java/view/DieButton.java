package view;

import java.awt.*;
import java.awt.geom.Point2D;
import javax.swing.*;

public class DieButton extends JButton {

  private static final Dimension size = new Dimension(80,80);
  
  private final ViewModel viewModel;
  private final int die;

  public DieButton(ViewModel viewModel, int dieIndex) {
    super("");
    this.viewModel = viewModel;
    this.die = dieIndex;
    this.setMinimumSize(size);
    this.setPreferredSize(size);
    this.setMaximumSize(size);
  }


  public void paintComponent(Graphics graphics) {
    Color background = 
      this.viewModel.isDieBlocked(die) ? Color.BLACK : Color.WHITE;
    Color foreground = 
      this.viewModel.isDieBlocked(die) ? Color.WHITE : Color.BLACK;
    graphics.setColor(background);
    graphics.fillRect(
      0,
      0,
      round(size.getWidth()), 
      round(size.getHeight())
    );
    graphics.setColor(foreground);
    int facialValue = this.viewModel.getDieValue(die);
    for (Point2D center :
         DiceDrawingParameters.getPoints(facialValue)) 
    {
      graphics.fillOval(
        round(center.getX()-DiceDrawingParameters.DIAMETER/2),
        round(center.getY()-DiceDrawingParameters.DIAMETER/2),
        round(DiceDrawingParameters.DIAMETER),
        round(DiceDrawingParameters.DIAMETER)
      );
    }
  }

  private static int round(double x) {
    return Math.toIntExact(Math.round(x));
  }
}