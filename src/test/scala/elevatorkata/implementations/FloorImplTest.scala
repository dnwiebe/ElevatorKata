package elevatorkata.implementations

import elevatorkata.interfaces.{Car, Floor}
import org.mockito.Matchers._
import org.mockito.Mockito._
import org.scalatest.path

/**
  * Created by dnwiebe on 7/10/16.
  */
class FloorImplTest extends path.FunSpec {
  val system = mock (classOf [ElevatorSystemImpl])
  when (system.timestamp).thenReturn (1234L)
  val cars = (1 to 4).map {id =>
    val car = mock (classOf[Car])
    when (car.id).thenReturn (id)
    car
  }.toArray
  when (system.getCars).thenReturn (cars)

  describe ("One FloorImpl built by the builder") {
    val sensor = mock (classOf[Floor.Sensor])
    val subject = new FloorImpl.BuilderImpl (system, 13, 128.5, sensor)
      .removeUpCar (2)
      .removeDownCar (3)
      .removeDownCar (4)
      .addKeyLock (5)
      .addKeyLock (8)
      .build ()

    it ("has the attributes it should") {
      assert (subject.number === 13)
      assert (subject.position === 128.5)
      assert (subject.upButtonCarIds.contains (1))
      assert (subject.upButtonCarIds.contains (3))
      assert (subject.upButtonCarIds.contains (4))
      assert (subject.upButtonCarIds.length === 3)
      assert (subject.downButtonCarIds.contains (1))
      assert (subject.downButtonCarIds.contains (2))
      assert (subject.downButtonCarIds.length === 2)
      assert (subject.keyLockIndexes.contains (5))
      assert (subject.keyLockIndexes.contains (8))
      assert (subject.keyLockIndexes.length === 2)
    }

    it ("says its sensor hasn't experienced anything") {
      verify (sensor, never ()).arrivalBell (anyInt (), anyLong ())
      verify (sensor, never ()).arrivalDownLight (anyInt (), anyBoolean (), anyLong ())
      verify (sensor, never ()).arrivalUpLight (anyInt (), anyBoolean (), anyLong ())
      verify (sensor, never ()).downButtonLight (anyBoolean (), anyLong ())
      verify (sensor, never ()).downButtonPressed (anyLong ())
      verify (sensor, never ()).keyInserted (anyInt (), anyLong ())
      verify (sensor, never ()).keyRemoved (anyInt (), anyLong ())
      verify (sensor, never ()).upButtonLight (anyBoolean (), anyLong ())
      verify (sensor, never ()).upButtonPressed (anyLong ())
    }

    describe ("put through its paces") {
      subject.arrivalBell (3)
      subject.arrivalDownLight (1, true)
      subject.arrivalDownLight (2, false)
      subject.arrivalUpLight (1, true)
      subject.arrivalUpLight (2, false)
      subject.downButtonLight (true)
      subject.downButtonLight (false)
      subject.upButtonLight (true)
      subject.upButtonLight (false)

      it ("records those paces on its sensor") {
        verify (sensor).arrivalBell (3, 1234L)
        verify (sensor).arrivalDownLight (1, true, 1234L)
        verify (sensor).arrivalDownLight (2, false, 1234L)
        verify (sensor).arrivalUpLight (1, true, 1234L)
        verify (sensor).arrivalUpLight (2, false, 1234L)
        verify (sensor).downButtonLight (true, 1234L)
        verify (sensor).downButtonLight (false, 1234L)
        verify (sensor).upButtonLight (true, 1234L)
        verify (sensor).upButtonLight (false, 1234L)
      }
    }
  }

  describe ("Another FloorImpl built by the builder without a sensor") {
    val subject = new FloorImpl.BuilderImpl (system, 1, 0.0, null)
      .build ()

    it ("has the attributes it should") {
      assert (subject.number === 1)
      assert (subject.upButtonCarIds === Array (1, 2, 3, 4))
      assert (subject.downButtonCarIds === Array (1, 2, 3, 4))
      assert (subject.keyLockIndexes.isEmpty)
    }

    describe ("put through its paces") {
      subject.arrivalBell (3)
      subject.arrivalDownLight (2, true)
      subject.arrivalDownLight (2, false)
      subject.arrivalUpLight (1, true)
      subject.arrivalUpLight (1, false)
      subject.downButtonLight (true)
      subject.downButtonLight (false)
      subject.upButtonLight (true)
      subject.upButtonLight (false)

      it ("doesn't complain about the lack of a sensor") {
        // if we get here, the test passes
      }
    }
  }
}
