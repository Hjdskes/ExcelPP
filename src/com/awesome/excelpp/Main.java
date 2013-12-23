package com.awesome.excelpp;

import com.awesome.excelpp.gui.GUI;
import java.io.IOException;
import javax.swing.UIManager;

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
