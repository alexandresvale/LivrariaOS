package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import sun.applet.Main;

/**
 *
 * @author Alexandre
 */
public class Conexao {
    private static String drive = "com.mysql.jdbc.Driver";
    private static String usuario = "root";
    private static String senha = "a251091e";
    private static String banco = "dblivro";
    private static String server ="localhost:3306";
    private static String url = "jdbc:mysql://" + server + "/" + banco;
    
    private static String status = "Não conectado ao banco.";
    
    public Conexao() {
        
    }
    
    public static Connection abrirConexao(){
        Connection conexao = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            
            conexao = DriverManager.getConnection(url, "root", "a251091e");
            if(conexao != null){
                status = "Conectado com sucesso";
            }else{
                status = "Não foi possivel realizar conexão com banco.";
            }
            
            return conexao;
        }catch(ClassNotFoundException e){
            System.err.println("O driver não foi encontrado.");
            return null;
            
        }catch(SQLException e){
            System.err.println("Não foi possivel conectar ao banco.");
            return null;
            
        }
        
    }

    public String getStatus(){
        return status;
    }
    
    public boolean fecharConexao(){
        try {
            Conexao.abrirConexao().close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
   
}
