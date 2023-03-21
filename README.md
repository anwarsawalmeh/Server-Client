# Auction System Java Project
This is a server-client java application that emulates a basic auction system. The application allows users to place items for auction, make bids on those items, and view a list of items available for auction.

# Running the Application
## Prerequisites
To run this application, you will need to have the following installed on your machine:

Java Development Kit (JDK) version 8 or higher
Apache Maven version 3.6.3 or higher
## Building the Application
To build the application, navigate to the root folder of the project in your terminal and compile all the different java files

## Running the Server
To run the server, navigate to the target folder in your terminal and run the following command:
`java Server`
The Server will automatically start listening to port `6060`.

## Running the Client
To run the client, navigate to the target folder in your terminal and run the following command:
`java Client` followed by the appropriate command line argument. This could be:
- `show`: Displays all the items in the Auction
- `item <string>`: Allows the client to input a new item. `item Table` inputs the item Table into the auction.
- `bid <item> <int>`: Allows the client to make bids on items. `bid Table 20.0` allows us to make a bid of 20.0 on Table (previously added).

## Logging
The server keeps track if the transactions by the client and stores these transactions in a text file displaying the Date, Time, IP address of Client and the action by the client (`bid` or `item`).
File is deleted everytime the server is run again as per backlog requirements. 

## Project Structure
The project is split into two folders: client and server. The client folder contains one file, `Client.java` , which contains the code for the client. The server folder contains three files:

- `Server.java`: This file contains the main method for the server, which starts the server and listens for incoming connections.
- `AuctionProtocol.java`: This file contains the protocol for communication between the client and server.
- `ClientHandler.java`: This file contains the code for handling a single client connection on the server.

