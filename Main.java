import java.util.*;

public class Main {
  
  static Random random = new Random();
  Dice dice = new Dice();
  GameUI gameUI = new GameUI();
  public static int attack;
  public static int playerLvl = 1;
  public static int playerMaxHP = 12;
  public static int playerHP = 12;
  public static String[] playerHPBar = new String[13];
  public static int playerExp = 0;
  public static final int playerMaxExp = 100;
  public static int playerSpells = 5;
  public static final int playerMaxSpells = 5;
  public static String[] playerExpBar = new String[13];
  public static int heal = 0;
  public static int enemyHP = 10;
  public static int enemyMaxHP = 10;
  public static String[] enemyHPBar = new String[13];

  
  public static void main(String[] args) {
    gameUI.clearScreen();
    gameUI.clearHistory();

    gameUI.ShowGuide();
  }

  public static void Battle() {
    gameUI.clearScreen();
    gameUI.clearHistory();
    gameUI.AddHistory("Wandering through a forest, you're attacked");
    gameUI.AddHistory("by a scary monster!");
    gameUI.AddHistory("");
    gameUI.AddHistory("Prepare yourself for battle!");
    gameUI.AddHistory("");
    
    Scanner sc = new Scanner(System.in);
    
    while (enemyHP > 0) {
      gameUI.ShowUI();

      System.out.println("Attack (a) | Fireball (f) | Potion (p)");
      String input = sc.next();
      
      switch(input) {
        case "a": 
          attack = d8(playerLvl);
          enemyHP -= attack;
          gameUI.AddHistory("You hit for " + attack + ".");
          break;
        case "f":
          if(playerSpells == 0) {
              gameUI.AddHistory("You have no more spells, so you attack!");
              attack = d8(playerLvl);
              enemyHP -= attack;
              gameUI.AddHistory("You hit for " + attack + ".");
              break;
          } else {
              attack = CastFireball(playerLvl);
              enemyHP -= attack;
              gameUI.AddHistory("Your fireball hit for " + attack + "!");
              break;
          }
        case "p": 
          HealPotion(playerLvl);
          break;
        default: 
          System.out.println("Enter 'a' to  attack or 'p' to use a potion.");
      }
      
      if (enemyHP <= 0) {
        enemyDefeated();
        generateEnemy(playerLvl);
      } else {
        attack = d6(playerLvl);
        playerHP -= attack;
        gameUI.AddHistory("Enemy hits you for " + attack + ".");
        if(playerHP <= 0) {
          GameRestart();
        }
      }
      
    }
    
    sc.close();
    
  }

  public static void GameRestart() {
    gameUI.AddHistory("");
    gameUI.AddHistory("You have died.");
    gameUI.AddHistory("");
    gameUI.AddHistory("Would you like to try again in a");
    gameUI.AddHistory("parallel universe?");
    gameUI.AddHistory("");
    gameUI.ShowUI();
    ResetAllStats();
    System.out.println("Yes (y) | No (n)");
    Scanner sc = new Scanner(System.in);
    String input = sc.next();
    switch(input) {
      case "y":
        Battle();
        break;
      case "n":
        gameUI.clearScreen();
        System.out.println("Thanks for playing! -Chris :)\n");
        System.out.println("               .---.");
        System.out.println("     |________/     \\________");
        System.out.println("()===|_______( () () )_______>");
        System.out.println("     |        \\  M  /");
        System.out.println("               |HHH|"); 
        System.out.println("               `---'");
        System.exit(0);
        break;
      default:
        Battle();
    }
    sc.close();
  }

  public static void ResetAllStats() {
    playerLvl = 1;
    playerMaxHP = 12;
    playerHP = 12;
    playerExp = 0;
    enemyHP = 10;
    enemyMaxHP = 10;
  }

  public static void AddHistory(String newtext) {
      for(int i=0; i<gameHistory.length-1; ++i) {
        gameHistory[i] = gameHistory[i+1];
      }
      
      gameHistory[14] = newtext;
  }

  public static void enemyDefeated() {
    playerExp += 10;
    playerHP += d6(playerLvl);
    if(playerHP > playerMaxHP) {
      playerHP = playerMaxHP;
    }
    gameUI.AddHistory("Enemy defeated!");
    gameUI.AddHistory("");
    if(playerExp >= playerMaxExp) {
      playerLevelUp();
    }
  }
  
  public static void playerLevelUp() {
    ++playerLvl;
    gameUI.AddHistory("You are now level " + playerLvl + ".");
    gameUI.AddHistory("");
    playerMaxHP += d12(1);
    playerHP = playerMaxHP;
    playerExp = 0;
    playerSpells = 5;
  }

  public static void generateEnemy(int level) {
    enemyMaxHP = d10(level) + level + 3;
    enemyHP = enemyMaxHP;
    gameUI.AddHistory("A new enemy appeared!");
  }

  public static int CastFireball(int level) {
    --playerSpells;
    attack = d10(level) + level + 5;
    return attack;
  }

  public static void HealPotion(int level) {
    heal = d10(level) + level + 3;
    playerHP += heal;
    
    if(playerHP > playerMaxHP)
      playerHP = playerMaxHP;
    
    gameUI.AddHistory("You healed for " + heal + ".");
  }





}