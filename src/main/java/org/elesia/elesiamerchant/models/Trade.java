package org.elesia.elesiamerchant.models;

import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MerchantRecipe;
import org.bukkit.permissions.Permission;
import org.elesia.elesiamerchant.utils.ItemBuilder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class Trade {

    private List<@NotNull ItemStack> item_inputs;
    private @Nullable double money_input;
    private @Nullable Permission needed_permission;

    private List<@NotNull ItemStack> item_outputs;
    private @Nullable double money_outputs;
    private @Nullable Permission output_permission;

    private @NotNull int max_uses;

    public Trade(@NotNull List<@NotNull ItemStack> item_inputs, @Nullable double money_input, @Nullable Permission needed_permission, List<@NotNull ItemStack> item_outputs, @Nullable double money_outputs, @Nullable Permission output_permission, @NotNull int max_uses) {
        this.item_inputs = item_inputs;
        this.money_input = money_input;
        this.needed_permission = needed_permission;

        this.item_outputs = item_outputs;
        this.money_outputs = money_outputs;
        this.output_permission = output_permission;

        this.max_uses = max_uses;
    }

    public @NotNull List<@NotNull ItemStack> getItem_inputs() {
        return item_inputs;
    }

    public void setItem_inputs(@NotNull List<@NotNull ItemStack> item_inputs) {
        this.item_inputs = item_inputs;
    }

    public double getMoney_input() {
        return money_input;
    }

    public void setMoney_input(double money_input) {
        this.money_input = money_input;
    }

    public @Nullable Permission getNeeded_permission() {
        return needed_permission;
    }

    public void setNeeded_permission(@Nullable Permission needed_permission) {
        this.needed_permission = needed_permission;
    }

    public @NotNull List<@NotNull ItemStack> getItem_outputs() {
        return item_outputs;
    }

    public void setItem_outputs(@NotNull List<@NotNull ItemStack> item_outputs) {
        this.item_outputs = item_outputs;
    }

    public double getMoney_outputs() {
        return money_outputs;
    }

    public void setMoney_outputs(double money_outputs) {
        this.money_outputs = money_outputs;
    }

    public @Nullable Permission getOutput_permission() {
        return output_permission;
    }

    public void setOutput_permission(@Nullable Permission output_permission) {
        this.output_permission = output_permission;
    }

    public int getMax_uses() {
        return max_uses;
    }

    public void setMax_uses(int max_uses) {
        this.max_uses = max_uses;
    }

    public boolean isConvertibleToVillagerTrade(){
        return this.getItem_inputs().size() <= 2 && this.getItem_outputs().size() == 1 && this.money_input == 0 && this.money_outputs == 0 && this.needed_permission == null && this.output_permission == null;
    }

    public MerchantRecipe convertToVillagerTrade(){
        if(isConvertibleToVillagerTrade()){
            MerchantRecipe recipe = new MerchantRecipe(this.item_outputs.getFirst(), this.max_uses);
            recipe.addIngredient(this.item_inputs.getFirst());
            if(this.item_inputs.size() == 2){
                recipe.addIngredient(this.item_inputs.get(1));
            }
            return recipe;
        }

        return null;
    }

    public void convertToComplexeTrade(Inventory inventory){
        if(!this.isConvertibleToVillagerTrade()){
            for(int i = 0; i < 9; i++){
                if(inventory.getItem(i) == null){
                    double moneyRequire = this.money_input;
                    double moneyTrade = this.money_outputs;

                    List<ItemStack> inputs = this.item_inputs;
                    List<ItemStack> outputs = this.item_outputs;

                    Permission neededPermission = this.needed_permission;
                    Permission tradePermission = this.output_permission;

                    // OUT-PUT

                    if(outputs.size() > 1 || (moneyTrade > 0 && outputs.size() == 1) ){
                        inventory.setItem(i, new ItemBuilder(Material.CHEST, 1, "Outputs Content", null).toItemStack());
                    }else{
                        if(outputs.size() == 1 && moneyTrade == 0){
                            inventory.setItem(i, outputs.getFirst());
                        }else if(outputs.isEmpty() && moneyTrade > 0){
                            inventory.setItem(i, new ItemBuilder(Material.PAPER, 1, "Outputs Money:" + (int) moneyTrade, null).toItemStack());
                        }
                    }

                    // IN-PUT

                    if(inputs.size() > 2 || (inputs.size() == 2 && money_input > 0)){
                        inventory.setItem(i+27, new ItemBuilder(Material.CHEST, 1, "Inputs Content", null).toItemStack());
                    }else{
                        if(moneyTrade == 0){
                            if(inputs.size() == 1){
                                inventory.setItem(i+27, inputs.getFirst());
                            }else if(inputs.size() == 2){
                                inventory.setItem(i+27, inputs.getFirst());
                                inventory.setItem(i+18, inputs.get(1));
                            }
                        }else{
                            if(inputs.size() == 1){
                                inventory.setItem(i+27, inputs.getFirst());
                                inventory.setItem(i+18, new ItemBuilder(Material.PAPER, 1, "Outputs Money:" + (int) moneyTrade, null).toItemStack());
                            }else if(inputs.size() == 2){
                                inventory.setItem(i+27, new ItemBuilder(Material.CHEST, 1, "Inputs Content", null).toItemStack());
                            }
                        }
                    }

                }
            }
        }
    }
}
