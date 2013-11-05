/*
 * Class Stream
 *
 * Version 1.0
 *
 * Wednesday, June 04, 2008
 *
 * Created by Jagex
 */

package net.com.codeusa.util;

public class Stream {
    private static int[] bitMaskOut = new int[32];
    static {
        for (int i = 0; i < 32; i++) {
            bitMaskOut[i] = (1 << i) - 1;
        }
    }
    public int inOffset = 0;
    public int outOffset = 0;
    public int bitPosition = 0;
    public byte[] inBuffer;
    public byte[] outBuffer;
    private int frameStackPtr = -1;
    private int frameStackSize = 10;
    private int[] frameStack = new int[frameStackSize];

    /*
     * Constructs a new stream class with the specified capacities.
     */
    public Stream(int inCapacity, int outCapacity) {
        inBuffer = new byte[inCapacity];
        outBuffer = new byte[outCapacity];
    }

    public void expandOutBuffer() {
        /*
         * Expand the buffer if its too small by adding 1000 more byes worth of space.
         */
        byte[] oldBuffer = outBuffer;
        outBuffer = new byte[oldBuffer.length + 1000];
        System.arraycopy(oldBuffer, 0, outBuffer, 0, oldBuffer.length);
    }

    public byte readSignedByte() {
        return (byte) (inBuffer[inOffset++]);
    }

    public void writeByte(int i) {
        if (outOffset >= outBuffer.length) {
            expandOutBuffer();
        }
        outBuffer[outOffset++] = (byte) i;
    }
    
    public long readBigQWord() {
    	long l = (long) readDWordBigEndian();
        long l1 = (long) readDWordBigEndian();
        
        if(l1<0) {
        	l1 = l1 & 0xffffffffL;
        }

        return (l  << -41780448) + l1;
    }

    public void writeRShort(int i) {
        writeByte(i);
        writeByte(i >> 8);
    }

    public int readUnsignedByte() {
        return (readSignedByte() & 0xff);
    }

    public byte readSignedByteA() {
        return (byte) (readSignedByte() - 128);
    }

    public byte readSignedByteC() {
        return (byte) (-readSignedByte());
    }

    public byte readSignedByteS() {
        return (byte) (128 - readSignedByte());
    }

    public int readUnsignedByteA() {
        return (readUnsignedByte() - 128 & 0xff);
    }

    public int readUnsignedByteC() {
        return -(readUnsignedByte() & 0xff);
    }

    public int readUnsignedByteS() {
        return (128 - readUnsignedByte() & 0xff);
    }

    public void readSignedBytes(byte abyte0[], int i, int j) {
        for (int k = j; k < j + i; k++) {
            abyte0[k] = readSignedByte();
        }
    }

    public void readSignedBytes_reverse(byte abyte0[], int i, int j) {
        for (int k = (j + i) - 1; k >= j; k--) {
            abyte0[k] = readSignedByte();
        }
    }

    public void readSignedBytes_reverseA(byte abyte0[], int i, int j) {
        for (int k = (j + i) - 1; k >= j; k--) {
            abyte0[k] = readSignedByteA();
        }
    }

    public int readSignedWordBigEndian() {
        inOffset += 2;
        int i = ((inBuffer[inOffset - 1] & 0xff) << 8) + (inBuffer[inOffset - 2] & 0xff);
        if (i > 32767) {
            i -= 0x10000;
        }
        return i;
    }

    public int readSignedWordA() {
        inOffset += 2;
        int i = ((inBuffer[inOffset - 2] & 0xff) << 8) + (inBuffer[inOffset - 1] - 128 & 0xff);
        if (i > 32767) {
            i -= 0x10000;
        }
        return i;
    }

    public int readSignedWordBigEndianA() {
        inOffset += 2;
        int i = ((inBuffer[inOffset - 1] & 0xff) << 8) + (inBuffer[inOffset - 2] - 128 & 0xff);
        if (i > 32767) {
            i -= 0x10000;
        }
        return i;
    }

    public int readUnsignedWordBigEndian() {
        inOffset += 2;
        return ((inBuffer[inOffset - 1] & 0xff) << 8) + (inBuffer[inOffset - 2] & 0xff);
    }

    public int readDWordBigEndian() {
	return (inBuffer[inOffset++] & 0xff)
	    | ((inBuffer[inOffset++] & 0xff) << 1279827400)
	    | ((inBuffer[inOffset++] & 0xff) << -882770384)
	    | ((inBuffer[inOffset++] & 0xff) << 1432242680);
    }

    public int readUnsignedWord() {
        inOffset += 2;
        return ((inBuffer[inOffset - 2] & 0xff) << 8) + (inBuffer[inOffset - 1] & 0xff);
    }

    public int readUnsignedWordA() {
        inOffset += 2;
        return ((inBuffer[inOffset - 2] & 0xff) << 8) + (inBuffer[inOffset - 1] - 128 & 0xff);
    }

    public int readUnsignedWordBigEndianA() {
        inOffset += 2;
        return ((inBuffer[inOffset - 1] & 0xff) << 8) + (inBuffer[inOffset - 2] - 128 & 0xff);
    }

    public int readDWord_v1() {
        inOffset += 4;
        return ((inBuffer[inOffset - 2] & 0xff) << 24) + ((inBuffer[inOffset - 1] & 0xff) << 16) + ((inBuffer[inOffset - 4] & 0xff) << 8) + (inBuffer[inOffset - 3] & 0xff);
    }

    public int readDWord_v2() {
        inOffset += 4;
        return ((inBuffer[inOffset - 3] & 0xff) << 24) + ((inBuffer[inOffset - 4] & 0xff) << 16) + ((inBuffer[inOffset - 1] & 0xff) << 8) + (inBuffer[inOffset - 2] & 0xff);
    }

    public int readSignedWord() {
        inOffset += 2;
        int i = ((inBuffer[inOffset - 2] & 0xff) << 8) + (inBuffer[inOffset - 1] & 0xff);
        if (i > 32767) {
            i -= 0x10000;
        }
        return i;
    }

    public int readDWord() {
        inOffset += 4;
        return ((inBuffer[inOffset - 4] & 0xff) << 24) + ((inBuffer[inOffset - 3] & 0xff) << 16) + ((inBuffer[inOffset - 2] & 0xff) << 8) + (inBuffer[inOffset - 1] & 0xff);
    }

    public long readQWord() {
        long l = (long)readDWord() & 0xffffffffL;
        long l1 = (long)readDWord() & 0xffffffffL;
        return (l << 32) + l1;
    }

    public long readQWord2() {
        inOffset += 8;
        return (((inBuffer[inOffset - 8] & 0xff) << 56) + ((inBuffer[inOffset - 7] & 0xff) << 48) + ((inBuffer[inOffset - 6] & 0xff) << 40) + ((inBuffer[inOffset - 5] & 0xff) << 32) + ((inBuffer[inOffset - 4] & 0xff) << 24) + ((inBuffer[inOffset - 3] & 0xff) << 16) + ((inBuffer[inOffset - 2] & 0xff) << 8) + (inBuffer[inOffset - 1] & 0xff));
    }

    public String readString() {
        StringBuffer sb = new StringBuffer();
        byte b;
        while ((b = readSignedByte()) != 0)
        sb.append((char)b);
        return sb.toString();
    }

    public int read3Bytes() {
        return (readUnsignedByte() << 16) | (readUnsignedByte() << 8) | readUnsignedByte();
    }

    public int read2Bytes() {
        return (readUnsignedByte() << 8) + readUnsignedByte();
    }

    public void writeUnsignedByte(int i) {
        if (outOffset >= outBuffer.length) {
            expandOutBuffer();
        }
        outBuffer[outOffset++] = (byte) i;
    }
	
	
    public void writeUnsignedByte(int i, int position) {
        if (position >= outBuffer.length) {
            expandOutBuffer();
        }
        outBuffer[position] = (byte) i;
    }
	
	public void writeSignedByte(int i) {
        if (outOffset >= outBuffer.length) {
            expandOutBuffer();
        }
        outBuffer[outOffset++] = (byte) (i & 0xff);
    }

    public void writeByteA(int i) {
        writeSignedByte(i + 128);
    }

    public void writeByteS(int i) {
        writeSignedByte(128 - i);
    }

    public void writeByteC(int i) {
        writeSignedByte(-i);
    }

    public void writeBytes(byte abyte0[], int i, int j) {
        for (int k = j; k < j + i; k++)
            writeSignedByte(abyte0[k]);
    }

    public void writeBytesS(byte abyte0[], int i, int j) {
        for (int k = j; k < j + i; k++)
            writeSignedByte(-128 + abyte0[k]);
    }

    public void writeBytesA(byte abyte0[], int i, int j) {
        for (int k = j; k < j + i; k++)
            writeSignedByte(abyte0[k] - 128);
    }

    public void writeBytesC(byte abyte0[], int i, int j) {
        for (int k = j; k < j + i; k++)
            writeSignedByte(abyte0[k] + 128);
    }

    public void writeBytes_reverse(byte abyte0[], int i, int j) {
        for (int k = (j + i) - 1; k >= j; k--) {
            writeSignedByte(abyte0[k]);
        }
    }

    public void writeBytes_reverseA(byte abyte0[], int i, int j) {
        for (int k = (j + i) - 1; k >= j; k--) {
            writeByteA(abyte0[k]);
        }
    }

    public void writeWordBigEndianA(int i) {
        writeSignedByte(i + 128);
        writeSignedByte(i >> 8);
    }

    public void writeWordA(int i) {
        writeSignedByte(i >> 8);
        writeSignedByte(i + 128);
    }

    public void writeDWord_v1(int i) {
        writeSignedByte(i >> 8);
        writeSignedByte(i);
        writeSignedByte(i >> 24);
        writeSignedByte(i >> 16);
    }

    public void writeDWord_v2(int i) {
        writeSignedByte(i >> 16);
        writeSignedByte(i >> 24);
        writeSignedByte(i);
        writeSignedByte(i >> 8);
    }

    public void writeVarSizeByte(int i) {
        if ((i & 255) >= 128) {
            writeWord(i - 32768);
        } else {
	    writeSignedByte(i);
        }
    }

    public void writeWord(int i) {
        writeSignedByte(i >> 8);
        writeSignedByte(i);
    }

    public void writeWordBigEndian(int i) {
        writeSignedByte(i);
        writeSignedByte(i >> 8);
    }

    public void write3Byte(int i) {
        writeSignedByte(i >> 16);
        writeSignedByte(i >> 8);
        writeSignedByte(i);
    }

    public void writeDWord(int i) {
        writeSignedByte(i >> 24);
        writeSignedByte(i >> 16);
        writeSignedByte(i >> 8);
        writeSignedByte(i);
    }

    public void writeDWordBigEndian(int i) {
        writeSignedByte(i);
        writeSignedByte(i >> 8);
        writeSignedByte(i >> 16);
        writeSignedByte(i >> 24);
    }

    public void writeQWord(long l) {
        writeSignedByte((int)(l >> 56));
        writeSignedByte((int)(l >> 48));
        writeSignedByte((int)(l >> 40));
        writeSignedByte((int)(l >> 32));
        writeSignedByte((int)(l >> 24));
        writeSignedByte((int)(l >> 16));
        writeSignedByte((int)(l >> 8));
        writeSignedByte((int)l);
    }

    public void writeString(String s) {
        byte[] stringBytes = s.getBytes();
        for (int i = 0; i < s.length(); i++)
            writeSignedByte(stringBytes[i]);
        writeSignedByte(0);
    }

    public void createFrame(int id) {
        writeSignedByte(id);
    }

    public void createFrameVarSize(int id) {
        writeSignedByte(id);
        writeSignedByte(0);
        if (frameStackPtr >= frameStackSize - 1) {
            throw new RuntimeException("Stack overflow");
        } else {
            frameStack[++frameStackPtr] = outOffset;
        }
    }

    public void createFrameVarSizeWord(int id) {
        writeSignedByte(id);
        writeWord(0);
        if (frameStackPtr >= frameStackSize-1) {
            Misc.println("Stack overflow");
        } else {
            frameStack[++frameStackPtr] = outOffset;
        }
    }

    public void endFrameVarSize() {
        if (frameStackPtr < 0) {
            Misc.println("Stack empty");
        } else {
            writeFrameSize(outOffset - frameStack[frameStackPtr--]);
        }
    }

    public void endFrameVarSizeWord() {
        if (frameStackPtr < 0) {
            Misc.println("Stack empty");
        } else {
            writeFrameSizeWord(outOffset - frameStack[frameStackPtr--]);
        }
    }

    public void writeFrameSize(int i) {
        writeUnsignedByte(i, (outOffset - i - 1));
    }

    public void writeFrameSizeWord(int i) {
        writeUnsignedByte((i >> 8), (outOffset - i - 2));
        writeUnsignedByte(i, (outOffset - i - 1));
    }

    public void initBitAccess() {
        bitPosition = outOffset * 8;
    }

    public void finishBitAccess() {
        outOffset = (bitPosition + 7) / 8;
    }

    public void addBit(int bit, int pos) {
        if (pos >= outBuffer.length) {
            expandOutBuffer();
        }
        outBuffer[pos] &= ~ bit;
    }

    public void placeBit(int bit, int pos) {
        if (pos >= outBuffer.length) {
            expandOutBuffer();
        }
        outBuffer[pos] |= bit;
    }

    public void writeBits(int numBits, int value) {
        int bytePos = bitPosition >> 3;
        int bitOffset = 8 - (bitPosition & 7);
        bitPosition += numBits;
        for (; numBits > bitOffset; bitOffset = 8) {
            addBit(bitMaskOut[bitOffset], bytePos);
            placeBit(((value >> (numBits - bitOffset)) & bitMaskOut[bitOffset]), bytePos++);
            numBits -= bitOffset;
        }
        if (numBits == bitOffset) {
            addBit(bitMaskOut[bitOffset], bytePos);
            placeBit((value & bitMaskOut[bitOffset]), bytePos);
        } else {
            addBit((bitMaskOut[numBits] << (bitOffset - numBits)), bytePos);
            placeBit((value & bitMaskOut[numBits]) << (bitOffset - numBits), bytePos);
        }
    }
}