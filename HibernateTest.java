import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HibernateTest {

    public static void main(String[] args) {
        // Create a SessionFactory
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

        Session session = null;
        Transaction transaction = null;
        try {
            // Open a new Session
            session = sessionFactory.openSession();

            // Begin a transaction
            transaction = session.beginTransaction();

            // Perform a simple query
            List<Nutzer> result = session.createQuery("from Nutzer").list();

            // Print the result
            for (Nutzer nutzer : result) {
                System.out.println(nutzer.getUsername());
            }

            // Commit the transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }
    }
}
