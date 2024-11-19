package controle.entidade.fornecedorController;

import modelo.dao.fornecedor.FornecedorDAO;
import modelo.entidade.pessoa.fornecedor.Fornecedor;
import visao.TelaFornecedor;

import java.sql.SQLException;
import java.util.List;

public class fornecedorController {

    private TelaFornecedor telaFornecedor;
    private FornecedorDAO fornecedorDAO;

    public fornecedorController(TelaFornecedor telaFornecedor) {
        this.telaFornecedor = telaFornecedor;
        this.fornecedorDAO = new FornecedorDAO();
    }

    // Método para cadastrar fornecedor
    public void cadastrarFornecedor(Fornecedor fornecedor) {
        if (validarCampos(fornecedor)) {
            try {
                fornecedorDAO.cadastrarFornecedor(fornecedor);
                atualizarTabela();
                telaFornecedor.limparCampos();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            telaFornecedor.exibirMensagem("Preencha todos os campos obrigatórios.");
        }
    }

    // Método para editar fornecedor
    public void editarFornecedor(Fornecedor fornecedor) {
        if (validarCampos(fornecedor)) {
            try {
                fornecedorDAO.atualizarFornecedor(fornecedor);
                atualizarTabela();
                telaFornecedor.limparCampos();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            telaFornecedor.exibirMensagem("Preencha todos os campos obrigatórios.");
        }
    }

    // Método para excluir fornecedor
    public void excluirFornecedor(int id) {
        if (id <= 0) {
            telaFornecedor.exibirMensagem("Selecione um fornecedor para excluir.");
            return;
        }

        try {
            fornecedorDAO.excluirFornecedor(id);
            atualizarTabela();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para atualizar a tabela na tela
    public void atualizarTabela() {
        try {
            List<Fornecedor> fornecedores = fornecedorDAO.listarFornecedores();
            telaFornecedor.atualizarTabela(fornecedores);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao atualizar tabela");
        }
    }

    // Método para validar os campos antes de cadastrar ou editar
    private boolean validarCampos(Fornecedor fornecedor) {
        // Aqui você pode adicionar suas validações, por exemplo:
        if (fornecedor.getNome() == null || fornecedor.getNome().trim().isEmpty()) {
            return false;  // Nome obrigatório
        }
        if (fornecedor.getCnpj() == null || fornecedor.getCnpj().trim().isEmpty()) {
            return false;  // CNPJ obrigatório
        }
        
        // Você pode adicionar mais validações conforme necessário

        return true;
    }
}
