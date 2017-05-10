import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoImple implements DAO{
	
	@Override
	public List<Guitar> search(String type) {
		List<Guitar> listguitar = new ArrayList<Guitar>();
		String sql = "select * from Guitar where type=?";
		Connection Conn = DBUtil.open();
		try {
			PreparedStatement pstmt = Conn.prepareStatement(sql);
			pstmt.setString(1,type);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()){
				Guitar guitar=new Guitar();
				guitar.setID(rs.getString(1));
				guitar.setPrice(rs.getString(2));
				guitar.setBuilder(rs.getString(3));
				guitar.setType(type);
				guitar.setBackWood(rs.getString(5));
				guitar.setTopWood(rs.getString(6));
				listguitar.add(guitar);
			}
			return listguitar;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(Conn);
		}
	return null;
}


@Override
public List<Guitar> guitars() {
	Connection Conn = DBUtil.open();
	String sql = "select * from Guitar";
	List<Guitar> inventory = new ArrayList<Guitar>();
	
	try {
		PreparedStatement pstmt = Conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()){
			Guitar guitar = new Guitar();
			guitar.setID(rs.getString(1));
			guitar.setPrice(rs.getString(2));
			guitar.setBuilder(rs.getString(3));
			guitar.setType(rs.getString(4));
			guitar.setBackWood(rs.getString(5));
			guitar.setTopWood(rs.getString(6));
			
			inventory.add(guitar);
		}
		return inventory;
	} catch (SQLException e) {
		e.printStackTrace();
	}finally{
		DBUtil.close(Conn);
	}
	return null;
}



}
