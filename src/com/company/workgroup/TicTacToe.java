package com.company.workgroup;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToe extends JFrame{
    public TicTacToe(){
        setTitle("Крестики-нолики");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(600,600);
        setLocationRelativeTo(null);

        JPanel jBottomPanel = new JPanel();
        jBottomPanel.setLayout(new GridLayout());
        JButton jbStart = new JButton("Start new Game");
        JButton jbExit = new JButton("Exit");

        jbStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                new TicTacToe();
            }
        });
        jbExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                System.exit(0);
            }
        });
        GameField gf = new GameField();
        jBottomPanel.add(jbStart);
        jBottomPanel.add(jbExit);
        add(gf, BorderLayout.CENTER);
        add(jBottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }
}
