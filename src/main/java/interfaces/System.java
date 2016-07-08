package interfaces;

import java.util.Set;

/**
 * Created by dnwiebe on 6/27/16.
 */
public interface System {
  Floor.Builder makeFloor (int floorNumber, double position, Floor.Sensor sensor);
  Car.Builder makeCar (int weightCapacity, Floor[] floors, Car.Sensor sensor);
  Passenger makePassenger (int floorNumber, int weight);

  Set<Floor> getFloors ();
  Set<Car> getCars ();
}
