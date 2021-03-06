package client.gui.elements;

import client.GuiView;
import client.gui.components.PButton;
import client.gui.components.PLabel;
import client.gui.components.PPanelContainer;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * A color switcher with an action listener that enable single selection
 */
public class ColorSwitcher {
    private final GuiView guiView;
    private final PPanelContainer bodyContainer;

    private final PPanelContainer colorContainer;

    /**
     * Constructor: build a color switcher
     * @param bodyContainer The container of the player switcher
     * @param guiView       The guiView that controls the bodyContainer
     */
    public ColorSwitcher(PPanelContainer bodyContainer, GuiView guiView){
        this.bodyContainer=bodyContainer;
        this.guiView=guiView;

        // Prepares the external container
        colorContainer = new PPanelContainer();
        colorContainer.setBounds(0, 80, bodyContainer.getWidth(), 292);
        colorContainer.setLayout(new GridLayout(1, 3, 10, 0));
    }

    /**
     * Adds the switcher to the container in a default position with a single selection action listener
     * @param colors    All the colors to be added to the switcher
     */
    public void showSwitcher(ArrayList<String> colors){
        bodyContainer.add(colorContainer);

        for (String color : colors) {
            Image scaledImage = (new ImageIcon(getClass().getResource("/GuiResources/"+color.toLowerCase()+"_choice.png")))
                    .getImage().getScaledInstance(273, 292, Image.SCALE_SMOOTH);
            PButton button = new PButton(scaledImage);
            colorContainer.add(button);
            button.addActionListener((ev) -> (new Thread(() -> guiView.getServerHandler().sendPlayerColor(color))).start());
        }
    }

    /**
     * Sets the text of the color switcher heading
     * @param heading   The text to be shown
     */
    public void setHeading(String heading){
        PLabel label = new PLabel(heading);
        label.setFontSize(30);
        label.setBounds(0, 10, 840, 40);
        bodyContainer.add(label);
    }
}
