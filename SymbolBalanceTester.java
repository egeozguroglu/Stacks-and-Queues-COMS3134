public class SymbolBalanceTester{
    
    public static void main(String[] args){
        
        SymbolBalance s = new SymbolBalance();
        s.setFile("TestFiles/Test1.java");
        System.out.println(s.checkFile());
        System.out.println("-----------------");
        s.setFile("TestFiles/Test2.java");
        System.out.println(s.checkFile());
        System.out.println("-----------------");
        s.setFile("TestFiles/Test3.java");
        System.out.println(s.checkFile());
        System.out.println("-----------------");
        s.setFile("TestFiles/Test4.java");
        System.out.println(s.checkFile());
        System.out.println("-----------------");
        s.setFile("TestFiles/Test5.java");
        System.out.println(s.checkFile());
        System.out.println("-----------------");
        s.setFile("TestFiles/Test6.java");
        System.out.println(s.checkFile());
        System.out.println("-----------------");          
        
    }
    
    
}