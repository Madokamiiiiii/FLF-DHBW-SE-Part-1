package komplexaufgabe1;

import airportfiretruck.AirportFireTruck;
import airportfiretruck.cabin.BusDoor;
import airportfiretruck.centralunit.CentralUnit;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ComplexCentralUnit extends CentralUnit implements IIdCentralUnit, ICabinCentralUnit {
    private AirportFireTruck flf;
    private final String code = "6072";
    private final String identifier = "DUS | FLF-5";
    private final List<Person> attached = new ArrayList<>() {
    };
    SecretKey secretKey;
    Cipher ecipher, dcipher;

    public ComplexCentralUnit() {
        super();
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
        byte[] clearBytes = String.join("-", "FT", identifiers[0], identifiers[1], name, code).getBytes();
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
        for (Person person : ((Complex1AirportFireTruck) flf).getPersons()) {
            register(person);
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
        String leftSide = String.join("-", "FT", identifiers[0], identifiers[1]);
        String regex = String.join("-", leftSide, "[\\p{L} .'-]+", this.code);
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(decryptedString);
        if (matcher.find()) {
            name = matcher.group().replaceFirst(leftSide + "-", "").replaceFirst("-" + this.code, "");
        } else {
            return false;
        }
        for (Person person : ((Complex1AirportFireTruck) flf).getPersons()) {
            if (person.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void setFlf(AirportFireTruck flf) {
        this.flf = flf;
        registerPersons();
    }

    @Override
    public void unLockDoors() {
        for (BusDoor door : this.flf.getCabin().getDoors()) {
            if (door.isOpen() && !((ComplexBusDoor) door).isLocked() || !door.isOpen() && ((ComplexBusDoor) door).isLocked()) {
                door.openClose();
            }
            ((ComplexBusDoor) door).lock();
        }
    }
}
