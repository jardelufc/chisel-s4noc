// See LICENSE.txt for license details.
package patmos

import chisel3._
import chisel3.iotesters.{Driver, TesterOptionsManager}
import utils.TutorialRunner

object Launcher {
  val tests = Map(
    "NiBox" -> { (manager: TesterOptionsManager) =>
      Driver.execute(() => new NiBox(), manager) {
        (c) => new NiBoxTests(c)
      }
    },
    "router" -> { (manager: TesterOptionsManager) =>
      Driver.execute(() => new router(), manager) {
        (c) => new routerTests(c)
      }
    },
    "ip" -> { (manager: TesterOptionsManager) =>
      Driver.execute(() => new Ip(), manager) {
        (c) => new IpTests(c)
      }
    },
    "initrom0" -> { (manager: TesterOptionsManager) =>
      Driver.execute(() => new schniROM_0(), manager) {
        (c) => new schniROM_0Tests(c)
      }
    },
    "schtab" -> { (manager: TesterOptionsManager) =>
      Driver.execute(() => new schtab(), manager) {
        (c) => new schtabTests(c)
      }
    },
    "RouterBox" -> { (manager: TesterOptionsManager) =>
      Driver.execute(() => new RouterBox(), manager) {
        (c) => new RouterBoxTests(c)
      }
    },
    "niQueue" -> { (manager: TesterOptionsManager) =>
      Driver.execute(() => new niQueue(4), manager) {
        (c) => new niQueueTests(c)
      }
    }


  )

  def main(args: Array[String]): Unit = {
    TutorialRunner("patmos", tests, args)
  }
}

