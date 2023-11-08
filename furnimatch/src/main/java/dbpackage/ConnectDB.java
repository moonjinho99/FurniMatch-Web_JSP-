package dbpackage;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.*;

public class ConnectDB {
	
	private static ConnectDB instance = new ConnectDB();

	public static ConnectDB getInstance() {
		return instance;
	}

	public ConnectDB() {

	}

	private String jdbcUrl = "jdbc:mysql://localhost:3306/furnimatch"; 
	private String dbId = "root"; 
	private String dbPw = "1234"; 
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private PreparedStatement pstmt2 = null;
	private Statement stmt = null;
	private Statement stmt2 = null;
	private ResultSet rs = null;
	private String sql = "";
	private String sql2 = "";
	String returns = "";
	String returns2 = "";
	String res = "";
	String code="";

	// 데이터베이스와 통신하기 위한 코드가 들어있는 메서드
	public String joindb(String member_id, String member_pwd,String member_name,String member_phonenum) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(jdbcUrl, dbId, dbPw);
			sql = "insert into member_table(member_id,member_pw,member_name,member_phonenum)values(?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member_id);
			pstmt.setString(2, member_pwd);
			pstmt.setString(3, member_name);
			pstmt.setString(4, member_phonenum);
			int row = pstmt.executeUpdate();

			if(row >0)
			{
				returns = "joinok";
				System.out.println("회원가입 성공");
			}
			else {
				returns = "joinno";
				System.out.println("회원가입 실패");

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {if (pstmt != null)try {pstmt.close();} catch (SQLException ex) {}
			if (conn != null)try {conn.close();} catch (SQLException ex) {}
			if (rs != null)try {rs.close();} catch (SQLException ex) {}
		}
		return returns;
	}
	
	
	public String checkId(String id)
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(jdbcUrl, dbId, dbPw);
			sql = "select member_id from member_table where member_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				if (rs.getString("member_id").equals(id)) { // 이미 아이디가 있는 경우
					returns = "id";
				} 
			} else { // 입력한 아이디가 없는 경우
				returns = "ok";
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {if (pstmt != null)try {pstmt.close();} catch (SQLException ex) {}
			if (conn != null)try {conn.close();} catch (SQLException ex) {}
			if (pstmt2 != null)try {pstmt2.close();} catch (SQLException ex) {}
			if (rs != null)try {rs.close();} catch (SQLException ex) {}
		}
		return returns;
	}
	
	
	

	public String logindb(String id, String pwd) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(jdbcUrl, dbId, dbPw);
			sql = "select member_id,member_pw from member_table where member_id=? and member_pw=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				if (rs.getString("member_id").equals(id) && rs.getString("member_pw").equals(pwd)) {
					returns2 = "success";// 로그인 가능
				} else {
					returns2 = "fail"; // 로그인 실패
				}
			} else {
				returns2 = "noId"; // 아이디 또는 비밀번호 존재 X
			}

		} catch (Exception e) {
			returns2 = "오류";
		} finally {if (rs != null)try {rs.close();} catch (SQLException ex) {}
			if (pstmt != null)try {pstmt.close();} catch (SQLException ex) {}
			if (conn != null)try {conn.close();} catch (SQLException ex) {}
		}
		System.out.println(returns2);
		return returns2;
	}
	
	public String findNamedb(String id)
	{
		String name = "";
		String img = "";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(jdbcUrl, dbId, dbPw);
			stmt2 = conn.createStatement();
			sql = "select member_name,member_profileimg from member_table where member_id = '"+id+"';";
			rs = stmt2.executeQuery(sql);
			while(rs.next())
			{
				name= rs.getString("member_name");
				img = rs.getString("member_profileimg");
			}
		}catch(Exception e) {name = "오류";}
		finally {
			try {
				conn.close();
				stmt2.close();
				rs.close();
			}catch(Exception e) {}
		}
		
		System.out.println(name+","+img);
		
		return name+"#"+img;
		
	}
	
	
	public String insertProduct(String img1, String img2,String img3,String img4,String sellerName,String title,String address,String price,String width,String length,String height,String content,String category,String sellerId)
	{
		
		code = title.substring(0,1)+address.substring(0,1)+price.substring(0,1);
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(jdbcUrl, dbId, dbPw);
			sql = "insert into product_information_table(product_seller_name,product_title,"
					+ "product_address,product_price,product_width,product_length,product_height,"
					+ "product_content,product_category,product_code,"
					+ "product_img1,product_img2,product_img3,product_img4,seller_id)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sellerName);
			pstmt.setString(2, title);
			pstmt.setString(3, address);
			pstmt.setString(4, price);
			pstmt.setString(5, width);
			pstmt.setString(6, length);
			pstmt.setString(7, height);
			pstmt.setString(8, content);
			pstmt.setString(9, category);
			pstmt.setString(10, code);
			pstmt.setString(11, img1);
			pstmt.setString(12, img2);
			pstmt.setString(13, img3);
			pstmt.setString(14, img4);
			pstmt.setString(15, sellerId);
			int row = pstmt.executeUpdate();

			if(row >0)
			{
				returns = "ok";
				System.out.println("물품 정보입력 성공");
			}
			else {
				returns = "no";
				System.out.println("물품 정보입력 실패");

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {if (pstmt != null)try {pstmt.close();} catch (SQLException ex) {}
			if (conn != null)try {conn.close();} catch (SQLException ex) {}
			if (rs != null)try {rs.close();} catch (SQLException ex) {}
		}
		return returns;
	}
	

	public String outputMainList()
	{
		String list_res="";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(jdbcUrl, dbId, dbPw);
			stmt2 = conn.createStatement();
			sql = "select product_title,product_address,product_price,product_img1,product_category from product_information_table";
			rs = stmt2.executeQuery(sql);
			while(rs.next())
			{
				String title = rs.getString("product_title");
				String address = rs.getString("product_address");
				String product_price = rs.getString("product_price");
				String product_img1 = rs.getString("product_img1");
				String product_category = rs.getString("product_category");
				list_res += (title+"#"+address+"#"+product_price+"#"+product_img1+"#"+product_category+"#");
			}
		}catch(Exception e) {}
		finally {
			try {
				conn.close();
				stmt2.close();
				rs.close();
			}catch(Exception e) {}
		}
		
		return list_res;
		
	}
	
	
	public String productInf(String code)
	{
		String inf_res="";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(jdbcUrl, dbId, dbPw);
			stmt2 = conn.createStatement();
			sql = "select product_seller_name,product_address,product_title,product_width,product_length,"
					+ "product_height,product_content,product_price,product_img1,product_img2,product_img3,product_img4,product_category,seller_id"
					+ " from product_information_table where product_code = '"+code+"'";
			rs = stmt2.executeQuery(sql);
			while(rs.next())
			{
				String seller_name = rs.getString("product_seller_name");
				String width = rs.getString("product_width");
				String length = rs.getString("product_length");
				String height = rs.getString("product_height");
				String content = rs.getString("product_content");
				String title = rs.getString("product_title");
				String address = rs.getString("product_address");
				String product_price = rs.getString("product_price");
				String product_img1 = rs.getString("product_img1");
				String product_img2 = rs.getString("product_img2");
				String product_img3 = rs.getString("product_img3");
				String product_img4 = rs.getString("product_img4");
				String product_category = rs.getString("product_category");
				String seller_id = rs.getString("seller_id");
				inf_res += (seller_name+"#"+address+"#"+title+"#"+width+"#"+length+"#"+height+"#"+content+"#"+product_price+"#"
						+product_img1+"#"+product_img2+"#"+product_img3+"#"+product_img4+"#"+product_category+"#"+seller_id);
			}
		}catch(Exception e) {}
		finally {
			try {
				conn.close();
				stmt2.close();
				rs.close();
			}catch(Exception e) {}
		}
		
		return inf_res;
	}
	
	public String productInfProfileImg(String id)
	{
		String img="";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(jdbcUrl, dbId, dbPw);
			
			sql = "select member_profileimg from member_table where member_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				img = rs.getString("member_profileimg");
				
			}
		}catch(Exception e) {}
		finally {
			try {
				conn.close();
				pstmt.close();
				rs.close();
			}catch(Exception e) {}
		}
		
		return img;
	}
	
	
	public String insertFavorite(String favorite_code, String id)
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(jdbcUrl, dbId, dbPw);
			sql = "insert into favorite_list_table values(?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, favorite_code);
			pstmt.setString(2, id);
			int row = pstmt.executeUpdate();

			if(row >0)
			{
				returns = "ok";
				System.out.println("관심 목록 삽입 성공");
			}
			else {
				returns = "no";
				System.out.println("관심 목록 삽입 실패");

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {if (pstmt != null)try {pstmt.close();} catch (SQLException ex) {}
			if (conn != null)try {conn.close();} catch (SQLException ex) {}
			if (rs != null)try {rs.close();} catch (SQLException ex) {}
		}
		return returns;
	}
	
	public String findFavoriteCode(String id,String code)
	{
	
		String res="no";
		String findcode="";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(jdbcUrl, dbId, dbPw);
			stmt2 = conn.createStatement();
			sql = "select favorite_code from favorite_list_table where member_id = '"+id+"' and favorite_code = '"+code+"';";
			rs = stmt2.executeQuery(sql);
			while(rs.next())
			{
				findcode =rs.getString("favorite_code");
				
				if(findcode.equals(code))
					res="ok";
			}
		}catch(Exception e) {res = "오류";}
		finally {
			try {
				conn.close();
				stmt2.close();
				rs.close();
			}catch(Exception e) {}
		}
		
		System.out.println(res);
		
		return res;
	}
	
	
	public String findFavorite(String id)
	{
	
		String res="";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(jdbcUrl, dbId, dbPw);
			stmt2 = conn.createStatement();
			sql = "select product_title,product_address,product_price,product_img1 "
					+ "from favorite_list_table f join product_information_table p "
					+ "on f.favorite_code = p.product_code "
					+ "where member_id = '"+id+"'";
			
			rs = stmt2.executeQuery(sql);
			while(rs.next())
			{
				String title = rs.getString("product_title");
				String addr = rs.getString("product_address");
				String price = rs.getString("product_price");
				String img = rs.getString("product_img1");
				res += title+"#"+addr+"#"+price+"#"+img+"#";
			}
		}catch(Exception e) {
			
			System.out.println(e.getMessage());
		}
		finally {
			try {
				conn.close();
				stmt2.close();
				rs.close();
			}catch(Exception e) {}
		}
		
		System.out.println(res);
		
		return res;
	}
	
	public String deleteFavorite(String favorite_code)
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(jdbcUrl, dbId, dbPw);
			sql = "delete from favorite_list_table where favorite_code = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, favorite_code);
			int row = pstmt.executeUpdate();

			if(row >0)
			{
				returns = "ok";
				System.out.println("관심 목록 삭제 성공");
			}
			else {
				returns = "no";
				System.out.println("관심 목록 삭제 실패");

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {if (pstmt != null)try {pstmt.close();} catch (SQLException ex) {}
			if (conn != null)try {conn.close();} catch (SQLException ex) {}
			if (rs != null)try {rs.close();} catch (SQLException ex) {}
		}
		return returns;
	}
	
	public void insertSellProduct(String id, String code)
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(jdbcUrl, dbId, dbPw);
			sql = "insert into sell_list_table values(?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, code);
			pstmt.setString(2, id);
			int row = pstmt.executeUpdate();
			if(row >0)
			{
				System.out.println("판매 내역 등록 성공");
			}
			else {
				System.out.println("판매 내역 삭제 실패");

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {if (pstmt != null)try {pstmt.close();} catch (SQLException ex) {}
			if (conn != null)try {conn.close();} catch (SQLException ex) {}
			if (rs != null)try {rs.close();} catch (SQLException ex) {}
		}
		
	}
	
	public String findSellItems(String id)
	{
		String res="";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(jdbcUrl, dbId, dbPw);
			stmt2 = conn.createStatement();
			sql = "select product_title,product_address,product_price,product_img1 "
					+ "from sell_list_table s join product_information_table p "
					+ "on s.sell_code = p.product_code "
					+ "where member_id = '"+id+"'";
			
			rs = stmt2.executeQuery(sql);
			while(rs.next())
			{
				String title = rs.getString("product_title");
				String addr = rs.getString("product_address");
				String price = rs.getString("product_price");
				String img = rs.getString("product_img1");
				res += title+"#"+addr+"#"+price+"#"+img+"#";
			}
		}catch(Exception e) {
			
			System.out.println(e.getMessage());
		}
		finally {
			try {
				conn.close();
				stmt2.close();
				rs.close();
			}catch(Exception e) {}
		}
		
		System.out.println(res);
		
		return res;
	}
	
	public String updateUser(String value,String id,String type)
	{
		String res="";
		String update="";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(jdbcUrl, dbId, dbPw);
			
			if(type.equals("username"))
			{
				sql = "update member_table set member_name = ? where member_id = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, value);
				pstmt.setString(2, id);
				update="사용자명";
			}
			else if(type.equals("phonenum"))
			{
				sql = "update member_table set member_phonenum = ? where member_id = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, value);
				pstmt.setString(2, id);
				update="전화번호";
			}
			else if(type.equals("password"))
			{
				sql = "update member_table set member_pw = ? where member_id = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, value);
				pstmt.setString(2, id);
				update="패스워드";
			}
			
			int row = pstmt.executeUpdate();
			if(row >0)
			{
				System.out.println(update+" 변경 성공");
				res="ok";
			}
			else {
				System.out.println("변경 실패");
				res="no";
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {if (pstmt != null)try {pstmt.close();} catch (SQLException ex) {}
			if (conn != null)try {conn.close();} catch (SQLException ex) {}
			if (rs != null)try {rs.close();} catch (SQLException ex) {}
		}
		
		return res;
	}
	
	public String findUserInf(String find_id)
	{
		String res="";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(jdbcUrl, dbId, dbPw);
			stmt2 = conn.createStatement();
			sql = "select member_name, member_id,member_phonenum,member_pw from member_table where member_id='"+find_id+"'";
			rs = stmt2.executeQuery(sql);
			while(rs.next())
			{
				String name = rs.getString("member_name");
				String id = rs.getString("member_id");
				String phonenum = rs.getString("member_phonenum");
				String pw = rs.getString("member_pw");
				res += name+"#"+id+"#"+phonenum+"#"+pw+"#";
			}
		}catch(Exception e) {
			
			System.out.println(e.getMessage());
		}
		finally {
			try {
				conn.close();
				stmt2.close();
				rs.close();
			}catch(Exception e) {}
		}
		
		System.out.println(res);
		
		return res;
	}
	
	public String getAR(String code) {
		String ar = "";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(jdbcUrl, dbId, dbPw);
			stmt2 = conn.createStatement();
			sql = "select product_ar from product_information_table where product_code = '"+code+"';";
			rs = stmt2.executeQuery(sql);
			while(rs.next())
			{
				ar= rs.getString("product_ar");
			}
		}catch(Exception e) {ar = "오류";}
		finally {
			try {
				conn.close();
				stmt2.close();
				rs.close();
			}catch(Exception e) {}
		}
		
		System.out.println(ar);
		
		return ar;
	}
	
	
	public void DeleteSellProduct(String id, String code)
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(jdbcUrl, dbId, dbPw);
			sql = "delete from product_information_table where seller_id=? and product_code=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, code);
			int row = pstmt.executeUpdate();
			if(row >0)
			{
				System.out.println("판매 내역 삭제 성공");
			}
			else {
				System.out.println("판매 내역 삭제 실패");
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {if (pstmt != null)try {pstmt.close();} catch (SQLException ex) {}
			if (conn != null)try {conn.close();} catch (SQLException ex) {}
			if (rs != null)try {rs.close();} catch (SQLException ex) {}
		}
		
	}
	
	
	public String UpdateProfileImg(String img,String id)
	{
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(jdbcUrl, dbId, dbPw);
			sql = "update member_table set member_profileimg = ? where member_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, img);
			pstmt.setString(2, id);
			int row = pstmt.executeUpdate();

			if(row >0)
			{
				returns = "ok";
				System.out.println("프로필 이미지 변경 성공");
			}
			else {
				returns = "no";
				System.out.println("프로필 이미지 변경 실패");

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {if (pstmt != null)try {pstmt.close();} catch (SQLException ex) {}
			if (conn != null)try {conn.close();} catch (SQLException ex) {}
			if (rs != null)try {rs.close();} catch (SQLException ex) {}
		}
		return returns;
	}
	
	public String outputARList()
	{
		String list_res="";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(jdbcUrl, dbId, dbPw);
			stmt2 = conn.createStatement();
			sql = "select product_title,product_address,product_price,product_img1 from product_information_table where product_ar is not null";
			rs = stmt2.executeQuery(sql);
			while(rs.next())
			{
				String title = rs.getString("product_title");
				String address = rs.getString("product_address");
				String product_price = rs.getString("product_price");
				String product_img1 = rs.getString("product_img1");
				list_res += (title+"#"+address+"#"+product_price+"#"+product_img1+"#");
			}
		}catch(Exception e) {}
		finally {
			try {
				conn.close();
				stmt2.close();
				rs.close();
			}catch(Exception e) {}
		}
		
		return list_res;
		
	}
	
	
}