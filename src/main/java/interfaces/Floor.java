package interfaces;

import java.util.Set;

/**
 * Created by dnwiebe on 6/25/16.
 */
public interface Floor {

  /// Initialization
  public static interface Builder {
    Builder addUpButton(Set<Integer> carIdx);
    Builder addDownButton(Set<Integer> carIdx);
    Builder addKeyLock(int keyIdx);
    Floor build ();
  }

  // Access
  int number ();
  double position ();
  Set<Integer> upButtonIds ();
  Set<Integer> downButtonIds ();
  Set<Integer> keyLockIndexes ();

  // Operation
  void upButtonLight (int buttonId, boolean onOff);
  void downButtonLight (int buttonId, boolean onOff);
  void arrivalUpLight (int carIdx);
  void arrivalDownLight (int carIdx);
  void arrivalBell (int carIdx);

  // Sensing
  public static interface Sensor {
    void upButtonPressed (int buttonId);
    void downButtonPressed (int buttonId);
    void keyInserted (int keyIdx);
    void keyRemoved (int keyIdx);
  }
}
