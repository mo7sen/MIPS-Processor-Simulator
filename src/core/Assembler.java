package core;

import java.util.ArrayList;
import java.util.Arrays;

public class Assembler
{
	private static int byteCount;
	static ArrayList<Label> labels = new ArrayList<>();

	private static Label findLabel(String name)
	{
		for(Label l: labels)
		{
			if(l.getName().equals(name))
				return l;
		}
		return null;
	}

	static void assembleProgram(String s)
	{
		String[] ss = s.split("\\n+");

		byteCount = 0;
		Arrays.stream(ss).forEach(Assembler::scanForLabels);
		byteCount = 0;
		Arrays.stream(ss).forEach(Assembler::assembleLine);
	}

	static void scanForLabels(String s)
	{
		s.trim();
		int currentByte = byteCount;
		if(s.contains(":"))
		{
			String[] ss = s.split(":");
			labels.add(new Label(ss[0].trim(),Integer.toBinaryString(byteCount)));
			if(ss.length > 1)
				scanForLabels(ss[1].trim());

		}
		if( byteCount == currentByte)
			byteCount+=4;
	}

	private static void assembleLine(String str)
	{
		String s,t,d,imm,a;
		str = str.trim();
		if(str.split(":").length > 1)
		{
			assembleLine(str.split(":",2)[1]);
			return;
		}
		s = t = d = imm = a = null;
			String[] ss = str.replaceAll(",\\s+|\\s+", " ").split("\\s");
			Instruction i = Instruction.searchInstruction(ss[0].trim());
			if (i != null)
			{
				String syn = i.getSyntaxAsString(i);
				syn = syn.replaceAll("f+|o+", i.opc);
				switch (i.syn)
				{
					case ArithLog:
					case JumpR:
					case DivMult:
					case ShiftV:
					case MoveFrom:
					case MoveTo:
					case Shift:
						s = (i.syn == Syntax.JumpR || i.syn == Syntax.DivMult || i.syn == Syntax.MoveTo)?Registers.findRegister(ss[1]).address :(i.syn == Syntax.ShiftV)?Registers.findRegister(ss[3]).address:(i.syn != Syntax.MoveFrom && i.syn != Syntax.Shift)?Registers.findRegister(ss[2]).address:"00000";
						t = (i.syn == Syntax.JumpR || i.syn == Syntax.MoveFrom || i.syn == Syntax.MoveTo) ? "00000" : (i.syn == Syntax.DivMult || i.syn == Syntax.ShiftV || i.syn == Syntax.Shift) ? Registers.findRegister(ss[2]).address : Registers.findRegister(ss[3]).address;
						d = (i.syn == Syntax.JumpR || i.syn == Syntax.DivMult || i.syn == Syntax.MoveTo) ? "00000" : Registers.findRegister(ss[1]).address;
						a = (i.syn == Syntax.Shift)?Integer.toBinaryString(Integer.parseInt(ss[3])):"00000";
						break;
					case LoadI:
					case ArithLogI:
						t = Registers.findRegister(ss[1]).address;
						s = (i.syn == Syntax.LoadI) ?"00000" :Registers.findRegister(ss[2]).address;
						imm = (i.syn == Syntax.LoadI) ?Integer.toBinaryString(Integer.parseInt(ss[2])):Integer.toBinaryString(Integer.parseInt(ss[3]));
						break;
					case LoadStore:
						t = Registers.findRegister(ss[1]).address;
						imm = Integer.toBinaryString(Integer.parseInt(ss[2].split("\\(")[0]));
						s = Registers.findRegister(ss[2].split("\\(")[1].split("\\)")[0]).address;
						break;
					case BranchZ:
					case Branch:
						s = Registers.findRegister(ss[1]).address;
						t = Registers.findRegister(ss[2]).address;
						Label l = findLabel(ss[3].trim());
						if(l!=null)
							imm = Integer.toBinaryString((Integer.parseInt(l.getAddress(),2) - byteCount)/4 - 1 );
					case Jump:
						Label l2 = findLabel(ss[1]);
						if(l2!=null)
						imm = l2.getAddress().substring(4,30);
						break;
				}

				if (a!=null)
					a = SignExtend.extendUnsigned(a, 5);
				if (imm!=null)
					imm = SignExtend.extendUnsigned(imm,16);
				if  (imm!= null && i.syn == Syntax.Jump)
					imm = SignExtend.extendUnsigned(imm,26);


				if(imm!=null)
					syn = syn.replaceAll("i+", imm);
				if(d!=null)
					syn = syn.replaceAll("d+", d);
				if(t!=null)
				syn = syn.replaceAll("t+", t);
				if(a!=null)
					syn = syn.replaceAll("a+", a);
				if(s!=null)
					syn = syn.replaceAll("s+", s);
				if(syn != null)
				{
					InstructionMemory.add(syn);
					System.out.println(syn);
				}
			}

		byteCount += 4;
	}
}
class Label
{
	String name;
	String address;
	public Label(String name, String address)
	{
		this.name = name;
		while(address.length() < 32)
			address = "0" + address;
		this.address = address;
	}
	public String getName()
	{
		return this.name;
	}
	public String getAddress()
	{
		return this.address;
	}
}
enum Encoding
{
	Register,
	Immediate,
	Jump
}
enum Syntax
{
	ArithLog,
	DivMult,
	Shift,
	ShiftV,
	JumpR,
	MoveFrom,
	MoveTo,
	ArithLogI,
	LoadI,
	Branch,
	BranchZ,
	LoadStore,
	Jump,
	Trap
}
