package com.company.workgroup;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class GameField extends JPanel{
    private char[][] map;
    public final int SIZE = 3;
    private int width;
    private int height;
    private int cellWidth;
    private int cellHeight;
    public Random random = new Random();

    public GameField(){
        map = new char[SIZE][SIZE];
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e){
                if (!checkWin('O')){
                    int clX = e.getX()/cellWidth;
                    int clY = e.getY()/cellHeight;
                    if (map[clX][clY] == '\u0000') {
                        map[clX][clY] = 'X';
                        if (!checkWin('X')){
                            if (isMapFull())System.out.println("Ничья.");
                            else aiTurn();
                        }
                        else System.out.println("Вы победили!");
                    }else System.out.println("Некорректный ввод");
                    repaint();
                }else System.out.println("Победил компьютер!");
            }
        });
    }
    public void aiTurn(){
        int x,y;
        do{
            x = random.nextInt(SIZE);
            y = random.nextInt(SIZE);
        }while (!isCellValid(x,y));
        map[y][x] = 'O';
    }
    public boolean isCellValid(int x, int y){
        if(map[y][x] == 'X' || map[y][x] == 'O') return false;
        return true;
    }
    public boolean checkWin(char symb){
        int diag1 = 0;
        int diag2 = 0;
        for (int i = 0; i < SIZE; i++) {
            int row = 0;
            int column = 0;
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == symb) row++;
                else row = 0;
                if (map[j][i] == symb) column++;
                else column = 0;
                if (i == j && map[i][j] == symb) diag1++;
                if (i == SIZE-1-j && map[i][j] == symb) diag2++;
                if (row == SIZE || column == SIZE || diag1 == SIZE || diag2 == SIZE) return true;
            }
        }
        return false;
    }
    public boolean isMapFull(){
        for(int i = 0; i < SIZE; i++){
            for(int j = 0; j < SIZE; j++){
                if(map[i][j] == '\u0000') return false;
            }
        }
        return true;
    }

    @Override
    protected void paintComponent(Graphics gr){
        Graphics2D g = (Graphics2D)gr;
        width = getWidth();
        height = getHeight();
        cellWidth = width/SIZE;
        cellHeight = height/SIZE;
        for(int i = 0; i < SIZE + 1; i++){
            g.drawLine(0, i*cellHeight, width, i*cellHeight);
            g.drawLine(i*cellWidth, 0, i*cellWidth, height);
        }

            for(int i = 0; i < SIZE; i++){
                for(int j = 0; j < SIZE; j++){
                    if(map[i][j] == 'X'){
                        BasicStroke pen1 = new BasicStroke(10);
                        g.setStroke(pen1);
                        g.setColor(Color.RED);
                        g.drawLine(i*cellWidth + 10, j*cellHeight + 10, (i+1)*cellWidth - 10, (j+1)*cellHeight - 10);
                        g.drawLine((i+1)*cellWidth - 10, j*cellHeight + 10, i*cellWidth + 10, (j+1)*cellHeight - 10);

                    }
                    if(map[i][j] == 'O'){
                        BasicStroke pen2 = new BasicStroke(10);
                        g.setStroke(pen2);
                        g.setColor(Color.BLUE);
                        g.drawOval(i*cellWidth + 10, j*cellHeight + 10, cellWidth - 20, cellHeight - 20);
                        if (checkWin('O')) break;
                    }
                }
            }


    }
}

