package by.htp.client.console.client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import by.htp.client.console.FailedConnectionException;
import by.htp.client.entity.Book;

public class ConsoleClientIO2 {
	
	private String title;
	private String type;
	private Socket socket;
	DataOutputStream os = null;
		
	public void connect( String host, int port ) throws FailedConnectionException {
		
		// validate the input:
		try {
			socket = new Socket( host, port );
			System.out.println( "Connection established on client side..." );
		} catch (IOException e) {
			throw new FailedConnectionException( "Can not connect.", e );			
		} // end try catch
		
	} // end connect
	
	public Book getBook( int id ) {

		sendBookRequest( id );
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			connect("localhost", 9595);
		} catch (FailedConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Book book = receiveBookResponse( );
		book.setId( id );
		return book;
		
	} // end getBook
	
	private void sendBookRequest( int id ) {
		
		try {
			os = new DataOutputStream( socket.getOutputStream() );
			os.writeBytes( "getBook" + "\n" + Integer.toString( id ) );
			System.out.println( "Send book request from client side..." );
		
		} catch (IOException e) {
			System.out.println( "IOException catch, from client side..." );
			System.out.println("Here IOException Console client.");
			e.printStackTrace();
		} 
		finally {
			try {
				os.flush();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			try {
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} // end finally
		
	} // end sendBookRequest
	
	private Book receiveBookResponse( ) {
		
		Book book = new Book();
		BufferedReader br = null;
		String message = null;
		
		try {
			br = new BufferedReader( new InputStreamReader( socket.getInputStream( ) ) );
			message = br.readLine();
			System.out.println( "Book name in client is: " + message );
						
		} catch (IOException e) {
			e.printStackTrace();
			
		} finally {
			try {
				if( br != null ) {
					br.close();
				}
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} // end try catch finally
		
		book.setTitle( message );
		return book;
		
	}

} // end class