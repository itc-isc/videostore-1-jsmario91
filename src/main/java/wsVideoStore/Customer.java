package wsVideoStore;
import java.util.Vector;

public class Customer 
{
	private static final int NewReleaseMovie = 0;
	public Customer (String name) {
		this.name = name;
	}
	
	public static int getNewreleasemovie() {
		return NewReleaseMovie;
	}

	public void addRental (Rental rental) {
		extracted(rental);
	}

	
	private void extracted(Rental rental) {
	}

	public String getName () {
		return name;
	}
	
	public String statement () {
		double 				totalAmount 			= 0;
		int					frequentRenterPoints 	= 0;
		var 		rentals 				= this.rentals.elements ();
		String 				result 					= "Rental Record for " + getName () + "\n";
		
		while (rentals.hasMoreElements ()) {
			double 		thisAmount = 0;
			Rental 		each = (Rental)rentals.nextElement ();
			
			// determines the amount for each line
			switch (each.getMovie ().getClass().getName()) {
				case  "RegularMovie":
					thisAmount += 2;
					if (each.getDaysRented () > 2)
						thisAmount += (each.getDaysRented () - 2) * 1.5;
					break;
				case  "NewReleaseMovie":
					thisAmount += each.getDaysRented () * 3;
					break;
				case  "ChildrensMovie":
					thisAmount += 1.5;
					if (each.getDaysRented () > 3)
						thisAmount += (each.getDaysRented () - 3) * 1.5;
					break;
			}
			
			frequentRenterPoints++;
			
			if (each.getMovie ().getClass().getName() == "NewReleaseMovie" 
					&& each.getDaysRented () > 1)
				frequentRenterPoints++;
				
			result += "\t" + each.getMovie ().getTitle () + "\t"
								+ String.valueOf (thisAmount) + "\n";
			totalAmount += thisAmount;
				
		}
		
		result += "You owed " + String.valueOf (totalAmount) + "\n";
		result += "You earned " + String.valueOf (frequentRenterPoints) + " frequent renter points\n";
		
		
		return result;
	}
	

	private String name;
	private Vector rentals = new Vector ();
}