package com.xenonteam.helper.spritesheet;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class SpriteSheetGui extends JPanel implements MouseListener, MouseWheelListener, KeyListener, WindowListener, ActionListener
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4360016942284197391L;
	
	private boolean m_running = true;
	
	private JFrame m_frame;
	
	private JMenuBar m_menu;
	
	private boolean[] m_keysPressed = new boolean[402];
	
	private static String m_error = "none";
	
	private BufferedImage m_currentImage = null;
	
	public static String loadFromGui()
	{
		new SpriteSheetGui();
		
		return m_error;
	}
	
	private void addButton(String name, JComponent parent)
	{
		JButton button = new JButton(name);
		button.addActionListener(this);
		button.setVisible(true);
		
		parent.add(button);
	}
	
	private SpriteSheetGui()
	{
		Dimension dim = new Dimension(1080, 720);
		
		m_frame = new JFrame("SpriteSheet Editor");
		
		this.setPreferredSize(dim);
		this.setSize(dim);
		
		m_frame.add(this);
		
		m_frame.addMouseListener(this);
		m_frame.addMouseWheelListener(this);
		m_frame.addKeyListener(this);
		m_frame.addWindowListener(this);
		
		m_menu = new JMenuBar();
		
		
		addButton("Load", m_menu);
		addButton("Save", m_menu);
		
		m_menu.setLocation(100, 100);
		
		m_frame.add(m_menu);
		
		m_frame.setPreferredSize(dim);
		m_frame.setSize(dim);
		
		m_frame.setVisible(true);
		
		loop();
	}
	
	private void loop()
	{
		while(m_running)
		{
			
			if(keyPressed(KeyEvent.VK_ESCAPE))
			{
				m_running = false;
			}
			
			if(m_currentImage != null)
			{
				this.getGraphics().drawImage(m_currentImage, 0, 0, null);
			}
			
			
			
			capFrameRate(60);
		}
		
		m_frame.dispose();
		
	}
	
	private void capFrameRate(double fps) {
	    double start = 0, diff, wait;
	    wait = 1 / fps;
	    diff = System.currentTimeMillis() - start;
	    if (diff < wait) {
	        try
			{
				Thread.sleep((long) (wait - diff));
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}
	    }
	    start = System.currentTimeMillis();
	}
	
	private boolean keyPressed(int key)
	{
		return m_keysPressed[key];
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getID() == 1001)
		{
			if(e.getActionCommand().equals("Load"))
			{
				JFileChooser chooser = new JFileChooser("Select a sprite");
				
				  int returnVal = chooser.showOpenDialog(this);

			        if (returnVal == JFileChooser.APPROVE_OPTION) 
			        {
			        	loadImage(chooser.getSelectedFile());
			        }
				
			}
		}
	}
	
	private void loadImage(File f)
	{
		if(f.getName().endsWith(".png"))
		{
			m_frame.setTitle("SpriteSheet Editor - " + f.getName());
			
			try
			{
				m_currentImage = ImageIO.read(f);
			} catch (IOException e)
			{
				m_error = "Invalid image at: " + f;
				m_running = false;
			}
		}
	}
	
	@Override
	public void keyPressed(KeyEvent event)
	{
		m_keysPressed[event.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent event)
	{
		m_keysPressed[event.getKeyCode()] = false;
	}

	@Override
	public void keyTyped(KeyEvent event)
	{

		
	}

	@Override
	public void mouseClicked(MouseEvent event)
	{

		
	}

	@Override
	public void mouseEntered(MouseEvent event)
	{

		
	}

	@Override
	public void mouseExited(MouseEvent event)
	{

		
	}

	@Override
	public void mousePressed(MouseEvent event)
	{

		
	}

	@Override
	public void mouseReleased(MouseEvent event)
	{

		
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent event)
	{
		
		
	}

	@Override
	public void windowOpened(WindowEvent e)
	{
		m_running = false;
	}

	@Override
	public void windowClosing(WindowEvent e)
	{
		m_running = false;
	}

	@Override
	public void windowClosed(WindowEvent e)
	{
		m_running = false;
	}

	@Override
	public void windowIconified(WindowEvent e)
	{
	
	}

	@Override
	public void windowDeiconified(WindowEvent e)
	{
		
	}

	@Override
	public void windowActivated(WindowEvent e)
	{
	
	}

	@Override
	public void windowDeactivated(WindowEvent e)
	{
	
	}

	

}
