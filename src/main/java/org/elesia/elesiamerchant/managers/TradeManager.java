package org.elesia.elesiamerchant.managers;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Villager;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.MerchantRecipe;
import org.elesia.elesiamerchant.models.Trade;

import java.util.*;

public class TradeManager {

    public static Map<String, TradeManager> npc_list = new HashMap<>();

    private Entity entity;
    private String name;

    public TradeManager(Entity entity, String name){
        this.entity = entity;
        this.name = name;
    }

    public Inventory OwnerTradeMenu(List<Trade> trades){

        if(this.entity instanceof Villager villager){
            boolean convertible = true;
            if(trades.size() < 9){
                for(Trade trade : trades){
                    if(!trade.isConvertibleToVillagerTrade()){
                        convertible = false;
                    }
                }
            }
            if(convertible){
                List<MerchantRecipe> recipes = new ArrayList<>();
                for(Trade trade : trades){
                    recipes.add(trade.convertToVillagerTrade());
                }
                villager.setRecipes(recipes);

            }else{
                Inventory gui = Bukkit.createInventory(null, 36, "Trades");
                for(Trade trade : trades){
                    trade.convertToComplexeTrade(gui);
                }
                return gui;
            }
            return null;
        }

        return null;
    }
}
