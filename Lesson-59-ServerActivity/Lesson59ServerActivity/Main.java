import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.sql.*;

import java.net.InetSocketAddress;
import java.util.Map;

//For compiling on the shell on repl: Same on mac
//javac -cp sqlite-jdbc-3.23.1.jar: Main.java
//java -cp sqlite-jdbc-3.23.1.jar: Main

//Use for windows
//javac -cp sqlite-jdbc-3.23.1.jar; Main.java
class Main {

 public static void main(String[] args)throws IOException{
    (new Main()).init();
  }


  void print(Object o){ System.out.println(o);}
  void printt(Object o){ System.out.print(o);}

  void init() throws IOException{
   

    // create a port - our Gateway
    int port = 8300;
      
    //create the HTTPserver object
    HttpServer server = HttpServer.create(new InetSocketAddress(port),0);

    // create the database object
    Database db = new Database("jdbc:sqlite:veekun-pokedex2.db");
    
    
  
    server.createContext("/", new RouteHandler("Welcome to My pokemon database, i have provided 3 routes that show the pokemons abilities locations and moves....") );

    // Add your routes here....
    String sql = "Select * from abilities";
    
    server.createContext("/abilities", new RouteHandler(db,sql) );
	
	 sql = "Select * from pokemon";
    
    server.createContext("/pokemon", new RouteHandler(db,sql) );
	
	 sql = "Select * from locations";
    
    server.createContext("/locations", new RouteHandler(db,sql) );
	
	sql = "Select * from moves";
    
    server.createContext("/moves", new RouteHandler(db,sql) );
	
	sql = "Select * from stats";
    
    server.createContext("/stats", new RouteHandler(db,sql) );

   
     
      
    //Start the server
    server.start();

    System.out.println("Server is listening on port "+port);
       
      
    }    
}


