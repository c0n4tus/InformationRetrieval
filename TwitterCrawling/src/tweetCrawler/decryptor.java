package tweetCrawler;

public class decryptor {

	public static void main(String[] args) {
		// TODO Auto-generated method stub gahzh zgaff irfcc fqgmx eefsp xmgab bxscy
		String[] myStringArray = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
		for(int i=0;i<=25;i++){
			System.out.print(myStringArray[i]+"\t");
		}
		System.out.println("\n");
		for(int i=0;i<=25;i++){
			System.out.print(i+"\t");
		}
		System.out.println();
		System.out.println(myStringArray[6]+myStringArray[0]+myStringArray[7]+myStringArray[25]+myStringArray[7]+myStringArray[25]+myStringArray[6]+myStringArray[0]+myStringArray[5]+myStringArray[5]+myStringArray[8]+myStringArray[17]+myStringArray[5]+myStringArray[2]+myStringArray[2]);
		for(int i=0;i<=25;i++)
		{
			System.out.println(myStringArray[(6+i)%26]+myStringArray[(0+i)%26]+myStringArray[(7+i)%26]+myStringArray[(25+i)%26]+myStringArray[(7+i)%26]+myStringArray[(25+i)%26]+myStringArray[(6+i)%26]+myStringArray[(0+i)%26]+myStringArray[(5+i)%26]+myStringArray[(5+i)%26]+myStringArray[(8+i)%26]+myStringArray[(17+i)%26]+myStringArray[(5+i)%26]+myStringArray[(2+i)%26]+myStringArray[(2+i)%26]);
		}

	}

}


