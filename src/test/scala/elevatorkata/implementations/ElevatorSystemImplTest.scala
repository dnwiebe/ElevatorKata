package elevatorkata.implementations

import elevatorkata.interfaces.ElevatorSystem
import org.scalatest.path

/**
  * Created by dnwiebe on 7/9/16.
  */
class ElevatorSystemImplTest extends path.FunSpec {
  describe ("A newly-created ElevatorSystemImpl") {
    val subject = ElevatorSystem.make ().asInstanceOf[ElevatorSystemImpl]

    it ("generates the correct sequence of IDs") {
      assert (subject.nextId () === 1)
      assert (subject.nextId () === 2)
      assert (subject.nextId () === 3)
      assert (subject.nextId () === 4)
      assert (subject.nextId () === 5)
    }

    describe ("instructed to make a floor builder") {
      val builder = subject.makeFloorBuilder (1, 0.0, null)

      describe ("which is then used to build a floor") {
        val floor = builder.build ()

        describe ("and asked to list its existing floors") {
          val result = subject.getFloors

          it ("returns the newly-created floor") {
            assert (result.contains (floor))
            assert (result.size === 1)
          }

          describe ("and given another floor") {
            val anotherFloor = subject.makeFloorBuilder (2, 10.0, null).build ()

            describe ("and asked to list its existing floors") {
              val result = subject.getFloors

              it ("returns both existing floors") {
                assert (result.contains (floor))
                assert (result.contains (anotherFloor))
                assert (result.size === 2)
              }
            }
          }
        }
      }
    }

    describe ("instructed to make a car builder") {
      val builder = subject.makeCarBuilder (0, Array (), null)

      describe ("which is then used to build a car") {
        val car = builder.build ()

        describe ("and asked to list its existing cars") {
          val result = subject.getCars

          it ("returns the newly-created car") {
            assert (result.contains (car))
            assert (result.size === 1)
          }

          describe ("and given another car") {
            val anotherCar = subject.makeCarBuilder (100, Array (), null).build ()

            describe ("and asked to list its existing cars") {
              val result = subject.getCars

              it ("returns both existing cars") {
                assert (result.contains (car))
                assert (result.contains (anotherCar))
                assert (result.size === 2)
              }
            }
          }
        }
      }
    }

    describe ("instructed to make a passenger") {
      val passenger = subject.makePassenger (1, 100)

      describe ("and asked to list its existing passengers") {
        val result = subject.getPassengers

        it ("returns the newly-created passenger") {
          assert (result.contains (passenger))
          assert (result.size === 1)
        }

        describe ("and given another passenger") {
          val anotherPassenger = subject.makePassenger (2, 200)

          describe ("and asked to list its existing passengers") {
            val result = subject.getPassengers

            it ("returns both existing passengers") {
              assert (result.contains (passenger))
              assert (result.contains (anotherPassenger))
              assert (result.size === 2)
            }
          }
        }
      }
    }
  }
}
