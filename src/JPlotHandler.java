

/*import com.panayotis.gnuplot.JavaPlot;
import com.panayotis.gnuplot.swing.JPlot;*/

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class JPlotHandler{
    private final String filePath;
    private double data[][];

 //   private ArrayList<Spectrum> = null;



    JPlotHandler(String filePath){
        this.filePath=filePath;      
    }
    
    void readData(){//nacita doubly ze souboru. Predpoklada sudy pocet zaznamu, casem osetrim pro lichy pocet Zaznamy oddeleny cimkoliv (krom cisla a tecky)
        try(FileInputStream dataFile=new FileInputStream(this.filePath)){
            int i, length=0;
            ArrayList<Double>fData=new ArrayList<Double>();//nactu do nej cely soubor, pak data presunu do 2D pole, to vratim
            do{//jenom nacita bajty a prevadi na doubly az do konce souboru 
                int coef=1;
                boolean is=false;
                double pom,x=0; 
                do{
                    i=dataFile.read();
                }while((i>'9'||i<'0')&&i!='.'&&i!=-1);//cte, dokud neni na vstupu cislo, tecka nebo konec souboru
                while((i<='9'&&i>='0')||i=='.'){//spusti konverzi do double, dokud je na vstupu cifra nebo tecka
                    if(i=='.')is=true;
                    else if(is){
                        coef=coef*10;
                        pom=i-'0';
                        x=x+pom/coef;
                    }
                    else x=x*10+(i-48);
                    i=dataFile.read();    
                }
                fData.add(x);
                length++;    
            }while(i!=-1);
            data=new double[length/2][2];
            for(i=0;i<length;i++){ //nahraje data z listu do dvourozmerneho pole (x a y osy)
                if(i%2==0)data[i/2][0]=fData.get(i);
                else data[i/2][1]=fData.get(i);
            }
            for(i=0;i<length/2;i++)System.out.println("\n"+data[i][0]+" "+data[i][1]);
        }catch(IOException err){System.out.println("error reading file "+filePath+": "+err);}       
    }
    
    void plot(){

    }
    
    void setRange(int min,int max, String axe){//nastavi rozpeti os (minimum, maximum, nazev osy[x1/x2/y1/y2]);defaultne nastavuje spodni osu (x1)
    }
}