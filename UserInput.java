package com.connect4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class UserInput extends JFrame
{
	JLabel label;
	JTextField jtf;
	Connect4Game game;
	public UserInput(Connect4Game game)
	{
		super("Player Input");
		this.game = game;
		setLocation(100,100);
		setResizable(false);
		setSize(400,100);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridLayout(2,1));
		add(label = new JLabel("Hi Player Enter Column to Insert the Disc And press Enter"));
		add(jtf = new JTextField());
		jtf.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				String s = jtf.getText();
				try
				{
					int column = Integer.parseInt(s);
                    game.setPlayerInput(column,UserInput.this);
                    //game.showPlayerPrompt(2);
                    dispose();
				}
				catch(NumberFormatException ex)
				{
					JOptionPane.showMessageDialog(UserInput.this,"column has to be a NUMBER","Invalid Column",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		setVisible(true);
	}
	public void showError(String message)
	{
		JOptionPane.showMessageDialog(UserInput.this,message,"Invalid Column",JOptionPane.ERROR_MESSAGE);
		new UserInput(game);
    }
    public UserInput getUserInput()
    {
        return this;
    }
};