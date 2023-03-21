import java.io.*;
import java.net.*;
import java.util.*;

// Auction Protocol Responsible for dealing with information
public class AuctionProtocol {
	
	// class info wraps the bid and ip address. Allows for storing all details in a HashMap
	public class info {
		// bids and ipAddress of clients
		private double bid;
		private String ipAddress;

		public info(double bid, String ipAddress) {

			this.bid = bid;
			this.ipAddress = ipAddress;
		}
		
		// setters and getters
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
	
	
	// Public to allow the client handler and server access
    public HashMap<String, info> sp = new HashMap<String, info>();

	public String addItem(String item, double bid, String ipAddress){
		// create an instance of the wrap
		info bid_ip = null;
		
		if(sp.containsKey(item)){
			return "Rejected.";
		}
		// checks for the inputted bids
		else if(bid == 0.00){
			bid_ip = new info(bid, "<no bids>");
			sp.put(item, bid_ip);
			return "Success.";

		}else{
			bid_ip = new info(bid, ipAddress);
			sp.put(item, bid_ip);
			return "Success.";
		}
		
	}

	// Where client inputs bids
	public String placeBid(String item, double bid, String ipAddress){
		// iterate over the hash map and look at all items (key)
		for(String items: sp.keySet()){
			info itemsDetails = sp.get(items);
			if(item.equals(items)){

				if(bid > itemsDetails.getBid()){
					itemsDetails.setBid(bid);
					itemsDetails.setIpAddress(ipAddress);
					return "Accepted.";
				} else if(bid <= itemsDetails.getBid()){
					return "Rejected.";
				} else if (bid <= 0){
					return "Failure.";
				}
			}
		}
		// item doesn't exist
		return "Failure.";
	}
}