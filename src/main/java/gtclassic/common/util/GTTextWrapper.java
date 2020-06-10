package gtclassic.common.util;

import java.util.ArrayList;
import java.util.List;

import ic2.api.classic.network.INetworkFieldData;
import ic2.api.classic.network.adv.IInputBuffer;
import ic2.api.classic.network.adv.IOutputBuffer;

public class GTTextWrapper implements INetworkFieldData {

	List<String> text = new ArrayList<>();

	public GTTextWrapper() {
	}

	@Override
	public void read(IInputBuffer in) {
		text.clear();
		int size = in.readInt();
		for (int i = 0; i < size; i++) {
			text.add(in.readString());
		}
	}

	@Override
	public void write(IOutputBuffer out) {
		out.writeInt(text.size());
		for (int i = 0; i < text.size(); i++) {
			out.writeString(text.get(i));
		}
	}

	public List<String> getWrapperList() {
		return this.text;
	}
}
