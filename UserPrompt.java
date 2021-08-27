package com.connect4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class UserPrompt extends JFrame
{
	JLabel label;
	JButton jtb, jtb1;

	public UserPrompt(String message, Connect4GUI discView)
	{
		super("Do you want to continue?");
		setLocation(300,400);
		setResizable(false);
		setSize(400,100);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridLayout(3,1));
		add(label = new JLabel(message));
		add(jtb = new JButton("New Game"));
		add(jtb1 = new JButton("End Game"));
		jtb.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				discView.dispose();
				Connect4Game game = new Connect4Game();
				dispose();
            }
		});
		jtb1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				discView.dispose();
                dispose();
            }
		});
		setVisible(true);
	}
};