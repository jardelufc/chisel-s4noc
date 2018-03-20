package patmos
import chisel3._
//import Chisel.Node._

import ConstantsN._

class Ip() extends Module {
 val io = IO(new ipIO())

      io.ipNI_io.ip_din:= 0.U // Bits(0)
      io.ipNI_io.ip_addr:= 0.U // Bits(0)
      io.ipNI_io.ip_rtw:= true.B // Bool(true)
      io.led1:= 0.U // UInt(0) 
      io.led2:= 0.U // UInt(0)

      io.ipNI_io.router_tx:= false.B // Bool(false) 
 val transmit = Wire(Bool())
     transmit := !(io.ipNI_io.ip_qtBusy)

 val CNT_MAX = UInt(20000000 / 2 - 1);

  val cntReg = Reg(init = UInt(0, 25))
  val blkReg = Reg(init = UInt(0, 1))

  cntReg := cntReg + 1.U // UInt(1)
  when(cntReg === CNT_MAX) {
    cntReg := 0.U // UInt(0)
    blkReg := ~blkReg
    when (transmit){
      io.ipNI_io.ip_din:= TEST_VALUE_1
      io.ipNI_io.router_tx:= true.B // Bool(true) 
      io.ipNI_io.ip_addr:= 5.U // Bits(5) // suppose to change
    }
  }
//  io.led1 := blkReg

  when ( io.ipNI_io.ip_dout === TEST_VALUE_1 ){
    io.led1:=blkReg
    io.led2:= 0.U // UInt(0)

  }.elsewhen (io.ipNI_io.ip_dout === TEST_VALUE_2) {
    io.led1:= 0.U // UInt(0)
    io.led2:=  ~blkReg
  }.otherwise{
    io.led1:= 0.U // UInt(0)
    io.led2:= 0.U // UInt(0)
  }




}






 
