import java.util.Scanner;
import java.io.File;
class FileDemo{
public static void main(String[]args){
Scanner input=new Scanner(System.in);
String s=input.nextLine();
File f1=new File(s);
System.out.println("file name:"+f1.getName());
System.out.println("path:"+f1.getPath());
System.out.println("Abs path:"+f1.getAbsolutePath());
System.out.println("parent:"+f1.getParent());
System.out.println("the file is:"+(f1.exists()?"Exists":"Does not exists"));
System.out.println("Is file:"+f1.isFile());
System.out.println("Is directory:"+f1.isDirectory());
System.out.println("Is Readable:"+f1.canRead());
System.out.println("Is  writable:"+f1.canWrite());
System.out.println("Is Absolute:"+f1.isAbsolute());
System.out.println("File Last MOdified:"+f1.lastModified());
System.out.println("FileSize:"+f1.length()+"bytes");
System.out.println("Is Hidden:"+f1.isHidden());
}
}
