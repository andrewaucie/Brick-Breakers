package com.mycompany.game;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public interface Bricks {
    //Brick images
    public static final Icon blueOG = convertIcon("/Breakout Tile Set Free.PNG/01-Breakout-Tiles.png");
    public static final Icon blueCrack = convertIcon("/Breakout Tile Set Free.PNG/02-Breakout-Tiles.png");
    public static final Icon limeOG = convertIcon("/Breakout Tile Set Free.PNG//03-Breakout-Tiles.png");
    public static final Icon limeCrack = convertIcon("/Breakout Tile Set Free.PNG/04-Breakout-Tiles.png");
    public static final Icon purpleOG = convertIcon("/Breakout Tile Set Free.PNG/05-Breakout-Tiles.png");
    public static final Icon purpleCrack = convertIcon("/Breakout Tile Set Free.PNG/06-Breakout-Tiles.png");
    public static final Icon redOG = convertIcon("/Breakout Tile Set Free.PNG/07-Breakout-Tiles.png");
    public static final Icon redCrack = convertIcon("/Breakout Tile Set Free.PNG/08-Breakout-Tiles.png");
    public static final Icon orangeOG = convertIcon("/Breakout Tile Set Free.PNG/09-Breakout-Tiles.png");
    public static final Icon orangeCrack = convertIcon("/Breakout Tile Set Free.PNG/10-Breakout-Tiles.png");
    public static final Icon skyOG = convertIcon("/Breakout Tile Set Free.PNG/11-Breakout-Tiles.png");
    public static final Icon skyCrack = convertIcon("/Breakout Tile Set Free.PNG/12-Breakout-Tiles.png");
    public static final Icon yellowOG = convertIcon("/Breakout Tile Set Free.PNG/13-Breakout-Tiles.png");
    public static final Icon yellowCrack = convertIcon("/Breakout Tile Set Free.PNG/14-Breakout-Tiles.png");
    public static final Icon greenOG = convertIcon("/Breakout Tile Set Free.PNG/15-Breakout-Tiles.png");
    public static final Icon greenCrack = convertIcon("/Breakout Tile Set Free.PNG/16-Breakout-Tiles.png");
    public static final Icon grayOG = convertIcon("/Breakout Tile Set Free.PNG/17-Breakout-Tiles.png");
    public static final Icon grayCrack = convertIcon("/Breakout Tile Set Free.PNG/18-Breakout-Tiles.png");
    public static final Icon brownOG = convertIcon("/Breakout Tile Set Free.PNG/19-Breakout-Tiles.png");
    public static final Icon brownCrack = convertIcon("/Breakout Tile Set Free.PNG/20-Breakout-Tiles.png");
    
    public static Icon convertIcon (String s) {
        String iconPath = s;
        Icon icon = new ImageIcon(Bricks.class.getResource(iconPath));
        return icon;
    }
}
