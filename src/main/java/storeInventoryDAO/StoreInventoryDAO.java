package storeInventoryDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import storeInventoryModel.StoreInventory;

public class StoreInventoryDAO {

	private String jdbcURL="jdbc:mysql://localhost:3306/storeInventoryDB?useSSL=false";
	private String jdbcUserName="root";
	private String jdbcPassword="Jitu@1998";
	
	private final String queryAddProduct="INSERT into Products (name,id,unit,quantity,amountPerQuantity,minimumQuantityAlert) values (?,?,?,?,?,?)";
	private final String queryGetDeletionInfo="SELECT name,unit,quantity,amountPerQuantity from Products where id=?";
	private final String queryDeleteProduct="DELETE from Products where id=?";
	private final String queryGetSellingInfo="SELECT name,quantity from Products where id=?";
	private final String querySellProduct="UPDATE Products SET quantity=?";
	private final String queryGetPurchasingInfo="SELECT name,quantity from Products where id=?";
	private final String queryPurchaseProduct="UPDATE Products SET quantity=?";
	private final String queryFetchProductDetails="SELECT name,unit,quantity,amountPerQuantity,minimumQuantityAlert from Products where id=?";
	private final String queryListAllProducts="SELECT id,name from Products";
	
	//Common function to establish a connection
	protected Connection getConnection() {
		Connection connection=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection=DriverManager.getConnection(jdbcURL,jdbcUserName,jdbcPassword);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	//Add Product
	public void addProduct(StoreInventory newProduct) {
		try(Connection connection=getConnection();
				PreparedStatement preparedStatement=connection.prepareStatement(queryAddProduct)){
			preparedStatement.setString(1, newProduct.getName());
			preparedStatement.setInt(2, newProduct.getId());
			preparedStatement.setString(3, newProduct.getUnit());
			preparedStatement.setInt(4, newProduct.getQuantity());
			preparedStatement.setInt(5, newProduct.getAmountPerQuantity());
			preparedStatement.setInt(6, newProduct.getMinimumQuantityAlert());
			preparedStatement.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
		}			
	}
	
	//Get Deletion Info
	public StoreInventory getDeletionInfo(int id) {
		StoreInventory deletionInfo=null;
		try(Connection connection=getConnection();
				PreparedStatement preparedStatement=connection.prepareStatement(queryGetDeletionInfo)){
			preparedStatement.setInt(1, id);
			ResultSet rs=preparedStatement.executeQuery();
			
			while(rs.next()) {
				String name=rs.getString("name");
				String quantityUnit=rs.getInt("quantity")+" "+rs.getString("unit");
				int totalAmount=rs.getInt("quantity")*rs.getInt("amountPerQuantity");
				deletionInfo=new StoreInventory(id,name,quantityUnit,totalAmount);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return deletionInfo;
	}
	
	//Delete Product
	public void deleteProduct(int id) {
		try(Connection connection=getConnection();
				PreparedStatement preparedStatement=connection.prepareStatement(queryDeleteProduct)){
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//Get Selling Info
	public StoreInventory getSellingInfo(int id) {
		StoreInventory sellingInfo=null;
		try(Connection connection=getConnection();
				PreparedStatement preparedStatement=connection.prepareStatement(queryGetSellingInfo)){
			preparedStatement.setInt(1, id);
			ResultSet rs=preparedStatement.executeQuery();
			
			while(rs.next()) {
				String name=rs.getString("name");
				int quantity=rs.getInt("quantity");
				sellingInfo=new StoreInventory(id,name,quantity);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}	
		return sellingInfo;
	}
	
	//Sell Product
	public void sellProduct(StoreInventory sellingInfo,int sellingQuantity) {
		try(Connection connection=getConnection();
				PreparedStatement preparedStatement=connection.prepareStatement(querySellProduct)){
			preparedStatement.setInt(1, sellingInfo.getQuantity()-sellingQuantity);
			preparedStatement.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
		}		
	}
	
	//Get Purchasing Info
	public StoreInventory getPurchasingInfo(int id) {
		StoreInventory purchasingInfo=null;
		try(Connection connection=getConnection();
				PreparedStatement preparedStatement=connection.prepareStatement(queryGetPurchasingInfo)){
			preparedStatement.setInt(1, id);
			ResultSet rs=preparedStatement.executeQuery();
			
			while(rs.next()) {
				String name=rs.getString("name");
				int quantity=rs.getInt("quantity");
				purchasingInfo=new StoreInventory(id,name,quantity);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return purchasingInfo;
	}
	
	//Purchase Product
	public void purchaseProduct(StoreInventory purchasingProduct,int purchasingQuantity) {
		try(Connection connection=getConnection();
				PreparedStatement preparedStatement=connection.prepareStatement(queryPurchaseProduct)){
			preparedStatement.setInt(1, purchasingProduct.getQuantity()+purchasingQuantity);
			preparedStatement.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
		}		
	}
	
	//Fetch Product Details
	public StoreInventory fetchProductDetails(int id) {
		StoreInventory productInfo=null;
		try(Connection connection=getConnection();
				PreparedStatement preparedStatement=connection.prepareStatement(queryFetchProductDetails)){
			preparedStatement.setInt(1, id);
			ResultSet rs=preparedStatement.executeQuery();
			
			while(rs.next()) {
				String name=rs.getString("name");
				String quantityUnit=rs.getInt("quantity")+" "+rs.getString("unit");
				int totalAmount=rs.getInt("amountPerQuantity")*rs.getInt("amountPerQuantity");
				int amountPerQuantity=rs.getInt("amountPerQuantity");
				int minimumQuantityAlert=rs.getInt("minimumQuantityAlert");
				productInfo=new StoreInventory(id,name,quantityUnit,totalAmount,amountPerQuantity,minimumQuantityAlert);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}		
		return productInfo;
	}
	
	//Get Products List
	public List<StoreInventory> getProductsList() {
		List<StoreInventory> productsList= new ArrayList<>();
		try(Connection connection=getConnection();
				PreparedStatement preparedStatement=connection.prepareStatement(queryListAllProducts)){
			ResultSet rs=preparedStatement.executeQuery();
				
			while(rs.next()) {
				int id=rs.getInt("id");
				String name=rs.getString("name");
				productsList.add(new StoreInventory(id,name));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}		
		return productsList;
	}

}
