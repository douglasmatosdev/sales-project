/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sales.dao;

import com.sales.jdbc.ConnectionFactory;
import com.sales.model.Fornecedor;
import com.sales.services.WebServiceCep;
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
public class FornecedorDAO {

    private Connection con;

    public FornecedorDAO() {
        this.con = new ConnectionFactory().getConnection();
    }

    // Método cadastrar fornecedor
    public void cadastrarFornecedor(Fornecedor obj) {
        try {

            // 1° passo - criar o comando sql
            String sql = "INSERT INTO tb_fornecedores (nome,cnpj,email,telefone,celular,cep,endereco,numero,complemento,bairro,cidade,estado)"
                    + "values (?,?,?,?,?,?,?,?,?,?,?,?)";

            // 2° passo - conectar o banco de dados e organizar o comando sql
            try ( PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setString(1, obj.getNome());
                stmt.setString(2, obj.getCnpj());
                stmt.setString(3, obj.getEmail());
                stmt.setString(4, obj.getTelefone());
                stmt.setString(5, obj.getCelular());
                stmt.setString(6, obj.getCep());
                stmt.setString(7, obj.getEndereco());
                stmt.setInt(8, obj.getNumero());
                stmt.setString(9, obj.getComplemento());
                stmt.setString(10, obj.getBairro());
                stmt.setString(11, obj.getCidade());
                stmt.setString(12, obj.getUf());
                // 3° passo - executar o comando sql
                stmt.execute();
                // Fechando a conexão
                stmt.close();
            }

            JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!");

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro: " + erro);
        }

    }

    // Método excluir fornecedor
    public void excluirFornecedor(Fornecedor obj) {
        try {

            // 1° passo - criar o comando sql
            String sql = "DELETE from tb_fornecedores WHERE id = ?";

            // 2° passo - conectar o banco de dados e organizar o comando sql
            try ( PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setInt(1, obj.getId());

                // 3° passo - executar o comando sql
                stmt.execute();
                // Fechando a conexão
                stmt.close();
            }

            JOptionPane.showMessageDialog(null, "Excluído com sucesso!");

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro: " + erro);
        }

    }

    // Método altera fornecedor
    public void alterarFornecedor(Fornecedor obj) {
        try {

            // 1° passo - criar o comando sql
            String sql = "UPDATE tb_fornecedores SET nome=?,cnpj=?,email=?,telefone=?,celular=?,cep=?,endereco=?,numero=?,complemento=?,bairro=?,cidade=?,estado=? WHERE id = ?";

            // 2° passo - conectar o banco de dados e organizar o comando sql
            try ( PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setString(1, obj.getNome());
                stmt.setString(2, obj.getCnpj());
                stmt.setString(3, obj.getEmail());
                stmt.setString(4, obj.getTelefone());
                stmt.setString(5, obj.getCelular());
                stmt.setString(6, obj.getCep());
                stmt.setString(7, obj.getEndereco());
                stmt.setInt(8, obj.getNumero());
                stmt.setString(9, obj.getComplemento());
                stmt.setString(10, obj.getBairro());
                stmt.setString(11, obj.getCidade());
                stmt.setString(12, obj.getUf());

                stmt.setInt(13, obj.getId());
                // 3° passo - executar o comando sql
                stmt.execute();
                // Fechando a conexão
                stmt.close();
            }

            JOptionPane.showMessageDialog(null, "Alterado com sucesso!");

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro: " + erro);
        }

    }

    // Método que mostra todos os fornecedores
    public List<Fornecedor> listarFornecedores() {

        try {

            // 1° passo - criar a lista
            List<Fornecedor> lista = new ArrayList<>();

            // 2° passo - criar o sql, organizar e executar
            String sql = "SELECT * FROM tb_fornecedores";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Fornecedor obj = new Fornecedor();

                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setCnpj(rs.getString("cnpj"));
                obj.setEmail(rs.getString("email"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setCelular(rs.getString("celular"));
                obj.setCep(rs.getString("cep"));
                obj.setEndereco(rs.getString("endereco"));
                obj.setNumero(rs.getInt("numero"));
                obj.setComplemento(rs.getString("complemento"));
                obj.setBairro(rs.getString("bairro"));
                obj.setCidade(rs.getString("cidade"));
                obj.setUf(rs.getString("estado"));

                lista.add(obj);
            }

            return lista;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e);
            return null;
        }
    }

    // Método que retorna uma lista de fornecedores por nome
    public List<Fornecedor> buscaFornnecedorPorNome(String nome) {

        try {

            // 1° passo - criar a lista
            List<Fornecedor> lista = new ArrayList<>();

            // 2° passo - criar o sql, organizar e executar
            String sql = "SELECT * FROM tb_fornecedores WHERE nome LIKE ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Fornecedor obj = new Fornecedor();

                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setCnpj(rs.getString("cnpj"));
                obj.setEmail(rs.getString("email"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setCelular(rs.getString("celular"));
                obj.setCep(rs.getString("cep"));
                obj.setEndereco(rs.getString("endereco"));
                obj.setNumero(rs.getInt("numero"));
                obj.setComplemento(rs.getString("complemento"));
                obj.setBairro(rs.getString("bairro"));
                obj.setCidade(rs.getString("cidade"));
                obj.setUf(rs.getString("estado"));

                lista.add(obj);
            }

            return lista;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e);
            return null;
        }
    }

    // Método que retorna apenas um objeto Fornecedor buscando pelo nome
    public Fornecedor consultaFornecedorPorNome(String nome) {
        try {
            String sql = "SELECT * FROM tb_fornecedores WHERE nome = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);

            ResultSet rs = stmt.executeQuery();

            Fornecedor obj = new Fornecedor();

            if (rs.next()) {
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setCnpj(rs.getString("cnpj"));
                obj.setEmail(rs.getString("email"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setCelular(rs.getString("celular"));
                obj.setCep(rs.getString("cep"));
                obj.setEndereco(rs.getString("endereco"));
                obj.setNumero(rs.getInt("numero"));
                obj.setComplemento(rs.getString("complemento"));
                obj.setBairro(rs.getString("bairro"));
                obj.setCidade(rs.getString("cidade"));
                obj.setUf(rs.getString("estado"));
            }

            return obj;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Fornecedor não encontrado!\n Error: " + e);
            return null;
        }
    }

    
     // Busca CEP
    public Fornecedor buscaCep(String cep) {

        WebServiceCep webServiceCep = WebServiceCep.searchCep(cep);

        Fornecedor obj = new Fornecedor();

        if (webServiceCep.wasSuccessful()) {
            obj.setEndereco(webServiceCep.getLogradouroFull());
            obj.setCidade(webServiceCep.getCidade());
            obj.setBairro(webServiceCep.getBairro());
            obj.setUf(webServiceCep.getUf());
            return obj;
        } else {
            JOptionPane.showMessageDialog(null, "Erro numero: " + webServiceCep.getResulCode());
            JOptionPane.showMessageDialog(null, "Descrição do erro: " + webServiceCep.getResultText());
            return null;
        }
    }
}
