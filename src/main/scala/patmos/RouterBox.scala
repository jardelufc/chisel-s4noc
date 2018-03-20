package patmos
import chisel3._
//import Chisel.Node._

import ConstantsN._

class RouterBox() extends Module {
 val io = IO(new RouterBoxIO())

 val schedule_table = Module(new schtab())
 val route = Module (new router() )



 schedule_table.io.dir <> route.io.routeInfo

 route.io.lc_din <> io.lc_din
 route.io.dn_din <> io.dn_din
 route.io.up_din <> io.up_din
 route.io.le_din <> io.le_din
 route.io.r_din <> io.r_din


 route.io.lc_dout <> io.lc_dout
 route.io.dn_dout <> io.dn_dout
 route.io.up_dout <> io.up_dout
 route.io.le_dout <> io.le_dout
 route.io.r_dout <> io.r_dout
 

}
