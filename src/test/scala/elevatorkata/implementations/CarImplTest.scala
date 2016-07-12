package elevatorkata.implementations

import elevatorkata.interfaces.{Car, Floor}
import org.scalatest.path
import org.mockito.Mockito._
import org.mockito.Matchers._

/**
  * Created by dnwiebe on 7/9/16.
  */
class CarImplTest extends path.FunSpec {
  val system = mock (classOf [ElevatorSystemImpl])
  when (system.nextId ()).thenReturn (1)
  when (system.timestamp).thenReturn (1234L)

  describe ("One CarImpl built by the builder") {
    val oneFloor = mock (classOf[FloorImpl]); when (oneFloor.number).thenReturn (123)
    val anotherFloor = mock (classOf[FloorImpl]); when (anotherFloor.number).thenReturn (234)
    val floors: Array[Floor] = Array (oneFloor, anotherFloor)
    val sensor = mock (classOf[Car.Sensor])
    val subject = new CarImpl.BuilderImpl (system, 3500, floors, sensor)
      .addContinueButton ()
      .addStopButton ()
      .addDoorsCloseButton ()
      .addDoorsOpenButton ()
      .addKeyLock (14)
      .addKeyLock (14)
      .addKeyLock (12)
      .addInteriorDisplay ()
      .addPositionSensor (3.25)
      .addPositionSensor (325)
      .addPositionSensor (325)
      .build ()

    it ("has the attributes it should") {
      assert (subject.id === 1)
      assert (subject.weightCapacity === 3500)
      assert (subject.floorsServed.contains (123))
      assert (subject.floorsServed.contains (234))
      assert (subject.floorsServed.length === 2)
      assert (subject.keyLockIndexes.contains (14))
      assert (subject.keyLockIndexes.contains (12))
      assert (subject.keyLockIndexes.length === 2)
      assert (subject.displayText === "")
      assert (subject.sensorPositions.contains (3.25))
      assert (subject.sensorPositions.contains (325))
      assert (subject.sensorPositions.length === 2)
    }

    it ("says its sensor hasn't experienced anything") {
      verify (sensor, never ()).floorButtonPressed (anyInt (), anyLong ())
      verify (sensor, never ()).stopButtonPressed (anyLong ())
      verify (sensor, never ()).continueButtonPressed (anyLong ())
      verify (sensor, never ()).doorsOpenButtonPressed (anyLong ())
      verify (sensor, never ()).doorsCloseButtonPressed (anyLong ())
      verify (sensor, never ()).doorsOpen (anyLong ())
      verify (sensor, never ()).doorsClosed (anyLong ())
      verify (sensor, never ()).obstructionDetected (anyLong ())
      verify (sensor, never ()).obstructionRemoved (anyLong ())
      verify (sensor, never ()).keyInserted (anyInt (), anyLong ())
      verify (sensor, never ()).keyRemoved (anyInt (), anyLong ())
      verify (sensor, never ()).positionHit (anyDouble (), anyLong ())
      verify (sensor, never ()).weightChanged (anyInt (), anyLong ())

      verify (sensor, never ()).floorButtonLight (anyInt(), anyBoolean (), anyLong ())
      verify (sensor, never ()).stopButtonLight (anyBoolean (), anyLong ())
      verify (sensor, never ()).continueButtonLight (anyBoolean (), anyLong ())
      verify (sensor, never ()).doorsOpenButtonLight (anyBoolean (), anyLong ())
      verify (sensor, never ()).doorsCloseButtonLight (anyBoolean (), anyLong ())
      verify (sensor, never ()).closeDoors (anyLong ())
      verify (sensor, never ()).openDoors (anyLong ())
      verify (sensor, never ()).changeDisplay (anyString (), anyLong ())
      verify (sensor, never ()).applyLift (anyDouble (), anyDouble (), anyLong ())
      verify (sensor, never ()).stopAt (anyDouble (), anyLong ())
    }

    describe ("put through its paces") {
      subject.floorButtonLight (12, true)
      subject.floorButtonLight (13, false)
      subject.stopButtonLight (false)
      subject.stopButtonLight (true)
      subject.continueButtonLight (true)
      subject.continueButtonLight (false)
      subject.doorsOpenButtonLight (false)
      subject.doorsOpenButtonLight (true)
      subject.doorsCloseButtonLight (true)
      subject.doorsCloseButtonLight (false)
      subject.closeDoors ()
      subject.openDoors ()
      subject.changeDisplay ("booga")
      subject.applyLift (123.25, 234.75)
      subject.stopAt (145.5)

      it ("records those paces on its sensor") {
        verify (sensor).floorButtonLight (12, true, 1234L)
        verify (sensor).floorButtonLight (13, false, 1234L)
        verify (sensor).stopButtonLight (true, 1234L)
        verify (sensor).stopButtonLight (false, 1234L)
        verify (sensor).continueButtonLight (true, 1234L)
        verify (sensor).continueButtonLight (false, 1234L)
        verify (sensor).doorsOpenButtonLight (true, 1234L)
        verify (sensor).doorsOpenButtonLight (false, 1234L)
        verify (sensor).doorsCloseButtonLight (true, 1234L)
        verify (sensor).doorsCloseButtonLight (false, 1234L)
        verify (sensor).closeDoors (1234L)
        verify (sensor).openDoors (1234L)
        verify (sensor).changeDisplay ("booga", 1234L)
        verify (sensor).applyLift (123.25, 234.75, 1234L)
        verify (sensor).stopAt (145.5, 1234L)
      }
    }

    describe ("instructed to set its display to null (missing") {
      subject.changeDisplay (null)

      it ("actually changes it to empty-string instead") {
        assert (subject.displayText === "")
      }

      it ("tells its sensor that the display has changed to empty-string") {
        verify (sensor).changeDisplay ("", 1234L)
      }
    }
  }

  describe ("Another CarImpl built by the builder without a sensor") {
    val subject = new CarImpl.BuilderImpl (system, 2500, Array (), null)
      .build ()

    it ("has the attributes it should") {
      assert (subject.id === 1)
      assert (subject.weightCapacity === 2500)
      assert (subject.floorsServed.isEmpty)
      assert (subject.keyLockIndexes.isEmpty)
      assert (subject.displayText === null)
      assert (subject.sensorPositions.isEmpty)
    }

    describe ("put through its paces") {
      subject.floorButtonLight (12, true)
      subject.floorButtonLight (13, false)
      subject.stopButtonLight (false)
      subject.stopButtonLight (true)
      subject.continueButtonLight (true)
      subject.continueButtonLight (false)
      subject.doorsOpenButtonLight (false)
      subject.doorsOpenButtonLight (true)
      subject.doorsCloseButtonLight (true)
      subject.doorsCloseButtonLight (false)
      subject.closeDoors ()
      subject.openDoors ()
      subject.changeDisplay ("booga")
      subject.applyLift (123.25, 234.75)
      subject.stopAt (145.5)

      it ("doesn't complain about the lack of a sensor") {
        // if we get here, the test passes
      }

      it ("shows null as its display text") {
        assert (subject.displayText === null)
      }
    }
  }
}
