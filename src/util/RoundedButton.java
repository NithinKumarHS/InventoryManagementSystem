package util;

import java.awt.*;

import javax.swing.JButton;
import javax.swing.*;

//public class RoundedButton extends JButton{
//	public RoundedButton(JButton baseButton) {
//        super(baseButton.getText(), baseButton.getIcon());
//        setFont(baseButton.getFont());
//        setBackground(baseButton.getBackground());
//        setForeground(baseButton.getForeground());
//        setCursor(baseButton.getCursor());
//        setFocusPainted(baseButton.isFocusPainted());
//        setContentAreaFilled(false);
//        setOpaque(false);
//    }
//
//    @Override
//    protected void paintComponent(Graphics g) {
//        Graphics2D g2 = (Graphics2D) g.create();
//        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//
//        // Draw rounded background
//        g2.setColor(getBackground());
//        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 50, 50); // corner radius = 20
//
//        super.paintComponent(g);
//        g2.dispose();
//    }
//}

public class RoundedButton extends JButton {

    public RoundedButton(JButton baseButton) {
        super(baseButton.getText(), baseButton.getIcon());
        setFont(baseButton.getFont());
        setBackground(baseButton.getBackground());
        setForeground(baseButton.getForeground());
        setCursor(baseButton.getCursor());
        setFocusPainted(baseButton.isFocusPainted());
        setContentAreaFilled(false);
        setOpaque(false);
        setVerticalTextPosition(SwingConstants.BOTTOM);
        setHorizontalTextPosition(SwingConstants.CENTER);
        setIconTextGap(baseButton.getIconTextGap());
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw rounded background
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);

        // Let Swing handle icon + text layout correctly
        super.paintComponent(g2);

        g2.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
        // Optional: Draw rounded border
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getForeground());
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
        g2.dispose();
    }
}
