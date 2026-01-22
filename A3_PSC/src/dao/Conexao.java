/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author mikio
 */
public class Conexao {
    
    Connection conexao = null;
    
    public Connection getConnection() throws SQLException{
        
        String url = "jdbc:MySql://localhost:3306/jogo_db";
        String user = "root";
        String password = "Aluno@2025";
        
        conexao = DriverManager.getConnection(url, user, password);
        return conexao;  
    }
    
    
}
