/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import models.Livro;


/**
 *
 * @author Alexandre
 */
public class LivroDao {
    Livro livro;
    Conexao conexao =  new Conexao();
    
    public boolean inserirLivro(Livro livro) throws SQLException{
        Connection con = null;
        String sql = "INSERT INTO LIVRO (nome, autor, anoedicao, notausuario, resenha, imagem, datadecadastro)"
                + "VALUES (?,?,?,?,?,?,?)";
        try{
            File file = livro.getImagemLivro();
            FileInputStream fis = new FileInputStream(file);
            con  = conexao.abrirConexao();
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, livro.getNomeDoLivro());
            statement.setString(2, livro.getAutor());
            statement.setInt(3, livro.getAnoDeEdicao());
            statement.setString(4, livro.getNotaDoUsuario());
            statement.setString(5, livro.getResenha());
            statement.setBinaryStream(6, fis, (int)file.length());
            statement.setTimestamp(7, (Timestamp) livro.getDataDeCadastro());
            statement.executeUpdate();
            statement.close();
            System.err.println("cadastrado com sucesso.");
            return true;
        }catch(SQLException | FileNotFoundException e){
            System.err.println("Erro ao cadastrar. ");
            e.printStackTrace();
            return false;
        }finally{
            con.close();
        }      
    }
    
    public boolean excluir(String nome) throws SQLException{
        Connection con = null;
        String sql = "DELETE FROM LIVRO WHERE nome = ?";
        try{
            con  = conexao.abrirConexao();
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, nome);
            statement.execute();
            statement.close();
            System.err.println("Livro deletado");
            return true;
        }catch(SQLException e){
            e.printStackTrace();
            System.err.println("Erro ao deletrar. ");
            return false;
        }finally{
            con.close();
        }
    }
    
    public boolean alterar(Livro livro) throws SQLException{
        Connection con = null;
        String sql = "UPDATE LIVRO SET nome = ?, autor = ?, anoedicao = ?, notausuario = ?,"
                + " resenha = ?, imagem = ?, datadecadastro = ? WHERE id = ?";
        try{
            File file = livro.getImagemLivro();
            FileInputStream fis = new FileInputStream(file);
            con  = conexao.abrirConexao();
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, livro.getNomeDoLivro());
            statement.setString(2, livro.getAutor());
            statement.setInt(3, livro.getAnoDeEdicao());
            statement.setString(4, livro.getNotaDoUsuario());
            statement.setString(5, livro.getResenha());
            statement.setBinaryStream(6, fis, (int)file.length());
            statement.setTimestamp(7, (Timestamp) livro.getDataDeCadastro());
            statement.setInt(8, livro.getId());
            statement.execute();
            statement.close();
            System.out.println("Altera:" + livro.getNomeDoLivro());
            System.out.println("ATUALIZADO COM SUCESSO.");
            return true;
        }catch(SQLException | FileNotFoundException e){
            System.err.println("Erro ao cadastrar. ");
            e.printStackTrace();
            return false;
        }finally{
            con.close();
        }
    }
    
    public Livro buscarLivroNome(String nome) throws IOException, SQLException{
        Connection con = null;
        String sql = "SELECT * FROM LIVRO WHERE nome like ?";
        FileOutputStream fos = null;
        livro = new Livro();
         try{
            
            con  = conexao.abrirConexao();
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, "%"+nome+"%");
            ResultSet rs = statement.executeQuery();
            
            while(rs.next()){
                livro.setId(rs.getInt("id"));
                livro.setNomeDoLivro(rs.getString("nome"));
                livro.setAutor(rs.getString("autor"));
                livro.setAnoDeEdicao(rs.getInt("anoedicao"));
                livro.setNotaDoUsuario(rs.getString("notausuario"));
                livro.setResenha(rs.getString("resenha"));
                livro.setDataDeCadastro(rs.getDate("datadecadastro"));
                
                File file = new File("imagemLivro" + ".png");
                fos = new FileOutputStream(file);
                Blob blob = rs.getBlob("imagem");
                byte b[] = blob.getBytes(1, (int) blob.length());
                fos.write(b);
                fos.close();
                livro.setImagemLivro(file);
            }
             System.out.println("Nome do livro: "  + livro.getNomeDoLivro());
             statement.close();
             return livro;
        }catch(SQLException e){
            System.err.println("Erro na busca. ");
            return null;
        }finally{
             con.close();
         }
        
    }
    
    public List<Livro> obterLivros() throws IOException, SQLException{
        Connection con = null;
        String sql = "SELECT * FROM LIVRO";
        FileOutputStream fos = null;
        List<Livro> livros = new ArrayList<>();
        try{
            con  = conexao.abrirConexao();
            PreparedStatement statement = con.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            
            while(rs.next()){
                Livro l = new Livro();
                l.setId(rs.getInt("id"));
                l.setNomeDoLivro(rs.getString("nome"));
                l.setAutor(rs.getString("autor"));
                l.setAnoDeEdicao(rs.getInt("anoedicao"));
                l.setNotaDoUsuario(rs.getString("notausuario"));
                l.setResenha(rs.getString("resenha"));
                l.setDataDeCadastro(rs.getDate("datadecadastro"));
                
                File file = new File("imagemLivro" + ".png");
                fos = new FileOutputStream(file);
                Blob blob = rs.getBlob("imagem");
                byte b[] = blob.getBytes(1, (int) blob.length());
                fos.write(b);
                fos.close();
                l.setImagemLivro(file);
                livros.add(l);
            }
             statement.close();
             return livros;
        }catch(SQLException e){
            System.err.println("Erro na busca. ");
            return null;
        }finally{
             con.close();
         }
        
    }
    
    
}
