import com.skillbox.airport.Airport;
import com.skillbox.airport.Flight;
import com.skillbox.airport.Terminal;

import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class Main {
    private static int hours = 2;
    private static Date date;
    private static Date dateBefore;

    public static void main(String[] args) {
        List<Terminal> terminals = Airport.getInstance().getTerminals();
        date();

        terminals.stream().flatMap(terminal -> terminal.getFlights().stream())
                .filter(flight -> flight.getType() == Flight.Type.ARRIVAL)
                .filter(flight -> flight.getDate().after(date))
                .filter(flight -> flight.getDate().before(dateBefore))
                .sorted(Comparator.comparing(Flight::getDate))
                .forEach(flight -> System.out.format("%1$s %2$tR %2$tD - %3$s\n",
                        "Вылет в ближайшие " + hours + " часа:", flight.getDate(), flight.getAircraft().getModel()));
    }

    private static void date() {
        Calendar calendar = Calendar.getInstance();
        date = calendar.getTime();
        calendar.add(Calendar.HOUR, hours);
        dateBefore = calendar.getTime();
    }

}
