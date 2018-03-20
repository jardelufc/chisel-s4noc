package patmos

import chisel3._
import chisel3.iotesters.PeekPokeTester


class schtabTests(dut: schtab) extends PeekPokeTester(dut) {

  //poke(dut.io.ena,1)
  step(1)

  poke(dut.io.dir.slotCounter, 1)

  peek(dut.io.dir.rInfo.up)
  peek(dut.io.dir.rInfo.right)
  peek(dut.io.dir.rInfo.down)
  peek(dut.io.dir.rInfo.left)
  peek(dut.io.dir.rInfo.local)
  peek(dut.io.dir.rInfo.dc)
  step(1)



  poke(dut.io.dir.slotCounter, 8)
  peek(dut.io.dir.rInfo.up)
  peek(dut.io.dir.rInfo.right)
  peek(dut.io.dir.rInfo.down)
  peek(dut.io.dir.rInfo.left)
  peek(dut.io.dir.rInfo.local)
  peek(dut.io.dir.rInfo.dc)
  step(1)

}


