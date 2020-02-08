import com.skillbox.airport.Airport;
import com.skillbox.airport.Flight;
import com.skillbox.airport.Terminal;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Terminal> terminals = Airport.getInstance().getTerminals();

        int hours = 2;
        Date date = new Date();
        date.setHours(date.getHours() + hours);

        terminals.stream().flatMap(terminal -> terminal.getFlights().stream())
                .filter(flight -> flight.getType() == Flight.Type.ARRIVAL)
                .filter(flight -> flight.getDate().after(new Date()))
                .filter(flight -> flight.getDate().before(date))
                .sorted(Comparator.comparing(Flight::getDate))
                .forEach(flight -> System.out.format("%1$s %2$tR %2$tD - %3$s\n",
                        "Вылет в ближайшие " + hours + " часа:", flight.getDate(), flight.getAircraft().getModel()));
    }
}
