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

public class Program {

	public static void main(String[] args) {
		Connection conn =null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		PreparedStatement st =null;
		try {
			conn = DB.getConnection();
			st = conn.prepareStatement("INSERT INTO seller"
										+"(Name, Email, BirthDate, BaseSalary, DepartmentId)"
										+"VALUES"
										+"(?,?,?,?,?)",
										Statement.RETURN_GENERATED_KEYS);//retorna os ids que acabaram de ser criados
				st.setString(1, "Kelly");
				st.setString(2, "Kelly@g.com");
				st.setDate(3, new java.sql.Date(sdf.parse("18/05/1998").getTime()));
				st.setDouble(4, 2000.00);
				st.setInt(5, 4);
				st.executeUpdate();
				
				
			int linhasAlteradas = st.executeUpdate();
			
			if(linhasAlteradas >0) {
				ResultSet rs = st.getGeneratedKeys();
				while(rs.next()) {
					int id = rs.getInt(1);
					System.out.println("Id igual a "+id);
					System.out.println("Fim! Linhas alteradas: "+linhasAlteradas);
				}
			
			}else {
				System.out.println("Nenhuma Linha alterada");
			}
			
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(ParseException e) {
			e.printStackTrace();
		}
		finally {
			DB.closeStatement(st);;
			DB.closeConnection();
		}
	}

}
