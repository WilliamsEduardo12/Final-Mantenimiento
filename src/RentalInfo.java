import java.util.HashMap;

public class RentalInfo {

  public String statement(Customer customer) {
    HashMap<String, Movie> movies = new HashMap();
    movies.put("F001", new Movie("You've Got Mail", "regular"));
    movies.put("F002", new Movie("Matrix", "regular"));
    movies.put("F003", new Movie("Cars", "childrens"));
    movies.put("F004", new Movie("Fast & Furious X", "new"));

    double totalAmount = 0;
    int frequentEnterPoints = 0;
    String result = "Rental Record for " + customer.getName() + "\n";
    for (MovieRental r : customer.getRentals()) {
      double thisAmount = 0;

      // determine amount for each movie

      Movie movie = movies.get(r.getMovieId());
      String typeMovie = movie.getCode();

      switch(typeMovie){
        case "regular":
          thisAmount = 2;
          if (r.getDays() > 2) {
            thisAmount += (r.getDays() - 2) * 1.5 ;
          }
          break;
        case "new":
          thisAmount = r.getDays() * 3;
          break;
        case "childrens":
          thisAmount = 1.5;
          if (r.getDays() > 3) {
              thisAmount += (r.getDays() - 3) * 1.5;
          }
          break;
        default:
          break;
      }

      //add frequent bonus points
      frequentEnterPoints++;
      // add bonus for a two day new release rental
      if (movies.get(r.getMovieId()).getCode().equals("new") && r.getDays() > 2) frequentEnterPoints++;

      //print figures for this rental
      result += "\t" + movies.get(r.getMovieId()).getTitle() + "\t" + thisAmount + "\n";
      totalAmount += thisAmount;
    }
    // add footer lines
    result += "Amount owed is " + totalAmount + "\n";
    result += "You earned " + frequentEnterPoints + " frequent points\n";

    return result;
  }
}
