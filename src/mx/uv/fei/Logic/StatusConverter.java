/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.uv.fei.Logic;

import javafx.util.StringConverter;

/**
 *
 * @author yusgu
 */
public class StatusConverter extends StringConverter<Status>{

    @Override
    public String toString(Status status) {
        return status == null ? null: status.getStatus();
    }

    @Override
    public Status fromString(String string) {
        return null;
    }
    
}
