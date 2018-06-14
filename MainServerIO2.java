package by.htp.server.run;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.Instant;

import by.htp.server.entity.Book;

public class MainServerIO2 {

	public static void main(String[] args) {
		
		ServerSocket server = null;
		Socket client = null;
		PrintStream ps = null;
		DataInputStream is = null;

		Book bookList1 = new Book( 1, "1st great book.");
		Book bookList2 = new Book( 2, "2nd great book.");
		Book bookList3 = new Book( 3, "3th great book.");
		Book bookList4 = new Book( 4, "4th great book.");
		Book[] bookList = new Book[ 4 ];
		bookList[ 0 ] = bookList1;
		bookList[ 1 ] = bookList2;
		bookList[ 2 ] = bookList3;
		bookList[ 3 ] = bookList4;		
				
		try {
			server = new ServerSocket( 9595 );
			System.out.println("Server started...");
			
			client = server.accept();
			System.out.println("client input connected...");
			
			is = new DataInputStream( client.getInputStream() );

			int bytes = is.available();
			System.out.println("Bytes available: " + bytes );
			
			byte[ ] data = new byte[ 1024 ];
			data = is.readAllBytes();
			String dataToString = new String( data ); 
			System.out.println("Data received, bytes available: " + bytes + ", bytes read: " + ":" + "\n" + dataToString );
			
			String[] dataLines = dataToString.split("(\n)");
			for( int x = 0; x < dataLines.length; x++ )
				System.out.println( dataLines[ x ] );

			String tempS = dataLines[ 1 ].trim();
			int numOfBook = Integer.parseInt( tempS );
			String currentBookName = bookList[ numOfBook ].getTitle();
			System.out.println( "Book title is: " + currentBookName );
			
			System.out.println( Instant.now().toEpochMilli() );
			
			is.close();
			client.close();
			
			client = server.accept();
			System.out.println( "Here 94" );
			ps = new PrintStream( client.getOutputStream() );
			System.out.println( "Here 96" );
			ps.println( currentBookName );
			System.out.println( "Here 98" );
			ps.flush();
			System.out.println( "Here 100" );
			
			Thread.sleep(100);
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			try {
				if( ps != null ) {
					ps.close();
				}
				is.close();
				client.close();
				System.out.println("Server closed.");
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		} // end try, catch, finally
		
	} // end main

} // end class