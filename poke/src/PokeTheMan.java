import java.awt.MouseInfo;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import java.awt.Color;
import java.awt.image.ImageObserver;
import java.awt.event.MouseListener;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;
import java.util.Random;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JFrame;

// 
// Decompiled by Procyon v0.5.36
// 

public class PokeTheMan extends JFrame
{
    Image image;
    Graphics graphics;
    Image bush;
    Image charmander;
    Image squirtle;
    Image bulbasaur;
    Image map;
    int mapX;
    int mapY;
    Image pokeballImg;
    Image dex;
    Image player;
    Image oops;
    int x;
    int y;
    int xSpeed;
    int ySpeed;
    int playerHP;
    int punching;
    int pokeballs;
    int pokeball;
    int pokedex;
    int tailX;
    int tailY;
    int timer;
    Random pX;
    Random pY;
    Random randomType;
    int[] pokemonX;
    int[] pokemonY;
    int[] showing;
    int[] boltX;
    int[] boltY;
    int[] shooting;
    int shootTime;
    int[] type;
    int[] hp;
    int[] caught;
    int selected;
    int targeted;
    Random bushCoord;
    int[] bushX;
    int[] bushY;

    public PokeTheMan() {
        this.bush = new ImageIcon("/PokeTheMan/bush").getImage();
        this.charmander = new ImageIcon("/PokeTheMan/pokemon/charmander.png").getImage();
        this.squirtle = new ImageIcon("/PokeTheMan/pokemon/squirtle.png").getImage();
        this.bulbasaur = new ImageIcon("/PokeTheMan/pokemon/bulbasaur.png").getImage();
        this.map = new ImageIcon("/PokeTheMan/map").getImage();
        this.mapX = 0;
        this.mapY = 0;
        this.pokeballImg = new ImageIcon("/PokeTheMan/pokeball.png").getImage();
        this.dex = new ImageIcon("/PokeTheMan/pokedex.png").getImage();
        this.player = new ImageIcon("/PokeTheMan/player.png").getImage();
        this.oops = new ImageIcon("/PokeTheMan/oops").getImage();
        this.x = 350;
        this.y = 300;
        this.xSpeed = 0;
        this.ySpeed = 0;
        this.playerHP = 700;
        this.punching = 0;
        this.pokeballs = 5;
        this.pokeball = 0;
        this.pokedex = 0;
        this.pX = new Random();
        this.pY = new Random();
        this.randomType = new Random();
        this.pokemonX = new int[] { this.pX.nextInt(2000), this.pX.nextInt(2100), this.pX.nextInt(2100), this.pX.nextInt(2100), this.pX.nextInt(2100), this.pX.nextInt(2100) };
        this.pokemonY = new int[] { this.pY.nextInt(1800), this.pY.nextInt(1800), this.pY.nextInt(1800), this.pY.nextInt(1800), this.pY.nextInt(1800), this.pY.nextInt(1800) };
        this.showing = new int[] { 0, 0, 0, 0, 0, 0 };
        this.boltX = new int[] { 0, 0, 0, 0, 0, 0 };
        this.boltY = new int[] { 0, 0, 0, 0, 0, 0 };
        this.shooting = new int[] { 0, 0, 0, 0, 0, 0 };
        this.type = new int[] { this.randomType.nextInt(3), this.randomType.nextInt(3), this.randomType.nextInt(3), this.randomType.nextInt(3), this.randomType.nextInt(3), this.randomType.nextInt(3) };
        this.hp = new int[] { 100, 100, 100, 100, 100, 100 };
        this.caught = new int[] { 0, 0, 0, 0, 0, 0 };
        this.selected = -1;
        this.targeted = -1;
        this.bushCoord = new Random();
        this.bushX = new int[] { this.pokemonX[0], this.pokemonX[1], this.pokemonX[2], this.pokemonX[3], this.pokemonX[4], this.pokemonX[5], this.bushCoord.nextInt(600), this.bushCoord.nextInt(600), this.bushCoord.nextInt(600) };
        this.bushY = new int[] { this.pokemonY[0], this.pokemonY[1], this.pokemonY[2], this.pokemonY[3], this.pokemonY[4], this.pokemonY[5], this.bushCoord.nextInt(500), this.bushCoord.nextInt(500), this.bushCoord.nextInt(500) };
        this.addKeyListener(new Keys());
        this.addMouseListener(new Mouse());
    }

    @Override
    public void paint(final Graphics graphics) {
        this.image = this.createImage(this.getWidth(), this.getHeight());
        this.paintComponent(this.graphics = this.image.getGraphics());
        graphics.drawImage(this.image, 0, 0, null);
        this.repaint();
    }

    public void paintComponent(final Graphics graphics) {
        if (this.pokedex == 0) {
            graphics.drawImage(this.map, this.mapX, this.mapY, 2100, 1800, null);
        }
        if (this.pokedex == 0) {
            graphics.drawImage(this.player, this.x - 50, this.y - 50, 100, 100, null);
            this.x += this.xSpeed;
            this.y += this.ySpeed;
            if (this.x + 50 >= 500 && this.mapX + 2100 > 700) {
                --this.mapX;
            }
            if (this.x - 50 <= 200 && this.mapX < 0) {
                ++this.mapX;
            }
            if (this.y + 50 >= 450 && this.mapY + 1800 > 600) {
                --this.mapY;
            }
            if (this.y - 50 <= 150 && this.mapY < 0) {
                ++this.mapY;
            }
            if (this.xSpeed != 0 || this.ySpeed != 0) {
                ++this.timer;
                if (this.timer == 100) {
                    this.tailX = this.x;
                    this.tailY = this.y;
                    this.timer = 0;
                }
            }
            for (int i = 0; i < 8; ++i) {
                graphics.drawImage(this.bush, this.bushX[i], this.bushY[i], 70, 70, null);
                if (this.x + 50 >= 500 && this.mapX + 2100 > 700) {
                    final int[] bushX = this.bushX;
                    final int n = i;
                    --bushX[n];
                }
                if (this.x - 50 <= 200 && this.mapX < 0) {
                    final int[] bushX2 = this.bushX;
                    final int n2 = i;
                    ++bushX2[n2];
                }
                if (this.y + 50 >= 450 && this.mapY + 1800 > 600) {
                    final int[] bushY = this.bushY;
                    final int n3 = i;
                    --bushY[n3];
                }
                if (this.y - 50 <= 150 && this.mapY < 0) {
                    final int[] bushY2 = this.bushY;
                    final int n4 = i;
                    ++bushY2[n4];
                }
            }
            for (int j = 0; j <= 5; ++j) {
                if (this.x + 50 >= 500 && this.caught[j] == 0 && this.mapX + 2100 > 700) {
                    final int[] pokemonX = this.pokemonX;
                    final int n5 = j;
                    --pokemonX[n5];
                }
                if (this.x - 50 <= 200 && this.caught[j] == 0 && this.mapX < 0) {
                    final int[] pokemonX2 = this.pokemonX;
                    final int n6 = j;
                    ++pokemonX2[n6];
                }
                if (this.y + 50 >= 450 && this.caught[j] == 0 && this.mapY + 1800 > 600) {
                    final int[] pokemonY = this.pokemonY;
                    final int n7 = j;
                    --pokemonY[n7];
                }
                if (this.y - 50 <= 150 && this.caught[j] == 0 && this.mapY < 0) {
                    final int[] pokemonY2 = this.pokemonY;
                    final int n8 = j;
                    ++pokemonY2[n8];
                }
                if ((this.x > this.pokemonX[j] - 200 && this.x < this.pokemonX[j] + 200 && this.y > this.pokemonY[j] - 200 && this.y < this.pokemonY[j] + 200 && this.caught[j] == 0) || (this.caught[j] != 0 && this.selected == j) || this.targeted == j) {
                    if (this.x > this.pokemonX[j] - 100 && this.x < this.pokemonX[j] + 100 && this.y > this.pokemonY[j] - 100 && this.y < this.pokemonY[j] + 100 && this.punching != 0 && this.caught[j] == 0) {
                        final int[] hp = this.hp;
                        final int n9 = j;
                        --hp[n9];
                    }
                    if (this.hp[j] < 10 && this.pokeball != 0) {
                        this.caught[j] = 1;
                    }
                    if (this.type[j] == 0) {
                        graphics.drawImage(this.charmander, this.pokemonX[j], this.pokemonY[j], 60, 60, null);
                    }
                    else if (this.type[j] == 1) {
                        graphics.drawImage(this.squirtle, this.pokemonX[j], this.pokemonY[j], 60, 60, null);
                    }
                    else if (this.type[j] == 2) {
                        graphics.drawImage(this.bulbasaur, this.pokemonX[j], this.pokemonY[j], 60, 60, null);
                    }
                    graphics.drawString("HP: " + this.hp[j], this.pokemonX[j], this.pokemonY[j] - 20);
                }
                if (this.selected == j && (this.xSpeed != 0 || this.ySpeed != 0) && this.targeted == -1) {
                    if (this.tailX > this.pokemonX[j]) {
                        final int[] pokemonX3 = this.pokemonX;
                        final int n10 = j;
                        ++pokemonX3[n10];
                    }
                    if (this.tailX < this.pokemonX[j]) {
                        final int[] pokemonX4 = this.pokemonX;
                        final int n11 = j;
                        --pokemonX4[n11];
                    }
                    if (this.tailY > this.pokemonY[j]) {
                        final int[] pokemonY3 = this.pokemonY;
                        final int n12 = j;
                        ++pokemonY3[n12];
                    }
                    if (this.tailY < this.pokemonY[j]) {
                        final int[] pokemonY4 = this.pokemonY;
                        final int n13 = j;
                        --pokemonY4[n13];
                    }
                }
                if (this.targeted != -1 && this.selected == j && this.hp[this.targeted] >= 10) {
                    if (this.pokemonX[this.targeted] > this.pokemonX[j]) {
                        final int[] pokemonX5 = this.pokemonX;
                        final int n14 = j;
                        ++pokemonX5[n14];
                    }
                    if (this.pokemonX[this.targeted] < this.pokemonX[j]) {
                        final int[] pokemonX6 = this.pokemonX;
                        final int n15 = j;
                        --pokemonX6[n15];
                    }
                    if (this.pokemonY[this.targeted] > this.pokemonY[j]) {
                        final int[] pokemonY5 = this.pokemonY;
                        final int n16 = j;
                        ++pokemonY5[n16];
                    }
                    if (this.pokemonY[this.targeted] < this.pokemonY[j]) {
                        final int[] pokemonY6 = this.pokemonY;
                        final int n17 = j;
                        --pokemonY6[n17];
                    }
                }
                if (this.selected != -1 && this.targeted != -1 && this.hp[this.targeted] > 9 && this.pokemonX[this.selected] > this.pokemonX[this.targeted] - 30 && this.pokemonX[this.selected] < this.pokemonX[this.targeted] + 30 && this.pokemonY[this.selected] > this.pokemonY[this.targeted] - 30 && this.pokemonY[this.selected] < this.pokemonY[this.targeted] + 30) {
                    final int[] hp2 = this.hp;
                    final int targeted = this.targeted;
                    --hp2[targeted];
                    if (this.hp[this.targeted] == 9) {
                        this.targeted = -1;
                    }
                }
                if (this.x > this.pokemonX[j] - 200 && this.x < this.pokemonX[j] + 200 && this.y > this.pokemonY[j] - 200 && this.y < this.pokemonY[j] + 200 && this.caught[j] == 0) {
                    this.shooting[j] = 1;
                }
                else {
                    this.shooting[j] = 0;
                }
                if (this.shooting[j] == 0) {
                    this.boltX[j] = this.pokemonX[j] + 30;
                    this.boltY[j] = this.pokemonY[j] + 30;
                }
                if (this.shooting[j] != 0) {
                    if (this.type[j] == 0) {
                        graphics.setColor(Color.RED);
                    }
                    else if (this.type[j] == 1) {
                        graphics.setColor(Color.CYAN);
                    }
                    else if (this.type[j] == 2) {
                        graphics.setColor(Color.GREEN);
                    }
                    graphics.fillRect(this.boltX[j], this.boltY[j], 10, 10);
                    ++this.shootTime;
                    if (this.shootTime == 10) {
                        this.boltX[j] -= (this.pokemonX[j] - this.x) / 10;
                        this.boltY[j] -= (this.pokemonY[j] - this.y) / 10;
                        this.shootTime = 0;
                    }
                }
                if (this.boltX[j] > this.x - 50 && this.boltX[j] < this.x + 50 && this.boltY[j] > this.y - 50 && this.boltY[j] < this.y + 50 && this.caught[j] == 0) {
                    this.boltX[j] = this.pokemonX[j] + 30;
                    this.boltY[j] = this.pokemonY[j] + 30;
                    this.playerHP -= 10;
                }
                if (this.boltX[j] > 700 || this.boltX[j] < 0 || this.boltY[j] > 600 || this.boltY[j] < 0) {
                    this.boltX[j] = this.pokemonX[j] + 30;
                    this.boltY[j] = this.pokemonY[j] + 30;
                }
            }
            graphics.setColor(Color.RED);
            graphics.drawImage(this.pokeballImg, 600, 500, 100, 100, null);
            graphics.drawString("Pokeballs: " + this.pokeballs, 600, 490);
        }
        if (this.pokedex == 1) {
            graphics.drawString("Your Pokemon: ", 100, 100);
            for (int k = 0; k <= 5; ++k) {
                if (this.caught[k] != 0) {
                    if (this.type[k] == 0) {
                        graphics.drawImage(this.charmander, k * 100 + 10, 200, 60, 60, null);
                    }
                    else if (this.type[k] == 1) {
                        graphics.drawImage(this.squirtle, k * 100 + 10, 200, 60, 60, null);
                    }
                    else if (this.type[k] == 2) {
                        graphics.drawImage(this.bulbasaur, k * 100 + 10, 200, 60, 60, null);
                    }
                    if (this.selected == k) {
                        graphics.drawRect(k * 100 + 10, 200, 60, 60);
                    }
                }
            }
        }
        graphics.setColor(Color.BLACK);
        graphics.drawImage(this.dex, 0, 500, 100, 100, null);
        graphics.setColor(Color.RED);
        graphics.fillRect(0, 0, 700, 50);
        graphics.setColor(Color.GREEN);
        graphics.fillRect(0, 0, this.playerHP, 50);
        if (this.playerHP <= 0) {
            graphics.drawImage(this.oops, 0, 0, 700, 600, null);
        }
        this.repaint();
    }

    public static void main(final String[] array) {
        final PokeTheMan pokeTheMan = new PokeTheMan();
        pokeTheMan.setVisible(true);
        pokeTheMan.setSize(700, 600);
    }

    private class Keys implements KeyListener
    {
        @Override
        public void keyPressed(final KeyEvent keyEvent) {
            final int keyCode = keyEvent.getKeyCode();
            if (keyCode == 87) {
                PokeTheMan.this.ySpeed = -1;
            }
            if (keyCode == 65) {
                PokeTheMan.this.xSpeed = -1;
            }
            if (keyCode == 83) {
                PokeTheMan.this.ySpeed = 1;
            }
            if (keyCode == 68) {
                PokeTheMan.this.xSpeed = 1;
            }
            if (keyCode == 90) {
                PokeTheMan.this.punching = 1;
            }
        }

        @Override
        public void keyTyped(final KeyEvent keyEvent) {
        }

        @Override
        public void keyReleased(final KeyEvent keyEvent) {
            PokeTheMan.this.xSpeed = 0;
            PokeTheMan.this.ySpeed = 0;
            PokeTheMan.this.punching = 0;
        }
    }

    private class Mouse implements MouseListener
    {
        @Override
        public void mousePressed(final MouseEvent mouseEvent) {
            final int y = MouseInfo.getPointerInfo().getLocation().y;
            final int x = MouseInfo.getPointerInfo().getLocation().x;
            if (x > 600 && y > 500 && PokeTheMan.this.pokeballs > 0) {
                --PokeTheMan.this.pokeballs;
                PokeTheMan.this.pokeball = 1;
            }
            if (x < 100 && y > 500 && PokeTheMan.this.pokedex == 0) {
                PokeTheMan.this.pokedex = 1;
            }
            if (x > 100 && x < 600 && y > 500 && PokeTheMan.this.pokedex == 1) {
                PokeTheMan.this.pokedex = 0;
            }
            for (int i = 0; i <= 5; ++i) {
                if (x > i * 100 + 10 && y > 200 && x < i * 100 + 70 && y < 270 && PokeTheMan.this.pokedex != 0) {
                    PokeTheMan.this.selected = i;
                }
                if (x > PokeTheMan.this.pokemonX[i] && x < PokeTheMan.this.pokemonX[i] + 60 && y > PokeTheMan.this.pokemonY[i] && y < PokeTheMan.this.pokemonY[i] + 60 && PokeTheMan.this.caught[i] == 0 && PokeTheMan.this.pokedex == 0) {
                    PokeTheMan.this.targeted = i;
                }
            }
        }

        @Override
        public void mouseClicked(final MouseEvent mouseEvent) {
        }

        @Override
        public void mouseReleased(final MouseEvent mouseEvent) {
            PokeTheMan.this.pokeball = 0;
        }

        @Override
        public void mouseEntered(final MouseEvent mouseEvent) {
        }

        @Override
        public void mouseExited(final MouseEvent mouseEvent) {
        }
    }
}