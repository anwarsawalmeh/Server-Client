import java.io.*;
import java.net.*;
import java.util.*;

// Auction Protocol Responsible for dealing with information
public class auctionProtocol {
	
	// type Wrapper allows for us to store all info in a HashMap
	public class info {
		private double bid;
		private String ipAddress;
	
		public info(double bid, String ipAddress) {

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
    private HashMap<String, info> sp = new HashMap<String, info>();

    public String show(String in){
        return in;
    }

	public String addItem(String item, double bid, String ipAddress){
		// create an instance of the wrap
		info bid_ip = null;
		if(bid == 0.00){
			bid_ip = new info(bid, "<no bids>");
			sp.put(item, bid_ip);
			return "Success.";

		}else{
			bid_ip = new info(bid, ipAddress);
			sp.put(item, bid_ip);
			return "Success.";
		}
		
	}

	public String displayItems(){
		if(sp.isEmpty()){
			return "There are currently no items in this auction.";
		}
		StringBuilder sb = new StringBuilder();;
		for(String items: sp.keySet()){
			info wrapperItems = sp.get(items);
			System.out.print(items+ " "+wrapperItems.getBid()+" "+wrapperItems.getIpAddress()+" \n");
			sb.append(items);
			sb.append(" : ");
			sb.append(Double.toString(wrapperItems.getBid()));
			sb.append(" : ");
			sb.append(wrapperItems.getIpAddress());
			// sb.append("\n");
		}

		return sb.toString();
	}

	public String placeBid(String item, double bid, String ipAddress){
		// iterate over the hash map and look at all items
		for(String items: sp.keySet()){
			info wrapperItems = sp.get(items);
			if(item.equals(items)){

				if(bid > wrapperItems.getBid()){
					wrapperItems.setBid(bid);
					wrapperItems.setIpAddress(ipAddress);
					return "Accepted.";
				} else if(bid <= wrapperItems.getBid()){
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