package uni.colewe.client;

public class MyRunner {
	public static void main(String[] args){
		MyDictionary dict = new MyDictionary();
		System.out.println(dict.lookup("in", true, false, true));
		System.out.println(dict.lookup("in", true, true, true));
	}
}
