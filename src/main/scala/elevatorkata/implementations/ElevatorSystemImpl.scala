package elevatorkata.implementations

import elevatorkata.interfaces.{Car, ElevatorSystem, Floor, Passenger}

/**
  * Created by dnwiebe on 7/8/16.
  */
class ElevatorSystemImpl extends ElevatorSystem {
  private var _nextId = 1
  private var floors: Set[FloorImpl] = Set ()
  private var cars: Set[CarImpl] = Set ()
  private var passengers: Set[PassengerImpl] = Set ()

  override def makeFloorBuilder (floorNumber: Int, position: Double, sensor: Floor.Sensor): Floor.Builder = {
    new FloorImpl.BuilderImpl (this, floorNumber, position, sensor)
  }

  override def makeCarBuilder (weightCapacity: Int, floors: Array[elevatorkata.interfaces.Floor], sensor: Car.Sensor): Car.Builder = {
    new CarImpl.BuilderImpl (this, weightCapacity, floors, sensor)
  }

  override def makePassenger (floorNumber: Int, weight: Int): Passenger = {
    new PassengerImpl (this)
  }

  override def getFloors: Array[Floor] = {
    floors.toArray
  }

  override def getCars: Array[Car] = {
    cars.toArray
  }

  override def getPassengers: Array[Passenger] = {
    passengers.toArray
  }

  def nextId (): Int = {
    val result = _nextId
    _nextId = _nextId + 1
    result
  }

  def timestamp: Long = 78L

  def addFloor (floor: FloorImpl): Unit = {
    floors = floors + floor
  }

  def addCar (car: CarImpl): Unit = {
    cars = cars + car
  }

  def addPassenger (passenger: PassengerImpl): Unit = {
    passengers = passengers + passenger
  }
}
