import java.util.*;

public class DeviceRegistry {
    private final List<SmartClassroomDevice> devices = new ArrayList<>();

    public void add(SmartClassroomDevice d) { devices.add(d); }

    public <T> T getFirst(Class<T> capability) {
        for (SmartClassroomDevice d : devices) {
            if (capability.isInstance(d)) return capability.cast(d);
        }
        throw new IllegalStateException("No device with capability: " + capability.getSimpleName());
    }

    public <T> List<T> getAll(Class<T> capability) {
        List<T> out = new ArrayList<>();
        for (SmartClassroomDevice d : devices) {
            if (capability.isInstance(d)) out.add(capability.cast(d));
        }
        return out;
    }
}
