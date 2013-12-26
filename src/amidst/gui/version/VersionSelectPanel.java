package amidst.gui.version;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JPanel;

import amidst.logging.Log;
import net.miginfocom.swing.MigLayout;

public class VersionSelectPanel extends JPanel implements MouseListener {
	private String emptyMessage;
	private int emptyMessageWidth;
	private FontMetrics emptyMessageMetric;
	private Font emptyMessageFont = new Font("arial", Font.BOLD, 30);
	
	private ArrayList<VersionComponent> componentMap = new ArrayList<VersionComponent>();
	private VersionComponent selected = null;
	
	public VersionSelectPanel() {
		setLayout(new MigLayout("ins 0", "", "[]0[]"));
		setEmptyMessage("Empty");
		addMouseListener(this);
	}
	
	public void addVersion(VersionComponent version) {
		add(version, "growx, pushx, wrap");
		componentMap.add(version);
	}
	
	@Override
	public void paintChildren(Graphics g) {
		super.paintChildren(g);
		Graphics2D g2d = (Graphics2D)g;
		g2d.setColor(Color.gray);
		for (int i = 1; i <= componentMap.size(); i++) {
			g2d.drawLine(0, i * 40, getWidth(), i * 40);
		}
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		((Graphics2D)g).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		if (emptyMessageMetric == null) {
			emptyMessageMetric = g.getFontMetrics(emptyMessageFont);
			emptyMessageWidth = emptyMessageMetric.stringWidth(emptyMessage);
		}
		g.setColor(Color.white);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		if (componentMap.size() == 0) {
			g.setColor(Color.gray);
			g.setFont(emptyMessageFont);
			g.drawString(emptyMessage, (getWidth() >> 1) - (emptyMessageWidth >> 1), 30);
		}
		
	}

	public void setEmptyMessage(String message) {
		emptyMessage = message;
		if (emptyMessageMetric != null)
			emptyMessageWidth = emptyMessageMetric.stringWidth(emptyMessage);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
	}
	@Override
	public void mousePressed(MouseEvent event)  {
		int index = event.getPoint().y / 40;
		if (selected != null) {
			selected.setSelected(false);
			selected.repaint();
		}
		
		selected = null;
		
		if (index < componentMap.size()) {
			selected = componentMap.get(index);
			selected.setSelected(true);
			selected.repaint();
		}
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		
	}
}
