package interfaces;

import java.util.Set;

/**
 * Created by dnwiebe on 6/27/16.
 */
public interface Car {
  // initialization
  public static interface Builder {
    Builder addStopButton();
    Builder addContinueButton();
    Builder addDoorsOpenButton();
    Builder addDoorsCloseButton();
    Builder addKeyLock(int keyIdx);
    Builder addPositionSensor(double position);
    Car build ();
  }

  // access
  int id ();
  int weightCapacity ();
  Set<Integer> floorsServed ();
  Set<Integer> keyLockIndexes ();
  Set<Double> sensorPositions ();

  // sensing
  public static interface Sensor {
    void floorButtonPressed(int floor);
    void stopButtonPressed ();
    void continueButtonPressed ();
    void doorsOpenButtonPressed ();
    void doorsCloseButtonPressed ();
    void doorsOpen ();
    void doorsClosed ();
    void obstructionDetected ();
    void obstructionRemoved ();
    void keyInserted (int keyIdx);
    void keyRemoved (int keyIdx);
    void positionHit (double position);
    void weightChanged (int newWeight);
  }

  // operation
  void floorButtonLight (boolean onOff);
  void stopButtonLight (boolean onOff);
  void continueButtonLight (boolean onOff);
  void doorsOpenButtonLight (boolean onOff);
  void doorsCloseButtonLight (boolean onOff);
  void closeDoors ();
  void openDoors ();
  void applyLift (double g, double targetSpeed);
  void stopAt (double position);
}
