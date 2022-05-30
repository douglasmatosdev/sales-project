/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sales.dao;

import com.sales.model.Produto;
import com.sales.jdbc.ConnectionFactory;
import com.sales.model.Fornecedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Douglas Matos da Silva
 */
public class ProdutoDAO {

    private Connection con;

    public ProdutoDAO() {
        this.con = new ConnectionFactory().getConnection();
    }

    // Metodo cadastrar produtos
    public void cadastrar(Produto obj) {
        try {
            // 1° passo - SQL
            String sql = "INSERT INTO tb_produtos (descricao,preco,qtd_estoque,for_id) VALUES (?,?,?,?)";

            // 2° passo - conectar ao banco de dados e organizar o SQL
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, obj.getDescricao());
            stmt.setDouble(2, obj.getPreco());
            stmt.setInt(3, obj.getQtd_estoque());

            stmt.setInt(4, obj.getFornecedor().getId());

            stmt.execute();

            stmt.close();
            
             JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, "Erro: " + e);
        }
    }

    // Método Listar todos clientes
    public List<Produto> listarProdutos() {

        try {

            // 1° passo - criar a lista
            List<Produto> lista = new ArrayList<>();

            // 2° passo - criar o sql, organizar e executar
            String sql = "SELECT p.id,p.descricao,p.preco,p.qtd_estoque,f.nome FROM tb_produtos AS p INNER JOIN tb_fornecedores AS f ON (p.for_id = f.id)";

            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Produto obj = new Produto();
                Fornecedor f = new Fornecedor();

                obj.setId(rs.getInt("p.id"));
                obj.setDescricao(rs.getString("p.descricao"));
                obj.setPreco(rs.getDouble("p.preco"));
                obj.setQtd_estoque(rs.getInt("p.qtd_estoque"));

                f.setNome(rs.getString("f.nome"));
                obj.setFornecedor(f);

                lista.add(obj);
            }

            return lista;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar produtos: " + e);
            return null;
        }
    }

    // Método alterar produto
    public void alterar(Produto obj) {
        try {

            // 1° passo - criar o comando sql
            String sql = "UPDATE tb_produtos SET descricao=?,preco=?,qtd_estoque=?,for_id=? WHERE id = ?";

            // 2° passo - conectar o banco de dados e organizar o comando sql
            try ( PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setString(1, obj.getDescricao());
                stmt.setDouble(2, obj.getPreco());
                stmt.setInt(3, obj.getQtd_estoque());

                stmt.setInt(4, obj.getFornecedor().getId());

                stmt.setInt(5, obj.getId());
                // 3° passo - executar o comando sql
                stmt.execute();
                // Fechando a conexão
                stmt.close();
            }

            JOptionPane.showMessageDialog(null, "Produto alterado com sucesso!");

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro: " + erro);
        }

    }

    // Método excluir produto
    public void excluir(Produto obj) {
        try {

            // 1° passo - criar o comando sql
            String sql = "DELETE from tb_produtos WHERE id = ?";

            // 2° passo - conectar o banco de dados e organizar o comando sql
            try ( PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setInt(1, obj.getId());

                // 3° passo - executar o comando sql
                stmt.execute();
                // Fechando a conexão
                stmt.close();
            }

            JOptionPane.showMessageDialog(null, "Produto excluído com sucesso!");

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro: " + erro);
        }

    }

    // Método listar cliento por nome
    public List<Produto> listarPorNome(String nome) {

        try {

            // 1° passo - criar a lista
            List<Produto> lista = new ArrayList<>();

            // 2° passo - criar o sql, organizar e executar
            String sql = "SELECT p.id,p.descricao,p.preco,p.qtd_estoque,f.nome FROM tb_produtos AS p INNER JOIN tb_fornecedores AS f ON (p.for_id = f.id) WHERE p.descricao LIKE ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Produto obj = new Produto();
                Fornecedor f = new Fornecedor();

                obj.setId(rs.getInt("p.id"));
                obj.setDescricao(rs.getString("p.descricao"));
                obj.setPreco(Double.parseDouble(rs.getString("p.preco")));
                obj.setQtd_estoque(Integer.parseInt(rs.getString("p.qtd_estoque")));

                f.setNome(rs.getString("f.nome"));
                obj.setFornecedor(f);

                lista.add(obj);
            }

            return lista;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e);
            return null;
        }
    }

    // Método listar cliento por nome
    public Produto consultaPorNome(String nome) {

        try {

            String sql = "SELECT p.id,p.descricao,p.preco,p.qtd_estoque,f.nome FROM tb_produtos AS p INNER JOIN tb_fornecedores AS f ON (p.for_id = f.id) WHERE p.descricao = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);

            ResultSet rs = stmt.executeQuery();
            Produto obj = new Produto();
            Fornecedor f = new Fornecedor();

            if (rs.next()) {

                obj.setId(rs.getInt("p.id"));
                obj.setDescricao(rs.getString("p.descricao"));
                obj.setPreco(Double.parseDouble(rs.getString("p.preco")));
                obj.setQtd_estoque(Integer.parseInt(rs.getString("p.qtd_estoque")));

                f.setNome(rs.getString("f.nome"));

                obj.setFornecedor(f);
            }

            return obj;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Produto não encontrado: " + e);
            return null;
        }
    }

}
