package de.dhbwka.java.exercise.classes.candycrush;

import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import javax.swing.event.MouseInputListener;

public class DrawPanel extends JPanel implements MouseInputListener {
    private static final long serialVersionUID = 1L;
    
    private WindowCandyCrush frame;
    
    private final int CELL_SIZE = 8;
    private final int PIXEL_SIZE = 5;
    private final int FULL_SIZE = CELL_SIZE * PIXEL_SIZE;

    public DrawPanel(WindowCandyCrush frame) {
        this.frame = frame;
    }

    private Color getCellColor(byte value) {
		int colors = frame.getGame().getField().getColors();
		return Color.getHSBColor(value / (float) colors, 1f, 0.5f);
    }

    private void drawField(Field field) {
        for (int y = 0; y < field.getSize(); y++) {
            for (int x = 0; x < field.getSize(); x++) {
                byte value = field.getValueAt(x, y);
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawField(frame.getGame().getField());
    }

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}