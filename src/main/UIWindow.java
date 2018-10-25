package main;

import main.fixture.Fixture;
import main.universe.PatchIdentity;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class UIWindow {
    private JPanel panel1;
    private JSlider slider1;
    private JSlider slider2;
    private JSlider slider3;
    private JSlider slider4;
    private JSlider slider5;
    private JSlider slider6;
    private JSlider slider7;
    private JSlider slider8;
    private JSlider slider9;

    private Fixture light;
    private void createUIComponents() {
        // TODO: place custom component creation code here
    }


    public UIWindow(Fixture lo) {
        light = lo;

        JFrame window = new JFrame("UIWindow");
        window.setContentPane(panel1);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack();
        window.setVisible(true);


        ChangeListener changeListener = new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                updateFixture();
            }
        };

        slider1.addChangeListener(changeListener);
        slider2.addChangeListener(changeListener);
        slider3.addChangeListener(changeListener);
        slider4.addChangeListener(changeListener);
        slider5.addChangeListener(changeListener);
        slider6.addChangeListener(changeListener);
        slider7.addChangeListener(changeListener);
        slider8.addChangeListener(changeListener);
        slider9.addChangeListener(changeListener);
    }

    public void updateFixture(){
        light.setValue(PatchIdentity.RED, (byte) slider1.getValue());
        light.setValue(PatchIdentity.WHITE, (byte) slider2.getValue());
        light.setValue(PatchIdentity.YELLOW, (byte) slider3.getValue());
        light.setValue(PatchIdentity.CYAN, (byte) slider4.getValue());
        light.setValue(PatchIdentity.GREEN, (byte) slider5.getValue());
        light.setValue(PatchIdentity.BLUE, (byte) slider6.getValue());
        light.setValue(PatchIdentity.PURPLE, (byte) slider7.getValue());
        light.setValue(PatchIdentity.INTENSITY, (byte) slider8.getValue());
        light.setValue(PatchIdentity.STROBE, (byte) slider9.getValue());
    }

}
