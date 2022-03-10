package member.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import common.JDBCTemplate;
import member.model.dao.ShoppingListDao;
import member.model.vo.Order;
import member.model.vo.OrderShopping;
import member.model.vo.Shopping;
import member.model.vo.ShoppingList;


public class ShoppingListService {
	public ArrayList<ShoppingList> selectShoppingList(int reqPage, int memberNo) {
		Connection conn = JDBCTemplate.getConnection();
		int numPerPage = 5;
		//reqPage = 1 start = 1 end = 5 , reqPage=2 start=6 end=10
		int end = reqPage*numPerPage;
		int start = end - numPerPage + 1;
		ShoppingListDao dao = new ShoppingListDao();
		ArrayList<ShoppingList> list = dao.selectShoppingList(conn,start,end,memberNo);
		JDBCTemplate.close(conn);
		return list;
	}

	public OrderShopping selectShoppingPro(int orderNo) {
		Connection conn = JDBCTemplate.getConnection();	
		ShoppingListDao dao = new ShoppingListDao();
		ArrayList<Shopping> slist = dao.selectShoppingPro(conn,orderNo);
		Order o = dao.selectOrder(conn,orderNo);
		OrderShopping os = new OrderShopping(slist,o);
		JDBCTemplate.close(conn);
		return os;
	}

	public int getOrderCount(int memberNo) {
		Connection conn = JDBCTemplate.getConnection();
		int oc = new ShoppingListDao().getOrderCount(conn,memberNo);
		if(oc>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return oc;
	}

}
