package patmos

import chisel3._
import chisel3.iotesters.PeekPokeTester


class routerTests(dut: router) extends PeekPokeTester(dut) {

  poke(dut.io.up_din.data,3)
  poke(dut.io.dn_din.data,4)
  poke(dut.io.r_din.data,5)
  poke(dut.io.le_din.data,6)
  poke(dut.io.lc_din.data,7)

  expect(dut.io.routeInfo.slotCounter,0)
  //expect(dut.directions.up,3)

  step(1)

  expect(dut.io.routeInfo.slotCounter,1)

  //expect(dut.directions.up,3)
  step(1)

  expect(dut.io.routeInfo.slotCounter,2)
  //expect(dut.directions.up,3)

  step(1)
  poke(dut.io.up_din.data,3)
  poke(dut.io.dn_din.data,4)
  poke(dut.io.r_din.data,5)
  poke(dut.io.le_din.data,6)
  poke(dut.io.lc_din.data,7)

  step(1)
}
