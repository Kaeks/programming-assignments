package de.jakob.minesweeper;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputListener;

public class DrawPanel extends JPanel implements MouseInputListener {
    private static final long serialVersionUID = 1L;

    private WindowMinesweeper frame;

    private final int CELL_SIZE = 8;
    private final int PIXEL_SIZE = 5;

    private final int FULL_SIZE = PIXEL_SIZE * CELL_SIZE;
    private final int INNER_SIZE = PIXEL_SIZE * (CELL_SIZE - 2);

    private Position highlighted;
    
    public DrawPanel(WindowMinesweeper frame) {
        super();
        this.frame = frame;
        setPreferredSize(new Dimension(generateWidth(), generateHeight()));
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    private int generateWidth() {
        return FULL_SIZE * frame.getGame().getField().getWidth();
    }

    private int generateHeight() {
        return FULL_SIZE * frame.getGame().getField().getHeight();
    }

    public int getFullCellSize() {
        return FULL_SIZE;
    }

    private char getCharacter(Cell cell) {
        if (cell.isCovered()) {
            if (cell.isFlag()) return 'P';
            if (cell.isQuestion()) return '?';
            return 'E';
        }
        if (cell.isMine()) return 'O';
        int neighbors = frame.getGame().getField().getNeighboringMines(cell);
        if (neighbors > 0) {
            return (char) ('0' + neighbors);
        }
        return 'E';
    }

    private Color getCharacterColor(Cell cell) {
        if (cell.isMine()) return Color.BLACK;

        switch (frame.getGame().getField().getNeighboringMines(cell)) {
            case 1: return Color.BLUE;
            case 2: return Color.GREEN;
            case 3: return Color.RED;
            case 4: return new Color(0, 0, 127);
            case 5: return new Color(127, 0, 0);
            case 6: return new Color(0, 127, 0);
            case 7: return new Color(0, 0, 63);
            case 8: return new Color(63, 0, 0);
        }
        return Color.WHITE;
    }

    private Color getBackgroundColor(Cell cell) {
        if (cell.isCovered()) {
            if (cell.getPosition().equals(highlighted)) return Color.LIGHT_GRAY;
            return Color.GRAY;
        }
        if (cell.isMine()) return Color.RED;
        return Color.WHITE;
    }

    private void drawField(Graphics g, Field field) {
        g.setFont(new Font("Courier New", Font.BOLD, FULL_SIZE));
        for (ArrayList<Cell> row : field.getCells()) {
            for (Cell cell : row) {
                Position pos = cell.getPosition();

                int x = pos.getX();
                int y = pos.getY();

                Color col = getBackgroundColor(cell);
                Color innerColor = col;
                Color borderColor = col.darker();

                g.setColor(borderColor);
                g.fillRect(x * FULL_SIZE, y * FULL_SIZE, FULL_SIZE, FULL_SIZE);
                g.setColor(innerColor);
                g.fillRect(x * FULL_SIZE + PIXEL_SIZE, y * FULL_SIZE + PIXEL_SIZE, INNER_SIZE, INNER_SIZE);
                
                char c = getCharacter(cell);
                if (c != 'E') {
                    g.setColor(getCharacterColor(cell));
                    g.drawString(String.valueOf(c), (int) (x * FULL_SIZE + 0.25 * FULL_SIZE), (int) (y * FULL_SIZE +  0.875 * FULL_SIZE));
                }
            }
        }
    }

    private Position getPositionFromPoint(Point p) {
        int x = (int) (p.getX() / FULL_SIZE);
        int y = (int) (p.getY() / FULL_SIZE);
        return new Position(x, y);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawField(g, frame.getGame().getField());
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
        if (e.getButton() != MouseEvent.BUTTON1 && e.getButton() != MouseEvent.BUTTON3) return;
        Point p = e.getPoint();
        Position pos = getPositionFromPoint(p);
        MoveType mt = e.getButton() == MouseEvent.BUTTON1 ? MoveType.UNCOVER : e.getButton() == MouseEvent.BUTTON3 ? MoveType.FLAG : null;
        Move move = new Move(pos, mt);
        frame.getGame().move(move);
        repaint();
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
        Point p = e.getPoint();
        Position pos = getPositionFromPoint(p);
        highlighted = pos;
        repaint();
	}
}