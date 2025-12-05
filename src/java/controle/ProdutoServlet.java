package controle;

import modelo.dao.ProdutoDAO;
import modelo.Produto;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/ProdutoServlet")
public class ProdutoServlet extends HttpServlet {

    private ProdutoDAO dao;

    @Override
    public void init() throws ServletException {
        dao = new ProdutoDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String acao = request.getParameter("acao");

        if (acao == null) {
            acao = "listar";
        }

        HttpSession session = request.getSession();

        switch (acao) {
            case "novo" -> 
                request.getRequestDispatcher("produtoForm.jsp").forward(request, response);

            case "editar" -> {
                int idEditar = Integer.parseInt(request.getParameter("id"));
                Produto produtoEditar = dao.buscarPorId(idEditar);
                request.setAttribute("produto", produtoEditar);
                request.getRequestDispatcher("produtoForm.jsp").forward(request, response);
            }

            case "excluir" -> {
    
            int idExcluir = Integer.parseInt(request.getParameter("id"));
            Produto produtoExcluir = new Produto();
            produtoExcluir.setId(idExcluir);

            dao.excluir(produtoExcluir); 
            session.setAttribute("mensagem", "Produto excluído com sucesso!");
            session.setAttribute("tipoMensagem", "sucesso");
            response.sendRedirect("ProdutoServlet");
}


            case "pesquisar" -> {
                String termo = request.getParameter("termo");
                List<Produto> listaProdutos = new ArrayList<>();

                if (termo != null && !termo.trim().isEmpty()) {
                    try {
                        int idPesquisa = Integer.parseInt(termo);
                        Produto produto = dao.buscarPorId(idPesquisa);
                        if (produto != null) {
                            listaProdutos.add(produto);
                        }
                    } catch (NumberFormatException e) {
                        listaProdutos = dao.buscarPorNome(termo);
                    }
                }

                if (listaProdutos.isEmpty()) {
                    session.setAttribute("mensagem", "Nenhum produto encontrado para: " + termo);
                    session.setAttribute("tipoMensagem", "erro");
                    response.sendRedirect("ProdutoServlet");
                } else {
                    request.setAttribute("listaProdutos", listaProdutos);
                    double total = listaProdutos.stream()
                        .mapToDouble(p -> p.getPreco() * p.getQuantidade())
                        .sum();
                    request.setAttribute("totalOrcamento", total);
                    request.getRequestDispatcher("produtoLista.jsp").forward(request, response);
                }
            }

            default -> {
                List<Produto> lista = dao.listarTodos();
                double total = lista.stream()
                        .mapToDouble(p -> p.getPreco() * p.getQuantidade())
                        .sum();
                request.setAttribute("listaProdutos", lista);
                request.setAttribute("totalOrcamento", total);
                request.getRequestDispatcher("produtoLista.jsp").forward(request, response);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String acao = request.getParameter("acao");

        int id = request.getParameter("id") != null && !request.getParameter("id").isEmpty()
                ? Integer.parseInt(request.getParameter("id")) : 0;

        String nome = request.getParameter("nome");
        String descricao = request.getParameter("descricao");
        double preco = Double.parseDouble(request.getParameter("preco"));
        int quantidade = Integer.parseInt(request.getParameter("quantidade"));
        String categoria = request.getParameter("categoria");
        String fornecedor = request.getParameter("fornecedor");

        String dataValidadeStr = request.getParameter("dataValidade");
        LocalDate dataValidade = null;
        if (dataValidadeStr != null && !dataValidadeStr.isEmpty()) {
            dataValidade = LocalDate.parse(dataValidadeStr);
        }

        double peso = Double.parseDouble(request.getParameter("peso"));
        String marca = request.getParameter("marca");

        Produto produto = new Produto(id, nome, descricao, preco, quantidade, categoria, fornecedor, dataValidade, peso, marca);

        HttpSession session = request.getSession();

        switch (acao) {
            case "inserir" -> {
                dao.inserir(produto);
                session.setAttribute("mensagem", "Produto cadastrado com sucesso!");
                session.setAttribute("tipoMensagem", "sucesso");
            }
            case "atualizar" -> {
                dao.atualizar(produto);
                session.setAttribute("mensagem", "Produto atualizado com sucesso!");
                session.setAttribute("tipoMensagem", "sucesso");
            }
        }

        response.sendRedirect("ProdutoServlet");
    }
}
