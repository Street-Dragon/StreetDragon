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

    public void atualizarTabela() {
        try {
            List<Fornecedor> fornecedores = fornecedorDAO.listarFornecedores();
            
            DefaultTableModel model = (DefaultTableModel) telaFornecedor.getTable().getModel();
            model.setRowCount(0); 
            for (Fornecedor fornecedor : fornecedores) {
                model.addRow(new Object[] { fornecedor.getId(), fornecedor.getNome(), fornecedor.getCnpj(), fornecedor.getRua(), fornecedor.getCep() });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //>:C

    private void preencherCampos(Fornecedor fornecedor) {
        telaFornecedor.getTextNome().setText(fornecedor.getNome());
        telaFornecedor.getTxtCnpj().setText(fornecedor.getCnpj());
        telaFornecedor.getTxtRua().setText(fornecedor.getRua());
        telaFornecedor.getTxtCep().setText(String.valueOf(fornecedor.getCep()));
    }
    
    


    public void cadastrarFornecedor(Fornecedor fornecedor) {
        if (validarCampos(fornecedor)) {
            try {
                
                if (fornecedorDAO.cnpjExiste(fornecedor.getCnpj())) {
                    TelaMensagens Tm = new TelaMensagens("Já existe um fornecedor cadastrado com esse CNPJ.", 3);
                    telaFornecedor.limparCampos();
                    return;
                }

                
                fornecedorDAO.cadastrarFornecedor(fornecedor);
                atualizarTabela();  

         
                telaFornecedor.limparCampos();
                TelaMensagens Tm = new TelaMensagens("Fornecedor Cadastrado com sucesso.", 0);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            TelaMensagens Tm = new TelaMensagens("Alguns campos não foram preenchidos corretamente.", 3);
        }
    }

    
    public void confirmarExclusaoFornecedor(int id) {
        TelaMensagens Tm = new TelaMensagens("Tem certeza que deseja excluir este fornecedor?");
        if (Tm.getResposta()) {
            excluirFornecedor(id); 
        }
    }

    
    public void editarFornecedor(Fornecedor fornecedor) {
        if (validarCampos(fornecedor)) {
            try {
                
                boolean cnpjAlterado = !fornecedor.getCnpj().equals(fornecedorDAO.buscarFornecedorPorId(fornecedor.getId()).getCnpj());
                
                if (cnpjAlterado && fornecedorDAO.cnpjExiste(fornecedor.getCnpj())) {
                    TelaMensagens Tm = new TelaMensagens("Já existe um fornecedor cadastrado com esse CNPJ.", 3);
                    return;
                }

                
                fornecedorDAO.atualizarFornecedor(fornecedor);

                
                atualizarTabela();

                
                telaFornecedor.limparCampos();

                
                TelaMensagens Tm = new TelaMensagens("Fornecedor atualizado com sucesso.", 0);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            TelaMensagens Tm = new TelaMensagens("Alguns campos não foram preenchidos corretamente.", 3);
        }
    }

    
    public void excluirFornecedor(int id) {
        if (id <= 0) {
            TelaMensagens Tm = new TelaMensagens("Selecione um fornecedor para excluir", 3);
            return;
        }
        try {
            fornecedorDAO.excluirFornecedor(id);
            
            
            atualizarTabela();
            telaFornecedor.limparCampos();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
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
