import net.danielgill.ros.service.Service;
import net.danielgill.ros.service.ServiceInvalidException;
import net.danielgill.ros.service.data.Data;
import net.danielgill.ros.service.event.CdtEvent;
import net.danielgill.ros.service.event.FerEvent;
import net.danielgill.ros.service.event.FnsEvent;
import net.danielgill.ros.service.event.SnsEvent;
import net.danielgill.ros.service.event.SntEvent;
import net.danielgill.ros.service.event.StopEvent;
import net.danielgill.ros.service.location.Location;
import net.danielgill.ros.service.location.NamedLocation;
import net.danielgill.ros.service.location.StartLocation;
import net.danielgill.ros.service.reference.Reference;
import net.danielgill.ros.service.time.Time;

public class Test {
    public static void main(String[] args) {
        Data d = new Data(10, 0, 10, 10, 10);
        Service s = new Service(new Reference("1A01"), "Test Service", d);
        s.addEvent(new SntEvent(new Time("12:00"), new StartLocation("2-2 2-1")));
        s.addEvent(new StopEvent(new Time("12:01"), new NamedLocation("A")));
        s.addEvent(new CdtEvent(new Time("12:02")));
        s.addEvent(new FnsEvent(new Time("12:03"), new Reference("1A02")));
        try {
            System.out.println(s.toFormattedString());
        } catch (ServiceInvalidException e) {
            System.err.println(e.toString());
        }

        Service s2 = new Service(new Reference("1A02"), "Test Service 2");
        s2.addEvent(new SnsEvent(new Time("12:03"), new Reference("1A01")));
        s2.addEvent(new StopEvent(new NamedLocation("A"), new Time("12:04")));
        s2.addEvent(new FerEvent(new Time("12:05"), new Location("2-2")));
        try {
            System.out.println(s2.toFormattedString());
        } catch (ServiceInvalidException e) {
            System.err.println(e.toString());
        }
    }
}
