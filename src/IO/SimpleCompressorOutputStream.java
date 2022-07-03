package IO;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

public class SimpleCompressorOutputStream extends OutputStream {
    private OutputStream out;
    private  byte curr;
    private int counter;



    public SimpleCompressorOutputStream(OutputStream out) {
        this.out = out;
        counter=0;
    }



    @Override
    public void write(int b) throws IOException {


    }
    @Override
    public void write(byte[] b) throws IOException {
        if (b.length==0)
        {
            return;
        }
        ArrayList<Byte> blist = new ArrayList<Byte>();
        int index = 0;
        byte curr=b[0];
        int i;
        for(i=0;i<6;i++)
        {
            while (curr!=-1)
            {
               //blist.add(b[index]);
                index++;
                curr=b[index];
            }
            index++;
            curr=b[index];

        }
        //index+=1;
        for(i=0;i<index;i++)
        {
            blist.add(b[i]);
        }
        int sum=0;
        curr=b[index];
        for (i=index;i<b.length;i++)
        {

            if (curr==b[i])
            {
                sum+=1;

                if (sum==127)
                {
                    blist.add((byte)127);
                    sum=1;
                    if(i<b.length-1 && b[i+1]==curr)
                    {
                        blist.add((byte)0);
                    }
                }
                if (i==b.length-1)
                {
                    blist.add((byte)sum);

                }
            }
            else
            {
                curr=b[i];
                blist.add((byte)sum);
                sum=1;

            }
        }
       // blist.add((byte)sum);

        byte[] barr = new byte[blist.size()];
        for (i=0;i<barr.length;i++)
        {
            barr[i]=blist.get(i);
        }
        out.write(barr);



    }


}
