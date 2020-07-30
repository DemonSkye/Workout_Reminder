import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class WorkoutReminder {
    static final String workouts[] = {
            "Pushups", "Curls", "Squats", "Crunches", "Lat raise", "Kneeling hammer Press", "Reverse pushups",
            "Ab roller"
    };
    static final int reps[] = {
            10,14,15,17,20
    };
    public static void main(String args[])throws AWTException, InterruptedException{
        if(SystemTray.isSupported()) {
            WorkoutReminder wr = new WorkoutReminder();
            wr.displayReminder();
        }
        else{
            System.out.println("Run this on windows plz <3");
        }
    }

    private static void displayReminder() throws AWTException, InterruptedException{
        while (true) {
            DateTimeFormatter dtfm = DateTimeFormatter.ofPattern("mm");
            DateTimeFormatter dtfh = DateTimeFormatter.ofPattern("HH");
            LocalDateTime currentTime = LocalDateTime.now();
            String min = dtfm.format(currentTime);
            int hour = Integer.parseInt(dtfh.format(currentTime));
            System.out.println(min);
            if(min.equals("00") && ( hour >=9 && hour < 21)) {
                Random rand = new Random();
                String workout = workouts[Math.abs(rand.nextInt() % workouts.length)];
                int rep = reps[Math.abs(rand.nextInt() % reps.length)];

                SystemTray st = SystemTray.getSystemTray();
                Image logo = Toolkit.getDefaultToolkit().createImage("image.png");
                TrayIcon trayIcon = new TrayIcon(logo, "Workout you fat slob");
                trayIcon.setImageAutoSize(true);
                String iconText = "Workout reminder";
                String workoutText = "Let's get it: " + workout + " for " + rep + "reps";
                trayIcon.setToolTip("YEEEET");
                st.add(trayIcon);
                trayIcon.displayMessage(workoutText, iconText, TrayIcon.MessageType.INFO);
                Thread.sleep(60000);
            }
            else{
                Thread.sleep(60000);
            }
        }
    }
}
