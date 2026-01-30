/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public void cadastrarProduto (ProdutosDTO produto){
        
        
        //conn = new conectaDAO().connectDB();
        
        
    }
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        
        ArrayList<ProdutosDTO> listagem = new ArrayList<>();
        
        String sql = "SELECT * FROM produtos";
        
        try{
            conn = new conectaDAO().connectDB();
            
            if (conn == null){
                JOptionPane.showMessageDialog(null,
                        "Falha na conexão com o banco de dados");
                return listagem;
            }
            prep = conn.prepareStatement(sql);
            resultset = prep.executeQuery();
            
            while (resultset.next()){
                ProdutosDTO produto = new ProdutosDTO();
                
                produto.setId(resultset.getInt("id"));
                produto.setNome(resultset.getString("nome"));
                produto.setValor(resultset.getInt("valor"));
                produto.setStatus(resultset.getString("status"));
                
                listagem.add(produto);
            }
            
        } catch(Exception e){
            JOptionPane.showMessageDialog(null,
                    "Erro ao listar produtos: " + e.getMessage());
        }
        
        return listagem;
    }
    
    public void venderProduto(int idProduto) {
    String sql = "UPDATE produtos SET status = 'Vendido' WHERE id = ?";

    try {
        conn = new conectaDAO().connectDB();
        prep = conn.prepareStatement(sql);
        prep.setInt(1, idProduto);
        prep.executeUpdate();
        prep.close();
        conn.close();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Erro ao vender produto: " + e.getMessage());
    }
}
    public ArrayList<ProdutosDTO> listarProdutosVendidos() {
    ArrayList<ProdutosDTO> lista = new ArrayList<>();
    String sql = "SELECT * FROM produtos WHERE status = 'Vendido'";

    try {
        conn = new conectaDAO().connectDB();
        prep = conn.prepareStatement(sql);
        resultset = prep.executeQuery();

        while (resultset.next()) {
            ProdutosDTO produto = new ProdutosDTO();
            produto.setId(resultset.getInt("id"));
            produto.setNome(resultset.getString("nome"));
            produto.setValor(resultset.getInt("valor"));
            produto.setStatus(resultset.getString("status"));

            lista.add(produto);
        }

        conn.close();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Erro ao listar produtos vendidos: " + e.getMessage());
    }

    return lista;
}   
    
        
}

