package application;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import db.DB;
import db.DbException;

public class Program {

	public static void main(String[] args) {
		Connection conn =null;
		Statement st =null;
		try {
			conn = DB.getConnection();
			conn.setAutoCommit(false);
			st = conn.createStatement();
			int linha1 = st.executeUpdate("UPDATE seller SET BaseSalary =5000 WHERE DepartmentId=1");
			
			System.out.println("D1 "+linha1);
	
			//simulando erro para melhor entendimento sobre transações
			int x=1;
			if(x<2) {
				throw new SQLException("Erro falso");
			}
			//quando há um erro a transação não é completada no banco de dados
			int linha2 = st.executeUpdate("UPDATE seller SET BaseSalary =4500 WHERE DepartmentId=2");
			System.out.println("D2 "+linha2);
			conn.commit();//Se não houver nenhum erro durante a execução do programa a mudança será comitada
		}
		catch(SQLException e) {
		try {
			conn.rollback();
			throw new DbException("Roll back, transação não foi concluída, erro causado por: "+e.getMessage());
		} catch (SQLException e1) {
			throw new DbException("Erro ao tentar voltar a transação, erro causado por: "+e.getMessage());
		}
		}
		finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}
		
	}

}
