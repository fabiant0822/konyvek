package konyvek;

import java.awt.Color;
import static java.awt.Color.red;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Fabian Tamas 1.0
 */
public class DB {
    Connection kapcs = null;
    Statement parancs = null;
    ResultSet eredmeny = null;
    PreparedStatement ekp = null;
    
    final String dbUrl = "jdbc:mysql://localhost:3306/konyvek";
    final String user = "root";
    final String pass = "";
    
    public DB() {
        try {
            //kapcsolódás
            kapcs = DriverManager.getConnection(dbUrl, user, pass);
            System.out.println("A kapcsolat létrejött.");
            
            //parancs létrehozása
            parancs = kapcs.createStatement();
        
        }   catch (SQLException ex) {
            System.out.println("" + ex);
        }
    }
    
    //adatok kiíratása
    public void kiir(int ev) {
        try {
            ekp = kapcs.prepareStatement("SELECT * FROM adatok WHERE ev = ?");
            ekp.setInt(1, ev);
            eredmeny = ekp.executeQuery();
            while (eredmeny.next()) {
                System.out.printf("%2d %-30s %-35s %-35s %d\n",
                    eredmeny.getInt("id"),
                    eredmeny.getString("szerzo"),
                    eredmeny.getString("cim"),
                    eredmeny.getString("eredeti"),
                    eredmeny.getInt("ev"));
            }
        } catch (SQLException ex) {
            System.out.println("Hiba a lekérdezésben!");
        }
    }
}
