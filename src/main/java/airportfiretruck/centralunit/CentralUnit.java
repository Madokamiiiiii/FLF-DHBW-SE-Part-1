package airportfiretruck.centralunit;

import airportfiretruck.AirportFireTruck;
import airportfiretruck.cabin.BusDoor;
import airportfiretruck.cabin.panel.rotaryknobs.FrontThrowerKnob;
import airportfiretruck.cabin.panel.rotaryknobs.IRotaryKnob;
import airportfiretruck.cabin.panel.rotaryknobs.RoofThrowerKnob;
import airportfiretruck.cabin.panel.switches.RelatedDevice;
import airportfiretruck.cabin.pedals.PedalType;
import airportfiretruck.engine.IEngine;
import airportfiretruck.extinguisher.thrower.FloorSprayNozzle;
import airportfiretruck.id.IdCard;
import airportfiretruck.id.RfidChip;
import airportfiretruck.lights.BrakeLight;
import airportfiretruck.lights.DirectionIndicatorLight;
import airportfiretruck.lights.HeadLight;
import airportfiretruck.lights.SideLight;
import airportfiretruck.lights.led.BlueLight;
import airportfiretruck.lights.led.WarningLight;
import airportfiretruck.person.Person;
import airportfiretruck.position.LeftRightSide;
import airportfiretruck.position.Position;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CentralUnit implements IPedalCentralUnit, ISteeringCentralUnit, IThrowerCentralUnit, IControlPanelCentralUnit, IIdCentralUnit, ICabinCentralUnit {
    private AirportFireTruck flf;
    private final String code = "6072";
    private final String identifier = "DUS | FLF-5";
    private final List<Person> attached = new ArrayList<>() {
    };
    SecretKey secretKey;
    Cipher ecipher, dcipher;

    public CentralUnit() {
        try {
            ecipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            dcipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            secretKey = KeyGenerator.getInstance("DES").generateKey();
            ecipher.init(Cipher.ENCRYPT_MODE, secretKey);
            dcipher.init(Cipher.DECRYPT_MODE, secretKey);
        } catch (Exception e) {

        }
    }

    public byte[] encryptName(String name) throws Exception {
        String[] identifiers = this.identifier.split(" \\| ");
        byte[] clearBytes = java.lang.String.join("-", "FT", identifiers[0], identifiers[1], name, code).getBytes();
        return ecipher.doFinal(clearBytes);
    }

    public void register(Person person) {
        if (attached.contains(person)) {
            return;
        }
        byte[] encrypted;
        try {
            encrypted = encryptName(person.getName());
        } catch (Exception e) {
            return;
        }
        IdCard idCard = new IdCard(person, new RfidChip(encrypted));
        person.setIdCard(idCard);
        attached.add(person);
    }

    public void registerPersons() {
        for (Person person : flf.getPersons()) {
            register(person);
        }
    }

    @Override
    public void pedal(PedalType pedalType) {
        int sign = 4;
        if (pedalType == PedalType.BRAKE) {
            sign = -4;
        }

        int newVelocity = flf.getEngines().get(0).getVelocity() + sign;
        if (newVelocity < 0) { // Nicht rückwarts fahren
            return;
        }
        flf.getCabin().getDisplays().get(1).setValue(newVelocity);

        for (IEngine engine : flf.getEngines()) {
            engine.setVelocity(newVelocity);
            engine.rotate(newVelocity);
            flf.getCabin().getDisplays().get(0).setValue(engine.getBatteryManagement().getRemainingBatteryLevel());
        }
        for (BrakeLight brakeLight : flf.getBrakeLights()) {
            if (sign < 0) {
                brakeLight.on();
                continue;
            }
            brakeLight.off();
        }
    }

    @Override
    public void panelSwitch(RelatedDevice device, boolean isOn) {
        switch (device) {
            case ENGINES -> {
                for (IEngine engine : flf.getEngines()) {
                    if (isOn) {
                        engine.on();
                        continue;
                    }
                    engine.off();
                }
            }
            case BLUE_LIGHTS -> {
                for (BlueLight blueLight : flf.getBlueLights()) {
                    if (isOn) {
                        blueLight.on();
                        continue;
                    }
                    blueLight.off();
                }
            }
            case ROOF_LIGHTS -> {
                for (HeadLight headLight : flf.getHeadLights()) {
                    if (headLight.getPosition() == Position.TOP) {
                        if (isOn) {
                            headLight.on();
                            continue;
                        }
                        headLight.off();
                    }
                }
            }
            case SIDE_LIGHTS -> {
                for (SideLight sideLight : flf.getSideLights()) {
                    if (isOn) {
                        sideLight.on();
                        continue;
                    }
                    sideLight.off();
                }
            }
            case FRONT_LIGHTS -> {
                for (HeadLight headLight : flf.getHeadLights()) {
                    if (headLight.getPosition() == Position.BOTTOM) {
                        if (isOn) {
                            headLight.on();
                            continue;
                        }
                        headLight.off();
                    }
                }
            }
            case WARNING_LIGHTS -> {
                for (WarningLight warningLight : flf.getWarningLights()) {
                    if (isOn) {
                        warningLight.off();
                        continue;
                    }
                    warningLight.on();
                }
            }
            case SELF_PROTECTION -> {
                for (FloorSprayNozzle floorSprayNozzle : flf.getFloorSprayNozzles()) {
                    floorSprayNozzle.setOn(isOn);
                }
            }
        }
    }

    @Override
    public void steer() {
        int position = flf.getCabin().getSteeringWheel().getPosition();
        flf.getFrontAxles().get(0).setSteeringAngle(position);
        flf.getFrontAxles().get(1).setSteeringAngle(position);
        if (position == 0) {
            for (DirectionIndicatorLight directionIndicatorLight : flf.getDirectionIndicatorLights()) {
                directionIndicatorLight.off();
            }
        }
        if (position < 0) {
            for (DirectionIndicatorLight directionIndicatorLight : flf.getDirectionIndicatorLights()) {
                if (directionIndicatorLight.getLeftRightSide() == LeftRightSide.LEFT) {
                    directionIndicatorLight.on();
                    continue;
                }
                directionIndicatorLight.off();
            }
        }
        if (position > 0) {
            for (DirectionIndicatorLight directionIndicatorLight : flf.getDirectionIndicatorLights()) {
                if (directionIndicatorLight.getLeftRightSide() == LeftRightSide.RIGHT) {
                    directionIndicatorLight.on();
                    continue;
                }
                directionIndicatorLight.off();
            }
        }
    }

    @Override
    public void thrower(IRotaryKnob rotaryKnob) {
        switch (rotaryKnob.getType()) {
            case ROOF -> flf.getRoofThrower().setLevel(((RoofThrowerKnob) rotaryKnob).getLevel());
            case FRONT -> flf.getFrontThrower().setLevel(((FrontThrowerKnob) rotaryKnob).getLevel());
        }
    }

    @Override
    public boolean validateCode(byte[] code) {
        byte[] decrypted;
        String name;
        try {
            decrypted = dcipher.doFinal(code);
        } catch (Exception e) {
            return false;
        }
        String decryptedString = new String(decrypted);
        String[] identifiers = this.identifier.split(" \\| ");
        String leftSide = java.lang.String.join("-", "FT", identifiers[0], identifiers[1]);
        String regex = java.lang.String.join("-", leftSide, "[\\p{L} .'-]+", this.code);
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(decryptedString);
        if (matcher.find()) {
            name = matcher.group().replaceFirst(leftSide + "-", "").replaceFirst("-" + this.code, "");
        } else {
            return false;
        }
        for (Person person : flf.getPersons()) {
            if (person.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public void setFlf(AirportFireTruck flf) {
        this.flf = flf;
        registerPersons();
    }

    @Override
    public void unLockDoors() {
        for (BusDoor door : this.flf.getCabin().getDoors()) {
            if (door.state() && !door.isLocked() || !door.state() && door.isLocked()) {
                door.openClose();
            }
            door.lock();
        }
    }
}
