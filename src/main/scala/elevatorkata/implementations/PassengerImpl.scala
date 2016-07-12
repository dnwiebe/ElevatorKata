package elevatorkata.implementations

/**
  * Created by dnwiebe on 7/8/16.
  */
class PassengerImpl (system: ElevatorSystemImpl) extends elevatorkata.interfaces.Passenger {
  system.addPassenger (this)

  override def weight: Int = 0

  override def carLocation: Integer = 0

  override def floorLocation: Integer = 0

  override def interiorDisplay: String = ""


  override def sincePressingDirectionButton: java.lang.Long = 0L

  override def sinceEnteringCar: java.lang.Long = 0L


  override def pressUpButton (delay: Long): Unit = {

  }

  override def pressDownButton (delay: Long): Unit = {

  }

  override def insertKeyAtFloor (delay: Long, keyIdx: Int): Unit = {

  }

  override def removeKeyAtFloor (delay: Long, keyIdx: Int): Unit = {

  }

  override def enterCar (delay: Long, carId: Int): Unit = {

  }

  override def exitCar (delay: Long): Unit = {

  }

  override def pressFloorButton (delay: Long, floor: Int): Unit = {

  }

  override def pressStopButton (delay: Long): Unit = {

  }

  override def pressContinueButton (delay: Long): Unit = {

  }

  override def pressDoorsOpenButton (delay: Long): Unit = {

  }

  override def pressDoorsCloseButton (delay: Long): Unit = {

  }

  override def obstructDoors (delay: Long): Unit = {

  }

  override def clearDoorObstruction (delay: Long): Unit = {

  }
}
