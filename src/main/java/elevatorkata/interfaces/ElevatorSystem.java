package elevatorkata.interfaces;

/**
 * Created by dnwiebe on 6/27/16.
 */
public interface ElevatorSystem {
  static ElevatorSystem make () {return new elevatorkata.implementations.ElevatorSystemImpl ();}

  Floor.Builder makeFloorBuilder (int floorNumber, double position, Floor.Sensor sensor);
  Car.Builder makeCarBuilder (int weightCapacity, elevatorkata.interfaces.Floor[] floors, Car.Sensor sensor);
  Passenger makePassenger (int floorNumber, int weight);

  elevatorkata.interfaces.Floor[] getFloors ();
  elevatorkata.interfaces.Car[] getCars ();
  elevatorkata.interfaces.Passenger[] getPassengers ();
}
