package airportfiretruck.extinguisher.thrower;

import airportfiretruck.cabin.joysticks.IJoystick;
import airportfiretruck.extinguisher.watersupply.Mixer;

public class FrontThrower extends ThrowerMixer {
    private int degree;
    private final int maxDegree;
    private int level;

    public FrontThrower(IJoystick joystick, Mixer mixer, int limit) {
        super(mixer, limit);
        joystick.assign(this);
        degree = 0;
        maxDegree = 180;
    }

    public void setDegree(int degree) {
        if (degree >= 0) {
            this.degree = Math.min(degree, maxDegree);
        } else {
            this.degree = 0;
        }
    }

    public int getDegree() {
        return degree;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public void spray() {
        // Wenn die Kapazität überschritten werden würde, nimm die maximale Kapazität.
        // Nach der Spezifikation dürfte das aber nicht erreicht werden.
        int output = mixer.getLiquid(Math.min(level, limit), mixingRatio);
        if (output != Math.min(level, limit)) {
            throw new RuntimeException("Not enough water/foam in tank");
        }
    }
}
