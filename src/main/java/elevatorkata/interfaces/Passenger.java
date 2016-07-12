package elevatorkata.interfaces;


/**
 * Created by dnwiebe on 6/27/16.
 */
public interface Passenger {

  // access
  int weight ();
  Integer carLocation ();
  Integer floorLocation ();
  String interiorDisplay ();
  Long sincePressingDirectionButton ();
  Long sinceEnteringCar ();

  // operation
  void pressUpButton (long delay);
  void pressDownButton (long delay);
  void insertKeyAtFloor (long delay, int keyIdx);
  void removeKeyAtFloor (long delay, int keyIdx);
  void enterCar (long delay, int carId);
  void exitCar (long delay); // passenger disappears
  void pressFloorButton (long delay, int floor);
  void pressStopButton (long delay);
  void pressContinueButton (long delay);
  void pressDoorsOpenButton (long delay);
  void pressDoorsCloseButton (long delay);
  void obstructDoors (long delay);
  void clearDoorObstruction (long delay);
}
