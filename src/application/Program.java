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
		PreparedStatement st =null;
		try {
			conn = DB.getConnection();
			st = conn.prepareStatement(
					"UPDATE seller "
					+"SET BaseSalary = BaseSalary + ? "
				    +"WHERE "
					+"(DepartmentId = ?)");
			st.setDouble(1, 200);
			st.setInt(2, 2);
			int linhasAfetadas = st.executeUpdate();
			System.out.println("Linhas afetadas: "+linhasAfetadas);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}
		
	}

}
