package airportfiretruck.cabin.panel.rotaryknobs;

import airportfiretruck.cabin.panel.ControlPanel;

public class FrontThrowerKnob extends RotaryKnob {
    private int level;

    private final ThrowerType type;

    public FrontThrowerKnob(ControlPanel controlPanel) {
        this.controlPanel = controlPanel;
        type = ThrowerType.FRONT;
        level = 0;
    }

    @Override
    public ThrowerType getType() {
        return type;
    }

    public int getLevel() {
        return level;
    }

    @Override
    public void turnLeft() {
        switch (level) {
            case 500 -> level = 0;
            case 1000 -> level = 500;
            case 1500 -> level = 1000;
            case 2000 -> level = 1500;
            case 2500 -> level = 2000;
            case 3000 -> level = 2500;
            case 3500 -> level = 3000;
            default -> {
                // Noop
            }
        }
        controlPanel.thrower(this);
    }

    @Override
    public void turnRight() {
        switch (level) {
            case 0 -> level = 500;
            case 500 -> level = 1000;
            case 1000 -> level = 1500;
            case 1500 -> level = 2000;
            case 2000 -> level = 2500;
            case 2500 -> level = 3000;
            case 3000 -> level = 3500;
            default -> {
                // Noop
            }
        }
        controlPanel.thrower(this);
    }
}
