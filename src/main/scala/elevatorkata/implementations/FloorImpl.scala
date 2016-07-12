package elevatorkata.implementations

import elevatorkata.interfaces.{Car, Floor}

/**
  * Created by dnwiebe on 7/8/16.
  */
object FloorImpl {

  class BuilderImpl (
    system: ElevatorSystemImpl,
    number: Int,
    position: Double,
    sensor: Floor.Sensor
  ) extends elevatorkata.interfaces.Floor.Builder {
    private var upButtonCarIds = system.getCars.map {_.id}.toSet
    private var downButtonCarIds = system.getCars.map {_.id}.toSet
    private var keyLockIndexes = Set[Int] ()

    def removeUpCar (carId: Int): Floor.Builder = {upButtonCarIds -= carId; this}
    def removeDownCar (carId: Int): Floor.Builder = {downButtonCarIds -= carId; this}
    def addKeyLock (keyIdx: Int): Floor.Builder = {keyLockIndexes += keyIdx; this}

    def build: FloorImpl = {
      val result = new FloorImpl (
        system,
        number,
        position,
        upButtonCarIds.toArray,
        downButtonCarIds.toArray,
        keyLockIndexes.toArray,
        sensor
      )
      system.addFloor (result)
      result
    }
  }
}

class FloorImpl (
  system: ElevatorSystemImpl,
  val number: Int,
  val position: Double,
  val upButtonCarIds: Array[Int],
  val downButtonCarIds: Array[Int],
  val keyLockIndexes: Array[Int],
  sensor: Floor.Sensor
) extends elevatorkata.interfaces.Floor {
  def upButtonLight (on: Boolean) {callSensor {_.upButtonLight (on, timestamp)}}
  def downButtonLight (on: Boolean) {callSensor {_.downButtonLight (on, system.timestamp)}}
  def arrivalUpLight (carIdx: Int, on: Boolean) {callSensor {_.arrivalUpLight (carIdx, on, system.timestamp)}}
  def arrivalDownLight (carIdx: Int, on: Boolean) {callSensor {_.arrivalDownLight (carIdx, on, system.timestamp)}}
  def arrivalBell (carIdx: Int) {callSensor {_.arrivalBell (carIdx, system.timestamp)}}

  private def callSensor (closure: Floor.Sensor => Unit): Unit = {
    if (sensor == null) {return}
    closure (sensor)
  }

  private def timestamp: Long = system.timestamp
}
