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
		PreparedStatement st =null;
		try {
			conn = DB.getConnection();
			st = conn.prepareStatement(
					"DELETE FROM Department "
					+"WHERE "
					+"id = ?"
					);

                st.setInt(1, 4);
			int linhasAfetadas = st.executeUpdate();
			System.out.println("Linhas afetadas: "+linhasAfetadas);
		}
		catch(SQLException e) {
		 throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}
		
	}

}
