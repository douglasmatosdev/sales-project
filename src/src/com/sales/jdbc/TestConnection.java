package src.com.sales.jdbc;

public class TestConnection {
    public static void main(String[] args) {
        try {
            new ConnectionFactory().getConnection();
            System.out.println("Success");
        } catch (Exception e) {
            System.out.println("Error");
        }

    }
}
