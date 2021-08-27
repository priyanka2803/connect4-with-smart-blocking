package com.connect4;

import javax.swing.*;
import java.awt.*;

public class Connect4GUI extends JFrame
{
	
	JTable table;
	String[] columns;
	JScrollPane jsp;
	JLabel label,label2;
	public Connect4GUI(String[][] discs, String statusMessage, String previousStatusMessage)
	{
		super("Connect 4");
		String discsReverse[][] = new String[6][7];
		for(int i = 0;i < 6; i++)
		{
			discsReverse[i] = discs[5-i];
		}
		columns = new String[]{"column 1","column 2","column 3","column 4","column 5","column 6","column 7"};
		setLocation(300,300);
		setResizable(false);
		setSize(800,200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		table = new JTable(discs,columns);
		jsp = new JScrollPane(table);
		add(jsp,BorderLayout.CENTER);
		label = new JLabel(statusMessage);
		label2 = new JLabel(previousStatusMessage);
		add(label,BorderLayout.SOUTH);
		add(label2,BorderLayout.NORTH);
		setVisible(true);
	}
};
