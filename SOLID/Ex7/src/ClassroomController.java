public class ClassroomController {
    private final DeviceRegistry reg;

    public ClassroomController(DeviceRegistry reg) { this.reg = reg; }

    public void startClass() {
        DisplayDevice display = reg.getFirst(DisplayDevice.class);
        display.powerOn();
        display.connectInput("HDMI-1");

        reg.getFirst(BrightnessControl.class).setBrightness(60);
        reg.getFirst(TemperatureControl.class).setTemperatureC(24);

        int present = reg.getFirst(AttendanceCapture.class).scanAttendance();
        System.out.println("Attendance scanned: present=" + present);
    }

    public void endClass() {
        System.out.println("Shutdown sequence:");
        for (Switchable s : reg.getAll(Switchable.class)) {
            s.powerOff();
        }
    }
}
