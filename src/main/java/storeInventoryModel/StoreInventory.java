package storeInventoryModel;

public class StoreInventory {
	
	private String name;
	private int id;
	private String unit;
	private int quantity;
	private String quantityUnit;
	private int amountPerQuantity;
	private int minimumQuantityAlert;
	private int totalAmount;
	
	//Collects newProduct Info from Web
	public StoreInventory(String name,int id,String unit,int quantity,int amountPerQuantity,int minimumQuantityAlert) {
		super();
		this.name=name;
		this.id=id;
		this.unit=unit;
		this.quantity=quantity;
		this.amountPerQuantity=amountPerQuantity;
		this.minimumQuantityAlert=minimumQuantityAlert;
	}
	
	//Collects deletionInfo from Database
	public StoreInventory(int id,String name,String quantityUnit,int totalAmount) {
		super();
		this.id=id;
		this.name=name;
		this.quantityUnit=quantityUnit;
		this.totalAmount=totalAmount;
	}
	
	//Collects sellingInfo, purchasingInfo from Database
	public StoreInventory(int id,String name,int quantity) {
		super();
		this.id=id;
		this.name=name;
		this.quantity=quantity;
	}

	//Collects productInfo from Database
	public StoreInventory(int id,String name, String quantityUnit, int totalAmount, int amountPerQuantity, int minimumQuantityAlert) {
		super();
		this.id=id;
		this.name = name;
		this.quantityUnit = quantityUnit;
		this.totalAmount=totalAmount;
		this.amountPerQuantity = amountPerQuantity;
		this.minimumQuantityAlert = minimumQuantityAlert;
	}
	
	//Collects productsList form Database
	public StoreInventory(int id,String name) {
		super();
		this.id=id;
		this.name=name;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getUnit() {
		return unit;
	}
	
	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public int getAmountPerQuantity() {
		return amountPerQuantity;
	}
	
	public void setAmountPerQuantity(int amountPerQuantity) {
		this.amountPerQuantity = amountPerQuantity;
	}
	
	public int getMinimumQuantityAlert() {
		return minimumQuantityAlert;
	}
	
	public void setMinimumQuantityAlert(int minimumQuantityAlert) {
		this.minimumQuantityAlert = minimumQuantityAlert;
	}
	
}
