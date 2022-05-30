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
            String sql = "INSERT INTO tb_clientes (nome,rg,cpf,email,senha,cargo,nivel_acesso,telefone,celular,cep,endereco,numero,complemento,bairro,cidade,estado)"
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
}
