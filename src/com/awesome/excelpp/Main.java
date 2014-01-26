package com.awesome.excelpp;

import java.io.IOException;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.awesome.excelpp.gui.GUI;

public class Main {
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		SwingUtilities.invokeLater(new Runnable() { //thread save zijn
			public void run() {
				try {
					new GUI();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		});
	}
}
