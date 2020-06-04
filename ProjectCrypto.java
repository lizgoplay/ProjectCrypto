import java.util.*;
public class ProjectCrypto {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Input the text you wish encrypted:");
        String text = input.nextLine();
        System.out.print("Input the number you wish to shift by: ");
        int shift = input.nextInt();
        System.out.print("Input the number you wish to group by: ");
        int group = input.nextInt();

        System.out.println(encryptString(text, shift,group));
        System.out.print("Do you wish to ungroupify?(Y/N) ");
        char YorN = input.next().charAt(0);
        if (YorN == 'Y' || YorN=='y')
        { text = encryptString(text, shift,group);
          ungroupify(text);
          System.out.println(ungroupify(text));}
    }
    public static String normalizeText(String I){
        I = I.toUpperCase();
        String normalizedText = I.replaceAll("[^a-zA-Z]", "");
        return normalizedText;
    }
    public static String obify(String L){
       String obifiedText = "";
        for (int i = 0; i < L.length(); i++) {
            char ch = L.charAt(i);
            if (ch == 'A' || ch == 'e' || ch == 'I' || ch == 'O' || ch == 'U' || ch == 'Y') {
                obifiedText += "OB";
                obifiedText += L.charAt(i); }
            else {
                obifiedText += L.charAt(i); }
        }
        return obifiedText;
       }
    public static String shiftAlphabet(int shift) {
        int start = 0;
        if (shift < 0) {
            start = (int) 'Z' + shift + 1;
        } else {
            start = 'A' + shift;
        }
        String result = "";
        char currChar = (char) start;
        for(; currChar <= 'Z'; ++currChar) {
            result = result + currChar;
        }
        if(result.length() < 26) {
            for(currChar = 'A'; result.length() < 26; ++currChar) {
                result = result + currChar;
            }
        }
        return result;
    }
    public static String caesarify(String L, int shift ){
        String actualString = shiftAlphabet(0);
        String shiftString = shiftAlphabet(shift);
        String caesarifiedText = "";
        for( char ch : L.toCharArray() )
        {
            caesarifiedText += shiftString.charAt(actualString.indexOf(ch));
        }
        return caesarifiedText;
    }
    public static String groupify(String L, int group){
        String groupifiedText = "";
        int i;
        int a=0;
        for (i = 0; i < L.length(); i++) {
            char ch = L.charAt(i);
            if (a<= group)
            {groupifiedText += L.charAt(i);
            a++;}
            while (a==group)
            {groupifiedText += " ";
            a=0;}
        }
        groupifiedText+= ("x");
        return groupifiedText;
    }
    public static String encryptString(String L, int shift, int group){
        L = normalizeText(L);
        L = obify(L);
        L = caesarify(L,shift);
        return groupify(L, group);
    }
    public static String ungroupify(String L) {
        return L.replaceAll("[x\\s+ ]", "");
    }
}
