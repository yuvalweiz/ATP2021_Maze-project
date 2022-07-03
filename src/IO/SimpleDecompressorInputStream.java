package IO;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class SimpleDecompressorInputStream extends InputStream {
    private InputStream in;
    private byte[] out;

    public SimpleDecompressorInputStream(InputStream in) {
        this.in = in;


    }

    public InputStream getIn() {
        return in;
    }

    @Override
    public int read() throws IOException{
        int returnVal = 0;
        returnVal = in.read();
        return returnVal;
    }

    public int read( byte[] arr ){
        ArrayList<Byte> mazeBytesInfo = new ArrayList<>();
        int pos=0;
        try {
            byte[] shrinked = in.readAllBytes();


            for (int i = 0; i < 6; i++) {
                while (shrinked[pos] != -1) {
                    mazeBytesInfo.add(shrinked[pos]);
                    pos++;
                }
                mazeBytesInfo.add(shrinked[pos]);
                pos++;
            }
            int curnum = shrinked[pos];
            int infoType = 0;
            for (int j = pos; j < shrinked.length; j++) {
                curnum = shrinked[pos];
                for (int i = 0; i < curnum; i++) {
                    if (infoType % 2 == 0) {
                        mazeBytesInfo.add((byte) 0);
                    } else {
                        mazeBytesInfo.add((byte) 1);
                    }
                }
                infoType++;
                pos++;
            }
            byte[] mazeinfo = new byte[mazeBytesInfo.size()];
            for (int i = 0; i < arr.length; i++) {
                if (i> mazeinfo.length-1)
                {
                    break;
                }
                arr[i] = mazeBytesInfo.get(i);
            }
        }
        catch (IOException e)
        {

        }
        return 0;
    }



}//class
