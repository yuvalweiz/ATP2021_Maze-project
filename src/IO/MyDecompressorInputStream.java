package IO;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;

public class MyDecompressorInputStream extends InputStream {
    InputStream in;

    public MyDecompressorInputStream(InputStream in) {
        this.in = in;


    }

    public static int convertByteToInt(byte b){
        return b & 0xFF;
   }
   public static byte[] convertIntToBin(int i){
        byte[] bin = new byte[8];
        for (int j = 0; j<8 ; j++){
            if (i < 0) break;
            bin[j] = (byte) (i%2);
            i = i/2;
        }
        if(bin.length < 8 ){
            for(int x = 0; x <(8-bin.length) ; x++){
                bin[x] = 0;
            }
        }
        return bin;
   }
   public static ArrayList<Byte> TransferByteArrToList(byte[] b){
        ArrayList<Byte> fin = new ArrayList<>();
        for (int i = 0 ; i<b.length; i++){
            fin.add(b[i]);
        }
        return fin;
   }

    @Override
    public int read(byte[] b) throws IOException {
        byte[] shrinked = in.readAllBytes();
        int flag = 0;
        ArrayList<Integer> TempInt = new ArrayList<>();
        ArrayList<Byte> TempByte = new ArrayList<>();
        int index = 0;
        while (flag<6){
            TempByte.add(shrinked[index]);
            if (shrinked[index] == -1){
                 flag++;
            }
            index++;
        }
        while (flag == 6 && index<=(shrinked.length-1)){
            TempInt.add(convertByteToInt(shrinked[index])); //Transfer to TempInt the
            index++;

        }

        for (int i = 0; i< TempInt.size()-2; i++){
            byte[] bin = convertIntToBin(TempInt.get(i));
            ArrayList<Byte> bin1 = TransferByteArrToList(bin);
            Collections.reverse(bin1);
            for (int j = 0; j<8;j++){
                TempByte.add(bin1.get(j));
            }
        }
        int num = TempInt.get(TempInt.size()-1);
        byte[] bin2 = convertIntToBin(TempInt.get(TempInt.size()-2));
        ArrayList<Byte> bin1 = TransferByteArrToList(bin2);
        Collections.reverse(bin1);
        if(num == 0){
            for (int j = 0; j<8;j++){
                TempByte.add(bin1.get(j));
            }
        }
        else {
            int s = bin1.size();
            for (int i = 0; i<num; i++){
                bin1.remove(s-i-1);
            }
            for (int j = 0; j<bin1.size();j++){
                TempByte.add(bin1.get(j));
            }
        }
        for (int i = 0; i<TempByte.size();i++){
            b[i] = TempByte.get(i);
        }
        return 0;
    }

    @Override
    public int read() throws IOException {
        return 0;
    }

}
