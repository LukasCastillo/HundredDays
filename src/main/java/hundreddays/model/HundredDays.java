/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package hundreddays.model;

import hundreddays.model.GameObjects.Entites.Animals.Pig;
import hundreddays.model.GameObjects.Entites.Base;
import hundreddays.model.GameObjects.Entites.Monsters.Zombie;
import hundreddays.model.GameObjects.Rock;
import hundreddays.model.Items.Item;
import hundreddays.model.Items.Weapon;

/**
 *
 * @author TAU
 */
public class HundredDays {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Scenario 1
        System.out.println("\n\nScenario 1");
        Character player = new Character("Lukas", 100, 100, 20, 10, "Swordsman", 0, 0);
        Rock rock = new Rock(8, 0, 1, 1);
        
        for(int i = 0; i < 10; i++){
            player.moveBy(1, 0);
            System.out.printf("Player moved to postion: %f, %f\n", player.getXPos(), player.getYPos());
            
            if(rock.collidesWith(new double[] {player.getXPos(), player.getYPos(), 1, 1})){
                player.moveBy(-1, 0);
                System.out.println("Plater collided!");
            }
        }
        
        
        //Scenario 2
        System.out.println("\n\nSecnario 2");
        player = new Character("Lukas", 100, 100, 20, 10, "Swordsman", 0, 0);
        Weapon sword = new Weapon("Wood Sword", 1, "Sword", 20, 30);
        Pig pig = new Pig(5, 5);
        
        player.moveBy(4, 4);
        pig.walk(); //is usually handled by pig.update()
        player.pickUpItem(sword);
        player.setSelectedItem(sword);
        player.attack(pig);
        pig.run();
        player.attack(pig);
        for(Item i : pig.getDrops()){
            player.pickUpItem(i);
            System.out.println("Player picks up: " + i.getName());
        }
        
        //scenario 3
        System.out.println("\n\nScenario 3");
        player.setXPos(0);
        player.setYPos(0);
        Zombie zombie = new Zombie(5, 5);
        
        zombie.walk();
        player.moveBy(4, 4);
        zombie.attack();
        player.setSelectedItem(sword);
        while(player.getHp() > 0 && zombie.getHp() > 0){
            player.onAttack(zombie.getBaseAttack());
            System.out.println("Zombie attacked player! Player hp: " + player.getHp());
            player.attack(zombie);
            System.out.println("Player attacked zombie! Zombie hp: " + zombie.getHp());
        }
        System.out.println("Player: " + player.getHp() + " Zombie: " + zombie.getHp());
        for(Item i : zombie.getDrops()){
            player.pickUpItem(i);
            System.out.println("Player picks up: " + i);
        }
        
        //scenario 4
        System.out.println("\n\nScenario 4");
        Base base = new Base(0, 0, "Base", 0, 10, 0, 20, 1);
        
        base.onHover(); //opens menu
        base.onUse(); //opens inventory
        for(Item i : player.getItems()){
            base.storeItem(i);
            System.out.println("Transfering item: " + i);
        }
        player.getItems().clear();
        
        System.out.println("Player:");
        System.out.println(player.getItems());
        System.out.println("Base:");
        System.out.println(base.getInventory());
        
    }
    
}
