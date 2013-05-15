package controleestoque.util;


import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class conexao {

    /** Creates a new instance of ConexaoMysql */
    Connection conexao = null;

    public conexao() {

    }

    public Connection conectarBd(){
        String username = "root";
        String password = "123";
        String hostname = "localhost";
        String portNumber = "3306";
        String database = "mysql";
    	String jdbcDriver = "org.gjt.mm.mysql.Driver";
    	String defaultUrl= "jdbc:mysql://" + hostname + ":" + portNumber + "/" + database;


        try {
            /**
             * Carrega o driver JDBC
             * Driver JDBC MySQL
             */
            Class.forName(jdbcDriver);

            /**
             * Cria a conexao com o banco de dados
             */
            conexao = DriverManager.getConnection(defaultUrl, username, password);
        }
        catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Falha Teste: Driver do BD nao encontrado" + e, "Resultado", JOptionPane.ERROR_MESSAGE);
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Falha Teste: BD nao encontrado" + e, "Resultado", JOptionPane.ERROR_MESSAGE);
        }

        return conexao;
    }

    public void closeBd(){
        try {
            conexao.close();
        } catch (SQLException ex) {
            Logger.getLogger(conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
