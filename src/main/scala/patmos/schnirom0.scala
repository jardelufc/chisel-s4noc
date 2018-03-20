/*
 * Authors: Constantina Ioannou
 *
 */
package patmos
import chisel3._
//import Chisel.Node._

import ConstantsN._

class schniROM_0() extends Module {
  val io = IO(new niRomIO())

  /*	val id = Vec(Array( new nav(UInt(0),UInt(0)), new nav(UInt(14),UInt(0)), new  nav(UInt(9),UInt(0)),
					 new nav(UInt(11),UInt(15)), new nav(UInt(0),UInt(10)), new  nav(UInt(6),UInt(0)),
					 new nav(UInt(12),UInt(6)), new nav(UInt(13),UInt(11)), new  nav(UInt(15),UInt(9)),
					 new nav(UInt(8),UInt(4)), new nav(UInt(2),UInt(14)), new  nav(UInt(7),UInt(7)),
					 new nav(UInt(0),UInt(5)), new nav(UInt(1),UInt(8)), new  nav(UInt(4),UInt(2)),
					 new nav(UInt(3),UInt(13)), new nav(UInt(0),UInt(3)), new  nav(UInt(10),UInt(12)),
					 new nav(UInt(5),UInt(1))))
*/
 val rdAddrReg = RegInit(0.U) // Reg(next = io.dir.rdAddr)

 //val idx = VecInit(Range(0, 15).map(_.asUInt(ADDRESS_WIDTH)))
 val idx = VecInit(0.U,14.U,9.U,11.U,0.U,6.U,12.U,13.U,15.U,8.U,2.U,7.U,0.U,1.U,4.U,3.U,0.U,10.U,5.U)
 val idy = VecInit(0.U,0.U,0.U,15.U,10.U,0.U,6.U,11.U,9.U,4.U,14.U,7.U,5.U,0.U,2.U,13.U,3.U,12.U,1.U)

when (io.dir.read === true.B) {
   rdAddrReg :=io.dir.rdAddr
}

  io.dir.rdData.src := idx(rdAddrReg)
  io.dir.rdData.dst := idy(rdAddrReg)

// io.dir.rdData := new nav(UInt(0),UInt(0))


  /*	 for( i <- 0 until 19 )
        {
        	when (UInt(i) === rdAddrReg && io.dir.read === Bool(true)){
	           io.dir.rdData := id(i)
	       }
        }*/
}

