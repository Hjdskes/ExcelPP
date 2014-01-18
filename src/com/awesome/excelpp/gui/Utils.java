package com.awesome.excelpp.gui;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

/**
 * A class that holds several utility methods.
 * @author Team Awesome
 */
public class Utils {
	/**
	 * Returns the screen width.
	 * @return The screen width
	 */
	public static double getScreenWidth() {
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice gd = ge.getDefaultScreenDevice();

		return gd.getDefaultConfiguration().getBounds().getWidth();
	}
	
	/**
	 * Returns the screen height.
	 * @return The screen height
	 */
	public static double getScreenHeight() {
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice gd = ge.getDefaultScreenDevice();

		return gd.getDefaultConfiguration().getBounds().getHeight();
	}
}
