package p.com;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.text.ParseException;


public class HP {
	public static void main(String args[]) throws ParseException
	{
		List<pojo> n =readFileFromtxt("inputfile.txt");
		calculation(n);
	
		try
		{
		 error(n);	
		}
		catch(Myexception M)
		{
			System.out.println(M);
		}
		}
	
	
	public static List<pojo> readFileFromtxt(String l)
	{

	
		List<pojo> list1=new ArrayList<>();
		Path pathToFile=Paths.get(l);
		
		
		 
		 try(BufferedReader br=Files.newBufferedReader(pathToFile,StandardCharsets.US_ASCII))
		 {
				
			  String line;
			 line=br.readLine();
			 while(line!=null)
			 {
			String[] x =line.split("  ");
			pojo file=createList(x);
			list1.add(file);
				 line=br.readLine();
				 
			 }
		 }
		 catch(IOException ioe)
		 {
			 ioe.printStackTrace();
		 }
		 return list1;
	}
	private static pojo createList(String[] metadata)
	{
		String keyvalue=metadata[0];
	    String cost=metadata[1];
		String expirydate=metadata[2];
		String issuedate=metadata[3];
		String currentdate=metadata[4];
		String specialist=metadata[5];
		
		return new pojo(keyvalue,cost,expirydate,issuedate,currentdate,specialist);
	}
	public static void calculation(List<pojo> d)
	{
		List<String> list2=new ArrayList<>();
		int sum=0;
		 for(pojo a:d)
		  {
			 
			 if(list2.contains(a.getKeyvalue().replaceAll("[^A-Z]"," "))==false)
			 {
				 list2.add(a.getKeyvalue().replaceAll("[^A-Z]"," "));
				 
			 }
			 
		  }
		  for(String a:list2)
		  {
			  for(pojo p:d)
			  {
				  if(p.getKeyvalue().contains(a))
				  {
					  sum=sum+(Integer.parseInt(p.getCost()));
				  }
			  }
			  System.out.println(a+" : "+sum);
		  }
		 
		 
		  }
		  
		  public static void error(List<pojo> dl) throws Myexception, ParseException
		  {
		  for(pojo pl:dl)
		  {
			SimpleDateFormat obj=new SimpleDateFormat("dd/mm/yyyy");
			
			Date dt_1=obj.parse(pl.getExpirydate());
			Date dt_2=obj.parse(pl.getCurrentdate());
				
				if(dt_1.compareTo(dt_2)<0)
					{
						throw new Myexception("the policy "+pl.getKeyvalue()+" is Expired");
			
		  }
		  }
		
	
	}


}
