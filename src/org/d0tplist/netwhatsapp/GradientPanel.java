/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.d0tplist.netwhatsapp;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.RenderingHints;

import javax.swing.JPanel;

/**
 *
 * @author alex
 */
public class GradientPanel extends JPanel {

    private boolean rounded = false;
    private int round = 5;
    private int opacitySpeed = 5;
    private int animationDelay = 200;
    private float trianguloHeight = 0.0f;

    protected final int[] opacity = new int[2];
    protected final Color[] colors
            = {Color.WHITE, Color.WHITE,
                Color.WHITE, Color.WHITE};

    private boolean randomGradient = true;
    private boolean schwifty = false;

    private float offsetX = 0.0f;
    private float offsetY = 0.0f;

    private float weightPercent = 1.0f;
    private float heightPercent = 1.0f;

    private float desfaceX = 0.5f;
    private float desfaceY = 0.5f;

    private float secondCordinateX = 0.0f;
    private float secondCordinateY = 0.0f;

    public GradientPanel() {
        opacity[0] = 255;
        opacity[1] = 0;

        for (int i = 0; i < colors.length; i++) {
            colors[i] = Color.WHITE;
        }

        super.setOpaque(false);
    }

    public int getAnimationDelay() {
        return animationDelay;
    }

    public void setAnimationDelay(int animationDelay) {
        this.animationDelay = animationDelay;
    }

    public void setOpacitySpeed(int opacitySpeed) {
        this.opacitySpeed = opacitySpeed;
    }

    public int getOpacitySpeed() {
        return opacitySpeed;
    }

    public float getOffsetX() {
        return offsetX;
    }

    public void setOffsetX(float offsetX) {
        this.offsetX = offsetX;
    }

    public float getOffsetY() {
        return offsetY;
    }

    public void setOffsetY(float offsetY) {
        this.offsetY = offsetY;
    }

    public float getWeightPercent() {
        return weightPercent;
    }

    public void setWeightPercent(float weightPercent) {
        this.weightPercent = weightPercent;
    }

    public float getHeightPercent() {
        return heightPercent;
    }

    public void setHeightPercent(float heightPercent) {
        this.heightPercent = heightPercent;
    }

    public void setDesfaceX(float desfaceX) {
        this.desfaceX = desfaceX;
        repaint();
    }

    public void setDesfaceY(float desfaceY) {
        this.desfaceY = desfaceY;
        repaint();
    }

    public float getDesfaceX() {
        return desfaceX;
    }

    public float getDesfaceY() {
        return desfaceY;
    }

    public void setSecondCordinateX(float secondCordinateX) {
        this.secondCordinateX = secondCordinateX;
        repaint();
    }

    public void setSecondCordinateY(float secondCordinateY) {
        this.secondCordinateY = secondCordinateY;
        repaint();
    }

    public float getSecondCordinateX() {
        return secondCordinateX;
    }

    public float getSecondCordinateY() {
        return secondCordinateY;
    }

    private long lastOpacity = 0L;

    private void nextOpacity() {

        if (System.currentTimeMillis() - lastOpacity < animationDelay) {
            return;
        }

        if (schwifty) {
            opacity[0] += opacitySpeed;
            opacity[1] -= opacitySpeed;
        } else {
            opacity[1] += opacitySpeed;
            opacity[0] -= opacitySpeed;
        }

        opacity[0] = Math.max(opacity[0], 0);
        opacity[1] = Math.max(opacity[1], 0);

        opacity[0] = Math.min(opacity[0], 255);
        opacity[1] = Math.min(opacity[1], 255);

        if (opacity[0] == 255 || opacity[0] == 0) {
            schwifty = !schwifty;
            nextBackground();
        }

        lastOpacity = System.currentTimeMillis();
    }

    private int rangeCheck(int a) {
        a = Math.max(a, 0);
        a = Math.min(a, 255);
        return a;
    }

    public final void nextBackground() {

        //setBackground(colorResultanteA);
        //setForeground(colorResultanteB);
        int AR = (int) Math.floor(Math.random() * 255);
        int AG = (int) Math.floor(Math.random() * 255);
        int AB = (int) Math.floor(Math.random() * 255);

        int BR = (int) Math.floor(Math.random() * 255);
        int BG = (int) Math.floor(Math.random() * 255);
        int BB = (int) Math.floor(Math.random() * 255);

        AR = rangeCheck(AR);
        AG = rangeCheck(AG);
        AB = rangeCheck(AB);

        BR = rangeCheck(BR);
        BG = rangeCheck(BG);
        BB = rangeCheck(BB);

        colors[schwifty ? 0 : 2] = new Color(AR, AG, AB);
        colors[schwifty ? 1 : 3] = new Color(BR, BG, BB);
    }

    public Color getColor(Color c, int opacity) {
        return new Color(c.getRed(), c.getGreen(), c.getBlue(), opacity);
    }

    public String toString(Color color) {
        return color.getRed() + "," + color.getGreen() + "," + color.getBlue();
    }

    public void setRound(int round) {
        this.round = round;
    }

    public int getRound() {
        return round;
    }

    public boolean isRounded() {
        return rounded;
    }

    public void setRounded(boolean rounded) {
        this.rounded = rounded;
    }

    public void setRandomGradient(boolean randomGradient) {
        this.randomGradient = randomGradient;
        if (!randomGradient) {
            opacity[0] = 254;
            opacity[1] = 254;
        }
        repaint();
    }

    public boolean isRandomGradient() {
        return randomGradient;
    }

    private GradientPaint getGradient(boolean first) {
        if (first) {
            return new GradientPaint(getWidth() * desfaceX, getHeight() * desfaceY, getColor(colors[0], opacity[0]),
                    0.0f, 0.0f, getColor(colors[1], opacity[0]));
        } else {
            return new GradientPaint(Math.round(getWidth() * desfaceX), getHeight() * desfaceY, getColor(colors[2], opacity[1]),
                    0.0f, 0.0f, getColor(colors[3], opacity[1]));
        }
    }

    @Override
    public void paint(Graphics gr) {
        if (gr == null) {
            return;
        }

        Graphics2D g = (Graphics2D) gr;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Paint paint = g.getPaint();

        if (randomGradient) {
            nextOpacity();

            g.setColor(getBackground());
            g.fillRect(0, 0, getWidth(), getHeight());

            paintGradient(g, getGradient(true));
            paintGradient(g, getGradient(false));
        } else {
            paintGradient(g, getGradient(true));
        }

        g.setPaint(paint);

        if (trianguloHeight > 0) {
            g.setColor(Color.WHITE);
            int[] xy = new int[]{0, getWidth(), getWidth()};
            int[] yx = new int[]{getHeight(), getHeight(), Math.round(getHeight() * trianguloHeight)};
            g.fillPolygon(xy, yx, xy.length);
        }

        super.paintBorder(gr);
        super.paintChildren(gr);
    }

    public float getTrianguloHeight() {
        return trianguloHeight;
    }

    public void setTrianguloHeight(float trianguloHeight) {
        this.trianguloHeight = trianguloHeight;
        repaint();
    }

    private void paintGradient(Graphics2D g, GradientPaint gradient) {
        g.setPaint(gradient);

        if (rounded) {
            g.fillRoundRect(Math.round(getWidth() * offsetX), Math.round(getHeight() * offsetY),
                    Math.round(getWidth() * weightPercent), Math.round(getHeight() * heightPercent), round, round);
        } else {
            g.fillRect(Math.round(getWidth() * offsetX), Math.round(getHeight() * offsetY),
                    Math.round(getWidth() * weightPercent), Math.round(getHeight() * heightPercent));
        }
    }

    @Override
    public void setBackground(Color bg) {
        if (bg != null && colors != null) {
            colors[0] = bg;
        }
        super.setBackground(bg);
    }

    @Override
    public void setForeground(Color fg) {
        if (fg != null && colors != null) {
            colors[1] = fg;
        }
        super.setForeground(fg);
    }

    @Override
    public void setOpaque(boolean ignored) {
        super.setOpaque(false);
    }

}
