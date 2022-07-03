package IO;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;


public class MyCompressorOutputStream extends OutputStream {
    OutputStream out;
    public MyCompressorOutputStream(OutputStream fileOutputStream){this.out = fileOutputStream;}

    @Override
    public void write(int b) throws IOException {
        return;
    }
    public static byte ConvertDecimal(ArrayList<Byte> b){
        byte dec = 0;
        for (int i = 0;i< b.size();i++){
            if(b.get(i) == 0){
                continue;
            }
            else {
                dec += Math.pow(2,i);
            }
        }
        return dec;


    }
    public void write(byte[] b) throws IOException{
        ArrayList<Byte> ByteArr = new ArrayList<>();
        ArrayList<Byte> TempArr = new ArrayList<>();
        int flag = 0;
        int num = 8;
        int temp = 0;
        int index = 0;
        while (flag<6){
            TempArr.add(b[index]);
            if (b[index] == -1){ //count 6 -1
                  flag++;
            }
            index++;
        }
        while (flag == 6 && index<=(b.length-1)){
            ByteArr.add(b[index]);
            num--;
            if(num == 0){
               num = 8;
                    if(ByteArr.size() != 8){
                        temp = 8- ByteArr.size();
                        for (int j = 0;j<temp;j++){
                            ByteArr.add((byte) 0);
                        }
                    }
                    Collections.reverse(ByteArr);
                    TempArr.add(ConvertDecimal(ByteArr));
                    ByteArr.clear();
                }
            if (b.length-index < num){
                    num = b.length-index-1;
            }
            index++;
        }
        TempArr.add((byte)temp);
        byte[] FinalArr = new byte[TempArr.size()];
        for (int i = 0; i<TempArr.size();i++){
            FinalArr[i] = TempArr.get(i);
        }
        out.write(FinalArr);
}
}
