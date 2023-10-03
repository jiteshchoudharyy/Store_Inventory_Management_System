package storeInventoryWeb;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import storeInventoryDAO.StoreInventoryDAO;
import storeInventoryModel.StoreInventory;

/**
 * Servlet implementation class StoreInventoryServlet
 */
@WebServlet("/StoreInventory")
public class StoreInventoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StoreInventoryDAO storeInventoryDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StoreInventoryServlet() {
    	this.storeInventoryDAO=new StoreInventoryDAO();
    }
    
    /**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=request.getServletPath();
		switch(action) {
		case "/addProduct":
			addProduct(request,response);
			break;
		case "/getDeletionInfo":
			try {
				getDeletionInfo(request,response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		case "/deleteProduct":
			deleteProduct(request,response);
			break;
		case "/getSellingInfo":
			try {
				getSellingInfo(request,response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		case "/sellProduct":
			try {
				sellProduct(request,response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		case "/getPurchasingInfo":
			try {
				getPurchasingInfo(request,response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		case "/purchaseProduct":
			try {
				purchaseProduct(request,response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		case "/fetchProductDetails":
			try {
				fetchProductDetails(request,response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		default:
			try {
				getProductsList(request,response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		}
	}
	
	//Accept data from web,pass it to StoreInventory constructor,call DAO method
	private void addProduct(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		String name=request.getParameter("name");
		int id=Integer.parseInt(request.getParameter("id"));
		String unit=request.getParameter("unit");
		int quantity=Integer.parseInt(request.getParameter("quantity"));
		int amountPerQuantity=Integer.parseInt(request.getParameter("amountPerQuantity"));
		int minimumQuantityAlert=Integer.parseInt(request.getParameter("minimumQuantityAlert"));
		StoreInventory newProduct=new StoreInventory(name,id,unit,quantity,amountPerQuantity,minimumQuantityAlert);
		storeInventoryDAO.addProduct(newProduct);
		try { //Display productsList after adding new product
			getProductsList(request,response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		response.sendRedirect("index.html");
	}
	
	//Accept data from web,pass it to DAO method,receive returned object from DAO method,display data on web
	private void getDeletionInfo(HttpServletRequest request,HttpServletResponse response) throws SQLException,ServletException,IOException{
		int id=Integer.parseInt(request.getParameter("id"));
		StoreInventory deletionInfo=storeInventoryDAO.getDeletionInfo(id);
		RequestDispatcher dispatcher=request.getRequestDispatcher("index.html");
		request.setAttribute("deletionInfo", deletionInfo);
		dispatcher.forward(request, response);
	}
	
	//Accepts data from web,call DAO method
	private void deleteProduct(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		int id=Integer.parseInt(request.getParameter("id"));
		storeInventoryDAO.deleteProduct(id);
		try { //Display productsList after deleting a product
			getProductsList(request,response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		response.sendRedirect("index.html");
	}
	
	//Accept data from web,call DAO method,receive returned object from DAO method,display data on web
	private void getSellingInfo(HttpServletRequest request,HttpServletResponse response) throws SQLException,ServletException,IOException{
		int id=Integer.parseInt(request.getParameter("id"));
		StoreInventory sellingInfo=storeInventoryDAO.getSellingInfo(id);
		RequestDispatcher dispatcher=request.getRequestDispatcher("index.html");
		request.setAttribute("sellingInfo", sellingInfo);
		dispatcher.forward(request, response);
	}
	
	//Accept data from web,call DAO method,receive returned object from DAO method, call another DAO method
	private void sellProduct(HttpServletRequest request,HttpServletResponse response) throws SQLException,ServletException,IOException{
		int id=Integer.parseInt(request.getParameter("id"));
		int sellingQuantity=Integer.parseInt(request.getParameter("sellingQuantity"));
		StoreInventory sellingInfo=storeInventoryDAO.getSellingInfo(id);
		storeInventoryDAO.sellProduct(sellingInfo, sellingQuantity);
		try { //Display productsList after selling a product
			getProductsList(request,response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		response.sendRedirect("index.html");
	}
	
	//Accept data from web,call DAO method,receive returned object from DAO method,display data on web
	private void getPurchasingInfo(HttpServletRequest request,HttpServletResponse response) throws SQLException,ServletException,IOException{
		int id=Integer.parseInt(request.getParameter("id"));
		StoreInventory purchasingInfo=storeInventoryDAO.getPurchasingInfo(id);
		RequestDispatcher dispatcher=request.getRequestDispatcher("index.html");
		request.setAttribute("purchasingInfo", purchasingInfo);
		dispatcher.forward(request, response);
	}
	
	//Accept data from web,call DAO method,receive returned object from DAO method, call another DAO method
	private void purchaseProduct(HttpServletRequest request,HttpServletResponse response) throws SQLException,ServletException,IOException{
		int id=Integer.parseInt(request.getParameter("id"));
		int purchasingQuantity=Integer.parseInt(request.getParameter("purchasingQuantity"));
		StoreInventory purchasingInfo=storeInventoryDAO.getPurchasingInfo(id);
		storeInventoryDAO.purchaseProduct(purchasingInfo, purchasingQuantity);
		try { //Display productsList after purchasing a product
			getProductsList(request,response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		response.sendRedirect("index.html");
	}
	
	//Accept data from web,call DAO method,display data on web
	private void fetchProductDetails(HttpServletRequest request,HttpServletResponse response) throws SQLException,ServletException,IOException{
		int id=Integer.parseInt(request.getParameter("id"));
		StoreInventory productInfo=storeInventoryDAO.fetchProductDetails(id);
		RequestDispatcher dispatcher=request.getRequestDispatcher("index.html");
		request.setAttribute("productInfo", productInfo);
		dispatcher.forward(request, response);
	}
	
	//Accept data from database,display data on web
	private void getProductsList(HttpServletRequest request, HttpServletResponse response) throws SQLException,ServletException, IOException {
		List<StoreInventory> productsList = storeInventoryDAO.getProductsList();
		request.setAttribute("productsList", productsList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.html");
		dispatcher.forward(request, response);
	}

}
