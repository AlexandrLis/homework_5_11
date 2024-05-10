import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Main {
    public static void main(String[] args) {

        makeDirectory("./backup");
        copyElements(new File("./Directory"), new File("./backup"));
    }

    public static void copyElements(File oldDirectory, File newDirectory){

        int length = oldDirectory.toString().length();

        File[] files = oldDirectory.listFiles();

        for (File f : files) {

            if(f.isFile()){
                makeFile(newDirectory.getPath() + "/" + f.getPath());
                copyInfo(newDirectory.getPath() + "/" + f.getPath(), f.toString());
            }
            if(f.isDirectory()){
                String stroka = "";
                for (int i = length; i < f.getPath().length(); i++) {
                    stroka += f.getPath().charAt(i);
                }
                makeDirectory(newDirectory.getPath() + "/" + f.getPath());
                copyElements(f, newDirectory);
            }

        }
    }

    public static void makeDirectory(String path){

        try{
            File file = new File(path);
            file.mkdirs();
        }catch (Exception e){
            System.out.println("Директория не была создана");
        }
    }

    public static void makeFile(String path){
        try{
            File file = new File(path);
            file.createNewFile();
        }catch (Exception e){
            System.out.println("Файл не был создан");
        }
    }

    public static void copyInfo(String newFile, String oldFile){

        try(FileOutputStream fileOutputStream = new FileOutputStream(newFile)){
            try(FileInputStream fileInputStream = new FileInputStream(oldFile)){
                int line = 0;
                while ((line = fileInputStream.read()) != -1){
                    fileOutputStream.write((char)line);
                }
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
