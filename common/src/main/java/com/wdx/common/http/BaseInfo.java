package com.wdx.common.http;

import java.io.Serializable;

/**
 * Created by admin on 2017/12/22.
 */

public class BaseInfo implements Serializable{
   public String id;

   public String getId() {
      return id;
   }

   public void setId(String id) {
      this.id = id;
   }
}
