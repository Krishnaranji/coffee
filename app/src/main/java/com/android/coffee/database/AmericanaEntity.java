package com.android.coffee.database;

import androidx.fragment.app.FragmentActivity;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Coffee")
public class AmericanaEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "powder")
    private String powder;

    @ColumnInfo(name = "milk")
    private String milk;

    public AmericanaEntity(String powder,String milk)
    {
        this.powder =powder;
        this.milk = milk;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPowder() {
        return powder;
    }

    public void setPowder(String powder) {
        this.powder = powder;
    }

    public String getMilk() {
        return milk;
    }

    public void setMilk(String milk) {
        this.milk = milk;
    }
}
