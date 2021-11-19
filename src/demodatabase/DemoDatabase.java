package demodatabase;

public class DemoDatabase {

    public static void main(String[] args) {
            Database database = new Database("prueba2.db");
            database.createNewDatabase();
            database.createNewTable();
            database.insertData("a@a.com");
            database.insertData("b@a.com");
            database.insertData("c@a.com");
            database.testQuery_2();
            
    }
    
}
