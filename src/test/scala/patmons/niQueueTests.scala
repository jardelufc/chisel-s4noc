package patmos

import chisel3._
import chisel3.iotesters.PeekPokeTester


class niQueueTests(dut: niQueue) extends PeekPokeTester(dut) {

  // ====================================
  // check that queue logic works
  // ====================================
  poke(dut.io.ipNI_io.ip_rtw,1)
  poke(dut.io.ipNI_io.ip_din,5)

  poke(dut.io.ipNI_io.ip_addr,5)
  poke(dut.io.dir_rdData_dst,0)

  //expect(dut.qtPhitCount,0) 
  step(1)
  poke(dut.io.ipNI_io.ip_rtw,1)
  poke(dut.io.ipNI_io.ip_din,4)

  //expect(dut.qtPhitCount,1) 
  // poke(dut.io.ipNI_io.ip_addr,3)
  step(1)
  poke(dut.io.ipNI_io.ip_rtw,1)
  poke(dut.io.ipNI_io.ip_din,2)
  //expect(dut.qtPhitCount,2)  
  step(1)
  poke(dut.io.dir_rdData_dst,5)
  //val reg_valid = Reg(init = Bits(0,1))
  
  //expect(dut.qtPhitCount,3)  

  peek(dut.io.r_lc_dout)
  //peek(dut.tx_dout)
  poke(dut.io.ipNI_io.ip_addr,5)
  poke(dut.io.ipNI_io.ip_rtw,0)
  step(1)

  poke(dut.io.ipNI_io.ip_addr,2)
  //expect(dut.qtPhitCount,3)
     
  peek(dut.io.r_lc_dout)

  //peek(dut.tx_dout)
  expect(dut.io.ipNI_io.ip_qtBusy,0)
  step(1)

  poke(dut.io.ipNI_io.ip_addr,5)

  peek(dut.io.r_lc_dout)

  //peek(dut.tx_dout)
  //expect(dut.qtPhitCount,2)
  expect(dut.io.ipNI_io.ip_qtBusy,0)
  step(100)
}
