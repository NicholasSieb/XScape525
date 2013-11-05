package net.com.codeusa.net.codec;

/**
 * @author Jordan <none> <none>
 *
 * It was ment to be a Stream class.
 */

public class RSInputStream {

	public int inOffset = 0;
	private byte[] readBuffer;
	private int readCapacity;

	public RSInputStream(int _inCapacity) {
		this.readCapacity = _inCapacity;
		this.readBuffer = new byte[this.getCapacity()];
	}

	public int getCapacity() {
		return this.readCapacity;
	}

	public int read2Bytes() {
		return (this.readUnsignedByte() << 8) + this.readUnsignedByte();
	}

	public int read3Bytes() {
		return (this.readUnsignedByte() << 16) | (this.readUnsignedByte() << 8)
				| this.readUnsignedByte();
	}

	public void readBytes(byte abyte0[], int i, int j) {
		for (int k = j; k < j + i; k++) {
			abyte0[k] = this.readSignedByte();
		}
	}

	public void readBytes_reverse(byte abyte0[], int i, int j) {
		for (int k = (j + i) - 1; k >= j; k--) {
			abyte0[k] = this.readSignedByte();
		}
	}

	public void readBytes_reverseA(byte abyte0[], int i, int j) {
		for (int k = (j + i) - 1; k >= j; k--) {
			abyte0[k] = this.readSignedByteA();
		}
	}

	public int readDWord() {
		this.inOffset += 4;
		return ((this.readBuffer[this.inOffset - 4] & 0xff) << 24)
				+ ((this.readBuffer[this.inOffset - 3] & 0xff) << 16)
				+ ((this.readBuffer[this.inOffset - 2] & 0xff) << 8)
				+ (this.readBuffer[this.inOffset - 1] & 0xff);
	}

	public int readDWord_v1() {
		this.inOffset += 4;
		return ((this.readBuffer[this.inOffset - 2] & 0xff) << 24)
				+ ((this.readBuffer[this.inOffset - 1] & 0xff) << 16)
				+ ((this.readBuffer[this.inOffset - 4] & 0xff) << 8)
				+ (this.readBuffer[this.inOffset - 3] & 0xff);
	}

	public int readDWord_v2() {
		this.inOffset += 4;
		return ((this.readBuffer[this.inOffset - 3] & 0xff) << 24)
				+ ((this.readBuffer[this.inOffset - 4] & 0xff) << 16)
				+ ((this.readBuffer[this.inOffset - 1] & 0xff) << 8)
				+ (this.readBuffer[this.inOffset - 2] & 0xff);
	}

	public long readQWord() {
		long l = this.readDWord() & 0xffffffffL;
		long l1 = this.readDWord() & 0xffffffffL;
		return (l << 32) + l1;
	}

	public long readQWord2() {
		this.inOffset += 8;
		return (((this.readBuffer[this.inOffset - 8] & 0xff) << 56)
				+ ((this.readBuffer[this.inOffset - 7] & 0xff) << 48)
				+ ((this.readBuffer[this.inOffset - 6] & 0xff) << 40)
				+ ((this.readBuffer[this.inOffset - 5] & 0xff) << 32)
				+ ((this.readBuffer[this.inOffset - 4] & 0xff) << 24)
				+ ((this.readBuffer[this.inOffset - 3] & 0xff) << 16)
				+ ((this.readBuffer[this.inOffset - 2] & 0xff) << 8) + (this.readBuffer[this.inOffset - 1] & 0xff));
	}

	public byte readSignedByte() {
		return (this.readBuffer[this.inOffset++]);
	}

	public byte readSignedByteA() {
		return (byte) (this.readSignedByte() - 128);
	}

	public byte readSignedByteC() {
		return (byte) (-this.readSignedByte());
	}

	public byte readSignedByteS() {
		return (byte) (128 - this.readSignedByte());
	}

	public int readSignedWord() {
		this.inOffset += 2;
		int i = ((this.readBuffer[this.inOffset - 2] & 0xff) << 8)
				+ (this.readBuffer[this.inOffset - 1] & 0xff);
		if (i > 32767) {
			i -= 0x10000;
		}
		return i;
	}

	public int readSignedWordA() {
		this.inOffset += 2;
		int i = ((this.readBuffer[this.inOffset - 2] & 0xff) << 8)
				+ (this.readBuffer[this.inOffset - 1] - 128 & 0xff);
		if (i > 32767) {
			i -= 0x10000;
		}
		return i;
	}

	public int readSignedWordBigEndian() {
		this.inOffset += 2;
		int i = ((this.readBuffer[this.inOffset - 1] & 0xff) << 8)
				+ (this.readBuffer[this.inOffset - 2] & 0xff);
		if (i > 32767) {
			i -= 0x10000;
		}
		return i;
	}

	public int readSignedWordBigEndianA() {
		this.inOffset += 2;
		int i = ((this.readBuffer[this.inOffset - 1] & 0xff) << 8)
				+ (this.readBuffer[this.inOffset - 2] - 128 & 0xff);
		if (i > 32767) {
			i -= 0x10000;
		}
		return i;
	}

	public String readString() {
		StringBuffer sb = new StringBuffer();
		byte b;
		while ((b = this.readSignedByte()) != 0) {
			sb.append((char) b);
		}
		return sb.toString();
	}

	public int readUnsignedByte() {
		return (this.readSignedByte() & 0xff);
	}

	public int readUnsignedByteA() {
		return (this.readUnsignedByte() - 128 & 0xff);
	}

	public int readUnsignedByteC() {
		return -(this.readUnsignedByte() & 0xff);
	}

	public int readUnsignedByteS() {
		return (128 - this.readUnsignedByte() & 0xff);
	}

	public int readUnsignedWord() {
		this.inOffset += 2;
		return ((this.readBuffer[this.inOffset - 2] & 0xff) << 8)
				+ (this.readBuffer[this.inOffset - 1] & 0xff);
	}

	public int readUnsignedWordA() {
		this.inOffset += 2;
		return ((this.readBuffer[this.inOffset - 2] & 0xff) << 8)
				+ (this.readBuffer[this.inOffset - 1] - 128 & 0xff);
	}

	public int readUnsignedWordBigEndian() {
		this.inOffset += 2;
		return ((this.readBuffer[this.inOffset - 1] & 0xff) << 8)
				+ (this.readBuffer[this.inOffset - 2] & 0xff);
	}

	public int readUnsignedWordBigEndianA() {
		this.inOffset += 2;
		return ((this.readBuffer[this.inOffset - 1] & 0xff) << 8)
				+ (this.readBuffer[this.inOffset - 2] - 128 & 0xff);
	}

}
