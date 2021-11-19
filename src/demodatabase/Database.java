package demodatabase;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Database {
    private final String name;

    public Database(String name) {
        this.name = name;
    }
    
    private Connection connect(){
        String url = "jdbc:sqlite:" + name;
        Connection conn = null;
 
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException ex) {
            System.out.println("" + ex.getMessage());
        }
        return conn;
    }
    
    private void close(Connection conn){
        if (conn != null){
            try {
                conn.close();
            } catch (SQLException ex) {
                System.out.println("" + ex.getMessage());
            }
        }
    }
    
    public void testQuery_2(){
        Connection conn = null;
        String sql = "SELECT * FROM CORREOS";
        
        try {
            conn = this.connect();
            java.sql.Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            while(rs.next()){
                System.out.println(rs.getInt("Id") + "\t" +
                rs.getString("Direccion")+ "\t");
            }
        } catch (SQLException ex) {
            System.out.println("" + ex.getMessage());
        } 
    }
    
    public void createNewDatabase(){
        Connection conn = null;
        String url = "jdbc:sqlite:" + name;
        
        try{
            conn = DriverManager.getConnection(url);
            if (conn != null){
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("El driver es" + meta.getDriverName());
                System.out.println("La base de datos se ha creado");
            }
        } catch (SQLException ex) {
            System.out.println("" + ex.getMessage());
        } finally {
            close(conn);
        }
  
    }    
        
        
    public void createNewTable(){
        Connection conn = null;
        String sql = "CREATE TABLE IF NOT EXISTS CORREOS (\n" +
                " Id integer PRIMARY KEY AUTOINCREMENT, \n" +
                " Direccion text NOT NULL);";
        try{
            conn = this.connect();
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
        } catch (SQLException ex){
            System.out.println("" + ex.getMessage());   
        } finally {
            close(conn);
        }
       
    }    
    
    
    public void insertData(String email){
        Connection conn = null;
        String sql = "INSERT INTO CORREOS(Direccion) VALUES (?)";
        
        try{
            conn= this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, email);
            pstmt.executeUpdate();
        } catch (SQLException ex){
            System.out.println("" + ex.getMessage());
        } finally {
            close(conn);
        }
    }
}
