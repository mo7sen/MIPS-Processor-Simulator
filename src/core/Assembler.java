package core;

import java.util.ArrayList;
import java.util.Arrays;

public class Assembler
{
	static ArrayList<Label> labels = new ArrayList<>();
	static ArrayList<String> codeLines;

	private static Label findLabel(String name)
	{
		for(Label lbl: labels)
		{
			if(lbl.getName().equals(name))
				return lbl;
		}
		return null;
	}

	static void assembleProgram(String s)
	{
		codeLines = new ArrayList<>(Arrays.asList(s.replaceAll(":", ":\n").split("\\n+")));
		labels.clear();
		scanForLabels();
		assembleLines();
	}

	static void scanForLabels()
	{
		for (int i = 0; i < codeLines.size();)
		{
			if(codeLines.get(i).contains(":"))
			{
				labels.add(new Label(codeLines.get(i).split(":")[0].trim(), Integer.toBinaryString(i * 4)));
				codeLines.set(i,"");
				continue;
			}
			i++;
		}
		codeLines.removeIf(String::isEmpty);
	}

	private static void assembleLines()
	{
		for(int i = 0; i < codeLines.size(); i++)
		{
			String str = null;
			String s, t, d, imm, a;
			str = codeLines.get(i).trim();
			s = t = d = imm = a = null;
			String[] ss = str.replaceAll(",\\s+|\\s+", " ").split("\\s");
			Instruction instruction = Instruction.searchInstruction(ss[0].trim());
			if (instruction != null)
			{
				String syn = instruction.getSyntaxAsString(instruction);
				syn = syn.replaceAll("f+|o+", instruction.opc);
				switch (instruction.syn)
				{
					case ArithLog:
					case JumpR:
					case DivMult:
					case ShiftV:
					case MoveFrom:
					case MoveTo:
					case Shift:
						s = (instruction.syn == Syntax.JumpR || instruction.syn == Syntax.DivMult || instruction.syn == Syntax.MoveTo) ? Registers.findRegister(ss[1]).address : (instruction.syn == Syntax.ShiftV) ? Registers.findRegister(ss[3]).address : (instruction.syn != Syntax.MoveFrom && instruction.syn != Syntax.Shift) ? Registers.findRegister(ss[2]).address : "00000";
						t = (instruction.syn == Syntax.JumpR || instruction.syn == Syntax.MoveFrom || instruction.syn == Syntax.MoveTo) ? "00000" : (instruction.syn == Syntax.DivMult || instruction.syn == Syntax.ShiftV || instruction.syn == Syntax.Shift) ? Registers.findRegister(ss[2]).address : Registers.findRegister(ss[3]).address;
						d = (instruction.syn == Syntax.JumpR || instruction.syn == Syntax.DivMult || instruction.syn == Syntax.MoveTo) ? "00000" : Registers.findRegister(ss[1]).address;
						a = (instruction.syn == Syntax.Shift) ? Integer.toBinaryString(Integer.parseInt(ss[3])) : "00000";
						break;
					case LoadI:
					case ArithLogI:
						t = Registers.findRegister(ss[1]).address;
						s = (instruction.syn == Syntax.LoadI) ? "00000" : Registers.findRegister(ss[2]).address;
						imm = (instruction.syn == Syntax.LoadI) ? Integer.toBinaryString(Integer.parseInt(ss[2])) : Integer.toBinaryString(Integer.parseInt(ss[3]));
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
						if (l != null)
							imm = Integer.toBinaryString((Integer.parseInt(l.getAddress(), 2)/4) - (i + 1));
					case Jump:
						Label l2 = findLabel(ss[1]);
						if (l2 != null)
							imm = l2.getAddress().substring(4, 30);
						break;
				}

				if (a != null)
					a = SignExtend.extendUnsigned(a, 5);
				if (imm != null)
					imm = SignExtend.extendUnsigned(imm, 16);
				if (imm != null && instruction.syn == Syntax.Jump)
					imm = SignExtend.extendUnsigned(imm, 26);


				if (imm != null)
					syn = syn.replaceAll("i+", imm);
				if (d != null)
					syn = syn.replaceAll("d+", d);
				if (t != null)
					syn = syn.replaceAll("t+", t);
				if (a != null)
					syn = syn.replaceAll("a+", a);
				if (s != null)
					syn = syn.replaceAll("s+", s);
				if (syn != null)
				{
					InstructionMemory.add(syn);
					System.out.println(syn);
				}
			}
		}
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
{Register,Immediate,Jump}

enum Syntax
{ArithLog,DivMult,Shift,ShiftV,JumpR,MoveFrom,MoveTo,ArithLogI,LoadI,Branch,BranchZ,LoadStore,Jump,Trap}