package net.com.codeusa.net.codec;

/**
 * @author Codeusa
 *
 * It was ment to be a Stream class.
 */

public class RSOutputStream {

	private int bitPosition = 0;
	private int[] frameStack = new int[this.frameStackSize];
	private int frameStackPtr = -1;
	private int frameStackSize = 10;
	private int outOffset = 0;
	private byte[] writeBuffer;

	public int getBitPosition() {
		return this.bitPosition;
	}

	public int[] getFrameStack() {
		return this.frameStack;
	}

	public int getFrameStackPtr() {
		return this.frameStackPtr;
	}

	public int getFrameStackSize() {
		return this.frameStackSize;
	}

	public int getOffset() {
		return this.outOffset;
	}

	public byte[] getWriteBuffer() {
		return this.writeBuffer;
	}

	public int setBitPosition(int _bitPos) {
		return this.bitPosition = _bitPos;
	}

	public int[] setFrameStack(int[] _frameStack) {
		return this.frameStack = _frameStack;
	}

	public int setFrameStackPtr(int _frameStack) {
		return this.frameStackPtr = _frameStack;
	}

	public int setFrameStackSize(int _frameSize) {
		return this.frameStackSize = _frameSize;
	}

	public int setOffset(int _offset) {
		return this.outOffset = _offset;
	}

	public byte[] writeBuffer(byte[] _buffer) {
		return this.writeBuffer = _buffer;
	}

}
