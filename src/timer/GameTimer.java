//package timer;
//
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//public class GameTimer {
//    private javax.swing.Timer timer; // Swing Timer for managing countdown
//    private int remainingTime; // Time remaining in seconds
//    private ITimerListener listener; // Listener to handle timer events
//
//    /**
//     * Constructor initializes the timer with a given listener.
//     * @param listener The object that will listen for timer updates.
//     */
//    public GameTimer(ITimerListener listener) {
//        this.remainingTime = 120; // Set initial time to 120 seconds
//        this.listener = listener; // Set the listener for timer events
//
//        // Initialize the timer to trigger every 1 second (1000 milliseconds)
//        timer = new javax.swing.Timer(1000, new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if (remainingTime > 0) {
//                    remainingTime--; // Decrease the remaining time by 1 second
//                    listener.onTimeUpdate(remainingTime); // Notify listener with the updated time
//                } else {
//                    timer.stop(); // Stop the timer when time is up
//                    listener.onTimeUp(); // Notify listener that the time is up
//                }
//            }
//        });
//    }
//
//    public void timerStart() {
//        timer.start(); // Begin the timer
//    }
//
//    public void timerStop() {
//        timer.stop(); // Stop the timer
//    }
//
//    public int getRemainingTime() {
//        return remainingTime; // Return the current remaining time in seconds
//    }
//}
//
