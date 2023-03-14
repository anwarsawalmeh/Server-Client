
import java.io.*;
import java.net.*;
import java.util.*;

// Auction Protocol Responsible for dealing with information
public class auctionProtocol {
	
	// type Wrapper allows for us to store all info in a HashMap
	public class typeWrap {
		private double bid;
		private String ipAddress;
	
		public typeWrap(double bid, String ipAddress) {

			this.bid = bid;
			this.ipAddress = ipAddress;
		}

		public double getBid() {
			return bid;
		}
		public String getIpAddress() {
			return ipAddress;
		}
		public void setBid(double bid) {
			this.bid = bid;
		}
		public void setIpAddress(String ipAddress) {
			this.ipAddress = ipAddress;
		}
		
	}
	
	// Create the Array List with a Wrapper Object
    private HashMap<String, typeWrap> sp = new HashMap<String, typeWrap>();
	
    public String show(String in){
        return in;
    }

	public String addItem(String item, double bid, String ipAddress){
		// create an instance of the wrap
		typeWrap bid_ip = null;
		if(bid == 0.00){
			bid_ip = new typeWrap(bid, "<no bids>");
			sp.put(item, bid_ip);
			return "Success.";

		}else{
			bid_ip = new typeWrap(bid, ipAddress);
			sp.put(item, bid_ip);
			return "Success.";
		}
		
	}

	public String displayItems(){
		if(sp.isEmpty()){
			return "There are currently no items in this auction.";
		}
		StringBuilder sb = new StringBuilder();
		for(String items: sp.keySet()){
			
			typeWrap wrapperItems = sp.get(items);
			System.out.print(items+ " "+wrapperItems.getBid()+" "+wrapperItems.getIpAddress()+" \n");
			sb.append(items);
			sb.append(" : ");
			sb.append(Double.toString(wrapperItems.getBid()));
			sb.append(" : ");
			sb.append(wrapperItems.getIpAddress());

		}

		return sb.toString();
	}

	public String placeBid(String item, double bid, String ipAddress){
		// iterate over the hash map and look at all items
		for(String items: sp.keySet()){
			typeWrap wrapperItems = sp.get(items);
			if(item.equals(items)){

				if(bid > wrapperItems.getBid()){
					wrapperItems.setBid(bid);
					wrapperItems.setIpAddress(ipAddress);
					return "Accepted.";
				} else if(bid <= wrapperItems.getBid()){
					return "Rejected.";
				}
			}
		}
		return "";
	}

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    // // Allowing for input of item
	// public void addDetails(String item, String ipAddress) {
	// 	data.add(new typeWrap(item, 0.00, ipAddress));
	// }
	
	// // Make a bid
	// // When making a bid. Bid can only be greater than the current bid value
	
	// public void makeBid(String item, double bid, String ipAddress) {
	// 	auctionProtocol ap = new auctionProtocol();
	// 	for(auctionProtocol.typeWrap lol: ap.data) {
	// 		if(lol.getItem() == item) {
	// 			if(lol.getBid() > bid) {
	// 				lol.setBid(bid);
	// 			}else if(lol.getBid() <= bid) {
	// 				System.out.println("Bid a higher amount");
	// 			}
	// 		}	
	// 		System.out.println(lol.getItem()+" "+lol.getBid()+" "+lol.getIpAddress());
	// 	}
	// }
	
	
	// // Method to be used with the "show" command line argument
	// // Displays items, bids and IP address of bidder from ArrayList
	// public void showItems() {
	// 	auctionProtocol ap = new auctionProtocol();
	// 	if(ap.data == null) {
	// 		System.err.println("Empty - No Items");
	// 		System.exit(1);
	// 	}else {
	// 		for(auctionProtocol.typeWrap lol: ap.data) {
	// 			System.out.println(lol.getItem()+" "+lol.getBid()+" "+lol.getIpAddress());
				
	// 		}
	// 	}
	// }
	
	
	
//	public static void main(String args[]) {
//		auctionProtocol ap = new auctionProtocol();
//		ap.addDetails("Table", 10.0, "1010101");
//		ap.addDetails("Table", 12.0, "1010101");
//		ap.addDetails("Table", 15.0, "1010101");
//		
//		for(auctionProtocol.typeWrap lol: ap.data) {
//			System.out.println(lol.getItem()+" "+lol.getBid()+" "+lol.getIpAddress());
//			
//		}
//	}
	
}