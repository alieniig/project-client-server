import java.util.HashMap;
import java.util.Map;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import javax.persistence.*; // Add this line to import the javax.persistence package
import javax.persistence.Column; // Add this line to import the Column class

@Entity
@Table(name = "nutzer")
public class Nutzer {
    @Id
    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Transient
    private Map<Aktie, Integer> depot = new HashMap<>();

    // Angenommen, dass 'Konto' auch eine Entität ist:
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nutzer")
    private Konto konto;

    // Constructor
    public Nutzer(String username, String password) {
        this.username = username;
        this.password = password;
        this.depot = new HashMap<>();
        this.konto = new Konto(0.0);
    }

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Map<Aktie, Integer> getDepot() {
        return depot;
    }

    public void setDepot(Map<Aktie, Integer> depot) {
        this.depot = depot;
    }

    public void printDepot() {
        System.out.println(depot.toString());
    }

    public void kaufeAktie(Aktie aktie, int anzahl) {
        this.depot.put(aktie, anzahl);
    }

    public Konto getKonto() {
        return konto;
    }

    public void setKonto(Konto konto) {
        this.konto = konto;
    }

    public void verkaufeAktie(Aktie aktie, int anzahl) {
        if (this.depot.containsKey(aktie)) {
            int anzahlVorher = this.depot.get(aktie);
            if (anzahlVorher > anzahl) {
                this.depot.put(aktie, anzahlVorher - anzahl);
            } else if (anzahlVorher == anzahl) {
                this.depot.remove(aktie);
            } else {
                System.out.println("Nicht genügend Aktien vorhanden.");
            }
        } else {
            System.out.println("Aktie nicht vorhanden.");
        }
    }
}
