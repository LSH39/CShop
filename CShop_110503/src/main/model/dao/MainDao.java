package main.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import notice.model.vo.Notice;
import product.model.vo.Product;

public class MainDao {

	public ArrayList<Product> seleceBestProduct(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Product> list = new ArrayList<Product>();
		String query = "SELECT * FROM (SELECT * FROM product where product_status=2 ORDER BY DBMS_RANDOM.RANDOM()) WHERE ROWNUM <= 3";
		try {
			pstmt=conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Product p = new Product();
				p.setProductNo(rset.getInt("product_no"));
				p.setProductId(rset.getString("product_id"));
				p.setProductSeller(rset.getInt("product_seller"));
				p.setProductCategory1(rset.getInt("product_category1"));
				p.setProductCategory2(rset.getInt("product_category2"));
				p.setProductName(rset.getString("product_name"));
				p.setProductContent(rset.getString("product_content"));
				p.setProductBrand(rset.getString("product_brand"));
				p.setProductImports(rset.getInt("product_imports"));
				p.setProductStatus(rset.getInt("product_status"));
				p.setProductImage(rset.getString("product_image"));
				p.setProductFile(rset.getString("product_file"));
				list.add(p);
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

	public ArrayList<Product> selectNewListProduct(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Product> list = new ArrayList<Product>();
		String query = "SELECT * FROM (SELECT * FROM product where product_status=2 ORDER BY product_no desc)WHERE ROWNUM <= 4";
		try {
			pstmt=conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Product p = new Product();
				p.setProductNo(rset.getInt("product_no"));
				p.setProductId(rset.getString("product_id"));
				p.setProductSeller(rset.getInt("product_seller"));
				p.setProductCategory1(rset.getInt("product_category1"));
				p.setProductCategory2(rset.getInt("product_category2"));
				p.setProductName(rset.getString("product_name"));
				p.setProductContent(rset.getString("product_content"));
				p.setProductBrand(rset.getString("product_brand"));
				p.setProductImports(rset.getInt("product_imports"));
				p.setProductStatus(rset.getInt("product_status"));
				p.setProductImage(rset.getString("product_image"));
				p.setProductFile(rset.getString("product_file"));
				list.add(p);
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

	public ArrayList<Product> selectSearchProductList(Connection conn, int start, int end, String search) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Product> list = new ArrayList<Product>();
		
		String query = "select * from (select rownum as rnum, p.*from (select * from product where product_name like ? and product_status=2 order by product_no desc)p) where rnum between ? and ?";
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, "%"+search+"%");
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rset=pstmt.executeQuery();
			while(rset.next()) {
				Product p = new Product();
				p.setProductNo(rset.getInt("product_no"));
				p.setProductId(rset.getString("product_id"));
				p.setProductSeller(rset.getInt("product_seller"));
				p.setProductCategory1(rset.getInt("product_category1"));
				p.setProductCategory2(rset.getInt("product_category2"));
				p.setProductName(rset.getString("product_name"));
				p.setProductContent(rset.getString("product_content"));
				p.setProductBrand(rset.getString("product_brand"));
				p.setProductImports(rset.getInt("product_imports"));
				p.setProductStatus(rset.getInt("product_status"));
				p.setProductImage(rset.getString("product_image"));
				p.setProductFile(rset.getString("product_file"));
				list.add(p);
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
	
	public int selectTotalCount(Connection conn, String search) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = "select count(*) as cnt from product where product_name like ?";
		
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, "%"+search+"%");
			rset=pstmt.executeQuery();
			if(rset.next()) {
				result=rset.getInt("cnt");//별칭으로 가져온거
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}


}
