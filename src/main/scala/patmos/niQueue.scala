package patmos

import chisel3._
import chisel3.util._
//import Chisel.Node._

import ConstantsN._


class niQueue(val length: Int) extends Module {
  val io = IO(new niIO())
  val reg_slotCount = RegInit(0.U) // (TDM_PERIOD) //(init = UInt(0, TDM_PERIOD))

  val router_tx = Wire(Bool())
  // router_tx:= false.B


// =====================================================================
// Slot Counter
// ===================================================================== 

  when(reg_slotCount === TDM_PERIOD.U) {
    reg_slotCount := 0.U
  }

  reg_slotCount := reg_slotCount + 1.U

// ===================================================================== 


   val init_phit = Wire(new phit())

   init_phit.data := 0.U
   init_phit.valid := 0.U

  val tx_dout = RegInit(init_phit) // RegInit(0.U) // (init = init_phit)
  
// =====================================================================
// qT: Queue Transmit
// =====================================================================

  val qtHead = Reg(init = UInt(0, width = log2Up(length)))
  val qtTail = Reg(init = UInt(0, width = log2Up(length)))

  val qtPhitCount = Reg(init = UInt(0, width = log2Up(length+1)))
  
  val qtInc = Wire(Bool()) 
    //  qtInc := false.B
  val qtDec = Wire(Bool())
     // qtDec := false.B

  val qtFull = Wire(Bool())
      qtFull:= (qtPhitCount === length.U)

  val qtEmpty = Wire(Bool())
      qtEmpty:=(qtPhitCount === 0.U)
val tmp = log2Up(length)
val qtTailNext = Wire(UInt(tmp.W))
  qtTailNext := Mux(qtTail === length.U, 0.U, qtTail + 1.U)

  val qtHeadNext = Wire(UInt(tmp.W))
      qtHeadNext := Mux(qtHead === length.U, 0.U, qtHead + 1.U)

// =====================================================================
// qT: Signal state of Queueu Transmit
// =====================================================================
  io.ipNI_io.ip_qtBusy:= qtFull
// =====================================================================
// Inistantiate phit - queue elements
// =====================================================================

  val qphit_init_state = Wire(new phit())
 
    qphit_init_state.data:= 0.U
   qphit_init_state.valid:= false.B

  
  val qt = Vec.fill(length) { RegInit(init = qphit_init_state) }


// =====================================================================
// Insert Element in TransmitQueue
// =====================================================================

  when (io.ipNI_io.ip_rtw && !qtFull) {
    qt(qtTail).data:= io.ipNI_io.ip_din
    qt(qtTail).valid:= true.B
    qtTail := qtTailNext
    qtInc := true.B
  }
  .otherwise{
      qtInc := false.B
  }

// =====================================================================
// Remove Element in TransmitQueue
// =====================================================================

  when(router_tx && !qtEmpty ){
      tx_dout:= qt(qtHead)
      qtHead:= qtHeadNext
      qtDec := true.B
  }.otherwise{
    tx_dout.data:= 0.U
    tx_dout.valid:= false.B
    qtDec := false.B
  }


  io.r_lc_dout:= tx_dout

  when (qtInc && !qtDec) {
    qtPhitCount := qtPhitCount + 1.U
  } 
  .elsewhen (!qtInc && qtDec) {
    qtPhitCount := qtPhitCount - 1.U
  }

  
// =====================================================================
// qr: Queue Receive
// =====================================================================

  //val qrHead = Reg(init = UInt(0, width = log2Up(length)))
  // val qrTail = Reg(init = UInt(0, width = log2Up(length)))

  val qrPhitCount = Reg(init = UInt(0, width = log2Up(length+1)))
  
  /*val qrInc = Wire(Bool()) 
      qrInc := false.B
  val qrDec = Wire(Bool())
      qrDec := false.B*/

  val qrFull = Wire(Bool())
      qrFull:= (qrPhitCount === length.U)

  //val qrEmpty = Wire(Bool())
  //    qrEmpty:=(qrPhitCount === 0.U)

  // val qrTailNext = Wire(UInt(tmp.W))
  //    qrTailNext := Mux(qrTail === length.U, 0.U, qrTail + 1.U)

  //val qrHeadNext = Wire(UInt(tmp.W))
  //    qrHeadNext := Mux(qrHead === length.U,0.U, qrHead + 1.U)

// =====================================================================
// qr: Signal state of Queueu Receive
// =====================================================================
  io.ipNI_io.ip_qrBusy:= qrFull
// =====================================================================
// Inistantiate phit - queue elements
// =====================================================================

  // val qr = Vec.fill(length) { Reg(init = qphit_init_state) }

// =====================================================================
// Insert Element in ReceiveQueue
// =====================================================================


// =====================================================================
// Remove Element in ReceiveQueue
// =====================================================================


// =======================================
//    NI - SCHEDULE 
// =======================================
//val init_dir_data = new nav(0.U,0.U)

val reg_dir_data_src = RegInit(0.U(ADDRESS_WIDTH)) //   (init = UInt(0,ADDRESS_WIDTH))
val reg_dir_data_dst = RegInit(0.U(ADDRESS_WIDTH)) //   (init = UInt(0,ADDRESS_WIDTH))


/*** ip_addr should be received from the top of transmit queue ***/
val reg_tx_dst = Reg(init = UInt(0,ADDRESS_WIDTH))
reg_tx_dst:= io.ipNI_io.ip_addr
/*** --------------------------------------------------------- ***/

// send slot counter and receive src and destination
io.dir_rdAddr:= reg_slotCount
io.dir_read:= true.B
reg_dir_data_src:= io.dir_rdData_src
reg_dir_data_dst:= io.dir_rdData_dst

  when (reg_dir_data_dst === reg_tx_dst){
    router_tx:=true.B
  }.otherwise{
    router_tx:=false.B
  }

  io.ipNI_io.ip_ack:=false.B
  io.ipNI_io.ip_dout:=0.U


//what do we do according to dir_data destination and source
//address


}
