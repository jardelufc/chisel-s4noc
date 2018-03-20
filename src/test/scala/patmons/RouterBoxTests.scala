package patmos

import chisel3._
import chisel3.iotesters.PeekPokeTester


class RouterBoxTests(dut: RouterBox) extends PeekPokeTester(dut) {

  poke(dut.io.up_din.data,3)
  poke(dut.io.dn_din.data,4)
  poke(dut.io.r_din.data,5)
  poke(dut.io.le_din.data,6)
  poke(dut.io.lc_din.data,7)


  step(1)

  peek(dut.io.up_dout.data)
  peek(dut.io.dn_dout.data)
  peek(dut.io.r_dout.data)
  peek(dut.io.le_dout.data)
  peek(dut.io.lc_dout.data)

  step(50)
}
