/*
 * Authors: Constantina Ioannou
 *
 */
package patmos
import chisel3._
//import Chisel.Node._

import ConstantsN._


class schtab() extends Module {
  val io = IO(new schtabIO())

  io.dir.rInfo.dc:= D 
 when (io.dir.slotCounter === Bits(0)) {
			io.dir.rInfo.up := L
			io.dir.rInfo.right := D
			io.dir.rInfo.down := D
			io.dir.rInfo.left := D
			io.dir.rInfo.local := D
		} .elsewhen (io.dir.slotCounter === Bits(1)) {			
			io.dir.rInfo.up := S
			io.dir.rInfo.right := L
			io.dir.rInfo.down := D
			io.dir.rInfo.left := D
			io.dir.rInfo.local := D
		}.elsewhen (io.dir.slotCounter === Bits(2)) {	
			io.dir.rInfo.up := D
			io.dir.rInfo.right := S
			io.dir.rInfo.down := W
			io.dir.rInfo.left := D
			io.dir.rInfo.local := D
		}.elsewhen (io.dir.slotCounter === Bits(3)) {		
			io.dir.rInfo.up := L
			io.dir.rInfo.right := W
			io.dir.rInfo.down := D
			io.dir.rInfo.left := D
			io.dir.rInfo.local := N
		}.elsewhen (io.dir.slotCounter === Bits(4)) {	
			io.dir.rInfo.up := L
			io.dir.rInfo.right := S
			io.dir.rInfo.down := D
			io.dir.rInfo.left := D
			io.dir.rInfo.local := W
		}.elsewhen (io.dir.slotCounter === Bits(5)) {	
			io.dir.rInfo.up := S
			io.dir.rInfo.right := W
			io.dir.rInfo.down := D
			io.dir.rInfo.left := L
			io.dir.rInfo.local := D
		}.elsewhen (io.dir.slotCounter === Bits(6)) {	
			io.dir.rInfo.up := E
			io.dir.rInfo.right := S
			io.dir.rInfo.down := D
			io.dir.rInfo.left := D
			io.dir.rInfo.local := W
		}.elsewhen (io.dir.slotCounter === Bits(7)) {	
			io.dir.rInfo.up := S
			io.dir.rInfo.right := L
			io.dir.rInfo.down := D
			io.dir.rInfo.left := D
			io.dir.rInfo.local := W
		}.elsewhen (io.dir.slotCounter === Bits(8)) {
			io.dir.rInfo.up := L
			io.dir.rInfo.right := W
			io.dir.rInfo.down := D
			io.dir.rInfo.left := D
			io.dir.rInfo.local := S
		}.elsewhen (io.dir.slotCounter === Bits(9)) {
			io.dir.rInfo.up := L
			io.dir.rInfo.right := D
			io.dir.rInfo.down := W
			io.dir.rInfo.left := D
			io.dir.rInfo.local := S
		}.elsewhen (io.dir.slotCounter === Bits(10)) {
			io.dir.rInfo.up := L
			io.dir.rInfo.right := S
			io.dir.rInfo.down := D
			io.dir.rInfo.left := D
			io.dir.rInfo.local := N
		}.elsewhen (io.dir.slotCounter === Bits(11)) {
			io.dir.rInfo.up := L
			io.dir.rInfo.right := D
			io.dir.rInfo.down := D
			io.dir.rInfo.left := S
			io.dir.rInfo.local := W
		}.elsewhen (io.dir.slotCounter === Bits(12)) {
			io.dir.rInfo.up := S
			io.dir.rInfo.right := L
			io.dir.rInfo.down := D
			io.dir.rInfo.left := D
			io.dir.rInfo.local := E
		}.elsewhen (io.dir.slotCounter === Bits(13)) {
			io.dir.rInfo.up := D
			io.dir.rInfo.right := W
			io.dir.rInfo.down := L
			io.dir.rInfo.left := D
			io.dir.rInfo.local := S
		}.elsewhen (io.dir.slotCounter === Bits(14)) {
			io.dir.rInfo.up := D
			io.dir.rInfo.right := D
			io.dir.rInfo.down := D
			io.dir.rInfo.left := N
			io.dir.rInfo.local := W
		}.elsewhen (io.dir.slotCounter === Bits(15)) {
			io.dir.rInfo.up := D
			io.dir.rInfo.right := L
			io.dir.rInfo.down := D
			io.dir.rInfo.left := D
			io.dir.rInfo.local := E
		}.elsewhen (io.dir.slotCounter === Bits(16)) {
			io.dir.rInfo.up := D
			io.dir.rInfo.right := D
			io.dir.rInfo.down := L
			io.dir.rInfo.left := D
			io.dir.rInfo.local := W
		}.elsewhen (io.dir.slotCounter === Bits(17)) {
			io.dir.rInfo.up := D
			io.dir.rInfo.right := D
			io.dir.rInfo.down := D
			io.dir.rInfo.left := L
			io.dir.rInfo.local := N
		}.elsewhen (io.dir.slotCounter === Bits(18)) {
			io.dir.rInfo.up := D
			io.dir.rInfo.right := D
			io.dir.rInfo.down := D
			io.dir.rInfo.left := D
			io.dir.rInfo.local := E
		} .otherwise{
			io.dir.rInfo.up := D
			io.dir.rInfo.right := D
			io.dir.rInfo.down := D
			io.dir.rInfo.left := D
			io.dir.rInfo.local := D
			io.dir.rInfo.dc := D
		}
}



