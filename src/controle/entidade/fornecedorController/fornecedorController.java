package controle.entidade.fornecedorController;

import modelo.dao.fornecedor.FornecedorDAO;
import modelo.entidade.pessoa.fornecedor.Fornecedor;
import modelo.entidade.pessoa.funcionario.Funcionario;
import visao.TelaFornecedor;
import visao.TelaMensagens;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
                // Verifica se o CNPJ já existe para cadastro
                if (fornecedorDAO.cnpjExiste(fornecedor.getCnpj())) {
                    TelaMensagens Tm = new TelaMensagens("Já existe um fornecedor cadastrado com esse CNPJ.", 3);
                    telaFornecedor.limparCampos();
                    return;  // Se o CNPJ já existir, não continua o cadastro
                }

                // Caso o CNPJ não exista, continua o processo de cadastro
                fornecedorDAO.cadastrarFornecedor(fornecedor);
                atualizarTabela();
                telaFornecedor.limparCampos();
                TelaMensagens Tm = new TelaMensagens("Fornecedor Cadastrado com sucesso.", 0);
           

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            TelaMensagens Tm = new TelaMensagens("Alguns campos não foram preenchidos de forma"
            		+ " correta ou não foram preenchidos.", 3);
            
            
        }
    }


    
    
    public void confirmarExclusaoFornecedor(int id) {
        // Exibe o JOptionPane de confirmação
        
    	TelaMensagens Tm = new TelaMensagens("O");
    	

        // Se o usuário clicar em "Sim"
        if (Tm.getResposta()) {
            excluirFornecedor(id); // Chama o método para excluir
        }
    }


 // Método para editar fornecedor
    public void editarFornecedor(Fornecedor fornecedor) {
        // Verifica se os campos obrigatórios foram preenchidos corretamente
        if (validarCampos(fornecedor)) {
            try {
                // Verifica se o CNPJ já existe (exceto para o próprio fornecedor sendo editado)
                if (fornecedorDAO.cnpjExiste(fornecedor.getCnpj())) {
                    TelaMensagens Tm = new TelaMensagens("Já existe um fornecedor cadastrado com esse CNPJ.", 3);
                    return;  // Se o CNPJ já existir, não continua o processo de edição
                }

                // Atualizando o fornecedor no banco de dados
                fornecedorDAO.atualizarFornecedor(fornecedor);

                // Atualizando a tabela na tela
                atualizarTabela();

                // Limpando os campos da interface
                telaFornecedor.limparCampos();

                // Mensagem de sucesso
                TelaMensagens Tm = new TelaMensagens("Fornecedor atualizado com sucesso.", 0);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            // Caso algum campo não tenha sido preenchido corretamente
            TelaMensagens Tm = new TelaMensagens("Alguns campos não foram preenchidos de forma correta ou não foram preenchidos.", 3);
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
            atualizarTabela();
            telaFornecedor.limparCampos();
            
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

    // Método para validar aqui
    private boolean validarCampos(Fornecedor fornecedor) {
       
    	
    	 if (fornecedor.getCep() == 0) { 
    	        return false;  
    	    }
    	
    	if (fornecedor.getRua() == null || fornecedor.getRua().trim().isEmpty() ) {
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
