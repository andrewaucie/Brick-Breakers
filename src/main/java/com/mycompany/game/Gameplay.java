package com.mycompany.game;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import static java.lang.Math.PI;
import java.util.Vector;
import javax.swing.Timer;
import javax.swing.JPanel;

import static com.mycompany.game.Menu.level;
import static com.mycompany.game.Menu.levelCol;
import static com.mycompany.game.Menu.levelRow;
import static com.mycompany.game.Menu.total;
import static com.mycompany.game.CheckVelocity.speedMultiplier;
import java.awt.AWTException;
import java.awt.Robot;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * 
 */
public class Gameplay extends JPanel implements KeyListener, ActionListener, MouseListener, MouseMotionListener {
    private boolean play = false;
    private boolean reset = false;
    private double score = 0;
    
    //private int trueLevel = level;
    private int totalBricks = total;
    
    private Timer timer;
    private int delay = 0;
    
    final double playerXinitial = 390.5;
    private double playerX = playerXinitial;
    final double playerY = 643;
    final int playerWidth = 13;
    private int playerLength = 100;
    
    final double ballposXinitial = playerX+40;
    final double ballposYinitial = playerY-20;
    
    private double ballposX = ballposXinitial;
    private double ballposY = ballposYinitial;

    private double ballXdir;
    private double ballYdir;
    
    //private int boostWidth = 20;
    //private int boostHeight = 20;
    //private double boostX;
    //private double boostY;
    
    private boolean isBoost = false;
    private boolean hasStarted = false;
    private boolean hasBounced = false;
    private boolean robotT = false;
    
    private double speed = Math.abs(speedMultiplier);
    private double velocityY = 5 * speed;
    private double velocityX = 3 * speed;
    
    final int mapLength = 663;
    final int mapWidth = 881;
    
    //private int boostYchange = 0;

    private MapGenerator map;
    
    
    public Gameplay() {
        System.out.println(levelCol+"; "+levelRow);
        map = new MapGenerator(levelRow, levelCol);
        addKeyListener(this);
        addMouseMotionListener(this);
        addMouseListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        timer.start();        
    }
    
    public void paint(Graphics g) {
        Robot robot;
        if (!robotT) {
            try {
                robot = new Robot();
                robot.keyPress(KeyEvent.VK_R);
            } catch (AWTException ex) {
                Logger.getLogger(Gameplay.class.getName()).log(Level.SEVERE, null, ex);
            }
            robotT = true;
        }
        g.setColor(Color.black);
        g.fillRect(0,0,900,700);
        map.draw((Graphics2D)g);
        g.setColor(Color.red);
        g.fillRect(0,0,3,mapLength);
        g.fillRect(0,0,mapWidth,3);
        g.fillRect(mapWidth, 0, 3, mapLength);
        g.setColor(Color.green);
        g.fillRect((int)playerX, (int)playerY, playerLength, playerWidth);
        g.setColor(Color.yellow);
        g.fillOval((int)ballposX, (int)ballposY, 20, 20);
        g.setColor(Color.white);
        g.setFont(new Font("serif", Font.BOLD, 25));
        g.drawString("Score: "+score, 10, 30);
        if (totalBricks==0) {
            if (level == 6) {
                g.setColor(Color.yellow);
                g.setFont(new Font("arial", Font.BOLD, 34));
                g.drawString("You have beat BrickBreaks! Final score: " + Math.round(score*10.0)/10.0, 100, 370);
                g.setColor(Color.white);
                g.setFont(new Font("arial", Font.BOLD, 34));
                g.drawString("ENTER to Restart", 290, 460);
                g.drawString("OR", 395, 520);
                g.drawString("Choose another level", 265, 580);
                
            } else {
                g.setColor(Color.yellow);
                g.setFont(new Font("arial", Font.BOLD, 34));
                g.drawString("Congratulations: Won with a score of " + Math.round(score * 10.0) / 10.0, 120, 350);

                g.setColor(Color.white);
                g.setFont(new Font("arial", Font.BOLD, 34));
                g.drawString("ENTER to Restart", 290, 440);
                g.drawString("OR", 400, 500);
                g.drawString("Choose another level", 265, 560);
            }
            play = false;
            reset = true;
            ballXdir = 0;
            ballYdir = 0;
        }
        if (ballposY > mapLength) {
            play = false;
            reset = true;
            ballXdir = 0;
            ballYdir = 0;
            g.setColor(Color.red);
            g.setFont(new Font("arial", Font.BOLD, 34));
            g.drawString("GAME OVER: Lost with a score of "+Math.round(score * 10.0) / 10.0, 180, 350);
            
            g.setColor(Color.white);
            g.setFont(new Font("arial", Font.BOLD, 34));
            g.drawString("ENTER to Restart", 300, 440);
            if (level == 1) {
                g.drawString("OR", 410, 500);
                g.drawString("Go back to the main menu", 235, 560);
            }
            else {
                g.drawString("OR", 410, 500);
                g.drawString("Choose another level", 270, 560);
            }
        }
        
        if (!play & !reset) {
         Point point = this.getMousePosition();
          if (point!=null) {
              g.setColor(Color.white);
              Graphics2D g2d = (Graphics2D) g.create();
              Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
              g2d.setStroke(dashed);
              if (point.getY()>playerY-30)
                  point.y=(int)playerY-30;
              g2d.drawLine((int)playerXinitial+50, (int)playerY-10, (int)point.getX(), (int)point.getY());
          }
        }
        g.dispose();
    }
    public static double[] vDir (double targetX, double targetY, double initialX, double initialY) {
        var tX = targetX - initialX;
        var tY = targetY - initialY;
        var distance = Math.sqrt(tX*tX+tY*tY);
        var velX = (tX/distance);
        var velY = (tY/distance);
        double[] a = {velX,velY};
        return a;
    }
    public double adjustY (double y) {
        while (Math.abs(y) < 1) {
            if (y < 0) {
                y -= 0.0015;
            }
            else {
                y += 0.0015;
            }
        }
        return y;
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if ((e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) && play) {
            if (playerX > 781) {
                playerX = 781;
            }
            else {
                moveRight(); 
            }
        }
        if ((e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) && play) {
            if (playerX <= 10) {
                playerX = 10;
            }
            else {
                moveLeft();
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_R) {
            ballposX = ballposXinitial;
            ballposY = ballposYinitial;
            playerX = playerXinitial;
            score = 0;
            totalBricks = total;
            map = new MapGenerator(levelRow, levelCol);
            repaint();
            reset = false;
            play = false;
            hasStarted = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (reset) {
                ballposX = ballposXinitial;
                ballposY = ballposYinitial; 
                playerX = playerXinitial;
                score = 0;
                totalBricks = total;
                map = new MapGenerator(levelRow, levelCol);
                repaint();
                reset = false;
                hasStarted = false;
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            play = true;
            Point point = this.getMousePosition();
            if (!hasStarted) {
                ballXdir = vDir(point.getX(), point.getY(), (double) playerX + 50.0, (double) playerY - 10.0)[0] * velocityX;
                ballYdir = vDir(point.getX(), point.getY(), (double) playerX + 50.0, (double) playerY - 10.0)[1] * velocityY;
                hasStarted = true;
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_M) {
            if (reset) {
                //menuFrame() = new Menu();
                //new Menu().setVisible(true);
            }
        }
    }
    int moveDistance = 40;
    public void moveRight() {
        if (playerX+moveDistance>780) {
            playerX = 780;
        }
        else {
            playerX += moveDistance;
    
        }
    }
    public void moveLeft() {
        playerX -= moveDistance;
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        if (play) {
            if (new Rectangle((int)ballposX, (int)ballposY, 20, 20).intersects(new Rectangle((int)playerX, (int)playerY, playerLength, playerWidth)) && !hasBounced) {
                ballYdir = -ballYdir;
                hasBounced = true;
            }

            A: for (int i = 0; i < map.map.length; i++) {
                for (int j = 0; j<map.map[0].length; j++) {
                    if (map.map[i][j]>0) {
                        double brickX = j*map.brickWidth+80;
                        double brickY = i*map.brickHeight+50;
                        int brickHeight = map.brickHeight;
                        int brickWidth = map.brickWidth;
                        //boostY += boostYchange;

                        
                        Rectangle rect = new Rectangle((int)brickX, (int)brickY, brickWidth, brickHeight);
                        Rectangle ballRect = new Rectangle((int)ballposX, (int)ballposY, 20, 20);
                        Rectangle brickRect = rect;
                        if (ballRect.intersects(brickRect)) {
                            if (map.map[i][j] < 10) {
                                map.map[i][j]--;
                                map.setValue(map.map[i][j], i, j);
                                if (map.map[i][j] == 0) {
                                    totalBricks--;
                                    score += 1 * speed;
                                }
                                
                            }
                            if (ballposX+10 <= brickRect.x || ballposX+1 >= brickRect.x + brickRect.width) {
                                ballXdir = -ballXdir;
                                hasBounced = false;
                            }
                            else {
                                ballYdir = adjustY(-ballYdir);
                                hasBounced = false;
                            }
                            
                            break A;
                        }
                    }
                }
            }
            ballposX += ballXdir;
            ballposY += ballYdir;
           /* if (isBoost) {
                boostYchange += 1;
            }*/
            if (ballposX < 0) {
                ballXdir = -ballXdir;
                hasBounced = false;
            }
            if (ballposY < 0) {
                ballYdir = adjustY(-ballYdir);
                hasBounced = false;
            }
            if (ballposX >= mapWidth-11) {
                ballXdir = -ballXdir;
                hasBounced = false;
            }
        }
        repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        repaint(); 
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        play = true;
        Point point = this.getMousePosition();
        if (!hasStarted) {
            ballXdir = vDir(point.getX(), point.getY(), (double) playerX + 50.0, (double) playerY - 10.0)[0] * velocityX;
            ballYdir = vDir(point.getX(), point.getY(), (double) playerX + 50.0, (double) playerY - 10.0)[1] * velocityY;
            hasStarted=true;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    
}
