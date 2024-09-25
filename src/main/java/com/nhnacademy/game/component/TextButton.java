package com.nhnacademy.game.component;

import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.Objects;

public class TextButton extends JButton {

    public TextButton(String text, ActionListener actionListener) {
        if (Objects.isNull(text)) {
            throw new IllegalArgumentException("button text is null");
        }

        if (Objects.nonNull(actionListener)) {
            addActionListener(actionListener);
        }

        setPreferredSize(new Dimension(100, 60));
        setFont(new Font(text, Font.BOLD, 18));
        setText(text);
    }
}
