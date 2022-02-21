package komplexaufgabe1;

public class Person {
    private String name;
    private IdCard idCard;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public IdCard getIdCard() {
        return this.idCard;
    }

    public void setIdCard(IdCard idCard) {
        this.idCard = idCard;
    }

    public void useCard(ComplexBusDoor targetDoor) {
        this.idCard.use(targetDoor);
    }
}
