package databases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.sqlite.SQLiteConfig;

/**
 * Command-line application for a set of queries
 * on the Chinook database
 * 
 * Name: YOUR NAME HERE
 * 
 * @author derbinsky
 */
public class ChinookApp {
	
	/**
	 * Allowed query types
	 */
	private static enum QueryTypes {
		CustomerByCountry,
		AllEmployees,
		CustomersByEmployeeNumber,
		AllCustomers,
		InvoicesByCustomerId
	}
	
	/**
	 * Query type and parameter value
	 * (null if not appropriate)
	 * 
	 * @author derbinsky
	 */
	private static class QueryData {
		final public QueryTypes queryType;
		final public String queryParam;
		
		public QueryData(QueryTypes qn, String qp) {
			queryType = qn;
			queryParam = qp;
		}
		
		@Override
		public String toString() {
			return String.format("%s" + ((queryParam == null)?(""):(" (%s)")), queryType, queryParam);
		}
	}
	
	/**
	 * Usage statement, then exit
	 * 
	 * @return null (to make other code easier)
	 */
	private static QueryData _usage() {
		System.out.printf("Usage: java %s <path to Chinook database> <query #> [parameter value]%n%n", ChinookApp.class.getCanonicalName());
		System.out.printf("1) How many customers live in country [parameter value]?%n");
		System.out.printf("2) List all employees (sort by employee id)%n");
		System.out.printf("3) How many customers have been supported by employee id [parameter value]?%n");
		System.out.printf("4) List all customers (sort by customer id)%n");
		System.out.printf("5) List all invoices for customer #[parameter value] (sort by invoice id, each line by invoice line id)%n%n");
		System.exit(0);
		return null;
	}
	
	/**
	 * Validates command-line arguments
	 * 
	 * @param args command-line arguments
	 * @return query data, or null if invalid
	 * @throws ClassNotFoundException cannot find JDBC driver
	 */
	private static QueryData validateInputs(String[] args) throws ClassNotFoundException {
		// must have at least two arguments
		if (args.length < 2) {
			return _usage();
		}
		
		// attempt connecting to the database
		// (read-only ensures exists)
		Class.forName( "org.sqlite.JDBC" );
		final SQLiteConfig config = new SQLiteConfig();
		config.setReadOnly(true);
		try (final Connection connection = DriverManager.getConnection( "jdbc:sqlite:" + args[0], config.toProperties() )) {
		} catch (SQLException e) {
			System.out.println("Invalid database");
			return _usage();
		}
		
		// make sure second argument is a valid query number
		// and third is appropriate to query
		try {
			final int queryNum = Integer.valueOf(args[1]);
						
			if (queryNum == 1) {
				if (args.length != 3) {
					return _usage();
				} else {
					return new QueryData(QueryTypes.CustomerByCountry, args[2]);
				}
			} else if (queryNum == 2) {
				if (args.length != 2) {
					return _usage();
				} else {
					return new QueryData(QueryTypes.AllEmployees, null);
				}
			} else if (queryNum == 3) {
				if (args.length != 3) {
					return _usage();
				} else {
					Integer.valueOf(args[2]);
					return new QueryData(QueryTypes.CustomersByEmployeeNumber, args[2]);
				}
			} else if (queryNum == 4) {
				if (args.length != 2) {
					return _usage();
				} else {
					return new QueryData(QueryTypes.AllCustomers, null);
				}
			} else if (queryNum == 5) {
				if (args.length != 3) {
					return _usage();
				} else {
					Integer.valueOf(args[2]);
					return new QueryData(QueryTypes.InvoicesByCustomerId, args[2]);
				}
			} else {
				return _usage();
			}
			
		} catch (NumberFormatException e) {
			return _usage();
		}
	}

	/**
	 * Command-line Chinook utility
	 * 
	 * @param args command-line arguments
	 * @throws ClassNotFoundException cannot find JDBC driver
	 * @throws SQLException SQL gone bad
	 */
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		// validates the inputs, exits if bad
		final QueryData qd = validateInputs(args);		
		
		// makes a connection to the database
		try (final Connection connection = DriverManager.getConnection("jdbc:sqlite:" + args[0])) {
			
			if (qd.queryType == QueryTypes.CustomerByCountry) {
				// Your code for query #1
				// Country in qd.queryParam
				final String sqlQuery = "select	count (*)	from	Customer	where 	country = ?";
				PreparedStatement stmnt;
				try {
					stmnt = connection.prepareStatement( sqlQuery );
					stmnt.setString( 1, qd.queryParam);
					final ResultSet res = stmnt.executeQuery();
					while (res.next() ) {
						System.out.println(res.getString("count (*)"));
					}
				} catch (SQLException e) {
					System.out.println(e);
				}
			} else if (qd.queryType == QueryTypes.AllEmployees) {
				// Your code for query #2
				final String sqlQuery = "select	*	from	Employee	order by	EmployeeId";
				PreparedStatement stmnt;
				try {
					stmnt = connection.prepareStatement( sqlQuery );
					final ResultSet res = stmnt.executeQuery();
					int count = 1;
					while (res.next() ) {
						System.out.println(count +  ". "+res.getString("FirstName") + " " + res.getString("LastName") + " (" + res.getString("Title") + ")");
						count++;
					}
				} catch (SQLException e) {
					System.out.println(e);
				}
			} else if (qd.queryType == QueryTypes.CustomersByEmployeeNumber) {
				// Your code for query #3
				// Employee number as a string in qd.queryParam
				final String sqlQuery = "select 	count (*)	from	Customer		where	supportRepId = ?";
				PreparedStatement stmnt;
				try {
					stmnt = connection.prepareStatement( sqlQuery );
					stmnt.setString( 1, qd.queryParam);
					final ResultSet res = stmnt.executeQuery();
					while (res.next() ) {
						System.out.println(res.getString("count (*)"));
					}
				} catch (SQLException e) {
					System.out.println(e);
				}
			} else if (qd.queryType == QueryTypes.AllCustomers) {
				// Your code for query #4
				final String sqlQuery = "select	*	 	"
						+ "from	Customer		order by	customerId";
				PreparedStatement stmnt;
				try {
					stmnt = connection.prepareStatement( sqlQuery );
					final ResultSet res = stmnt.executeQuery();
					int count1 = 1;
					while (res.next() ) {
						System.out.print(
								count1 +  
								". "+
								res.getString("FirstName") + " " + 
								res.getString("LastName") + 
								" (" +
								res.getString("City") +
								", " );
						
						if(res.getString("State") != null){
							System.out.print(
									res.getString("State") +
									", ");
						}	
							
						System.out.println(res.getString("Country") +
						")");
						count1++;
					}
				} catch (SQLException e) {
					System.out.println(e);
				}
				
			} else if (qd.queryType == QueryTypes.InvoicesByCustomerId) {
				// Your code for query #5
				// Customer id as a string in qd.queryParam
				final String sqlQuery = " select	( \" \" || il.invoiceLineId || \": \" || "
										+ " \"'\"|| t.name || \"' on \" ||"
										+ " \"'\"|| alb.title || \"' by \" ||"
										+ " \"'\"|| a.name ||"
										+ " \"' (1 @ $\" || t.unitPrice || \")\") as output, "
										+ " i.invoiceId as inv, "
										+ " i.total as tot,"
										+ " *"
										+ " from	Invoice i "
										+ "	inner join"
										+ " InvoiceLine il "
										+ "	inner join"
										+ " customer c"
										+ "	inner join"
										+ " Track t"
										+ "	inner join"
										+ " Album alb"
										+ "	inner join"
										+ " Artist a"
										+ " on i.invoiceId = il.invoiceId"
										+ " and i.customerId = c.customerId"
										+ " and il.trackId = t.trackId"
										+ " and alb.albumId = t.albumId"
										+ " and alb.artistId = a.artistId"
										+ " where	i.customerId = ?	order by	i.invoiceId, il.invoiceLineId";
				PreparedStatement stmnt;
				try {
					stmnt = connection.prepareStatement( sqlQuery );
					stmnt.setString( 1, qd.queryParam);
					final ResultSet res = stmnt.executeQuery();
					int invoiceId = 0, prev = res.getInt("inv");

					System.out.println("Invoice #"+ prev + " ($" + res.getFloat("tot") + ")");
					
					while (res.next() ) {
						//"Invoice #23 ($3.96)",
						invoiceId = res.getInt("inv");
						if(prev!= invoiceId)	{
							System.out.println("");
							prev = invoiceId;
							float total = res.getFloat("tot");
							System.out.println("Invoice #"+ invoiceId + " ($" + total + ")");
						}
						System.out.println(res.getString("output"));
					}
					System.out.println("");
				} catch (SQLException e) {
					System.out.println(e);
				}
				
			}
			
		}
	}

}
