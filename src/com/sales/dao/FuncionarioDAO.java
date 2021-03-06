/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sales.dao;

import com.sales.jdbc.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import com.sales.model.Funcionario;
import com.sales.services.WebServiceCep;
import com.sales.view.FormLogin;
import com.sales.view.FormMenu;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Douglas Matos da Silva
 */
public class FuncionarioDAO {

    final private Connection con;

    public FuncionarioDAO() {
        this.con = new ConnectionFactory().getConnection();
    }

    public void cadastrarFuncionario(Funcionario obj) {
        try {

            // 1° passo - criar o comando sql
            String sql = "INSERT INTO tb_funcionarios (nome,rg,cpf,email,senha,cargo,nivel_acesso,telefone,celular,cep,endereco,numero,complemento,bairro,cidade,estado)"
                    + "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            // 2° passo - conectar o banco de dados e organizar o comando sql
            try ( PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setString(1, obj.getNome());
                stmt.setString(2, obj.getRg());
                stmt.setString(3, obj.getCpf());
                stmt.setString(4, obj.getEmail());

                stmt.setString(5, obj.getSenha());
                stmt.setString(6, obj.getCargo());
                stmt.setString(7, obj.getNivel_acesso());

                stmt.setString(8, obj.getTelefone());
                stmt.setString(9, obj.getCelular());
                stmt.setString(10, obj.getCep());
                stmt.setString(11, obj.getEndereco());
                stmt.setInt(12, obj.getNumero());
                stmt.setString(13, obj.getComplemento());
                stmt.setString(14, obj.getBairro());
                stmt.setString(15, obj.getCidade());
                stmt.setString(16, obj.getUf());
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

    // Método alterar Funcionario
    public void alterarFuncionario(Funcionario obj) {
        try {

            // 1° passo - criar o comando sql
            String sql = "UPDATE tb_funcionarios SET nome=?,rg=?,cpf=?,email=?,senha=?,cargo=?,nivel_acesso=?,telefone=?,celular=?,cep=?,endereco=?,numero=?,complemento=?,bairro=?,cidade=?,estado=? WHERE id = ?";

            // 2° passo - conectar o banco de dados e organizar o comando sql
            try ( PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setString(1, obj.getNome());
                stmt.setString(2, obj.getRg());
                stmt.setString(3, obj.getCpf());
                stmt.setString(4, obj.getEmail());

                stmt.setString(5, obj.getSenha());
                stmt.setString(6, obj.getCargo());
                stmt.setString(7, obj.getNivel_acesso());

                stmt.setString(8, obj.getTelefone());
                stmt.setString(9, obj.getCelular());
                stmt.setString(10, obj.getCep());
                stmt.setString(11, obj.getEndereco());
                stmt.setInt(12, obj.getNumero());
                stmt.setString(13, obj.getComplemento());
                stmt.setString(14, obj.getBairro());
                stmt.setString(15, obj.getCidade());
                stmt.setString(16, obj.getUf());

                stmt.setInt(17, obj.getId());
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

    // Método excluirFuncionario
    public void excluirFuncionario(Funcionario obj) {
        try {

            // 1° passo - criar o comando sql
            String sql = "DELETE from tb_funcionarios WHERE id = ?";

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

    // Método Listar todos clientes
    public List<Funcionario> listarFuncionarios() {

        try {

            // 1° passo - criar a lista
            List<Funcionario> lista = new ArrayList<>();

            // 2° passo - criar o sql, organizar e executar
            String sql = "SELECT * FROM tb_funcionarios";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Funcionario obj = new Funcionario();

                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setRg(rs.getString("rg"));
                obj.setCpf(rs.getString("cpf"));
                obj.setEmail(rs.getString("email"));

                obj.setSenha(rs.getString("senha"));
                obj.setCargo(rs.getString("cargo"));
                obj.setNivel_acesso(rs.getString("nivel_acesso"));

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

    // Método consulta Funcionario por nome
    public Funcionario consultaFuncionarioPorNome(String nome) {
        try {
            String sql = "SELECT * FROM tb_funcionarios WHERE nome = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);

            ResultSet rs = stmt.executeQuery();

            Funcionario obj = new Funcionario();

            if (rs.next()) {
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setRg(rs.getString("rg"));
                obj.setCpf(rs.getString("cpf"));
                obj.setEmail(rs.getString("email"));

                obj.setSenha(rs.getString("senha"));
                obj.setCargo(rs.getString("cargo"));
                obj.setNivel_acesso(rs.getString("nivel_acesso"));

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
            JOptionPane.showMessageDialog(null, "Funcionario não encontrado!\n Error: " + e);
            return null;
        }
    }

    // Método listar Funcionario por nome
    public List<Funcionario> buscaFuncionarioPorNome(String nome) {

        try {

            // 1° passo - criar a lista
            List<Funcionario> lista = new ArrayList<>();

            // 2° passo - criar o sql, organizar e executar
            String sql = "SELECT * FROM tb_funcionarios WHERE nome LIKE ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Funcionario obj = new Funcionario();

                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setRg(rs.getString("rg"));
                obj.setCpf(rs.getString("cpf"));
                obj.setEmail(rs.getString("email"));

                obj.setSenha(rs.getString("senha"));
                obj.setCargo(rs.getString("cargo"));
                obj.setNivel_acesso(rs.getString("nivel_acesso"));

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

    // Busca CEP
    public Funcionario buscaCep(String cep) {

        WebServiceCep webServiceCep = WebServiceCep.searchCep(cep);

        Funcionario obj = new Funcionario();

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

    // Método de login
    public void login(String email, String senha) {
        try {

            // 1° passo - SQL
            String sql = "SELECT * FROM tb_funcionarios WHERE email = ? AND senha = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, senha);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                // Usuário logou
                JOptionPane.showMessageDialog(null, "Seja bem vindo ao sistema Sales");

                // Display menu
                FormMenu menu = new FormMenu();
                menu.usuariologado = rs.getString("nome");
                menu.setVisible(true);

            } else {
                // Dados incorretos
                JOptionPane.showMessageDialog(null, "Dados incorretos");
                new FormLogin().setVisible(true);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e);
        }
    }
}
