package modelo.dao.genericdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import controle.entidade.conexao.ConexaoBD;

public class GenericDAO {
    private Connection conexao;

    protected GenericDAO() {
        this.conexao = ConexaoBD.getConexaoMySQL();
    }

    protected Connection getConnection() {
        return conexao;
    }

    protected void save(String insertSql, Object... parametros) throws SQLException {
        PreparedStatement ps = getConnection().prepareStatement(insertSql);

        for (int i = 0; i < parametros.length; i++) {
            ps.setObject(i + 1, parametros[i]);
        }

        ps.execute();
        ps.close();
        conexao.close();
    }

    protected void update(String updateSql, Object id, Object... parametros) throws SQLException {
        PreparedStatement ps = getConnection().prepareStatement(updateSql);
        for (int i = 0; i < parametros.length; i++) {
            ps.setObject(i + 1, parametros[i]);
        }
        ps.setObject(parametros.length + 1, id);
        ps.execute();
        ps.close();
        conexao.close();
    }

    protected void delete(String deleteSql, Object... parametros) throws SQLException {
        PreparedStatement ps = getConnection().prepareStatement(deleteSql);

        for (int i = 0; i < parametros.length; i++) {
            ps.setObject(i + 1, parametros[i]);
        }

        ps.execute();
        ps.close();
        conexao.close();
    }
}
