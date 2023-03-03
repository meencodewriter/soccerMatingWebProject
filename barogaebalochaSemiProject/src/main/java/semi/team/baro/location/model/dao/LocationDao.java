package semi.team.baro.location.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import semi.team.baro.location.model.vo.Location;

public class LocationDao {

	public ArrayList<Location> selectLocationList(Connection conn, int start, int end) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Location> list = new ArrayList<Location>();
		String query = "SELECT*FROM (SELECT ROWNUM AS RNUM, N. * FROM(SELECT GROUND_NO, GROUND_NAME, GROUND_PRICE, GROUND_LAT, GROUND_LNG, GROUND_CONTENT, FILE_PATH FROM GROUND_TBL ORDER BY 1 DESC)N)WHERE RNUM BETWEEN ? AND ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Location l = new Location();
				l.setGroundNo(rset.getInt("ground_no"));
				l.setGroundName(rset.getString("ground_name"));
				l.setGroundPrice(rset.getInt("ground_price"));
				l.setGroundLat(rset.getString("ground_lat"));
				l.setGroundLng(rset.getString("ground_lng"));
				l.setGroundContent(rset.getString("ground_content"));
				l.setFilePath(rset.getString("file_path"));
				list.add(l);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}

	public int selectLocationCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int totalCount = 0;
		String query = "select count(*) as cnt from ground_tbl";
		
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				totalCount = rset.getInt("cnt");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return totalCount;
	}

	public Location selectOneLocation(Connection conn, int groundNo) {
		PreparedStatement pstmt = null;
		ResultSet rset =  null;
		Location l = null;
		String query = "select * from ground_tbl where ground_no=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, groundNo);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				l = new Location();
				l.setGroundName(rset.getString("ground_name"));
				l.setGroundPrice(rset.getInt("ground_price"));
				l.setGroundLat(rset.getString("ground_lat"));
				l.setGroundLng(rset.getString("ground_lng"));
				l.setGroundContent(rset.getString("ground_content"));
				l.setFilePath(rset.getString("file_path"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return l;
	}

	public int insertLocation(Connection conn, Location l) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into ground_tbl values(ground_seq.nextval,?,?,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, l.getGroundName());
			pstmt.setInt(2, l.getGroundPrice());
			pstmt.setString(3, l.getGroundLat());
			pstmt.setString(4, l.getGroundLng());
			pstmt.setString(5, l.getGroundContent());
			pstmt.setString(6, l.getFilePath());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

}