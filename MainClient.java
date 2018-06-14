package by.htp.client.run;

import by.htp.client.console.FailedConnectionException;
import by.htp.client.console.client.ConsoleClientIO2;
import by.htp.client.entity.Book;

public class MainClient {

	public static void main(String[] args) {
		
		ConsoleClientIO2 client = new ConsoleClientIO2();
		Book book = null;
		try {
			client.connect("localhost", 9595);
			book = client.getBook( 3 );
			//System.out.println( book );
		} catch ( FailedConnectionException  e ) {
			System.out.println( "Sorry, you can not connect to the server!" );
			e.printStackTrace();
		}
		
		System.out.println( "Book from server library:");
		System.out.println( "-------------------------");
		System.out.println( "Book id: " + ( book.getId() + 1 ) );
		System.out.println( "Book title: " + book.getTitle() );
	}
}