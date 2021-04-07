import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

class Main {
  public static void main(String[] args) throws FileNotFoundException{
    //Scanner scan = new Scanner(new File("input2.txt"));
    Scanner scan = new Scanner(System.in);
    int cases = scan.nextInt();
    for(int i=0; i<cases; i++){
      int d = scan.nextInt(); // bomb strength/reach
      int pops = scan.nextInt(); // # of rat populations
      int[][] killed = new int[1025][1025];
      for(int j=0; j<pops; j++){
        int x = scan.nextInt();
        int y = scan.nextInt();
        int populationSize = scan.nextInt();
        // (x1, y1) and (x2, y2) are the lower left corner and upper right conner of the region, within which a bomb of the strength d can reach and kill *this* population of rats.
        int x1 = x-d; x1 = x1<0?0:x1;
        int y1 = y-d; y1 = y1<0?0:y1;
        int x2 = x+d; x2 = x2>1024?1024:x2;
        int y2 = y+d; y2 = y2>1024?1024:y2;
        for(int row=x1; row<=x2; row++){
          for(int col=y1; col<=y2; col++){
            killed[row][col] += populationSize;
          }
        }
      }
      // determin optimal bombing position
      int optimalX = 0;
      int optimalY = 0;
      int max = 0;
      for(int x=1024; x>=0; x--){
        for(int y=1024; y>=0; y--){
          if(killed[x][y] >= max){
            // remember optimal bombing position
            optimalX = x;
            optimalY = y;
            max = killed[x][y];
          }
        }
      }
      System.out.println(optimalX+" "+optimalY+" "+max);
    }
  }
}