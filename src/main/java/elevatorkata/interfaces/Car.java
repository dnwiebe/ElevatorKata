package elevatorkata.interfaces;

import java.util.Set;

/**
 * Created by dnwiebe on 6/27/16.
 */
public interface Car {
  // initialization
  interface Builder {
    Builder addStopButton ();
    Builder addContinueButton ();
    Builder addDoorsOpenButton ();
    Builder addDoorsCloseButton ();
    Builder addKeyLock (int keyIdx);
    Builder addInteriorDisplay ();
    Builder addPositionSensor (double position);
    Car build ();
  }

  // access
  int id ();
  int weightCapacity ();
  int[] floorsServed ();
  int[] keyLockIndexes ();
  String displayText ();
  double[] sensorPositions ();

  // operation
  void floorButtonLight (int floor, boolean on);
  void stopButtonLight (boolean on);
  void continueButtonLight (boolean on);
  void doorsOpenButtonLight (boolean on);
  void doorsCloseButtonLight (boolean on);
  void closeDoors ();
  void openDoors ();
  void changeDisplay (String text);
  void applyLift (double g, double targetSpeed);
  void stopAt (double position);

  // sensing
  interface Sensor {
    void floorButtonPressed(int floor, long timestamp);
    void stopButtonPressed (long timestamp);
    void continueButtonPressed (long timestamp);
    void doorsOpenButtonPressed (long timestamp);
    void doorsCloseButtonPressed (long timestamp);
    void doorsOpen (long timestamp);
    void doorsClosed (long timestamp);
    void obstructionDetected (long timestamp);
    void obstructionRemoved (long timestamp);
    void keyInserted (int keyIdx, long timestamp);
    void keyRemoved (int keyIdx, long timestamp);
    void positionHit (double position, long timestamp);
    void weightChanged (int newWeight, long timestamp);

    void floorButtonLight (int floor, boolean on, long timestamp);
    void stopButtonLight (boolean on, long timestamp);
    void continueButtonLight (boolean on, long timestamp);
    void doorsOpenButtonLight (boolean on, long timestamp);
    void doorsCloseButtonLight (boolean on, long timestamp);
    void closeDoors (long timestamp);
    void openDoors (long timestamp);
    void changeDisplay (String text, long timestamp);
    void applyLift (double g, double targetSpeed, long timestamp);
    void stopAt (double position, long timestamp);
  }
  
  class NullSensor implements Sensor {
    @Override public void floorButtonPressed(int floor, long timestamp) {}
    @Override public void stopButtonPressed (long timestamp) {}
    @Override public void continueButtonPressed (long timestamp) {}
    @Override public void doorsOpenButtonPressed (long timestamp) {}
    @Override public void doorsCloseButtonPressed (long timestamp) {}
    @Override public void doorsOpen (long timestamp) {}
    @Override public void doorsClosed (long timestamp) {}
    @Override public void obstructionDetected (long timestamp) {}
    @Override public void obstructionRemoved (long timestamp) {}
    @Override public void keyInserted (int keyIdx, long timestamp) {}
    @Override public void keyRemoved (int keyIdx, long timestamp) {}
    @Override public void positionHit (double position, long timestamp) {}
    @Override public void weightChanged (int newWeight, long timestamp) {}

    @Override public void floorButtonLight (int floor, boolean on, long timestamp) {}
    @Override public void stopButtonLight (boolean on, long timestamp) {}
    @Override public void continueButtonLight (boolean on, long timestamp) {}
    @Override public void doorsOpenButtonLight (boolean on, long timestamp) {}
    @Override public void doorsCloseButtonLight (boolean on, long timestamp) {}
    @Override public void closeDoors (long timestamp) {}
    @Override public void openDoors (long timestamp) {}
    @Override public void changeDisplay (String text, long timestamp) {}
    @Override public void applyLift (double g, double targetSpeed, long timestamp) {}
    @Override public void stopAt (double position, long timestamp) {}
  }
}
