import java.util.List;

public interface IMixer {
    int getMix(Integer amount, Integer foamRatio);

    List<ITank> getTanks();
}
