package com.mycompany.game;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import static com.mycompany.game.Menu.level;

public class MapGenerator {
    public int map[][];
    public int brickWidth;
    public int brickHeight;

    public MapGenerator(int row, int col) {
        map = new int[row][col];
        System.out.println("here");
        switch (level) {
            case 1:
                for (int[] map1 : map) {
                    for (int j = 0; j < map[0].length; j++) {
                        map1[j] = 1;
                    }
                }
                break;

            case 2:
                for (int i = 0; i < map[0].length; i++) {
                    map[0][i] = 1; // row 1
                    map[2][i] = 1; // row 3
                    map[4][i] = 1; // row 5
                }
                for (int i = 2; i <= 5; i++) {
                    map[1][i] = 2; // row 2
                    map[3][i] = 2; // row 4
                }
                map[1][1] = 10; // row 2 (brick)
                map[1][6] = 10; // row 2 (brick)
                break;
            case 3:
                for (int i = 0; i < map[0].length; i=i+2) {
                    map[0][i] = 1; // row 1 (alternating)
                    map[2][i] = 3; // row 3
                }
                for (int i = 1; i < map[0].length; i=i+2) {
                    map[0][i] = 2; // row 1 (alternating)
                    map[4][i] = 2; // row 5
                }
                for (int i = 0; i < map[0].length; i++) {
                    map[1][i] = 2; // row 2
                    map[3][i] = 1; // row 4
                    map[5][i] = 1; // row 6
                }
                map[2][3] = 10; // row 3 (brick)
                map[2][5] = 10; // row 3 (brick)
                break;
            case 4:
                for (int i = 0; i < map[0].length; i++) {
                    map[0][i] = 2; // row 1
                    map[1][i] = 2; // row 2
                    map[4][i] = 2; // row 5
                    map[6][i] = 1; // row 7
                }
                for (int i = 0; i <= 2; i++) {
                    map[2][i] = 3; // row 3
                    map[3][i] = 1; // row 4
                }
                for (int i = 7; i <= 9; i++) {
                    map[2][i] = 3; // row 3
                    map[3][i] = 1; // row 4
                }
                for (int i = 3; i <= 6; i++) {
                    map[3][i] = 4; // row 4
                    map[5][i] = 4; //row 6
                }
                map[5][0] = 10;
                map[5][9] = 10;
                break;
            case 5:
                for (int i = 0; i < map[0].length; i++) {
                    map[0][i] = 3; // row 1
                    map[2][i] = 1; // row 3
                    map[3][i] = 2; // row 4
                    map[6][i] = 1; // row 7
                }
                for (int i = 5; i<=6; i++) {
                    map[4][i] = 4; // row 4
                }
                for (int i = 3; i<=8; i++) {
                    map[5][i] = 3; // row 6
                }
                for (int i = 0; i<map[0].length; i++) {
                    if (i!=4 && i!=7) {
                        map[8][i] = 2;
                    } 
                }
                map[7][4] = 4;
                map[7][7] = 4;
                map[8][4] = 4;
                map[8][7] = 4;
                map[7][3] = 10;
                map[7][8] = 10;
                map[1][4] = 10; // brick
                map[1][7] = 10; // brick
                break;
            case 6:
                for (int i = 0; i <= 1; i++) {
                    map[0][i] = 3;
                }
                for (int i = 4; i <= 12; i++) {
                    map[0][i] = 2;
                }
                for (int i = 0; i <= 5; i++) {
                    map[2][i] = 3;
                }
                for (int i = 2; i <= 5; i++) {
                    map[i][8] = 4;
                }
                for (int i = 10; i < map[0].length; i++) {
                    map[2][i] = 1;
                }
                for (int i = 0; i < map[0].length; i++) {
                    if (i!=8) {
                        map[4][i] = 3;
                        map[5][i] = 5;
                    }
                }
                for (int i = 0; i < map[0].length; i++) {
                    map[6][i] = 5;
                }
                for (int i = 7; i < map[0].length; i++) {
                    map[7][i] = 3;
                }
                for (int i = 1; i < map[0].length; i+=2) {
                    map[8][i] = 2;
                }
                for (int i = 0; i < map[0].length; i+=2) {
                    map[8][i] = 4;
                }
                map[2][6] = 10;
                map[2][7] = 10;
                map[6][4] = 10;
                map[7][4] = 10;
                
                break;
                
        }

        brickWidth = 740/col;
        if (level == 5) {
            brickHeight = 300/row;
        }
        if (level == 6) {
            brickHeight = 250/row;
        }
        else {
            brickHeight = 200/row;
        }
    }
    public void draw (Graphics2D g) {
        Color c2 = new Color(11, 255, 238);
        Color c3 = new Color(255,153,102);
        Color c4 = new Color(255, 0, 0);
        Color c5 = new Color(179, 50, 255);
        for (int i=0; i<map.length; i++) {
            for (int j=0; j<map[0].length; j++) {
                if (map[i][j] == 1) {
                    g.setColor(Color.white);
                    g.fillRect(j*brickWidth+80, i*brickHeight+50, brickWidth, brickHeight);
                    
                    g.setStroke(new BasicStroke(3));
                    g.setColor(Color.black);
                    g.drawRect(j*brickWidth+80, i*brickHeight+50, brickWidth, brickHeight);
                }
                else if (map[i][j] == 2) {
                    g.setColor(c2);
                    g.fillRect(j*brickWidth+80, i*brickHeight+50, brickWidth, brickHeight);
                    
                    g.setStroke(new BasicStroke(3));
                    g.setColor(Color.black);
                    g.drawRect(j*brickWidth+80, i*brickHeight+50, brickWidth, brickHeight);
                }
                else if (map[i][j] == 3) {
                    g.setColor(c3);
                    g.fillRect(j*brickWidth+80, i*brickHeight+50, brickWidth, brickHeight);
                    
                    g.setStroke(new BasicStroke(3));
                    g.setColor(Color.black);
                    g.drawRect(j*brickWidth+80, i*brickHeight+50, brickWidth, brickHeight);
                }
                else if (map[i][j] == 4) {
                    g.setColor(c4);
                    g.fillRect(j*brickWidth+80, i*brickHeight+50, brickWidth, brickHeight);
                    
                    g.setStroke(new BasicStroke(3));
                    g.setColor(Color.black);
                    g.drawRect(j*brickWidth+80, i*brickHeight+50, brickWidth, brickHeight);
                }
                else if (map[i][j] == 5) {
                    g.setColor(c5);
                    g.fillRect(j*brickWidth+80, i*brickHeight+50, brickWidth, brickHeight);
                    
                    g.setStroke(new BasicStroke(3));
                    g.setColor(Color.black);
                    g.drawRect(j*brickWidth+80, i*brickHeight+50, brickWidth, brickHeight);
                }
                else if (map[i][j] == 10) {
                    g.setColor(Color.gray);
                    g.fillRect(j*brickWidth+80, i*brickHeight+50, brickWidth, brickHeight);
                    
                    g.setStroke(new BasicStroke(3));
                    g.setColor(Color.black);
                    g.drawRect(j*brickWidth+80, i*brickHeight+50, brickWidth, brickHeight);
                }
            }
        }
    }
    public void setValue(int value, int row, int col) {
        map[row][col] = value;
    }

}
