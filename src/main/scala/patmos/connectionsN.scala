/*
 * Authors: Constantina Ioannou
 *
 */
package patmos
import chisel3._
//import Chisel.Node._

import ConstantsN._


class routeDir() extends Bundle(){
   val up = UInt(3.W) // Bits( width = 3.W)
   val right = UInt(3.W) // Bits( width = 3.W )
   val down = UInt(3.W) // Bits( width = 3.W)
   val left = UInt(3.W) // Bits( width = 3.W )
   val local = UInt(3.W) // Bits( width = 3.W)
   val dc = UInt(3.W) // Bits( width = 3.W )

    override def clone() = {
      val res = new routeDir()
      res.asInstanceOf[this.type]
    }
}
// class nav(d:Int , s:Int) extends Bundle(){
class nav() extends Bundle(){
   val src = UInt(ADDRESS_WIDTH)
   val dst = UInt(ADDRESS_WIDTH)
   //src := s.U
   //dst := d.U
   override def clone() = {
      val res = new nav()
      res.asInstanceOf[this.type]
    }
    /*override def clone() = {
      val res = new nav(s,d)
      res.asInstanceOf[this.type]
    }*/
}
class phit() extends Bundle() {
   val data = UInt(DATA_WIDTH) // Bits(width=DATA_WIDTH)
   val valid = Bool()

   override def clone() = {
      val res = new phit()
      res.asInstanceOf[this.type]
    }

}

class routerSchTab extends Bundle(){
    val slotCounter = Input(UInt(TOTAL_IP_NUM.W)) // UInt(INPUT, TOTAL_IP_NUM)
    val rInfo =  Output(new routeDir())  // new routeDir().asOutput
}

class schtabIO() extends Bundle() {
  val dir = new routerSchTab()

}

class routerIO() extends Bundle() {
 
   val ena =Input(Bool()) // Bool(MYINPUT)
   val routeInfo = Flipped(new routerSchTab()) // val routeInfo = new routerSchTab().flip()
 
// =======================================
//    Signals to/from NI
// =======================================  
   val lc_din = Input(new phit()) // .asInput
   val lc_dout = Output(new phit()) // .asOutput
 

   val addr= Input(UInt(DATA_WIDTH)) // Bits(INPUT, DATA_WIDTH)
   val qFull = Input(Bool()) // Bool(MYINPUT)
   val test = Output(UInt(DATA_WIDTH)) // Bits(OUTPUT,DATA_WIDTH)


   val ready = Output(Bool()) // Bool(MYOUTPUT)
// =======================================
//    Signals to other routers 
// =======================================
   val r_din = Input(new phit()) // .asInput
   val r_dout = Output(new phit()) // .asOutput

   val le_din  = Input(new phit()) // .asInput
  val le_dout = Output(new phit()) // .asOutput


  val up_din = Input(new phit()) // .asInput
  val up_dout = Output(new phit()) // .asOutput

   val dn_din = Input(new phit()) // .asInput
  val dn_dout = Output(new phit()) // .asOutput


}


class schniNI() extends Bundle(){
  val rdAddr = Input(UInt(TOTAL_NI_SLOT_NUM)) // Bits(INPUT, TOTAL_NI_SLOT_NUM)
  val rdData = Output(new nav()) //.asOutput
  //val rdData = Output(new nav(0,0)) //.asOutput
  val read = Input(Bool()) // Bool(MYINPUT)

}
class niRomIO() extends Bundle(){
  val dir = new schniNI()
}

class ipNI() extends Bundle(){
  val ip_din = Input(UInt(DATA_WIDTH)) // Bits(INPUT, DATA_WIDTH)
  val router_tx = Input(Bool()) // Bool(MYINPUT)
  val ip_rtw = Input(Bool()) // Bool(MYINPUT)
  val ip_ack = Output(Bool()) // Bool(MYOUTPUT)
  val ip_addr = Input(UInt(ADDRESS_WIDTH)) // Bits(INPUT, ADDRESS_WIDTH)
  val ip_qtBusy = Output(Bool()) // Bool(MYOUTPUT)
  val ip_qrBusy= Output(Bool()) // Bool(MYOUTPUT)
  val ip_dout = Output(UInt(DATA_WIDTH)) // Bits(OUTPUT, DATA_WIDTH)

}
class niIO() extends Bundle(){
// =======================================
//    NI - ROUTER
// =======================================

//receive channel 
   val r_lc_din_ = Input(new phit()) // .asInput

//transmit channel
   val r_lc_dout = Output(new phit()) //.asOutput

// =======================================
//    NI - PROCESSOR
// =======================================
//receive
val ipNI_io = new ipNI()
//transmit


// =======================================
//    NI - NI - SCHEDULE
// =======================================
val dir_rdAddr = Output(UInt(TOTAL_NI_SLOT_NUM)) // Bits(OUTPUT, TOTAL_NI_SLOT_NUM)
val dir_rdData_src = Input (UInt(ADDRESS_WIDTH)) // UInt(INPUT,ADDRESS_WIDTH)
val dir_rdData_dst = Input(UInt(ADDRESS_WIDTH)) // UInt(INPUT,ADDRESS_WIDTH)
val dir_read = Output(Bool()) // Bool(MYOUTPUT)


}

class NiBoxIO() extends Bundle(){
  /* change when i create the core*/
  val core_io = new ipNI()

   val output1 = Output(new phit()) // .asOutput
}



class RouterBoxIO() extends Bundle(){

   val lc_din =Input(new phit()) // .asInput
   val lc_dout = Output(new phit()) // .asOutput


   val r_din = Input(new phit()) // .asInput
   val r_dout = Output(new phit()) // .asOutput

   val le_din  = Input(new phit()) // .asInput
   val le_dout = Output(new phit()) // .asOutput


   val up_din = Input(new phit()) // asInput
   val up_dout = Output(new phit()) // asOutput

   val dn_din = Input(new phit()) // asInput
   val dn_dout = Output(new phit()) // asOutput


}

class ipIO() extends Bundle(){

  val led1 = Output(UInt(1.W)) // UInt(OUTPUT, 1)

  val led2 = Output(UInt(1.W)) // UInt(OUTPUT, 1)

  val ipNI_io = Flipped(new ipNI()) //.flip

}


