import java.util.List;

public class Depot {

    private List<Aktie> aktien;

    public Depot(String userId, List<Aktie> aktien) {

        this.aktien = aktien;
    }

    public List<Aktie> getAktien() {
        return aktien;
    }

    public void setAktien(List<Aktie> aktien) {
        this.aktien = aktien;
    }
}