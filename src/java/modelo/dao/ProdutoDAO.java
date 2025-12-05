package modelo.dao;

import java.sql.*;
import java.util.*;
import modelo.Produto;
import util.Conexao;
import java.sql.Date;

public class ProdutoDAO {

    public void inserir(Produto produto) {
        String sql = "INSERT INTO produto (nome, descricao, preco, quantidade, categoria, fornecedor, dataValidade, peso, marca) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getDescricao());
            stmt.setDouble(3, produto.getPreco());
            stmt.setInt(4, produto.getQuantidade());
            stmt.setString(5, produto.getCategoria());
            stmt.setString(6, produto.getFornecedor());
            if (produto.getDataValidade() != null) {
                stmt.setDate(7, Date.valueOf(produto.getDataValidade()));
            } else {
                stmt.setNull(7, Types.DATE);
            }
            stmt.setDouble(8, produto.getPeso());
            stmt.setString(9, produto.getMarca());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizar(Produto produto) {
        String sql = "UPDATE produto SET nome = ?, descricao = ?, preco = ?, quantidade = ?, categoria = ?, fornecedor = ?, dataValidade = ?, peso = ?, marca = ? WHERE id = ?";

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getDescricao());
            stmt.setDouble(3, produto.getPreco());
            stmt.setInt(4, produto.getQuantidade());
            stmt.setString(5, produto.getCategoria());
            stmt.setString(6, produto.getFornecedor());
            if (produto.getDataValidade() != null) {
                stmt.setDate(7, Date.valueOf(produto.getDataValidade()));
            } else {
                stmt.setNull(7, Types.DATE);
            }
            stmt.setDouble(8, produto.getPeso());
            stmt.setString(9, produto.getMarca());
            stmt.setInt(10, produto.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluir(Produto produto) {
    String sql = "DELETE FROM produto WHERE id = ?";

    try (Connection conn = Conexao.getConexao();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, produto.getId());
        stmt.executeUpdate();

    } catch (SQLException e) {
        e.printStackTrace();
    }
}


    public Produto buscarPorId(Integer id) {
        if (id == null) {
            return null;
        }
        String sql = "SELECT * FROM produto WHERE id = ?";
        Produto produto = null;

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                produto = new Produto(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("descricao"),
                    rs.getDouble("preco"),
                    rs.getInt("quantidade"),
                    rs.getString("categoria"),
                    rs.getString("fornecedor"),
                    rs.getDate("dataValidade") != null ? rs.getDate("dataValidade").toLocalDate() : null,
                    rs.getDouble("peso"),
                    rs.getString("marca")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return produto;
    }

    public List<Produto> listarTodos() {
        List<Produto> lista = new ArrayList<>();
        String sql = "SELECT * FROM produto";

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Produto produto = new Produto(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("descricao"),
                    rs.getDouble("preco"),
                    rs.getInt("quantidade"),
                    rs.getString("categoria"),
                    rs.getString("fornecedor"),
                    rs.getDate("dataValidade") != null ? rs.getDate("dataValidade").toLocalDate() : null,
                    rs.getDouble("peso"),
                    rs.getString("marca")
                );

                lista.add(produto);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public List<Produto> buscarPorNome(String nome) {
        List<Produto> lista = new ArrayList<>();
        String sql = "SELECT * FROM produto WHERE nome LIKE ?";

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + nome + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Produto produto = new Produto(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("descricao"),
                    rs.getDouble("preco"),
                    rs.getInt("quantidade"),
                    rs.getString("categoria"),
                    rs.getString("fornecedor"),
                    rs.getDate("dataValidade") != null ? rs.getDate("dataValidade").toLocalDate() : null,
                    rs.getDouble("peso"),
                    rs.getString("marca")
                );

                lista.add(produto);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
}
