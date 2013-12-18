package com.awesome.excelpp;

import javax.swing.UIManager;
import java.io.IOException;
import com.awesome.excelpp.gui.GUI;

public class Main {
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			new GUI();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
