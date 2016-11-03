package net.project;

import java.awt.EventQueue;

import net.project.gui.MainFrame;

/**
 * <p>
 * Title: The Application class
 * </p>
 * <p>
 * Description: This class will be responsible for launches the jframe of the
 * application
 * </p>
 * 
 * @author Justin Mintzer
 *
 */
public class Application {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
