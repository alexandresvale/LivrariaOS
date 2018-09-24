/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import javafx.scene.effect.ImageInput;
import javafx.scene.image.WritableImage;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author Alexandre
 */
public class Livro {
    private int id;
    private String autor;
    private String nomeDoLivro;
    //private byte[] imagemDoLivro;
    private File imagemLivro;
    private int anoDeEdicao;
    private String notaDoUsuario;
    private String Resenha;
    private Date dataDeCadastro;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    

    public Livro(String autor, String nomeDoLivro, int anoDeEdicao, String notaDoUsuario, String Resenha, Date dataDeCadastro) {
        this.autor = autor;
        this.nomeDoLivro = nomeDoLivro;
        this.anoDeEdicao = anoDeEdicao;
        this.notaDoUsuario = notaDoUsuario;
        this.Resenha = Resenha;
        this.dataDeCadastro = dataDeCadastro;
    }

    public Livro() {
        
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getNomeDoLivro() {
        return nomeDoLivro;
    }

    public void setNomeDoLivro(String nomeDoLivro) {
        this.nomeDoLivro = nomeDoLivro;
    }

    public int getAnoDeEdicao() {
        return anoDeEdicao;
    }

    public void setAnoDeEdicao(int anoDeEdicao) {
        this.anoDeEdicao = anoDeEdicao;
    }

    public String getNotaDoUsuario() {
        return notaDoUsuario;
    }

    public void setNotaDoUsuario(String notaDoUsuario) {
        this.notaDoUsuario = notaDoUsuario;
    }

    public String getResenha() {
        return Resenha;
    }

    public void setResenha(String Resenha) {
        this.Resenha = Resenha;
    }

    public Date getDataDeCadastro() {
        return dataDeCadastro;
    }

    public void setDataDeCadastro(Date dataDeCadastro) {
        this.dataDeCadastro = dataDeCadastro;
    }

    public File getImagemLivro() {
        return imagemLivro;
    }

    public void setImagemLivro(File imagemLivro) {
        this.imagemLivro = imagemLivro;
    }
    
    
    public void processarImagem(String caminho) throws IOException{
        File imagem = new File(caminho);
        BufferedImage bufferedImage = ImageIO.read(imagem);
        WritableRaster raster = bufferedImage.getRaster();
        DataBufferByte bufferByte = (DataBufferByte) raster.getDataBuffer();
        //this.imagemDoLivro = bufferByte.getData();
        //return imagemDoLivro;
    }
    
    public InputStream setImagem(String caminho) throws IOException{
        File imagem = new File(caminho);
        FileInputStream fs = new FileInputStream(imagem);
        return fs;
        //return imagemDoLivro;
    }
    
    
    
}
