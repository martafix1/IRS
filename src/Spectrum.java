

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

class Spectrum{
    private String filePath=new String();
    private String name=new String();
    private double data[][];
    private int length;
    
    Spectrum(String name, String filePath){
        this.filePath=filePath;
        this.name=name;
        setFile(filePath);
    }
    
    String getName(){
        return name;
    }
    
    void setName(String name){
        this.name=name;
    }
    
    public void setFile(String filePath){
        this.filePath=filePath;
        try(FileInputStream dataFile=new FileInputStream(this.filePath)){
            int i;
            char c;
            ArrayList<Double>fData=new ArrayList<Double>();
            do{
                double x; 
                String s="";
                do{
                    i=dataFile.read();
                }while((i>'9'||i<'0')&&i!='.'&&i!=(int)-1);
                while((i<='9'&&i>='0')||i=='.'){
                    c=(char)i;
                    s=s+c;
                    i=dataFile.read(); 
                }
                x=Double.parseDouble(s);
                fData.add(x);
                length++;  
            }while(i!=-1);
            data=new double[(length/2)+1][2];
            for(i=0;i<length;i++){ 
                if(i%2==0)data[i/2][0]=fData.get(i);
                else data[i/2][1]=fData.get(i);
            }
            if(length>1){
                data[length/2][0]=data[(length/2)-1][0];
                data[length/2][1]=data[(length/2)-1][1];
            }
        }catch(IOException err){System.out.println("error reading file "+filePath+": "+err);}       
    }
    
    public void printData(){
        for(int i=0;i<length/2;i++)System.out.println(data[i][0]+"   \t"+data[i][1]);
    }
    
    public double[][] getData(){
        return this.data;
    }
    
    public String getFileName(){
        return filePath;
    } 
}