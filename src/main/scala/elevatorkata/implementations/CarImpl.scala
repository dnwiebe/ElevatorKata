package elevatorkata.implementations

import elevatorkata.interfaces.{Car, Floor}

/**
  * Created by dnwiebe on 7/8/16.
  */

object CarImpl {
  class BuilderImpl (system: ElevatorSystemImpl, weightLimit: Int, floors: Array[Floor], sensor: Car.Sensor)
      extends elevatorkata.interfaces.Car.Builder {
    private var stopButton = false
    private var continueButton = false
    private var doorsOpenButton = false
    private var doorsCloseButton = false
    private var keyLockIndexes = Set[Int] ()
    private var hasInteriorDisplay = false
    private var positionSensors = Set[Double] ()

    def addStopButton (): Car.Builder = {stopButton = true; this}
    def addContinueButton (): Car.Builder = {continueButton = true; this}
    def addDoorsOpenButton (): Car.Builder = {doorsOpenButton = true; this}
    def addDoorsCloseButton (): Car.Builder = {doorsCloseButton = true; this}
    def addKeyLock (keyIdx: Int): Car.Builder = {keyLockIndexes += keyIdx; this}
    def addInteriorDisplay (): Car.Builder = {hasInteriorDisplay = true; this}
    def addPositionSensor (position: Double): Car.Builder = {positionSensors += position; this}

    def build (): CarImpl = {
      val id = system.nextId ()
      val result = new CarImpl (
        system,
        id,
        weightLimit,
        floors.map {_.asInstanceOf[FloorImpl]}.toSet,
        stopButton,
        continueButton,
        doorsOpenButton,
        doorsCloseButton,
        keyLockIndexes,
        hasInteriorDisplay,
        positionSensors,
        sensor
      )
      system.addCar (result)
      result
    }
  }
}

class CarImpl (
  system: ElevatorSystemImpl,
  val id: Int,
  val weightCapacity: Int,
  floorSet: Set[FloorImpl],
  val stopButton: Boolean,
  val continueButton: Boolean,
  val doorsOpenButton: Boolean,
  val doorsCloseButton: Boolean,
  keyLockIndexSet: Set[Int],
  hasInteriorDisplay: Boolean,
  sensorPositionSet: Set[Double],
  sensor: Car.Sensor
) extends elevatorkata.interfaces.Car {
  private var display = if (hasInteriorDisplay) "" else null

  def floorsServed: Array[Int] = floorSet.map {_.number}.toArray
  def keyLockIndexes: Array[Int] = keyLockIndexSet.toArray
  def displayText: String = display
  def sensorPositions: Array[Double] = sensorPositionSet.toArray

  def floorButtonLight (floor: Int, on: Boolean) {callSensor {_.floorButtonLight (floor, on, timestamp)}}
  def stopButtonLight (on: Boolean) {callSensor {_.stopButtonLight (on, timestamp)}}
  def continueButtonLight (on: Boolean) {callSensor {_.continueButtonLight (on, timestamp)}}
  def doorsOpenButtonLight (on: Boolean) {callSensor {_.doorsOpenButtonLight (on, timestamp)}}
  def doorsCloseButtonLight (on: Boolean) {callSensor {_.doorsCloseButtonLight (on, timestamp)}}
  def closeDoors () {callSensor {_.closeDoors (timestamp)}}
  def openDoors () {callSensor {_.openDoors (timestamp)}}
  def changeDisplay (text: String) {
    if (display == null) {return}
    val actualText = if (text == null) "" else text
    callSensor {_.changeDisplay (actualText, timestamp)}
  }
  def applyLift (g: Double, targetSpeed: Double) {callSensor {_.applyLift (g, targetSpeed, timestamp)}}
  def stopAt (position: Double) {callSensor {_.stopAt (position, timestamp)}}

  private def callSensor (closure: Car.Sensor => Unit): Unit = {
    if (sensor == null) {return}
    closure (sensor)
  }

  private def timestamp: Long = system.timestamp
}
