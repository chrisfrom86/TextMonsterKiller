import java.util.*;

public class Main {
  
  static Random random = new Random();
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
  static String[] gameHistory = new String[15];

  
  public static void main(String[] args) {
    clearScreen();
    clearHistory();

    ShowGuide();
  }

  public static void ShowGuide() {
    AddHistory("*******Welcome to Monster Attacker!******");
    AddHistory("");
    AddHistory("**Your level and experience are up top.**");
    AddHistory("");
    AddHistory("*Your battle text will scroll through here.*");
    AddHistory("");
    AddHistory("***Your HP and spells remaining are below.***");
    AddHistory("**Beware! You only have 5 spells per level.**");
    AddHistory("");
    AddHistory("*****Your options are at the bottom.*****");
    AddHistory("****Enter the letter and press enter.****");
    AddHistory("");
    AddHistory("");
    AddHistory("*****Type any key and press Enter to begin!*****");
    AddHistory("");

    ShowUI();

    Scanner sc = new Scanner(System.in);
    String input = sc.next();
    switch(input) {
      case "":
        Battle();
      default:
        Battle();
    }
    sc.close();
  }

  public static void Battle() {
    clearScreen();
    clearHistory();
    // System.out.println("----------------------------------------");
    AddHistory("Wandering through a forest, you're attacked");
    AddHistory("by a scary monster!");
    AddHistory("");
    AddHistory("Prepare yourself for battle!");
    AddHistory("");
    
    Scanner sc = new Scanner(System.in);
    
    while (enemyHP > 0) {
      ShowUI();

      System.out.println("Attack (a) | Fireball (f) | Potion (p)");
      String input = sc.next();
      
      switch(input) {
        case "a": 
          attack = d8(playerLvl);
          enemyHP -= attack;
          AddHistory("You hit for " + attack + ".");
          break;
        case "f":
          if(playerSpells == 0) {
              AddHistory("You have no more spells, so you attack!");
              attack = d8(playerLvl);
              enemyHP -= attack;
              AddHistory("You hit for " + attack + ".");
              break;
          } else {
              attack = CastFireball(playerLvl);
              enemyHP -= attack;
              AddHistory("Your fireball hit for " + attack + "!");
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
        AddHistory("Enemy hits you for " + attack + ".");
        if(playerHP <= 0) {
          GameRestart();
        }
      }
      
    }
    
    sc.close();
    
  }

  public static void GameRestart() {
    AddHistory("");
    AddHistory("You have died.");
    AddHistory("");
    AddHistory("Would you like to try again in a");
    AddHistory("parallel universe?");
    AddHistory("");
    ShowUI();
    ResetAllStats();
    System.out.println("Yes (y) | No (n)");
    Scanner sc = new Scanner(System.in);
    String input = sc.next();
    switch(input) {
      case "y":
        Battle();
        break;
      case "n":
        clearScreen();
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

  public static String[] PrintHistory() {
    
    for (String string : gameHistory) {
      System.out.println(string);
    }
    
    return gameHistory;
  }

  public static void clearHistory() {
    for(int i=0; i<gameHistory.length; ++i) {
      gameHistory[i] = "";
    }
  }

  public static void AddHistory(String newtext) {
      for(int i=0; i<gameHistory.length-1; ++i) {
        gameHistory[i] = gameHistory[i+1];
      }
      
      gameHistory[14] = newtext;
  }

  public static void ShowUI() {
    clearScreen();
    System.out.println("---------Monster Attacker v1.0----------");
    System.out.println("Player Level : " + playerLvl);
    ShowPlayerExpBar(playerExp);
    System.out.println("----------------------------------------");
    PrintHistory();
    System.out.println("----------------------------------------");
    showHPStats();
    System.out.println("----------------------------------------");
  }

  public static void showLevelStats() {
    clearScreen();
    System.out.println("Player Level : " + playerLvl);
    ShowPlayerExpBar(playerExp);
  }

  public static void showHPStats() {
    ShowPlayerHPBar(playerHP);
    ShowEnemyHPBar(enemyHP);
    ShowPlayerSpells(playerSpells);
  }

  public static void ShowPlayerSpells(int playerSpells) {
    System.out.print("Player Spells: ");
    switch(playerSpells) {
      case 0:
        System.out.print("X X X X X");
        break;
      case 1:
        System.out.print("O X X X X");
        break;
      case 2:
        System.out.print("O O X X X");
        break;
      case 3:
        System.out.print("O O O X X");
        break;
      case 4:
        System.out.print("O O O O X");
        break;
      case 5:
        System.out.print("O O O O O");
        break;
    }
    System.out.println("");
  }
  
  public static void ShowPlayerHPBar(int playerHP) {
    
    Double hp = playerHP*1.0 / playerMaxHP;
    int hpBar = (int) Math.round(hp*10);
    if(hpBar <= 0) {
      hpBar = 1;
    }
    
    playerHPBar[0] = "{";
    
    for(int i=0; i<hpBar; ++i) {
      playerHPBar[i+1] = "-";
    }
    
    playerHPBar[hpBar+1] = "|";
    
    for(int j=11; j>hpBar+1; --j) {
      playerHPBar[j] = " ";
    }
    
    playerHPBar[12] = "}";

    System.out.print("Player HP    : ");
    for(int q=0; q<13; ++q) {
      System.out.print(playerHPBar[q]);
    }
    System.out.print(" " + playerHP + "/" + playerMaxHP);
    System.out.println("");

  }

  public static void ShowPlayerExpBar(int playerExp) {
    
    Double exp = playerExp*1.0 / playerMaxExp;
    int expBar = (int) Math.round(exp*10);
    
    playerExpBar[0] = "{";
    
    for(int i=0; i<expBar; ++i) {
      playerExpBar[i+1] = "-";
    }
    
    playerExpBar[expBar+1] = "|";
    
    for(int j=11; j>expBar+1; --j) {
      playerExpBar[j] = " ";
    }
    
    playerExpBar[12] = "}";

    System.out.print("Player EXP   : ");
    for(int q=0; q<13; ++q) {
      System.out.print(playerExpBar[q]);
    }
    System.out.print(" " + playerExp + "/" + playerMaxExp);
    System.out.println("");

  }

  public static void ShowEnemyHPBar(int enemyHP) {
    
    Double hp = enemyHP*1.0 / enemyMaxHP;
    int hpBar = (int) Math.round(hp*10);
    if(hpBar <= 0) {
      hpBar = 1;
    }
    
    enemyHPBar[0] = "{";
    
    for(int i=0; i<hpBar; ++i) {
      enemyHPBar[i+1] = "-";
    }
    
    enemyHPBar[hpBar+1] = "|";
    
    for(int j=11; j>hpBar+1; --j) {
      enemyHPBar[j] = " ";
    }
    
    enemyHPBar[12] = "}";

    System.out.print("Enemy HP     : ");
    for(int q=0; q<13; ++q) {
      System.out.print(enemyHPBar[q]);
    }
    System.out.print(" " + enemyHP + "/" + enemyMaxHP);
    System.out.println("");

  }

  public static void enemyDefeated() {
    playerExp += 10;
    playerHP += d6(playerLvl);
    if(playerHP > playerMaxHP) {
      playerHP = playerMaxHP;
    }
    AddHistory("Enemy defeated!");
    AddHistory("");
    if(playerExp >= playerMaxExp) {
      playerLevelUp();
    }
  }
  
  public static void playerLevelUp() {
    ++playerLvl;
    AddHistory("You are now level " + playerLvl + ".");
    AddHistory("");
    playerMaxHP += d12(1);
    playerHP = playerMaxHP;
    playerExp = 0;
    playerSpells = 5;
  }

  public static void generateEnemy(int level) {
    enemyMaxHP = d10(level) + level + 3;
    enemyHP = enemyMaxHP;
    AddHistory("A new enemy appeared!");
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
    
    AddHistory("You healed for " + heal + ".");
  }

  public static void clearScreen() {  
    System.out.print("\033[H\033[2J");  
    System.out.flush();  
  }

  public static int d4(int rolls) {
    int sum = 0;

    for (int i=0; i<rolls; ++i) {
      sum += random.nextInt(4) +1;
    }
    return sum;
  }

  public static int d6(int rolls) {
    int sum = 0;

    for (int i=0; i<rolls; ++i) {
      sum += random.nextInt(6) +1;
    }
    return sum;
  }

  public static int d8(int rolls) {
    int sum = 0;

    for (int i=0; i<rolls; ++i) {
      sum += random.nextInt(8) +1;
    }
    return sum;
  }

  public static int d10(int rolls) {
    int sum = 0;

    for (int i=0; i<rolls; ++i) {
      sum += random.nextInt(10) +1;
    }
    return sum;
  }

  public static int d12(int rolls) {
    int sum = 0;

    for (int i=0; i<rolls; ++i) {
      sum += random.nextInt(12) +1;
    }
    return sum;
  }

}