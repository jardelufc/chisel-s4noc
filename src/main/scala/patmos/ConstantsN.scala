/*
 * Authors: Constantina Ioannou
 *
 */
package patmos
import chisel3._
import chisel3.util._

//import Chisel.Node._

object ConstantsN {

  val DATA_WIDTH    = 32.W
  val ADDRESS_WIDTH =  4.W
  val PORTS_NUM = 6.W 

/*  constant TILE_CLK_FREQ  : positive := 100000000; */
 val TOTAL_IP_NUM  = 16
 val TOTAL_NI_SLOT_NUM = 20.W // 5 bits 
 val TOTAL_NI_NUM = 4.W // 4bits 

 val TDM_PERIOD   = 19

 /*val INPUT   = 1
 val OUTPUT   = 0
 val MYINPUT   = true
 val MYOUTPUT   = false*/

// =================
// test values for ip cores
// =================
val TEST_VALUE_1 = 5.U
val TEST_VALUE_2 = 7.U


 // val dD :: dL :: dS :: dW :: dE :: dN :: Nil = Enum(6)

 //val k :: q :: qQ :: qMsb:: sErr :: Nil = Enum(5)

 val D = 0.U(3.W) // Bits("b000")   
 val L = 1.U(3.W) // Bits("b001")   
 val S = 2.U(3.W) // Bits("b010")   
 val W = 3.U(3.W) // Bits("b011")   
 val E = 4.U(3.W) // Bits("b100")   
 val N = 5.U(3.W) // Bits("b101")   

  //constant DUAL_CLOCK_NOC : boolean  := false;

}
