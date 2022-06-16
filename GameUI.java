import java.util.*;

public class GameUI {

  import Main;
  public static String[] gameHistory = new String[15];
  
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
        Main.Battle();
      default:
        Battle();
    }
    sc.close();
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

  public static void showHPStats() {
    ShowPlayerHPBar(playerHP);
    ShowEnemyHPBar(enemyHP);
    ShowPlayerSpells(playerSpells);
  }
  
  public static void showLevelStats() {
    clearScreen();
    System.out.println("Player Level : " + playerLvl);
    ShowPlayerExpBar(playerExp);
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

  public static void clearScreen() {  
    System.out.print("\033[H\033[2J");  
    System.out.flush();  
  }
  
}