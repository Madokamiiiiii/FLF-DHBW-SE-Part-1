package komplexaufgabe1;

public class Person {
    private String name;
    private IdCard idCard;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setIdCard(IdCard idCard) {
        this.idCard = idCard;
    }

    public IdCard getIdCard() {
        return this.idCard;
    }

    public void useCard(ComplexBusDoor targetDoor) {
        this.idCard.use(targetDoor);
    }
}
