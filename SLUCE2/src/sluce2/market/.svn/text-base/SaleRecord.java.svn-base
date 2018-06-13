package sluce2.market;

import sluce2.agent.LandOwnerAgent;
import sluce2.landscape.component.Parcel;

public class SaleRecord
{
	private LandOwnerAgent seller;
	private Integer parcelID;
	private double askingPrice;
	private LandOwnerAgent buyer;
	private Double salePrice;
	private boolean sold; 
	
	/**
	 * 
	 */
	public SaleRecord()
	{
		this.seller = null;
		this.parcelID = null;
		this.askingPrice = 0d;
		this.buyer = null;
		this.salePrice = null;
		this.sold = false;
	}
	
	/**
	 * @param seller
	 * @param parcel
	 * @param askingPrice
	 */
	public SaleRecord(LandOwnerAgent seller, int parcelID, double askingPrice)
	{
		this.seller = seller;
		this.parcelID = parcelID;
		this.askingPrice = askingPrice;
		this.buyer = null;
		this.salePrice = null;
		this.sold = false;
	}

	/**
	 * @param thatParcel
	 * @return true if this SaleRecord contains the given parcel, false otherwise.
	 */
	public boolean contains(int parcelID)
	{
		return this.parcelID == parcelID;
	}
	
	/**
	 * 
	 */
	@Override
	public String toString()
	{
		return("seller:" + this.seller.getClass()+"-"+this.seller.getId() + " | parcel-" + parcelID + " asking:$" + this.askingPrice);
	}
	
	
}
