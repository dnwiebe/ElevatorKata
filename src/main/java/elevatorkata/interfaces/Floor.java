package elevatorkata.interfaces;

import java.util.Set;

/**
 * Created by dnwiebe on 6/25/16.
 */
public interface Floor {

  /// Initialization
  interface Builder {
    Builder removeUpCar (int carId);
    Builder removeDownCar (int carId);
    Builder addKeyLock (int keyIdx);
    Floor build ();
  }

  // Access
  int number ();
  double position ();
  int[] upButtonCarIds ();
  int[] downButtonCarIds ();
  int[] keyLockIndexes ();

  // Operation
  void upButtonLight (boolean on);
  void downButtonLight (boolean on);
  void arrivalUpLight (int carIdx, boolean on);
  void arrivalDownLight (int carIdx, boolean on);
  void arrivalBell (int carIdx);

  // Sensing
  interface Sensor {
    void upButtonPressed (long timestamp);
    void downButtonPressed (long timestamp);
    void keyInserted (int keyIdx, long timestamp);
    void keyRemoved (int keyIdx, long timestamp);

    void upButtonLight (boolean on, long timestamp);
    void downButtonLight (boolean on, long timestamp);
    void arrivalUpLight (int carIdx, boolean on, long timestamp);
    void arrivalDownLight (int carIdx, boolean on, long timestamp);
    void arrivalBell (int carIdx, long timestamp);
  }

  class NullSensor implements Sensor {
    @Override public void upButtonPressed (long timestamp) {}
    @Override public void downButtonPressed (long timestamp) {}
    @Override public void keyInserted (int keyIdx, long timestamp) {}
    @Override public void keyRemoved (int keyIdx, long timestamp) {}

    @Override public void upButtonLight (boolean on, long timestamp) {}
    @Override public void downButtonLight (boolean on, long timestamp) {}
    @Override public void arrivalUpLight (int carIdx, boolean on, long timestamp) {}
    @Override public void arrivalDownLight (int carIdx, boolean on, long timestamp) {}
    @Override public void arrivalBell (int carIdx, long timestamp) {}
  }
}
