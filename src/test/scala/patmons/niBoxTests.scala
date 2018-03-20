package patmos
import chisel3._
import chisel3.iotesters.PeekPokeTester

import ConstantsN._

class NiBoxTests(dut: NiBox) extends PeekPokeTester(dut) {

     poke(dut.io.core_io.ip_rtw,1)
     poke(dut.io.core_io.ip_din,15)
     poke(dut.io.core_io.ip_addr,14)
     peek(dut.io.output1)
     step(1)
     poke(dut.io.core_io.ip_rtw,0)
     peek(dut.io.output1)
     step(50)

}

/*
object tNiBox {
   def main(args: Array[String]): Unit = {
      chiselMainTest(args,
         () => Module(new NiBox())) {
            dut => new NiBoxTests(dut)
         }
      }
   }


*/
