import java.io.File;

public interface Decoy {

    default String getPathToDecoy() {
        File aux = new File("data/decoy.txt");
        String absolut = aux.getAbsolutePath();
        StringBuilder sb = new StringBuilder(absolut);
        sb.deleteCharAt(sb.length()-1);
        sb.deleteCharAt(sb.length()-1);
        sb.deleteCharAt(sb.length()-1);
        sb.deleteCharAt(sb.length()-1);
        sb.deleteCharAt(sb.length()-1);
        sb.deleteCharAt(sb.length()-1);
        sb.deleteCharAt(sb.length()-1);
        sb.deleteCharAt(sb.length()-1);
        sb.deleteCharAt(sb.length()-1);
        return String.valueOf(sb);
    }

    default Boolean pertenceInternos(String input,String path){
        File directory = new File(path);
        File[] contents = directory.listFiles();
        if(contents!=null) {
            System.out.println("Lista de estados guardados interiormente no programa:\n");
            for (File f : contents) {
                if(f.getName().equals(input)) {
                    return true;
                }
            }
        }

        return false;
    }
}