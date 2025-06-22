package org.elesia.elesiamerchant.models;

import org.bukkit.Location;

import java.util.*;

public class Npc {

    public final static List<Npc> npc_list = new ArrayList<>();

    private final String name;
    private final UUID uniqueId;
    private final Location location;
    private final List<Trade> trades;
    private boolean active;

    public Npc(String name, UUID uniqueId, Location location, List<Trade> trades, boolean active){
        this.name = name;
        this.uniqueId = uniqueId;
        this.location = location;
        this.trades = trades;
        this.active = active;
    }

    public static void assertNpcToLocation(Npc npc, Location location){
        npc.getLocation().set(location.getX(), location.getY(), location.getZ());
    }

    public static void setActive(Npc npc){
        npc.active = true;
    }

    public static void setDisable(Npc npc){
        npc.active = false;
    }

    public UUID getUniqueId(){
        return this.uniqueId;
    }

    public Location getLocation(){
        return this.location;
    }

    public List<Trade> getTrades(){
        return this.trades;
    }

    public String getName(){
        return this.name;
    }

    public boolean isActive(){
        return this.active;
    }

    public static List<Trade> getTrades(Npc npc){
        return npc.getTrades();
    }

    public static UUID getUniqueId(Npc npc){
        return npc.getUniqueId();
    }

    public static Location getLocation(Npc npc){
        return npc.getLocation();
    }

    public static String getName(Npc npc){
        return npc.getName();
    }

    public static boolean isActive(Npc npc){
        return npc.isActive();
    }

}
