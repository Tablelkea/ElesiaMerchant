package org.elesia.elesiamerchant.models;

import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.UUID;

public class Trade {

    private final List<ItemStack> inputs;
    private final List<ItemStack> outputs;

    private final boolean repeatable;
    private final boolean visibleWhenLocked;

    public Trade(List<ItemStack> inputs, List<ItemStack> outputs, boolean repeatable, boolean visibleWhenLocked){
        this.inputs = inputs;
        this.outputs = outputs;
        this.repeatable = repeatable;
        this.visibleWhenLocked = visibleWhenLocked;
    }

    public void addToNpc(Npc npc){
        npc.getTrades().add(this);
    }

    public void addToNpc(String name){
        for(Npc npc : Npc.npc_list){
            if(npc.getName().equalsIgnoreCase("name")){
                npc.getTrades().add(this);
            }
        }
    }

    public void addToNpc(UUID uuid){
        for(Npc npc : Npc.npc_list){
            if(npc.getUniqueId().equals(uuid)){
                npc.getTrades().add(this);
            }
        }
    }

    public List<ItemStack> getInputs(){
        return this.inputs;
    }

    public List<ItemStack> getOutputs(){
        return this.outputs;
    }

    public boolean isRepeatable(){
        return this.repeatable;
    }

    public boolean isVisibleWhenLocked(){
        return this.visibleWhenLocked;
    }

    public static List<ItemStack> getInputs(Trade trade){
        return trade.getInputs();
    }

    public static List<ItemStack> getOutputs(Trade trade){
        return trade.getOutputs();
    }

    public static boolean isRepeatable(Trade trade){
        return trade.isRepeatable();
    }

    public static boolean isVisibleWhenLocked(Trade trade){
        return trade.isVisibleWhenLocked();
    }

}
