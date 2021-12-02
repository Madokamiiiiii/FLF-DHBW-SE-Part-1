package airportfiretruck.extinguisher.thrower;

import airportfiretruck.cabin.joysticks.IJoystick;
import airportfiretruck.extinguisher.watersupply.Mixer;

public class FrontThrower extends ThrowerMixer {
    private int degree = 0;
    private final int maxDegree = 180;
    private int level;

    public FrontThrower(IJoystick joystick, Mixer mixer, int limit) {
        super(mixer, limit);
        joystick.assign(this);
    }

    public void setDegree(int degree) {
        if (degree >= 0) {
            this.degree = Math.min(degree, maxDegree);
        } else {
            this.degree = 0;
        }
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public void spray() {
        // same here: evaluate??
        // mixer.getLiquid(level);

        // Wenn die Kapazität überschritten werden würde, nimm die maximale Kapazität.
        // Nach der Spezifikation dürfte das aber nicht erreicht werden.
        mixer.getLiquid(Math.min(level, limit), mixingRatio);
    }
}
