package com.higradius;
import java.util.List;
import java.util.*;


//class for json response to client side
public class Resp {
   private boolean success;
   private int total;
   private List<User>list;
   
   public Resp(boolean success,int total,List<User>list) {
	   this.success=success;
	   this.total=total;
	   this.list=list;
   }
   
}
