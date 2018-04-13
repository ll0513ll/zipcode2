package kr.co.bit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import kr.co.bit.database.ConnectionManager;
import kr.co.bit.vo.MemberVO;

public class MemberDAO {
	
	
	public MemberVO select(String id) {
		MemberVO vo = null;
		// 鈺곌퀬�돳
		ConnectionManager mgr = new ConnectionManager();
		Connection con = mgr.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select * from Member_tbl where user_id = ?";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, id);
			rs = stmt.executeQuery();
			while(rs.next()) {
				vo = new MemberVO();
				vo.setId(rs.getString(1));
				vo.setPw(rs.getString(2));
				vo.setName(rs.getString(3));
				vo.setEmail(rs.getString(4));
				vo.setZipcode(rs.getString(5));
				vo.setAddr1(rs.getString(6));
				vo.setAddr2(rs.getString(7));
				vo.setTool(rs.getString(8));
				String temp = rs.getString(9); //langs
				// 獄쏄퀣肉닸에占� 癰귨옙占쎌넎占쎈릭占쎈뮉 �굜遺얜굡 占쎌삂占쎄쉐
				
				String[] langs = temp.split("-");
				String[] vals = {"","","",""};
				for(String index : langs){
					int idx = Integer.parseInt(index);
					vals[idx] = index;
				}
				
				vo.setLangs(vals);
				vo.setProject(rs.getString(10));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(vo==null) {
			vo = new MemberVO();
			vo.setId(id);
		}
		mgr.connectClose(con, stmt, rs);
		return vo;
	}
	
	public boolean insert(MemberVO vo) {
		boolean flag = false;
		ConnectionManager mgr = new ConnectionManager();
		Connection con = mgr.getConnection();
		String sql = "insert into member_tbl values (?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, vo.getId());
			stmt.setString(2, vo.getPw());
			stmt.setString(3, vo.getName());
			stmt.setString(4, vo.getEmail());
			stmt.setString(5, vo.getZipcode());
			stmt.setString(6, vo.getAddr1());
			stmt.setString(7, vo.getAddr2());
			stmt.setString(8, vo.getTool());
			String[] temp = vo.getLangs();
			StringBuffer sb = new StringBuffer(temp[0]);
			for(int i=1;i<temp.length;i++) {
				// - �뤃�됲뀋占쎌쁽嚥∽옙 占쎈퉸占쎄퐣 �뤃�뗭겱
				sb.append("-"+temp[i]);
			}
			stmt.setString(9, sb.toString());
			stmt.setString(10, vo.getProject());
			int affectedCount = stmt.executeUpdate();
			if(affectedCount>0) {
				flag = true;
				System.out.println("占쎄땜占쎌뿯占쎌끏�뙴占�");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			mgr.connectClose(con, stmt, null);
		}
		return flag;
	}
	
	public ArrayList<MemberVO> selectAll(){
		ArrayList<MemberVO> list = null;
		//占쎈�믭옙�뵠�뇡占� 占쎌젔占쎈꺗 �굜遺얜굡
		//Departments占쎈퓠 占쎌젔占쎈꺗占쎈퉸占쎄퐣 筌뤴뫀諭� 占쎌쁽�뙴占� 揶쏉옙占쎌죬占쏙옙占쎄퐣 �굜�꼷�꼧占쎈퓠 �빊�뮆�젾
		ConnectionManager mgr = new ConnectionManager();
		Connection con = mgr.getConnection();
		Statement stmt;
		try {
			stmt = con.createStatement();
			String sql = "select * from member_tbl";
			ResultSet rs = stmt.executeQuery(sql);
			list = new ArrayList<MemberVO>();
			MemberVO dao = null;
			while(rs.next()){
				dao = new MemberVO();
				dao.setId(rs.getString(1));
				dao.setPw(rs.getString(2));
				dao.setName(rs.getString(3));
				dao.setEmail(rs.getString(4));
				dao.setZipcode(rs.getString(5));
				dao.setAddr1(rs.getString(6));
				dao.setAddr2(rs.getString(7));
				dao.setTool(rs.getString(8));
				String temp = rs.getString(9); //langs
				
				String[] langs = temp.split("-");
				String[] vals = {"","","",""};
				for(String index : langs){
					int idx = Integer.parseInt(index);
					vals[idx] = index;
				}
				
				dao.setLangs(vals);
				dao.setProject(rs.getString(10));
				list.add(dao);
			}
			mgr.connectClose(con, stmt, rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		return list;
	}
}
