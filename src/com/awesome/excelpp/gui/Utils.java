package com.awesome.excelpp.gui;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

/**
 * A class that holds multiple utility methods
 */
public class Utils {
	/**
	 * Returns the screen width
	 * @return int
	 */
	static double getScreenWidth() {
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice gd = ge.getDefaultScreenDevice();

		return gd.getDefaultConfiguration().getBounds().getWidth();
	}
	
	/**
	 * Returns the screen height
	 * @return int
	 */
	static double getScreenHeight() {
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice gd = ge.getDefaultScreenDevice();

		return gd.getDefaultConfiguration().getBounds().getHeight();
	}
}
