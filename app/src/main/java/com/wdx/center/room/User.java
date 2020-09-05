package com.wdx.center.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * @ Description:
 * @ Author: wdx
 * @ CreateDate: 2020/9/5 17:35
 */

/*建表     @Entity(tableName = "tb_student",//定义表名
        indices = @Index(value = {"name", "sex"}, unique = true),//定义索引
        foreignKeys = {@ForeignKey(entity = ClassEntity.class,
                parentColumns = "id",
                childColumns = "class_id")})/*/


/*建立索引
public @interface Index {
    //定义需要添加索引的字段
    String[] value();
    //定义索引的名称
    String name() default "";
    //true-设置唯一键，标识value数组中的索引字段必须是唯一的，不可重复
    boolean unique() default false;
*/

@Entity(tableName = "user")
public class User {
    @PrimaryKey
    public int uid;

    @ColumnInfo(name = "first_name")
    public String firstName;

    @ColumnInfo(name = "last_name")
    public String lastName;

    public User(int uid) {
        this.uid = uid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
