package controle.entidade.fornecedorController;

import modelo.dao.fornecedor.FornecedorDAO;
import modelo.entidade.pessoa.fornecedor.Fornecedor;
import visao.TelaFornecedor;
import visao.TelaMensagens;
import java.sql.SQLException;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class fornecedorController {

    private TelaFornecedor telaFornecedor;
    private FornecedorDAO fornecedorDAO;

    public fornecedorController(TelaFornecedor telaFornecedor) {
        this.telaFornecedor = telaFornecedor;
        this.fornecedorDAO = new FornecedorDAO();
    }

    // Atualizar a tabela com a lista de fornecedores
    public void atualizarTabela() {
        try {
            // Obtém a lista de fornecedores do banco de dados
            List<Fornecedor> fornecedores = fornecedorDAO.listarFornecedores();
            
            // Acessa a tabela da tela e limpa as linhas antigas
            DefaultTableModel model = (DefaultTableModel) telaFornecedor.getTable().getModel();
            model.setRowCount(0); // Limpa os dados antigos

            // Adiciona as linhas na tabela com os dados dos fornecedores
            for (Fornecedor fornecedor : fornecedores) {
                model.addRow(new Object[] { fornecedor.getId(), fornecedor.getNome(), fornecedor.getCnpj(), fornecedor.getRua(), fornecedor.getCep() });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Configurar o MouseListener para capturar a seleção na tabela
    

    // Método para preencher os campos na tela com os dados do fornecedor
    private void preencherCampos(Fornecedor fornecedor) {
        telaFornecedor.getTextNome().setText(fornecedor.getNome());
        telaFornecedor.getTxtCnpj().setText(fornecedor.getCnpj());
        telaFornecedor.getTxtRua().setText(fornecedor.getRua());
        telaFornecedor.getTxtCep().setText(String.valueOf(fornecedor.getCep()));
    }
    
    


    // Método para cadastrar fornecedor
    public void cadastrarFornecedor(Fornecedor fornecedor) {
        if (validarCampos(fornecedor)) {
            try {
                // Verifica se o CNPJ já existe
                if (fornecedorDAO.cnpjExiste(fornecedor.getCnpj())) {
                    TelaMensagens Tm = new TelaMensagens("Já existe um fornecedor cadastrado com esse CNPJ.", 3);
                    telaFornecedor.limparCampos();
                    return;
                }

                // Cadastra o fornecedor
                fornecedorDAO.cadastrarFornecedor(fornecedor);
                atualizarTabela();  // Atualiza a tabela

                // Limpa os campos e exibe a mensagem
                telaFornecedor.limparCampos();
                TelaMensagens Tm = new TelaMensagens("Fornecedor Cadastrado com sucesso.", 0);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            TelaMensagens Tm = new TelaMensagens("Alguns campos não foram preenchidos corretamente.", 3);
        }
    }

    // Método para confirmar exclusão do fornecedor
    public void confirmarExclusaoFornecedor(int id) {
        TelaMensagens Tm = new TelaMensagens("Tem certeza que deseja excluir este fornecedor?");
        if (Tm.getResposta()) {
            excluirFornecedor(id); // Chama o método para excluir
        }
    }

    // Método para editar fornecedor
    public void editarFornecedor(Fornecedor fornecedor) {
        if (validarCampos(fornecedor)) {
            try {
                // Se o CNPJ foi alterado, verifica se o novo CNPJ já existe
                boolean cnpjAlterado = !fornecedor.getCnpj().equals(fornecedorDAO.buscarFornecedorPorId(fornecedor.getId()).getCnpj());
                
                if (cnpjAlterado && fornecedorDAO.cnpjExiste(fornecedor.getCnpj())) {
                    TelaMensagens Tm = new TelaMensagens("Já existe um fornecedor cadastrado com esse CNPJ.", 3);
                    return;
                }

                // Atualiza o fornecedor no banco de dados
                fornecedorDAO.atualizarFornecedor(fornecedor);

                // Atualiza a tabela
                atualizarTabela();

                // Limpa os campos da interface
                telaFornecedor.limparCampos();

                // Mensagem de sucesso
                TelaMensagens Tm = new TelaMensagens("Fornecedor atualizado com sucesso.", 0);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            TelaMensagens Tm = new TelaMensagens("Alguns campos não foram preenchidos corretamente.", 3);
        }
    }

    // Método para excluir fornecedor
    public void excluirFornecedor(int id) {
        if (id <= 0) {
            TelaMensagens Tm = new TelaMensagens("Selecione um fornecedor para excluir", 3);
            return;
        }
        try {
            fornecedorDAO.excluirFornecedor(id);
            
            // Atualiza a tabela após a exclusão
            atualizarTabela();
            telaFornecedor.limparCampos();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para validar os campos
    private boolean validarCampos(Fornecedor fornecedor) {
        if (fornecedor.getCep() == 0) {
            return false;
        }

        if (fornecedor.getRua() == null || fornecedor.getRua().trim().isEmpty()) {
            return false;
        }

        if (fornecedor.getNome() == null || fornecedor.getNome().trim().isEmpty()) {
            return false;
        }

        if (!fornecedor.getCnpj().matches("[0-9/]+")) {
            return false;
        }

        return true;
    }
    
}
