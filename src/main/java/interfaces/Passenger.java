package interfaces;


/**
 * Created by dnwiebe on 6/27/16.
 */
public interface Passenger {
  // access
  int weight ();
  Integer carLocation ();
  Integer floorLocation ();
  Long sincePressingDirectionButton ();
  Long sinceEnteringCar ();

  // operation
  void pressUpButton ();
  void pressDownButton ();
  void insertKeyAtFloor (int keyIdx);
  void removeKeyAtFloor (int keyIdx);
  void enterCar (int carId);
  void exitCar (); // disappears
  void pressFloorButton ();
  void pressStopButton ();
  void pressContinueButton ();
  void pressDoorsOpenButton ();
  void pressDoorsCloseButton ();
  void obstructDoors (int carId);
  void clearDoorObstruction (int carId);
}
