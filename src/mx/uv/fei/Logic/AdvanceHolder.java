/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.uv.fei.Logic;

/**
 *
 * @author yusgu
 */
public final class AdvanceHolder {
    private Advance advance;
    
    private final static AdvanceHolder INSTANCE = new AdvanceHolder();
    
    private AdvanceHolder(){}
    
    public static AdvanceHolder getInstance(){
        return INSTANCE;
    }
    
    public void setAdvance(Advance advance){
        this.advance = advance;
    }
    
    public Advance getAdvance(){
        return this.advance;
    }
}
